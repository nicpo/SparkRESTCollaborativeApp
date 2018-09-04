package com.np.ui;

import com.vaadin.data.util.converter.StringToIntegerConverter;

import java.util.Locale;

public class CheckboxConverter extends StringToIntegerConverter {
    @Override
    public String convertToPresentation(Integer value, Class<? extends String> targetType, Locale locale) throws ConversionException {
        if (value==null)
            return "☐";

        if (value==1)
            return "☒";
        else
            return "☐";
    }
}
