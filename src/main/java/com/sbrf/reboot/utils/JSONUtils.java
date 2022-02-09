package com.sbrf.reboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbrf.reboot.dto.Request;
import com.sbrf.reboot.dto.Response;

public class JSONUtils {
    static ObjectMapper objectMapper=new ObjectMapper();

    static String toJSON(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    static Request JSONtoRequest(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, Request.class);
    }

    static Response JSONtoResponse(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, Response.class);
    }
}
