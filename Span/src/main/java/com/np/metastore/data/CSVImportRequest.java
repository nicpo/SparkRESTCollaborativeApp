package com.np.metastore.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement(name="CSVImportRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class CSVImportRequest {
    @XmlElement
    private String bucket;

    @XmlElement
    private String key;

    @XmlElement
    private CSVFormat format;

    @XmlElement
    private int partitionSize;

    @XmlElement
    private String parquetURL;

    @XmlElement
    private String[] columns;

    public CSVImportRequest() {
    }

    // Used when we should guess format
    public CSVImportRequest(String bucket, String key) {
        this.bucket = bucket;
        this.key = key;
    }

    // used when we like to export data
    public CSVImportRequest(String bucket, String key, CSVFormat format, int partitionSize, String parquetURL, String[] columns) {
        this.bucket = bucket;
        this.key = key;
        this.format = format;
        this.partitionSize = partitionSize;
        this.parquetURL = parquetURL;
        this.columns = columns;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public CSVFormat getFormat() {
        return format;
    }

    public void setFormat(CSVFormat format) {
        this.format = format;
    }

    public int getPartitionSize() {
        return partitionSize;
    }

    public void setPartitionSize(int partitionSize) {
        this.partitionSize = partitionSize;
    }

    public String getParquetURL() {
        return parquetURL;
    }

    public void setParquetURL(String parquetURL) {
        this.parquetURL = parquetURL;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "CSVImportRequest{" +
                "bucket='" + bucket + '\'' +
                ", key='" + key + '\'' +
                ", format=" + format +
                ", partitionSize=" + partitionSize +
                ", parquetURL='" + parquetURL + '\'' +
                ", columns=" + Arrays.toString(columns) +
                '}';
    }
}
