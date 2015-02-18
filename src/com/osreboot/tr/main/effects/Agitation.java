package com.osreboot.tr.main.effects;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.osreboot.tr.main.DataTable;
import com.osreboot.tr.main.NodeEffects;
import com.osreboot.tr.main.Util;

public class Agitation extends NodeEffects{

	public Agitation(){}

	public static HashMap<String, Integer> active = new HashMap<String, Integer>();
	public static HashMap<String, Integer> buildup = new HashMap<String, Integer>();
	public static HashMap<String, Integer> extinguish = new HashMap<String, Integer>();

	@Override
	public void tick(DataTable d){
		if(!active.containsKey(d.getPlayer().getName())) active.put(d.getPlayer().getName(), 0);
		if(!buildup.containsKey(d.getPlayer().getName())) buildup.put(d.getPlayer().getName(), 0);
		if(!extinguish.containsKey(d.getPlayer().getName())) extinguish.put(d.getPlayer().getName(), 0);
		if(buildup.get(d.getPlayer().getName()) > 0) buildup.put(d.getPlayer().getName(), buildup.get(d.getPlayer().getName()) - 1);
		if(active.get(d.getPlayer().getName()) > 0){
			active.put(d.getPlayer().getName(), active.get(d.getPlayer().getName()) - 1);

			if(active.get(d.getPlayer().getName()) == 1) d.getPlayer().playSound(d.getPlayer().getLocation(), Sound.FIZZ, 10, 1);

			d.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20, 1), true);
			if(new Random().nextInt((31 - d.nodes[14])*2) == 0) d.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10, 1), true);

			if(d.getPlayer().isSneaking()) extinguish.put(d.getPlayer().getName(), extinguish.get(d.getPlayer().getName()) + 1);
		}
		if(extinguish.get(d.getPlayer().getName()) > 40){
			extinguish.put(d.getPlayer().getName(), 0);
			buildup.put(d.getPlayer().getName(), 0);
			active.put(d.getPlayer().getName(), 0);
			d.getPlayer().playSound(d.getPlayer().getLocation(), Sound.FIZZ, 10, 1);
			d.ping(30);
		}
	}

	@Override
	public void onMove(PlayerMoveEvent evt, DataTable d){
		Player p = evt.getPlayer();
		if(d.nodes[14] > 0){
			if(evt.getFrom().getY() == evt.getTo().getY() && evt.getFrom().getX() != evt.getTo().getX() || evt.getFrom().getZ() != evt.getTo().getZ()){
				if(p.isSneaking() && active.containsKey(d.getPlayer().getName()) && active.get(p.getName()) == 0 && Util.getPlayerUUIDS(DataTable.floaters, d.getPlayer().getName()).size() == 0){
					if(buildup.get(p.getName()) < (40 - d.nodes[14])*4){
						buildup.put(p.getName(), buildup.get(p.getName()) + 4);
					}else{
						active.put(p.getName(), 100 + (d.nodes[14]*10) + new Random().nextInt(d.nodes[14]*100));
						p.playSound(p.getLocation(), Sound.NOTE_PIANO, 10, 1);
						d.ping(20);
					}
				}
			}
			if(evt.getFrom().getX() != evt.getTo().getX() || evt.getFrom().getZ() != evt.getTo().getZ() || evt.getFrom().getY() != evt.getTo().getY()){
				extinguish.put(p.getName(), 0);
			}
		}
	}

}
