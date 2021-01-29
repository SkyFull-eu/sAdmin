package me.GGGEDR.sAdmin.Listeners.event;

import me.GGGEDR.sAdmin.commands.event.SUMOSTATE;
import me.GGGEDR.sAdmin.commands.event.sumoEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Sumo implements Listener {

    @EventHandler
    public void quit(PlayerQuitEvent e){
        if(sumoEvent.status == SUMOSTATE.STARTING || sumoEvent.status == SUMOSTATE.INGAME){
            if(sumoEvent.players.contains(e.getPlayer())) {
                sumoEvent.players.remove(e.getPlayer());
                sumoEvent.senderr.sendMessage("§e§lEVENT §7► §fHráč: §e" + e.getPlayer().getName() + " §fodišiel z eventu!");
                for (Player p : sumoEvent.players) {
                    p.sendMessage("§e§lEVENT §7► §fHráč: §e" + e.getPlayer().getName() + " §fodišiel z eventu!");
                }
                control();
            }
        }
    }

    @EventHandler
    public void dead(PlayerDeathEvent e){
        if(sumoEvent.status == SUMOSTATE.STARTING){
            //252 5 -28
            if(sumoEvent.players.contains(e.getEntity())) {
                e.getEntity().teleport(new Location(Bukkit.getWorld("eventy"), 252, 5, -28));
            }
        }
    }

    @EventHandler
    public void damage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player) {
            if (sumoEvent.status == SUMOSTATE.INGAME) {
                if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                    if (sumoEvent.players.contains(e.getEntity())) {
                        e.setCancelled(true);
                        sumoEvent.players.remove(e.getEntity());
                        sumoEvent.senderr.sendMessage("§e§lEVENT §7► §fHráč: §e" + e.getEntity().getName() + " §fbol zabitý!");
                        for (Player p : sumoEvent.players) {
                            p.sendMessage("§e§lEVENT §7► §fHráč: §e" + e.getEntity().getName() + " §fbol zabitý!");
                        }
                        //170 51 -35
                        e.getEntity().sendMessage("§e§lEVENT §7► §fZomrel si §c:c");
                        e.getEntity().teleport(new Location(Bukkit.getWorld("eventy"), 170, 51, -34));
                        control();
                    }
                } else if(e.getCause() == EntityDamageEvent.DamageCause.FALL){
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onTP(PlayerTeleportEvent e){
        if(e.getFrom().getWorld() != e.getTo().getWorld()){
            if(sumoEvent.status == SUMOSTATE.INGAME){
                if(e.getTo().getWorld().getName().equalsIgnoreCase("eventy")) {
                    if (!sumoEvent.players.contains(e.getPlayer())) {
                        e.getPlayer().teleport(new Location(Bukkit.getWorld("eventy"), 170, 51, -34));
                    }
                } else {
                    if (sumoEvent.players.contains(e.getPlayer())) {
                        sumoEvent.players.remove(e.getPlayer());
                        sumoEvent.senderr.sendMessage("§e§lEVENT §7► §fHráč: §e" + e.getPlayer().getName() + " §fodišiel z eventu!");
                        for (Player p : sumoEvent.players) {
                            p.sendMessage("§e§lEVENT §7► §fHráč: §e" + e.getPlayer().getName() + " §fodišiel z eventu!");
                        }
                    }
                }
            }
        }
    }


    public void control(){
        if(sumoEvent.players.size() == 1){
            sumoEvent.status = SUMOSTATE.END;
            Bukkit.broadcastMessage("§e§lEVENT §7► §fEvent §eSumo §fvyhral hráč: §e"+ sumoEvent.players.get(0).getName());
            sumoEvent.players.clear();
        }
    }
}
