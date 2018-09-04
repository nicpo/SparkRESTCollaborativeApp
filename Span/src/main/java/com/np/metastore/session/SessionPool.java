package com.np.metastore.session;

import com.np.metastore.data.User;
import com.np.metastore.management.AccessDeniedException;
import com.np.util.Config;

import javax.annotation.concurrent.Immutable;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Immutable
public class SessionPool {
    private final ConcurrentHashMap<String, Session> pool = new ConcurrentHashMap<>();
    private static final SessionPool ourInstance = new SessionPool();

    public static SessionPool getInstance() {
        return ourInstance;
    }

    private SessionPool() {
        if (true || Config.isDevelopmentServer()) {
            User maximkr = new User("maximkr@gmail.com", "Maxim Kramarenko", "maximkr@gmail.com", "93176ad56fc9284d55cd82c999ca77af904f495ee22a3be0bf97071165c0915b", "4f44cbee-ff85-48a5-9c75-f633a8eb0723", true, true);
            pool.put("0", new Session("0", maximkr));
        }
    }

    public Session createSession(User u)
    {
        Session sess = new Session(u);
        pool.put(sess.getSid(), sess);
        return sess;
    }

    public void removeSession(String sessionId)
    {
        pool.remove(sessionId);
    }

    public Session get(String sessionId)
    {
        if (sessionId==null || sessionId.isEmpty())
            throw new AccessDeniedException("Set session id for this call");
        Session sess = pool.get(sessionId);
        if (sess == null)
            throw new AccessDeniedException("Wrong session id");
        if ((System.currentTimeMillis() - sess.getTimestamp()) / 1000 / 3600 / 24  > 14) // 14 days to expire
            throw new AccessDeniedException("Session id " + sessionId + " has been expired");
        return sess;
    }


}
