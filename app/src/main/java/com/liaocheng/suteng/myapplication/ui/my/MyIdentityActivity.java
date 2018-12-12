package com.liaocheng.suteng.myapplication.ui.my;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.circle.common.base.BaseActivity;
import com.circle.common.response.CommonRes;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.LoadingDialog;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.AuthBean;
import com.liaocheng.suteng.myapplication.presenter.IdentityPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.IdentityContract;
import com.liaocheng.suteng.myapplication.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.bluemoon.cardocr.lib.bean.IdCardInfo;
import cn.com.bluemoon.cardocr.lib.common.CardType;


public class MyIdentityActivity extends BaseActivity<IdentityPresenter> implements IdentityContract.View {
    //mybar
    @BindView(R.id.mybar)
    MyToolBar mybar;

    //人像
    @BindView(R.id.IV_face_photo)
    ImageView IVFacePhoto;

    //国徽
    @BindView(R.id.IV_reverse_side)
    ImageView IVReverseSide;
    //手持
    @BindView(R.id.IV_reverse_he)
    ImageView IVReverseHe;



    ImageView back, imagebig;
    //显示隐藏
    boolean isselect = false;
    int i = 1;
    //图片位置
    Bitmap integer_renxiang, integer_guohui;
    //身份证
    String ID;
    //姓名
    String name;
    String renxiang, guohui;
    //存放图片
    private ArrayList<String> mImgList = new ArrayList<>();
    //存放地址
    List<String> namesList = new ArrayList<>();
    //取得第几个位置
    int mHthttpCount = 0;
    //七牛token
    String mToken = "";
    //图片名字位置
    private String mFilePahtList = "";

    private String authority; //签发机关，XXX公安局
    private String validDate; //有效期限，2007.02.14-2017.02.14
    private String xingming; //姓名，艾米
    private String sex; //性别，女
    private String nation; //民族，汉
    private String birth; //出生，1990/12/22
    private String address; //住址，浙江省海盐县武原街工人路
    private String idnumber; //公民身份号码，610333199012223323
    private String renxiangpath; //人像图片地址
    private String guohuipath; //国徽图片地址
    private String hepath; //国徽图片地址

    List<String> images = new ArrayList<>();  //选择的图片

