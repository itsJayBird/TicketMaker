package ticketMaster;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
    private Menu menu;
    private Form form;
    private CheckList checkList;
    private JButton confirm;
    private String currForm;
    private TextPanel textPanel;
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

    public MainFrame() {
        super("Resound Ticket Builder");
        setLayout(new BorderLayout());
        form = new Form("none");
        menu = new Menu();
        checkList = new CheckList();
        confirm = new JButton("Confirm");

        menu.setStringListener(new StringListener() {
            public void textEmitted(String text) {
                remove(form);
                textPanel = new TextPanel();
                form = new Form(text);
                add(form, BorderLayout.WEST);
                add(checkList, BorderLayout.EAST);
                add(confirm, BorderLayout.SOUTH);
                validate();
                repaint();
                setSize(1000, 430);
                setCurrForm(text);
            }
        });

        checkList.setRadioDownListener(new RadioDownListener() {
            public void booleanEmitted(boolean check) {
                if(check==true) {
                    switch(currForm) {
                    case "ubnt":
                        form.disableUbnt();
                    }
                } else if(check==false) {
                    switch(currForm) {
                    case "ubnt":
                        form.enableUbnt();
                    }
                }
            }
        });

        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                form.getUbntInfo();
                form.setFormListener(new FormListener() {
                    public void formEventOccured(FormEvent e) {
                        String complaint = e.getComplaint();
                        String ping = e.getPing();
                        String ap = e.getAp();
                            signal0 = e.getSignal0();
                            signal1 = e.getSignal1();
                            chain0 = e.getChain0();
                            chain1 = e.getChain1();
                            sinr0 = e.getSinr0();
                            sinr1 = e.getSinr1();
                            sinr0 = e.getLan();
                            System.out.println("local signal: " + signal0);
                    }

                });

            }
        });

        add(menu, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 500);
        setResizable(false);
    }

    private void setCurrForm(String form) {
        this.currForm = form;
    }
}
