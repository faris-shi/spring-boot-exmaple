package com.github.faris.springboot.actuator.monitor;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "memory")
public class MemoryActuatorEndpoint {

    @ReadOperation
    public Map<String, Long> readMemory() {
        Map<String, Long> map = new HashMap<>();
        map.put("free", Runtime.getRuntime().freeMemory());
        map.put("total", Runtime.getRuntime().totalMemory());
        map.put("max", Runtime.getRuntime().maxMemory());
        return map;
    }
}
