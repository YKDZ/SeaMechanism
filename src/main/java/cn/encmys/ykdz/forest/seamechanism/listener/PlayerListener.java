package cn.encmys.ykdz.forest.seamechanism.listener;

import cn.encmys.ykdz.forest.seamechanism.SeaMechanism;
import cn.encmys.ykdz.forest.seamechanism.profile.Profile;
import cn.encmys.ykdz.forest.seamechanism.utils.PlayerUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        SeaMechanism.PROFILE_FACTORY.buildProfile(player);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        SeaMechanism.PROFILE_FACTORY.removeProfile(player);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Location from = event.getFrom();
        Location to = event.getTo();
        if (to == null || to.getWorld() == null || from.distance(to) <= 0) {
            return;
        }
        // 判断玩家是否在海洋生物群系中
        // 且所处的位置是水
        Profile profile = SeaMechanism.PROFILE_FACTORY.buildProfile(event.getPlayer());
        if (PlayerUtils.ifInOceanBiome(to) && PlayerUtils.isWater(to)) {
            double distanceToSeaLevel = to.getWorld().getSeaLevel() - to.getY();
            profile.setInVibrioRange(
                    distanceToSeaLevel < 5 && distanceToSeaLevel > 0
            );
        }
    }
}
