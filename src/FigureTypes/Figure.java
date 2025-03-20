package FigureTypes;

import Interfaces.*;
import Main.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public abstract class Figure implements Movable, Cloneable, Serializable {
    public static int min_distance_to_interact = 4;
    private ArrayList<Dot> dots = new ArrayList<>();

    protected Figure(int vertex_count)
    {
        dots.clear();
        double angle = 0;
        double step = 2 * 3.141592 / vertex_count;
        for (int i = 0; i < vertex_count; i++) {
            Dot temporary =  new Dot(this);
            temporary.x = (int) (900 / 2 + 100 * Math.cos(angle));
            temporary.y = (int) (800 / 2 + 100 * Math.sin(angle));
            dots.add(temporary);
            angle += step;
        }
    }
    synchronized public void move(int dot_nr, int offset_x, int offset_y)
    {
        if (offset_x > ScreenBounds.leftBound && offset_x < ScreenBounds.rightBound)
            dots.get(dot_nr).x = offset_x;
        if (offset_y > ScreenBounds.upperBound && offset_y < ScreenBounds.lowerBound)
            dots.get(dot_nr).y = offset_y;
    }

    synchronized public void move(Dot dot, int offset_x, int offset_y)
    {
        if (offset_x > ScreenBounds.leftBound && offset_x < ScreenBounds.rightBound)
            dot.x = offset_x;
        if (offset_y > ScreenBounds.upperBound && offset_y < ScreenBounds.lowerBound)
            dot.y = offset_y;
    }

    synchronized public Object clone() throws CloneNotSupportedException
    {
        Figure figure = (Figure)super.clone();
        figure.dots = (ArrayList<Dot>) dots.clone();
        return (Object)figure;
    }
    public final ArrayList<Dot> get_dots()
    {
        return dots;
    }
    synchronized public final void set_dots(ArrayList<Dot> new_dots)
    {
        dots = new_dots;
    }
    @Override
    public abstract String toString();
    public void paint(Graphics g) {
        for(int i = 0; i  < dots.size() - 1; ++i)
        {
            g.drawLine((int)dots.get(i).x, (int)dots.get(i).y,
                    (int)dots.get(i + 1).x, (int)dots.get(i + 1).y);
        }
        g.drawLine((int)dots.get(0).x, (int)dots.get(0).y,
                (int)dots.get(dots.size() - 1).x, (int)dots.get(dots.size() - 1).y);
    }

    public int getClosestDot(double x_coord, double y_coord)
    {
        for(int i = 0; i < dots.size(); i++)
        {
            int x_proj = (int)(x_coord - get_dots().get(i).x);
            int y_proj = (int)(y_coord - get_dots().get(i).y);
            double distance = Math.sqrt(((x_proj)*(x_proj)) + ((y_proj)*(y_proj)));
            if(distance <= min_distance_to_interact)
            {
                return i;
            }
        }
        return -1;
    }

    synchronized public boolean checkMerging(Dot dot)
    {
        int close_dot = getClosestDot(dot.x, dot.y);
        if(close_dot != -1 && dots.get(close_dot) != dot)
        {
            Dot.dots.remove(dots.get(close_dot));
            dot.restrainingFigures.addAll(dots.get(close_dot).restrainingFigures);
            dots.set(close_dot, dot);
            return true;
        }
        return false;
    }

    public boolean equals(Figure figure)
    {
        if(dots.size() != figure.dots.size())
            return false;
        for(int i = 0; i < dots.size(); i++)
            if(!figure.dots.contains(dots.get(i)))
                return false;
        return true;
    }
}
