package com.np.ui;

import com.vaadin.data.util.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimestampConverter implements Converter<String, Long> {
    @Override
    public String convertToPresentation(Long value, Class<? extends String> targetType, Locale locale) throws ConversionException {
        if (value==null)
            return "";

        return SimpleDateFormat.getInstance().format(new Date(value));

    }

    @Override
    public Long convertToModel(String value, Class<? extends Long> targetType, Locale locale) throws ConversionException {
        return null;
    }

    @Override
    public Class<Long> getModelType() {
        return Long.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}
