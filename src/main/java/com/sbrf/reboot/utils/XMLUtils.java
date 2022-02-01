package com.sbrf.reboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sbrf.reboot.dto.Request;
import com.sbrf.reboot.dto.Response;

public class XMLUtils {
    static XmlMapper xmlMapper=new XmlMapper();

    static String toXML(Object obj) throws JsonProcessingException {
        return xmlMapper.writeValueAsString(obj);
    }

    static Request XMLtoRequest(String xml) throws JsonProcessingException {
        return xmlMapper.readValue(xml,Request.class);
    }

    static Response XMLtoResponse(String xml) throws JsonProcessingException {
        return xmlMapper.readValue(xml,Response.class);
    }
}
