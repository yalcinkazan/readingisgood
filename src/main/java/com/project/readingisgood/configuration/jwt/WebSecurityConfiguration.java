package com.project.readingisgood.configuration.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.readingisgood.component.JwtTokenFilter;
import com.project.readingisgood.enums.AuthorizationStatus;
import com.project.readingisgood.enums.TokenType;
import com.project.readingisgood.exception.model.TokenUnauthorized;
import com.project.readingisgood.service.UserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfiguration.class);

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    public void configurePasswordEncoder(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(this.userDetailService).passwordEncoder(getBCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            response.setStatus(401);
            response.setHeader("content-type", "application/json");
            ObjectMapper mapper = new ObjectMapper();
            TokenUnauthorized tokenUnauthorized = new TokenUnauthorized();
            tokenUnauthorized.setErrorCode(401);
            tokenUnauthorized.setResponseStatus(AuthorizationStatus.UNAUTHORIZED);
            tokenUnauthorized.setErrorMessage("Authentication is not success");
            tokenUnauthorized.setTokenType(TokenType.BEARER);
            String responseMsg = mapper.writeValueAsString(tokenUnauthorized);
            response.getWriter().write(responseMsg);
            LOGGER.error("Authentication is not success");
        }).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilterBefore(this.jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers(HttpMethod.POST, "/login")
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .and().ignoring()
                .antMatchers(HttpMethod.GET,"/")
                .and().ignoring()
                .antMatchers("/h2-console/**/**", "/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**");// Available for tests
    }

}
