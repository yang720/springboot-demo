package com.example.syscode.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 生成二维码工具类
 * @author liuyang
 * @date 2020/10/28 14:43
 */
public class QRCodeUtils {
    /**
     * 二维码大小(一般二维码为正方形，高宽统一)
     */
    private static final int SIZE = 200;

    /**
     * 方法1 ：使用MultiFormatWriter类生成二进制二维码
     * @param contents 二维码内容
     * @return BitMatrix类
     * @throws WriterException
     * @throws IOException
     */
    public static BitMatrix getBitMatrixByMfw(String contents) throws WriterException, IOException {

        return new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, SIZE, SIZE, codeFormatMap());
    }

    /**
     * 方法2 ：使用QRCodeWriter类生成二进制二维码
     * @param contents 二维码内容
     * @return         二维码字节数组
     * @throws WriterException
     * @throws IOException
     */
    public static byte[] getBitMatrixByQcw(String contents) throws WriterException, IOException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(contents, BarcodeFormat.QR_CODE, SIZE, SIZE, codeFormatMap());

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();

        return pngData;
    }

    /**
     * 设置二维码格式所需参数
     * @return Map<EncodeHintType, Object>
     */
    private static Map<EncodeHintType, Object> codeFormatMap() {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 指定纠错级别(L--7%,M--15%,Q--25%,H--30%),纠错的能力越高可存储的越少，一般使用M
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 编码内容,编码类型(这里指定为二维码),生成图片宽度,生成图片高度,设置参数
        return hints;
    }

}
