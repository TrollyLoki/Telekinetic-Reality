package com.osreboot.tr.main.effects.equilibrial;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.osreboot.tr.main.DataTable;
import com.osreboot.tr.main.NodeEffects;

public class Augmentation extends NodeEffects{

	public Augmentation(){}

	@Override
	public void onInteract(PlayerInteractEvent evt, final DataTable d){
		if(d.nodes[20] > 0){
			if(Coordination.active.containsKey(d.getPlayer().getName()) && Coordination.active.get(d.getPlayer().getName()) > 0){
				if(effectIsCooledDown(d.getPlayer())){
					if(evt.getAction() == Action.LEFT_CLICK_BLOCK && !d.getPlayer().isSneaking() && d.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR){
						d.getPlayer().removePotionEffect(PotionEffectType.JUMP);
						d.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, (d.nodes[20]/5) + 2));

						for(int x = -10; x <= 10; x++) for(int y = -1; y <= 1; y++) for(int z = -10; z <= 10; z++){
							Location l = d.getPlayer().getLocation().getBlock().getLocation().add(x, y, z);
							if(l.distance(d.getPlayer().getLocation()) < (d.nodes[20]/15) + 2) if(l.getBlock().getType() != Material.AIR) d.getPlayer().playEffect(l, Effect.STEP_SOUND, l.getBlock().getType());
						}

						effectSetCooldown(d.getPlayer(), 260 - (d.nodes[20] * 5));
					}
				}else{
					d.getPlayer().playEffect(d.getPlayer().getLocation(), Effect.EXTINGUISH, null);
				}
			}
		}
	}

}
