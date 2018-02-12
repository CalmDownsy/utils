package fileutils;

import constants.Constants;
import exception.UtilsException;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author zhangsy
 * @date 2017/12/28
 */
public class FileUtils {

    /**
     * 复制文件
     *
     * @param sourceFile  源文件
     * @param destFile    目标文件
     * @param overWritten 是否强制覆盖
     */
    public static void copyFile(File sourceFile, File destFile, boolean overWritten, String excludeFile) throws UtilsException, IOException {
        if (!sourceFile.exists()) {
            throw new UtilsException(Constants.FILE_EXCEPTION_CODE, sourceFile.getPath() + " 源文件不存在");
        }
        copyMultiFile(sourceFile, destFile, overWritten, excludeFile);
    }

    /**
     * 复制单个文件
     *
     * @param sourceFile  源文件
     * @param destFile    目标文件
     * @param overWritten 是否强制覆盖
     */
    private static void copySingleFile(File sourceFile, File destFile, boolean overWritten) throws UtilsException, IOException {
        if (destFile.exists()) {
            if (overWritten) {
                if (!destFile.delete()) {
                    throw new UtilsException(Constants.FILE_EXCEPTION_CODE, destFile.getPath() + " 不允许覆盖");
                }
            } else {
                return;
            }
        }

        FileInputStream fis = new FileInputStream(sourceFile);
        FileOutputStream fos = new FileOutputStream(destFile);
        //自定义缓冲区,也可使用缓冲字节流，默认缓冲区8192
        byte[] bufferSize = new byte[1024];
        int read;
        if ((read = fis.read(bufferSize)) != -1) {
            fos.write(bufferSize, 0, read);
        }
        fis.close();
        fos.close();
    }

    /**
     * 用作递归 递归 递归 递归 递归
     *
     * @param sourceFile  源文件
     * @param destFile    目标文件
     * @param overWritten 是否强制覆盖
     */
    private static void copyMultiFile(File sourceFile, File destFile, boolean overWritten, final String excludeFile) throws IOException, UtilsException {
        if (sourceFile.isFile()) {
            copySingleFile(sourceFile, destFile, overWritten);
        } else {
            if (!destFile.exists()) {
                destFile.mkdir();
            }
            FileFilter fileFilter = new FileFilter() {
                public boolean accept(File pathname) {
                    return !excludeFile.equals(pathname.getName());
                }
            };
            for (File childFile : sourceFile.listFiles(fileFilter)) {
                copyFile(childFile, new File(destFile.getPath() + File.separator + childFile.getName()), overWritten, excludeFile);
            }
        }
    }

    /**
     * 删除文件，如果是多级目录，需要递归删除  递归 递归 递归 递归
     *
     * @param file 需要删除的文件
     */
    public static void deleteFile(File file) throws UtilsException {
        if (null == file || !file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            boolean res = file.delete();
            if (!res) {
                throw new UtilsException(Constants.FILE_EXCEPTION_CODE, " 删除失败");
            }
        } else {
            for (File childFile : file.listFiles()) {
                deleteFile(childFile);
            }
            file.delete();
        }
    }

    /**
     * 获取文件扩展名
     *
     * @param file 指定文件
     * @return 文件扩展名
     */
    public static String getExtensionName(File file) throws UtilsException {
        if (null == file || !file.exists()) {
            throw new UtilsException(Constants.FILE_EXCEPTION_CODE, " 文件不存在");
        }
        String fileName = file.getName();
        int extensionIndex = fileName.lastIndexOf(Constants.FILE_EXTENSION_SEPARATOR);
        if (-1 != extensionIndex) {
            int separatorIndex = fileName.lastIndexOf(File.separator);
            if (separatorIndex > extensionIndex) {
                return null;
            }
        }
        return fileName.substring(extensionIndex + 1);
    }

    /**
     * 从网络下载文件到本地
     *
     * @param url      下载地址
     * @param fileName 保存的文件名
     * @param savePath 保存地址
     */
    public static void downloadFile(String url, String fileName, String savePath) throws IOException, UtilsException {
        if (StringUtils.isEmpty(url)) {
            throw new UtilsException(Constants.FILE_EXCEPTION_CODE, "下载地址错误");
        }
        URL downloadUrl = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) downloadUrl.openConnection();
        //设置超时时间
        httpURLConnection.setReadTimeout(3 * 1000);
        //获取文件流
        InputStream is = httpURLConnection.getInputStream();
        //文件保存位置
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File saveFile = new File(saveDir + File.separator + fileName);
        //自定义缓冲区
        byte[] bufferSize = new byte[1024];
        FileOutputStream fos = new FileOutputStream(saveFile);
        int read;
        while ((read = is.read(bufferSize)) != -1) {
            fos.write(bufferSize, 0, read);
        }
        //关闭文件流
        fos.close();
        is.close();
    }
}
