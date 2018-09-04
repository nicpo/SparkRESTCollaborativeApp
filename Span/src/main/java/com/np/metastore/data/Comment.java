package com.np.metastore.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.ResultSet;
import java.sql.SQLException;

@XmlRootElement(name="Comment")
@XmlAccessorType(XmlAccessType.NONE)
public class Comment {
    @XmlElement
    private String id;

    @XmlElement
    private String user;

    @XmlElement
    private long created;

    @XmlElement
    private long updated;

    @XmlElement
    private String text;

    @XmlElement
    private String dataset;

    @XmlElement
    private String analysis;

    @XmlElement
    private String target;

    @XmlElement
    private String reference;

    public Comment() {
    }

    public Comment (ResultSet res) throws SQLException
    {
        this(res.getString(1), res.getString(2), res.getLong(3), res.getLong(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9));
    }

    public Comment(String id, String user, long created, long updated, String text, String dataset, String analysis, String target, String reference) {
        this.id = id;
        this.user = user;
        this.created = created;
        this.updated = updated;
        this.text = text;
        this.dataset = dataset;
        this.analysis = analysis;
        this.target = target;
        this.reference = reference;
    }

    public Comment(String text, String dataset, String analysis, String target, String reference) {
        this.text = text;
        this.dataset = dataset;
        this.analysis = analysis;
        this.target = target;
        this.reference = reference;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", text='" + text + '\'' +
                ", dataset='" + dataset + '\'' +
                ", analysis='" + analysis + '\'' +
                ", target='" + target + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }
}
