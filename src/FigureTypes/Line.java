package FigureTypes;

public class Line extends Figure{
    public Line()
    {
        super(2);
    }

    @Override
    public String toString() {
        return "Line: [" + get_dots().get(0).x + "; " + get_dots().get(0).y + "]," +
                " [" + get_dots().get(1).x + "; " + get_dots().get(1).y + "]. ";
    }
}
