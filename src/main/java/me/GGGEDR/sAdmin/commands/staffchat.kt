package me.GGGEDR.sAdmin.commands

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.Collections.list

object staffchat : CommandExecutor {

    var toogle: HashMap<String, Boolean> = HashMap()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender.hasPermission("skyfull.schat")){
                var message: String = "null"
                for(arg: String in args){
                    if(message.equals("null")){
                        message = arg
                    } else {
                        message = "$message $arg"
                    }
                }
                if(sender.hasPermission("skyfull.schat")){
                    if(message.equals("null")){
                        sender.sendMessage("§c§lSystém §7► §fPouži: §c/staffchat (toggle) [message]")
                    } else {
                        if(message.equals("toggle")){
                            if(toogle.contains(sender.name)){
                                if(toogle.get(sender.name) == false){
                                    toogle.remove(sender as Player)
                                    toogle.set(sender.name, true)
                                    sender.sendMessage("§c§lSystém §7► §fTvoj staffchat bol togglenutý na: §cTrue")
                                } else {
                                    toogle.remove(sender.name)
                                    toogle.set(sender.name, false)
                                    sender.sendMessage("§c§lSystém §7► §fTvoj staffchat bol togglenutý na: §cFalse")
                                }
                            } else {
                                toogle.set(sender.name, true)
                                sender.sendMessage("§c§lSystém §7► §fTvoj staffchat bol togglenutý na: §cTrue")
                            }
                        } else {
                            for(p: Player in Bukkit.getOnlinePlayers()) {
                                if(p.hasPermission("skyfull.schat")) {
                                    p.sendMessage("§b§lStaff §r§a ${sender.name} §7► §f$message")
                                }
                            }
                        }

                    }
                }
        } else {
            sender.sendMessage("§c§lSystém §7► §fNa túto akciu nemáš dostatočné permissie!")
        }
        return false;
    }
}
