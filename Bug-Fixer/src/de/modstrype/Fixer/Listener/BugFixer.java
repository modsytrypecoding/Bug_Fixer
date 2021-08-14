package de.modstrype.Fixer.Listener;

import de.modstrype.Fixer.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;


public class BugFixer implements Listener {
    @EventHandler
    public static void offOffHand(InventoryClickEvent e) {
        if(e.getSlot() == 40) {
            Player p = (Player) e.getWhoClicked();
            if(p.getInventory().getType() == InventoryType.PLAYER) {
                e.setCancelled(true);
                p.updateInventory();
            }

        }
    }
    @EventHandler
    public static void onFullDrop(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        if(p.getInventory().firstEmpty() == -1) {
            if(!(p.getInventory().getItemInOffHand().getType().equals(Material.AIR))) {
                World world = p.getWorld();
                world.dropItem(p.getLocation(), p.getInventory().getItemInOffHand());
                p.getInventory().setItemInOffHand(null);
            }
        }else {
            for(int i = 0; i <36; i++) {
                if(p.getInventory().getItem(i) == null) {
                    p.getInventory().setItem(i, p.getInventory().getItemInOffHand());
                    p.getInventory().setItemInOffHand(null);
                    break;

                }
            }
        }
    }

    @EventHandler
    public static void offSwitch(PlayerSwapHandItemsEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public static void offLightningFire(BlockIgniteEvent e) {
        if(e.getCause() == BlockIgniteEvent.IgniteCause.LIGHTNING) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void offPlacedFire(BlockIgniteEvent e) {
        if(e.getCause().equals(BlockIgniteEvent.IgniteCause.EXPLOSION)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public static void offLightningDamage(EntityDamageByEntityEvent e) {
        if(e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) {
            if(e.getEntity() instanceof Player) {
                e.setCancelled(true);
                ((Player) e.getEntity()).damage(5);
            }
        }
    }
    @EventHandler
    public static void offExplosion(EntityDamageByEntityEvent e) {
        EntityType droppedItem = EntityType.DROPPED_ITEM;
        if(e.getEntity().getType().equals(droppedItem)) {
            if(e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) || e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) {
                e.setCancelled(true);
            }


        }
    }
    @EventHandler
    public static void offFire(EntityDamageEvent e) {
        EntityType droppedItem = EntityType.DROPPED_ITEM;
        if(e.getEntity().getType().equals(droppedItem)) {
            if(e.getCause().equals(EntityDamageEvent.DamageCause.LIGHTNING) || e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
                e.setCancelled(true);
            }
        }


    }

    @EventHandler
    public static void offExplosionDamage(EntityExplodeEvent e) {
        if(e.getEntity().getType().equals(EntityType.FIREBALL) || e.getEntity().getType().equals(EntityType.WITHER_SKULL)) {
            e.setCancelled(true);
            e.getLocation().getWorld().createExplosion(e.getLocation(), 0.0F);
        }
    }
    }






