package com.github.faris.springboot.actuator.monitor;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CustomerHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        if(check()){
            return Health.up().withDetail("Service", "Service is running").build();
        }
        return Health.down().withDetail("Service", "Service is not available").build();
    }

    private boolean check() {
        int random = new Random().nextInt(10);
        return random % 2 == 0;
    }
}
