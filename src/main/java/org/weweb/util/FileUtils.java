package org.weweb.util;

import org.springframework.util.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * Created by jackshen on 2017/5/6.
 */
public class FileUtils {

    public static void download(String remoteFilePath, String localFilePath) throws Exception {
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
       // File f = new File(localFilePath);
        File f=createFile(localFilePath);
        if(f==null)
            return;
        try
        {
            urlfile = new URL(remoteFilePath);
            httpUrl = (HttpURLConnection)urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1)
            {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                bis.close();
                bos.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void createDirectory(String path) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return;
        }
        try {
            // 获得文件对象
            File f = new File(path);
            if (!f.exists()) {
                // 如果路径不存在,则创建
                f.mkdirs();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    public static File createFile(String path) throws Exception {
        File f=null;
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        try {
            // 获得文件对象
             f = new File(path);
            if (f.exists()) {
                return null;
            }
            // 如果路径不存在,则创建
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            f.createNewFile();
        } catch (Exception e) {
            throw e;
        }
        return f;
    }
    public static void main(String[] args) throws Exception {

        String url="https://www.sstparts.com/ih.php?dim=2&imgName=28166.jpg";
        String localPath="/download/cod/"+ UUID.randomUUID()+".jpg";

        localPath="/Users/jackshen/Documents/NewIDEA/weweb-server/target/weweb-server-1.0-SNAPSHOT/download/cod/28166.jpg";
       // localPath="/Users/jackshen/Document/test00001/111.jpg";
        //localPath="test/testtest.jpg";
        download(url,localPath);
    }
}
