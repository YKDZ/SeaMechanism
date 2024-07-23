package cn.encmys.ykdz.forest.seamechanism.profile;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Profile {
    private final UUID uuid;
    private boolean isInVibrioRange = false;
    private boolean isInVibrioInfect = false;

    public Profile(@NotNull UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return uuid;
    }

    public boolean isInVibrioRange() {
        return isInVibrioRange;
    }

    public void setInVibrioRange(boolean inVibrioRange) {
        isInVibrioRange = inVibrioRange;
    }

    public boolean isInVibrioInfect() {
        return isInVibrioInfect;
    }

    public void setInVibrioInfect(boolean inVibrioInfect) {
        isInVibrioInfect = inVibrioInfect;
    }
}
