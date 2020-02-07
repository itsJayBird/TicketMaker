package ticketMaker;

import java.util.EventObject;

public class TelradEvent extends EventObject {
    
    private String signal;
    private String sinr;
    private String pci;
    private String cell;
    private String cellLocked;
    private String complaint;
    private String notes;
    private String ping;
    private boolean pwrcy;
    private boolean down;
    private boolean rCable;
    private boolean vMac;
    private boolean connectedDevices;
    private boolean downAtStart;
    
    public TelradEvent(Object source) {
        super(source);
    }
    public TelradEvent(Object source, String signal, String sinr, String pci, String cell, 
            String cellLocked, String complaint, String notes, String ping, boolean pwrcy,
            boolean down, boolean rcable, boolean vmac, boolean connectedDev, boolean dwnAtStart) {
        super(source);
        this.signal = signal;
        this.sinr = sinr;
        this.pci = pci;
        this.cell = cell;
        this.cellLocked = cellLocked;
        this.complaint = complaint;
        this.notes = notes;
        this.ping = ping;
        this.pwrcy = pwrcy;
        this.down = down;
        this.rCable = rcable;
        this.vMac = vmac;
        this.connectedDevices = connectedDev;
        this.downAtStart = dwnAtStart;
    }
    public String getSignal() {
        return signal;
    }
    public String getSinr() {
        return sinr;
    }
    public String getPci() {
        return pci;
    }
    public String getCell() {
        return cell;
    }
    public String getCellLocked() {
        return cellLocked;
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
