package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.Json;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {
    @Autowired
    CustomerService customerService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/test")
    public String test() throws JsonProcessingException {
        Customer customer=customerService.getById(1);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        jsonStr =  mapper.writeValueAsString(customer);
        return jsonStr;
    }
}
