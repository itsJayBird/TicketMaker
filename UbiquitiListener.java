package ticketMaker;

import java.util.EventListener;

public interface UbiquitiListener extends EventListener {
    public void FormEventOccurred(UbiqEvent e);
}
