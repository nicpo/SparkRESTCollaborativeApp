package com.np.metastore.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.ResultSet;
import java.sql.SQLException;

@XmlRootElement(name="Analysis")
@XmlAccessorType(XmlAccessType.NONE)
public class Analysis {
    @XmlElement
    private String id;

    @XmlElement
    private String name;

    @XmlElement
    private String type;

    @XmlElement
    private String query; // query used to filter data for analysis

    @XmlElement
    private String user;

    @XmlElement
    private String request;

    @XmlElement
    private String state;

    @XmlElement
    private String output;

    @XmlElement
    private long startTime;

    @XmlElement
    private long endTime;

    @XmlElement
    private String description;

    public Analysis() {
    }

    public Analysis(ResultSet res) throws SQLException {
        this(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getLong(9), res.getLong(10), res.getString(11));
    }

    public Analysis(String id, String name, String type, String query, String user, String request, String state, String output, long startTime, long endTime, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.query = query;
        this.user = user;
        this.request = request;
        this.state = state;
        this.output = output;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    public Analysis(String name, String type, String query, String user, String request, String state, String output) {
        this.name = name;
        this.type = type;
        this.query = query;
        this.user = user;
        this.request = request;
        this.state = state;
        this.output = output;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Analysis{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", query='" + query + '\'' +
                ", user='" + user + '\'' +
                ", request='" + request + '\'' +
                ", state='" + state + '\'' +
                ", output='" + output + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", description='" + description + '\'' +
                '}';
    }
}
