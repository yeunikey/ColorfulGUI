package me.xflyiwnl.colorfulgui.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class TextUtil {

    public static String colorize(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg.
                replaceAll("#([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])", "&x&$1&$2&$3&$4&$5&$6"));
    }

    public static List<String> colorize(List<String> list) {
        List<String> hexList = new ArrayList<String>();

        for (String key : list) {
            hexList.add(colorize(key));
        }

        return hexList;
    }

}
