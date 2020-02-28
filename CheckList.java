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
    
    private CheckEventListener checkEventListener;
    private RadioDownListener radioDownListener;
    private PingListener pingListener;
    private BandwidthListener bandwidthListener;
    
    public CheckList() {
        //Dimension dim = getPreferredSize();
        //dim.width = 260;
        //setPreferredSize(dim);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        powerCycleRadio = new JCheckBox("Power Cycle Radio");
        powerCycleRouter = new JCheckBox("Power Cycle Router");
        custRouter = new JCheckBox("Customer Owned Router");
        reseatCable = new JCheckBox("Reseat Cables");
        verifiedCable = new JCheckBox("Verified Cable Positions");
        movedAP = new JCheckBox("Moved AP/PCI");
        verifiedPower = new JCheckBox("Verified Power to Equipment");
        upgradeRouter = new JCheckBox("Upgrade Router Firmware");
        upgradeRadio = new JCheckBox("Upgrade Radio Firmware");
        verifiedMAC = new JCheckBox("Verified Router MAC");
        devicesConnected = new JCheckBox("Devices Connected");
        radioDownAtStart = new JCheckBox("Radio Was Down at the Start");
        radioStillDown = new JCheckBox("Radio Still Down");
        checkPing = new JCheckBox("Checked Ping");
        checkBandwidth = new JCheckBox("Checked Bandwidth");
        
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
        add(verifiedPower);
        add(verifiedMAC);
        add(movedAP);
        add(devicesConnected);
        add(upgradeRouter);
        add(upgradeRadio);
        add(checkPing);
        add(checkBandwidth);
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
        
        CheckEvent ev = new CheckEvent(this, downStart, powerRadio, powerRouter, cxRouter, reCable,
                                       verCable, verPower, verMAC, mAP, devConnected, upRouter, upRadio, stillDown);
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
