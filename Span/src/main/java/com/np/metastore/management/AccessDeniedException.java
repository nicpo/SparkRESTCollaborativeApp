package com.np.metastore.management;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String msg) {
        super(msg);
        System.out.println(msg);
    }
}
