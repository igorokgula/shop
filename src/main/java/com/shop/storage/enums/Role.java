package com.shop.storage.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by Igor on 27.06.2015.
 */
public enum Role {
    ADMIN("admin"),

    USER("user");

    private String name;

    private Role(String name){
        this.name = name;
    }

    private static final Map<String, Role> map =
            new HashMap<String, Role>();

    static {
        for (Role type : Role.values()) {
            map.put(type.name, type);
        }
    }

    public String getName() {
        return name;
    }

    public static Role fromString(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        throw new NoSuchElementException(name + "not found");
    }
}
