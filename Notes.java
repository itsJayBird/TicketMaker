package ticketMaster;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class Notes extends JPanel {
    private JTextArea notes;
    private JScrollPane scroll;
    private StringListener stringListener;
    
    public Notes(){
        notes = new JTextArea();
        notes.setLineWrap(true);
        notes.setEditable(true);
        scroll = new JScrollPane(notes);
        
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        
        Border innerBorder = BorderFactory.createTitledBorder("Notes");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5 );
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }
    public void setStringListener(StringListener listener) {
        this.stringListener = listener;
        stringListener.textEmitted(notes.getText());
    }
}
