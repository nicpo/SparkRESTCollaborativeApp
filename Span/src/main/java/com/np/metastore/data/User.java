package com.np.metastore.data;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.ResultSet;
import java.sql.SQLException;

@XmlRootElement(name="User")
@XmlAccessorType(XmlAccessType.NONE)
public class User {
    @XmlElement
    private String id;

    @XmlElement
    private String name;

    @XmlElement
    private String email;

    @XmlElement
    private String password;

    @XmlElement
    private String salt;

    @XmlElement
    private boolean active;

    @XmlElement
    private boolean admin;

    public User() {
    }

    public User (ResultSet res) throws SQLException
    {
        this(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5),  res.getInt(6)==1 ? true : false, res.getInt(7)==1 ? true : false);
    }

    public User(String id, String name, String email, String password, String salt, boolean active, boolean admin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.active = active;
        this.admin = admin;
    }

    public User(String name, String email, String password, boolean admin) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = true;
        this.admin = admin;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", active=" + active +
                ", admin=" + admin +
                '}';
    }
}
