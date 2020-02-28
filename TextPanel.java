package ticketMaster;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPanel extends JPanel {
    private JTextArea textArea;
    
    public TextPanel() {
        textArea = new JTextArea();
        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        textArea.setLineWrap(true);
    }
    
    public void appendText(String text) {
        textArea.append(text);
    }
    
    public String getText() {
        return textArea.getText();
    }
    
}