    private LoadingDialog loadingDialog;//弹出框（加载中）

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_identity;
    }

    @Override
    public void initEventAndData() {
        mybar.setTitleText("实名验证").setBackFinish();
       mybar.setRight(0xff333333, "保存", new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (i==4){
                   if (images.size() < 3) {
                       images.add(renxiangpath);
                       images.add(guohuipath);
                       images.add( hepath);
                   } else {
                       images.set(0, renxiangpath);
                       images.set(1, guohuipath);
                       images.set(2, hepath);
                   }

                  String photo1 = Util.imageToBase64(renxiangpath);
                   String photo2 = Util.imageToBase64(guohuipath);
                   String photo3 = Util.imageToBase64(hepath);
                   mPresenter.Identity(SPCommon.getString("token",""),xingming,SPCommon.getString("phone",""),idnumber,photo1,photo2,photo3);
                    if (loadingDialog == null)
                       loadingDialog = new LoadingDialog(mContext);
                   loadingDialog.setCancelable(true);
                   loadingDialog.setMsg("加载中");
                   loadingDialog.show();
               }

           }
       });
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void showError(int reqCode, String msg) {
        //有错误，关闭加载框
        if (loadingDialog != null) {
            loadingDialog.cancel();
        }
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }


    @Override
    public void delIdentitySucss() {

    }

    @Override
    public void IdentitySucss() {
        loadingDialog.cancelDialog();
        ToastUtil.show("提交成功，等待审核");
        finish();
    }

    @Override
    public void IdentityInfoSucss(AuthBean mBeaan) {

    }


    @OnClick({R.id.IV_face_photo, R.id.IV_reverse_side, R.id.IV_reverse_he})
    public void onViewClicked(View view) {
        String dirPath = Environment.getExternalStorageDirectory() + "/images";
        switch (view.getId()) {
            //人像删除
//            case R.id.renxiang_shanchu:
//                i--;
//                renxiangShanchu.setVisibility(View.GONE);
//                renxiangFangda.setVisibility(View.GONE);
//                IVFacePhoto.setImageResource(R.mipmap.paisherenxiang);
//                IVFacePhoto.setBackgroundColor(getResources().getColor(R.color.background));
//
//                break;
            //人像拍照
            case R.id.IV_face_photo:
                CoustomCaptureActivity.startAction(this, CardType.TYPE_ID_CARD_FRONT, dirPath, 0);//人像照
                break;
            //人像放大
//            case R.id.renxiang_fangda:
//                showpopuwindow(integer_renxiang);
//                break;
            //手持
            case R.id.IV_reverse_he:
                openCamera(this);
                break;
            //国徽删除
//            case R.id.guohui_shanchu:
//                i--;
//                guohuiShanchu.setVisibility(View.GONE);
//                guohuoFangda.setVisibility(View.GONE);
//                IVReverseSide.setImageResource(R.mipmap.paisheguohui);
//                IVReverseSide.setBackgroundColor(getResources().getColor(R.color.background));
//
//                break;
            //国徽拍照
            case R.id.IV_reverse_side:
                CoustomCaptureActivity.startAction(this, CardType.TYPE_ID_CARD_BACK, dirPath, 1);//国徽照
                break;
            //国徽放大
//            case R.id.guohuo_fangda:
//                showpopuwindow(integer_guohui);
//                break;
//            //

        }
    }

    /**
     * 拍照回传照片
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("AuthenticationPS onActivityResult:333 ");

        if (resultCode == RESULT_OK) {
            if (requestCode ==  PHOTO_REQUEST_CAREMA) {
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO); // 启动裁剪程序
                }
                return;
            }
                if (requestCode ==  CROP_PHOTO) {
                    if (resultCode == RESULT_OK) {
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
                                    .openInputStream(imageUri));
                            IVReverseHe.setImageBitmap(bitmap);
                            hepath = tempFile.getPath();
                            if (i < 4) {
                                i++;
                            } else {
                                i = 4;
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    return;
                }
            if (requestCode == 0) {
                try {
                    /**
                     * 该uri就是照片文件夹对应的uri
                     */
                    IdCardInfo info = (IdCardInfo) data.getSerializableExtra(CoustomCaptureActivity.BUNDLE_DATA);
                    renxiangpath = info.getImageUrl();
                    xingming = info.getName();//解析出来的姓名
                    sex = info.getSex();//解析出来的性别
                    nation = info.getNation();//解析出来的民族
                    birth = info.getBirth();//解析出来的出生日期
                    address = info.getAddress();//解析出来的住址
                    idnumber = info.getId();//解析出来的身份证号码
                    String path = info.getImageUrl();
                    Display display = getWindowManager().getDefaultDisplay(); // 显示屏尺寸
                    float destWidth = display.getWidth();
                    float destHeight = display.getHeight();
                    // 读取本地图片尺寸
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(path, options);// 设置为true，options依然对应此图片，但解码器不会为此图片分配内存
                    float srcWidth = options.outWidth;
                    float srcHeight = options.outHeight;
                    int inSampleSize = 1;
                    if (srcHeight > destHeight || srcWidth > destWidth) { // 当图片长宽大于屏幕长宽时
                        if (srcWidth > srcHeight) {
                            inSampleSize = Math.round(srcHeight / destHeight);
                        } else {
                            inSampleSize = Math.round(srcWidth / destWidth);
                        }
                    }
                    options = new BitmapFactory.Options();
                    options.inSampleSize = inSampleSize;
                    Bitmap bitmap = BitmapFactory.decodeFile(path, options);
                    BitmapDrawable bDrawable = new BitmapDrawable(getResources(), bitmap);
                    IVFacePhoto.setBackground(bDrawable);
                    IVFacePhoto.setImageResource(R.mipmap.shuiyin);
