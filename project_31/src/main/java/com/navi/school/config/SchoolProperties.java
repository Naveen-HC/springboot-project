package com.navi.school.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "school")
@Data
public class SchoolProperties {
    
    private String name;
    private String profile;
    private List<String> branches;
    private Map<String, String> contact;
}
