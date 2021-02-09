package me.GGGEDR.sAdmin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class MobTrapka {

    public static void start(){
        new BukkitRunnable() {

            @Override
            public void run() {
                int a = 0;
                for(Player p: Bukkit.getOnlinePlayers()){
                    if(p.getLocation().getWorld().getName().equalsIgnoreCase("world_the_end")){
                        a++;
                    }
                }
                if(a >= 1){
                    spawnEnderMan(new Location(Bukkit.getWorld("world_the_end"), 7, 80, -20));
                    spawnEnderMan(new Location(Bukkit.getWorld("world_the_end"), 7, 80, -25));
                    spawnEnderMan(new Location(Bukkit.getWorld("world_the_end"), 7, 80, -24));
                    spawnEnderMan(new Location(Bukkit.getWorld("world_the_end"), 7, 80, -23));
                    spawnEnderMan(new Location(Bukkit.getWorld("world_the_end"), 7, 80, -22));
                    spawnEnderMan(new Location(Bukkit.getWorld("world_the_end"), 7, 80, -21));
                }
            }
        }.runTaskTimer(Main.Companion.getInstance(), 0, 100L);
    }

    private static void spawnEnderMan(Location loc){
        loc.getWorld().spawnEntity(loc, EntityType.ENDERMAN);
    }
}

