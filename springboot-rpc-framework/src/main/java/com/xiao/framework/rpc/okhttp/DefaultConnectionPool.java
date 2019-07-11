package com.xiao.framework.rpc.okhttp;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Default connection pool.
 *
 * @author lix wang
 */
public class DefaultConnectionPool {
    private static final ConnectionPool POOL = new ConnectionPool(10, 5, TimeUnit.MINUTES);
    private static final OkHttpClient CLIENT = new OkHttpClient();

    public static OkHttpClient getDefaultClient() {
        return getClientFromPool(5, 5, 5);
    }

    private static OkHttpClient getClientFromPool(int connectTimeout, int readTimeout, int writeTimeout) {
        return CLIENT.newBuilder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .connectionPool(POOL).build();
    }
}
