package ticketMaker;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class TelradForm extends JPanel {

    private JTextField signal;
    private JTextField sinr;
    private JTextField pci;
    private JTextField cell;
    private JTextField cellLocked;
    private JTextField ping;
    private JTextField complaint;
    private JTextArea notes;
    private JScrollPane scroll;
    private JButton submit;
    private JButton newTicket;
    private JCheckBox powerCycle;
    private JCheckBox reseatCable;
    private JCheckBox devicesConnected;
    private JCheckBox verifiedMAC;
    private JCheckBox radioDown;
    private JCheckBox downAtStart;

    private TelradListener formListener;
    private StringListener stringListener;

    public TelradForm() {
        Dimension dim = getPreferredSize();
        dim.width = 720;
        setPreferredSize(dim);

        // instantiate fields
        signal = new JTextField(5);
        sinr = new JTextField(5);
        pci = new JTextField(5);
        cell = new JTextField(5);
        cellLocked = new JTextField(8);
        ping = new JTextField(5);
        complaint = new JTextField(15);
        notes = new JTextArea(10, 42);
        notes.setLineWrap(true);
        scroll = new JScrollPane(scroll);
        submit = new JButton("Submit");
        newTicket = new JButton("New Ticket");
        
        powerCycle = new JCheckBox("Power Cycle");
        reseatCable = new JCheckBox("Reseat Cable");
        devicesConnected = new JCheckBox("Devices Connected");
        verifiedMAC = new JCheckBox("Verified Router MAC");
        radioDown = new JCheckBox("Radio is still down");
        downAtStart = new JCheckBox("Radio was down at the start");

        // action listener for submit button
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sig = signal.getText();
                String sin = sinr.getText();
                String PCI = pci.getText();
                String Cell = cell.getText();
                String isLocked = cellLocked.getText();
                String comp = complaint.getText();
                String nts = notes.getText();
                String png = ping.getText();
                boolean pwr = powerCycle.isSelected();
                boolean rcable = reseatCable.isSelected();
                boolean devConnect = devicesConnected.isSelected();
                boolean vmac = verifiedMAC.isSelected();
                boolean rdown = radioDown.isSelected();
                boolean downStart = downAtStart.isSelected();

                TelradEvent ev = new TelradEvent(this, sig, sin, PCI, Cell, isLocked, comp, nts, png,
                                                 pwr, rdown, rcable, vmac, devConnect, downStart);

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
        
     // action listener for radio is up button
        radioDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean rDown = radioDown.isSelected();
                if(rDown==true) {
                    signal.setEnabled(false);
                    sinr.setEnabled(false);
                    pci.setEnabled(false);
                    cell.setEnabled(false);
                    cellLocked.setEnabled(false);
                    ping.setEnabled(false);
                }
                if(rDown==false) {
                    signal.setEnabled(true);
                    sinr.setEnabled(true);
                    pci.setEnabled(true);
                    cell.setEnabled(true);
                    cellLocked.setEnabled(true);
                    ping.setEnabled(true);
                }
            }
        });

        // setting a border for the overall window
        Border innerBorder = BorderFactory.createTitledBorder("Telrad");
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
        
        
        // next row //
        gc.gridy = 1;
        
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Signal: "), gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(signal, gc);
        
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("SINR: "), gc);
        
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(sinr, gc);
        
        // next row //
        gc.gridy = 2;
        
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("PCI: "), gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(pci, gc);
        
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Cell: "), gc);
        
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(cell, gc);
        
        // next row //
        gc.gridy = 3;
        
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Cell Locked: "), gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(cellLocked, gc);
        
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Ping: "), gc);
        
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(ping, gc);
        
        // next row //
        gc.gridx = 1;
        gc.gridy = 4;
        gc.insets = new Insets(10, 0, 0, 0);
        add(powerCycle, gc);
        
        gc.gridx = 2;
        add(reseatCable, gc);
        
        gc.gridy = 5;
        gc.gridx = 1;
        gc.insets = new Insets(10, 0, 0, 0);
        add(devicesConnected, gc);
        
        gc.gridx = 2;
        add(verifiedMAC, gc);
        
        gc.gridy = 6;
        gc.gridx = 1;
        add(radioDown, gc);
        
        gc.gridx = 2;
        add(downAtStart, gc);
        
        // next row //
        gc.gridy = 7;
        gc.gridheight = 4;
        gc.gridwidth = 15;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("Notes: "), gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(notes, gc);
        
        // next row //
        gc.weighty = 3;
        gc.gridheight = 1;
        gc.gridwidth = 1;
        
        gc.gridy = 13;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.CENTER;
        add(submit, gc);
        
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(newTicket, gc);
    }

    public void setFormListener(TelradListener formListener) {
        this.formListener = formListener;
    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }

}
