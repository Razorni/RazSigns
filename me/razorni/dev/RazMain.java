package me.razorni.dev;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import me.razorni.dev.listeners.RefillSignListener;
import me.razorni.dev.listeners.TrashSignListener;
import me.razorni.dev.listeners.FreeBlocksListener;

public class RazSigns extends JavaPlugin {
	
    public static RazSigns instance;
    
    public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[RazSigns] Plugin has been loaded successfully.");
        instance = this;    
        this.registerListeners();

    }
    
    private void registerListeners() {
        PluginManager manager = this.getServer().getPluginManager();
	    manager.registerEvents(new RefillSignListener(this), this);
	    manager.registerEvents(new TrashSignListener(this), this);
	    manager.registerEvents(new FreeBlocksListener(), this);
	    
    }

    public void onDisable() {   	
    }
	
    public static RazSigns getInstance() {
        return instance;
      }
}
