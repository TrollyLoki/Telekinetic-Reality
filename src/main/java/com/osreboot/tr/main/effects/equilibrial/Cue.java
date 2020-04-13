package com.osreboot.tr.main.effects.equilibrial;

import java.util.HashMap;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.osreboot.tr.main.DataTable;
import com.osreboot.tr.main.NodeEffects;

public class Cue extends NodeEffects{

	public Cue(){}

	public static HashMap<String, Integer> buildup = new HashMap<String, Integer>();

	@Override
	public void tick(DataTable d){
		if(!buildup.containsKey(d.getPlayer().getName())) buildup.put(d.getPlayer().getName(), 0);
		if(buildup.get(d.getPlayer().getName()) > 0){
			buildup.put(d.getPlayer().getName(), buildup.get(d.getPlayer().getName()) + 1);
			if(buildup.get(d.getPlayer().getName()) % 2 == 0 && buildup.get(d.getPlayer().getName()) <= 100) d.getPlayer().playEffect(d.getPlayer().getLocation(), Effect.STEP_SOUND, Material.DIRT);
			if(buildup.get(d.getPlayer().getName()) > 400){
				buildup.put(d.getPlayer().getName(), 0);
				d.getPlayer().playEffect(d.getPlayer().getLocation(), Effect.STEP_SOUND, Material.STONE);//TODO material other than stone, based on ground beneath player?
			}
		}
	}

	@Override
	public void onInteract(PlayerInteractEvent evt, final DataTable d){
		if(d.nodes[21] > 0){
			if(Coordination.active.containsKey(d.getPlayer().getName()) && Coordination.active.get(d.getPlayer().getName()) > 0){
				if(evt.getAction() == Action.LEFT_CLICK_AIR && !d.getPlayer().isSneaking()){
					if(buildup.get(d.getPlayer().getName()) >= 100){

						d.getPlayer().playEffect(d.getPlayer().getLocation(), Effect.STEP_SOUND, Material.GLASS);

						d.getPlayer().setVelocity(d.getPlayer().getLocation().getDirection().multiply(((float)(d.nodes[21])/20) + 1));

						buildup.put(d.getPlayer().getName(), 0);
					}else if(buildup.get(d.getPlayer().getName()) == 0) buildup.put(d.getPlayer().getName(), 1);
				}
			}
		}
	}

}
