package me.GGGEDR.sAdmin.Listeners.udrzba

import me.GGGEDR.sAdmin.Main
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class join : Listener{

    @EventHandler
    fun onJoin(e: PlayerJoinEvent){
        if(Main.udrzba_status){
            if(e.player.hasPermission("skyfull.udrzba.bypass")){
                e.player.kickPlayer("§f§l§m---------------------------\n" +
                        "§c§lPrebieha údržba\n" +
                        "§f\n" +
                        "§fViac info na našom discorde:\n" +
                        "§chttps://discord.gg/hReeQXce7C\n" +
                        "§f§l§m---------------------------")
            }
        }
    }
}
