package me.GGGEDR.sAdmin.Listeners.miner

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class MinerListener : Listener{

    @EventHandler
    fun onMine(e: BlockBreakEvent){
        if(e.player.gameMode == GameMode.SURVIVAL){
            if(!e.player.hasPermission("skyfull.mine")) {
                if (e.block.type == Material.IRON_ORE || e.block.type == Material.GOLD_ORE || e.block.type == Material.DIAMOND_ORE || e.block.type == Material.EMERALD_ORE || e.block.type == Material.ANCIENT_DEBRIS) {
                    for (p: Player in Bukkit.getOnlinePlayers()) {
                        if (p.hasPermission("skyfull.mine")) {
                            p.sendMessage("§d§lMine §7► §fHráč: §d${e.player.name} §fvykopal: §d${e.block.type.name}")
                        }
                    }
                }
            }
        }
    }

}
