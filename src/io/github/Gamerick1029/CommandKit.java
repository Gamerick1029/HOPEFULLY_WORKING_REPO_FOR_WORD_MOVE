package io.github.Gamerick1029;

import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;

public class CommandKit implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
		
			ItemStack diamond = new ItemStack(Material.DIAMOND);
			ItemStack bricks = new ItemStack(Material.BRICK, 10);
			
			bricks.setAmount(20);
			
			player.getInventory().addItem(bricks, diamond);
		
		}
		
		return true;
	}
	
}
