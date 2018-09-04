package com.np.metastore.management;

public class SystemException extends RuntimeException {
    public SystemException(String msg) {
        super(msg);
        System.out.println(msg);
    }
}
