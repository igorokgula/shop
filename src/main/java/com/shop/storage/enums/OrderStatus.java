package com.shop.storage.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by Igor on 25.06.2015.
 */
public enum OrderStatus {
    DELIVERED("delivered"),

    CANCELED("canceled"),

    EXPECTED_DELIVERY("expectedDelivery");

    private String name;

    private OrderStatus(String name){
        this.name = name;
    }

    private static final Map<String, OrderStatus> map =
            new HashMap<String, OrderStatus>();

    static {
        for (OrderStatus type : OrderStatus.values()) {
            map.put(type.name, type);
        }
    }

    public String getName() {
        return name;
    }

    public static OrderStatus fromString(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        throw new NoSuchElementException(name + "not found");
    }
}
