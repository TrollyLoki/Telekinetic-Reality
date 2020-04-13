package com.osreboot.tr.main;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.osreboot.tr.main.effects.Control;
import com.osreboot.tr.main.effects.Levitation;
import com.osreboot.tr.main.effects.Syntax;
import com.osreboot.tr.main.effects.aqueous.Liberation;
import com.osreboot.tr.main.effects.aqueous.Regulation;
import com.osreboot.tr.main.effects.detrimental.Agitation;
import com.osreboot.tr.main.effects.detrimental.Dissolution;
import com.osreboot.tr.main.effects.equilibrial.Augmentation;
import com.osreboot.tr.main.effects.equilibrial.Coordination;
import com.osreboot.tr.main.effects.equilibrial.Cue;
import com.osreboot.tr.main.effects.equilibrial.Subtraction;
import com.osreboot.tr.main.effects.oculus.Divergence;
import com.osreboot.tr.main.effects.oculus.Hallucination;
import com.osreboot.tr.main.effects.oculus.Reticence;

public class NodeInits {
	
	public static int length = 34;

	public static void init(){
		String[] a = Util.chop(ChatColor.BLUE + "Want to make things fly? Basic levitation is nothing more than a little determination and a lot of free time. " + ChatColor.LIGHT_PURPLE + "Vertical block levitation.", length, "");
		String[] d = Util.chop(ChatColor.DARK_GRAY + "Hold right mouse and shift simultaneously while looking at a block. Hold shift and left click to drop levitated blocks.", length, "");
		new Node(0, "levitation", ChatColor.GREEN + "Levitation", new ItemStack(Material.DIRT), toArray(a), 49, null, 0, new Levitation(), 30, null, toArray(d));

		String[] a2 = Util.chop(ChatColor.BLUE + "\"Use the force, Luke.\" " + ChatColor.LIGHT_PURPLE + "Control horizontal block movement.", length, "");
		String[] d2 = Util.chop(ChatColor.DARK_GRAY + "Hold shift and walk around while you have blocks in the air.", length, "");
		new Node(1, "control", ChatColor.GREEN + "Control", new ItemStack(Material.STONE), toArray(a2), 40, Node.findNode("levitation"), 10, new Control(), 50, null, toArray(d2));

		String[] a3 = Util.chop(ChatColor.BLUE + "There is a beauty in multitasking. " + ChatColor.LIGHT_PURPLE + "Increase the quantity of affected blocks.", length, "");
		new Node(2, "quantity", ChatColor.GREEN + "Quantity", new ItemStack(Material.COBBLESTONE), toArray(a3), 39, Node.findNode("control"), 5, null, 10, null, null);

		String[] a4 = Util.chop(ChatColor.BLUE + "\"A single lie destroys a whole reputation of integrity.\" " + ChatColor.LIGHT_PURPLE + "Decreased chance of dropping blocks.", length, "");
		new Node(3, "continuity", ChatColor.GREEN + "Continuity", new ItemStack(Material.IRON_BLOCK), toArray(a4), 48, Node.findNode("levitation"), 5, null, 30, null, null);

		String[] a5 = Util.chop(ChatColor.BLUE + "\"If you want to live a long life, focus on making contributions.\" " + ChatColor.LIGHT_PURPLE + "Decreased chance of telekinetic self-detriment.", length, "");
		new Node(4, "exemption", ChatColor.GREEN + "Exemption", new ItemStack(Material.GOLD_BLOCK), toArray(a5), 50, Node.findNode("levitation"), 5, null, 30, null, null);
		
		String[] a20 = Util.chop(ChatColor.BLUE + "\"There is no top. There are always furthur heights to reach.\" " + ChatColor.LIGHT_PURPLE + "Decreased proximity requirements for levitated materials.", length, "");
		new Node(19, "reach", ChatColor.GREEN + "Reach", new ItemStack(Material.BIRCH_STAIRS), toArray(a20), 47, Node.findNode("continuity"), 7, null, 30, null, null);

		String[] a6 = Util.chop(ChatColor.BLUE + "\"If there is no struggle, there is no progress.\" " + ChatColor.LIGHT_PURPLE + "Are you ready to make a choice?", length, "");
		String[] s1 = {"indifference"};
		new Node(5, "indifference", ChatColor.AQUA + "Indifference", new ItemStack(Material.COAL), toArray(a6), 31, Node.findNode("control"), 20, null, 1, s1, null);
		
		String[] a23 = Util.chop(ChatColor.BLUE + "\"Clarity affords focus.\" " + ChatColor.LIGHT_PURPLE + "Decreased chance of telekinetic \"side effects\".", length, "");
		new Node(22, "negation", ChatColor.GREEN + "Negation", new ItemStack(Material.MILK_BUCKET), toArray(a23), 42, Node.findNode("exemption"), 5, null, 30, null, null);

		//SYNTAX

		String[] a7 = Util.chop(ChatColor.BLUE + "\"You haven't seen a tree until you've seen it's shadow from the sky.\" " + ChatColor.LIGHT_PURPLE + "Wind. Transport. Mass.", length, "");
		String[] s2 = {"aqueous", "detrimental", "oculus"};
		new Node(6, "equilibrial", ChatColor.AQUA + "Equilibrial", new ItemStack(Material.FEATHER), toArray(a7), 28, Node.findNode("indifference"), 1, new Syntax(), 1, s2, null);

		String[] a8 = Util.chop(ChatColor.BLUE + "\"A drop of water, if it could write out its own history, would explain the universe to us.\" " + ChatColor.LIGHT_PURPLE + "Liquid. Climate. Persistence.", length, "");
		String[] s3 = {"equilibrial", "detrimental", "oculus"};
		new Node(7, "aqueous", ChatColor.AQUA + "Aqueous", new ItemStack(Material.SNOWBALL), toArray(a8), 30, Node.findNode("indifference"), 1, new Syntax(), 1, s3, null);

		String[] a9 = Util.chop(ChatColor.BLUE + "\"Every act of creation is first an act of destruction.\" " + ChatColor.LIGHT_PURPLE + "Havoc. Erosion. Pressure.", length, "");
		String[] s4 = {"equilibrial", "aqueous", "oculus"};
		new Node(8, "detrimental", ChatColor.AQUA + "Detrimental", new ItemStack(Material.GUNPOWDER), toArray(a9), 32, Node.findNode("indifference"), 1, new Syntax(), 1, s4, null);

		String[] a10 = Util.chop(ChatColor.BLUE + "\"Reality is merely an illusion, albeit a very persistent one.\" " + ChatColor.LIGHT_PURPLE + "Light. Disorder. Perception.", length, "");
		String[] s5 = {"equilibrial", "aqueous", "detrimental"};
		new Node(9, "oculus", ChatColor.AQUA + "Oculus", new ItemStack(Material.DIAMOND), toArray(a10), 34, Node.findNode("indifference"), 1, new Syntax(), 1, s5, null);

		//END SYNTAX

		//EQUILIBRIAL

		String[] a11 = Util.chop(ChatColor.BLUE + "Align the aura to suit your needs. " + ChatColor.LIGHT_PURPLE + "Ability to activate the Equilibrial aura.", length, "");
		String[] d3 = Util.chop(ChatColor.DARK_GRAY + "Hold shift and jump in place to activate. Hold shift and stand still to deactivate.", length, "");
		new Node(10, "coordination", ChatColor.GREEN + "Coordination", new ItemStack(Material.GLASS_BOTTLE), toArray(a11), 19, Node.findNode("equilibrial"), 1, new Coordination(), 30, null, toArray(d3));

		String[] a12 = Util.chop(ChatColor.BLUE + "Eating gravel sucks. Becoming a human pancake isn't much fun either. " + ChatColor.LIGHT_PURPLE + " Channel vertical kinetic energy into surrounding terrain.", length, "");
		String[] d4 = Util.chop(ChatColor.DARK_GRAY + "Hold shift when hitting the ground.", length, "");
		new Node(11, "subtraction", ChatColor.GREEN + "Subtraction", new ItemStack(Material.SLIME_BALL), toArray(a12), 20, Node.findNode("coordination"), 5, new Subtraction(), 30, null, toArray(d4));
		
		String[] a21 = Util.chop(ChatColor.BLUE + "\"It does not matter how slowly you go as long as you do not stop.\" " + ChatColor.LIGHT_PURPLE + " Launch yourself upward with a telekinetic shockwave.", length, "");
		String[] d12 = Util.chop(ChatColor.DARK_GRAY + "Left click the ground when \"Coordination\" is active to gain a massive temporary jump boost.", length, "");
		new Node(20, "augmentation", ChatColor.GREEN + "Augmentation", new ItemStack(Material.GOLDEN_BOOTS), toArray(a21), 21, Node.findNode("subtraction"), 15, new Augmentation(), 40, null, toArray(d12));
		//TODO aug ping
		
		String[] a22 = Util.chop(ChatColor.BLUE + "\"Either I will find a way, or I will make one.\" " + ChatColor.LIGHT_PURPLE + " Compress the surrounding air into a method of propulsion.", length, "");
		String[] d13 = Util.chop(ChatColor.DARK_GRAY + "Left click in the air when \"Coordination\" is active to charge. Left click again to be launched in the facing direction.", length, "");
		new Node(21, "cue", ChatColor.GREEN + "Cue", new ItemStack(Material.DISPENSER), toArray(a22), 12, Node.findNode("augmentation"), 5, new Cue(), 25, null, toArray(d13));
		//TODO cue ping
		//TODO cue particles mimick ground block
		
		//END EQUILIBRIAL

		//AQUEOUS

		String[] a13 = Util.chop(ChatColor.BLUE + "Bend the aura to form fit your desires. " + ChatColor.LIGHT_PURPLE + "Ability to activate the Aqueous aura.", length, "");
		String[] d5 = Util.chop(ChatColor.DARK_GRAY + "Hold shift and stand still next to a liquid to activate. Hold shift and stand still on dry land to deactivate.", length, "");
		new Node(12, "regulation", ChatColor.GREEN + "Regulation", new ItemStack(Material.WATER_BUCKET), toArray(a13), 21, Node.findNode("aqueous"), 1, new Regulation(), 30, null, toArray(d5));
		
		String[] a14 = Util.chop(ChatColor.BLUE + "\"We all live in a yellow submarine!\" " + ChatColor.LIGHT_PURPLE + "Forcibly displace liquids to act as a method of propulsion when submerged.", length, "");
		String[] d6 = Util.chop(ChatColor.DARK_GRAY + "Left click while in a liquid.", length, "");
		new Node(13, "liberation", ChatColor.GREEN + "Liberation", new ItemStack(Material.SUGAR), toArray(a14), 20, Node.findNode("regulation"), 5, new Liberation(), 50, null, toArray(d6));

		//END AQUEOUS
		
		//DETRIMENTAL
		
		String[] a15 = Util.chop(ChatColor.BLUE + "Erode the aura into a form that meets your requirements. " + ChatColor.LIGHT_PURPLE + "Ability to activate the Detrimental aura.", length, "");
		String[] d7 = Util.chop(ChatColor.DARK_GRAY + "Hold shift and walk around to activate. Hold shift and stand still to deactivate.", length, "");
		new Node(14, "agitation", ChatColor.GREEN + "Agitation", new ItemStack(Material.GRAVEL), toArray(a15), 23, Node.findNode("detrimental"), 1, new Agitation(), 30, null, toArray(d7));
		
		String[] a16 = Util.chop(ChatColor.BLUE + "Small rocks came from larger rocks, larger rocks came from still larger ones and those rocks came from planets. " + ChatColor.LIGHT_PURPLE + "Basic material area destruction.", length, "");
		String[] d8 = Util.chop(ChatColor.DARK_GRAY + "Hold shift and left click a basic material.", length, "");
		new Node(15, "dissolution", ChatColor.GREEN + "Dissolution", new ItemStack(Material.SAND), toArray(a16), 24, Node.findNode("agitation"), 5, new Dissolution(), 30, null, toArray(d8));
		
		//END DETRIMENTAL
		
		//OCULUS
		
		String[] a17 = Util.chop(ChatColor.BLUE + "Transition the aura into an appearance that meets your demands. " + ChatColor.LIGHT_PURPLE + "Ability to activate Oculus aura.", length, "");
		String[] d9 = Util.chop(ChatColor.DARK_GRAY + "Hold shift and move mouse to activate. Hold shift and keep mouse still to deactivate.", length, "");
		new Node(16, "hallucination", ChatColor.GREEN + "Hallucination", new ItemStack(Material.ENDER_EYE), toArray(a17), 25, Node.findNode("oculus"), 1, new Hallucination(), 30, null, toArray(d9));
		
		String[] a18 = Util.chop(ChatColor.BLUE + "\"It matters not how fast light may travel, darkness shall always be there awaiting its arrival.\" " + ChatColor.LIGHT_PURPLE + "Implant a torch into a basic material from a distance.", length, "");
		String[] d10 = Util.chop(ChatColor.DARK_GRAY + "Press Q while holding a torch and looking at a basic material.", length, "");
		new Node(17, "divergence", ChatColor.GREEN + "Divergence", new ItemStack(Material.GLOWSTONE_DUST), toArray(a18), 24, Node.findNode("hallucination"), 5, new Divergence(), 30, null, toArray(d10));
		
		String[] a19 = Util.chop(ChatColor.BLUE + "You don't need light, and neither does anybody else! " + ChatColor.LIGHT_PURPLE + "Extinguish all light sources in a specified radius.", length, "");
		String[] d11 = Util.chop(ChatColor.DARK_GRAY + "Hold shift and left click on a block.", length, "");
		new Node(18, "reticence", ChatColor.GREEN + "Reticence", new ItemStack(Material.INK_SAC), toArray(a19), 26, Node.findNode("hallucination"), 15, new Reticence(), 30, null, toArray(d11));
		
		//END OCULUS
	}

	public static ArrayList<String> toArray(String[] s){
		ArrayList<String> sa = new ArrayList<String>();
		for(int i = 0; i < s.length; i++) sa.add(s[i]);
		return sa;
	}

}
