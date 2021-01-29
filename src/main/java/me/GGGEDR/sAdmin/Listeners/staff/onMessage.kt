package me.GGGEDR.sAdmin.Listeners.staff

import me.GGGEDR.sAdmin.commands.staffchat
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class onMessage : Listener {

    @EventHandler
    fun onChattingInStaffChat(e: AsyncPlayerChatEvent){
        if(staffchat.toogle.contains(e.player.name)){
            if(e.player.hasPermission("skyfull.schat")){
                if(staffchat.toogle.get(e.player.name) == true){
                    e.isCancelled = true
                    for(p: Player in Bukkit.getOnlinePlayers()){
                        if(p.hasPermission("skyfull.schat")){
                            p.sendMessage("§b§lStaff §r§a ${e.player.name} §7► §f${e.message}")
                        }
                    }
                }
            }
        }
    }

}
