package ticketMaker;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SelectionPanel extends JPanel {
    private JList techList;
    private JButton okBtn;
    
    private SelectionListener selectionListener;
    
    public SelectionPanel() {
        // set dimension
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        
        // instantiate the list to select technology
        // and button to submit selection and move onto the next step
        techList = new JList();
        okBtn = new JButton("Ok");
        
        // building the list
        DefaultListModel techs = new DefaultListModel();
        techs.addElement("Telrad");
        techs.addElement("Mimosa");
        techs.addElement("Ubiquiti");
        techs.addElement("Cambium");
        techList.setModel(techs);
        techList.setPreferredSize(new Dimension(140, 80));
        techList.setBorder(BorderFactory.createBevelBorder(4));
        techList.setSelectedIndex(2);
        
        // set actionListener for the button to take information from the form
        okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String radioTech = (String)techList.getSelectedValue();
                
                SelectionEvent ev = new SelectionEvent(this, radioTech);
                
                if(selectionListener != null) {
                    selectionListener.selectionEventOccured(ev);
                }
            }
        });
        
        // setting a border for the overall window
        Border innerBorder = BorderFactory.createTitledBorder("Select Radio Technology");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        // add elements to the panel
        layoutComponents();
    }
    
    private void layoutComponents() {
        setLayout(new GridBagLayout()); // sets layout to gridbag
        GridBagConstraints gc = new GridBagConstraints(); // constraints we will be setting for the positions
        
        // first row //
        gc.gridy = 0;
        
        gc.weightx = 0;
        gc.weighty = 1;
        
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(techList, gc);
        
        // second row //
        gc.gridy++;
        gc.gridx = 0;
        gc.weighty = 2;
        gc.anchor = GridBagConstraints.CENTER;
        add(okBtn, gc);
        
    }
    public void setSelectionListener(SelectionListener selectionListener) {
        this.selectionListener = selectionListener;
    }
    
}
