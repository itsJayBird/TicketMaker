package ticketMaster;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener {

    private JButton ubnt;
    private JButton telrad;
    private JButton mimosa;
    private JButton cambium;
    private StringListener stringListener;

    public Menu() {
        ubnt = new JButton("Ubiquiti");
        telrad = new JButton("Telrad");
        mimosa = new JButton("Mimosa");
        cambium = new JButton("Cambium");

        ubnt.addActionListener(this);
        telrad.addActionListener(this);
        mimosa.addActionListener(this);
        cambium.addActionListener(this);

        setLayout(new FlowLayout());
        add(ubnt);
        add(telrad);
        add(mimosa);
        add(cambium);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();

        if(stringListener != null) {
            if(clicked == ubnt) {
                stringListener.textEmitted("ubnt");
            } else if(clicked == telrad) {
                stringListener.textEmitted("telrad");
            } else if(clicked == mimosa) {
                stringListener.textEmitted("mimosa");
            } else if(clicked == cambium) {
                stringListener.textEmitted("cambium");
            }
        }
    }
    public void setStringListener(StringListener listener) {
        this.stringListener = listener;
    }
}
