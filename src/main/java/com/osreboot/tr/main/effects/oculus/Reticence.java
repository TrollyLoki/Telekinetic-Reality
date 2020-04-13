package com.osreboot.tr.main.effects.oculus;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.osreboot.tr.main.DataTable;
import com.osreboot.tr.main.Main;
import com.osreboot.tr.main.NodeEffects;
import com.osreboot.tr.main.Util;

public class Reticence extends NodeEffects{

	public Reticence(){}

	public static HashMap<String, Integer> cooldown = new HashMap<String, Integer>();

	@Override
	public void tick(DataTable d){
		if(!cooldown.containsKey(d.getPlayer().getName())) cooldown.put(d.getPlayer().getName(), 0);
		if(cooldown.get(d.getPlayer().getName()) > 0) cooldown.put(d.getPlayer().getName(), cooldown.get(d.getPlayer().getName()) - 1);
	}

	@Override
	public void onInteract(PlayerInteractEvent evt, final DataTable d){
		if(d.nodes[18] > 0){
			if(Hallucination.active.containsKey(d.getPlayer().getName()) && Hallucination.active.get(d.getPlayer().getName()) > 0){
				if(evt.getAction() == Action.LEFT_CLICK_BLOCK && evt.getPlayer().isSneaking() && evt.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR){
					if(cooldown.get(d.getPlayer().getName()) == 0){
						d.getPlayer().playSound(d.getPlayer().getLocation(), Sound.AMBIENT_CAVE, 10, 1);
						Location l = Util.getTargetBlock(evt.getPlayer(), 100).getLocation();
						int range = 10 + (d.nodes[18]*2);
						for(int x = -range; x <= range; x++){
							for(int y = -range; y <= range; y++){
								for(int z = -range; z <= range; z++){
									final Location l2 = new Location(l.getWorld(), l.getX() + x, l.getY() + y, l.getZ() + z);
									if(Main.lights.containsKey(l2.getBlock().getType())){
										Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable(){
											public void run(){
												d.getPlayer().playEffect(l2, Effect.STEP_SOUND, l2.getBlock().getType());
												if(Main.canModify(d.getPlayer(), l2.getBlock().getLocation())) l2.getBlock().breakNaturally();
											}
										}, (long)(l2.distance(l)*2));
									}
								}
							}
						}
						cooldown.put(d.getPlayer().getName(), 1400 - (d.nodes[18]*40));
						d.ping(20);
					}else{
						d.getPlayer().playEffect(d.getPlayer().getLocation(), Effect.EXTINGUISH, null);
						d.ping(250);
					}
				}
			}
		}
	}

}
