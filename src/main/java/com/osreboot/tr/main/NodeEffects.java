package com.osreboot.tr.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class NodeEffects implements Listener{

	public static ArrayList<NodeEffects> effects = new ArrayList<NodeEffects>();
	
	private HashMap<UUID, Integer> effectCooldown;
	
	public NodeEffects(){
		Bukkit.getPluginManager().registerEvents(this, Main.plugin);
		effects.add(this);
		effectCooldown = new HashMap<UUID, Integer>();
	}
	
	public void tick(DataTable d){
		if(!effectCooldown.containsKey(d.getPlayer().getUniqueId())) effectCooldown.put(d.getPlayer().getUniqueId(), 0);
		if(effectCooldown.get(d.getPlayer().getUniqueId()) > 0) effectCooldown.put(d.getPlayer().getUniqueId(), effectCooldown.get(d.getPlayer().getUniqueId()) - 1);
	}
	
	public void onInteract(PlayerInteractEvent evt, DataTable d){}
	public void onInteractEntity(PlayerInteractEntityEvent evt, DataTable d){}
	public void onMove(PlayerMoveEvent evt, DataTable d){}
	
	public boolean effectIsCooledDown(Player p){
		return effectCooldown.containsKey(p.getUniqueId()) && effectCooldown.get(p.getUniqueId()) < 1;
	}
	
	public void effectSetCooldown(Player p, int time){
		effectCooldown.put(p.getUniqueId(), time);
	}
	
}
