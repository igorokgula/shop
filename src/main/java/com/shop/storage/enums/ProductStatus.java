package com.shop.storage.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by Igor on 27.06.2015.
 */
public enum ProductStatus {
    FREE("free"),

    BOUGHT("bought");

    private String name;

    private ProductStatus(String name){
        this.name = name;
    }

    private static final Map<String, ProductStatus> map =
            new HashMap<String, ProductStatus>();

    static {
        for (ProductStatus type : ProductStatus.values()) {
            map.put(type.name, type);
        }
    }

    public String getName() {
        return name;
    }

    public static ProductStatus fromString(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        throw new NoSuchElementException(name + "not found");
    }
}
