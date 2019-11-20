package fsmc.fsmc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.server.ServerListPingEvent;

public final class Events implements Listener {

    private FSMC plugin;
    public Events(FSMC plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if(plugin.getConfig().getString("user.nick." + e.getPlayer().getName()) == null) {
            return;
        } else {
            e.getPlayer().setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("user.nick." + e.getPlayer().getName())));
        }
        if(plugin.getConfig().getString("join-message").equalsIgnoreCase("true")) {
            String prefix = plugin.getConfig().getString("join-prefix");
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', prefix)  + " " + e.getPlayer().getName());
        }
        else {
            return;
        }
    }
    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent e) {
        if(plugin.getConfig().getString("leave-message").equalsIgnoreCase("true")) {
            String prefix = plugin.getConfig().getString("leave-prefix");
            e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', prefix) + " " + e.getPlayer().getName());
        }
        else {
            return;
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(!p.hasPermission("fsmc.break")) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "You do not have permission to break blocks!");
        }
        else {
            return;
        }
    }
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        World w = Bukkit.getServer().getWorld(plugin.getConfig().getString("spawn.world"));
        double x = plugin.getConfig().getDouble("spawn.x");
        double y = plugin.getConfig().getDouble("spawn.y");
        double z = plugin.getConfig().getDouble("spawn.z");
        Float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");
        Float pitch = (float) plugin.getConfig().getDouble("spawn.pitch");
        Location loc = new Location(w, x, y, z, yaw, pitch);
        e.setRespawnLocation(loc);
    }
    @EventHandler
    public void onServerPing(ServerListPingEvent e) {
        if(plugin.getConfig().getString("motd.message") == null) {
            return;
        }
        String motd = plugin.getConfig().getString("motd.message");
        motd = motd.replaceAll("&", "\u00A7");
        e.setMotd(motd);
    }
}
