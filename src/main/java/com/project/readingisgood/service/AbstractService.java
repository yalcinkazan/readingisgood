package com.project.readingisgood.service;

import com.project.readingisgood.component.DozerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractService {

    @Autowired
    protected DozerMapper mapper;

}
