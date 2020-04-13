package com.osreboot.tr.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.osreboot.tr.apis.FileAPI;
import com.osreboot.tr.apis.StatsFile;

public class DataTable {

	public static ArrayList<DataTable> tables = new ArrayList<DataTable>();

	public static DataTable findPlayer(Player p){
		for(DataTable d : tables) if(d.p == p) return d;
		return null;
	}

	public Player p;
	public int[] nodes = new int[Node.nodes.size()];
	public Inventory i;
	public String title;
	public int skp;
	public int discovered = 0;
	public int total = 0;
	public int syntax = 0;
	public int scroll = 0;

	private Random random;

	//First UUID is the entity, second UUID is the player
	public static HashMap<FallingBlock, UUID> floaters = new HashMap<FallingBlock, UUID>();
	//public static HashMap<FallingBlock, ArmorStand> shulkers = new HashMap<FallingBlock, ArmorStand>();
	public static HashMap<FallingBlock, UUID> floatersSnd = new HashMap<FallingBlock, UUID>();

	public DataTable(Player p){
		this.p = p;

		StatsFile f = new StatsFile(p.getUniqueId().toString(), "tr_playerdata");
		if(!f.doesExist()){
			if(FileAPI.doesFileExist(p.getName() + ".yml", "tr_playerdata")){//TODO the FileAPI is sloppy and outdated
				f = new StatsFile(p.getName(), "tr_playerdata");
				f.load();
			}else{
				f.generate();
				f.load();
				for(Node n : Node.nodes) f.getConfiguration().set(n.getName(), "0");
				f.getConfiguration().set("skp", "0");
				f.getConfiguration().set("discovered", "0");
				f.getConfiguration().set("total", "0");
				f.getConfiguration().set("syntax", "0");
			}
		}else f.load();

		for(Node n : Node.nodes) this.nodes[n.getIndex()] = verifyAndCache(f, n.getName());
		this.skp = verifyAndCache(f, "skp");
		this.discovered = verifyAndCache(f, "discovered");
		this.total = verifyAndCache(f, "total");
		this.syntax = verifyAndCache(f, "syntax");

		this.random = new Random();

		tables.add(this);
	}

	public Player getPlayer(){
		return p;
	}

	public int getSyntax(){
		return syntax;
	}

	public void setSyntax(int syntaxArg){
		syntax = syntaxArg;
	}

	private int verifyAndCache(StatsFile f, String path){
		if(!f.getConfiguration().contains(path)) f.getConfiguration().set(path, "0");
		return f.getConfiguration().getInt(path);
	}

	public void save(){
		Util.clearHash(floaters, p.getUniqueId());
		Util.clearHash(floatersSnd, p.getUniqueId());
		StatsFile f = new StatsFile(p.getUniqueId().toString(), "tr_playerdata");
		f.load();
		f.getConfiguration().set("skp", skp);
		f.getConfiguration().set("discovered", discovered);
		f.getConfiguration().set("total", total);
		f.getConfiguration().set("syntax", syntax);
		for(Node n : Node.nodes) f.getConfiguration().set(n.getName(), nodes[n.index]);
		f.save();
	}

	public void open(){
		title = "Syntax Tree : " + skp + " Skill Points";
		i = Bukkit.createInventory(null, 54, title);
		updateInv();
		p.openInventory(i);
	}

	public void updateInv(){
		i.clear();

		Main.holidayify(this.i);

		for(Node n : Node.nodes){
			if((n.getPreReq() == null || nodes[n.getPreReq().getIndex()] >= n.getPreReqL()) && n.isCooperative(this) && n.getInvSpace() + (scroll*9) < 54) i.setItem(n.getInvSpace() + (scroll*9), n.getItem());
		}
		for(ItemStack item : i.getContents()){
			if(item != null  && !Main.decor.contains(item.getType())){
				ItemMeta m = item.getItemMeta();
				if(nodes[Node.findNode(item).getIndex()] == 0) m.setDisplayName(m.getDisplayName() + ChatColor.DARK_GRAY + " [Not Activated]");
				else if(nodes[Node.findNode(item).getIndex()] == Node.findNode(item).getMaxL()) m.setDisplayName(m.getDisplayName() + ChatColor.RED + " [Max Level]");
				else m.setDisplayName(m.getDisplayName() + ChatColor.GREEN + " [" + nodes[Node.findNode(item).getIndex()] + "/" + Node.findNode(item).getMaxL() + "]");
				if(this.nodes[Node.findNode(item).getIndex()] > 0 && Node.findNode(item).controls != null){
					ArrayList<String> lore = new ArrayList<String>();
					for(String s : Node.findNode(item).getData()) lore.add(s);
					for(String s : Node.findNode(item).controls) lore.add(s);
					m.setLore(lore);
				}else m.setLore(Node.findNode(item).getData());
				item.setItemMeta(m);
			}
		}

		ItemStack title = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta m = (SkullMeta) title.getItemMeta();
		m.setOwningPlayer(getPlayer());

		String name = "";
		if(total >= 40) name += ChatColor.AQUA;
		if(total >= 80) name += ChatColor.DARK_AQUA;
		if(total >= 120) name += ChatColor.BLUE;
		if(total >= 160) name += ChatColor.DARK_BLUE;
		if(total >= 200) name += ChatColor.LIGHT_PURPLE;
		if(total >= 240) name += ChatColor.DARK_PURPLE;
		name += getPlayer().getName() + ", level " + total;
		name += " Adept ";
		if(syntax == 1) name += "Equilibrial";
		else if(syntax == 2) name += "Aqueous";
		else if(syntax == 3) name += "Detrimental";
		else if(syntax == 4) name += "Oculus";
		else name += "Neophyte";

		m.setDisplayName(name);
		title.setItemMeta(m);
		i.setItem(0, title);
		i.setItem(8, Main.scrollup);
		i.setItem(53, Main.scrolldown);
		i.setItem(45, Help.helpi);
		i.setItem(36, Info.infoi);
	}

