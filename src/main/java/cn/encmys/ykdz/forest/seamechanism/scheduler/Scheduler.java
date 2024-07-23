package cn.encmys.ykdz.forest.seamechanism.scheduler;

import cn.encmys.ykdz.forest.seamechanism.SeaMechanism;
import cn.encmys.ykdz.forest.seamechanism.profile.Profile;
import cn.encmys.ykdz.forest.seamechanism.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class Scheduler {
    public Scheduler() {
        runVibrioRangerTimer();
    }

    public void runVibrioRangerTimer() {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimer(SeaMechanism.INSTANCE, (task) -> {
            for (Profile profile : SeaMechanism.PROFILE_FACTORY.getProfiles().values()) {
                if (profile.isInVibrioRange()) {
                    Player player = Bukkit.getPlayer(profile.getUUID());
                    if (player == null) {
                        return;
                    }
                    PlayerUtils.modifyHunger(player, -1f);
                    // TODO 粒子效果
                }
            }
        }, 0L, 40L);
    }
}
