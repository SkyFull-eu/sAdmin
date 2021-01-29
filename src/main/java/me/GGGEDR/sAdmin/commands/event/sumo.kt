package me.GGGEDR.sAdmin.commands.event

import me.GGGEDR.sAdmin.Main
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask
import org.jetbrains.annotations.NotNull

object sumo : CommandExecutor{

    val players: ArrayList<Player> = ArrayList()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender.hasPermission("skyfull.sumo")){
            if(args.size == 1){
               if(args[0].equals("start")) run {
                   for(p: Player in Bukkit.getOnlinePlayers()){
                       if(p.world.name.equals("eventy")){
                           players.add(p)
                           p.sendMessage("§b§lSkySumo §7► §fEvent štartuje o: §b10 &fsekúnd !")
                       }
                   }
               } else {
                   sendUsage(sender)
               }
            } else {
                sendUsage(sender)
            }
        } else {
            sender.sendMessage("§c§lSystém §7► §fNa túto akciu nemáš dostatočné permissie!")
        }
        return false
    }

    fun sendUsage(sender: CommandSender){
        sender.sendMessage("§c§Systém §7► §fPouži: §c/sumo start")
    }

    /*fun startTimer(){
        BukkitRunnable({
            override fun run() {

            }
        }).runTaskTimer(Main.instance!!, 20, 20)
        var s = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance!!, Runnable {
            n++
            if(n > 20){

            }
        }, 20, 0)
    }*/

}
