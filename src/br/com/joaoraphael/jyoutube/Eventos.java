package br.com.joaoraphael.jyoutube;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Eventos implements Listener {
    
    @EventHandler
    public void bloquearPegar(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        String clicado = e.getInventory().getName();
        if (clicado.equalsIgnoreCase("Youtubers Online")){
            e.setCancelled(true);
        }
    }
}
