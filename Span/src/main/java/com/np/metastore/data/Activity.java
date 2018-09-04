package com.np.metastore.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.ResultSet;
import java.sql.SQLException;

@XmlRootElement(name="Activity")
@XmlAccessorType(XmlAccessType.NONE)
public class Activity {
    @XmlElement
    private String id;

    @XmlElement
    private String type;

    @XmlElement
    private String user;

    @XmlElement
    private String dataset_id;

    @XmlElement
    private String dataset_name;

    @XmlElement
    private String analysis_id;

    @XmlElement
    private String analysis_name;

    @XmlElement
    private String target;

    @XmlElement
    private long timestamp;

    @XmlElement
    private String info;

    public Activity() {
    }

    public Activity(ResultSet res) throws SQLException
    {
        this(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getLong(9), res.getString(10));
    }

    public Activity(String id, String type, String user, String dataset_id, String dataset_name, String analysis_id, String analysis_name, String target, long timestamp, String info) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.dataset_id = dataset_id;
        this.dataset_name = dataset_name;
        this.analysis_id = analysis_id;
        this.analysis_name = analysis_name;
        this.target = target;
        this.timestamp = timestamp;
        this.info = info;
    }

    public Activity(String id, String type, String user, String dataset_id, String analysis_id, String target, long timestamp, String info) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.dataset_id = dataset_id;
        this.analysis_id = analysis_id;
        this.target = target;
        this.timestamp = timestamp;
        this.info = info;
    }

    public Activity(String type, String user, String dataset_id, String analysis_id, String target, String info) {
        this.type = type;
        this.user = user;
        this.dataset_id = dataset_id;
        this.analysis_id = analysis_id;
        this.target = target;
        this.info = info;
    }

    public Activity(String type, String user, String dataset_id, String analysis_id, String target) {
        this.type = type;
        this.user = user;
        this.dataset_id = dataset_id;
        this.analysis_id = analysis_id;
        this.target = target;
    }

    public Activity(String type, String user, String target, String info) {
        this.type = type;
        this.user = user;
        this.target = target;
        this.info = info;
    }

    public Activity(String type, String user, String info) {
        this.type = type;
        this.user = user;
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() { return user; }

    public void setUser(String user) { this.user = user; }

    public String getDataset_id() { return dataset_id; }

    public String getDataset_name() { return dataset_name; }

    public void setDataset_id(String dataset_id) { this.dataset_id = dataset_id; }

    public void setDataset_name(String dataset_name) { this.dataset_name = dataset_name; }

    public String getAnalysis_id() { return analysis_id; }

    public String getAnalysis_name() { return analysis_name; }

    public void setAnalysis_id(String analysis_id) { this.analysis_id = analysis_id; }

    public void setAnalysis_name(String analysis_name) { this.analysis_name = analysis_name; }

    public String getTarget() { return target; }

    public void setTarget(String target) { this.target = target; }

    public Long getTimestamp() { return timestamp; }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", dataset_id='" + dataset_id + '\'' +
                ", dataset_name='" + dataset_name + '\'' +
                ", analysis_id='" + analysis_id + '\'' +
                ", analysis_name='" + analysis_name + '\'' +
                ", target='" + target + '\'' +
                ", timestamp=" + timestamp +
                ", info='" + info + '\'' +
                '}';
    }
}