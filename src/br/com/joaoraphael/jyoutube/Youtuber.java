package br.com.joaoraphael.jyoutube;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Youtuber implements CommandExecutor{

    private final Main principal = Main.getMain();
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("youtube")){
                if (args.length == 0){
                    if (p.hasPermission("jyoutube.use")){
                        for (String mensagem: principal.getConfig().getStringList("mensagem")){
                            p.sendMessage(mensagem.replace("&", "§"));
                        }
                        
                    }else{
                        p.sendMessage(principal.getConfig().getString("sem_permissao").replace("&", "§"));
                    }
                }else if (args.length >= 1){
                    if (args[0].equals("online")){
                        if(p.hasPermission("jyoutube.lista")){
                            ArrayList<String> youtubers = new ArrayList();
                            
                            for (Player todos : Bukkit.getOnlinePlayers()){
                                if (todos.hasPermission("jyoutube.youtuber")){
                                    youtubers.add(todos.getName());
                                }
                            }
                            if (youtubers.isEmpty()){
                                p.sendMessage(principal.getConfig().getString("nenhum_on").replace("&", "§").replace("{prefixo}", principal.getConfig().getString("prefixo").replace("&", "§")));
                                
                            }else if (!(youtubers.isEmpty())){
                                p.sendMessage("§fYoutubers online: ");
                            for (int c = 0;c<youtubers.size();c++){
                                p.sendMessage("  §f- " + principal.getConfig().getString("prefixo").replace("&", "§") + " " + youtubers.get(c));
                            }
                            }
                            
                        }else{
                            p.sendMessage(principal.getConfig().getString("sem_permissao").replace("&", "§"));
                        }
                    }
                }
            }
            
        }else{
            Bukkit.getConsoleSender().sendMessage("§cEsse comando só pode ser executado por jogadores.");
        }
        return false;
    }
    
    
        
    
    

}
