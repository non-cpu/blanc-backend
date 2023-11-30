package com.blanc.market.domain.product.entity;

public enum Category {
    SUNCARE,
    cleansing,
    Skincare;

    public static Category fromValue(String value) {
        for (Category category : values()) {
            if (category.name().equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException("No constant");
    }
}
