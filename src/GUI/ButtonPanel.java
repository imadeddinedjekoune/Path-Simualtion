/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Maze.Maze;
import Utils.Util;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Imad
 */
public class ButtonPanel extends JPanel {
    
    private JButton btn_point_depart ;
    private JButton btn_point_arrive ;
    private JButton btn_obstacle ;
    private JButton btn_effacer_tout ;
    private JButton btn_commencer_simulation ;
    private JButton btn_genertae_maze ;
    
    
    
    public ButtonPanel ()
    {
        init();
        listenner();
        add();
    }
    
    private void init ()
    {
        
        btn_point_depart = new JButton("Ajouter Point");
        btn_point_arrive = new JButton("La Fin De point");
        btn_obstacle = new JButton("Ajouter Mure");
        btn_effacer_tout = new JButton("effacer Tout");
        btn_commencer_simulation = new JButton("Start");
        btn_genertae_maze = new JButton("Generate");
        
        setLayout (new GridLayout(15, 1));
    }
    
    private void listenner ()
    {
        btn_point_depart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GridPanel.type_dessin = 1 ;
            }
        });
        btn_point_arrive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GridPanel.type_dessin = 2 ;
            }
        });
        btn_obstacle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GridPanel.type_dessin = 0 ;
            }
        });
        btn_effacer_tout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GridPanel.type_dessin = -1 ;
                GridPanel.color = new int[40][56];
                MainFrame.p.rep();
            }
        });
        
        btn_genertae_maze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Maze m = new Maze(40, 56);
                GridPanel.color = m.getMaze();
                MainFrame.p.rep();
            }
        });
        
        btn_commencer_simulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ArrayList <ArrayList<Integer>> l =  Util.getAjacentList(GridPanel.color);
                
                Point start = Util.get_Point(GridPanel.color,Util.DEBUT_POINT);
                Point end = Util.get_Point(GridPanel.color,Util.END_POINT);

                Queue<Integer> q = new LinkedList<>(); // creer la file //
                q.add((int)(start.getX()*56+start.getY())); // Ajouter Le Point De deaprt //

                int Check [] = new int[56*40];
                Check[(int)(start.getX()*56+start.getY())] = 1 ;
                
                
                Thread t2 = new Thread(() ->{
                    while(true)
                    {
                        MainFrame.p.rep();
                    }
                });
                t2.start();
                
                
                Thread t1 = new Thread(() -> {
                    int prev [] = null;
                    prev = Util.initTab(56*40, -1);

                    while (Check[(int)(end.getX()*56+end.getY())] != 1)
                    {
                        int node ;
                        try {
                            node = q.peek();
                        } catch (Exception ex) {
                            break ;
                        }
                        q.remove();
                        GridPanel.color[(node/56)][(node%56)] = 4;
                        ArrayList<Integer> adjacents = l.get(node);

                        
                        for (int i = 0 ; i < adjacents.size() ; i++ )
                        {
                            if (Check[adjacents.get(i)] == 0)
                            {
                                q.add(adjacents.get(i));
                                Check[adjacents.get(i)] = 1;
                                GridPanel.color[(adjacents.get(i)/56)][(adjacents.get(i)%56)] = 5;
                                prev[adjacents.get(i)] = node ; 
                            }
                        }
                        try {
                            Thread.sleep(5);
                        } catch (Exception ee) {
                        }
                        GridPanel.color[(int)(start.getX())][(int)(start.getY())] = 2 ;
                    } 
                    
                    // Clear Tab //
                    GridPanel.color = Util.clear_Tab(GridPanel.color);
                    
                    int TF[] = Util.getPath(prev,(int)(start.getX()*56+start.getY()), (int)(end.getX()*56+end.getY()));
                    
                    int X1 = (int)(start.getX()) , Y1 = (int)(start.getY());
                    int X2 = (int)(end.getX()) , Y2  = (int)(end.getY()) ;
                    
                    
                    GridPanel.color[X1][Y1] = 2 ;
                    GridPanel.color[X2][Y2] = 3 ;
                    
                    for ( int i = 0 ; i < TF.length ; i++ )
                    {
                        int x , y ;
                        x = GridPanel.get_X_Y(TF[i])[0];
                        y = GridPanel.get_X_Y(TF[i])[1];
                        
                        if (!(X1 == x && Y1 == y )
                         && !(X2 == x && Y2 == y ))
                        {
                            GridPanel.color[x][y] = 5 ;
                        }
                        
                        try {
                            Thread.sleep(10);
                        } catch (Exception ee) {
                        }
                    }
                    
                    
                });
                t1.start();
            }
        });
        
    }
    
    private void add()
    {
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());

        add(btn_point_depart);
        add(btn_point_arrive);
        add(btn_obstacle);
        add(btn_effacer_tout);
        add(btn_commencer_simulation);
        add(btn_genertae_maze);
    }
}
