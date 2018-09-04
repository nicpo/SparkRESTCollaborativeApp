package com.np.util;

import com.np.metastore.management.*;
import com.np.spark.management.*;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import java.util.*;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("")
public class BaseApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();

    public BaseApplication()
    {
        CorsFilter corsFilter = new CorsFilter();
        corsFilter.getAllowedOrigins().add("*");
        corsFilter.setAllowedHeaders("sid, content-type, accept");
        singletons.add(corsFilter);

        singletons.add(new AccessDeniedExceptionMapper());
        singletons.add(new SystemExceptionMapper());
        singletons.add(new FileNotFoundExceptionMapper());
        singletons.add(new SQLExceptionMapper());
        singletons.add(new URISyntaxExceptionMapper());
        singletons.add(new NoSuchFileExceptionMapper());


        singletons.add(new AnalysisManagement());
        singletons.add(new CoreManagement());
        singletons.add(new CommentManagement());
        singletons.add(new DatasetManagement());
        singletons.add(new UserManagement());
        singletons.add(new PermissionManagement());
        singletons.add(new StatisticsManagement());
        singletons.add(new ImportManagement());
    }
    @Override
    public Set<Class<?>> getClasses()
    {
        return empty;
    }
    @Override
    public Set<Object> getSingletons()
    {
        return singletons;
    }}