package ticketMaker;

import java.util.EventObject;

public class CambiumEvent extends EventObject {
    private String signal;
    private String connectedAP;
    private String lanSpeed;
    private String complaint;
    private String notes;
    private String ping;
    private String sinr;
    
    public CambiumEvent(Object source) {
        super(source);
    }
    public CambiumEvent(Object source, String signal, String connectedAP, String lanSpeed,
                       String complaint, String notes, String ping, String sinr) {
        super(source);
        this.signal = signal;
        this.connectedAP = connectedAP;
        this.lanSpeed = lanSpeed;
        this.complaint = complaint;
        this.notes = notes;
        this.ping = ping;
        this.sinr = sinr;
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
    public String getSinr() {
        return sinr;
    }
}
