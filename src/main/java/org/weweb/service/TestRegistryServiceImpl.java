package org.weweb.service;


import org.springframework.stereotype.Service;

/**
 * Created by jackshen on 2017/3/20.
 */
@Service
public class TestRegistryServiceImpl implements TestRegistryService {
    public String hello(String name) {
        return "hello" + name;
    }
}