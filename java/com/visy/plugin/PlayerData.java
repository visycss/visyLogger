package com.visy.visylogger;

import org.bukkit.Material;
import java.util.HashMap;
import java.util.Map;

public class PlayerData {
    private final Map<Material, Long> firstMineTime = new HashMap<>();
    private final Map<Material, Integer> itemCount = new HashMap<>();

    public void addItem(Material material) {
        if (!firstMineTime.containsKey(material)) {
            firstMineTime.put(material, System.currentTimeMillis());
        }
        itemCount.put(material, itemCount.getOrDefault(material, 0) + 1);
    }

    public int getCount(Material material) {
        return itemCount.getOrDefault(material, 0);
    }

    public long getTimeSinceFirstMine(Material material) {
        if (!firstMineTime.containsKey(material)) return 0;
        return (System.currentTimeMillis() - firstMineTime.get(material)) / 1000;
    }

    public void reset(Material material) {
        firstMineTime.remove(material);
        itemCount.remove(material);
    }
}
