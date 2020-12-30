package me.razorni.dev.listeners;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

// Contribute to Marko / drzey

public class FreeBlocksListener implements Listener {
  
  private String[] buildingBlocks = new String[] { "&7[&bFree&7]", "&3Blocks", "", "", "[Blocks]" };
  
  public boolean isSignEqualTo(Sign sign, String[] to) {
    if (sign.getLines()[0] == null)
      return false; 
    return sign.getLines()[0].equals(ChatColor.translateAlternateColorCodes('&', to[0]));
  }
  
  @EventHandler
  public void onSignChange(SignChangeEvent event) {
    Player player = event.getPlayer();
    if (!player.isOp())
      return; 
    String line = ChatColor.stripColor(event.getLine(0));
    Arrays.<String[]>asList(new String[][] { this.buildingBlocks }).forEach(strings -> {
          if (line.equalsIgnoreCase(strings[4]))
            for (int i = 0; i < 4; i++)
              event.setLine(i, ChatColor.translateAlternateColorCodes('&', strings[i]));  
        });
  }
  
  @EventHandler
  public void onSignClick(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
      return; 
    Block block = event.getClickedBlock();
    if (block == null)
      return; 
    if (!(block.getState() instanceof Sign))
      return; 
    Sign sign = (Sign)block.getState();
      if (isSignEqualTo(sign, this.buildingBlocks)) {
      Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', "&bBlocks"));
      for (Colors colors : Colors.values()) {
        ItemStack item = new ItemStack(Material.WOOL, 64);
        item.setDurability((short)(byte)colors.getDurability());
        inventory.addItem(new ItemStack[] { item });
      } 
      for (Colors colors : Colors.values()) {
        ItemStack item = new ItemStack(Material.STAINED_GLASS, 64);
        item.setDurability((short)(byte)colors.getDurability());
        inventory.addItem(new ItemStack[] { item });
      } 
      for (Colors colors : Colors.values()) {
        ItemStack item = new ItemStack(Material.STAINED_CLAY, 64);
        item.setDurability((short)(byte)colors.getDurability());
        inventory.addItem(new ItemStack[] { item });
      } 
      player.openInventory(inventory);
    } 
  }
  
  public enum Colors {
    WHITE(0),
    ORANGE(1),
    MAGENTA(2),
    LIGHT_BLUE(3),
    YELLOW(4),
    LIME(5),
    PINK(6),
    GRAY(7),
    LIGHT_GRAY(8),
    CYAN(9),
    PURPLE(10),
    BLUE(11),
    BROWN(12),
    GREEN(13),
    RED(14),
    BLACK(15);
    
    Colors(int durability) {
      this.durability = durability;
    }
    
    private int durability;
    
    public int getDurability() {
      return this.durability;
    }
  }
}
