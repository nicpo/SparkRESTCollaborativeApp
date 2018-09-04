package com.np.metastore.session;

import com.np.metastore.data.User;
import com.np.metastore.management.AccessDeniedException;
import com.np.util.Config;
import net.jcip.annotations.Immutable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Immutable
@XmlRootElement(name="Session")
@XmlAccessorType(XmlAccessType.NONE)
public class Session {
    @XmlElement
    private final String sid;

    @XmlElement
    private final User user;

    @XmlElement
    private final long timestamp;

    protected Session(User user) {
        this.sid = Config.getShortUUID();
        this.user = user;
        this.timestamp = System.currentTimeMillis();
    }

    protected Session(String sessionId, User user) {
        this.sid = sessionId;
        this.user = user;
        this.timestamp = System.currentTimeMillis();
    }

    public String getSid() {
        return sid;
    }

    // TODO: fix object leak
    public User getUser() {
        return user;
    }

    public String getUserId()
    {
        return user.getId();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void requireAdmin()
    {
        if (!user.isAdmin())
            throw new AccessDeniedException("User should be admin");
    }

    public void requireOwnerOrAdmin(String userId)
    {
        if (user.getId().equals(userId))
            return; // it's object owner

        if (user.isAdmin())
            return ; // it's admin

        throw new AccessDeniedException("User should be object owner or admin");
    }


    public void canUpdateDataset(String datasetId)
    {
        // TODO
        return;
    }

    public void canDeleteDataset(String datasetId)
    {
        // TODO
        return;
    }

    public void canViewDataset(String datasetId)
    {
        // TODO
        return;
    }

    public void canDeleteAnalysis(String analysisId)
    {
        // TODO
        return;
    }

    public void canViewAnalysis(String analysisId)
    {
        // TODO
        return;
    }

}
