package com.osreboot.tr.main.effects.oculus;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import com.osreboot.tr.main.DataTable;
import com.osreboot.tr.main.Main;
import com.osreboot.tr.main.NodeEffects;
import com.osreboot.tr.main.Util;

public class Divergence extends NodeEffects{

	public Divergence(){}

	public static HashMap<String, Integer> cooldown = new HashMap<String, Integer>();

	@Override
	public void tick(DataTable d){
		if(!cooldown.containsKey(d.getPlayer().getName())) cooldown.put(d.getPlayer().getName(), 0);
		if(cooldown.get(d.getPlayer().getName()) > 0) cooldown.put(d.getPlayer().getName(), cooldown.get(d.getPlayer().getName()) - 1);
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent evt){
		DataTable d = DataTable.findPlayer(evt.getPlayer());
		if(d.nodes[17] > 0){
			if(evt.getItemDrop().getItemStack().getType() == Material.TORCH){
				if(cooldown.get(d.getPlayer().getName()) == 0){
					Block b = Util.getTargetBlock(evt.getPlayer(), d.nodes[17]);
					if(Hallucination.active.containsKey(d.getPlayer().getName()) && Hallucination.active.get(d.getPlayer().getName()) > 0) b = Util.getTargetBlock(evt.getPlayer(), d.nodes[17]*2);
					if(Main.breakables.containsKey(b.getType()) && Main.canModify(d.getPlayer(), b.getLocation())){
						evt.getItemDrop().remove();
						d.getPlayer().playEffect(b.getLocation(), Effect.STEP_SOUND, b.getType());
						Bukkit.getWorld(d.getPlayer().getWorld().getName()).dropItemNaturally(b.getLocation(), new ItemStack(Main.breakables.get(b.getType())));
						b.setType(Material.TORCH);
						cooldown.put(d.getPlayer().getName(), 125 - (d.nodes[17]*4));
						d.ping(40);
					}else{
						evt.setCancelled(true);
						d.getPlayer().playEffect(d.getPlayer().getLocation(), Effect.CLICK1, null);
					}
				}else{
					evt.setCancelled(true);
					d.getPlayer().playEffect(d.getPlayer().getLocation(), Effect.EXTINGUISH, null);
				}
			}
		}
	}

}
