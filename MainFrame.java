package ticketMaster;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame implements FormListener, CheckEventListener {
    private Menu menu;
    private Form form;
    private CheckList checkList;
    private JButton confirm;
    private String currForm;
    private TextPanel textPanel;
    private Notes notes;
    private String complaint;
    private String ap;
    private String signal0;
    private String signal1;
    private String chain0;
    private String chain1;
    private String sinr0;
    private String sinr1;
    private String lan;
    private String ping;
    private String bandwidth;
    private String note;
    private boolean radioDownAtStart;
    private boolean powerCycleRadio;
    private boolean powerCycleRouter;
    private boolean custRouter;
    private boolean reseatCable;
    private boolean verifiedCable;
    private boolean verifiedPower;
    private boolean verifiedMAC;
    private boolean movedAP;
    private boolean devicesConnected;
    private boolean upgradeRouter;
    private boolean upgradeRadio;
    private boolean radioStillDown = false;
    private boolean chkPing = false;
    private boolean chkBandwidth = false;

    public MainFrame() {
        super("Resound Ticket Builder");
        setLayout(null);
        form = new Form("none");
        menu = new Menu();
        notes = new Notes();
        confirm = new JButton("Confirm");

        menu.setStringListener(new StringListener() {
            public void textEmitted(String text) {
                remove(form);
                if(checkList != null)remove(checkList);
                if(textPanel != null)remove(textPanel);
                if(notes != null)remove(notes);
                checkList = new CheckList();
                notes = new Notes();
                textPanel = new TextPanel();
                form = new Form(text);
                
                checkList.setRadioDownListener(new RadioDownListener() {
                    public void booleanEmitted(boolean check) {
                        if(check==true) {
                            if(currForm=="ubnt") {
                                form.disableUbnt();
                            }else if(currForm=="mimosa") {
                                form.disableMimosa();
                            }else if(currForm=="cambium") {
                                form.disableCambium();
                            } else if(currForm=="telrad") {
                                form.disableTelrad();
                            }
                        } else if(check==false) {
                            if(currForm=="ubnt") {
                                form.enableUbnt();
                            }else if(currForm=="mimosa") {
                                form.enableMimosa();
                            }else if(currForm=="cambium") {
                                form.enableCambium();
                            }else if(currForm=="telrad") {
                                form.enableTelrad();
                            }
                        }
                    }
                });
                checkList.setPingListener(new PingListener() {
                    public void booleanEmitted(boolean check) {
                        if(check==true) {
                            form.enablePing();
                            chkPing = true;
                        } else if(check==false) {
                            form.disablePing();
                            chkPing = false;
                        }
                    }
                });
                checkList.setBandwidthListener(new BandwidthListener() {
                    public void booleanEmitted(boolean check) {
                        if(check==true) {
                            form.enableBandwidth();
                            chkBandwidth = true;
                        } else if(check==false) {
                            form.disableBandwidth();
                            chkBandwidth = false;
                        }
                    }
                });
                menu.setBounds(150, 15, 380, 30);
                form.setBounds(10, 50, 610, 200);
                add(form);
                checkList.setBounds(620, 50, 270, 400);
                add(checkList);
                notes.setBounds(10, 250, 610, 200);
                add(notes);
                confirm.setBounds(270, 450, 100, 30);
                add(confirm);
                validate();
                repaint();
                setSize(900, 530);
                setCurrForm(text);
            }
        });

        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(notes);
                textPanel.setBounds(15, 260, 600, 180);
                add(textPanel);
                validate();
                repaint();
                notes.setStringListener(new StringListener() {
                    public void textEmitted(String text) {
                        note = text;
                    }
                });
                getFormStats();
                getCheckList();
                displayTicket();
            }
        });
        menu.setBounds(60, 0, 380, 50);
        add(menu);
        form.setBounds(80, 0, 400, 400);
        add(form);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 400);
        setResizable(false);
    }

    private void setCurrForm(String form) {
        this.currForm = form;
    }

    private void getFormStats() {
        form.setFormListener(this);
        if(currForm == "ubnt") {
            form.getUbntInfo();
        } else if(currForm == "mimosa") {
            form.getMimosaInfo();
        } else if(currForm == "cambium") {
            form.getCambiumInfo();
        } else if(currForm == "telrad") {
            form.getTelradInfo();
        }

    }
    private void getCheckList() {
        checkList.setCheckEventListener(this);
        checkList.checkBoxes();
    }
    private void displayTicket() {
        // set radio stats based on radio
        if(currForm == "ubnt") {
            textPanel.appendText("Ubiquiti\n\n");
            if(radioDownAtStart==true)textPanel.appendText("- Radio was down at the start \n");
            if(radioStillDown==false) {
                textPanel.appendText("Complaint: " + complaint + "\n\n");
                textPanel.appendText("Connected AP: " + ap + "\n");
                textPanel.appendText("Local Signal: -" + signal0 + " \u0394" + chain0 + "\n");
                textPanel.appendText("Remote Signal: -" + signal1 + " \u0394" + chain1 + "\n");
                textPanel.appendText("Local Noise Floor: -" + sinr0 + " | Remote Noise Floor: -" + sinr1 + "\n");
                textPanel.appendText("LAN Speed: " + lan + "\n");
                if(chkPing==true)textPanel.appendText("Ping: " + ping + "ms\n");
                if(chkBandwidth==true)textPanel.appendText("Bandwidth: " + bandwidth + "\n");
            }
        }else if(currForm == "mimosa") {
            textPanel.appendText("Mimosa \n\n");
            if(radioDownAtStart==true)textPanel.appendText("- Radio was down at the start \n\n");
            if(radioStillDown==false) {
                textPanel.appendText("Complaint: " + complaint + "\n\n");
                textPanel.appendText("Connected AP: " + ap + "\n");
                textPanel.appendText("Signal: -" + signal0 + "\n");
                textPanel.appendText("LAN Speed: " + lan + "\n");
                if(chkPing==true)textPanel.appendText("Ping: " + ping + "ms\n");
                if(chkBandwidth==true)textPanel.appendText("Bandwidth: " + bandwidth + "\n");
            }
        }else if(currForm == "cambium") {
            textPanel.appendText("Cambium \n\n");
            if(radioDownAtStart==true)textPanel.appendText("- Radio was down at the start \n\n");
            if(radioStillDown==false) {
                textPanel.appendText("Complaint: " + complaint + "\n\n");
                textPanel.appendText("Color Code / AP: " + ap + "\n");
                textPanel.appendText("Signal: -" + signal0 + "\n");
                textPanel.appendText("SINR: " + sinr0 + "\n");
                textPanel.appendText("LAN Speed: " + lan + "\n");
                if(chkPing==true)textPanel.appendText("Ping: " + ping + "ms\n");
                if(chkBandwidth==true)textPanel.appendText("Bandwidth: " + bandwidth + "\n");
            }
        }else if(currForm == "telrad") {
            textPanel.appendText("Telrad \n\n");
            if(radioDownAtStart==true)textPanel.appendText("- Radio was down at the start \n\n");
            if(radioStillDown==false) {
                textPanel.appendText("Complaint: " + complaint + "\n\n");
                textPanel.appendText("PCI: " + ap + " Cell: " + chain0 + "\n");
                textPanel.appendText("Signal: -" + signal0 + "\n");
                textPanel.appendText("SINR: " + sinr0 + "\n");
                textPanel.appendText("Cell Locked: " + chain1 + "\n");
                if(chkPing==true)textPanel.appendText("Ping: " + ping + "ms\n");
                if(chkBandwidth==true)textPanel.appendText("Bandwidth: " + bandwidth + "\n");
            }
        }

        // next the checklist
        textPanel.appendText("\nTroubleshooting Checklist: \n\n");
        if(powerCycleRadio==true)textPanel.appendText("- Power cycle radio for minimum of 2 min \n");
        if(powerCycleRouter==true)textPanel.appendText("- Power cycle router for a minimum of 2 min \n");
        if(custRouter==true)textPanel.appendText("- Customer owned router \n");
        if(reseatCable==true)textPanel.appendText("- Reseated cables \n");
        if(verifiedCable==true)textPanel.appendText("- Verified cable positions \n");
        if(verifiedPower==true)textPanel.appendText("- Verified power to equipment \n");
        if(verifiedMAC==true)textPanel.appendText("- Verified router MAC \n");
        if(movedAP==true)textPanel.appendText("- Moved to better serving AP \n");
        if(devicesConnected==true)textPanel.appendText("- Checked that devices are connected \n");
        if(upgradeRouter==true)textPanel.appendText("- Upgraded router firmware \n");
        if(upgradeRadio==true)textPanel.appendText("- Upgraded radio firmware \n");

        // finally notes
        textPanel.appendText("\nNotes: \n\n" + note);
        copyToClipboard();
    }
    private void copyToClipboard() {
        String toClipboard = textPanel.getText();
        StringSelection stringSelection = new StringSelection(toClipboard);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        textPanel.appendText("\n\n Text has been copied to clipboard!");
    }
    public void formEventOccured(FormEvent e) {
        if(currForm == "ubnt") {
            complaint = e.getComplaint();
            ping = e.getPing();
            ap = e.getAp();
            signal0 = e.getSignal0();
            signal1 = e.getSignal1();
            chain0 = e.getChain0();
            chain1 = e.getChain1();
            sinr0 = e.getSinr0();
            sinr1 = e.getSinr1();
            lan = e.getLan();
            bandwidth = e.getBandwidth();
        } else if(currForm == "mimosa") {
            complaint = e.getComplaint();
            ping = e.getPing();
            ap = e.getAp();
            signal0 = e.getSignal0();
            lan = e.getLan();
            bandwidth = e.getBandwidth();
        } else if(currForm == "cambium") {
            complaint = e.getComplaint();
            ping = e.getPing();
            ap = e.getAp();
            signal0 = e.getSignal0();
            sinr0 = e.getSinr0();
            lan = e.getLan();
            bandwidth = e.getBandwidth();
        } else if(currForm == "telrad") {
            complaint = e.getComplaint();
            ping = e.getPing();
            ap = e.getAp();
            signal0 = e.getSignal0();
            sinr0 = e.getSinr0();
            chain0 = e.getChain0();
            chain1 = e.getChain1();
            bandwidth = e.getBandwidth();
        }
    }
    public void checkEventOccured(CheckEvent e) {
        radioDownAtStart = e.isRadioDownAtStart();
        powerCycleRadio = e.isPowerCycleRadio();
        powerCycleRouter = e.isPowerCycleRouter();
        custRouter = e.isCustRouter();
        reseatCable = e.isReseatCable();
        verifiedCable = e.isVerifiedCable();
        verifiedPower = e.isVerifiedPower();
        verifiedMAC = e.isVerifiedMAC();
        movedAP = e.isMovedAP();
        devicesConnected = e.isDevicesConnected();
        upgradeRouter = e.isUpgradeRouter();
        upgradeRadio = e.isUpgradeRadio();
        radioStillDown = e.isRadioStillDown();
    }
}
