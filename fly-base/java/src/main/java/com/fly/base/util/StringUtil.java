package com.fly.base.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 字符串工具类
 *
 * @author weijiancai
 * @version 0.0.1
 */
public class StringUtil {
    public static int compareVersionNumbers(@Nullable String v1, @Nullable String v2) {
        if (v1 == null && v2 == null) {
            return 0;
        }
        if (v1 == null) {
            return -1;
        }
        if (v2 == null) {
            return 1;
        }

        String[] part1 = v1.split("[\\.\\_\\-]");
        String[] part2 = v2.split("[\\.\\_\\-]");

        int idx = 0;
        for (; idx < part1.length && idx < part2.length; idx++) {
            String p1 = part1[idx];
            String p2 = part2[idx];

            int cmp;
            if (p1.matches("\\d+") && p2.matches("\\d+")) {
                cmp = new Integer(p1).compareTo(new Integer(p2));
            }
            else {
                cmp = part1[idx].compareTo(part2[idx]);
            }
            if (cmp != 0) return cmp;
        }

        if (part1.length == part2.length) {
            return 0;
        }
        else if (part1.length > idx) {
            return 1;
        }
        else {
            return -1;
        }
    }

    @NotNull
    public static List<String> split(@NotNull String s, @NotNull String separator) {
        return split(s, separator, true);
    }

    @NotNull
    public static List<String> split(@NotNull String s, @NotNull String separator, boolean excludeSeparator) {
        return split(s, separator, excludeSeparator, true);
    }

    @NotNull
    public static List<String> split(@NotNull String s, @NotNull String separator, boolean excludeSeparator, boolean excludeEmptyStrings) {
        if (separator.isEmpty()) {
            return Collections.singletonList(s);
        }
        List<String> result = new ArrayList<String>();
        int pos = 0;
        while (true) {
            int index = s.indexOf(separator, pos);
            if (index == -1) break;
            final int nextPos = index + separator.length();
            String token = s.substring(pos, excludeSeparator ? index : nextPos);
            if (!token.isEmpty() || !excludeEmptyStrings) {
                result.add(token);
            }
            pos = nextPos;
        }
        if (pos < s.length() || (!excludeEmptyStrings && pos == s.length())) {
            result.add(s.substring(pos, s.length()));
        }
        return result;
    }

    public static boolean charsEqual(char a, char b, boolean ignoreCase) {
        return ignoreCase ? charsEqualIgnoreCase(a, b) : a == b;
    }
    public static boolean charsEqualIgnoreCase(char a, char b) {
        return a == b || toUpperCase(a) == toUpperCase(b) || toLowerCase(a) == toLowerCase(b);
    }

    public static String toUpperCase(String s) {
        StringBuilder answer = null;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char upcased = toUpperCase(c);
            if (answer == null && upcased != c) {
                answer = new StringBuilder(s.length());
                answer.append(s.substring(0, i));
            }

            if (answer != null) {
                answer.append(upcased);
            }
        }

        return answer == null ? s : answer.toString();
    }

    public static char toUpperCase(char a) {
        if (a < 'a') {
            return a;
        }
        if (a <= 'z') {
            return (char)(a + ('A' - 'a'));
        }
        return Character.toUpperCase(a);
    }

    public static char toLowerCase(char a) {
        if (a < 'A' || a >= 'a' && a <= 'z') {
            return a;
        }

        if (a <= 'Z') {
            return (char)(a + ('a' - 'A'));
        }

        return Character.toLowerCase(a);
    }
}
