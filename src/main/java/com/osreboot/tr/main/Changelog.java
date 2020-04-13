package com.osreboot.tr.main;

import org.bukkit.ChatColor;

public class Changelog {

	private static String[] log = {"0.3.0.0 [Addition] Oculus: Hallucination, Oculus: Divergence.",
		"0.3.1.0 [Utility] (Added changelog command).",
		"0.3.2.0 [Addition] Oculus: Reticence.",
		"0.3.3.0 [Utility] (Added control descriptions to purchased nodes).",
		"0.3.3.1 [Fix] (Repaired the descriptions for Oculus: Divergence and Oculus: Reticence).",
		"0.3.4.0 [Utility] (Added a help screen to the skill tree).",
		"0.3.4.1 [Fix] (Fixed CME with saving a data table).",
		"0.3.4.2 [Utility] (Added player title to skill tree).",
		"0.3.4.3 [Balance] (Re-balanced skill point messages), (Re-balanced skill point earning rate).",
		"0.3.4.4 [Balance] (Oculus: Divergence no longer requires telekinetic mode activation).",
		"0.3.4.5 [Fix] (Fixed /reload causing errors).",
		"0.3.4.6 [Fix] (Clarified the description for the node \"Levitation\").",
		"0.3.4.7 [Fix] (Fixed CME on PlayerDeathEvent).",
		"0.4.0.0 [Fix] (Fixed blocks despawning after a while of being levitated), (Blocks now fall after reaching world height limit), (Removed Material.BUCKET from light sources list), (Added beacons, beds, command blocks, doors, signs and item frames to the blacklist). [Addition] (Added \"Reach\"), (Added \"Developer Information\" menu).",
		"0.4.1.0 [Addition] (Complete overhaul of old node text system for new automatic newline function). [Fix] (New formatting system now inserts newline before extraneous words instead of after), (Added left click description to \"Levitation\").",
		"0.4.2.0 [Addition] (Added support for holiday themes). [Fix] (Fixed the description for \"Levitation\" again...), (Converted help menu to newline system), (Fixed ArrayIndexOutOfBoundsException in the help menu).",
		"0.4.3.0 [Fix] (Added wall signs to the blacklist), (Negative effects now actually stack with multiple block levitation), (Changed description of \"Exemption\"). [Addition] (Split the potion effects of \"Exemption\" into the new node \"Negation\").",
		"1.0.0.0 [Addition] (Added fist attempt at an external protection hook: WorldGuard), (Added test nodes \"Augmentation\" and \"Cue\"). [Fix] (Various optimizations), (Fixed errors when the server reloads and a player is still in the skill tree menu), (Fixed error with \"Subtraction\" node), (Added empty player hand requirement to various nodes).",
		"1.0.1.0 [Addition] (Added PreciousStones protection hook). [Fix] (Added empty player hand requirement to \"Liberation\"), (Fixed errors with the /reload command for every node).",
		"1.0.2.0 [Addition] (Added various methods to prepare for addon support). [Fix] (Fixed various warnings, removed unused imports), (Fixed a memory leak).",
		"1.0.3.0 [Addition] (Added descriptions to \"Augmentation\" and \"Cue\"). [Fix] (Cleaned up DataTable code).",
		"1.1.0.0 [Addition] (Updated methods to CB 1.8), [Fix] (Migrated from saving by player names to UUIDs), (Cleaned up code), (Added armor stands, banners and barrier blocks to the blacklist)."};
	
	private static String news2 = "1.0.0.0: Welp, this is it. Full version is out. There is going to be some chaos, let the detrimentals run wild! ;)";
	@SuppressWarnings("unused")
	private static String news1 = "0.4.2.0: Tis the season... bringing you some pretty decor to spice up your Halloween! " + ChatColor.GOLD + " ((`))";
	@SuppressWarnings("unused")
	private static String news = "0.4.0.0: Yay! Players can now view the changelog AND plugin version with a single command! And my personal information! Wait a second...";
	
	public static String[] getLog(){
		return log;
	}
	
	public static String getNews(){
		return news2;
	}
	
	//TODOS
	/*
	 * - Make help menu more clear (seriously)
	 * - Add aqueous nodes
	 * 
	 */
}
