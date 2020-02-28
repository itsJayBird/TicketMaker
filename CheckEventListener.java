package ticketMaster;

import java.util.EventListener;

public interface CheckEventListener extends EventListener {
    public void checkEventOccured(CheckEvent e);
}
