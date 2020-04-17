package ticketMaster;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Form extends JPanel implements Ubnt, Telrad, Cambium, Mimosa {
    private JTextField ap;
    private JTextField signal0;
    private JTextField signal1;
    private JTextField chain0;
    private JTextField chain1;
    private JTextField sinr0;
    private JTextField sinr1;
    private JComboBox lan;
    private JTextField ping;
    private JTextField bandwidth;
    private JComboBox complaint;
    private FormListener formListener;
    private BufferedImage image;

    public Form(String formType) {
        // decide what formType to use
        switch(formType) {
        case "ubnt":
            buildUbntForm();
            break;
        case "mimosa":
            buildMimosaForm();
            break;
        case "cambium":
            buildCambiumForm();
            break;
        case "telrad":
            buildTelradForm();
            break;
        }
    }
    public void buildUbntForm() {
        Dimension dim = new Dimension();
        dim.height = 250;
        setPreferredSize(dim);
        signal0 = new JTextField(5);
        signal1 = new JTextField(5);
        chain0 = new JTextField(5);
        chain1 = new JTextField(5);
        sinr0 = new JTextField(5);
        sinr1 = new JTextField(5);
        lan = new JComboBox();
        ping = new JTextField(5);
        bandwidth = new JTextField(5);
        ap = new JTextField(17);
        complaint = new JComboBox();
        setComplaints();
        setLAN();
        // set components
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        // first row //
        gc.gridx = 1;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Complaint: "), gc);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(complaint, gc);
        // second row //
        gc.gridx = 1;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Connected AP: "), gc);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(ap, gc);
        // second row //
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Local Signal: "), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(signal0, gc);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Remote Signal: "), gc);
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(signal1, gc);
        // third row //
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Local Chains: "), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(chain0, gc);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Remote Chains: "), gc);
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(chain1, gc);
        // fourth row //
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Local Noise Floor: "), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(sinr0, gc);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Remote Noise Floor: "), gc);
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(sinr1, gc);
        // fifth row //
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("LAN Speed: "), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(lan, gc);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Ping: "), gc);
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(ping, gc);
        ping.setEnabled(false);
        // sixth row //
        gc.gridy++;
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Bandwidth: "), gc);
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(bandwidth, gc);
        bandwidth.setEnabled(false);
    }
    public void disableUbnt() {
        signal0.setEnabled(false);
        signal1.setEnabled(false);
        chain0.setEnabled(false);
        chain1.setEnabled(false);
        sinr0.setEnabled(false);
        sinr1.setEnabled(false);
        lan.setEnabled(false);
        ap.setEnabled(false);
    }
    public void enableUbnt() {
        signal0.setEnabled(true);
        signal1.setEnabled(true);
        chain0.setEnabled(true);
        chain1.setEnabled(true);
        sinr0.setEnabled(true);
        sinr1.setEnabled(true);
        lan.setEnabled(true);
        ap.setEnabled(true);
    }
    public void getUbntInfo() {
        String comp = (String)complaint.getSelectedItem();
        String a = ap.getText();
        String loSignal = signal0.getText();
        String reSignal = signal1.getText();
        String loChains = chain0.getText();
        String reChains = chain1.getText();
        String loNoise = sinr0.getText();
        String reNoise = sinr1.getText();
        String lanSpeed = (String)lan.getSelectedItem();
        String p = ping.getText();
        String band = bandwidth.getText();
        FormEvent ev = new FormEvent(this, comp, a, loSignal, reSignal, 
                loChains, reChains, loNoise, reNoise, lanSpeed,
                p, band);
        if(formListener != null) {
            formListener.formEventOccured(ev);
        }
    }
    public void buildMimosaForm() {
        signal0 = new JTextField(5);
        lan = new JComboBox();
        ping = new JTextField(5);
        bandwidth = new JTextField(5);
        ap = new JTextField(17);
        complaint = new JComboBox();
        setComplaints();
        setLAN();
        // set components
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        // first row //
        gc.gridx = 1;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Complaint: "), gc);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(complaint, gc);
        // second row //
        gc.gridx = 1;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Connected AP: "), gc);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(ap, gc);
        // second row //
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Signal: "), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(signal0, gc);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Ping: "), gc);
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(ping, gc);
        // third row //
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("LAN Speed: "), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(lan, gc);
        ping.setEnabled(false);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Bandwidth: "), gc);
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(bandwidth, gc);
        bandwidth.setEnabled(false);
    }
    public void getMimosaInfo() {
        String comp = (String)complaint.getSelectedItem();
        String a = ap.getText();
        String Signal = signal0.getText();
        String lanSpeed = (String)lan.getSelectedItem();
        String p = ping.getText();
        String bwidth = bandwidth.getText();
        FormEvent ev = new FormEvent(this, comp, a, Signal,lanSpeed, p, bwidth);
        if(formListener != null) {
            formListener.formEventOccured(ev);
        }
    }
    public void enableMimosa() {
        signal0.setEnabled(true);
        lan.setEnabled(true);
        ap.setEnabled(true);
    }
    public void disableMimosa() {
        signal0.setEnabled(false);
        lan.setEnabled(false);
        ap.setEnabled(false);
    }
    public void buildCambiumForm() {
        signal0 = new JTextField(5);
        lan = new JComboBox();
        ping = new JTextField(5);
        bandwidth = new JTextField(5);
        ap = new JTextField(17);
        complaint = new JComboBox();
        sinr0 = new JTextField(5);
        setComplaints();
        setLAN();
        // set components
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        // first row //
        gc.gridx = 1;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Complaint: "), gc);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(complaint, gc);
        // second row //
        gc.gridx = 1;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Color Code / AP: "), gc);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(ap, gc);
        // second row //
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Signal: "), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(signal0, gc);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("SINR: "), gc);
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(sinr0, gc);
        // third row //
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("LAN Speed: "), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(lan, gc);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Ping: "), gc);
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(ping, gc);
        ping.setEnabled(false);
        // fourth row //
        gc.gridy++;
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Bandwidth: "), gc);
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(bandwidth, gc);
        bandwidth.setEnabled(false);
    }
    public void getCambiumInfo() {
        String comp = (String)complaint.getSelectedItem();
        String a = ap.getText();
        String Signal = signal0.getText();
        String lanSpeed = (String)lan.getSelectedItem();
        String sinr = sinr0.getText();
        String p = ping.getText();
        String bwidth = bandwidth.getText();
        FormEvent ev = new FormEvent(this, comp, a, Signal,lanSpeed, p, sinr, bwidth);
        if(formListener != null) {
            formListener.formEventOccured(ev);
        }
    }
    public void enableCambium() {
        signal0.setEnabled(true);
        lan.setEnabled(true);
        ap.setEnabled(true);
        sinr0.setEnabled(true);
    }
    public void disableCambium() {
        signal0.setEnabled(false);
        lan.setEnabled(false);
        ap.setEnabled(false);
        sinr0.setEnabled(false);
    }
    public void buildTelradForm() {
        signal0 = new JTextField(5);
        sinr0 = new JTextField(5);
        chain0 = new JTextField(5);
        chain1 = new JTextField(5);
        ping = new JTextField(5);
        bandwidth = new JTextField(5);
        ap = new JTextField(17);
        complaint = new JComboBox();
        setComplaints();
        // set components
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        // first row //
        gc.gridx = 1;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Complaint: "), gc);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(complaint, gc);
        // second row //
        gc.gridx = 1;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("PCI: "), gc);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(ap, gc);
        // second row //
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Signal: "), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(signal0, gc);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("SINR: "), gc);
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(sinr0, gc);
        // third row //
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Cell: "), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(chain0, gc);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Cell Locked: "), gc);
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(chain1, gc);
        // fourth row //
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Ping: "), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(ping, gc);
        ping.setEnabled(false);
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Bandwidth: "), gc);
        gc.gridx = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(bandwidth, gc);
        bandwidth.setEnabled(false);
    }
    public void getTelradInfo() {
        String comp = (String)complaint.getSelectedItem();
        String a = ap.getText();
        String Signal = signal0.getText();
        String sinr = sinr0.getText();
        String cell = chain0.getText();
        String locked = chain1.getText();
        String p = ping.getText();
        String bwidth = bandwidth.getText();
        FormEvent ev = new FormEvent(this, comp, a, Signal, p, sinr, cell, locked, bwidth);
        if(formListener != null) {
            formListener.formEventOccured(ev);
        }
    }
    public void enableTelrad() {
        ap.setEnabled(true);
        signal0.setEnabled(true);
        sinr0.setEnabled(true);
        chain0.setEnabled(true);
        chain1.setEnabled(true);
    }
    public void disableTelrad() {
        ap.setEnabled(false);
        signal0.setEnabled(false);
        sinr0.setEnabled(false);
        chain0.setEnabled(false);
        chain1.setEnabled(false);
    }
    public void setComplaints() {
        //set up combo box with common complaints
        complaint = new JComboBox();
        DefaultComboBoxModel complaints = new DefaultComboBoxModel();
        complaints.addElement("No Connection");
        complaints.addElement("Intermittent Connection");
        complaints.addElement("Slow Speeds");
        complaints.addElement("Router Support");
        complaints.addElement("Follow Up");
        complaint.setModel(complaints);
        complaint.setSelectedIndex(2);
        complaint.setEditable(true);
        complaint.setBackground(Color.white);
    }
    private void setLAN() {
        //set up combo box for LAN speeds
        lan = new JComboBox();
        DefaultComboBoxModel ls = new DefaultComboBoxModel();
        ls.addElement("10mbps Full-Duplex");
        ls.addElement("100mbps Full-Duplex");
        ls.addElement("1000mbps Full-Duplex");
        ls.addElement("Half-Duplex");
        ls.addElement("No Link");
        lan.setModel(ls);
        lan.setBackground(Color.white);
    }
    public void enablePing() {
        ping.setEnabled(true);
    }
    public void disablePing() {
        ping.setEnabled(false);
    }
    public void enableBandwidth() {
        bandwidth.setEnabled(true);
    }
    public void disableBandwidth() {
        bandwidth.setEnabled(false);
    }
    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }
}
