package by.bsu.fami.openshop.pack;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Resh extends JFrame {

    private static final long serialVersionUID = 2761039175617651022L;
    private JMenuBar menubar = null;
    private JMenu menu = null;
    private JMenu menuinput=null;
    private JMenuItem menuInputDadaPerebor=null;
    private JMenuItem menuInputDadaResult=null;
    private JMenuItem openmenu = null;
    private JMenuItem findmenu = null;
    private JMenuItem delay = null;
    private JMenuItem saveimage = null;
    private MyPanel panel = null;
    private int timedelay = 1000;

    private int styleAnswer=0;
    
    public Resh(String title, int style) {
        super(title);
        styleAnswer=style;
        menubar = new JMenuBar();
        menu = new JMenu("File");
        openmenu = new JMenuItem("Read file for search");
        findmenu = new JMenuItem("Read file for result");
        menuinput=new JMenu("Input test");
        menuInputDadaPerebor=new JMenuItem("Input test for search");
        menuInputDadaResult=new JMenuItem("Input test and get result");
        new JMenu("About");
        
        openmenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                opentest(true);
            }

        });

        findmenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                opentest(false);
            }

        });

        delay = new JMenuItem("Set delay");

        delay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sizeField = JOptionPane
                        .showInputDialog("Input delya in millisecond");
                if (sizeField != null) {
                    try {
                        int p = Integer.parseInt(sizeField);
                        if ((p > 10) && (p <= 10000)) {
                            timedelay = p;
                            if (panel != null) {
                                panel.setdelay(timedelay);
                            }
                        }
                    } catch (NumberFormatException ee) {
                    }
                }
            }
        });

        saveimage = new JMenuItem("Save record");
        saveimage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveresualt();
            }
        });
        
        menuInputDadaPerebor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputTest(true);
            }
        });
        
        menuInputDadaResult.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputTest(false);
            }
        });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    panel.prevRecord();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    panel.nextRecord();
                }
            }
        });

        menu.add(saveimage);
        menu.add(delay);
        menuinput.add(openmenu);
        menuinput.add(findmenu);
        menuinput.add(menuInputDadaPerebor);
        menuinput.add(menuInputDadaResult);
        menubar.add(menu);
        menubar.add(menuinput);
        // menubar.add(menuAbout);
        setJMenuBar(menubar);
        setLayout(new BorderLayout());
        setFocusable(true);
        
    }

    private void inputTest(boolean seach){
        InputTestDialog itd=new InputTestDialog(this, "Input test", true);
        int result=itd.showDialog();
        if (result==InputTestDialog.APPROVE_OPTION){
            int n=itd.getN();
            int m=itd.getM();
            int a[][]=itd.getMatrix();
            if (panel != null) {
                panel.stopp();
                remove(panel);
                panel = null;
            }

            panel = new MyPanel(this,timedelay, seach, n, m, a,styleAnswer);
            add(panel, BorderLayout.CENTER);
            repaint();
            panel.updateUI();
            
        }
        
    }
    
    public void opentest(boolean pr) {
        JFileChooser fc = new JFileChooser();
        int value = fc.showSaveDialog(fc);
        if (value == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            String fileName = f.getAbsolutePath();
            if (panel != null) {

                panel.stopp();
                remove(panel);
                panel = null;
            }

            panel = new MyPanel(this, fileName, timedelay, pr,styleAnswer);
            add(panel, BorderLayout.CENTER);

            repaint();
            panel.updateUI();
        }
    }

    public void saveresualt() {
        if (panel.isRunning()) {
            JOptionPane.showMessageDialog(this,
                    "Sorry, but record don't find yet", "Saving",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            JFileChooser fc = new JFileChooser();
            int value = fc.showSaveDialog(fc);
            if (value == JFileChooser.APPROVE_OPTION) {
                File f = fc.getSelectedFile();
                String fileName = f.getAbsolutePath();
                if (panel != null) {
                    panel.saveresualt(fileName);
                }
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (panel != null) {
            panel.repaint();
        }
    }

}