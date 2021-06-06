package fr.bafbi.myplugin;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import fr.bafbi.myplugin.custom.CustomItems;

public class MonPluginListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        player.getInventory().clear();

        ItemStack customsword = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta customMeta = customsword.getItemMeta();
        customMeta.setDisplayName(ChatColor.RED + "L'épée de la victoire");
        customMeta.setLore(Arrays.asList("Vacuum","wala"));
        customMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(), "customitem"), PersistentDataType.INTEGER, 1);
        customsword.setItemMeta(customMeta);

        player.getInventory().addItem(CustomItems.getCustomItem("victory_sword"));
        player.updateInventory();

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();

        if(item == null) return;

        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();

        if(data.has(new NamespacedKey(Main.getPlugin(), "customItem"), PersistentDataType.INTEGER)) {
            player.sendMessage("Cette item est custom");
            if(action == Action.RIGHT_CLICK_AIR && item.getItemMeta().hasLore() && item.getItemMeta().getLore().contains("Vacuum")) {
                player.sendMessage("cette item a vacuum");
            }
        }
        

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        //aa
    }

    @EventHandler
    public void onEntityDeath (EntityDeathEvent event) {

        LivingEntity entity = event.getEntity();
        Player player = entity.getKiller();

        if(player == null) return;

        ItemStack item = player.getInventory().getItemInMainHand();

        if(item == null) return;

        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();

        if(data.has(new NamespacedKey(Main.getPlugin(), "customItem"), PersistentDataType.INTEGER)) {
            if(data.has(new NamespacedKey(Main.getPlugin(), "victory_sword"), PersistentDataType.INTEGER)) {
                int pieceOfSet = 1;
                ItemStack[] armors = player.getInventory().getArmorContents();

                for (ItemStack armor : armors) {

                    if (armor != null) {
                        ItemMeta armormeta = armor.getItemMeta();
                        PersistentDataContainer armordata = armormeta.getPersistentDataContainer();

                        if(armordata.has(new NamespacedKey(Main.getPlugin(), "customitem"), PersistentDataType.INTEGER)) {
                            if(armordata.has(new NamespacedKey(Main.getPlugin(), "victory_set"), PersistentDataType.INTEGER)) {
                                pieceOfSet++;
                            }
                        }
                    }
                    
                }
                if (player.getAbsorptionAmount() < 4 * pieceOfSet) {

                    player.setAbsorptionAmount(player.getAbsorptionAmount() + 2);
                    player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.VOICE, 1, 1);
                    player.spawnParticle(Particle.HEART, player.getLocation(), 10, 0.5, 0.5, 0.5);
                }
                
            }
        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();
        InventoryView view = event.getView();

        if (current == null) return;

        if (view.getTitle() == "§8§nFarmrun GUI") {

            if (current.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getPlugin(), "create_farmplot"), PersistentDataType.INTEGER)) {

                event.setCancelled(true);
                
                if (current.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getPlugin(), "create_farmplot"), PersistentDataType.INTEGER)) {
                
                
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "customgive " + player.getName() + " victory_sword");

            }
            }

        }

    }


}
