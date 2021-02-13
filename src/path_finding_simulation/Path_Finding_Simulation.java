/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package path_finding_simulation;

import GUI.MainFrame;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 *
 * @author Imad
 */
public class Path_Finding_Simulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() 
            {
                new MainFrame();
            }
        });
        
    }
}
