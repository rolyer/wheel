package com.wheel.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

public class FileUitl {

    /**
     * 将文件转成base64 字符串
     *
     * @param path
     * @return *
     * @throws Exception
     */

    public static String encodeBase64File(String path) throws IOException {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);

        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);

    }

    public static String encodeBase64File(byte[] bytes) throws IOException {
        return new BASE64Encoder().encode(bytes);
    }

    public static byte[] decoderBase64ToByte(String base64Code)
            throws Exception {
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);

        return buffer;
    }

    /**
     * 将base64字符解码保存文件
     *
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */

    public static void decoderBase64File(String base64Code, String targetPath)
            throws Exception {
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();

    }

    /**
     * 将base64字符保存文本文件
     *
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */

    public static void toFile(String base64Code, String targetPath)
            throws Exception {
        File dir = new File(targetPath);
        if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
            dir.mkdirs();
        }
        if (dir.isFile() && dir.exists()) {
            dir.deleteOnExit();
        }

        byte[] buffer = base64Code.getBytes();
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }

    /**
     * 将base64字符保存文本文件
     *
     * @param
     * @param targetPath
     * @throws Exception
     */

    public static void toFile(byte[] bytes, String targetPath)
            throws Exception {
        File dir = new File(targetPath);
        if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
            dir.mkdirs();
        }
        if (dir.isFile() && dir.exists()) {
            dir.deleteOnExit();
        }

        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(bytes);
        out.close();
    }

    public static void main(String[] args) {
        try {
            String base64Code = encodeBase64File("D:/0101-2011-qqqq.tif");
            System.out.println(base64Code);
            decoderBase64File(base64Code, "D:/2.tif");
            toFile(base64Code, "D:\\three.txt");
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static String judgeTxtCode(String path) throws Exception {
        String code = "";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
            int a = fis.read();
            int b = fis.read();
            if (a == 0xFF && b == 0xFE) {
                code = "Unicode";
            } else if (a == 0xFE && b == 0xFF) {
                code = "UTF-16BE";
            } else if (a == 0xEF && b == 0xBB) {
                code = "UTF-8";
            } else {
                code = "GBK";
            }
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return code;
    }

    public static InputStream byte2Input(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }

    public static byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }


    public static byte[] File2byte(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static void byte2File(byte[] buf, String filePath) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath);
            file = new File(filePath);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
