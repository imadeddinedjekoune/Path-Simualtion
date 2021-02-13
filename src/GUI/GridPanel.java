/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Imad
 */
public class GridPanel extends JPanel implements MouseInputListener{
    
    public static int [][] color ;
    public static int type_dessin = -1 ; 
    private static int last_x = 0 , last_y = 0;
    private static int last_x_end = 0 , last_y_end = 0;
    
    
    public GridPanel()
    {
        setBackground(Color.black);
        addMouseListener(this);
        addMouseMotionListener(this);
        color = new int[40][56];
    }
    
    
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for ( int i = 0 ; i < 40 ; i++ )
        {
            for ( int j = 0 ; j < 56 ; j++ )
            {
                switch (color[i][j])
                {
                    case 0 : 
                        g.setColor(Color.white);
                        break ;
                    case 1 : 
                        g.setColor(Color.black);
                        break ;
                    case 2 : 
                        g.setColor(Color.green);
                        break ;
                    case 3 : 
                        g.setColor(Color.blue);
                        break ;
                    case 4 : 
                        g.setColor(Color.yellow);
                        break ;
                    case 5 : 
                        g.setColor(Color.red);
                        break ;
                }
                g.fillRect(1+11*j, 1+11*i, 10, 10);
            }
        }
        
    };

    
    
    private int getPoint (int X , int Y)
    {
        return ((Y-1)/11) * 56 + ((X-1)/11) ;
    }
    
    public static int [] get_X_Y (int ID)
    {
        int T [] = new int [2] ;
        T[0] = ID / 56 ;
        T[1] = ID % 56 ;
        return T ;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        if (type_dessin == 1)
        {
            int ID = getPoint(e.getX(),e.getY());
            int T [] = get_X_Y(ID);
            if (e.getX() < 616 && e.getX() > 0
             && e.getY() < 440 && e.getY() > 0 
             && !(T[0] == last_x_end && T[1] == last_y_end)
             )
            {
                color[T[0]][T[1]] = 2 ;
                color[last_x][last_y] = 0 ;
                last_x = T[0] ;
                last_y = T[1];
            }
            
            repaint();
        }
        if (type_dessin == 2)
        {
            int ID = getPoint(e.getX(),e.getY());
            int T [] = get_X_Y(ID);
            if (e.getX() < 616 && e.getX() > 0
             && e.getY() < 440 && e.getY() > 0 
             && !(T[0] == last_x && T[1] == last_y))
            {
                color[T[0]][T[1]] = 3 ;
                color[last_x_end][last_y_end] = 0 ;
                last_x_end = T[0] ;
                last_y_end = T[1];
            }
            
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (type_dessin == 0)
        {
            int ID = getPoint(e.getX(),e.getY());
            int T [] = get_X_Y(ID);
            if (e.getX() < 616 && e.getX() > 0
             && e.getY() < 440 && e.getY() > 0 )
            {
                if (!(T[0] == last_x && T[1] == last_y)
                 && !(T[0] == last_x_end && T[1] == last_y_end))
                {
                    color[T[0]][T[1]] = 1 ;
                }
            }
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    public void rep ()
    {
        this.repaint();
    }
    
    public void repV2 ()
    {
        removeAll();
        
    }
    
}
