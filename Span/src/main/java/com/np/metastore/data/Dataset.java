package com.np.metastore.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.ResultSet;
import java.sql.SQLException;

@XmlRootElement(name="Dataset")
@XmlAccessorType(XmlAccessType.NONE)
public class Dataset {
    @XmlElement
    private String id;

    @XmlElement
    private String name;

    @XmlElement
    private String url;

    @XmlElement
    private String table;

    @XmlElement
    private String query; // used ONLY if dataset created from another one with query

    @XmlElement
    private String description;

    @XmlElement
    private String info;

    @XmlElement
    private long timestamp;

    @XmlElement
    private String user;

    @XmlElement
    private String size;

    public Dataset() {
    }

    public Dataset(ResultSet res) throws SQLException
    {
        this(res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getLong(8),res.getString(9), res.getString(10));
    }

    public Dataset(String id, String name, String url, String table, String query, String description, String info, long timestamp, String user, String size) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.table = table;
        this.query = query;
        this.description = description;
        this.info = info;
        this.timestamp = timestamp;
        this.user = user;
        this.size = size;
    }

    public Dataset(String name, String url, String table, String query, String description, String info) {
        this.name = name;
        this.url = url;
        this.table = table;
        this.query = query;
        this.description = description;
        this.info = info;
    }

    public Dataset(String name, String url, String table, String query, String description, String info, String user, String size) {
        this.name = name;
        this.url = url;
        this.table = table;
        this.query = query;
        this.description = description;
        this.info = info;
        this.user = user;
        this.size = size;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Dataset{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", table='" + table + '\'' +
                ", query='" + query + '\'' +
                ", description='" + description + '\'' +
                ", info='" + info + '\'' +
                ", timestamp=" + timestamp +
                ", user='" + user + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
