package com.blanc.market.domain.product.repository;

import com.blanc.market.domain.product.entity.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class ProductCategoryConverter implements Converter<String, Category> {

    @Override
    public Category convert(String source) {
        return Category.fromValue(source);
    }
}


