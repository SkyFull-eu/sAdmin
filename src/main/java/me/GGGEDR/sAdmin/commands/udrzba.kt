package me.GGGEDR.sAdmin.commands

import me.GGGEDR.sAdmin.Main
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object udrzba : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender.hasPermission("skyfull.admin.udrzba")){
            if(Main.udrzba_status == false){
                Main.udrzba_status = true
                sender.sendMessage("§c§lSystém §7► §fStatus udržby bol zmenený na: §cPrebiehajúca")
            } else {
                Main.udrzba_status = false
                sender.sendMessage("§c§lSystém §7► §fStatus udržby bol zmenený na: §cDokončená")
            }
        } else {
            sender.sendMessage("§c§lSystém §7► §fNa túto akciu nemáš dostatočné permissie!")
        }
        return false
    }
}
