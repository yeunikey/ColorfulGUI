package me.xflyiwnl.colorfulgui.util;

import org.bukkit.ChatColor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TextUtil {

    public static List<String> colorize(List<String> list) {
        List<String> hexList = new ArrayList<>();

        for (String key : list) {
            hexList.add(colorize(key));
        }

        return hexList;
    }

    public static String colorize(String msg) {
        if (msg.charAt(0) != '#' || msg.charAt(msg.length() - 7) != '#'){
            return ChatColor.translateAlternateColorCodes('&', msg.replaceAll("#([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])", "&f&x&$1&$2&$3&$4&$5&$6"));
        }

        Color firstCode = hexToColor(msg.substring(1,7).toUpperCase());
        Color secondCode = hexToColor(msg.substring(msg.length() - 6).toUpperCase());

        String uncolored = msg.replaceAll("#([a-fA-F0-9]){6}", "");

        return "§f" + ChatColor.translateAlternateColorCodes('&', gradient(firstCode, secondCode, uncolored));
    }

    private static Color hexToColor(String s){
        int r = Integer.parseInt(s.substring(0,2), 16);
        int g = Integer.parseInt(s.substring(2,4), 16);
        int b = Integer.parseInt(s.substring(4,6), 16);
        return new Color(r, g, b);
    }

    private static String gradient(Color color1, Color color2, String msg){
        int incrementR = (color1.getRed() - color2.getRed()) / msg.length();
        int incrementG = (color1.getGreen() - color2.getGreen()) / msg.length();
        int incrementB = (color1.getBlue() - color2.getBlue()) / msg.length();

        StringBuilder colorized = new StringBuilder();

        for (int i = 1; i <= msg.length(); i++){
            Color step = new Color(color1.getRed() - incrementR*i, color1.getGreen() - incrementG*i, color1.getBlue() - incrementB*i);
            colorized.append(net.md_5.bungee.api.ChatColor.of(step));
            colorized.append(msg.charAt(i - 1));
        }

        return colorized.toString();
    }

}
