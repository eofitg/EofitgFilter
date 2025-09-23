package net.eofitg.eofitgfilter.util;

public class StringUtil {

    public static final String FORMAT_SYMBOL = "§";

    public static String removeFormattingCodes(String text) {
        if (text == null || text.length() < 2) return text;
        final int len = text.length();
        final char[] chars = text.toCharArray();
        final char[] newChars = new char[len];
        int count = 0;
        for (int i = 0; i < len; i++) {
            final char c = chars[i];
            if (c == '§' && i + 1 < len && "0123456789abcdefklmnorABCDEFKLMNOR".indexOf(chars[i + 1]) != -1) {
                i++;
                continue;
            }
            newChars[count] = c;
            count++;
        }
        return new String(newChars, 0, count);
    }

}
