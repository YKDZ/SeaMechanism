package cn.encmys.ykdz.forest.seamechanism;

import cn.encmys.ykdz.forest.seamechanism.listener.PlayerListener;
import cn.encmys.ykdz.forest.seamechanism.profile.factory.ProfileFactory;
import cn.encmys.ykdz.forest.seamechanism.scheduler.Scheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class SeaMechanism extends JavaPlugin {
    public static Plugin INSTANCE;
    public static ProfileFactory PROFILE_FACTORY;

    @Override
    public void onLoad() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        PROFILE_FACTORY = new ProfileFactory();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), INSTANCE);

        new Scheduler();
    }

    @Override
    public void onDisable() {

    }
}
