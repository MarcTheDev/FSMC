package fsmc.fsmc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class FSMC extends JavaPlugin {

    /*
    FSMC - Nigga
     */

    @Override
    public void onEnable() {
        this.getCommand("setspawn").setExecutor(new Spawn(this));
        this.getCommand("spawn").setExecutor(new Spawn(this));
        this.getCommand("motd").setExecutor(new Motd(this));
        this.getCommand("nick").setExecutor(new Nick(this));
        Bukkit.getServer().getPluginManager().registerEvents(new Events(this), this);
        Bukkit.getLogger().info("FSMC v1.0 has been enabled.");
        saveDefaultConfig();
    }
    public void onDisable() {
        Bukkit.getLogger().info("FSMC v1.0 has been disabled.");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("fsmc")) {
            if(args.length == 0) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("default")));
                return true;
            }
            if(args[0].equalsIgnoreCase("reload")) {
                reloadConfig();
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("reload-message")));
                return true;
            }
        }
        return true;
    }
}
