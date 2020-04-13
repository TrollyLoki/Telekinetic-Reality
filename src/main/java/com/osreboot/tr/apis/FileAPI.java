/**
 * Copyright © 2015 Os_Reboot
 *	All rights reserved. This code was created by Os_Reboot and may not 
 *	be distributed or used without the strict permission of Os_Reboot.
 */

package com.osreboot.tr.apis;

import java.io.File;

public class FileAPI {
	public static boolean doesFileExist(String name, String path){
		File folder = new File(path);
		File f = null;
		if(folder.listFiles() != null){
		for(File d : folder.listFiles()){
			if(d.getName().equals(name)){
				f = d;
			}
		}
		}
		if(f == null)
		return false;
		else
		return true;
	}
	
	public static void generateFolder(String name){
		if(!new File(name).exists()) new File(name).mkdirs();
	}
}
