package org.csource.fastdfs;

/**
 * client properties
 * Created by fangxm on 17-4-23.
 */
public class ClientProperties {

    /**
     * 连接超时（秒）
     */
    private int connectTimeout = ClientGlobal.DEFAULT_CONNECT_TIMEOUT;

    /**
     * 网络超时（秒）
     */
    private int networkTimeout = ClientGlobal.DEFAULT_NETWORK_TIMEOUT;

    /**
     * 字符集
     */
    private String charset = ClientGlobal.DEFAULT_CHARSET;

    /**
     * tracker servers
     */
    private String[] trackerServers;

    /**
     * tracker HTTP port
     */
    private int trackerHttpPort = ClientGlobal.DEFAULT_HTTP_PORT;

    /**
     * anti-steal token
     */
    private boolean antiStealToken = false;

    /**
     * secret key
     */
    private String secretKey = "";

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout < 0 ?
                ClientGlobal.DEFAULT_CONNECT_TIMEOUT : connectTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setNetworkTimeout(int networkTimeout) {
        this.networkTimeout = networkTimeout < 0 ?
                ClientGlobal.DEFAULT_NETWORK_TIMEOUT : networkTimeout;
    }

    public int getNetworkTimeout() {
        return networkTimeout;
    }

    public void setCharset(String charset) {
        this.charset = (charset == null || charset.length() == 0) ?
                ClientGlobal.DEFAULT_CHARSET : charset;
    }

    public String getCharset() {
        return charset;
    }

    public void setTrackerServers(String[] trackerServers) {
        this.trackerServers = trackerServers;
    }

    public String[] getTrackerServers() {
        return trackerServers;
    }

    public void setTrackerHttpPort(int trackerHttpPort) {
        this.trackerHttpPort = trackerHttpPort <= 0 ?
                ClientGlobal.DEFAULT_HTTP_PORT : trackerHttpPort;
    }

    public int getTrackerHttpPort() {
        return trackerHttpPort;
    }

    public void setAntiStealToken(boolean antiStealToken) {
        this.antiStealToken = antiStealToken;
    }

    public boolean isAntiStealToken() {
        return antiStealToken;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
