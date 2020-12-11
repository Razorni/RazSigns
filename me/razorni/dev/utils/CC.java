package me.razorni.dev.utils;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;

public class CC {
  public static String translate(String input) {
    return ChatColor.translateAlternateColorCodes('&', input);
  }
  
  public static List<String> translateFromArray(List<String> text) {
    List<String> messages = new ArrayList<>();
    for (String string : text)
      messages.add(translate(string)); 
    return messages;
  }
  
  public static String BLUE = ChatColor.BLUE.toString();
  
  public static String AQUA = ChatColor.AQUA.toString();
  
  public static String YELLOW = ChatColor.YELLOW.toString();
  
  public static String RED = ChatColor.RED.toString();
  
  public static String GRAY = ChatColor.GRAY.toString();
  
  public static String GOLD = ChatColor.GOLD.toString();
  
  public static String GREEN = ChatColor.GREEN.toString();
  
  public static String WHITE = ChatColor.WHITE.toString();
  
  public static String BLACK = ChatColor.BLACK.toString();
  
  public static String BOLD = ChatColor.BOLD.toString();
  
  public static String ITALIC = ChatColor.ITALIC.toString();
  
  public static String UNDER_LINE = ChatColor.UNDERLINE.toString();
  
  public static String STRIKE_THROUGH = ChatColor.STRIKETHROUGH.toString();
  
  public static String RESET = ChatColor.RESET.toString();
  
  public static String MAGIC = ChatColor.MAGIC.toString();
  
  public static String DARK_BLUE = ChatColor.DARK_BLUE.toString();
  
  public static String DARK_AQUA = ChatColor.DARK_AQUA.toString();
  
  public static String DARK_GRAY = ChatColor.DARK_GRAY.toString();
  
  public static String DARK_GREEN = ChatColor.DARK_GREEN.toString();
  
  public static String DARK_PURPLE = ChatColor.DARK_PURPLE.toString();
  
  public static String DARK_RED = ChatColor.DARK_RED.toString();
  
  public static String PINK = ChatColor.LIGHT_PURPLE.toString();
  
  public static String MENU_BAR = String.valueOf(String.valueOf(String.valueOf(ChatColor.GRAY.toString()))) + ChatColor.STRIKETHROUGH.toString() + "------------------------";
  
  public static String CHAT_BAR = String.valueOf(String.valueOf(String.valueOf(ChatColor.GRAY.toString()))) + ChatColor.STRIKETHROUGH.toString() + "------------------------------------------------";
  
  public static String SB_BAR = String.valueOf(String.valueOf(String.valueOf(ChatColor.GRAY.toString()))) + ChatColor.STRIKETHROUGH.toString() + "----------------------";
}
