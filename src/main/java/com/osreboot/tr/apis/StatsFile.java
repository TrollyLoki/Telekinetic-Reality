/**
 * Copyright © 2015 Os_Reboot
 *	All rights reserved. This code was created by Os_Reboot and may not 
 *	be distributed or used without the strict permission of Os_Reboot.
 */

package com.osreboot.tr.apis;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class StatsFile {

	private File file;
	private FileConfiguration c;

	public StatsFile(String name, String path){
		if(!new File(new File(".").getAbsolutePath(), name + ".yml").isFile() && !new File(path, name + ".yml").isFile()){
			if(path.equals("rootpath"))
				this.file = new File(new File(".").getAbsolutePath(), name + ".yml");
			else
				this.file = new File(path, name + ".yml");
		}else{
			try{
				for(File f : new File(".").listFiles()){
					if(f.getName().equals(name + ".yml")){
						this.file = f;
					}
				}
				for(File f : new File(path).listFiles()){
					if(f.getName().equals(name + ".yml")){
						this.file = f;
					}
				}
			}catch(Exception e){

			}
		}
	}

	/**
	 *Creates the file if it does not exist, returns false if it does not exist.
	 */
	public boolean generate(){
		if(!this.file.exists()){
			try {
				//this.file.mkdirs();
				this.file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		else return false;
	}

	public boolean doesExist(){
		if(this.file.exists())
			return true;
		else return false;
	}

	/**
	 *Loads the StatsFile configuration. Meant to only be used once per file.
	 */
	public void load(){
		if(this.c != null){
			//System.out.println("[FileAPI] A file configuration for " + this.file.getName() + " has already been loaded! Overwriting...");
		}
		this.c = new YamlConfiguration();
		if(this.file.exists()){
			try {
				this.c.load(this.file);
				//System.out.println("[FileAPI] A file configuration for " + this.file.getName() + " has been successfully loaded!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *Returns the configuration if it has been loaded.
	 */
	public FileConfiguration getConfiguration(){
		if(this.c != null)
			return this.c;
		else{
			//System.out.println("[FileAPI] Tried to get a nonexistent configuration for " + this.file.getName() + "! Returning null.");
			return null;
		}
	}

	public void save(){
		try {
			this.c.save(this.file);
		} catch (IOException e) {
			//System.out.println("[FileAPI] Could not save configuration for " + this.file.getName() + "!");
		}
	}
}