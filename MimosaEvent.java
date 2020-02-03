package ticketMaker;

import java.util.EventObject;

public class MimosaEvent extends EventObject {
    
    private String signal;
    private String connectedAP;
    private String lanSpeed;
    private String complaint;
    private String notes;
    private String ping;
    
    public MimosaEvent(Object source) {
        super(source);
    }
    public MimosaEvent(Object source, String signal, String connectedAP, String lanSpeed,
                       String complaint, String notes, String ping) {
        super(source);
        this.signal = signal;
        this.connectedAP = connectedAP;
        this.lanSpeed = lanSpeed;
        this.complaint = complaint;
        this.notes = notes;
        this.ping = ping;
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
}
