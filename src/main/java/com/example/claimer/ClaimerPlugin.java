package com.example.claimer;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class ClaimerPlugin extends JavaPlugin {
    private ClaimManager claimManager;
    private AdminGui adminGui;

    @Override
    public void onEnable() {
        this.claimManager = new ClaimManager(this);
        this.claimManager.load();
        this.adminGui = new AdminGui(this, claimManager);

        Bukkit.getPluginManager().registerEvents(new ClaimProtectionListener(this, claimManager), this);
        Bukkit.getPluginManager().registerEvents(adminGui, this);

        PluginCommand command = getCommand("claimadmin");
        if (command != null) {
            ClaimAdminCommand executor = new ClaimAdminCommand(this, claimManager, adminGui);
            command.setExecutor(executor);
            command.setTabCompleter(executor);
        }

        getLogger().info("Claimer enabled with " + claimManager.getClaims().size() + " loaded claims.");
    }

    @Override
    public void onDisable() {
        if (claimManager != null) {
            claimManager.save();
        }
    }

    public ClaimManager getClaimManager() {
        return claimManager;
    }

    public AdminGui getAdminGui() {
        return adminGui;
    }
}
