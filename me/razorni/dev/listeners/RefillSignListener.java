package me.razorni.dev.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.razorni.dev.RazMain;
import me.razorni.dev.utils.*;

public class RefillSignListener implements Listener {
  private String[] lines = new String[] { CC.translate("&7[&bRefill&7]"), CC.translate("&3Pots"), CC.translate(""), CC.translate("") };
  
  private String[] error = new String[] { CC.translate("&7[&bRefill&7]"), CC.translate("&3Pots"), CC.translate(""), CC.translate("") };
  
  public RefillSignListener(RazMain plugin) {}
  
  public Inventory openMainInventory(Player player) {
    Inventory inv = Bukkit.createInventory(null, 36, "§bRefill");
    inv.setItem(0, new ItemStack(Material.POTION, 1, (short)8226));
    inv.setItem(1, new ItemStack(Material.POTION, 1, (short)8226));
    inv.setItem(9, new ItemStack(Material.POTION, 1, (short)8226));
    inv.setItem(10, new ItemStack(Material.POTION, 1, (short)8226));
    inv.setItem(18, new ItemStack(Material.POTION, 1, (short)8226));
    inv.setItem(19, new ItemStack(Material.POTION, 1, (short)8226));
    inv.setItem(27, new ItemStack(Material.GOLD_SWORD, 1));
    inv.setItem(28, new ItemStack(Material.GOLD_SWORD, 1));
    inv.setItem(29, new ItemStack(Material.ENDER_PEARL, 16));
    inv.setItem(35, new ItemStack(Material.COOKED_BEEF, 64));
    inv.setItem(8, new ItemStack(Material.POTION, 1, (short)16421));
    inv.setItem(7, new ItemStack(Material.POTION, 1, (short)16421));
    ItemStack healpot = new ItemStack(Material.POTION, 1, (short)16421);
    int in1;
    for (in1 = 2; in1 < 7; in1++)
      inv.setItem(in1, healpot); 
    for (in1 = 11; in1 < 18; in1++)
      inv.setItem(in1, healpot); 
    for (in1 = 20; in1 < 27; in1++)
      inv.setItem(in1, healpot); 
    for (in1 = 30; in1 < 35; in1++)
      inv.setItem(in1, healpot); 
    player.openInventory(inv);
    return inv;
  }
  
  @EventHandler
  public void onSignPlace(SignChangeEvent event) {
    if (event.getLine(0).equals("[Refil]")) {
      Player player = event.getPlayer();
      if (player.hasPermission("hcf.comamnd.*")) {
        for (int i = 0; i < this.lines.length; i++)
          event.setLine(i, this.lines[i]); 
      } else {
        for (int i = 0; i < this.error.length; i++)
          event.setLine(i, this.error[i]); 
      } 
    } 
  }
  
  @EventHandler(priority = EventPriority.HIGH)
  public void onPlayerInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    Block block = event.getClickedBlock();
    if ((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) && block.getState() instanceof Sign) {
      Sign sign = (Sign)block.getState();
      for (int i = 0; i < this.lines.length; i++) {
        if (!sign.getLine(i).equals(this.lines[i]))
          return; 
      } 
      openMainInventory(player);
    } 
  }
}

