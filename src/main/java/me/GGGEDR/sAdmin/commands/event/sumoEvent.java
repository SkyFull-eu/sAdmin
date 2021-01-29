package me.GGGEDR.sAdmin.commands.event;

import me.GGGEDR.sAdmin.Main;
import me.GGGEDR.sAdmin.StringTools.CenterMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class sumoEvent implements CommandExecutor {

    public static List<Player> players = new ArrayList<>();
    public static CommandSender senderr;
    public static SUMOSTATE status = SUMOSTATE.END;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender.hasPermission("skyfull.sumo")){
            if(args.length == 1) {
                if (args[0].equalsIgnoreCase("start")) {
                    status = SUMOSTATE.STARTING;
                    senderr = sender;
                    start(sender.getName());
                } else if(args[0].equalsIgnoreCase("notify")) {
                    for(Player p : Bukkit.getOnlinePlayers()){
                        sendAnnouncement(p);
                    }
                } else {
                    usage(sender);
                }
            } else {
                usage(sender);
            }
        } else {
            sender.sendMessage("§c§lSystém §7► §fNa túto akciu nemáš dostatočné permissie!");
        }

        return false;
    }

    public void usage(CommandSender sender){
        sender.sendMessage("§c§Systém §7► §fPouži: §c/sumo <start|notify>");
    }

    public void sendAnnouncement(Player p){
        p.sendMessage(" ");
        p.sendMessage(" ");
        new CenterMessage("§a§lSUMO EVENT").send(p);
        new CenterMessage("§7(§c/event§7)").send(p);;
        p.sendMessage(" ");
        new CenterMessage("§e§lVýhra: ").send(p);;
        new CenterMessage("§e10 §fTokenov").send(p);;
        p.sendMessage(" ");
        p.sendMessage(" ");
    }

    public void start(String sender){
        for(Player p : Bukkit.getOnlinePlayers()){
            if(p.getLocation().getWorld().getName().equalsIgnoreCase("eventy")){
                if(!p.getName().equalsIgnoreCase(sender)){
                    players.add(p);
                }
            }
        }
        final int[] odpocet = {20};
        new BukkitRunnable(){
            @Override
            public void run() {
                odpocet[0]--;
                if(odpocet[0] == 15){
                    senderr.sendMessage("§e§lEVENT §7► §fEvent začne za: §e15 §fsekund");
                    for(Player p : players){
                        p.sendMessage("§e§lEVENT §7► §fEvent začne za: §e15 §fsekund");
                    }
                }
                if(odpocet[0] == 10){
                    senderr.sendMessage("§e§lEVENT §7► §fEvent začne za: §e10 §fsekund");
                    for(Player p : players){
                        p.sendMessage("§e§lEVENT §7► §fEvent začne za: §e10 §fsekund");
                    }
                }
                if(odpocet[0] <= 5){
                    if(!(odpocet[0] < 0)) {
                        if(!(odpocet[0] == 0)) {
                            senderr.sendMessage("§e§lEVENT §7► §fEvent začne za: §e" + odpocet[0] + " §fsekund");
                            for (Player p : players) {
                                p.getInventory().clear();
                                ItemStack knocback = new ItemStack(Material.STICK);
                                ItemMeta meta = knocback.getItemMeta();
                                meta.addEnchant(Enchantment.KNOCKBACK, 2, false);
                                knocback.setItemMeta(meta);
                                p.getInventory().addItem(knocback);
                                p.sendMessage("§e§lEVENT §7► §fEvent začne za: §e" + odpocet[0] + " §fsekund");
                            }
                        } else {
                            status = SUMOSTATE.INGAME;
                            senderr.sendMessage("§e§lEVENT §7► §fEvent začína!");
                            for (Player p : players) {
                                p.sendMessage("§e§lEVENT §7► §fEvent začína!");
                                //168 15 -32
                                p.teleport(new Location(Bukkit.getWorld("eventy"), 168, 15, -32));
                            }
                        }
                    } else {
                        cancel();
                    }
                }
            }
        }.runTaskTimer(Main.Companion.getInstance(), 0, 20L);
    }
}
