package cn.encmys.ykdz.forest.seamechanism.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class PlayerUtils {
    public static final Set<Biome> OCEAN_BIOMES = new HashSet<>() {{
        add(Biome.COLD_OCEAN);
        add(Biome.DEEP_COLD_OCEAN);
        add(Biome.DEEP_FROZEN_OCEAN);
        add(Biome.DEEP_LUKEWARM_OCEAN);
        add(Biome.DEEP_OCEAN);
        add(Biome.FROZEN_OCEAN);
        add(Biome.LUKEWARM_OCEAN);
        add(Biome.OCEAN);
        add(Biome.WARM_OCEAN);
        add(Biome.RIVER);
    }};
    public static final Set<Material> WATER_MATERIAL = new HashSet<>() {{
        add(Material.WATER);
    }};

    public static boolean ifInOceanBiome(Location loc) {
        Block block = loc.getBlock();
        Biome biome = block.getBiome();
        return OCEAN_BIOMES.contains(biome);
    }

    public static boolean isWater(Location loc) {
        Block block = loc.getBlock();
        Material material = block.getType();
        return WATER_MATERIAL.contains(material);
    }

    public static void modifyHunger(Player player, float amount) {
        int currentFoodLevel = player.getFoodLevel();
        float currentSaturation = player.getSaturation();
        if (amount > 0) {
            float need = 20 - currentFoodLevel;
            float remain = amount - need;
            if (need > amount) {
                player.setFoodLevel((int) (currentFoodLevel + amount));
                return;
            } else {
                player.setFoodLevel(20);
            }
            if (remain > 0) {
                player.setSaturation(remain + currentSaturation);
            }
        } else {
            if (-amount > currentSaturation) {
                player.setSaturation(0f);
                player.setFoodLevel((int) (currentFoodLevel + (currentSaturation + amount)));
            } else {
                player.setSaturation(currentSaturation + amount);
            }
        }
    }
}
