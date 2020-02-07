package ticketMaker;

import java.util.EventObject;

public class MimosaEvent extends EventObject {
    
    private String signal;
    private String connectedAP;
    private String lanSpeed;
    private String complaint;
    private String notes;
    private String ping;
    private boolean pwrcy;
    private boolean down;
    private boolean rCable;
    private boolean vMac;
    private boolean connectedDevices;
    private boolean downAtStart;
    
    public MimosaEvent(Object source) {
        super(source);
    }
    public MimosaEvent(Object source, String signal, String connectedAP, String lanSpeed,
                       String complaint, String notes, String ping, boolean pwrcy, boolean down,
                       boolean rcable, boolean vmac, boolean connectedDev, boolean downStart) {
        super(source);
        this.signal = signal;
        this.connectedAP = connectedAP;
        this.lanSpeed = lanSpeed;
        this.complaint = complaint;
        this.notes = notes;
        this.ping = ping;
        this.pwrcy = pwrcy;
        this.down = down;
        this.rCable = rcable;
        this.vMac = vmac;
        this.connectedDevices = connectedDev;
        this.downAtStart = downStart;
    }
    
    public String getSignal() {
        return signal;
    }
    public String getConnectedAP() {
        return connectedAP;
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
    public String getPing() {
        return ping;
    }
    public boolean isPwrcy() {
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
