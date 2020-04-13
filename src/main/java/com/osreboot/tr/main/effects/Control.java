package com.osreboot.tr.main.effects;

import java.util.ArrayList;

import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import com.osreboot.tr.main.DataTable;
import com.osreboot.tr.main.NodeEffects;
import com.osreboot.tr.main.Util;

public class Control extends NodeEffects{

	public Control(){}

	public static ArrayList<String> crouching = new ArrayList<String>();

	@Override
	public void onMove(PlayerMoveEvent evt, DataTable d){
		Player p = evt.getPlayer();
		if(!crouching.contains(d.getPlayer().getName())){
			if(p.isSneaking()){
				for(FallingBlock e : Util.getPlayerUUIDS(DataTable.floaters, evt.getPlayer().getUniqueId())){
					e.setVelocity(new Vector( 
							e.getVelocity().getX() + (evt.getTo().getX() - evt.getFrom().getX())*(1 + (d.nodes[1]/30)), 
							e.getVelocity().getY(),
							e.getVelocity().getZ() + (evt.getTo().getZ() - evt.getFrom().getZ())*(1 + (d.nodes[1]/30))));
				}
				if(Util.getPlayerUUIDS(DataTable.floaters, d.getPlayer().getUniqueId()).size() > 0) d.ping(200);
			}else if(p.isSprinting()){
				for(FallingBlock e : Util.getPlayerUUIDS(DataTable.floaters, evt.getPlayer().getUniqueId())){
					e.setVelocity(new Vector( 
							e.getVelocity().getX() + (evt.getTo().getX() - evt.getFrom().getX())*(1 + (d.nodes[1]/10)), 
							e.getVelocity().getY(),
							e.getVelocity().getZ() + (evt.getTo().getZ() - evt.getFrom().getZ())*(1 + (d.nodes[1]/10))));
				}
				if(Util.getPlayerUUIDS(DataTable.floaters, evt.getPlayer().getUniqueId()).size() > 0) d.ping(200);
			}
		}
	}

	@Override
	public void tick(DataTable d){
		if(!crouching.contains(d.getPlayer().getName()) && d.getPlayer().isSneaking() && Util.getPlayerUUIDS(DataTable.floaters, d.getPlayer().getUniqueId()).size() == 0) crouching.add(d.getPlayer().getName());
		if(crouching.contains(d.getPlayer().getName()) && !d.getPlayer().isSneaking()) crouching.remove(d.getPlayer().getName());
		for(FallingBlock e : Util.getPlayerUUIDS(DataTable.floaters, d.getPlayer().getUniqueId())){
			e.setVelocity(new Vector(e.getVelocity().getX()*0.85, e.getVelocity().getY(), e.getVelocity().getZ()*0.85));
		}
	}

}
