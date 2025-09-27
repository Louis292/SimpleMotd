package fr.Louis292.simpleMotd;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleMotd extends JavaPlugin implements Listener {
    public FileConfiguration config;

    public static String MOTD;

    public static boolean MAX_SLOT_ACTIVE;
    public static int MAX_SLOT;

    public static boolean MIN_SLOT_ACTIVE;
    public static int MIN_SLOT;

    @Override
    public void onEnable() {
        getLogger().info("Starting...");

        this.saveConfig();
        config = getConfig();

        MOTD = config.getString("server_motd");

        MOTD = ChatColor.translateAlternateColorCodes('&', MOTD);

        MAX_SLOT_ACTIVE = config.getBoolean("max_slot.active");
        MAX_SLOT = config.getInt("max_slot.value");

        MIN_SLOT_ACTIVE = config.getBoolean("min_slot.active");
        MIN_SLOT = config.getInt("min_slot.value");

        getServer().getPluginManager().registerEvents(this, this);

        getLogger().info("The plugin was been start !");
    }

    @EventHandler
    public void onPing(ServerListPingEvent event) {
        event.setMotd(MOTD);

        if (MAX_SLOT_ACTIVE) {
            event.setMaxPlayers(MAX_SLOT);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
