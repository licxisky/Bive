package com.lichaoxi.bive.bive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/install")
@RestController
public class InstallController {

    @GetMapping({"", "/", "/index"})
    public Map index() {
        Map map = new HashMap();
        map.put("id", 1);
        map.put("name", "LiCxi");
        map.put("route", "InstallController@index");
        return map;
    }
}
