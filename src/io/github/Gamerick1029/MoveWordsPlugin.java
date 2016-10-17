package io.github.Gamerick1029;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class MoveWordsPlugin extends JavaPlugin {

	
	public static HashMap<Character, String[]> fontFormat = new HashMap<Character, String[]>();
	public static Plugin plugin;
	
	public void main(String[] args){
		
	}
	
	
	@Override
	public void onEnable() {
		plugin = this;
		this.getCommand("kit").setExecutor(new CommandKit());
		this.getCommand("moveWord").setExecutor(new CommandMoveWord());
		
		try {
			parseFontInImage();
		} catch (IOException e) {
			printing.print(e.toString());
		}
		

	}
	
	@Override
	public void onDisable(){
		
	}
	
	public void parseFontInImage() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResource("/ASCII_TEXT.png"));
		int[][] pixels = new int[image.getWidth()][];
	
		for (int x = 0; x < image.getWidth(); x++) {
		    pixels[x] = new int[image.getHeight()];

		    for (int y = 0; y < image.getHeight(); y++) {
		        pixels[x][y] = (image.getRGB(x, y) == 0xFFFFFFFF ? 0 : 1);
		    }
		}
		//All of the above taken from http://stackoverflow.com/questions/5925426/java-how-would-i-load-a-black-and-white-image-into-binary
		
		String[] tempChar = new String[7];
		String line = "";
		
		for (int i = 0; i < 26; i++){
			
			for (int j = 0; j < 7; j++){
				line = "";
				for (int k = 0; k < 5; k++){
					line = line + pixels[(6*i) + k][j];
				}
				tempChar[j] = line;
			}
			fontFormat.put(getAlphabetFromNumber(i + 1), tempChar.clone());
		}
		
		for (int i = 0; i < 7; i++){
			tempChar[i] = "00000";
		}
		fontFormat.put((char)' ', tempChar);
		
		tempChar[0] = "01110";
		tempChar[1] = "10001";
		tempChar[2] = "00010";
		tempChar[3] = "00100";
		tempChar[4] = "00100";
		tempChar[5] = "00000";
		tempChar[6] = "00100";
		
		fontFormat.put((char)'?', tempChar);
	}
	
	public char getAlphabetFromNumber(int number){
		return number > 0 && number < 27 ? (char)(number + 'A' - 1) : null;
		//Taken from http://stackoverflow.com/questions/10813154/converting-number-to-letter
	}
	
		
	
}
