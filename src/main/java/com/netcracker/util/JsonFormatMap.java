package com.netcracker.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.models.ManagerSubBill;
import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.Map;


@Log4j
public class JsonFormatMap {

    public static Map<String, Double> transformMapToJson(Map<ManagerSubBill, Double> map) throws JsonProcessingException {
        Map<String, Double> formattedMap = new HashMap<>();
        for (Map.Entry<ManagerSubBill, Double> mapToTransform: map.entrySet()) {
            try {
                formattedMap.put(new ObjectMapper().writeValueAsString(mapToTransform.getKey()), mapToTransform.getValue());
            }catch (JsonProcessingException e)
            {
                log.warn("IN transformMapToJson cant parse map to json",e);
                throw e;
            }

        }
        return formattedMap;
    }
}
