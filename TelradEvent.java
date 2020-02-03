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
    
    public TelradEvent(Object source) {
        super(source);
    }
    public TelradEvent(Object source, String signal, String sinr, String pci, String cell, 
            String cellLocked, String complaint, String notes, String ping) {
        super(source);
        this.signal = signal;
        this.sinr = sinr;
        this.pci = pci;
        this.cell = cell;
        this.cellLocked = cellLocked;
        this.complaint = complaint;
        this.notes = notes;
        this.ping = ping;
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
    
}
