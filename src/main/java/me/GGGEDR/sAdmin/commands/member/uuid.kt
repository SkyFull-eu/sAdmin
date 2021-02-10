package me.GGGEDR.sAdmin.commands.member

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import java.nio.charset.StandardCharsets.UTF_8
import java.util.*


object uuid : CommandExecutor{

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender is Player) {
            if (args.isEmpty()) {
                val uuid = UUID.nameUUIDFromBytes("OfflinePlayer:${(sender as Player).name}".toByteArray(UTF_8))
                sender.sendMessage("§c§lSystém §7► §fTvoje Informácie:")
                sender.sendMessage("      §c× §fNick: §c${sender.name}")
                sender.sendMessage("      §c× §fOnline UUID: §c${(sender as Player).uniqueId}")
                sender.sendMessage("      §c× §fOffline UUID: §c$uuid")
                sender.sendMessage("      §c× §fClient: §c${(sender as Player).clientBrandName}")
            } else if (args.size == 1) {
                if(sender.hasPermission("skyfull.uuid")) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        val uuid = UUID.nameUUIDFromBytes("OfflinePlayer:${Bukkit.getPlayer(args[0])?.name}".toByteArray(UTF_8))
                        sender.sendMessage("§c§lSystém §7► §fHráčove informácie:")
                        sender.sendMessage("      §c× §fNick: §c${Bukkit.getPlayer(args[0])?.name}")
                        sender.sendMessage("      §c× §fOnline UUID: §c${Bukkit.getPlayer(args[0])?.uniqueId}")
                        sender.sendMessage("      §c× §fOffline UUID: §c$uuid")
                        sender.sendMessage("      §c× §fClient: §c${Bukkit.getPlayer(args[0])?.clientBrandName}")
                    } else {
                        sender.sendMessage("§c§lSystém §7► §fTento hráč nieje online!")
                    }
                } else {
                    val uuid = UUID.nameUUIDFromBytes("OfflinePlayer:${sender.name}".toByteArray(UTF_8))
                    sender.sendMessage("§c§lSystém §7► §fTvoje Informácie:")
                    sender.sendMessage("      §c× §fNick: §c${sender.name}")
                    sender.sendMessage("      §c× §fOnline UUID: §c${sender.uniqueId}")
                    sender.sendMessage("      §c× §fOffline UUID: §c$uuid")
                    sender.sendMessage("      §c× §fClient: §c${sender.clientBrandName}")
                }
            } else {
                val uuid = UUID.nameUUIDFromBytes("OfflinePlayer:${sender.name}".toByteArray(UTF_8))
                sender.sendMessage("§c§lSystém §7► §fTvoje Informácie:")
                sender.sendMessage("      §c× §fNick: §c${sender.name}")
                sender.sendMessage("      §c× §fOnline UUID: §c${sender.uniqueId}")
                sender.sendMessage("      §c× §fOffline UUID: §c$uuid")
                sender.sendMessage("      §c× §fClient: §c${sender.clientBrandName}")
            }
        } else {
            sender.sendMessage("§c§lSystém §7► §fTento príkaz môže použit len hráč!")
        }
        return false
    }


}
