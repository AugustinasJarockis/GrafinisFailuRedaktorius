package Main;

import FigureTypes.Figure;

import java.io.Serializable;
import java.util.ArrayList;

public class Dot implements Serializable, Cloneable {
    public static ArrayList<Dot> dots = new ArrayList<Dot>();
    public ArrayList<Figure> restrainingFigures = new ArrayList<Figure>();
    public double x;
    public double y;
    public Dot() {dots.add(this);}
    public Dot(Figure restrainingFigure, int x, int y)
    {
        this(restrainingFigure);
        this.x = x;
        this.y = y;
    }

    public Dot(Figure restrainingFigure) {
        dots.add(this);
        restrainingFigures.add(restrainingFigure);
    }
    public Dot(int x, int y)
    {
        this();
        this.x = x;
        this.y = y;
    }
    synchronized public void move(int offset_x, int offset_y)
    {
        for(int i = 0; i < restrainingFigures.size(); i++)
        {
            restrainingFigures.get(i).move(this, offset_x, offset_y);
        }
    }
    synchronized public static void moveAll(int offset_x, int offset_y)
    {
        for(int i = 0; i < dots.size(); i++)
        {
            dots.get(i).x += offset_x;
            dots.get(i).y += offset_y;
        }
    }
    synchronized public static void moveAbsAll(int offset_x, int offset_y)
    {
        for(int i = 0; i < dots.size(); i++)
        {
            dots.get(i).x = offset_x;
            dots.get(i).y = offset_y;
        }
    }
    synchronized public static void scale(double factor, int middle_x, int middle_y)
    {
        for(int i = 0; i < dots.size(); i++)
        {
            dots.get(i).x = (dots.get(i).x  - middle_x) * factor + middle_x;
            dots.get(i).y = (dots.get(i).y  - middle_y) * factor + middle_y;
        }
        boolean dotsTooClose = false;
        for (int i = 0; i < dots.size(); i++)
        {
            for (int i2 = 0; i2 < dots.size(); i2++)
            {
                if(i != i2
                        && Math.abs(dots.get(i).x - dots.get(i2).x) <= Figure.min_distance_to_interact
                        && Math.abs(dots.get(i).y - dots.get(i2).y) <= Figure.min_distance_to_interact)
                {
                    dotsTooClose = true;
                    break;
                }
            }
        }
        if(dotsTooClose == true)
        {
            for(int i = 0; i < dots.size(); i++)
            {
                dots.get(i).x = (dots.get(i).x  - middle_x) * (1 / factor) + middle_x;
                dots.get(i).y = (dots.get(i).y  - middle_y) * (1 / factor) + middle_y;
            }
        }
    }
    synchronized public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
