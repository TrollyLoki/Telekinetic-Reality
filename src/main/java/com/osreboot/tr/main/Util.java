package com.osreboot.tr.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

public class Util {

	public static Block getTargetBlock(Player p, int range){
		BlockIterator bi = new BlockIterator(p, range);
		Block block = bi.next();
		while(bi.hasNext()){
			block = bi.next();
			if(block.getType() != Material.AIR) break;
		}
		return block;
	}
	
	@Deprecated
	public static ArrayList<Entity> getEntitysFromUUIDs(ArrayList<UUID> u){
		ArrayList<Entity> e = new ArrayList<Entity>();
		for(UUID uid : u){
			for(Player p : Bukkit.getOnlinePlayers()){
				for(Entity e1 : p.getWorld().getEntities()){
					if(e1.getUniqueId() == uid && !e.contains(e1)) e.add(e1);
				}
			}
		}
		return e;
	}
	
	public static void removeItemFromInventory(Player p, ItemStack i){
		boolean finished = false;
		for(ItemStack it : p.getInventory().getContents()){
			if(!finished){
				if(it != null && it.getType() == i.getType()){
					if(it.getAmount() > 1){
						it.setAmount(it.getAmount() - 1);
						finished = true;
					}else if(it.getAmount() == 1){
						it.setAmount(0);
						finished = true;
					}
				}
			}
		}
	}
	
	public static void clearHash(HashMap<FallingBlock, UUID> h, UUID n){
		ArrayList<FallingBlock> t = new ArrayList<FallingBlock>();
		for(FallingBlock u : h.keySet()) if(h.get(u) == n) t.add(u);
		for(FallingBlock u : t) h.remove(u);
	}
	
	public static ArrayList<FallingBlock> getPlayerUUIDS(HashMap<FallingBlock, UUID> h, UUID n){
		ArrayList<FallingBlock> a = new ArrayList<FallingBlock>();
		for(FallingBlock u : h.keySet()) if(h.get(u) == n && !a.contains(u)) a.add(u);
		return a;
	}
	
	public static String[] chop(String s, int length, String prefix){
		ArrayList<String> fin = new ArrayList<String>();
		boolean deadp = prefix == "";
		int detail = 0;
		String current = "";
		String nextPref = "";
		
		for(int i = 0; i < s.length(); i++){
			if(detail < length){
				
				if(deadp) if(s.charAt(i) == ChatColor.COLOR_CHAR) nextPref = s.charAt(i) + "" + s.charAt(i + 1);
				
				current += s.charAt(i);
				if(s.charAt(i) != ChatColor.COLOR_CHAR) if((i != 0 && s.charAt(i - 1) != ChatColor.COLOR_CHAR) || i == 0) detail++;
			}else{
				s = "W" + s;
				while(s.charAt(i) != ' ') s = "W" + s;
				while(current.charAt(current.length() - 1) != ' ') current = new StringBuilder(current).deleteCharAt(current.length() - 1).toString();
				detail = 0;
				fin.add(prefix + current);
				if(deadp) prefix = nextPref;
				current = "";
			}
			if(i == s.length() - 1) fin.add(prefix + current);
		}
		
		if(fin.size() == 0) fin.add(s);
		
		String[] a = new String[fin.size()];
		for(String st : fin) a[fin.indexOf(st)] = st;
		return a;
	}
	
	public static boolean isNearLiquid(Player p, int dist){
		for(int x = -dist; x <= dist; x++){
			for(int y = -dist; y <= dist; y++){
				for(int z = -dist; z <= dist; z++){
					Location l = p.getLocation().getBlock().getLocation().add(x, y, z);
					if(Main.liquids.contains(l.getBlock().getType()) && l.distance(p.getLocation().getBlock().getLocation()) < dist){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static void dropPerfectly(Entity e){
		double x = e.getLocation().getX();
		double z = e.getLocation().getZ();
		double xmod = 0, zmod = 0;
		if(Math.round(e.getLocation().getX()) > x) xmod = -0.5; else xmod = 0.5;
		if(Math.round(e.getLocation().getZ()) > z) zmod = -0.5; else zmod = 0.5;
		x = Math.round(x);
		z = Math.round(z);
		e.teleport(new Location(e.getWorld(), x + xmod, e.getLocation().getY(), z + zmod));
		e.setVelocity(new Vector(0, e.getVelocity().getY(), 0));
	}
	
}
