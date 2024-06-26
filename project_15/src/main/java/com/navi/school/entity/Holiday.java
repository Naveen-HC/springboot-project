package com.navi.school.entity;

import lombok.Data;

@Data
public class Holiday {

    public enum Type {
        FESTIVAL, FEDERAL
    }

    private final String day;
    private final String reason;
    private final Type type;


}
