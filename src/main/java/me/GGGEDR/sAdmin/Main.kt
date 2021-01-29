package me.GGGEDR.sAdmin

import me.GGGEDR.sAdmin.Listeners.antinadavkovac.antiNadavkovacTriTisice
import me.GGGEDR.sAdmin.Listeners.event.Sumo
import me.GGGEDR.sAdmin.Listeners.miner.MinerListener
import me.GGGEDR.sAdmin.Listeners.staff.onMessage
import me.GGGEDR.sAdmin.Listeners.suradnice.hotbarSuradnice
import me.GGGEDR.sAdmin.Listeners.udrzba.join
import me.GGGEDR.sAdmin.commands.admin
import me.GGGEDR.sAdmin.commands.event.sumoEvent
import me.GGGEDR.sAdmin.commands.member.odmena
import me.GGGEDR.sAdmin.commands.staffchat
import me.GGGEDR.sAdmin.commands.udrzba
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.ipvp.canvas.MenuFunctionListener




class Main : JavaPlugin() {

    companion object {
        var instance: Main? = null
            private set
        var udrzba_status: Boolean = false
    }

    var n = 0;

    val log: HashMap<Player, List<String>> = HashMap();

    override fun onEnable() {
        instance = this
        dataFolder.mkdir()
        config.options().copyDefaults(true)
        saveConfig()
        getCommand("staffchat")?.setExecutor(staffchat)
        getCommand("admin")?.setExecutor(admin)
        getCommand("udrzba")?.setExecutor(udrzba)
        getCommand("odmena")?.setExecutor(odmena)
        getCommand("sumo")?.setExecutor(sumoEvent())
        Bukkit.getPluginManager().registerEvents(MenuFunctionListener(), this)
        Bukkit.getPluginManager().registerEvents(join(), this)
        Bukkit.getPluginManager().registerEvents(onMessage(), this)
        Bukkit.getPluginManager().registerEvents(antiNadavkovacTriTisice(), this)
        Bukkit.getPluginManager().registerEvents(MinerListener(), this)
        Bukkit.getPluginManager().registerEvents(Sumo(), this)
        n = hotbarSuradnice().startBucketSuradniceRunnable()
    }

    override fun onDisable() {
        Bukkit.getScheduler().cancelTask(n)
    }
}
