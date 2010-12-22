package by.bsu.fami.openshop.pack;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelForInput extends JPanel{
    
    /**
     * 
     */
    private static final long serialVersionUID = 8815336944514298932L;
    private JLabel labelN=new JLabel("Input n");
    private JLabel labelM=new JLabel("Input m");
    private JTextField inputN=new JTextField();
    private JTextField inputM=new JTextField();
    private JButton ok=new JButton("Ok");
    private InputTestDialog itd; 
    
    public PanelForInput(InputTestDialog dialog){
        itd=dialog;
        setLayout(new GridLayout(1,5));
        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                itd.getNAndM();   
            }
        });  
        add(labelN);
        add(inputN);
        add(labelM);
        add(inputM);
        add(ok);       
    }
    
    public String getN(){
        return inputN.getText();
    }
    
    public String getM(){
        return inputM.getText();
    }

}
