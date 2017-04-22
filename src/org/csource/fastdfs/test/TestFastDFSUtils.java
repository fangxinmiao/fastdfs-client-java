package org.csource.fastdfs.test;

import org.csource.fastdfs.ClientProperties;
import org.csource.fastdfs.FastDFSUtils;

import java.util.UUID;

/**
 * FastDFS utility test
 * Created by fangxm on 17-4-23.
 */
public class TestFastDFSUtils {
    public static void main(String[] args) throws Exception {
        ClientProperties prop = new ClientProperties();
        prop.setConnectTimeout(5);
        prop.setNetworkTimeout(30);
        prop.setCharset("utf-8");
        prop.setTrackerServers(new String[]{"192.168.7.61:22122", "192.168.7.62:22122"});
        prop.setTrackerHttpPort(8080);
        prop.setAntiStealToken(false);

        //http.access.url: http://inv.axnfw.com/
        //http.access.int.url: http://inv.axnfw.net/

        byte[] bytes = null;
        String url = FastDFSUtils.uploadFile(prop, bytes, UUID.randomUUID().toString());
    }
}
