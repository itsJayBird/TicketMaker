package ticketMaster;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainFrame extends JFrame implements FormListener, CheckEventListener {
	private Menu menu;
	private JTabbedPane container;
	private CheckList checkList;
	private JButton confirm;
	private int currForm;
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

	private Form u;
	private Form t;
	private Form m;
	private Form c;
	private JButton reset;
	private BufferedImage name;
	private BufferedImage logo;

	public MainFrame() {
		super("Resound Ticket Builder");
		setLayout(null);
		menu = new Menu();
		notes = new Notes();
		confirm = new JButton("Confirm");
		container = new JTabbedPane();
		u = new Form("ubnt");
		t = new Form("telrad");
		m = new Form("mimosa");
		c = new Form("cambium");
		container.addTab("Ubiquiti", u);
		container.addTab("Telrad", t);
		container.addTab("Mimosa" , m);
		container.addTab("Cambium", c);
		checkList = new CheckList();
		reset = new JButton("Reset");
		try {
			name = ImageIO.read(new File("logo_name1.png"));
			logo = ImageIO.read(new File("logo_1.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JButton n = new JButton(new ImageIcon(name));
		JButton l = new JButton(new ImageIcon(logo));

		container.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				currForm = container.getSelectedIndex();
				remove(checkList);
				checkList = new CheckList();
				checkList.setBounds(620, 50, 270, 400);
				add(checkList);
				checkListeners();
			}
		});

		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(notes);
				textPanel = new TextPanel();
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
				refreshTabs();
			}
		});
		
		checkListeners();
		container.setBounds(15, 20, 600, 200);
		add(container);
		checkList.setBounds(620, 25, 250, 400);
		add(checkList);
		notes.setBounds(10, 230, 610, 220);
		add(notes);
		confirm.setBounds(200, 450, 100, 30);
		add(confirm);
		reset.setBounds(330, 450, 100, 30);
		add(reset);
		n.setBounds(795, 440, 80, 50);
		n.setBorder(BorderFactory.createEmptyBorder());
		n.setContentAreaFilled(false);
		l.setBounds(760, 430, 80, 50);
		l.setBorder(BorderFactory.createEmptyBorder());
		l.setContentAreaFilled(false);
		add(l);
		add(n);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(900, 530);
		setResizable(false);
	}
	private void getFormStats() {
		currForm = container.getSelectedIndex();
		if(currForm == 0) {
			u.setFormListener(this);
			u.getUbntInfo();
		} else if(currForm == 2) {
			m.setFormListener(this);
			m.getMimosaInfo();
		} else if(currForm == 3) {
			c.setFormListener(this);
			c.getCambiumInfo();
		} else if(currForm == 1) {
			t.setFormListener(this);
			t.getTelradInfo();
		}

	}
	private void getCheckList() {
		checkList.setCheckEventListener(this);
		checkList.checkBoxes();
	}
	private void displayTicket() {
		// set radio stats based on radio
		if(currForm == 0) {
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
		}else if(currForm == 2) {
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
		}else if(currForm == 3) {
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
		}else if(currForm == 1) {
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
		if(currForm == 0) {
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
		} else if(currForm == 2) {
			complaint = e.getComplaint();
			ping = e.getPing();
			ap = e.getAp();
			signal0 = e.getSignal0();
			lan = e.getLan();
			bandwidth = e.getBandwidth();
		} else if(currForm == 3) {
			complaint = e.getComplaint();
			ping = e.getPing();
			ap = e.getAp();
			signal0 = e.getSignal0();
			sinr0 = e.getSinr0();
			lan = e.getLan();
			bandwidth = e.getBandwidth();
		} else if(currForm == 1) {
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
	private void checkListeners() {
		checkList.setRadioDownListener(new RadioDownListener() {
			public void booleanEmitted(boolean check) {
				currForm = container.getSelectedIndex();
				if(check==true) {
					if(currForm==0) {
						u.disableUbnt();
					}else if(currForm==2) {
						m.disableMimosa();
					}else if(currForm==3) {
						c.disableCambium();
					} else if(currForm==1) {
						t.disableTelrad();
					}
				} else if(check==false) {
					if(currForm==0) {
						u.enableUbnt();
					}else if(currForm==2) {
						m.enableMimosa();
					}else if(currForm==3) {
						c.enableCambium();
					}else if(currForm==1) {
						t.enableTelrad();
					}
				}
			}
		});
		checkList.setPingListener(new PingListener() {
			public void booleanEmitted(boolean check) {
				currForm = container.getSelectedIndex();
				if(check==true) {
					if(currForm==0)u.enablePing();
					if(currForm==1)t.enablePing();
					if(currForm==2)m.enablePing();
					if(currForm==3)c.enablePing();
					chkPing = true;
				} else if(check==false) {
					if(currForm==0)u.disablePing();
					if(currForm==1)t.disablePing();
					if(currForm==2)m.disablePing();
					if(currForm==3)c.disablePing();
					chkPing = false;
				}
			}
		});
		checkList.setBandwidthListener(new BandwidthListener() {
			public void booleanEmitted(boolean check) {
				currForm = container.getSelectedIndex();
				if(check==true) {
					if(currForm==0)u.enableBandwidth();
					if(currForm==1)t.enableBandwidth();
					if(currForm==2)m.enableBandwidth();
					if(currForm==3)c.enableBandwidth();
					chkBandwidth = true;
				} else if(check==false) {
					if(currForm==0)u.disableBandwidth();
					if(currForm==1)t.disableBandwidth();
					if(currForm==2)m.disableBandwidth();
					if(currForm==3)c.disableBandwidth();
					chkBandwidth = false;
				}
			}
		});
	}
	private void refreshTabs() {
		remove(container);
		container = new JTabbedPane();
		u = new Form("ubnt");
		t = new Form("telrad");
		m = new Form("mimosa");
		c = new Form("cambium");
		container.addTab("Ubiquiti", u);
		container.addTab("Telrad", t);
		container.addTab("Mimosa" , m);
		container.addTab("Cambium", c);
		container.setBounds(15, 50, 600, 200);
		add(container);
		validate();
		repaint();
	}
}
