package com.osreboot.tr.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Help {

	public static ItemStack intro, scroll, levitation, node, node2, skp, basic, back, helpi, up, down, more, title;
	public static Inventory help;
	
	private static int length = 34;
	
	public static void init(){
		help = Bukkit.createInventory(null, 54, "Getting to know Telekinesis");
		
		Main.holidayify(help);
		
		intro = new ItemStack(Material.PAPER);
		scroll = new ItemStack(Material.PAPER);
		levitation = new ItemStack(Material.DIRT);
		node = new ItemStack(Material.PAPER);
		node2 = new ItemStack(Material.PAPER);
		skp = new ItemStack(Material.PAPER);
		basic = new ItemStack(Material.PAPER);
		back = new ItemStack(Material.BOOK);
		helpi = new ItemStack(Material.BOOK);
		up = new ItemStack(Material.EMERALD);
		down = new ItemStack(Material.EMERALD);
		more = new ItemStack(Material.PAPER);
		title = new ItemStack(Material.PAPER);
		
		String[] d = Util.chop(ChatColor.YELLOW + "Welcome! This is your skill tree, get back here at any time with the command '/syntax'.", length, "");
		addData(intro, d);
		help.setItem(13, intro);
		
		String[] d2 = Util.chop(ChatColor.YELLOW + "Use these buttons to scroll up and down your skill tree.", length, "");
		addData(scroll, d2);
		help.setItem(7, scroll);
		
		String[] d3 = {ChatColor.GREEN + "Levitation [#/30]",
				ChatColor.BLUE + "Want to make things fly? Basic",
				ChatColor.BLUE + "levitation is nothing more than a",
				ChatColor.BLUE + "little determination and a lot of",
				ChatColor.BLUE + "free time." + ChatColor.LIGHT_PURPLE + " Vertical block",
				ChatColor.LIGHT_PURPLE + "levitation.",
				ChatColor.DARK_GRAY + "Hold right mouse and shift",
				ChatColor.DARK_GRAY + "simultaneously while looking at a",
				ChatColor.DARK_GRAY + "block. Hold shift and left click",
				ChatColor.DARK_GRAY + "to drop levitated blocks."};
		addData(levitation, d3);
		help.setItem(49, levitation);
		
		String[] d4 = Util.chop(ChatColor.YELLOW + "This is a node, left click when you have skill points to level it up and increase the efficacy of the ability!", length, "");
		addData(node, d4);
		help.setItem(48, node);
		
		String[] d5 = Util.chop(ChatColor.YELLOW + "Getting your first skill point will be a hassle. Hold shift and right mouse simultaneously while looking at a block (not a tile entity!) that you want to levitate.", length, "");
		addData(basic, d5);
		help.setItem(22, basic);
		
		String[] d6 = {ChatColor.GREEN + "Node name [your level/maximum level]",
				ChatColor.BLUE + "Node description",
				ChatColor.LIGHT_PURPLE + "Node functions",
				ChatColor.DARK_GRAY + "Node controls"};
		addData(node2, d6);
		help.setItem(50, node2);
		
		String[] d7 = Util.chop(ChatColor.YELLOW + "These are your skill points, they can be spent on upgrading nodes. You can earn more by practicing different telekinetic abilities.", length, "");
		addData(skp, d7);
		help.setItem(3, skp);
		
		String[] d8 = {ChatColor.YELLOW + "Back"};
		addData(back, d8);
		help.setItem(45, back);
		
		String[] d9 = {ChatColor.YELLOW + "Need help getting started?"};
		addData(helpi, d9);
		
		String[] d10 = {ChatColor.YELLOW + "Up"};
		addData(up, d10);
		help.setItem(8, up);
		
		String[] d11 = {ChatColor.YELLOW + "Down"};
		addData(down, d11);
		help.setItem(53, down);
		
		String[] d12 = Util.chop(ChatColor.YELLOW + "You can unlock more nodes by leveling up earlier ones in the tree.", length, "");
		addData(more, d12);
		help.setItem(40, more);
		
		String[] d13 = Util.chop(ChatColor.YELLOW + "Your title and total skill level is displayed here.", length, "");
		addData(title, d13);
		help.setItem(0, title);
	}
	
	private static void addData(ItemStack i, String[] lore){
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(lore[0]);
		m.setLore(toArrayMinus(lore));
		i.setItemMeta(m);
	}
	
	private static ArrayList<String> toArrayMinus(String[] s){
		ArrayList<String> sa = new ArrayList<String>();
		for(int i = 1; i < s.length; i++) sa.add(s[i]);
		return sa;
	}
	
}
