package com.osreboot.tr.main.effects.equilibrial;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.util.Vector;

import com.osreboot.tr.main.DataTable;
import com.osreboot.tr.main.Main;
import com.osreboot.tr.main.NodeEffects;
import com.osreboot.tr.main.hooks.ProtectionManager;

public class Subtraction extends NodeEffects{

	public Subtraction(){}

	@EventHandler
	public void onDamage(final EntityDamageEvent evt){
		if(evt.getEntity() instanceof Player){
			final Player p = (Player)evt.getEntity();
			final DataTable d = DataTable.findPlayer(p);
			if(d.nodes[11] > 0){
				if(evt.getCause() == DamageCause.FALL){
					if(p.isSneaking()){
						final double initDmg = evt.getDamage();
						double subDmg = 0;
						for(int x = -10; x <= 10; x++){
							for(int y = -2; y <= 2; y++){
								for(int z = -10; z <= 10; z++){
									final Location l = p.getLocation().add(x, y, z).getBlock().getLocation();
									if(new Location(l.getWorld(), l.getX(), l.getY() + 1, l.getZ()).getBlock().getType() == Material.AIR && l.getBlock().getType() != Material.AIR){
										if(Main.canModify(d.getPlayer(), l.getBlock().getLocation())){
											final double distance = l.distance(p.getLocation().getBlock().getLocation());
											if(new Random().nextInt(32 + ((int)distance) - d.nodes[11]) == 0 && distance < 6 && distance > 1 && !Main.blacklist.contains(l.getBlock().getType()) && !Main.liquids.contains(l.getBlock().getType())){
												Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable(){
													public void run(){
														FallingBlock b = p.getWorld().spawnFallingBlock(l.getBlock().getLocation().add(0.5, 0, 0.5), l.getBlock().getBlockData());
														b.setDropItem(false);
														b.setVelocity(new Vector(0, (distance/10), 0));
														l.getBlock().setType(Material.AIR);
														ProtectionManager.abandoned.put(b, d.getPlayer().getUniqueId());
													}
												}, (long)(distance/10));
												if(Coordination.active.containsKey(p.getName()) && Coordination.active.get(p.getName()) > 0) subDmg += initDmg/15; else subDmg += initDmg/30;
											}
										}
									}
								}
							}
						}
						evt.setDamage(evt.getDamage() - subDmg);
						if(evt.getDamage() < 0) evt.setDamage(0D);
						int toPing = (int)Math.abs(40 - initDmg);
						if(toPing == 0) d.ping(toPing); else d.ping(10);
					}
				}
			}
		}
	}

}
