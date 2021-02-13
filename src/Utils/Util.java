/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Imad
 */
public class Util 
{
    public static final int DEBUT_POINT = 2 ;
    public static final int END_POINT = 3 ;
    
    
    public static void print_array_2D (int mat[][])
    {
        int i , j ;
        for ( i = 0 ; i < mat.length ; i++ )
        {
            for ( j = 0 ; j < mat[0].length ; j++ )
            {
                System.out.print(mat[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    public static Point get_Point (int mat[][] , int type)
    {
        Point p = null;
        int i , j ;
        
        for ( i = 0 ; i < mat.length ; i++ )
        {
            for ( j = 0 ; j < mat[0].length ; j++ )
            {
                if (mat[i][j] == type)
                {
                    p = new Point(i,j);
                    break;
                }
            }
            if (p != null)
                break ;
        }
        
        return p;
    }
    public static ArrayList<ArrayList <Integer>> getAjacentList (int mat[][])
    {
        int i , j ;
        ArrayList<ArrayList <Integer>> list = new ArrayList<>();
        for ( i = 0 ; i < mat.length ; i++ )
        {
            for ( j = 0 ; j < mat[0].length ; j++ )
            {
                ArrayList <Integer> sub_list = new ArrayList<Integer>();
                if (mat[i][j] != 1)
                {
                    // First Point == UP //
                    if (i > 0)
                    {
                        if (mat[i-1][j] != 1)
                        {
                            sub_list.add((i-1)*mat[0].length + j);
                        }
                    }
                    // Secend Point == DOWN //
                    if (i < mat.length-1)
                    {
                        if (mat[i+1][j] != 1)
                        {
                            sub_list.add((i+1)*mat[0].length + j);
                        }
                    }
                    // Third Point == LEFT //
                    if (j > 0)
                    {
                        if (mat[i][j-1] != 1)
                        {
                            sub_list.add(i*mat[0].length + (j-1));
                        }
                    }
                    // Fourth Point == LEFT //
                    if (j < mat[0].length-1)
                    {
                        if (mat[i][j+1] != 1)
                        {
                            sub_list.add(i*mat[0].length + (j+1));
                        }
                    }
                    
                    list.add(new ArrayList<>(sub_list));
                }else
                {
                    list.add(new ArrayList<>());
                }
            }
        }
        return list ;
    }
    
    public static int [] ArrayList_To_Array (ArrayList<Integer> l)
    {
        int Tab [] = new int[l.size()];
        int i ;
        for ( i = 0 ; i < l.size() ; i++ )
        {
            Tab[i] = l.get(i);
        }
        return Tab;
    }
    
    public static int [] initTab (int size , int value)
    {
        int Tab[] = new int [size];
        int i ; 
        for ( i = 0 ; i < Tab.length ; i++ )
        {
            Tab[i] = value;
        }
        return Tab ;
    }
    
    public static int [] getPath (int [] prev , int s , int e)
    {
        ArrayList <Integer> path = new ArrayList<>();
        int i ;
        for ( i = e ; i != -1 ; i = prev[i])
        {
            path.add(i);
        }    
        
        int Tab_Path [] = new int [path.size()];
        int cpt = 0 ;
        for (i = Tab_Path.length-1 ; i > -1  ; i-- )
        {
            Tab_Path[cpt++] = path.get(i);
        }
        
        if (Tab_Path[0] == s)
        {
            return Tab_Path;
        }else
        {
            return new int[0];
        }
    }
    
    public static int [][] clear_Tab (int mat[][])
    {
        int ret [][] = new int [mat.length][mat[0].length];
        int i , j ; 
        for (i = 0 ; i < mat.length ; i++ )
        {
            for ( j  = 0 ; j < mat[0].length ; j ++ )
            {
                if(mat[i][j] < 4)
                {
                    ret[i][j] = mat[i][j];
                }else
                {
                    ret[i][j] = 0 ;
                }
            }
        }
        return ret ;
    }
    
}
