package com.np.metastore.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.ResultSet;
import java.sql.SQLException;

@XmlRootElement(name="DatasetSize")
@XmlAccessorType(XmlAccessType.NONE)
public class DatasetSize {
    @XmlElement
    private long fileSize;

    @XmlElement
    private long recordCount;

    @XmlElement
    private long columnCount;

    public DatasetSize() {
    }

    public DatasetSize(long fileSize, long recordCount, long columnCount) {
        this.fileSize = fileSize;
        this.recordCount = recordCount;
        this.columnCount = columnCount;
    }

    public long getFileSize() {
        return fileSize;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public long getColumnCount() {
        return columnCount;
    }

    @Override
    public String toString() {
        return "DatasetSize{" +
                "fileSize=" + fileSize +
                ", recordCount=" + recordCount +
                ", columnCount=" + columnCount +
                '}';
    }
}
