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

public class CambiumForm extends JPanel {
    private JTextField signal;
    private JTextField connectedAP;
    private JTextField lanSpeed;
    private JTextField ping;
    private JTextField sinr;
    private JTextField complaint;
    private JTextArea notes;
    private JScrollPane scroll;
    private JButton submit;
    private JButton newTicket;

    private CambiumListener formListener;
    private StringListener stringListener;

    public CambiumForm() {
        Dimension dim = getPreferredSize();
        dim.width = 600;
        setPreferredSize(dim);

        // instantiate fields
        signal = new JTextField(5);
        connectedAP = new JTextField(10);
        lanSpeed = new JTextField(8);
        ping = new JTextField(5);
        complaint = new JTextField(15);
        sinr = new JTextField(5);
        notes = new JTextArea(10, 20);
        scroll = new JScrollPane(notes);
        submit = new JButton("Submit");
        newTicket = new JButton("New Ticket");
        
        // action listener for submit button
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sig = signal.getText();
                String comp = complaint.getText();
                String lan = lanSpeed.getText();
                String ap = connectedAP.getText();
                String nts = notes.getText();
                String png = ping.getText();
                String sin = sinr.getText();

                CambiumEvent ev = new CambiumEvent(this, sig, ap, lan, comp, nts, png, sin);

                if(formListener != null) {
                    formListener.FormEventOccurred(ev);
                }
            }
        });

        // action listener for new ticket button
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
        Border innerBorder = BorderFactory.createTitledBorder("Cambium");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        // set components
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
        add(new JLabel("Complaint: "), gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(complaint, gc);
        
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Signal: "), gc);
        
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(signal, gc);
        
        // next row //
        gc.gridy = 1;
        
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("SINR: "), gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(sinr, gc);
        
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Color Code/AP: "), gc);
        
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(connectedAP, gc);
        
        // next row //
        gc.gridy = 2;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Ping: "), gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(ping, gc);
        
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("LAN Speed: "), gc);
        
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(lanSpeed, gc);
        
        // next row //
        gc.gridy = 3;
        gc.gridheight = 4;
        gc.gridwidth = 15;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("Notes: "), gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(scroll, gc);
        
        // next row //
        gc.weighty = 3;
        gc.gridheight = 1;
        gc.gridwidth = 1;
        
        gc.gridy = 8;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.CENTER;
        add(submit, gc);
        
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(newTicket, gc);
    }

    public void setFormListener(CambiumListener formListener) {
        this.formListener = formListener;
    }
    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }
}