	public void tick(){
		for(int i = 0; i < nodes.length; i++){
			if(Node.findNode(i).getEffects() != null && nodes[i] != 0)
				Node.findNode(i).getEffects().tick(this);
		}
	}

	public void onInteract(PlayerInteractEvent evt){
		boolean first = true;
		for(int i = 0; i < nodes.length; i++){
			if(nodes[i] != 0) first = false;
			if(Node.findNode(i).getEffects() != null && nodes[i] != 0)
				Node.findNode(i).getEffects().onInteract(evt, this);
		}
		if(first && evt.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR && p.isSneaking() && evt.getAction() == Action.RIGHT_CLICK_BLOCK) ping(100);
	}

	public void onInteractEntity(PlayerInteractEntityEvent evt){
		for(int i = 0; i < nodes.length; i++){
			if(Node.findNode(i).getEffects() != null && nodes[i] != 0)
				Node.findNode(i).getEffects().onInteractEntity(evt, this);
		}
	}

	public void onMove(PlayerMoveEvent evt){
		for(int i = 0; i < nodes.length; i++){
			if(Node.findNode(i).getEffects() != null && nodes[i] != 0)
				Node.findNode(i).getEffects().onMove(evt, this);
		}
	}

	public void ping(int r){
		if(random.nextInt(r) == 0){
			if(discovered < 10 + (total/4)) discovered++; else{
				discovered = 0;
				total++;
				skp++;
				p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);

				if(total >= 180) p.sendMessage(ChatColor.LIGHT_PURPLE + "The power has consumed you. The aura lives inside you. The world crumples at your will.");
				else if(total >= 160) p.sendMessage(ChatColor.LIGHT_PURPLE + "The aura is a second sense. You lift hills in your sleep. Energy buzzes at your fingertips.");
				else if(total >= 140) p.sendMessage(ChatColor.DARK_BLUE + "The aura brings another sight. You can feel world around you, without having to open your eyes.");
				else if(total >= 120) p.sendMessage(ChatColor.DARK_BLUE + "You can sense power. Power will make you immortal, invincible, unlimited. If you only can grasp it.");
				else if(total >= 100) p.sendMessage(ChatColor.BLUE + "The aura is your ally. There is strength just around the corner.");
				else if(total >= 80) p.sendMessage(ChatColor.BLUE + "You can lift things now. Objects move as you command, with some effort.");
				else if(total >= 60) p.sendMessage(ChatColor.AQUA + "The aura feels a little stronger. Is it that far out of your grasp?");
				else if(total >= 40) p.sendMessage(ChatColor.AQUA + "You can feel a faint aura of energy. Are those particles moving in sync with your thoughts?");
				else if(total >= 20) p.sendMessage(ChatColor.WHITE + "There is a pattern to how objects move around you. Nothing you do seems to influence it.");
				else p.sendMessage(ChatColor.WHITE + "Dust moves across the ground... but is it really the wind?");

				if(total >= 160) p.sendMessage(ChatColor.LIGHT_PURPLE + "A new skill point is available.");
				else if(total >= 120) p.sendMessage(ChatColor.DARK_BLUE + "A new skill point is available.");
				else if(total >= 80) p.sendMessage(ChatColor.BLUE + "A new skill point is available.");
				else if(total >= 40) p.sendMessage(ChatColor.AQUA + "A new skill point is available.");
				else p.sendMessage(ChatColor.WHITE + "A new skill point is available.");
			}
		}
	}
}
