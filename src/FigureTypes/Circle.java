package FigureTypes;

import Main.Dot;
import Main.ScreenBounds;

public class Circle extends Figure{
    public Circle()
    {
        super(360);
    }

    synchronized public void move(int dot_nr, int offset_x, int offset_y)
    {
        get_dots().get(dot_nr).x = offset_x;
        get_dots().get(dot_nr).y = offset_y;

        int otherDot = (dot_nr + get_dots().size() / 2) % get_dots().size();

        double angle = 0;

        double previousOtherDotX = get_dots().get(otherDot).x;
        double previousOtherDotY = get_dots().get(otherDot).y;

        double middleX = (get_dots().get(dot_nr).x + get_dots().get(otherDot).x) / 2;
        double middleY = (get_dots().get(dot_nr).y + get_dots().get(otherDot).y) / 2;

        double rotationAngle = getAngleBetweenVectors(offset_x - middleX, offset_y - middleY, 1, 0);
        if(offset_y < middleY)
        {
            rotationAngle = 6.283185 - rotationAngle;
        }
        double radiusLength = Math.sqrt(Math.pow(offset_x - get_dots().get(otherDot).x, 2) + Math.pow(offset_y - get_dots().get(otherDot).y, 2)) / 2;

        double step = 2 * 3.141592 / get_dots().size();
        for (int i = 0; i < get_dots().size(); i++) {
            get_dots().get(i).x = (int) ((ScreenBounds.rightBound - ScreenBounds.leftBound) / 2 + radiusLength * Math.cos(angle + rotationAngle));
            get_dots().get(i).y = (int) ((ScreenBounds.lowerBound - ScreenBounds.upperBound) / 2 + radiusLength * Math.sin(angle + rotationAngle));
            angle += step;
        }

        double toMoveX = previousOtherDotX - get_dots().get(otherDot).x;
        double toMoveY = previousOtherDotY - get_dots().get(otherDot).y;

        for(int i = 0; i < get_dots().size(); i++)
        {
            get_dots().get(i).x += toMoveX;
            get_dots().get(i).y += toMoveY;
        }
    }

    synchronized public void move(Dot dot, int offset_x, int offset_y) {
        dot.x = offset_x;
        dot.y = offset_y;

        int dot_nr = get_dots().indexOf(dot);
        int otherDot = (dot_nr + get_dots().size() / 2) % get_dots().size();

        double angle = 0;

        double previousOtherDotX = get_dots().get(otherDot).x;
        double previousOtherDotY = get_dots().get(otherDot).y;

        double middleX = (dot.x + get_dots().get(otherDot).x) / 2;
        double middleY = (dot.y + get_dots().get(otherDot).y) / 2;

        double rotationAngle = getAngleBetweenVectors(-offset_x + middleX, -offset_y + middleY, 1, 0);
        if(offset_y > middleY)
        {
            rotationAngle = 6.283185 - rotationAngle;
        }

        double radiusLength = Math.sqrt(Math.pow(offset_x - get_dots().get(otherDot).x, 2) + Math.pow(offset_y - get_dots().get(otherDot).y, 2)) / 2;

        double step = 2 * 3.141592 / get_dots().size();
        for (int i = 0; i < get_dots().size(); i++) {
            get_dots().get((i + otherDot) % get_dots().size()).x = (int) ((ScreenBounds.rightBound - ScreenBounds.leftBound) / 2 + radiusLength * Math.cos(angle + rotationAngle));
            get_dots().get((i + otherDot) % get_dots().size()).y = (int) ((ScreenBounds.lowerBound - ScreenBounds.upperBound) / 2 + radiusLength * Math.sin(angle + rotationAngle));
            angle += step;
        }

        double toMoveX = previousOtherDotX - get_dots().get(otherDot).x;
        double toMoveY = previousOtherDotY - get_dots().get(otherDot).y;

        for(int i = 0; i < get_dots().size(); i++)
        {
            get_dots().get(i).x += toMoveX;
            get_dots().get(i).y += toMoveY;
        }
    }
    private double getAngleBetweenVectors(double firstX, double firstY, double secondX, double secondY)
    {
        return Math.acos(Math.round((((firstX * secondX) + (firstY * secondY)) /
        ((Math.sqrt(Math.pow(firstX, 2) + Math.pow(firstY, 2))) * (Math.sqrt(Math.pow(secondX, 2) + Math.pow(secondY, 2))))) * 100000.0) / 100000.0);
    }
    @Override
    public String toString() {
        return "Square: [" + get_dots().get(0).x + "; " + get_dots().get(0).y + "]," +
                " [" + get_dots().get(1).x + "; " + get_dots().get(1).y + "]," +
                " [" + get_dots().get(2).x + "; " + get_dots().get(2).y + "]," +
                " [" + get_dots().get(3).x + "; " + get_dots().get(3).y + "]. ";
    }
}

