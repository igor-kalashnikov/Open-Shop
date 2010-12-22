package by.bsu.fami.openshop.pack;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InputTestDialog extends JDialog {

    private static final long serialVersionUID = -882908177587858504L;

    public static final int APPROVE_OPTION = 0;
    public static final int CANCEL_OPTION = 1;
    public static final int ERROR_OPTION = 3;


    private int resalt = ERROR_OPTION;

    private JButton input = null;
    private JButton cancel = null;
    private PanelForInput panel = null;
    private int n, m;
    private int a[][];
    private JTable table = null;
    private JScrollPane scroll = null;
    private JPanel panell=null;

    public InputTestDialog(JFrame parent, String title, boolean mode) {
        super(parent, title, mode);
       
        panel = new PanelForInput(this);

        input = new JButton("Input");
        cancel = new JButton("Cancel");

        input.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (table != null) {
                    a = new int[n][m];
                    DefaultTableModel dtm = (DefaultTableModel) table
                            .getModel();
                    try {

                        for (int i = 0; i < n; i++)
                            for (int j = 0; j < m; j++) {
                                a[i][j] = Integer.parseInt((String) dtm
                                        .getValueAt(i, j));
                                if (a[i][j] <= 0) {
                                    throw new Exception("Incorrect data");
                                }
                            }
                        resalt = APPROVE_OPTION;
                        setVisible(false);
                    } catch (Exception ee) {
                        JOptionPane.showMessageDialog(null,
                                "Sorry incorrect input data",
                                "Sorry incorrect input data",
                                JOptionPane.OK_OPTION);

                    }
                }

            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resalt = CANCEL_OPTION;
                setVisible(false);
            }
        });

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(input, BorderLayout.WEST);
        add(cancel, BorderLayout.EAST);
        setSize(600, 400);
        setVisible(true);
        pack();
    }

    public void getNAndM() {
        try {
            n = Integer.parseInt(panel.getN());
            m = Integer.parseInt(panel.getM());
            if ((n <= 0) && (m <= 0)) {
                throw new Exception("Incorrect");
            }

            if (scroll != null) {
                remove(scroll);
            }
            table = new JTable(n, m);
            panell=new JPanel();
            panell.add(table);
            scroll = new JScrollPane(panell);
            add(scroll, BorderLayout.CENTER);
            setVisible(false);
            setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry incorrect input data",
                    "Sorry incorrect input data", JOptionPane.OK_OPTION);
        }
    }

    public int showDialog() {
        return resalt;
    }

    public int getN(){
        return n;
    }
    
    public int getM(){
        return m;
    }
    
    public int[][] getMatrix(){
        return a;
    }
    
    public void paint(Graphics g) {
        super.paint(g);
    }

}
