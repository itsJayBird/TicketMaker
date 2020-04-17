package ticketMaster;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Outage extends JPanel {

    private JTextField outage;
    private JComboBox dd0;
    private JComboBox mm0;
    private JComboBox yy0;
    private JComboBox dd1;
    private JComboBox mm1;
    private JComboBox yy1;
    private JTextField area;
    private JCheckBox etr;

    private boolean isETR;
    private FormListener formListener;

    public Outage() {
        // setting fields
        JPanel infoPanel = new JPanel(new GridBagLayout());
        outage = new JTextField(10);
        area = new JTextField(10);
        etr = new JCheckBox("Gave ETR: ");
        // setting combo boxes
        JPanel datePanel = new JPanel();
        JPanel etrPanel = new JPanel();
        dd0 = new JComboBox();
        mm0 = new JComboBox();
        yy0 = new JComboBox();
        dd1 = new JComboBox();
        mm1 = new JComboBox();
        yy1 = new JComboBox();
        setDate0();
        setDate1();

        etr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(etr.isSelected()==true)isETR = true;
                if(etr.isSelected()==false)isETR = false;
            }
        });

        // lay out date box
        datePanel.add(new JLabel("Date: "));
        datePanel.add(dd0);
        datePanel.add(mm0);
        datePanel.add(yy0);
        // lay out date box 2
        etrPanel.add(new JLabel("ETR: "));
        etrPanel.add(dd1);
        etrPanel.add(mm1);
        etrPanel.add(yy1);

        // layout info area
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(new JLabel("Outage Name: "), gc);
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(outage, gc);
        gc.gridx = 2;
        //gc.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(new JLabel("Affected Area: "), gc);
        gc.gridx = 3;
        //gc.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(area, gc);
        gc.gridx = 1;
        gc.gridy++;
        //gc.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(datePanel, gc);
        gc.gridy++;
        gc.gridx = 1;
        infoPanel.add(etr, gc);
        gc.gridy++;
        gc.gridx = 1;
        infoPanel.add(etrPanel, gc);
        add(infoPanel);
        if(isETR == true) {
            etrPanel.setVisible(true);
            repaint();
            validate();
        }
        if(isETR == false) {
            etrPanel.setVisible(false);
            repaint();
            validate();
        }
    }
    private void setDate0() {
        // set days
        DefaultComboBoxModel dd = new DefaultComboBoxModel();
        dd.addElement("dd");
        for(int i = 0; i < 31; i++) {
            dd.addElement(i+1);
        }
        dd0.setModel(dd);
        // set months
        DefaultComboBoxModel mm = new DefaultComboBoxModel();
        mm.addElement("mm");
        for(int i = 0; i < 12; i++) {
            mm.addElement(i+1);
        }
        mm0.setModel(mm);
        //set year
        DefaultComboBoxModel yy = new DefaultComboBoxModel();
        yy.addElement("yy");
        for(int i = 0; i < 30; i++) {
            yy.addElement(2020 + i);
        }
        yy0.setModel(yy);
    }
    private void setDate1() {
        // set days
        DefaultComboBoxModel dd = new DefaultComboBoxModel();
        dd.addElement("dd");
        for(int i = 0; i < 31; i++) {
            dd.addElement(i+1);
        }
        dd1.setModel(dd);
        // set months
        DefaultComboBoxModel mm = new DefaultComboBoxModel();
        mm.addElement("mm");
        for(int i = 0; i < 12; i++) {
            mm.addElement(i+1);
        }
        mm1.setModel(mm);
        //set year
        DefaultComboBoxModel yy = new DefaultComboBoxModel();
        yy.addElement("yy");
        for(int i = 0; i < 30; i++) {
            yy.addElement(2020 + i);
        }
        yy1.setModel(yy);
    }
    public void getOutage() {
        String outg = outage.getText();
        String ar = area.getText();
        String dt = (String)dd0.getSelectedItem() + "/" 
                + (String)mm0.getSelectedItem() + "/" 
                + (String)yy0.getSelectedItem();
        String etrD = "";
        if(isETR == true) {
            etrD = (String)dd1.getSelectedItem() + "/"
                    + (String)mm1.getSelectedItem() + "/"
                    + (String)yy1.getSelectedItem();
        }
    }
}
