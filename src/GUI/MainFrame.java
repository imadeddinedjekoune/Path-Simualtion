/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Imad
 */
public class MainFrame extends JFrame 
{
    private static int width = 800  , height = 480  ;
    public static GridPanel p ;
    public static ButtonPanel bp ;
    
    public MainFrame()
    {
        p = new GridPanel();
        bp = new ButtonPanel();
        
        setTitle("Path Finding Simulation");
	setResizable(false);
        setSize(width,height);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2,
                    (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2);
        setLayout(null);
        
        p.setBounds(0,0, 617, 441);
        bp.setBounds(618, 0, 166, 441);
        add(p);
        add(bp);
        
    }
}
