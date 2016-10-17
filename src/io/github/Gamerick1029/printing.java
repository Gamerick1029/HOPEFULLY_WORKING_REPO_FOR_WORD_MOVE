package io.github.Gamerick1029;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class printing {
	
	public static void print(String text){
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	    console.sendMessage(ChatColor.AQUA+text);
	}

}
