package ticketMaker;

import java.awt.BorderLayout;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    private SelectionPanel selectionPanel;
    private TextPanel textPanel;
    private UbiquitiForm ubntForm;
    private TelradForm telradForm;
    private MimosaForm mimosaForm;
    private CambiumForm cambiumForm;

    public MainFrame() {
        super("Ticket Builder");
        setLayout(new BorderLayout());

        selectionPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void selectionPanel() {

        selectionPanel = new SelectionPanel(); // this panel will ask what technology we are using

        add(selectionPanel, BorderLayout.CENTER);
        selectionPanel.setSelectionListener(new SelectionListener() {
            public void selectionEventOccured(SelectionEvent e) {
                String radioTech = e.getRadioTech();

                switch(radioTech) {
                case "Ubiquiti":
                    ubiquitiGUI();
                    break;
                case "Telrad":
                    telradGUI();
                    break;
                case "Mimosa":
                    mimosaGUI();
                    break;
                case "Cambium":
                    camGUI();
                    break;
                }
            }
        });

        setSize(400, 300);
    }

    private void ubiquitiGUI() {
        ubntForm = new UbiquitiForm();
        textPanel = new TextPanel();
        add(ubntForm, BorderLayout.WEST);

        ubntForm.setUbiquitiListener(new UbiquitiListener() {
            public void FormEventOccurred(UbiqEvent e) {
                String localSignal = e.getLocalSignal();
                String localChains = e.getLocalChains();
                String localNoiseFloor = e.getLocalNoiseFloor();
                String remoteSignal = e.getRemoteSignal();
                String remoteChains = e.getRemoteChains();
                String remoteNoiseFloor = e.getRemoteNoiseFloor();
                String connectedAP = e.getConnectedAP();
                String complaint = e.getComplaint();
                String notes = e.getNotes();
                String pingTime = e.getPingTime();
                String lanSpeed = e.getLanSpeed();
                setSize(900, 400);
                add(textPanel, BorderLayout.CENTER);
                textPanel.appendText("Ubiquiti\n\n"
                        + "Connected AP: " + connectedAP + "\n\n" 
                        + "Complaint: \n" + complaint + "\n"
                        + "Local Signal: " + localSignal + "d" + localChains + "\n"
                        + "Remote Signal: " + remoteSignal + "d" + remoteChains + "\n"
                        + "Noise Floor: " + localNoiseFloor + " | " + remoteNoiseFloor + "\n"
                        + "Ping: " + pingTime + "\n"
                        + "LAN Speed: " + lanSpeed + "\n"
                        + "\n\nNotes: \n" + notes);   

                String toClipboard = textPanel.getText();
                StringSelection stringSelection = new StringSelection(toClipboard);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);

                textPanel.appendText("\n\n Text has been copied to clipboard!");
            }
        });

        ubntForm.setStringListener(new StringListener() {
            public void textEmitted(String text) {
                if(text == "newTicket") {
                    remove(ubntForm);
                    remove(textPanel);
                    selectionPanel();
                }
            }

        });

        remove(selectionPanel);
        setSize(600, 400);
    }
    private void telradGUI() {
        telradForm = new TelradForm();
        textPanel = new TextPanel();
        add(telradForm, BorderLayout.WEST);

        telradForm.setFormListener(new TelradListener() {
            public void FormEventOccurred(TelradEvent e) {
                String signal = e.getSignal();
                String sinr = e.getSinr();
                String pci = e.getPci();
                String cell = e.getCell();
                String cellLocked = e.getCellLocked();
                String complaint = e.getComplaint();
                String notes = e.getNotes();
                String ping = e.getPing();
                setSize(900, 400);
                add(textPanel, BorderLayout.CENTER);
                textPanel.appendText("Telrad\n\n"
                        + "Complaint: \n" + complaint + "\n\n"
                        + "Signal: " + signal + "\n"
                        + "SINR: " + sinr + "\n"
                        + "PCI: " + pci + " Cell: " + cell + "\n"
                        + "Cell Locked: " + cellLocked + "\n"
                        + "Ping: " + ping + "\n"
                        + "\n\nNotes: \n\n" + notes);

                String toClipboard = textPanel.getText();
                StringSelection stringSelection = new StringSelection(toClipboard);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);

                textPanel.appendText("\n\n Text has been copied to clipboard!");
            }
        });

        telradForm.setStringListener(new StringListener() {
            public void textEmitted(String text) {
                if(text == "newTicket") {
                    remove(telradForm);
                    remove(textPanel);
                    selectionPanel();
                }
            }

        });
        remove(selectionPanel);
        setSize(600, 400);
    }
    private void mimosaGUI() {
        mimosaForm = new MimosaForm();
        textPanel = new TextPanel();
        add(mimosaForm, BorderLayout.WEST);

        mimosaForm.setFormListener(new MimosaListener() {
            public void FormEventOccurred(MimosaEvent e) {
                String signal = e.getSignal();
                String ap = e.getConnectedAP();
                String lan = e.getLanSpeed();
                String ping = e.getPing();
                String comp = e.getComplaint();
                String notes = e.getNotes();
                setSize(900, 400);
                add(textPanel, BorderLayout.CENTER);
                textPanel.appendText("Mimosa\n\n"
                        + "Complaint: \n" + comp + "\n\n"
                        + "AP: " + ap + "\n"
                        + "Signal: " + signal + "\n"
                        + "LAN Speed: " + lan + "\n"
                        + "Ping: " + ping + "\n"
                        + "\n\nNotes: \n" + notes);

                String toClipboard = textPanel.getText();
                StringSelection stringSelection = new StringSelection(toClipboard);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);

                textPanel.appendText("\n\n Text has been copied to clipboard!");
            }
        });

        mimosaForm.setStringListener(new StringListener() {
            public void textEmitted(String text) {
                if(text == "newTicket") {
                    remove(mimosaForm);
                    remove(textPanel);
                    selectionPanel();
                }
            }

        });
        remove(selectionPanel);
        setSize(600, 400);
    }
    private void camGUI() {
        cambiumForm = new CambiumForm();
        textPanel = new TextPanel();
        add(cambiumForm, BorderLayout.WEST);

        cambiumForm.setFormListener(new CambiumListener() {
            public void FormEventOccurred(CambiumEvent e) {
                String signal = e.getSignal();
                String sinr = e.getSinr();
                String ap = e.getConnectedAP();
                String lan = e.getLanSpeed();
                String ping = e.getPing();
                String complaint = e.getComplaint();
                String notes = e.getNotes();
                setSize(900, 400);
                add(textPanel, BorderLayout.CENTER);
                textPanel.appendText("Cambium\n\n"
                        + "Complaint: \n" + complaint + "\n\n"
                        + "AP/Color Code: " + ap + "\n"
                        + "Signal: " + signal + "\n"
                        + "SINR: " + sinr + "\n"
                        + "LAN Speed: " + lan + "\n"
                        + "Ping: " + ping + "\n"
                        + "\n\nNotes: \n" + notes);
                String toClipboard = textPanel.getText();
                StringSelection stringSelection = new StringSelection(toClipboard);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);

                textPanel.appendText("\n\n Text has been copied to clipboard!");
            }
        });

        cambiumForm.setStringListener(new StringListener() {
            public void textEmitted(String text) {
                if(text == "newTicket") {
                    remove(cambiumForm);
                    remove(textPanel);
                    selectionPanel();
                }
            } 
        });
        setSize(600, 400);
        remove(selectionPanel);
    }
}
