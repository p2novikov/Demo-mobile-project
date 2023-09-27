package framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static String getMatchInStr(String template, String str) {
        String s = "";
        Pattern pattern = Pattern.compile(template);
        Matcher m = pattern.matcher(str);
        if (m.find()) {
            s = m.group();
        }
        return s;
    }
}
