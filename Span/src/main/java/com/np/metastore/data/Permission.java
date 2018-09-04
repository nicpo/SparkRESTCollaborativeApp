package com.np.metastore.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.ResultSet;
import java.sql.SQLException;

@XmlRootElement(name="Permission")
@XmlAccessorType(XmlAccessType.NONE)
public class Permission {
    @XmlElement
    private String id;

    /**
     * Permission type: VIEW, COMMENT, ANALYZE, SHARE
     */
    @XmlElement
    private String type;

    @XmlElement
    private String creator;

    @XmlElement
    private String user;

    @XmlElement
    private String dataset;

    @XmlElement
    private String analysis;

    public Permission() {
    }

    public Permission(ResultSet res) throws SQLException
    {
        this(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6));

    }

    public Permission(String id, String type, String creator, String user, String dataset, String analysis) {
        this.id = id;
        this.type = type;
        this.creator = creator;
        this.user = user;
        this.dataset = dataset;
        this.analysis = analysis;
    }

    public Permission(String type, String user, String dataset, String analysis) {
        this.type = type;
        this.user = user;
        this.dataset = dataset;
        this.analysis = analysis;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", creator='" + creator + '\'' +
                ", user='" + user + '\'' +
                ", dataset='" + dataset + '\'' +
                ", analysis='" + analysis + '\'' +
                '}';
    }
}
