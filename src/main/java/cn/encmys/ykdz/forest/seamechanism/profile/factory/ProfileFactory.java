package cn.encmys.ykdz.forest.seamechanism.profile.factory;

import cn.encmys.ykdz.forest.seamechanism.profile.Profile;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileFactory {
    private final Map<UUID, Profile> profiles = new HashMap<>();

    public Profile buildProfile(Player player) {
        UUID uuid = player.getUniqueId();
        Profile profile = new Profile(uuid);
        profiles.put(uuid, profile);
        return profile;
    }

    public void removeProfile(Player player) {
       profiles.remove(player.getUniqueId());
    }

    public Profile getProfile(Player player) {
        return profiles.getOrDefault(player.getUniqueId(), buildProfile(player));
    }

    public Map<UUID, Profile> getProfiles() {
        return Collections.unmodifiableMap(profiles);
    }
}
