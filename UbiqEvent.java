package ticketMaker;

import java.util.EventObject;

public class UbiqEvent extends EventObject {
    
    private String localSignal;
    private String localChains;
    private String remoteSignal;
    private String remoteChains;
    private String connectedAP;
    private String localNoiseFloor;
    private String remoteNoiseFloor;
    private String pingTime;
    private String lanSpeed;
    private String complaint;
    private String notes;
    private boolean pwrcy;
    private boolean down;
    private boolean rCable;
    private boolean vMac;
    private boolean connectedDevices;
    private boolean downAtStart;
    
    public UbiqEvent(Object source) {
        super(source);
    }
    
    public UbiqEvent(Object source, String localSignal, String localChains, String remoteSignal, String remoteChains,
                     String connectedAP, String localNoiseFloor, String remoteNoiseFloor, String pingTime, 
                     String lanSpeed, String complaint, String notes, boolean pwrcy, boolean down, boolean rCable, boolean vMac,
                     boolean connectedDevices, boolean downAtStart) {
        super(source);
        this.localSignal = localSignal;
        this.localChains = localChains;
        this.localNoiseFloor = localNoiseFloor;
        this.remoteSignal = remoteSignal;
        this.remoteChains = remoteChains;
        this.remoteNoiseFloor = remoteNoiseFloor;
        this.connectedAP = connectedAP;
        this.pingTime = pingTime;
        this.lanSpeed = lanSpeed;
        this.notes = notes;
        this.complaint = complaint;
        this.pwrcy = pwrcy;
        this.down = down;
        this.rCable = rCable;
        this.vMac = vMac;
        this.connectedDevices = connectedDevices;
        this.downAtStart = downAtStart;
    }
    public String getLocalSignal() {
        return localSignal;
    }
    public String getLocalChains() {
        return localChains;
    }
    public String getRemoteSignal() {
        return remoteSignal;
    }
    public String getRemoteChains() {
        return remoteChains;
    }
    public String getConnectedAP() {
        return connectedAP;
    }
    public String getLocalNoiseFloor() {
        return localNoiseFloor;
    }
    public String getRemoteNoiseFloor() {
        return remoteNoiseFloor;
    }
    public String getPingTime() {
        return pingTime;
    }
    public String getLanSpeed() {
        return lanSpeed;
    }
    public String getComplaint() {
        return complaint;
    }
    public String getNotes() {
        return notes;
    }
    public boolean getPwrCycle() {
        return pwrcy;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isrCable() {
        return rCable;
    }

    public boolean isvMac() {
        return vMac;
    }

    public boolean isConnectedDevices() {
        return connectedDevices;
    }

    public boolean isDownAtStart() {
        return downAtStart;
    }
}
