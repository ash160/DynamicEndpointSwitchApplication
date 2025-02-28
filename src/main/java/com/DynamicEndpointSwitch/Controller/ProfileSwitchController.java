package com.DynamicEndpointSwitch.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/profile")
public class ProfileSwitchController {

    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    private ApplicationContext applicationContext;

    @PostMapping("/switch/{profile}")
    public String switchProfile(@PathVariable String profile) {
        if (!profile.equals("dev") && !profile.equals("sit")) {
            return "Invalid profile! Use 'dev' or 'sit'.";
        }

        environment.setActiveProfiles(profile);
        applicationContext.publishEvent(new EnvironmentChangeEvent(Set.of("spring.profiles.active")));

        return "Switched to profile: " + profile + ". Now call /actuator/refresh to apply changes.";
    }

    @GetMapping("/current")
    public String getActiveProfile() {
        String[] activeProfiles = environment.getActiveProfiles();
        return activeProfiles.length > 0 ? "Active Profile: " + String.join(", ", activeProfiles)
                : "No active profile set";
    }
}