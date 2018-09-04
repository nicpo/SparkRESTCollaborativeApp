package com.np.ui;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.data.util.sqlcontainer.query.generator.DefaultSQLGenerator;

import java.sql.SQLException;

public class DataSource {
    private static final DataSource ourInstance = new DataSource();
    private final JDBCConnectionPool pool;

    private final TableQuery userTableQuery;
    private final TableQuery datasetTableQuery;
    private final TableQuery analysisTableQuery;
    private final TableQuery commentTableQuery;
    private final TableQuery permissionTableQuery;
    private final TableQuery auditTableQuery;
    private final TableQuery activityTableQuery;

    private final SQLContainer userSQLContainer;
    private final SQLContainer datasetSQLContainer;
    private final SQLContainer analysisSQLContainer;
    private final SQLContainer commentSQLContainer;
    private final SQLContainer permissionSQLContainer;
    private final SQLContainer auditSQLContainer;
    private final SQLContainer activitySQLContainer;


    public static DataSource getInstance()
    {
        return ourInstance;
    }

    private DataSource()
    {
        try {
            pool = new SimpleJDBCConnectionPool("org.sqlite.JDBC", "jdbc:sqlite:/etc/span/metastore.db", "SA", "", 2, 5);
            userTableQuery = new TableQuery("aa_user", pool);
            datasetTableQuery = new TableQuery("aa_dataset", pool);
            analysisTableQuery = new TableQuery("aa_analysis", pool);
            commentTableQuery = new TableQuery("aa_comment", pool, new DefaultSQLGenerator(NullReferenceHelper.class)); // required when foreign key can be null
            permissionTableQuery = new TableQuery("aa_permission", pool, new DefaultSQLGenerator(NullReferenceHelper.class)); // required when foreign key can be null
            auditTableQuery = new TableQuery("aa_audit", pool);
            activityTableQuery = new TableQuery("aa_activity", pool);

            userSQLContainer = new SQLContainer(userTableQuery);
            datasetSQLContainer = new SQLContainer(datasetTableQuery);
            analysisSQLContainer = new SQLContainer(analysisTableQuery);
            commentSQLContainer = new SQLContainer(commentTableQuery);
            permissionSQLContainer = new SQLContainer(permissionTableQuery);
            auditSQLContainer = new SQLContainer(auditTableQuery);
            activitySQLContainer = new SQLContainer(activityTableQuery);

/*            commentSQLContainer.addReference(analysisSQLContainer, "comment_analysis", "analysis_id");
            commentSQLContainer.addReference(datasetSQLContainer, "comment_dataset","dataset_id");
            commentSQLContainer.addReference(commentSQLContainer, "comment_reference","comment_id");
*/
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public SQLContainer getUserSQLContainer()
    {
        return userSQLContainer;
    }

    public SQLContainer getDatasetSQLContainer()
    {
        return datasetSQLContainer;
    }

    public SQLContainer getAnalysisSQLContainer()
    {
        return analysisSQLContainer;
    }

    public SQLContainer getCommentSQLContainer() {
        return commentSQLContainer;
    }

    public SQLContainer getPermissionSQLContainer() {
        return permissionSQLContainer;
    }

    public SQLContainer getAuditSQLContainer() {
        return auditSQLContainer;
    }

    public SQLContainer getActivitySQLContainer() {
        return activitySQLContainer;
    }

}
