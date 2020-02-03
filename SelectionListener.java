package ticketMaker;

import java.util.EventListener;

public interface SelectionListener extends EventListener{
    public void selectionEventOccured(SelectionEvent e);
}
