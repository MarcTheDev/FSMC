package fsmc.fsmc;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Motd implements CommandExecutor {
    private final FSMC plugin;

    public Motd(FSMC plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("motd")) {
            Player p = (Player) sender;
            if(args.length == 0) {
                if(plugin.getConfig().getString("motd.message") == null) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aFSMC&8] &rThere is no MOTD set."));
                    return true;
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aFSMC&8] &r The MOTD is currently: " + plugin.getConfig().getString("motd.message")));
                return true;
                }
            }
            if(args[0].equalsIgnoreCase("set")) {
                if(args.length == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aFSMC&8] &cUsage: /motd set <motd>"));
                    return true;
                }
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    str.append(args[i] + " ");
                }
                String motd = str.toString();
                motd = motd.replace("set", "");
                plugin.getConfig().set("motd.message", motd);
                plugin.saveConfig();
                String system = plugin.getConfig().getString("motd.message");
                system = system.replaceAll("&", "§");
                sender.sendMessage(ChatColor.GREEN + "MOTD set to:" + system);
                return true;

            }
            else {
                p.sendMessage(ChatColor.RED + "Error: Invalid usage.");
                return true;
            }
        }
        return true;
    }
}
