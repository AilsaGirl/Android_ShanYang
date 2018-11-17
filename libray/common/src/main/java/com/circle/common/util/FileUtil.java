package com.circle.common.util;

import android.os.Environment;

import com.circle.common.app.BaseApplication;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class FileUtil {
    // ------------------------------------数据的缓存目录-------------------------------------------------------
    private static String DIR_NAME = "hualang/";


    private static String getRootPath() {
        // 判断SD卡是否存在，并且是否具有读写权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getPath() + "/";
        } else {
            return BaseApplication.getInstance().getCacheDir().getAbsolutePath() + "/android/data/" + BaseApplication.getInstance().getPackageName() + "/";
        }
    }

    public static String getDirpath() {
        String filePath = getRootPath() + DIR_NAME;
        File f = new File(filePath);
        if (!f.exists())
            f.mkdir();
        return filePath;
    }

    /**
     * pk结果保存图片
     * @return
     */
    public static String getSharePkResultImagePath(){
        return getDirpath() + "pk_result.png";
    }


    public static void writeFile(String filePath, String fileName, String content, boolean append) {
        LogManager.LogShow("savefile ==" + filePath + " content==" + content );
        try {
            File f = new File(filePath);
            if(!f.exists())
                f.mkdirs();
            FileOutputStream fout = new FileOutputStream(filePath + fileName,append);
            byte[] bytes = content.getBytes("UTF-8");
            fout.write(bytes);
            fout.close();
            LogManager.LogShow("savefile succ");
        } catch (Exception e) {
            LogManager.LogShow("savefile error=="+e);
        }
    }
}
