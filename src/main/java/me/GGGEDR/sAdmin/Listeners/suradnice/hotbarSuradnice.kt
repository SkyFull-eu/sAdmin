package me.GGGEDR.sAdmin.Listeners.suradnice

import me.GGGEDR.sAdmin.Main
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player

class hotbarSuradnice {

    fun startBucketSuradniceRunnable() : Int {
        val n = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance!!, Runnable {
            for(p: Player in Bukkit.getOnlinePlayers()){
                val l: Location = p.getLocation();
                p.sendActionBar("§bX: §f${l.blockX} §bY: §f${l.blockY} §bZ: §f${l.blockZ}")
            }
        }, 5, 0);

        return n
    }
}
