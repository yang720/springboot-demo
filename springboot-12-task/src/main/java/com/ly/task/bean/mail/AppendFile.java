package com.ly.task.bean.mail;

/**
 * 上传文件信息
 * @author liuyang
 * @date 2020/3/12 23:36
 */
public class AppendFile {

    private String filename;
    private String pathname;

    public AppendFile() {

    }

    public AppendFile(String filename, String pathname) {
        this.filename = filename;
        this.pathname = pathname;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }
}
