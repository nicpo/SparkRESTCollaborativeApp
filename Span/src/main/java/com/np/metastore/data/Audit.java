package com.np.metastore.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.ResultSet;
import java.sql.SQLException;

@XmlRootElement(name="Audit")
@XmlAccessorType(XmlAccessType.NONE)
public class Audit {
    @XmlElement
    private String id;

    @XmlElement
    private String type;

    @XmlElement
    private String user;

    @XmlElement
    private long timestamp;

    @XmlElement
    private String info;

    public Audit() {
    }

    public Audit(ResultSet res) throws SQLException
    {
        this(res.getString(1), res.getString(2), res.getString(3), res.getLong(4), res.getString(5));
    }

    public Audit(String id, String type, String user, long timestamp, String info) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.timestamp = timestamp;
        this.info = info;
    }

    public Audit(String type, String user, String info) {
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getTimestamp() {
        return timestamp;
    }

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
        return "Audit{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", timestamp=" + timestamp +
                ", info='" + info + '\'' +
                '}';
    }
}
