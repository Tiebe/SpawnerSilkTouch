package com.tiebe.plugins.spawnersilktouch;

import org.bukkit.plugin.java.JavaPlugin;

public final class SpawnerSilkTouch extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new breakListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
