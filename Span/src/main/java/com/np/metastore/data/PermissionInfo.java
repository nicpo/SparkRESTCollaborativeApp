package com.np.metastore.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement(name="PermissionInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class PermissionInfo {
    @XmlElement
    private String user;

    @XmlElement
    private String[] permissions;

    public PermissionInfo() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "PermissionInfo{" +
                "user='" + user + '\'' +
                ", permissions=" + Arrays.toString(permissions) +
                '}';
    }
}
