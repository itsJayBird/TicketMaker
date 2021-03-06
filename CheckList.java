package ticketMaster;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class CheckList extends JPanel {
    private JCheckBox powerCycleRadio;
    private JCheckBox powerCycleRouter;
    private JCheckBox reseatCable;
    private JCheckBox verifiedCable;
    private JCheckBox movedAP;
    private JCheckBox verifiedPower;
    private JCheckBox upgradeRouter;
    private JCheckBox upgradeRadio;
    private JCheckBox verifiedMAC;
    private JCheckBox devicesConnected;
    private JCheckBox radioDownAtStart;
    private JCheckBox radioStillDown;
    private JCheckBox custRouter;
    private JCheckBox checkPing;
    private JCheckBox checkBandwidth;
    private JCheckBox checkEnds;
    private JCheckBox calledRNA;
    private JCheckBox leftVM;
    private JCheckBox refuse;
    
    private CheckEventListener checkEventListener;
    private RadioDownListener radioDownListener;
    private PingListener pingListener;
    private BandwidthListener bandwidthListener;
    
    public CheckList() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        radioDownAtStart = new JCheckBox("Radio Was Down at the Start");
        powerCycleRadio = new JCheckBox("Power Cycle Radio");
        powerCycleRouter = new JCheckBox("Power Cycle Router");
        custRouter = new JCheckBox("Customer Owned Router");
        reseatCable = new JCheckBox("Reseat Cables");
        verifiedCable = new JCheckBox("Verified Cable Positions");
        verifiedPower = new JCheckBox("Verified Power to Equipment");
        verifiedMAC = new JCheckBox("Verified Router MAC");
        devicesConnected = new JCheckBox("Devices Connected");
        upgradeRouter = new JCheckBox("Upgrade Router Firmware");
        upgradeRadio = new JCheckBox("Upgrade Radio Firmware");
        movedAP = new JCheckBox("Moved AP/PCI");
        radioStillDown = new JCheckBox("Radio Still Down");
        checkPing = new JCheckBox("Checked Ping");
        checkBandwidth = new JCheckBox("Checked Bandwidth");
        checkEnds = new JCheckBox("Checked Cable Ends");
        calledRNA = new JCheckBox("Called No Answer");
        leftVM = new JCheckBox("Left Voicemail");
        refuse = new JCheckBox("Refused to Troubleshoot");
        
        
        radioStillDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean down = radioStillDown.isSelected();
                if(radioDownListener != null) {
                    radioDownListener.booleanEmitted(down);
                }
            }
        });
        checkPing.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean chkPing = checkPing.isSelected();
                if(pingListener != null) {
                    pingListener.booleanEmitted(chkPing);
                }
            }
        });
        checkBandwidth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean chkBand = checkBandwidth.isSelected();
                if(bandwidthListener != null) {
                    bandwidthListener.booleanEmitted(chkBand);
                }
            }
        });
        
        add(radioDownAtStart);
        add(powerCycleRadio);
        add(powerCycleRouter);
        add(custRouter);
        add(reseatCable);
        add(verifiedCable);
        add(checkEnds);
        add(verifiedPower);
        add(verifiedMAC);
        add(devicesConnected);
        add(upgradeRouter);
        add(upgradeRadio);
        add(movedAP);
        add(checkPing);
        add(checkBandwidth);
        add(calledRNA);
        add(leftVM);
        add(refuse);
        add(radioStillDown);
        
        Border innerBorder = BorderFactory.createTitledBorder("Checklist");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5 );
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }
    public void checkBoxes() {
        boolean downStart = radioDownAtStart.isSelected();
        boolean powerRadio = powerCycleRadio.isSelected();
        boolean powerRouter = powerCycleRouter.isSelected();
        boolean cxRouter = custRouter.isSelected();
        boolean reCable = reseatCable.isSelected();
        boolean verCable = verifiedCable.isSelected();
        boolean verPower = verifiedPower.isSelected();
        boolean verMAC = verifiedMAC.isSelected();
        boolean mAP = movedAP.isSelected();
        boolean devConnected = devicesConnected.isSelected();
        boolean upRouter = upgradeRouter.isSelected();
        boolean upRadio = upgradeRadio.isSelected();
        boolean stillDown = radioStillDown.isSelected();
        boolean called = calledRNA.isSelected();
        boolean vm = leftVM.isSelected();
        boolean ref = refuse.isSelected();
        boolean chkEnd = checkEnds.isSelected();
        
        CheckEvent ev = new CheckEvent(this, downStart, powerRadio, powerRouter, cxRouter, reCable,
                                       verCable, verPower, verMAC, mAP, devConnected, upRouter, upRadio, 
                                       stillDown, called, vm, ref, chkEnd);
        if(checkEventListener != null) {
            checkEventListener.checkEventOccured(ev);
        }
    }
    public void setCheckEventListener(CheckEventListener checkEventListener) {
        this.checkEventListener = checkEventListener;
    }
    public void setRadioDownListener(RadioDownListener radioDownListener) {
        this.radioDownListener = radioDownListener;
    }
    public void setPingListener(PingListener pingListener) {
        this.pingListener = pingListener;
    }
    public void setBandwidthListener(BandwidthListener bandwidthListener) {
        this.bandwidthListener = bandwidthListener;
    }
    
}
