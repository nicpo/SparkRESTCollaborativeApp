package com.np.metastore.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Arrays;

@XmlRootElement(name="CSVFormat")
@XmlAccessorType(XmlAccessType.NONE)
public class CSVFormat implements Serializable {
    @XmlElement
    private boolean withHeader;

    @XmlElement
    private char delimiter;

    @XmlElement
    private String[] headers;

    @XmlElement
    private String[] types;

    public CSVFormat() {
    }

    public CSVFormat(boolean withHeader, char delimiter, String[] headers, String[] types) {
        this.withHeader = withHeader;
        this.delimiter = delimiter;
        this.headers = headers;
        this.types = types;
    }

    public boolean isWithHeader() {
        return withHeader;
    }

    public void setWithHeader(boolean withHeader) {
        this.withHeader = withHeader;
    }

    public char getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "CSVFormat{" +
                "withHeader=" + withHeader +
                ", delimiter=" + delimiter +
                ", headers=" + Arrays.toString(headers) +
                ", types=" + Arrays.toString(types) +
                '}';
    }
}
