package com.osreboot.tr.main.effects.aqueous;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.osreboot.tr.main.DataTable;
import com.osreboot.tr.main.Main;
import com.osreboot.tr.main.NodeEffects;
import com.osreboot.tr.main.Util;

public class Regulation extends NodeEffects{

	public Regulation(){}

	public static HashMap<String, Integer> active = new HashMap<String, Integer>();
	public static HashMap<String, Integer> buildup = new HashMap<String, Integer>();
	public static HashMap<String, Integer> extinguish = new HashMap<String, Integer>();

	@Override
	public void tick(DataTable d){
		if(!active.containsKey(d.getPlayer().getName())) active.put(d.getPlayer().getName(), 0);
		if(!buildup.containsKey(d.getPlayer().getName())) buildup.put(d.getPlayer().getName(), 0);
		if(!extinguish.containsKey(d.getPlayer().getName())) extinguish.put(d.getPlayer().getName(), 0);
		if(buildup.get(d.getPlayer().getName()) > 0) buildup.put(d.getPlayer().getName(), buildup.get(d.getPlayer().getName()) - 1);
		
		if(d.getPlayer().isSneaking() && active.get(d.getPlayer().getName()) == 0 && Util.isNearLiquid(d.getPlayer(), 2)){
			if(buildup.get(d.getPlayer().getName()) < (40 - d.nodes[12])*4){
				buildup.put(d.getPlayer().getName(), buildup.get(d.getPlayer().getName()) + 4);
			}else{
				active.put(d.getPlayer().getName(), 100 + (d.nodes[12]*10) + new Random().nextInt(d.nodes[12]*100));
				d.getPlayer().playSound(d.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 10, 1);
				d.ping(20);
			}
		}
		
		if(active.get(d.getPlayer().getName()) > 0){
			active.put(d.getPlayer().getName(), active.get(d.getPlayer().getName()) - 1);

			if(active.get(d.getPlayer().getName()) == 1) d.getPlayer().playSound(d.getPlayer().getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 10, 1);

			d.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 20, 1));
			if(new Random().nextInt((31 - d.nodes[12])*2) == 0) d.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20, 1));

			if(d.getPlayer().isSneaking() && !Main.liquids.contains(d.getPlayer().getLocation().getBlock().getType())) extinguish.put(d.getPlayer().getName(), extinguish.get(d.getPlayer().getName()) + 1);
		}
		if(extinguish.get(d.getPlayer().getName()) > 40){
			extinguish.put(d.getPlayer().getName(), 0);
			buildup.put(d.getPlayer().getName(), 0);
			active.put(d.getPlayer().getName(), 0);
			d.getPlayer().playSound(d.getPlayer().getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 10, 1);
			d.ping(30);
		}
	}

	@Override
	public void onMove(PlayerMoveEvent evt, DataTable d){
		Player p = evt.getPlayer();
		if(d.nodes[12] > 0){
			if(evt.getFrom().getX() != evt.getTo().getX() || evt.getFrom().getZ() != evt.getTo().getZ()) extinguish.put(p.getName(), 0);
		}
	}

}
