package com.visy.visylogger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class Main extends JavaPlugin implements Listener {
    private FileConfiguration config;
    private final Map<UUID, PlayerData> playerDataMap = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("visyLogger включен для Paper 1.21.3!");
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Material blockType = event.getBlock().getType();
        List<String> trackedItems = config.getStringList("tracked-items");

        if (!trackedItems.contains(blockType.toString())) return;

        UUID playerId = player.getUniqueId();
        PlayerData playerData = playerDataMap.computeIfAbsent(playerId, k -> new PlayerData());
        playerData.addItem(blockType);

        int maxItems = config.getInt("limits.max-items");
        int timeInterval = config.getInt("limits.time-interval");

        if (playerData.getCount(blockType) >= maxItems && 
            playerData.getTimeSinceFirstMine(blockType) <= timeInterval) {
            notifyOperators(player.getName(), blockType.toString(), 
                        playerData.getCount(blockType), timeInterval);
            playerData.reset(blockType);
        }
    }

    private void notifyOperators(String nick, String item, int count, int time) {
        String message = config.getString("messages.alert")
                .replace("{nick}", nick)
                .replace("{item}", item)
                .replace("{count}", String.valueOf(count))
                .replace("{time}", String.valueOf(time));

        Bukkit.getOnlinePlayers().stream()
                .filter(Player::isOp)
                .forEach(op -> op.sendMessage(message));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("logger")) return false;

        if (!sender.hasPermission("visylogger.stats")) {
            sender.sendMessage(config.getString("messages.no-permission"));
            return true;
        }

        if (args.length == 0 && sender instanceof Player) {
            showStats(sender, ((Player) sender).getName());
        } else if (args.length == 1) {
            showStats(sender, args[0]);
        } else {
            sender.sendMessage("§cИспользуйте: /logger [ник]");
        }
        return true;
    }

    private void showStats(CommandSender sender, String playerName) {
        Player target = Bukkit.getPlayer(playerName);
        if (target == null) {
            sender.sendMessage(config.getString("messages.no-data")
                    .replace("{nick}", playerName));
            return;
        }

        PlayerData playerData = playerDataMap.get(target.getUniqueId());
        if (playerData == null) {
            sender.sendMessage(config.getString("messages.no-data")
                    .replace("{nick}", playerName));
            return;
        }

        for (Material material : Material.values()) {
            int count = playerData.getCount(material);
            if (count > 0) {
                long time = playerData.getTimeSinceFirstMine(material);
                sender.sendMessage(config.getString("messages.stats")
                        .replace("{nick}", playerName)
                        .replace("{item}", material.toString())
                        .replace("{count}", String.valueOf(count))
                        .replace("{time}", String.valueOf(time)));
            }
        }
    }
}