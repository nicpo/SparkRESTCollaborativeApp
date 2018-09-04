package com.np.metastore.data;

/**
 * The owner can invite other users to work on the dataset with the following sets of permissions:
 * View only (including viewing comments)
 * Comment
 * Run analytics operations [includes “View only” and “Comment”]
 * Share
 */
public interface PermissionType {
        public static final String VIEW = "VIEW"; // item and comments visible
        public static final String COMMENT = "COMMENT"; // can add new comments
        public static final String ANALYZE = "ANALYZE"; // can analyze
        public static final String SHARE = "SHARE"; // can share
}
