package com.np.metastore.kernel;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.np.metastore.data.*;
import com.np.metastore.management.SystemException;
import com.np.util.Config;
import org.apache.commons.lang.mutable.MutableLong;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.hive.HiveContext;
import org.apache.spark.sql.types.StructType;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Core {
    public static ArrayList<Analysis> listAnalysis() throws SQLException {
        ArrayList<Analysis> result = new ArrayList<Analysis>();
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT analysis_id, analysis_name, analysis_type, analysis_query, analysis_user, analysis_request, analysis_state, analysis_output, analysis_starttime, analysis_endtime, analysis_description FROM aa_analysis");
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result.add(new Analysis(res));
            }
            stmt.close();
            Config.getInstance().getConn().commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static Analysis getQueuedAnalysis() throws SQLException {
        Analysis result = null;
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT analysis_id, analysis_name, analysis_type, analysis_query, analysis_user, analysis_request, analysis_state, analysis_output, analysis_starttime, analysis_endtime, analysis_description FROM aa_analysis WHERE analysis_state=?");
            stmt.setString(1, "QUEUED");
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                result = new Analysis(res);
            }
            stmt.close();
            Config.getInstance().getConn().commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static ArrayList<Analysis> listAnalysisForUser(String userId) throws SQLException {
        ArrayList<Analysis> result = new ArrayList<Analysis>();
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT analysis_id, analysis_name, analysis_type, analysis_query, analysis_user, analysis_request, analysis_state, analysis_output, analysis_starttime, analysis_endtime, analysis_description FROM aa_analysis WHERE analysis_user=? OR analysis_id in (select permission_analysis from aa_permission where permission_type='VIEW' AND permission_user=?)");
            stmt.setString(1, userId);
            stmt.setString(2, userId);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result.add(new Analysis(res));
            }
            stmt.close();
            Config.getInstance().getConn().commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static ArrayList<Audit> listAudit(String userId) throws SQLException {
        ArrayList<Audit> result = new ArrayList<Audit>();
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT  audit_id, audit_type, audit_user, audit_timestamp, audit_info FROM aa_audit WHERE audit_user=?");
            stmt.setString(1, userId);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result.add(new Audit(res));
            }
            stmt.close();
            Config.getInstance().getConn().commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static ArrayList<Activity> listActivityForDataset(String id) throws SQLException {
        ArrayList<Activity> result = new ArrayList<Activity>();
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT a.activity_id, a.activity_type, a.activity_user, a.activity_dataset_id, d.dataset_name, a.activity_analysis_id, '', a.activity_target, a.activity_timestamp, a.activity_info FROM aa_activity a, aa_dataset d WHERE a.activity_dataset_id=? AND a.activity_dataset_id = d.dataset_id ORDER BY a.activity_timestamp DESC");
            stmt.setString(1, id);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result.add(new Activity(res));
            }
            stmt.close();
            Config.getInstance().getConn().commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static ArrayList<Activity> listActivityForAnalysis(String id) throws SQLException {
        ArrayList<Activity> result = new ArrayList<Activity>();
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    //.prepareStatement("SELECT  activity_id, activity_type, activity_user, activity_dataset, activity_analysis, activity_target, activity_timestamp, activity_info FROM aa_activity WHERE activity_analysis=?");
                    .prepareStatement("SELECT a.activity_id, a.activity_type, a.activity_user, a.activity_dataset_id, '', a.activity_analysis_id, n.analysis_name, a.activity_target, a.activity_timestamp, a.activity_info FROM aa_activity a, aa_analysis n WHERE a.activity_analysis_id=? AND a.activity_analysis_id = n.analysis_id ORDER BY a.activity_timestamp DESC");
            stmt.setString(1, id);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result.add(new Activity(res));
            }
            stmt.close();
            Config.getInstance().getConn().commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static ArrayList<Activity> listActivityForUser(String userId) throws SQLException {
        ArrayList<Activity> result = new ArrayList<Activity>();
        try {
            // first get activity for datasets the user has permissions to
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT a.activity_id, a.activity_type, a.activity_user, a.activity_dataset_id, d.dataset_name, a.activity_analysis_id, '', a.activity_target, a.activity_timestamp, a.activity_info FROM aa_activity a, aa_dataset d WHERE (d.dataset_user=? OR d.dataset_id in (SELECT permission_dataset FROM aa_permission WHERE permission_type='VIEW' AND permission_user=?)) AND a.activity_dataset_id = d.dataset_id");
            stmt.setString(1, userId);
            stmt.setString(2, userId);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result.add(new Activity(res));
            }
            stmt.close();

            // then get activity for user's analyses
            stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT a.activity_id, a.activity_type, a.activity_user, a.activity_dataset_id, '', a.activity_analysis_id, n.analysis_name, a.activity_target, a.activity_timestamp, a.activity_info FROM aa_activity a, aa_analysis n WHERE (n.analysis_user=? OR n.analysis_id in (SELECT permission_analysis FROM aa_permission WHERE permission_type='VIEW' AND permission_user=?)) AND a.activity_analysis_id = n.analysis_id");
            stmt.setString(1, userId);
            stmt.setString(2, userId);
            res = stmt.executeQuery();
            while (res.next()) {
                result.add(new Activity(res));
            }
            stmt.close();
            Config.getInstance().getConn().commit();

            // and sort the whole thing by timestamp desc
            Collections.sort(result, new Comparator<Activity>() {
                @Override
                public int compare(Activity a1, Activity a2) {
                    return a2.getTimestamp().compareTo(a1.getTimestamp());
                }
            });
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static ArrayList<Comment> listCommentForDataset(String id) throws SQLException {
        ArrayList<Comment> result = new ArrayList<Comment>();
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT  comment_id, comment_user, comment_created, comment_updated, comment_text, comment_dataset, comment_analysis, comment_target, comment_reference FROM aa_comment WHERE comment_dataset=?");
            stmt.setString(1, id);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result.add(new Comment(res));
            }
            stmt.close();
            Config.getInstance().getConn().commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static ArrayList<Comment> listCommentForAnalysis(String id) throws SQLException {
        ArrayList<Comment> result = new ArrayList<Comment>();
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT  comment_id, comment_user, comment_created, comment_updated, comment_text, comment_dataset, comment_analysis, comment_target, comment_reference FROM aa_comment WHERE comment_analysis=?");
            stmt.setString(1, id);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result.add(new Comment(res));
            }
            stmt.close();
            Config.getInstance().getConn().commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static ArrayList<Dataset> listDataset() throws SQLException {
        ArrayList<Dataset> result = new ArrayList<Dataset>();
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT  dataset_id, dataset_name, dataset_url, dataset_table, dataset_query, dataset_description, dataset_info, dataset_timestamp, dataset_user, dataset_size FROM aa_dataset");
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result.add(new Dataset(res));
            }
            stmt.close();
            Config.getInstance().getConn().commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static ArrayList<Dataset> listDatasetForUser(String userId) throws SQLException {
        ArrayList<Dataset> result = new ArrayList<Dataset>();
        try {
            // this query returns datasets where specified user is owner
            // or where he has VIEW permission
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT  dataset_id, dataset_name, dataset_url, dataset_table, dataset_query, dataset_description, dataset_info, dataset_timestamp, dataset_user, dataset_size FROM aa_dataset WHERE dataset_user=? OR dataset_id in (select permission_dataset from aa_permission where permission_type='VIEW' AND permission_user=?)");
            stmt.setString(1, userId);
            stmt.setString(2, userId);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result.add(new Dataset(res));
            }
            stmt.close();
            Config.getInstance().getConn().commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static ArrayList<Permission> listPermissionForUser(String userId) throws SQLException {
        ArrayList<Permission> result = new ArrayList<Permission>();
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT  permission_id, permission_type, permission_creator, permission_user, permission_dataset, permission_analysis FROM aa_permission WHERE permission_user=?");
            stmt.setString(1, userId);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result.add(new Permission(res));
            }
            stmt.close();
            Config.getInstance().getConn().commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static ArrayList<Permission> listPermissionForDataset(String datasetId) throws SQLException {
        ArrayList<Permission> result = new ArrayList<Permission>();
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT  permission_id, permission_type, permission_creator, permission_user, permission_dataset, permission_analysis FROM aa_permission WHERE permission_dataset=?");
            stmt.setString(1, datasetId);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result.add(new Permission(res));
            }
            stmt.close();
            Config.getInstance().getConn().commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static ArrayList<Permission> listPermissionForAnalysis(String analysisId) throws SQLException {
        ArrayList<Permission> result = new ArrayList<Permission>();
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT  permission_id, permission_type, permission_creator, permission_user, permission_dataset, permission_analysis FROM aa_permission WHERE permission_analysis=?");
            stmt.setString(1, analysisId);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result.add(new Permission(res));
            }
            stmt.close();
            Config.getInstance().getConn().commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static ArrayList<User> listUser() throws SQLException {
        ArrayList<User> result = new ArrayList<User>();
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT user_id, user_name, user_email, user_password, user_salt, user_active, user_admin FROM aa_user");
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result.add(new User(res));
            }
            stmt.close();
            Config.getInstance().getConn().commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static User getUserByEmail(String email) throws SQLException {
        try {
            User ret = null;
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT user_id, user_name, user_email, user_password, user_salt, user_active, user_admin FROM aa_user WHERE user_email=?");
            stmt.setString(1, email);
            ResultSet res = stmt.executeQuery();
            if (res.next())
                ret = new User(res);
            stmt.close();
            Config.getInstance().getConn().commit();
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static ArrayList<Dataset> listDatasetsForQuery(String query) {
        ArrayList<Dataset> result = new ArrayList<>();

        try {
            HiveContext hiveContext = Config.getInstance().getHiveContext();
            String planAsString = hiveContext.sql(query).logicalPlan().children().toString();

            ArrayList<Dataset> datasets = listDataset();

            for (Dataset ds : datasets)
                if (planAsString.contains("Subquery " + ds.getTable() + "\n"))
                    result.add(ds);

        } catch (Throwable e) {
            System.out.println("ERROR: " + e.getMessage()); //Exceptions should be ignored, otherwise wrong query in some analysis will break related query feature
        }
        return result;
    }

    public static ArrayList<Analysis> relatedAnalysisForDataset(String userId, String datasetId) throws SQLException {
        ArrayList<Analysis> result = new ArrayList<>();

        // get full list of analysis, available for the current user
        ArrayList<Analysis> analysisList = listAnalysisForUser(userId);
        System.out.println("User " + userId + " has permissions to the following analysis ");
        for (Analysis a : analysisList) {
            System.out.print(a.getId() + " ");
        }
        System.out.println();

        for (Analysis a : analysisList) {
            if (a.getQuery() != null && !a.getQuery().equals("")) {

                // each analysis contains query
                // we should analyze that query to get list of datasets, which
                // analysis uses
                System.out.println("Testing analysis id " + a.getId() + " name " + a.getName() + " query " + a.getQuery());
                ArrayList<Dataset> datasetsForQuery = listDatasetsForQuery(a.getQuery());
                System.out.println("Found " + datasetsForQuery.size() + " datasets");

                // now we return analysis if it uses the specified dataset
                for (Dataset d : datasetsForQuery) {
                    if (d.getId().equals(datasetId))
                        result.add(a);
                }
            }
        }
        return result;
    }

    public static String testQueryGetSchema(String query) {
        try {
            HiveContext hiveContext = Config.getInstance().getHiveContext();
            DataFrame df = hiveContext.sql(query);
            StructType st = df.schema();
            return st.toString();
        } catch (Throwable e) {
            throw new SystemException(e.getMessage());
        }
    }

    public static void deleteParquet(String url) throws URISyntaxException, IOException {
        URI uri = new URI(url);
        if (uri.getScheme().equals("file")) { // local file
            Path p = Paths.get(uri);
            if (p.startsWith("/etc/span/data/"))
                removeRecursive(p);
            else
                throw new SystemException("Can delete local files only in /etc/span/data directory");
        } else if (uri.getScheme().equals("s3n")) { // s3 file
            String bucketName = uri.getHost();
            String key = uri.getPath().substring(1); // key without leading /

            AmazonS3 s3 = Config.getInstance().getS3Client();
            final ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucketName).withPrefix(key);

            ObjectListing objects = s3.listObjects(listObjectsRequest);
            do {
                for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
                    s3.deleteObject(bucketName, objectSummary.getKey());
                }
                objects = s3.listNextBatchOfObjects(objects);
            } while (objects.isTruncated());

        }

    }

    private static void removeRecursive(Path path) throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                // try to delete the file anyway, even if its attributes
                // could not be read, since delete-only access is
                // theoretically possible
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (exc == null) {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                } else {
                // directory iteration failed; propagate exception
                    throw exc;
                }
            }
        });
    }


    public static DatasetSize datasetSize(String url) throws IOException, URISyntaxException {
        final HiveContext hiveContext = Config.getInstance().getHiveContext();
        DataFrame df = hiveContext.parquetFile(url);
        long resultSetCount = df.count();
        long columnCount = df.columns().length;
        long size = sizeByUrl(url);
        return new DatasetSize(size, resultSetCount, columnCount);

    }

    private static long sizeByUrl(String url) throws URISyntaxException, IOException {
        URI uri = new URI(url);
        if (uri.getScheme().equals("file")) { // local file
            Path p = Paths.get(uri);
            if (p.startsWith("/etc/span/data/"))
                return sizeRecursive(p);
            else
                throw new SystemException("Can delete local files only in /etc/span/data directory");
        } else if (uri.getScheme().equals("s3n")) { // s3 file
            String bucketName = uri.getHost();
            String key = uri.getPath().substring(1); // key without leading /

            AmazonS3 s3 = Config.getInstance().getS3Client();
            final ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucketName).withPrefix(key);
            long size = 0;

            ObjectListing objects = s3.listObjects(listObjectsRequest);
            do {
                for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
                    size += objectSummary.getSize();
                }
                objects = s3.listNextBatchOfObjects(objects);
            } while (objects.isTruncated());

            return size;

        }
        return -1;
    }

    private static long sizeRecursive(Path path) throws IOException {
        final MutableLong size = new MutableLong();
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                size.add(attrs.size());
                return FileVisitResult.CONTINUE;

            }
        });
        return size.longValue();
    }

}