//                    renxiangFangda.setVisibility(View.VISIBLE);
//                    renxiangShanchu.setVisibility(View.VISIBLE);
                    integer_renxiang = bitmap;
                    renxiang = info.getImageUrl();
                    ID = info.getId();
                    name = info.getName();
                    if (i < 4) {
                        i++;
                    } else {
                        i = 4;
                    }
                } catch (Exception e) {
                }
            } else if (requestCode == 1) {
                try {
                    /**
                     * 该uri就是照片文件夹对应的uri
                     */
                    IdCardInfo info = (IdCardInfo) data.getSerializableExtra(CoustomCaptureActivity.BUNDLE_DATA);
                    guohuipath = info.getImageUrl();
                    String path = info.getImageUrl();
                    authority = info.getAuthority();//解析出来的签发机关；
                    validDate = info.getValidDate();//解析出来的有效期限；
                    Display display = getWindowManager().getDefaultDisplay(); // 显示屏尺寸
                    float destWidth = display.getWidth();
                    float destHeight = display.getHeight();
                    // 读取本地图片尺寸
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(path, options);// 设置为true，options依然对应此图片，但解码器不会为此图片分配内存
                    float srcWidth = options.outWidth;
                    float srcHeight = options.outHeight;
                    int inSampleSize = 1;
                    if (srcHeight > destHeight || srcWidth > destWidth) { // 当图片长宽大于屏幕长宽时
                        if (srcWidth > srcHeight) {
                            inSampleSize = Math.round(srcHeight / destHeight);
                        } else {
                            inSampleSize = Math.round(srcWidth / destWidth);
                        }
                    }
                    options = new BitmapFactory.Options();
                    options.inSampleSize = inSampleSize;
                    Bitmap bitmap = BitmapFactory.decodeFile(path, options);
                    BitmapDrawable bDrawable = new BitmapDrawable(getResources(), bitmap);
                    IVReverseSide.setBackground(bDrawable);
                    IVReverseSide.setImageResource(R.mipmap.shuiyin);
//                    guohuoFangda.setVisibility(View.VISIBLE);
//                    guohuiShanchu.setVisibility(View.VISIBLE);
                    guohui = info.getImageUrl();
                    integer_guohui = bitmap;
                    if (i < 4) {
                        i++;
                    } else {
                        i = 4;
                    }
                } catch (Exception e) {
                }
//
            }

        }
    }



    /**
     * 弹出框
     *
     * @param integer
     */
    private void showpopuwindow(Bitmap integer) {
//        final AlertDialog builder = new AlertDialog.Builder(this, R.style.Dialog_FS)
//                .create();
//        builder.show();
//        if (builder.getWindow() == null) return;
//        builder.getWindow().setContentView(R.layout.big_image_dialog);//设置弹出框加载的布局
//        back = builder.findViewById(R.id.back);
//        imagebig = builder.findViewById(R.id.imagebig);
//        imagebig.setImageBitmap(integer);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                builder.cancel();
//            }
//        });
//        builder.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

    public static final int PHOTO_REQUEST_CAREMA = 4;// 拍照
    public static final int CROP_PHOTO = 5;
    private Uri imageUri;

    public static File tempFile;

    public void openCamera(Activity activity) {
        //獲取系統版本
        int currentapiVersion = Build.VERSION.SDK_INT;
        // 激活相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                    "yyyy_MM_dd_HH_mm_ss");
            String filename = timeStampFormat.format(new Date());
            tempFile = new File(Environment.getExternalStorageDirectory(),
                    filename + ".jpg");
            if (currentapiVersion < 24) {
                // 从文件中创建uri
                imageUri = Uri.fromFile(tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            } else {
                //兼容android7.0 使用共享文件的形式
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
                //检查是否有存储权限，以免崩溃
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    Toast.makeText(this,"请开启存储权限",Toast.LENGTH_SHORT).show();
                    return;
                }
                imageUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        activity.startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }

    /*
* 判断sdcard是否被挂载
*/
    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

}
