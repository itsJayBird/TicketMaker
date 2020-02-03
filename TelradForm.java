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

    private TelradListener formListener;
    private StringListener stringListener;

    public TelradForm() {
        Dimension dim = getPreferredSize();
        dim.width = 600;
        setPreferredSize(dim);

        // instantiate fields
        signal = new JTextField(5);
        sinr = new JTextField(5);
        pci = new JTextField(5);
        cell = new JTextField(5);
        cellLocked = new JTextField(8);
        ping = new JTextField(5);
        complaint = new JTextField(15);
        notes = new JTextArea(10, 20);
        scroll = new JScrollPane(notes);
        submit = new JButton("Submit");
        newTicket = new JButton("New Ticket");

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

                TelradEvent ev = new TelradEvent(this, sig, sin, PCI, Cell, isLocked, comp, nts, png);

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
        gc.gridy = 4;
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
        
        gc.gridy = 9;
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
