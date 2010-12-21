package pack;



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

public class Resh  extends JFrame{


    private static final long serialVersionUID = 2761039175617651022L;
    private JMenuBar menubar=null;
    private JMenu menu=null;
    private JMenuItem openmenu=null;
    private JMenuItem findmenu=null;
    private JMenuItem delay=null;
    private JMenuItem saveimage=null;
    private MyPanel panel=null;
    private int timedelay=1000;
    public Resh(String title){
        super(title);
        menubar=new JMenuBar();
        menu=new JMenu("File");
        openmenu=new JMenuItem("Pebor");
        findmenu=new JMenuItem("Result");
        openmenu.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               opentest(true);
           }
        
        });
        
        findmenu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                opentest(false);
            }
         
         });
        
        delay=new JMenuItem("Set delay");
        
        delay.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String sizeField = JOptionPane.showInputDialog("Input delya in millisecond");
                if (sizeField != null) {
                    try {
                        int p = Integer.parseInt(sizeField);
                        if ((p > 10) && (p <= 10000)){
                            timedelay=p;
                            if (panel!=null){
                                panel.setdelay(timedelay);
                            }
                            }
                    } catch (NumberFormatException ee) {
                    }
                }
            }
         });
        
        saveimage=new JMenuItem("Save record");
        saveimage.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                saveresualt();
            }
         });
        
        addKeyListener(new KeyAdapter(){
           
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode()==KeyEvent.VK_LEFT){
                    panel.prevRecord();
                }
                if (e.getKeyCode()==KeyEvent.VK_RIGHT){
                    panel.nextRecord();
                }
            }
        });
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        menu.add(openmenu);
        menu.add(findmenu);
        menu.add(saveimage);
        menu.add(delay);
        menubar.add(menu);
        setJMenuBar(menubar);
        setLayout(new BorderLayout());
        setFocusable(true);
        
    }
    
        
    public void opentest(boolean pr){ 
        JFileChooser fc=new JFileChooser();
        int value=fc.showSaveDialog(fc);
        if (value==JFileChooser.APPROVE_OPTION){
            File f=fc.getSelectedFile();
            String fileName=f.getAbsolutePath();
            if (panel!=null){
            
            panel.stopp();
            remove(panel);
            panel=null;
            }
            
            panel=new MyPanel(this,fileName,timedelay,pr);
            add(panel,BorderLayout.CENTER);
            
            repaint();
            panel.updateUI();
        }             
    }
    
    public void saveresualt(){ 
        if (panel.isRunning()){
            JOptionPane.showMessageDialog(this, "Sorry, but record don't find yet", "Saving", JOptionPane.ERROR_MESSAGE);
        }else{
            JFileChooser fc=new JFileChooser();
            int value=fc.showSaveDialog(fc);
            if (value==JFileChooser.APPROVE_OPTION){
                File f=fc.getSelectedFile();
                String fileName=f.getAbsolutePath();
                if (panel!=null){
                    panel.saveresualt(fileName);
                }
            }     
        }
    }
    
    public void paint(Graphics g){
        super.paint(g);
        if (panel!=null){
            panel.repaint();
        }
    }
    
    public static void main(String[] args){
        Resh frame=new Resh("Test");
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

}