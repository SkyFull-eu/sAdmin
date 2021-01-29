package me.GGGEDR.sAdmin.commands.member

import me.GGGEDR.sAdmin.commands.admin
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.ipvp.canvas.ClickInformation
import org.ipvp.canvas.Menu
import org.ipvp.canvas.mask.BinaryMask
import org.ipvp.canvas.mask.Mask
import org.ipvp.canvas.slot.ClickOptions
import org.ipvp.canvas.slot.Slot
import org.ipvp.canvas.type.ChestMenu

object odmena : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        openGui((sender as Player))
        return false
    }

    fun openGui(p: Player){
        val m: Menu = ChestMenu.builder(4)
                .title("§9§lOdmena")
                .build()
        addWhiteBorder(m)
        addReward(p, m, 13, "%srewards_member%", "member", "Odmena pre hráčov","250", "5")
        addReward(p, m, 20, "%srewards_vip%", "vip", "Odmena pre God VIP","500", "10")
        addReward(p, m, 24, "%srewards_sponzor%", "sponzor", "Odmena pre Sponzorov","750", "15")
        m.open(p)
    }


    fun addWhiteBorder(menu: Menu) {
        val mask: Mask = BinaryMask.builder(menu)
                .item(ItemStack(Material.GRAY_STAINED_GLASS_PANE))
                .pattern("111111111")
                .pattern("100000001")
                .pattern("100000001")
                .pattern("111111111").build()
        mask.apply(menu)
    }

    fun addReward(p: Player, menu: Menu, slot: Int, placeholder: String, reward: String, title: String, obsahcoins: String, obsahlevel: String){
        val s: Slot = menu.getSlot(slot)
        s.setItemTemplate {
            val item = ItemStack(getMaterial(p, placeholder))
            val itemMeta: ItemMeta = item.itemMeta
            val color: String = getColor(p, placeholder)
            itemMeta.setDisplayName(color + title)
            itemMeta.lore = listOf(
                    "${color}§l┊ §fStav: ${getYesOrNoFromPlaceholder(p, placeholder)}",
                    "${color}§l┊ §fOdmena: ${color + obsahcoins} Coins §fa ${color + obsahlevel} Levelov"
            )
            item.itemMeta = itemMeta
            item
        }
        addClickOptions(s)
        addPlayerClickHandler(s, reward)
    }

    fun getMaterial(p: Player, placeholder: String) : Material{
        var m: Material = Material.MINECART
        if(PlaceholderAPI.setPlaceholders(p, placeholder).equals("true")){
            m = Material.CHEST_MINECART
        }

        return m
    }

    fun getYesOrNoFromPlaceholder(p: Player, placeholder: String) : String{
        var m: String = "§cNedotupná"
        if(PlaceholderAPI.setPlaceholders(p, placeholder).equals("true")){
            m = "§aDostupná"
        }
        return m
    }

    fun getColor(p: Player, placeholder: String) : String{
        var m: String = "§c"
        if(PlaceholderAPI.setPlaceholders(p, placeholder).equals("true")){
            m = "§a"
        }
        return m
    }

    fun addClickOptions(slot: Slot) {
        val options = ClickOptions.builder()
                .allow(ClickType.LEFT, ClickType.RIGHT)
                .allow(InventoryAction.PLACE_ALL, InventoryAction.PLACE_ONE, InventoryAction.PLACE_SOME)
                .build()
        slot.clickOptions = options
    }

    fun addPlayerClickHandler(slot: Slot, odmena: String) {
        slot.setClickHandler { player: Player, info: ClickInformation ->
            run {
                player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
                player.closeInventory()
                player.chat("/daylier $odmena")
            }
        }
    }

}
