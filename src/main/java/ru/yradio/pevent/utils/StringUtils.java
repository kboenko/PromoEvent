package ru.yradio.pevent.utils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    
    public static List<String> convertStrings2UTF8(List<String> stringList) {
       List<String> decodedStringList = new ArrayList<>();
        for (String str: stringList) {
            byte[] bytes = str.getBytes(StandardCharsets.ISO_8859_1);
            String newStr = new String(bytes, StandardCharsets.UTF_8);
            decodedStringList.add(newStr);
        }
        return decodedStringList;
    }
    
}
