package de.modstrype.Fixer.Main;

import de.modstrype.Fixer.Listener.BugFixer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main plugin;

    public void onEnable() {
        plugin = this;
        PluginManager pl = Bukkit.getPluginManager();
        pl.registerEvents(new BugFixer(), this);
        getPlugin().getLogger().info("Das Plugin wurde erfolgreich geladen!");
    }

    public static Main getPlugin() {
        return plugin;
    }

}
