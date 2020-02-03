package ticketMaker;

import java.util.EventObject;

public class SelectionEvent extends EventObject {
    private String radioTech;
    
    public SelectionEvent(Object source) {
        super(source);
    }
    public SelectionEvent(Object source, String radioTech) {
        super(source);
        this.radioTech = radioTech;
    }
    public String getRadioTech() {
        return radioTech;
    }
}
