package com.np.metastore.kernel;

import com.np.metastore.data.FieldType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVTypeDetector {
    private final static Pattern longPattern = Pattern.compile("(-|\\+)?[0-9]+");
    private final static Pattern doublePattern = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?");

    int rowCounter=0;

    int longExceptionCounter=0;
    int doubleExceptionCounter=0;

    private boolean isLong(String val) {
        Matcher m = longPattern.matcher(val);
        return m.matches();
    }

    private boolean isDouble(String val) {
        Matcher m = doublePattern.matcher(val);
        return m.matches();
    }

    public CSVTypeDetector() {
    }

    void apply(String value)
    {
        rowCounter++;

        if (!isLong(value))
            longExceptionCounter++;

        if (!isDouble(value))
            doubleExceptionCounter++;
    }

    public FieldType getType(double maxErrorRate)
    {
        if ((double)longExceptionCounter/rowCounter < maxErrorRate)
            return FieldType.LONG;

        if ((double)doubleExceptionCounter/rowCounter < maxErrorRate)
            return FieldType.DOUBLE;

        return FieldType.STRING;
    }
}
