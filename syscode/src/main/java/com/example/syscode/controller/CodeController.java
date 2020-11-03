package com.example.syscode.controller;

import com.example.syscode.service.QRCodeService;
import com.example.syscode.util.QRCodeUtils;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author liuyang
 * @date 2020/10/28 14:53
 */
@Controller
public class CodeController {

    @Resource
    QRCodeService qrCodeService;

    @GetMapping(value = {"/","/qrcode"})
    public String qrcode() {
        return "qrcode";
    }

    /**
     * 使用QRCodeUtil2工具生成二维码
     * @return ResponseEntity<byte[]> 字节流输出
     */
    @GetMapping(value="/qrimage1")
    public ResponseEntity<byte[]> getQrCode() {
        //二维码内的信息
        String contents = "This is my first QR Code\n" +
                "黄成鹏武功天下第一\n" +
                "最近做到页面数据展示分页的功能，由于每个模块都需要分页，所以每个页面都需要将分页的页码选择内容重复的写N遍，如下所示：\n" +
                "首先在pom.xml引入thymeleaf的依赖";

        byte[] qrCode = qrCodeService.getQRCode(contents);

        // Set headers
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<> (qrCode, headers, HttpStatus.CREATED);
    }
    /**
     * QRCodeUtil2工具指定编码生成二维码
     * @return 字节流输出
     */
    @GetMapping(value="/qrimage2")
    public void getQrCode2(HttpServletResponse response) throws WriterException, IOException {
        //二维码内的信息
        String contents = "This is my first QR Code\n" +
                "黄成鹏武功天下第一\n" +
                "最近做到页面数据展示分页的功能，由于每个模块都需要分页，所以每个页面都需要将分页的页码选择内容重复的写N遍，如下所示：\n" +
                "首先在pom.xml引入thymeleaf的依赖";
        //设置请求头
        response.setHeader("Content-Type","application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + "二维码.png");

        // 编码内容,编码类型(这里指定为二维码),生成图片宽度,生成图片高度,设置参数
        BitMatrix bitMatrix = QRCodeUtils.getBitMatrixByMfw(contents);

        OutputStream outputStream = response.getOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
