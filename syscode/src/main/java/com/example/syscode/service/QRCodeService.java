package com.example.syscode.service;

import com.example.syscode.util.QRCodeUtils;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author liuyang
 * @date 2020/10/31 13:50
 */
@Service
public class QRCodeService {

    /**
     * 生成二维码
     * @return
     */
    public byte[] getQRCode(String contents) {

        byte[] qrCode = null;
        try {
            qrCode = QRCodeUtils.getBitMatrixByQcw(contents);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }

        return qrCode;
    }

}
