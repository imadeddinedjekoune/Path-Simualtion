/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze;

import Utils.Util;
import java.util.ArrayList;

/**
 *
 * @author Imad
 */
public class Maze 
{
    private int r , c ;
    
    public Maze(int r , int c)
    {
        this.r = r ;
        this.c = c ;
    }
    
    public int [][] getMaze()
    {
        ArrayList <Integer> s = new ArrayList<>();
        for (int x = 0; x < c; x++)
        {
            s.add(1);
        }
        int[][] maz = new int[r][c];
        for (int x = 0; x < r; x++) 
        {
            maz[x] = Util.ArrayList_To_Array(s);
        }

        // select random point and open as start node
        Point st = new Point((int)(Math.random() * r), (int)(Math.random() * c), null);
        maz[st.r][st.c] = 2 ;

        // iterate through direct neighbors of node
        ArrayList < Point > frontier = (ArrayList < Point >) new ArrayList < Point > ();
        for (int x = -1; x <= 1; x++)
            for (int y = -1; y <= 1; y++) {
            if (x == 0 && y == 0 || x != 0 && y != 0)
                continue;
                try {
                 if (maz[st.r + x][st.c + y] == 0) continue;
                } catch (Exception e) { // ignore ArrayIndexOutOfBounds
                 continue;
                }
          // add eligible points to frontier
          frontier.add(new Point(st.r + x, st.c + y, st));
        }

        Point last = null;
        while (!frontier.isEmpty()) {

        // pick current node at random
        Point cu = frontier.remove((int)(Math.random() * frontier.size()));
        Point op = cu.opposite();
       try {
        // if both node and its opposite are walls
        if (maz[cu.r][cu.c] == 1) {
         if (maz[op.r][op.c] == 1) {

          // open path between the nodes
          maz[cu.r][cu.c] = 0;
          maz[op.r][op.c] = 0;

          // store last node in order to mark it later
          last = op;

          // iterate through direct neighbors of node, same as earlier
          for (int x = -1; x <= 1; x++)
           for (int y = -1; y <= 1; y++) {
            if (x == 0 && y == 0 || x != 0 && y != 0)
             continue;
            try {
             if (maz[op.r + x][op.c + y] == 0) continue;
            } catch (Exception e) {
             continue;
            }
            frontier.add(new Point(op.r + x, op.c + y, op));
           }
         }
        }
       } catch (Exception e) { // ignore NullPointer and ArrayIndexOutOfBounds
       }

       // if algorithm has resolved, mark end node
        if (frontier.isEmpty())
            maz[last.r][last.c] = 3;
        }

        return maz ;
    }
    
}
