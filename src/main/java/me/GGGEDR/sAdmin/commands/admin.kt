package me.GGGEDR.sAdmin.commands


import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.SkullMeta
import org.ipvp.canvas.ClickInformation
import org.ipvp.canvas.Menu
import org.ipvp.canvas.mask.BinaryMask
import org.ipvp.canvas.mask.Mask
import org.ipvp.canvas.slot.ClickOptions
import org.ipvp.canvas.slot.Slot
import org.ipvp.canvas.type.ChestMenu
import java.awt.SystemColor.menu


object admin : CommandExecutor, Listener {


    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender.hasPermission("skyfull.admin.menu")){
            openMainMenu((sender as Player))
        } else {
            sender.sendMessage("§c§lSystém §7► §fNa túto akciu nemáš dostatočné permissie!")
        }
        return false
    }

    fun openMainMenu(p: Player){
        val m: Menu = ChestMenu.builder(4)
                    .title("§9§lPlayers")
                    .build()
        val e = Bukkit.getOnlinePlayers().size -1
        for (i in 0..e) run {
            val pe: Player = Bukkit.getOnlinePlayers().toList().get(i);
            val slot: Slot = m.getSlot(i);
            slot.setItemTemplate {
                val item = ItemStack(Material.PLAYER_HEAD)
                val itemMeta: SkullMeta = (item.itemMeta as SkullMeta)
                itemMeta.setDisplayName("§b§l${pe.name}")
                itemMeta.setOwner(pe.name)
                itemMeta.lore = listOf(
                        "§b§l┊ §fIP Adresa: §b${pe.address.toString().split("/")[1].split(":")[0]}",
                        "§b§l┊ §fClient: §b${pe.clientBrandName}",
                        "§b§l| §fISP: §b${pe.address.hostName}"
                );
                item.itemMeta = itemMeta
                item
            }
            addClickOptions(slot)
            addPlayerClickHandler(slot, pe)
        }
        m.open(p)
    }

    fun addClickOptions(slot: Slot) {
        val options = ClickOptions.builder()
                .allow(ClickType.LEFT, ClickType.RIGHT)
                .allow(InventoryAction.PLACE_ALL, InventoryAction.PLACE_ONE, InventoryAction.PLACE_SOME)
                .build()
        slot.clickOptions = options
    }

    fun addPlayerClickHandler(slot: Slot, target: Player) {
        slot.setClickHandler { player: Player, info: ClickInformation ->
            run {
                player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
                player.closeInventory()
                openPlayerMenu(player, target)
            }
        }
    }

    fun openPlayerMenu(p: Player, target: Player){
        val m: Menu = ChestMenu.builder(4)
                .title("§9§l${target.name}")
                .build()
        addWhiteBorder(m)
        addPlayerHead(m.getSlot(4), target)
        addPlayerCoin(m.getSlot(11), target)
        addPlayerXPBottle(m.getSlot(20), target)
        addPlayerInformationNameTag(m.getSlot(15), target)
        addPlayerInventoryOpen(m.getSlot(24), target)
        addPlayerTeleportCompass(m.getSlot(22), target)
        m.open(p);
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

    fun addPlayerHead(slot: Slot, target: Player){
        slot.setItemTemplate {
            val item = ItemStack(Material.PLAYER_HEAD)
            val itemMeta: SkullMeta = (item.itemMeta as SkullMeta)
            itemMeta.setDisplayName("§b§l${target.name}")
            itemMeta.setOwner(target.name)
            itemMeta.lore = listOf(
                    "§b§l┊ §fIP Adresa: §b${target.address.toString().split("/")[1].split(":")[0]}",
                    "§b§l┊ §fClient: §b${target.clientBrandName}",
                    "§b§l| §fISP: §b${target.address.hostName}"
            )
            item.itemMeta = itemMeta
            item
        }
        addClickOptions(slot)
    }

    fun addPlayerCoin(slot: Slot, target: Player){
        slot.setItemTemplate {
            val item = ItemStack(Material.SUNFLOWER)
            val itemMeta: ItemMeta = item.itemMeta
            itemMeta.setDisplayName("§e§lEconomy")
            itemMeta.lore = listOf(
                    PlaceholderAPI.setPlaceholders(target, "§e§l┊ §fCoins: §e%vault_eco_balance_formatted% §e⛃"),
                    PlaceholderAPI.setPlaceholders(target, "§e§l┊ §fTokeny: §e%tm_tokens_formatted% §e⛁")
            )
            item.itemMeta = itemMeta
            item
        }
        addClickOptions(slot)
    }

    fun addPlayerXPBottle(slot: Slot, target: Player){
        slot.setItemTemplate {
            val item = ItemStack(Material.EXPERIENCE_BOTTLE)
            val itemMeta: ItemMeta = item.itemMeta
            itemMeta.setDisplayName("§a§lLeveling")
            itemMeta.lore = listOf(
                    PlaceholderAPI.setPlaceholders(target, "§a§l┊ §fLevel: §a%LevelPoints_player_level%"),
                    PlaceholderAPI.setPlaceholders(target, "§a§l┊ §fPostup o: §a%level_ostava% XP")
            )
            item.itemMeta = itemMeta
            item
        }
        addClickOptions(slot)
    }

    fun addPlayerInformationNameTag(slot: Slot, target: Player){
        slot.setItemTemplate {
            val item = ItemStack(Material.NAME_TAG)
            val itemMeta: ItemMeta = item.itemMeta
            itemMeta.setDisplayName("§e§lInformation")
            itemMeta.lore = listOf(
                    PlaceholderAPI.setPlaceholders(target, "§e§l┊ §fRank: §e%luckperms_primary_group_name%"),
                    PlaceholderAPI.setPlaceholders(target, "§e§l┊ §fGamemode: §e%player_gamemode%"),
                    PlaceholderAPI.setPlaceholders(target, "§e§l┊ §fFly: §e%essentials_fly%")
            )
            item.itemMeta = itemMeta
            item
        }
        addClickOptions(slot)
    }


    fun addPlayerInventoryOpen(slot: Slot, target: Player){
        slot.setItemTemplate {
            val item = ItemStack(Material.CHEST)
            val itemMeta: ItemMeta = item.itemMeta
            itemMeta.setDisplayName("§a§lInventory")
            itemMeta.lore = listOf(
                    "§fKliknutím otvoríš inventár hráča: §a${target.name}"
            )
            item.itemMeta = itemMeta
            item
        }
        addClickOptions(slot)
        slot.setClickHandler { player: Player, info: ClickInformation ->
            run {
                player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
                player.closeInventory()
                player.chat("/invsee ${target.name}")
            }
        }
    }

    fun addPlayerTeleportCompass(slot: Slot, target: Player){
        slot.setItemTemplate {
            val item = ItemStack(Material.COMPASS)
            val itemMeta: ItemMeta = item.itemMeta
            itemMeta.setDisplayName("§b§lTeleport")
            itemMeta.lore = listOf(
                    "§fKliknutím sa teleportuješ k hráčovi: §b${target.name}"
            )
            item.itemMeta = itemMeta
            item
        }
        addClickOptions(slot)
        slot.setClickHandler { player: Player, info: ClickInformation ->
            run {
                player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
                player.closeInventory()
                player.chat("/tp ${target.name}")
            }
        }
    }

}
