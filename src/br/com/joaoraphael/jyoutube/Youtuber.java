package br.com.joaoraphael.jyoutube;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

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
                            
                            if (principal.getConfig().getBoolean("Chat") == true && principal.getConfig().getBoolean("Gui") == false){
                                if (youtubers.isEmpty()){
                                p.sendMessage(principal.getConfig().getString("nenhum_on").replace("&", "§").replace("{prefixo}", principal.getConfig().getString("prefixo").replace("&", "§")));
                                
                            }else if (!(youtubers.isEmpty())){
                                p.sendMessage("§fYoutubers online: ");
                            for (int c = 0;c<youtubers.size();c++){
                                p.sendMessage("  §f- " + principal.getConfig().getString("prefixo").replace("&", "§") + " " + youtubers.get(c));
                            }
                            }
                            }else if (principal.getConfig().getBoolean("Chat") == false && principal.getConfig().getBoolean("Gui") == true){
                                int tamanho = 9;
                                
                                if (!(youtubers.isEmpty())){
                                    //Todos outros ifs aqui dentro
                                    
                                    //Definir tamanho do inventário
                                    if (youtubers.size() > 7){
                                        tamanho = 2 * 9;
                                    }
                                    if (youtubers.size() > 16){
                                        tamanho = 3 * 9;
                                    }
                                    if (youtubers.size() > 25){
                                        tamanho = 4 * 9;
                                    }
                                    if (youtubers.size() > 34){
                                        tamanho = 5 * 9;
                                    }
                                    if (youtubers.size() > 43){
                                        tamanho = 54;
                                    }
                                    
                                    Inventory youtubergui = Bukkit.createInventory(null, tamanho, "Youtubers Online");
                                ArrayList<ItemStack> cabecasy = new ArrayList();
                                
                                for (int c = 0;c<youtubers.size();c++){
                                    ItemStack cabecaa = new ItemStack(397, 1, (short)3);
                                    ItemMeta cmeta = cabecaa.getItemMeta();
                                    cmeta.setDisplayName(ChatColor.GOLD + youtubers.get(c));
                                    ArrayList<String> clore = new ArrayList();
                                    clore.add(" §eNick: §7" + youtubers.get(c));
                                    clore.add(" §eCanal: §7em desenvolvimento");
                                    clore.add(" §eInscritos: §7em desenvolvimento");
                                    cmeta.setLore(clore);
                                    cabecaa.setItemMeta(cmeta);
                                    SkullMeta ccmeta = (SkullMeta) cabecaa.getItemMeta();
                                    ccmeta.setOwner(youtubers.get(c));
                                    cabecaa.setItemMeta(ccmeta);
                                    cabecasy.add(cabecaa);
                                }
                                
                                for (int cc = 0;cc<cabecasy.size();cc++){
                                    youtubergui.addItem(cabecasy.get(cc));
                                }
                                
                                p.openInventory(youtubergui);
                                    
                                }else if (youtubers.isEmpty()){
                                    p.sendMessage(principal.getConfig().getString("nenhum_on").replace("&", "§").replace("{prefixo}", principal.getConfig().getString("prefixo").replace("&", "§")));
                                }
                                
                                
                            }else if (((principal.getConfig().getBoolean("Chat") == true && principal.getConfig().getBoolean("Gui") == true)) || ((principal.getConfig().getBoolean("Chat") == false && principal.getConfig().getBoolean("Gui") == false))){
                                p.sendMessage("§Ops... Algum erro ocorreu, entre em contato com a equipe de administração.");
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
