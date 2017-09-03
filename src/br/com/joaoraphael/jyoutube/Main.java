package br.com.joaoraphael.jyoutube;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        RegisterCommands();
        RegisterEvents();
        if (!new File(getDataFolder(), "config.yml").exists()){
            saveDefaultConfig();
        }
    }

    @Override
    public void onDisable() {
        super.onDisable(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void RegisterCommands(){
        getCommand("youtube").setExecutor(new Youtuber());
    }
    
    public void RegisterEvents(){
        Bukkit.getPluginManager().registerEvents(new Eventos(), this);
    }
    
    
    public static Main getMain(){
        return (Main) Bukkit.getPluginManager().getPlugin("jYoutube");
    }
    
}
