package com.osreboot.tr.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Info {

	public static ItemStack back, infoi, twitter, twitch, bukkitdev, changelog, version, news;
	public static Inventory info;
	
	public static void init(){
		info = Bukkit.createInventory(null, 54, "TR Developer Information");
		
		Main.holidayify(info);
		
		back = new ItemStack(Material.ZOMBIE_HEAD);
		infoi = new ItemStack(Material.SKELETON_SKULL);
		twitter = new ItemStack(Material.FEATHER);
		twitch = new ItemStack(Material.LAPIS_LAZULI);
		bukkitdev = new ItemStack(Material.LAVA_BUCKET);
		changelog = new ItemStack(Material.WRITABLE_BOOK);
		version = new ItemStack(Material.PAPER);
		news = new ItemStack(Material.PAPER);
		
		String[] d = {ChatColor.YELLOW + "Back"};
		addData(back, d);
		info.setItem(45, back);
		
		int n = 5;
		for(int i = Changelog.getLog().length - 4; i < Changelog.getLog().length; i++){
			n--;
			ItemStack it = new ItemStack(Material.PAPER);
			addData(it, Util.chop(Changelog.getLog()[i], 36, ChatColor.GREEN + ""));
			info.setItem(29 + n, it);
		}
		
		String[] d2 = {ChatColor.BLUE + "Developer Information"};
		addData(infoi, d2);
		
		String[] d3 = {ChatColor.BLUE + "Twitter",
				ChatColor.BLUE + "@os_reboot"};
		addData(twitter, d3);
		info.setItem(48, twitter);
		
		String[] d4 = {ChatColor.BLUE + "Twitch",
				ChatColor.BLUE + "www.twitch.tv/os_reboot"};
		addData(twitch, d4);
		info.setItem(49, twitch);
		
		String[] d5 = {ChatColor.BLUE + "BukkitDev",
				ChatColor.BLUE + "osreboot"};
		addData(bukkitdev, d5);
		info.setItem(50, bukkitdev);
		
		String[] d6 = {ChatColor.GREEN + "Changelog",
				ChatColor.GREEN + "All the most recent changes",
				ChatColor.GREEN + "and additions!"};
		addData(changelog, d6);
		info.setItem(29, changelog);
		
		String[] d7 = {ChatColor.GOLD + "This server is running TR v" + Main.plugin.getDescription().getVersion()};
		addData(version, d7);
		info.setItem(12, version);
		
		String[] d8 = Util.chop(ChatColor.AQUA + "    News " + ChatColor.DARK_AQUA + Changelog.getNews(), 30, ChatColor.DARK_AQUA + "");
		addData(news, d8);
		info.setItem(14, news);
	}
	
	public static void addData(ItemStack i, String[] lore){
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(lore[0]);
		m.setLore(toArrayMinus(lore));
		i.setItemMeta(m);
	}
	
	public static ArrayList<String> toArrayMinus(String[] s){
		ArrayList<String> sa = new ArrayList<String>();
		for(int i = 1; i < s.length; i++) sa.add(s[i]);
		return sa;
	}
	
}
