package com.osreboot.tr.main;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Node {

	public static ArrayList<Node> nodes = new ArrayList<Node>();
	
	public static Node findNode(ItemStack i){
		for(Node n : nodes) if(n.getItem().getType() == i.getType()) return n;
		return null;
	}
	
	public static Node findNode(String s){
		for(Node n : nodes) if(n.getName() == s) return n;
		return null;
	}
	
	public static Node findNode(int i){
		for(Node n : nodes) if(n.getIndex() == i) return n;
		return null;
	}

	protected int index, invSpace, prereqL, maxl;
	protected String name, displayName;
	protected ArrayList<String> data, controls;
	protected ItemStack item;
	protected Node prereq;
	protected NodeEffects effects;
	protected String[] conflicts;

	public Node(int index, String name, String displayName, ItemStack item, ArrayList<String> data, int invSpace, Node prereq, int prereqL, NodeEffects effects, int maxl, String[] conflicts, ArrayList<String> controls){
		this.index = index;
		this.name = name;
		this.displayName = displayName;
		this.item = item;
		this.data = data;
		this.invSpace = invSpace;
		this.prereq = prereq;
		this.prereqL = prereqL;
		this.effects = effects;
		this.maxl = maxl;
		this.conflicts = conflicts;
		this.controls = controls;
		nodes.add(this);
		this.syncData();
	}

	public void syncData(){
		ItemMeta m = this.item.getItemMeta();
		m.setDisplayName(this.displayName);
		m.setLore(this.data);
		this.item.setItemMeta(m);
	}
	
	public boolean isCooperative(DataTable d){
		if(this.conflicts != null){
			for(int i = 0; i < this.conflicts.length; i++) if(d.nodes[Node.findNode(this.conflicts[i]).index] > 0) return false;
			return true;
		}else return true;
	}
	
	public int getIndex(){
		return this.index;
	}

	public String getName(){
		return this.name;
	}
	
	public String getDisplayName(){
		return this.displayName;
	}

	public ItemStack getItem(){
		return this.item;
	}

	public ArrayList<String> getData(){
		return this.data;
	}

	public int getInvSpace(){
		return this.invSpace;
	}
	
	public Node getPreReq(){
		return this.prereq;
	}
	
	public int getPreReqL(){
		return this.prereqL;
	}
	
	public NodeEffects getEffects(){
		return this.effects;
	}
	
	public int getMaxL(){
		return this.maxl;
	}
}
