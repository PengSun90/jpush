package com.xunmeng.jpush.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.xunmeng.jpush.R;
import com.xunmeng.jpush.utils.CommonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pengsun on 2016/3/8.
 */
public class AboutMeActivity extends BaseActivity {

    private ImageView img;

    private String aboutme = "电话：15618549655 \n 邮箱：554587344@qq.com \n 联系我。。。";
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_me);
        inUi();

    }

    private void inUi() {
        img = (ImageView) findViewById(R.id.bitmap_me);
        Bitmap bp = createQRImage(aboutme, img.getLayoutParams().width, img.getLayoutParams().height);
        img.setImageBitmap(bp);

        tv = (TextView)findViewById(R.id.tell);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+tv.getText().toString()));
                startActivity(intent);
            }
        });
    }


    /**
     * 生成二维码
     */
    public Bitmap createQRImage(String content, int widthPix, int heightPix) {
        try {
            if (TextUtils.isEmpty(content)) {
                return null;
            }

            //配置参数
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //容错级别
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            //设置空白边距的宽度
            hints.put(EncodeHintType.MARGIN, 1); //default is 4

            // 图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, widthPix, heightPix, hints);

            //去白边
            bitMatrix = updateBitMatrix(bitMatrix, 0);

            int qrRealWidth = bitMatrix.getWidth();
            int qrRealHeight = bitMatrix.getHeight();
            int[] pixels = new int[qrRealWidth * qrRealHeight];
            // 下面这里按照二维码的算法，逐个生成二维码的图片，
            // 两个for循环是图片横列扫描的结果
            for (int y = 0; y < qrRealHeight; y++) {
                for (int x = 0; x < qrRealWidth; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * qrRealWidth + x] = 0xff000000;
                    } else {
                        pixels[y * qrRealWidth + x] = 0xffffffff;
                    }
                }
            }

            // 生成二维码图片的格式
            Bitmap bitmap = Bitmap.createBitmap(qrRealWidth, qrRealHeight, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, qrRealWidth, 0, 0, qrRealWidth, qrRealHeight);
            //Scale
            Bitmap scaleBitmap = CommonUtils.compressImgByWidthAndHeight(bitmap, widthPix, heightPix);
            return scaleBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private BitMatrix updateBitMatrix(BitMatrix matrix, int margin) {
        int tempM = margin * 2;
        int[] rec = matrix.getEnclosingRectangle();   //获取二维码图案的属性
        int resWidth = rec[2] + tempM;
        int resHeight = rec[3] + tempM;
        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight); // 按照自定义边框生成新的BitMatrix
        resMatrix.clear();
        for (int i = margin; i < resWidth - margin; i++) {   //循环，将二维码图案绘制到新的bitMatrix中
            for (int j = margin; j < resHeight - margin; j++) {
                if (matrix.get(i - margin + rec[0], j - margin + rec[1])) {
                    resMatrix.set(i, j);
                }
            }
        }
        return resMatrix;
    }
}
