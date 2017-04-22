package org.csource.fastdfs;

import java.io.InputStream;

/**
 * FastDFS utility
 * Created by fangxm on 17-4-23.
 */
public class FastDFSUtils {

    /**
     * 发送文件到fastdfs
     *
     * @param is       输入流
     * @param fileName 文件名
     * @return 文件路径, 须加域名或ip才能访问(配置http.access.url时会加上该配置项)
     * @throws Exception Exception
     */
    public static String uploadFile(ClientProperties prop,
                                    InputStream is,
                                    String fileName) throws Exception {
        return uploadFile(prop, is, null, fileName);
    }

    /**
     * 发送文件到fastdfs
     *
     * @param bytes    字节流
     * @param fileName 文件名
     * @return 文件路径, 须加域名或ip才能访问(配置http.access.url时会加上该配置项)
     * @throws Exception Exception
     */
    public static String uploadFile(ClientProperties prop,
                                    byte[] bytes,
                                    String fileName) throws Exception {
        return uploadFile(prop, null, bytes, fileName);
    }

    /**
     * 注：is和bytes参数同时只能传入一个
     */
    private static String uploadFile(ClientProperties prop,
                                     InputStream is,
                                     byte[] bytes,
                                     String fileName) throws Exception {
        StorageServer ss = null;
        TrackerServer ts = null;
        try {
            if ((is == null && bytes == null) || fileName == null || fileName.trim().length() <= 0) {
                return null;
            }

            init(prop);
            TrackerGroup tg = ClientGlobal.g_tracker_group;
            TrackerClient tc = new TrackerClient(tg);
            ts = tc.getConnection();
            if (ts == null) {
                throw new RuntimeException("getConnection return null");
            }
            ss = tc.getStoreStorage(ts);
            if (ss == null) {
                throw new RuntimeException("getStoreStorage return null");
            }
            StorageClient1 sc1 = new StorageClient1(ts, ss);
            String ext = null;
            if (fileName.lastIndexOf(".") > 0 &&
                    (fileName.lastIndexOf(".") + 1 < fileName.length())) {
                ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            }

            String fileId;
            if (is != null) {
                fileId = sc1.upload_file1(null, is.available(),
                        new UploadStream(is, is.available()), ext, null);
            } else {
                fileId = sc1.upload_file1(bytes, ext, null);
            }
            if (fileId != null /*&&
                    FastDFSProperties.httpAccessUrl != null &&
                    FastDFSProperties.httpAccessUrl.startsWith("http")*/) {
                // TODO
                //return FastDFSProperties.httpAccessUrl + fileId;
                return fileId;
            } else {
                return fileId;
            }
        } finally {
            if (ss != null) {
                ss.close();
            }
            if (ts != null) {
                ts.close();
            }
        }
    }

    private static void init(ClientProperties prop) {
        if (ClientGlobal.g_tracker_group == null) {
            try {
                ClientGlobal.init(prop);
            } catch (Exception e) {
                throw new RuntimeException("fastDFS配置错误");
            }
        }
    }
}
