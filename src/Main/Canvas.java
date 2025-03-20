package Main;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    private Displayer displayer;
    public Canvas(Displayer displayer)
    {
        this.displayer = displayer;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(int i = 0; i < displayer.figures.size(); i++)
        {
            displayer.figures.get(i).paint(g);
        }
    }
}
