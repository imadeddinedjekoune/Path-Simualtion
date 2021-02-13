/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze;

/**
 *
 * @author Imad
 */
public class Point 
{
    Integer r , c;
    Point parent;

    public Point(int x, int y, Point p) {
        r = x;
        c = y;
        parent = p;
    }
    public Point opposite()
    {
        if (this.r.compareTo(parent.r) != 0)
            return new Point(this.r + this.r.compareTo(parent.r), this.c, this);
        if (this.c.compareTo(parent.c) != 0)
            return new Point(this.r, this.c + this.c.compareTo(parent.c), this);
        return null;
    }
}

