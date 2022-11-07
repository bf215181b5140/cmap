package com.changemyavatarparams.cmap.object;

import java.time.LocalDateTime;
import java.util.HashSet;

public class AvatarMeta {
    private boolean online = false;
    private boolean afk;
    private boolean sleeping;
    private boolean muteSelf;
    private boolean vrMode;
    private boolean supportedAvatar = true;
    private LocalDateTime lastPulse = java.time.LocalDateTime.now().minusDays(1);
    private HashSet<AvatarParameter> avatarParameters = new HashSet<>();

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean currentStatus) {
        this.online = currentStatus;
        if(!currentStatus){
            this.afk = false;
            this.sleeping = false;
            this.muteSelf = true;
            this.vrMode = false;
        }
    }

    public boolean isAfk() {
        return afk;
    }

    public void setAfk(boolean afk) {
        this.afk = afk;
    }

    public boolean isSleeping() {
        return sleeping;
    }

    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }

    public boolean isMuteSelf() {
        return muteSelf;
    }

    public void setMuteSelf(boolean muteSelf) {
        this.muteSelf = muteSelf;
    }

    public boolean isInVrMode() {
        return vrMode;
    }

    public void setVrMode(boolean vrMode) {
        this.vrMode = vrMode;
    }

    public boolean isSupportedAvatar() {
        return supportedAvatar;
    }

    public void setSupportedAvatar(boolean supportedAvatar) {
        this.supportedAvatar = supportedAvatar;
    }

    public LocalDateTime getLastPulse() {
        return lastPulse;
    }

    public void setLastPulse(LocalDateTime lastPulse) {
        this.lastPulse = lastPulse;
    }

    public HashSet<AvatarParameter> getAvatarParameters() {
        return avatarParameters;
    }

    public void setAvatarParameters(HashSet<AvatarParameter> avatarParameters) {
        this.avatarParameters = avatarParameters;
    }
}
