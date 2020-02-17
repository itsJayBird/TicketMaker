package ticketMaster;

import java.util.EventObject;

public class CheckEvent extends EventObject {
    
    private boolean radioDownAtStart;
    private boolean powerCycleRadio;
    private boolean powerCycleRouter;
    private boolean custRouter;
    private boolean reseatCable;
    private boolean verifiedCable;
    private boolean verifiedPower;
    private boolean verifiedMAC;
    private boolean movedAP;
    private boolean devicesConnected;
    private boolean upgradeRouter;
    private boolean upgradeRadio;
    private boolean radioStillDown;
    
    public CheckEvent(Object source) {
        super(source);
    }
    public CheckEvent(Object source, boolean downStart, boolean powerRadio, boolean powerRouter, boolean cxRouter,
                      boolean reCable, boolean verCable, boolean verPower, boolean verMAC, boolean mAP, boolean devConnected,
                      boolean upRouter, boolean upRadio, boolean stillDown) {
        super(source);
        this.radioDownAtStart = downStart;
        this.powerCycleRadio = powerRadio;
        this.powerCycleRouter = powerRouter;
        this.custRouter = cxRouter;
        this.reseatCable = reCable;
        this.verifiedCable = verCable;
        this.verifiedPower = verPower;
        this.verifiedMAC = verMAC;
        this.movedAP = mAP;
        this.devicesConnected = devConnected;
        this.upgradeRouter = upRouter;
        this.upgradeRadio = upRadio;
        this.radioStillDown = stillDown;
    }
    public boolean isRadioDownAtStart() {
        return radioDownAtStart;
    }
    public boolean isPowerCycleRadio() {
        return powerCycleRadio;
    }
    public boolean isPowerCycleRouter() {
        return powerCycleRouter;
    }
    public boolean isCustRouter() {
        return custRouter;
    }
    public boolean isReseatCable() {
        return reseatCable;
    }
    public boolean isVerifiedCable() {
        return verifiedCable;
    }
    public boolean isVerifiedPower() {
        return verifiedPower;
    }
    public boolean isVerifiedMAC() {
        return verifiedMAC;
    }
    public boolean isMovedAP() {
        return movedAP;
    }
    public boolean isDevicesConnected() {
        return devicesConnected;
    }
    public boolean isUpgradeRouter() {
        return upgradeRouter;
    }
    public boolean isUpgradeRadio() {
        return upgradeRadio;
    }
    public boolean isRadioStillDown() {
        return radioStillDown;
    }
}
