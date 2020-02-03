package ticketMaker;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class UbiquitiForm extends JPanel {
    private JTextField localSignal;
    private JTextField localChains;
    private JTextField localNoiseFloor;
    private JTextField remoteNoiseFloor;
    private JTextField connectedAP;
    private JTextField remoteSignal;
    private JTextField remoteChains;
    private JTextField lanSpeed;
    private JTextField pingTime;
    private JTextField complaint;
    private JTextArea notes;
    private JButton submitBtn;
    private JButton newTicket;
    private JScrollPane scroll;
    
    private UbiquitiListener ubiquitiListener;
    private StringListener stringListener;
    
    public UbiquitiForm() {
        // set dimension
        Dimension dim = getPreferredSize();
        dim.width = 600;
        setPreferredSize(dim);
        
        // instantiate the needed text fields
        localSignal = new JTextField(5);
        localChains = new JTextField(5);
        connectedAP = new JTextField(10);
        remoteSignal = new JTextField(5);
        remoteChains = new JTextField(5);
        localNoiseFloor = new JTextField(5);
        remoteNoiseFloor = new JTextField(5);
        lanSpeed = new JTextField(10);
        pingTime = new JTextField(5);
        complaint = new JTextField(20);
        notes = new JTextArea(10, 20);
        scroll = new JScrollPane(notes);
        submitBtn = new JButton("Submit");
        newTicket = new JButton("New Ticket");
        
        // set action listener for button to grab information from fields
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String locSignal = localSignal.getText();
                String locChains = localChains.getText();
                String localNF = localNoiseFloor.getText();
                String ap = connectedAP.getText();
                String remSignal = remoteSignal.getText();
                String remChains = remoteChains.getText();
                String remoteNF = remoteNoiseFloor.getText();
                String LAN = lanSpeed.getText();
                String ping = pingTime.getText();
                String comp = complaint.getText();
                String nts = notes.getText();
                
                // uses the ubiquiti event class to pass the information to main frame
                UbiqEvent ev = new UbiqEvent(this, locSignal, locChains, remSignal, remChains,
                                             ap, localNF, remoteNF, ping, LAN, comp, nts);
                
                if(ubiquitiListener != null) {
                    ubiquitiListener.FormEventOccurred(ev);
                }
            }
        });
        
        // set action listener for new ticket button
        newTicket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton clicked = (JButton)e.getSource();
                if(clicked == newTicket) {
                    if(stringListener != null) {
                        stringListener.textEmitted("newTicket");
                    }
                }
            }
        });
        
        // setting a border for the overall window
        Border innerBorder = BorderFactory.createTitledBorder("Ubiquiti");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        // add elements to the panel
        layoutComponents();
    }
    
    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        // first row // 
        gc.gridy = 0;
        
        gc.weightx = 0.1;
        gc.weighty = 0.1;
        
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Connected AP: "), gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(connectedAP, gc);
        
        // next row //
        gc.gridy = 1; 
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Complaint: "), gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(complaint, gc);
        
        // next row //
        gc.gridy = 2;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Local Signal: "), gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(localSignal, gc);
        
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Remote Signal: "), gc);
        
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(remoteSignal, gc);
        
        // next row //
        gc.gridy = 3;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Local Chains: "), gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(localChains, gc);
        
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Remote Chains: "), gc);
        
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(remoteChains, gc);
        
        // next row //
        gc.gridy = 4;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Local Noise Floor: "), gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(localNoiseFloor, gc);
        
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Remote Noise Floor: "), gc);
        
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(remoteNoiseFloor, gc);
        
        // next row //
        gc.gridy = 5;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("LAN Speed: "), gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(lanSpeed, gc);
        
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Ping: "), gc);
        
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(pingTime, gc);
        
        // next row//
        gc.gridy = 6;
        gc.gridheight = 4;
        gc.gridwidth = 15;
        gc.gridx = 0;
        add(new JLabel("Notes: "), gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(scroll, gc);
        
        // next row //
        gc.weighty = 3;
        gc.gridy = 10;
        gc.gridheight = 1;
        gc.gridwidth = 1;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.CENTER;
        add(submitBtn, gc);
        
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(newTicket, gc);
    }

    public void setUbiquitiListener(UbiquitiListener ubiquitiListener) {
        this.ubiquitiListener = ubiquitiListener;
    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }
    
}
