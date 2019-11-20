package fsmc.fsmc;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Nick implements CommandExecutor {
    private FSMC plugin;
    public Nick(FSMC plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("nick")) {
            if(p.hasPermission("fsmc.nick")) {
                if(args.length == 0) {
                    p.sendMessage("Error: Usage invalid.");
                    return true;
                }
                if(args[0].equalsIgnoreCase("off")) {
                    plugin.getConfig().set("user.nick." + p.getName(), p.getName());
                    plugin.saveConfig();
                    p.setDisplayName(p.getName());
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aFSMC&8] &bYour nickname is removed: " + plugin.getConfig().getString("user.nick." + p.getName())));
                    return true;
                }
                if(args.length == 1) {
                    plugin.getConfig().set("user.nick." + p.getName(), args[0] + "&r");
                    plugin.saveConfig();
                    p.setDisplayName(ChatColor.translateAlternateColorCodes('&', args[0] + "&r"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aFSMC&8] &bYour nickname is now: " + plugin.getConfig().getString("user.nick." + p.getName())));
                    return true;
                }
                if(args.length  >= 2) {
                    p.sendMessage("Error: Usage invalid.");
                    return true;
                }
            }
        }
        return true;
    }
}
