package com.sayantan.retail.controller;

import com.sayantan.retail.type.StatusType;

import java.util.LinkedHashMap;
import java.util.Map;

public class BasicController {

    protected Map map(StatusType status, Object data) {

        Map map = new LinkedHashMap();
        map.put("status", status);
        map.put("data", data);

        return map;
    }
}
