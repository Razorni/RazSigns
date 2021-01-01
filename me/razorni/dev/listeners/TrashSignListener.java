package me.razorni.dev.listeners;

import me.razorni.dev.RazSigns;
import me.razorni.dev.utils.CC;
import org.bukkit.Bukkit;
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

public class TrashSignListener implements Listener {
	
  private RazSigns plugin = (RazSigns)RazSigns.getPlugin(RazSigns.class);
  
  private String[] lines = new String[] { CC.translate(this.plugin.getConfig().getString("TRASH-SIGN-LINE-1")), CC.translate(this.plugin.getConfig().getString("TRASH-SIGN-LINE-2")), CC.translate(this.plugin.getConfig().getString("TRASH-SIGN-LINE-3")), CC.translate(this.plugin.getConfig().getString("TRASH-SIGN-LINE-4")) };
  
  private String[] error = new String[] { CC.translate(this.plugin.getConfig().getString("TRASH-SIGN-LINE-1")), CC.translate(this.plugin.getConfig().getString("TRASH-SIGN-LINE-2")), CC.translate(this.plugin.getConfig().getString("TRASH-SIGN-LINE-3")), CC.translate(this.plugin.getConfig().getString("TRASH-SIGN-LINE-4")) };
  
  public TrashSignListener(RazSigns plugin) {}
  
  public Inventory openMainInventory(Player player) {
    Inventory inv = Bukkit.createInventory(null, 36, this.plugin.getConfig().getString("TRASH-SIGN-INVENTORY-TITLE"));
    player.openInventory(inv);
    return inv;
  }
  
  @EventHandler
  public void onSignPlace(SignChangeEvent event) {
    if (event.getLine(0).equals("[Trash]")) {
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
