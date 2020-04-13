package com.osreboot.tr.main.hooks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

import com.osreboot.tr.main.DataTable;
import com.osreboot.tr.main.Main;

public class ProtectionManager {

	public static HashMap<FallingBlock, UUID> abandoned = new HashMap<FallingBlock, UUID>();
	public static ArrayList<FallingBlock> toRemove = new ArrayList<FallingBlock>();

	public static void tick(){
		for(FallingBlock u : toRemove){
			abandoned.remove(u);
			DataTable.floaters.remove(u);
			DataTable.floatersSnd.remove(u);
		}
		toRemove.clear();
		for(FallingBlock u : abandoned.keySet()){
			if(!Main.canModify(Bukkit.getPlayer(abandoned.get(u)), u.getLocation())) dispose(Bukkit.getPlayer(abandoned.get(u)), u);
		}
		for(FallingBlock u : DataTable.floaters.keySet()){
			if(!Main.canModify(Bukkit.getPlayer(DataTable.floaters.get(u)), u.getLocation())) dispose(Bukkit.getPlayer(DataTable.floaters.get(u)), u);
		}
		for(FallingBlock u : DataTable.floatersSnd.keySet()){
			if(!Main.canModify(Bukkit.getPlayer(DataTable.floatersSnd.get(u)), u.getLocation())) dispose(Bukkit.getPlayer(DataTable.floatersSnd.get(u)), u);
		}
	}

	public static void dispose(Player p, FallingBlock e){
		toRemove.add(e);
		p.sendMessage(ChatColor.RED + "Your powers do not extend into this area.");
		e.getWorld().playEffect(e.getLocation(), Effect.MOBSPAWNER_FLAMES, 2000);
		e.remove();
	}

}
