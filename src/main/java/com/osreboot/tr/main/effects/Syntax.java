package com.osreboot.tr.main.effects;

import com.osreboot.tr.main.DataTable;
import com.osreboot.tr.main.NodeEffects;

public class Syntax extends NodeEffects{
	
	public Syntax(){}
	
	@Override
	public void tick(DataTable d){
		if(d.nodes[6] != 0 && d.getSyntax() != 1) d.setSyntax(1);
		if(d.nodes[7] != 0 && d.getSyntax() != 2) d.setSyntax(2);
		if(d.nodes[8] != 0 && d.getSyntax() != 3) d.setSyntax(3);
		if(d.nodes[9] != 0 && d.getSyntax() != 4) d.setSyntax(4);
	}

}
