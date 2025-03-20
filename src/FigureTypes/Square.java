package FigureTypes;

import Main.Dot;

public class Square extends Figure{
    public Square()
    {
        super(4);
    }
    synchronized public void move(int dot_nr, int offset_x, int offset_y)
    {
        get_dots().get(dot_nr).x = offset_x;
        get_dots().get(dot_nr).y = offset_y;

        int otherDot = (dot_nr + 2) % 4;
        int previousDot = (dot_nr + 3) % 4;
        int nextDot = (dot_nr + 1) % 4;

        double middleX = (get_dots().get(dot_nr).x + get_dots().get(otherDot).x) / 2;
        double middleY = (get_dots().get(dot_nr).y + get_dots().get(otherDot).y) / 2;

        double midToDotVectorX = get_dots().get(dot_nr).x - middleX;
        double midToDotVectorY = get_dots().get(dot_nr).y - middleY;

        get_dots().get(previousDot).x = middleX - midToDotVectorY;
        get_dots().get(previousDot).y = middleY + midToDotVectorX;

        get_dots().get(nextDot).x = middleX + midToDotVectorY;
        get_dots().get(nextDot).y = middleY - midToDotVectorX;
    }

    synchronized public void move(Dot dot, int offset_x, int offset_y)
    {
        dot.x = offset_x;
        dot.y = offset_y;

        int dot_nr = get_dots().indexOf(dot);

        int otherDot = (dot_nr + 2) % 4;
        int previousDot = (dot_nr + 3) % 4;
        int nextDot = (dot_nr + 1) % 4;

        double middleX = (dot.x + get_dots().get(otherDot).x) / 2;
        double middleY = (dot.y + get_dots().get(otherDot).y) / 2;

        double midToDotVectorX = dot.x - middleX;
        double midToDotVectorY = dot.y - middleY;

        get_dots().get(previousDot).x = middleX - midToDotVectorY;
        get_dots().get(previousDot).y = middleY + midToDotVectorX;

        get_dots().get(nextDot).x = middleX + midToDotVectorY;
        get_dots().get(nextDot).y = middleY - midToDotVectorX;
    }
    @Override
    public String toString() {
        return "Square: [" + get_dots().get(0).x + "; " + get_dots().get(0).y + "]," +
                " [" + get_dots().get(1).x + "; " + get_dots().get(1).y + "]," +
                " [" + get_dots().get(2).x + "; " + get_dots().get(2).y + "]," +
                " [" + get_dots().get(3).x + "; " + get_dots().get(3).y + "]. ";
    }
}
