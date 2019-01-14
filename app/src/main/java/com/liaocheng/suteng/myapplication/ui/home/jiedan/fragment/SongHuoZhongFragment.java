package com.liaocheng.suteng.myapplication.ui.home.jiedan.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.circle.common.base.BaseFragment;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.ReceiveOrderModel;
import com.liaocheng.suteng.myapplication.presenter.QuHuoPresenter;
import com.liaocheng.suteng.myapplication.presenter.SongHuoPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.QuHuoContent;
import com.liaocheng.suteng.myapplication.presenter.contract.SongHuoContent;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.adapter.RenWuAdapter;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.adapter.SongHuoZhongAdapter;
import com.liaocheng.suteng.myapplication.util.Util;
import com.liaocheng.suteng.myapplication.view.ChangeCultureLabourDialog;
import com.liaocheng.suteng.myapplication.view.GlideImageLoader;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.liaocheng.suteng.myapplication.ui.my.UpDataMyInFoActivity.REQUEST_CODE_SELECT;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 * Created by Administrator on 2018/11/6.
 */

@SuppressLint("ValidFragment")
public class SongHuoZhongFragment extends BaseFragment<SongHuoPresenter> implements SongHuoContent.View {
    int mId;
//    @BindView(R.id.toolBar)
//    MyToolBar toolBar;
    @BindView(R.id.ivNull)
    ImageView ivNull;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    @SuppressLint("ValidFragment")
    public SongHuoZhongFragment(int id) {
        mId = id;
    }

    @Override
    public void showError(int reqCode, String msg) {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_daiquhuo;
    }

    @Override
    public void initEventAndData() {
//        toolBar.setTitleText("我的关注").setBackFinish();

    }

    @Override
    public void initEventAndDataNoLazy() {
        super.initEventAndDataNoLazy();
        initImagePicker();
        initRecyclerView();
    }

    int page = 1;
    String duration = "7";
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.queryOnTheWayOrder(page+"");
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.queryOnTheWayOrder(page+"");
            }
        });

        recyclerView.setVisibility(View.VISIBLE);

            mAdapter = new SongHuoZhongAdapter(mContext, 0, new SongHuoZhongAdapter.FollowClickListener() {
                @Override
                public void onBtnClick(String type, String id) {
                    //type = 0  取货  1  返回大厅   2返回发货人
                    mPresenter.checkReceiveCode(id,type);
                }

                @Override
                public void onPhoto(String num,String code) {
                    mNum = num;
                    mCode = code;
                    changHead();
                }
            });
            recyclerView.setAdapter(mAdapter);
        mAdapter.setData(mList);
        recyclerView.refresh();
    }
    private String mNum = "1";
    String mCode;
    //裁剪图片
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setMultiMode(false);                       //图片选择模式   fale单选   true多选
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }
    /**
     * 更换头像
     */
    ChangeCultureLabourDialog dialog;
    public  void changHead(){
         dialog=new ChangeCultureLabourDialog(getContext());
        dialog.show();
        dialog.ivCultureOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.ll_logo_paizhao:
                        ImagePicker.getInstance().setSelectLimit(1);
                        Intent intent = new Intent(getContext(), ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, REQUEST_CODE_SELECT);
                        dialog.dismiss();
                        break;
                    case R.id.ll_logo_xiangze:
                        ImagePicker.getInstance().setSelectLimit(1);
                        Intent intent1 = new Intent(getContext(), ImageGridActivity.class);
                        /* 如果需要进入选择的时候显示已经选中的图片，
                         * 详情请查看ImagePickerActivity
                         * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                        startActivityForResult(intent1, REQUEST_CODE_SELECT);
                        dialog.dismiss();
                        break;
                    case R.id.ll_logo_quxiao:
                        dialog.dismiss();
                        break;
                }
            }
        });
    }
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ArrayList<ImageItem> mImgList = new ArrayList<>();
    //选择图片，返回处理事件
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                mImgList = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                Log.d("mImgList", "onActivityResult: "+mImgList.get(0).path);
//                Glide.with(mContext).load(mImgList.get(0).path).into(ivHeadimage);
                compressBitmap(mImgList.get(0).path,600,mImgList.get(0).path);
                String imgs = Util.imageToBase64(mImgList.get(0).path);
                mPresenter.order_upPhotoByIndex(mCode,imgs,mNum);
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                mImgList = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);

            }
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
    SongHuoZhongAdapter mAdapter;
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
List<ReceiveOrderModel.ReceiveOrderBean> mList = new ArrayList<>();
    @Override
    public void queryOnTheWayOrder(ReceiveOrderModel ReceiveOrderBean) {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        if (ReceiveOrderBean.data != null&&ReceiveOrderBean.data.size()>0) {
            if (page==1){
                mList.clear();
            }
            mList.addAll(ReceiveOrderBean.data);

            recyclerView.setVisibility(View.VISIBLE);
            ivNull.setVisibility(View.GONE);
            mAdapter.setData(mList);
        } else {
            if (page != 1) {
                ToastUtil.show("最后一页");
            } else {
                ivNull.setVisibility(View.VISIBLE);
                if (page==1){
                    mList.clear();
                }
                mList.addAll(ReceiveOrderBean.data);
            }
        }
    }
    @Override
    public void checkReceiveCode() {
        ToastUtil.show("确认成功");
        recyclerView.refresh();
    }

    @Override
    public void order_upPhotoByIndex() {
        ToastUtil.show("图片提交成功");
        recyclerView.refresh();
    }

    double bd_lat; double bd_lon;
    //高德转百度
    void bd_encrypt(double gg_lat, double gg_lon) {
        double x = gg_lon, y = gg_lat;
        double z = sqrt(x * x + y * y) + 0.00002 * sin(y * Math.PI);
        double theta = atan2(y, x) + 0.000003 * cos(x * Math.PI);
        bd_lon = z * cos(theta) + 0.0065;
        bd_lat = z * sin(theta) + 0.006;
    }
    double gg_lon;
    double gg_lat;
    //百度转高德
    void bd_decrypt(double bd_lat, double bd_lon) {
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = sqrt(x * x + y * y) - 0.00002 * sin(y * Math.PI);
        double theta = atan2(y, x) - 0.000003 * cos(x * Math.PI);
        gg_lon = z * cos(theta);
        gg_lat = z * sin(theta);
    }



}
