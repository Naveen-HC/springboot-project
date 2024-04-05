package com.navi.school.entity;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
public class Holiday extends BaseEntity{

    public enum Type {
        FESTIVAL, FEDERAL
    }

    private String day;
    private String reason;
    private Type type;


}
