package me.razorni.dev.listeners;

import me.razorni.dev.RazSigns;
import me.razorni.dev.utils.CC;
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

public class RefillSignListener implements Listener {
  private RazSigns plugin = (RazSigns)RazSigns.getPlugin(RazSigns.class);
  
  private String[] lines = new String[] { CC.translate(this.plugin.getConfig().getString("REFILL-SIGN-LINE-1")), CC.translate(this.plugin.getConfig().getString("REFILL-SIGN-LINE-2")), CC.translate(this.plugin.getConfig().getString("REFILL-SIGN-LINE-3")), CC.translate(this.plugin.getConfig().getString("REFILL-SIGN-LINE-4")) };
  
  private String[] error = new String[] { CC.translate(this.plugin.getConfig().getString("REFILL-SIGN-LINE-1")), CC.translate(this.plugin.getConfig().getString("REFILL-SIGN-LINE-2")), CC.translate(this.plugin.getConfig().getString("REFILL-SIGN-LINE-3")), CC.translate(this.plugin.getConfig().getString("REFILL-SIGN-LINE-4")) };
  
  public RefillSignListener(RazSigns plugin) {}
  
  public Inventory openMainInventory(Player player) {
    Inventory inv = Bukkit.createInventory(null, 54, this.plugin.getConfig().getString("REFILL-SIGN-INVENTORY-TITLE"));
    inv.setItem(36, new ItemStack(Material.POTION, 1, (short)8259));
    inv.setItem(37, new ItemStack(Material.POTION, 1, (short)8259));
    inv.setItem(38, new ItemStack(Material.POTION, 1, (short)8259));
    inv.setItem(39, new ItemStack(Material.POTION, 1, (short)8259));
    inv.setItem(40, new ItemStack(Material.ENDER_PEARL, 16));
    inv.setItem(41, new ItemStack(Material.POTION, 1, (short)8226));
    inv.setItem(42, new ItemStack(Material.POTION, 1, (short)8226));
    inv.setItem(43, new ItemStack(Material.POTION, 1, (short)8226));
    inv.setItem(44, new ItemStack(Material.POTION, 1, (short)8226));
    inv.setItem(18, new ItemStack(Material.POTION, 1, (short)16421));
    inv.setItem(29, new ItemStack(Material.POTION, 1, (short)16421));
    ItemStack goldsword = new ItemStack(Material.GOLD_SWORD, 1);
    ItemStack healpot = new ItemStack(Material.POTION, 1, (short)16421);
    int in1;
    for (in1 = 0; in1 < 9; in1++)
      inv.setItem(in1, healpot); 
    for (in1 = 9; in1 < 18; in1++)
      inv.setItem(in1, healpot); 
    for (in1 = 19; in1 < 29; in1++)
      inv.setItem(in1, healpot); 
    for (in1 = 30; in1 < 36; in1++)
      inv.setItem(in1, healpot); 
    for (in1 = 45; in1 < 54; in1++)
      inv.setItem(in1, goldsword); 
    player.openInventory(inv);
    return inv;
  }
  
  @EventHandler
  public void onSignPlace(SignChangeEvent event) {
    if (event.getLine(0).equals("[Refill]")) {
      Player player = event.getPlayer();
      if (player.hasPermission(this.plugin.getConfig().getString("AUTO-CREATE-SIGN-PERMISSION"))) {
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
