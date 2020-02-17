package ticketMaster;

import java.util.EventObject;

public class FormEvent extends EventObject {
    private String complaint;
    private String ap;
    private String signal0;
    private String signal1;
    private String chain0;
    private String chain1;
    private String sinr0;
    private String sinr1;
    private String lan;
    private String ping;
    
    public FormEvent(Object source) {
        super(source);
    };
    
    public FormEvent(Object source, String complaint, String ap, String loSignal, String reSignal, 
                     String loChains, String reChains, String loNoise, String reNoise, String lan,
                     String ping) {
        super(source);
        this.complaint = complaint;
        this.ap = ap;
        this.signal0 = loSignal;
        this.signal1 = reSignal;
        this.chain0 = loChains;
        this.chain1 = reChains;
        this.sinr0 = loNoise;
        this.sinr1 = reNoise;
        this.lan = lan;
        this.ping = ping;
    }

    public String getComplaint() {
        return complaint;
    }

    public String getAp() {
        return ap;
    }

    public String getSignal0() {
        return signal0;
    }

    public String getSignal1() {
        return signal1;
    }

    public String getChain0() {
        return chain0;
    }

    public String getChain1() {
        return chain1;
    }

    public String getSinr0() {
        return sinr0;
    }

    public String getSinr1() {
        return sinr1;
    }

    public String getLan() {
        return lan;
    }

    public String getPing() {
        return ping;
    }
}
