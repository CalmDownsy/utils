import arrayutils.ArrayUtils;
import exception.UtilsException;
import fileutils.FileUtils;
import org.testng.annotations.Test;
import stringutils.StringUtils;

import java.io.*;

/**
 * @author zhangsy
 * @date 2017/12/28
 */
public class AllTest {

    @Test
    public void test1() throws UtilsException {
        System.out.println(StringUtils.halfToFull("@#$^%#%&%^$#12345678"));
    }

    @Test
    public void test2() throws IOException, UtilsException {
        File sourceFile = new File("/Users/zhangsy/Desktop" + File.separator + "a");
        File destFile = new File("/Users/zhangsy/Desktop" + File.separator + "acopy");
        FileUtils.copyFile(sourceFile, destFile, true, ".DS_Store");
    }

    @Test
    public void test3() throws UtilsException {
        FileUtils.deleteFile(new File("/Users/zhangsy/Desktop" + File.separator + "a.txt"));
    }

    @Test
    public void test4() {
        System.out.println("\u963f\u91cc\u4e91");
    }

    @Test
    public void test5() throws UtilsException {
        System.out.println(FileUtils.getExtensionName(new File("/Users/zhangsy/Desktop/a/1.txt")));
        System.out.println(new File("/Users/zhangsy/Desktop/a/1.txt").getName());
    }

    @Test
    public void test6() {
        Integer[] a = {1,2,3};
        Integer[] integers = ArrayUtils.expandCapacity(a, 2);
        System.out.println(integers.length);
    }

    @Test
    public void test100() throws FileNotFoundException {
        File file = new File("");
        //字节流
        FileInputStream fs = new FileInputStream(file);
        //缓冲字节流 默认缓冲区8192
        BufferedInputStream bis = new BufferedInputStream(fs);
        //字符流
        FileReader fr = new FileReader(file);
        //缓冲字符流
        BufferedReader br = new BufferedReader(fr);
        //字节流转字符流
        InputStreamReader isr = new InputStreamReader(fs);
    }
}
