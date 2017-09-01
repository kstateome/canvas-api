package edu.ksu.canvas.model.assignment;

import java.io.Serializable;
import java.util.Date;

public class LockInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String assetString;
    private Date unlockAt;
    private Date lockAt;
    private Object contextModule; //TODO: what is this object? a "module" as in this? https://canvas.instructure.com/doc/api/modules.html#Module
    private Boolean manuallyLocked;

    public String getAssetString() {
        return assetString;
    }

    public void setAssetString(String assetString) {
        this.assetString = assetString;
    }

    public Date getUnlockAt() {
        return unlockAt;
    }

    public void setUnlockAt(Date unlockAt) {
        this.unlockAt = unlockAt;
    }

    public Date getLockAt() {
        return lockAt;
    }

    public void setLockAt(Date lockAt) {
        this.lockAt = lockAt;
    }

    public Object getContextModule() {
        return contextModule;
    }

    public void setContextModule(Object contextModule) {
        this.contextModule = contextModule;
    }

    public Boolean getManuallyLocked() {
        return manuallyLocked;
    }

    public void setManuallyLocked(Boolean manuallyLocked) {
        this.manuallyLocked = manuallyLocked;
    }
}
