package fsmc.fsmc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Spawn implements CommandExecutor {
    private final FSMC plugin;
    public Spawn(FSMC plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] strings) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("setspawn")) {
            plugin.getConfig().set("spawn.world", p.getLocation().getWorld().getName());
            plugin.getConfig().set("spawn.x", p.getLocation().getX());
            plugin.getConfig().set("spawn.y", p.getLocation().getY());
            plugin.getConfig().set("spawn.z", p.getLocation().getZ());
            plugin.getConfig().set("spawn.pitch", p.getLocation().getPitch());
            plugin.getConfig().set("spawn.yaw", p.getLocation().getPitch());
            plugin.saveConfig();
            p.sendMessage(ChatColor.GREEN + "Spawn location has been set.");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("spawn")) {
            if (plugin.getConfig().getConfigurationSection("spawn") == null) {
                p.sendMessage(ChatColor.RED + "The spawn has not yet been set!");
                return true;
            }
            World w = Bukkit.getServer().getWorld(plugin.getConfig().getString("spawn.world"));
            double x = plugin.getConfig().getDouble("spawn.x");
            double y = plugin.getConfig().getDouble("spawn.y");
            double z = plugin.getConfig().getDouble("spawn.z");
            Float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");
            Float pitch = (float) plugin.getConfig().getDouble("spawn.pitch");
            p.teleport(new Location(w, x, y, z, yaw, pitch));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("spawn-text")));
            return true;
        }
        return true;
    }
}
