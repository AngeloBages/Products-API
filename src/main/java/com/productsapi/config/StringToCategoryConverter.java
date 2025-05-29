package com.productsapi.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.productsapi.model.Category;

@Component
public class StringToCategoryConverter implements Converter<String, Category> {
    @Override
    public Category convert(String source) {
        return Category.valueOf(source.trim().toUpperCase());
    }
}