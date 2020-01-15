package com.example.db_connect.service;


import com.example.db_connect.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class HelloService {
    @Autowired
    private TestDao testDao;

    public Map<String, Map<String, Object>> getTest() throws Exception {
        return Collections.singletonMap("test", testDao.getName(1));
    }
}
