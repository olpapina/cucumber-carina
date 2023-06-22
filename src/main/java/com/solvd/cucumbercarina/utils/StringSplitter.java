package com.solvd.cucumbercarina.utils;

import org.apache.commons.exec.util.StringUtils;

import java.util.List;

public class StringSplitter {
    public static List<String> getOrderProducts(String order) {
        order= order.replaceAll("[}\"{]", "");
        return List.of(StringUtils.split(order, ","));
    }
}
