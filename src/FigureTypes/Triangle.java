package FigureTypes;

import Main.Dot;

public class Triangle extends Figure{
    public Triangle()
    {
        super(3);
    }
    synchronized public void move(int dot_nr, int offset_x, int offset_y)
    {
        get_dots().get(dot_nr).x = offset_x;
        get_dots().get(dot_nr).y = offset_y;

        int previousDot = (dot_nr + 2) % 3;
        int nextDot = (dot_nr + 1) % 3;

        double middleX = (get_dots().get(dot_nr).x + get_dots().get(previousDot).x) / 2;
        double middleY = (get_dots().get(dot_nr).y + get_dots().get(previousDot).y) / 2;

        double midToDotVectorX = get_dots().get(dot_nr).x - middleX;
        double midToDotVectorY = get_dots().get(dot_nr).y - middleY;

        get_dots().get(nextDot).x = middleX - midToDotVectorY * 1.73205;
        get_dots().get(nextDot).y = middleY + midToDotVectorX * 1.73205;
    }
    synchronized public void move(Dot dot, int offset_x, int offset_y)
    {
        dot.x = offset_x;
        dot.y = offset_y;

        int dot_nr = get_dots().indexOf(dot);

        int previousDot = (dot_nr + 2) % 3;
        int nextDot = (dot_nr + 1) % 3;

        double middleX = (dot.x + get_dots().get(previousDot).x) / 2;
        double middleY = (dot.y + get_dots().get(previousDot).y) / 2;

        double midToDotVectorX = dot.x - middleX;
        double midToDotVectorY = dot.y - middleY;

        get_dots().get(nextDot).x = middleX - midToDotVectorY * 1.73205;
        get_dots().get(nextDot).y = middleY + midToDotVectorX * 1.73205;
    }

    @Override
    public String toString() {
        return "Triangle: [" + get_dots().get(0).x + "; " + get_dots().get(0).y + "]," +
                " [" + get_dots().get(1).x + "; " + get_dots().get(1).y + "]," +
                " [" + get_dots().get(2).x + "; " + get_dots().get(2).y + "]. ";
    }
}

