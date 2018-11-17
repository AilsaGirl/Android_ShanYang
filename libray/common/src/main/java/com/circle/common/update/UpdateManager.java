package com.circle.common.update;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import com.circle.common.R;
import com.circle.common.app.BaseApplication;
import com.circle.common.util.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 下载
 * Created by
 */

public class UpdateManager {
    public static interface UpdateListener {
        public void onCancelUpdate();
    }

    private UpdateListener mUpdateListener;
    /* 下载中 */
    private static final int DOWNLOAD = 1;
    /* 下载结束 */
    private static final int DOWNLOAD_FINISH = 2;
    private static final int DOWNLOAD_FAILED = 3;
    /* 下载保存路径 */
    private String mSavePath;
    /* 记录进度条数量 */
    private int progress;
    /* 是否取消更新 */
    private boolean cancelUpdate = false;
    public String updateVersion; //版本号
    public String updateNotice;//版本更新内容
    public String updateUrl;//下载地址

    private Activity mContext;
    private DownloadDialog mDownloadDialog;

    private boolean isForceUpdate = false;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 正在下载
                case DOWNLOAD:
                    // 设置进度条位置
                    mDownloadDialog.setProgress(progress);
                    break;
                case DOWNLOAD_FINISH:
                    // 安装文件
                    installApk();
                    break;
                case DOWNLOAD_FAILED:
                    ToastUtil.show("下载失败");
                    break;
                default:
                    break;
            }
        }
    };

    public UpdateManager(Activity context, boolean isForceUpdate, boolean isToday,String url, String version, String notice, UpdateListener updateListener) {
        this.mContext = context;
        this.updateUrl = url;
        this.updateVersion = version;
        this.updateNotice = notice;
        this.isForceUpdate = isForceUpdate;
        mUpdateListener = updateListener;
        if (!isToday&&!TextUtils.isEmpty(updateUrl)) {
            // 显示提示对话框
            showNoticeDialog();
        } else if (updateListener != null) {
            updateListener.onCancelUpdate();
        }
    }


    /**
     * 显示软件更新对话框
     */
    private void showNoticeDialog() {
        if(!TextUtils.isEmpty(updateNotice) && updateNotice.contains("\\n"))
            updateNotice = updateNotice.replace("\\n","\n");
        UpdateDialog dialog = new UpdateDialog(mContext, !isForceUpdate, "V" + updateVersion, updateNotice + "\n", new UpdateDialog.OnDialogClickListener() {
            @Override
            public void onOkClick(Dialog dialog) {
                showDownloadDialog();
                dialog.cancel();
            }
            @Override
            public void onCancelClick(Dialog dialog) {
                if (mUpdateListener != null) {
                    mUpdateListener.onCancelUpdate();
                }
                if (!isForceUpdate) {
                    dialog.cancel();
                }
            }
        });
        dialog.show();
    }

    /**
     * 显示软件下载对话框
     */
    private void showDownloadDialog() {
        mDownloadDialog = new DownloadDialog(mContext);
        mDownloadDialog.setCancelClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mDownloadDialog.dismiss();
                // // 设置取消状态
                cancelUpdate = true;
                if (isForceUpdate) {
                    mContext.finish();
                } else {
                    if (mUpdateListener != null) {
                        mUpdateListener.onCancelUpdate();
                    }
                }
            }
        });
        mDownloadDialog.show();

        //  下载apk文件
        new downloadApkThread().start();
    }

    /**
     * 下载文件线程
     */
    private class downloadApkThread extends Thread {
        @Override
        public void run() {
            try {

                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    mSavePath = Environment.getExternalStorageDirectory() + "/";
                } else {
                    mSavePath = BaseApplication.getInstance().getFilesDir() + "/";
                }

                mSavePath = mSavePath + "download";


                URL url = new URL(updateUrl);
//                URL url = new URL("https://dev.emintong.com/uploads/2.0.3-1526106418.apk");
                // 创建连接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                // 获取文件大小
                int length = conn.getContentLength();
                // 创建输入流
                InputStream is = conn.getInputStream();

                File file = new File(mSavePath);
                // 判断文件目录是否存在
                if (!file.exists()) {
                    file.mkdirs();
                }
                File apkFile = new File(mSavePath, updateVersion + ".apk");
                FileOutputStream fos = new FileOutputStream(apkFile);
                int count = 0;
                // 缓存
                byte buf[] = new byte[1024];
                // 写入到文件中
                do {
                    int numread = is.read(buf);
                    count += numread;
                    // 计算进度条位置
                    progress = (int) (((float) count / length) * 100);
                    // 更新进度
                    mHandler.sendEmptyMessage(DOWNLOAD);
                    if (numread <= 0) {
                        // 下载完成
                        mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                        break;
                    }
                    // 写入文件
                    fos.write(buf, 0, numread);
                } while (!cancelUpdate);// 点击取消就停止下载.
                fos.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
                mHandler.sendEmptyMessage(DOWNLOAD_FAILED);
                return;
            }
            // 取消下载对话框显示
            mDownloadDialog.dismiss();
        }
    }

    /**
     * 安装APK文件
     */
    private void installApk() {
        mContext.finish();
        File apkfile = new File(mSavePath, updateVersion + ".apk");
        if (!apkfile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//
//        intent.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
//        mContext.startActivity(intent);
        //////
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + apkfile.toString()),"application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}
