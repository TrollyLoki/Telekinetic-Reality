package com.osreboot.tr.main.hooks;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.osreboot.tr.main.Main;

public abstract class Hook {

	public static ArrayList<Hook> hooks = new ArrayList<Hook>();
	
	private Plugin plugin;
	private boolean loaded = false;
	
	public Hook(String pluginName){
		try{
			this.plugin = Main.plugin.getServer().getPluginManager().getPlugin(pluginName);
			if(this.plugin != null) this.loaded = true;
		}catch(Exception e){
		}finally{
			if(!this.loaded) Main.plugin.logger.warning("Telekinetic Reality : a compatable version of " + pluginName + " was not found, ignoring.");
			else Main.plugin.logger.info("Telekinetic Reality : successfully connected to " + pluginName + "'s API!");
		}
		hooks.add(this);
	}
	
	public Plugin getPlugin(){
		return this.plugin;
	}
	
	public boolean isLoaded(){
		return this.loaded;
	}
	
	public abstract boolean canModify(Player p, Location l);
	
}
