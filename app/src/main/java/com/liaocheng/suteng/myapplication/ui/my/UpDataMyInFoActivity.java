package com.liaocheng.suteng.myapplication.ui.my;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.circle.common.base.BaseActivity;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.BuildConfig;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.api.SYPTService;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.presenter.UpDataPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.UpDataMyContact;
import com.liaocheng.suteng.myapplication.util.Util;
import com.liaocheng.suteng.myapplication.view.GlideImageLoader;
import com.liaocheng.suteng.myapplication.view.SelectDialog;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class UpDataMyInFoActivity extends BaseActivity<UpDataPresenter> implements UpDataMyContact.View {

    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.tvTouXiang)
    TextView tvTouXiang;
    @BindView(R.id.civHead)
    CircleImageView civHead;
    @BindView(R.id.tvName)
    EditText tvName;
    @BindView(R.id.tvTel)
    TextView tvTel;
    @BindView(R.id.tvOK)
    TextView tvOK;

    @Override
    public int getLayoutId() {
        return R.layout.activity_updatamy;
    }
String name;
    String img;
    String tel;
    @Override
    public void initEventAndData() {
        toolBar.setTitleText("个人资料").setBackFinish();
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        img = intent.getStringExtra("img");
        tel = intent.getStringExtra("tel");
        tvTel.setText(tel+"");
        tvName.setText(name+"");
        Picasso.with(this)
                .load(img)
                .placeholder(R.mipmap.sanyangtubiao)
                .error(R.mipmap.sanyangtubiao)
                .into(civHead);
        initImagePicker();
    }

//    private static final Retrofit sRetrofit = new Retrofit .Builder()
//            .baseUrl(ENDPOINT)
//            .addConverterFactory(GsonConverterFactory.create())
//// .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
//            .build();

    //    private static final SYPTService apiManager = sRetrofit.create(SYPTService.class);



    /**
     * 根据图片的大小设置压缩的比例，提高速度
     *
     * @param imageMB
     * @return
     */
    private static int setSubstractSize(int imageMB) {

        if (imageMB > 1000) {
            return 60;
        } else if (imageMB > 750) {
            return 40;
        } else if (imageMB > 500) {
            return 20;
        } else {
            return 10;
        }

    }
    /**
     * 根据分辨率压缩图片比例
     *
     * @param imgPath
     * @param w
     * @param h
     * @return
     */
    private static Bitmap compressByResolution(String imgPath, int w, int h) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imgPath, opts);

        int width = opts.outWidth;
        int height = opts.outHeight;
        int widthScale = width / w;
        int heightScale = height / h;

        int scale;
        if (widthScale < heightScale) { //保留压缩比例小的
            scale = widthScale;
        } else {
            scale = heightScale;
        }
        if (scale < 1) {
            scale = 1;
        }
        opts.inSampleSize = scale;

        opts.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeFile(imgPath, opts);

        return bitmap;
    }

    /**
     * 质量压缩方法
     * @param image
     * @return
     */
    /**
     * 根据分辨率压缩
     *
     * @param srcPath 图片路径
     * @param ImageSize 图片大小 单位kb
     * @return
     */
    public static boolean compressBitmap(String srcPath, int ImageSize, String savePath) {
        int subtract;

        Bitmap bitmap = compressByResolution(srcPath, 200, 200); //分辨率压缩
        if (bitmap == null) {

            return false;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中

        while (baos.toByteArray().length > ImageSize * 1024) { //循环判断如果压缩后图片是否大于ImageSize kb,大于继续压缩
            subtract = setSubstractSize(baos.toByteArray().length / 1024);
            baos.reset();//重置baos即清空baos
            options -= subtract;//每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中

        }

        try {
            FileOutputStream fos = new FileOutputStream(new File(savePath));//将压缩后的图片保存的本地上指定路径中
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (bitmap != null) {
            bitmap.recycle();
        }

        return true; //压缩成功返回ture
    }
String path = Environment.getExternalStorageDirectory()+"/userHead.jpg";


    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    private ArrayList<String> mImgList = new ArrayList<>();
int maxImgCount =1;
    //配置选择相片
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setMultiMode(true);                       //图片选择模式   fale单选   true多选
        imagePicker.setSaveRectangle(false);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    ArrayList<ImageItem> images = new ArrayList<>();//选择的图片
    public static final int IMAGE_ITEM_ADD = -1;//添加图片框
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                        for (int i = 0;i<images.size();i++){
//                            BitmapUtil.saveBitmapToShare(images.get(i).path,100);
                            mImgList.add(images.get(i).path+"");
                        }

                    String path = mImgList.get(0);
                    Bitmap bm = BitmapFactory.decodeFile(path);
                    civHead.setImageBitmap(bm);//不会变形
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    for (int i = 0;i<images.size();i++){
//                            BitmapUtil.saveBitmapToShare(images.get(i).path,100);
                        mImgList.add(images.get(i).path+"");
                    }

                }
            }
        }

    }
    //自定义弹框
    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(UpDataMyInFoActivity.this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }
    @OnClick({R.id.civHead, R.id.tvOK})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.civHead:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机

                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - mImgList.size());
                                Intent intent = new Intent(UpDataMyInFoActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - mImgList.size());
                                Intent intent1 = new Intent(UpDataMyInFoActivity.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);
                break;
            case R.id.tvOK:
                if (TextUtils.isEmpty(tvName.getText().toString().trim())){
                    ToastUtil.show("昵称不能为空");
                }else {
                    if (mImgList!=null&&mImgList.size()>0){
//                    upload(mImgList,tvName.getText().toString()+"");
                        compressBitmap(mImgList.get(0),600,path);
                        String imgs = Util.imageToBase64(path);
                        mPresenter.getImg(imgs);
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                upImage(mImgList.get(0));
//                            }
//                        }).start();
                    }
                            if (!name.equals(tvName.getText().toString().trim())){
                                mPresenter.getName(tvName.getText().toString()+"");
                            }
            }

                break;
        }
    }

    @Override
    public void setName() {
        ToastUtil.show("修改成功");
    }

    @Override
    public void setImg() {
        ToastUtil.show("修改成功");
    }
}
