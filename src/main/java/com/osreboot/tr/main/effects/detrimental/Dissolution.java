package com.osreboot.tr.main.effects.detrimental;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.osreboot.tr.main.DataTable;
import com.osreboot.tr.main.Main;
import com.osreboot.tr.main.NodeEffects;

public class Dissolution extends NodeEffects{

	public Dissolution(){}

	public static HashMap<String, Integer> cooldown = new HashMap<String, Integer>();

	@Override
	public void tick(DataTable d){
		if(!cooldown.containsKey(d.getPlayer().getName())) cooldown.put(d.getPlayer().getName(), 0);
		if(cooldown.get(d.getPlayer().getName()) > 0) cooldown.put(d.getPlayer().getName(), cooldown.get(d.getPlayer().getName()) - 1);
	}

	@Override
	public void onInteract(PlayerInteractEvent evt, DataTable d){
		if(d.nodes[15] > 0){
			if(Agitation.active.containsKey(d.getPlayer().getName()) && Agitation.active.get(d.getPlayer().getName()) > 0){
				if(cooldown.get(d.getPlayer().getName()) == 0){
					if(d.getPlayer().isSneaking() && evt.getAction() == Action.LEFT_CLICK_BLOCK && Main.breakables.containsKey(evt.getClickedBlock().getType())  && d.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR){
						if(new Random().nextInt(18 - (d.nodes[15]/2)) == 0){

							for(int x = -1; x <= 1; x++){
								for(int y = -1; y <= 1; y++){
									for(int z = -1; z <= 1; z++){
										Location l = evt.getClickedBlock().getLocation().add(x, y, z);
										if(Main.canModify(d.getPlayer(), l.getBlock().getLocation())){
											if(Main.breakables.containsKey(l.getBlock().getType()) && new Random().nextInt(d.nodes[15]*2) != 0){
												ItemStack i = new ItemStack(Main.breakables.get(l.getBlock().getType()));
												d.getPlayer().playEffect(l, Effect.STEP_SOUND, l.getBlock().getType());
												Bukkit.getWorld(d.getPlayer().getWorld().getName()).dropItemNaturally(l, i);
												l.getBlock().setType(Material.AIR);
											}
										}
									}
								}
							}

							d.ping(40);
							cooldown.put(d.getPlayer().getName(), 120 - (d.nodes[15]*3));
						}else{
							d.getPlayer().playEffect(evt.getClickedBlock().getLocation(), Effect.STEP_SOUND, evt.getClickedBlock().getType());
							d.ping(250);
						}
					}
				}else{
					d.getPlayer().playEffect(d.getPlayer().getLocation(), Effect.EXTINGUISH, null);
				}
			}
		}
	}

}
