package net.missingtoe;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakEvent.RemoveCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.bukkit.util.io.BukkitObjectInputStream;

import com.sun.tools.sjavac.comp.dependencies.PublicApiCollector;

import io.netty.util.concurrent.ThreadProperties;
import jdk.internal.jshell.tool.resources.l10n;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.level.PlayerChunk.e;


public class Main extends JavaPlugin implements Listener{
	public boolean isactive = false;
	public Player cheesmoon;
	public Plugin plugin = this;
	ArrayList<Player> theworlds = new ArrayList<Player>();
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);


	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
				if(command.getName().equalsIgnoreCase("cheesemoon")) { 
					theworlds.add((Player) sender);
					sender.sendMessage(ChatColor.GREEN + "You are now CHEESE MOON");
					//getentity(cheesmoon);
					
			}
		
		return false;
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void sneak(PlayerToggleSneakEvent event) {
		if(theworlds.contains(event.getPlayer())) {

			int task2id = 0;
		ArrayList<Vector> velocity = new ArrayList<Vector>();
		ArrayList<Entity> entities = (ArrayList<Entity>) event.getPlayer().getNearbyEntities(50, 50, 50);
		freeze(entities, event.getPlayer(), event);
		}
			

			
		}
	public int freeze(ArrayList<Entity> entities, Player player, PlayerToggleSneakEvent event) {
		int taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				if(event.isSneaking()) {
				for (int k = 0;k<entities.size();k++) {

					entities.get(k).setVelocity(entities.get(k).getVelocity().setY(0).setX(0).setZ(0));
					entities.get(k).setGravity(false);
						if(entities.get(k).hasGravity() == false) {		
							entities.get(k).setGravity(true);
							//entities.get(k).setVelocity(velocity.get(k));
							if(entities.get(k).getType() == EntityType.PLAYER) {
								Player p = (Player) entities.get(k);
								p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(1);
							}
						}
		
					}
				}

			}					
	}, 0, 0);

		
		int task1id = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			public void run() {
				if(event.isSneaking() == false) {

						Bukkit.getServer().getScheduler().cancelTask(taskid);					
				for (int k = 0;k<entities.size();k++) {
					

						entities.get(k).setGravity(true);
						//entities.get(k).setVelocity(velocity.get(k));
						if(entities.get(k).getType() == EntityType.PLAYER) {
							Player p = (Player) entities.get(k);
							p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(1);
						}
					}
				}
			
			}

		}, 0, 0);
		if(event.isSneaking()) {
		player.sendMessage("yes " + task1id);
		Bukkit.getServer().getScheduler().cancelTask(task1id);
		}
		return taskid;
	}
//	public void getentity(Player player) {
//		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
//		    public void run() {
//		    	ArrayList<Entity> entities = (ArrayList<Entity>) player.getNearbyEntities(50, 50, 50);
//		    	//ArrayList<Vector> velocity = new ArrayList<Vector>();
//		    	for (int k = 0;k<entities.size();k++) {
//			    	if(isactive == true) {
//	        				entities.get(k).setVelocity(entities.get(k).getVelocity().setY(0).setX(0).setZ(0));
//	        				entities.get(k).setGravity(false);
//	        				//if (entities.get(k).getType() == EntityType.PLAYER){
//			        		//	entities.get(k).setVelocity(entities.get(k).getVelocity().setY(0).setX(0).setZ(0));
//			        		//	entities.get(k).setGravity(false);
//			    			//	Player p = (Player) entities.get(k);
//			    			//	p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
////
//			    			//}
//			    			
//
//		        				
//			    		
//		        	}else {
//			        	entities.get(k).setGravity(true);
//			        	//entities.get(k).setVelocity(velocity.get(k));
//			        	if(entities.get(k).getType() == EntityType.PLAYER) {
//			    		Player p = (Player) entities.get(k);
//			    		p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(1);
//			    		}else if(entities.get(k) instanceof Animals) {
//			    			player.sendMessage(entities.get(k).getType().toString());
//			    				
//			    			//	((Pig) entities.get(k)).setAI(true);
//			    		}else if (entities.get(k) instanceof Monster) {
//			    			//((Zombie) entities.get(k)).setAI(true);
//			    		}
//					}
//
//		        	
//			    		
//
//			    	}
//
//
//		    	}
//			}, 0, 0);
//	}
//	public void run(PlayerMoveEvent e) {
//		Player player = e.getPlayer();
//		if(!player.isOnGround() && isactive == true) {
//	)		if(player != cheesmoon) {
//				player.getVelocity().setY(-100);
//			}
//		}
//	}

}
