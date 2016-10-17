package io.github.Gamerick1029;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.Bukkit;


public class CommandMoveWord implements CommandExecutor {
	
	public Player player;
	public final int hScreenSize = 50;
	public final int screenHeight = 5;
	public final int xBase = 0;
	public final int yBase = 56;
	public String[] screenData = new String[7];
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		StringBuilder builder = new StringBuilder();
		String input;
		
		for (int i = 0; i < args.length; i++ ){
			builder.append(args[i] + " ");
		}
		
		input = builder.toString().trim();

		
		for (int i = 0; i < 7 ; i++){
			screenData[i] = "";
		}
		
		
		player = (Player) sender;
		
		for (int i = 1; i < 7; i++){
			screenData[i] = "";
		}
		
		parseInput(input);

		
		Location loc = new Location(player.getWorld(), hScreenSize, 56, 2*hScreenSize, 180, 0);
		player.teleport(loc);
		
		updateScreen(Material.WOOL);
		
		updateScreen();


		
				
		return true;
	}
	
	public void parseInput(String input){
		input = input.toUpperCase();
		for (int i = 0; i < input.length(); i++){
			try{
				for (int j = 0; j < 7; j++){
					screenData[j] = screenData[j] + MoveWordsPlugin.fontFormat.get(input.charAt(i))[j] + "0";
				}
				
			} catch (NullPointerException e){
				for (int j = 0; j < 7; j++){
					screenData[j] = screenData[j] + MoveWordsPlugin.fontFormat.get((char)' ')[j] + "0";
				}
			}
		}
		
		
	}
		
	public void updateScreen(){
	
		for (int k = 1; k + screenData[0].length() < xBase + (2*hScreenSize) + 1; k++){
			final int l = k;
			final String[] wordData = screenData.clone();
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MoveWordsPlugin.plugin, new Runnable() {
				public void run() {
					drawWord(l, wordData);
				}
			}, k * 3L);
		}

	}
	
	public void updateScreen(Material buildingBlock) {
		for (int l = 0; l < (2*hScreenSize) + 1; l++) {
			for (int m = 56; m < 57 + screenHeight + 2; m++){
				setBlockAt(l, m, hScreenSize, buildingBlock);
			}
		}
	}
	
	public void drawWord(int xPosStart, String[] wordData) {	
		updateScreen(Material.WOOL);
			for (int j = 0; j < 7; j++){
				for (int i = 0; i < wordData[0].length(); i++){
					if (wordData[j].charAt(i) == (char)'1'){
						setBlockAt(xPosStart + i, yBase + (7-j), hScreenSize, Material.LAPIS_BLOCK);
					}
				}	
			}
	}
	
	public void setBlockAt(int x, int y, int z, Material material){
		player.getWorld().getBlockAt(x, y, z).setType(material);
	}
}


