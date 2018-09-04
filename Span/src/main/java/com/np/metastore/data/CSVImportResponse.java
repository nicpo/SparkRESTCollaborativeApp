package com.np.metastore.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="CSVImportResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class CSVImportResponse {
    @XmlElement
    private int receivedLines;

    @XmlElement
    private int processedLines;

    @XmlElement
    private List<String> failedLines;

    public CSVImportResponse() {
    }

    public CSVImportResponse(List<String> failedLines, int receivedLines, int processedLines) {
        this.failedLines = failedLines;
        this.receivedLines = receivedLines;
        this.processedLines = processedLines;
    }

    public List<String> getFailedLines() {
        return failedLines;
    }

    public void setFailedLines(List<String> failedLines) {
        this.failedLines = failedLines;
    }

    public int getReceivedLines() {
        return receivedLines;
    }

    public void setReceivedLines(int receivedLines) {
        this.receivedLines = receivedLines;
    }

    public int getProcessedLines() {
        return processedLines;
    }

    public void setProcessedLines(int processedLines) {
        this.processedLines = processedLines;
    }
}
