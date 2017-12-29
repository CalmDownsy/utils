package stringutils;

import constants.Constants;
import exception.UtilsException;

/**
 * @author zhangsy
 * @date 2017/12/28
 */
public class StringUtils {

    /**
     * 半角字符转全角字符
     */
    public static String halfToFull(String str) throws UtilsException {
        if (str == null || str.length() == 0) {
            throw new UtilsException(Constants.STRING_EXCEPTION_CODE, "空字符");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int position = Constants.HALF_STRING.indexOf(str.charAt(i));
            if (position != -1) {
                result.append(Constants.FULL_STRING[position]);
            } else {
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }

    /**
     *
     */
    public static Boolean containNone() {
        return null;
    }
}
