package com.minecave.powders.listeners;

import com.minecave.powders.Powders;
import com.minecave.powders.item.CustomItem;
import com.minecave.powders.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by Carter on 7/19/2015.
 */
public class RightClickListener implements Listener{

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event){
        if(event.getPlayer().hasPermission("powders.use")) {
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                ItemStack stack = event.getItem();
                if (stack != null) {
                    if (stack.hasItemMeta()) {
                        if (stack.getItemMeta().hasLore()) {
                            List<String> lore = stack.getItemMeta().getLore();
                            if (lore.size() > 0) {
                                String line = ChatColor.stripColor(lore.get(0));
                                CustomItem item = Powders.getInstance().getItemCoordinator().getByName(line);
                                if (item != null) {
                                    item.use(event.getPlayer());
                                    event.getPlayer().sendMessage(Messages.get("message.use." + item.getName().toLowerCase()));
                                    if (stack.getAmount() == 1) {
                                        event.getPlayer().setItemInHand(null);
                                    } else {
                                        stack.setAmount(stack.getAmount() - 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
