package me.GGGEDR.sAdmin.Listeners.antinadavkovac

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import java.util.*

class antiNadavkovacTriTisice : Listener {

    @EventHandler
    fun onChattingInStaffChat(e: AsyncPlayerChatEvent){
        val nadavky: ArrayList<String> = ArrayList<String>()
        nadavky.add("kokot")
        nadavky.add("kkt")
        nadavky.add("pica")
        nadavky.add("piča")
        nadavky.add("dpc")
        nadavky.add("dpč")
        nadavky.add("kurva")
        nadavky.add("kurwa")
        nadavky.add("gay")
        nadavky.add("buzerant")
        nadavky.add("hovno")
        nadavky.add("pipik")
        nadavky.add("vagina")
        nadavky.add("penis")
        nadavky.add("pico")
        nadavky.add("pičo")
        nadavky.add("zkurven")
        nadavky.add("jebal")
        nadavky.add("debil")
        nadavky.add("idiot")
        nadavky.add("tpc")
        nadavky.add("tpč")
        var located: Boolean = false
        var slovo: String = "???"
        for(word: String in nadavky){
            if(e.message.toLowerCase().replace(" ", "").contains(word.toLowerCase())){
                slovo = word
                located = true
            }
        }
        if(located){
            e.isCancelled = true
            e.player.sendMessage("§c§lSystém §7► §fPorušovanie pravidla: §c2.1 §falebo §c2.2 §fnieje povolené!")
        }
    }
}
