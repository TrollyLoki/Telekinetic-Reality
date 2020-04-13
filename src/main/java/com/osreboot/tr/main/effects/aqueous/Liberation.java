package com.osreboot.tr.main.effects.aqueous;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import com.osreboot.tr.main.DataTable;
import com.osreboot.tr.main.Main;
import com.osreboot.tr.main.NodeEffects;

public class Liberation extends NodeEffects{

	public Liberation(){}

	public static HashMap<String, Integer> cooldown = new HashMap<String, Integer>();

	@Override
	public void tick(DataTable d){
		if(!cooldown.containsKey(d.getPlayer().getName())) cooldown.put(d.getPlayer().getName(), 0);
		if(cooldown.get(d.getPlayer().getName()) > 0) cooldown.put(d.getPlayer().getName(), cooldown.get(d.getPlayer().getName()) - 1);
	}

	@Override
	public void onInteract(PlayerInteractEvent evt, DataTable d){
		if(d.nodes[13] > 0){
			if(cooldown.containsKey(d.getPlayer().getName()) && cooldown.get(d.getPlayer().getName()) == 0){
				if(evt.getAction() == Action.LEFT_CLICK_AIR || evt.getAction() == Action.LEFT_CLICK_BLOCK){
					if(Regulation.active.get(d.getPlayer().getName()) > 0 && d.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR){
						if(Main.liquids.contains(d.getPlayer().getLocation().getBlock().getType())){
							double multi = 1 + (d.nodes[13]/60);
							Vector v = d.getPlayer().getVelocity();
							v.add(d.getPlayer().getLocation().getDirection().multiply(multi));
							d.getPlayer().setVelocity(v);
							d.ping(75);
							cooldown.put(d.getPlayer().getName(), 32 - (int)(((d.nodes[13]*2)/3.333333)));
						}
					}
				}
			}
		}
	}

}
