package InputManagers;

import FigureTypes.*;
import Main.Displayer;
import Main.Dot;
import Main.PreviousStateHolder;
import Main.ScreenBounds;

import java.awt.event.*;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {
    Displayer displayer;
    public MouseManager(Displayer displayer)
    {
        this.displayer = displayer;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if(displayer.mode == 0)
        {
            displayer.dotSelected = 0;
            for(int i = 0; i < displayer.figures.size(); i++) {
                displayer.dotSelected = displayer.figures.get(i).getClosestDot(e.getX(), e.getY()) + 1;
                if (displayer.dotSelected != 0) {
                    displayer.figureSelected = i;
                    break;
                }
            }
        }
        else if(displayer.mode == 1)
        {
            Figure line = new Line();
            line.move(0, e.getX(), e.getY());
            line.move(1, e.getX(), e.getY());
            displayer.figureSelected = displayer.figures.size();
            displayer.figures.add(line);
            for(int i = 0; i < displayer.figures.size(); i++)
            {
                displayer.figures.get(i).checkMerging(displayer.figures.get(displayer.figureSelected).get_dots().get(0));
            }
            displayer.dotSelected = 2;
        }
        else if(displayer.mode == 2) {
            Circle circle = new Circle();
            circle.move(0, e.getX() - 1, e.getY() - 1);
            circle.move(circle.get_dots().size() / 2, e.getX(), e.getY());
            displayer.figureSelected = displayer.figures.size();
            displayer.figures.add(circle);
            for(int i = 0; i < displayer.figures.size(); i++)
            {
                displayer.figures.get(i).checkMerging(displayer.figures.get(displayer.figureSelected).get_dots().get(0));
            }
            displayer.dotSelected = 3;
        }
        else if(displayer.mode == 3)
        {
            Square square = new Square();
            square.move(0, e.getX() - 1, e.getY() - 1);
            square.move(2, e.getX(), e.getY());
            displayer.figureSelected = displayer.figures.size();
            displayer.figures.add(square);
            for(int i = 0; i < displayer.figures.size(); i++)
            {
                displayer.figures.get(i).checkMerging(displayer.figures.get(displayer.figureSelected).get_dots().get(0));
            }
            displayer.dotSelected = 3;
        }
        else if(displayer.mode == 4)
        {
            Triangle triangle = new Triangle();
            triangle.move(0, e.getX() - 1, e.getY() - 1);
            triangle.move(1, e.getX(), e.getY());
            displayer.figureSelected = displayer.figures.size();
            displayer.figures.add(triangle);
            for(int i = 0; i < displayer.figures.size(); i++)
            {
                displayer.figures.get(i).checkMerging(displayer.figures.get(displayer.figureSelected).get_dots().get(0));
            }
            displayer.dotSelected = 2;
        }
        else {
            System.out.println("Unknown state reached");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(displayer.dotSelected != 0)
        {
            for(int i = 0; i < displayer.figures.size(); i++)
            {
                if(i != displayer.figureSelected && displayer.figures.get(i).checkMerging(displayer.figures.get(displayer.figureSelected).get_dots().get(displayer.dotSelected - 1)))
                {
                    for(int i2 = 0; i2 < displayer.figures.size(); i2++)
                    {
                        if(i != i2 && displayer.figures.get(i2).equals(displayer.figures.get(i)))
                        {
                            displayer.figures.remove(i2);
                        }
                    }
                }
            }
            PreviousStateHolder.addState(displayer.figures);
        }
        displayer.dotSelected = 0;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(displayer.dotSelected != 0)
        {
            displayer.figures.get(displayer.figureSelected).get_dots().get(displayer.dotSelected - 1).move(e.getX(), e.getY());
            displayer.canvas.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(displayer.dotSelected != 0)
        {
            displayer.figures.get(displayer.figureSelected).get_dots().get(displayer.dotSelected - 1).move(e.getX(), e.getY());
            displayer.canvas.repaint();
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        double scale_factor = Math.pow(1.05, e.getWheelRotation());
        Dot.scale(scale_factor,
                (ScreenBounds.rightBound - ScreenBounds.leftBound) / 2,
                (ScreenBounds.lowerBound - ScreenBounds.upperBound) / 2);
        displayer.canvas.repaint();
    }
}
