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
    
    private CheckEventListener checkEventListener;
    private RadioDownListener radioDownListener;
    
    public CheckList() {
        Dimension dim = getPreferredSize();
        dim.width = 260;
        setPreferredSize(dim);
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
        
        radioStillDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean down = radioStillDown.isSelected();
                if(radioDownListener != null) {
                    radioDownListener.booleanEmitted(down);
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
        boolean upRouter = upgradeRouter.isSelected();
        boolean upRadio = upgradeRadio.isSelected();
        boolean stillDown = radioStillDown.isSelected();
        
        CheckEvent ev = new CheckEvent(this, downStart, powerRadio, powerRouter, cxRouter, reCable,
                                       reCable, verCable, verPower, verMAC, mAP, upRouter, upRadio, stillDown);
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
    
}
