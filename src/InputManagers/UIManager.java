package InputManagers;

import Main.Displayer;
import Main.PreviousStateHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UIManager implements ActionListener {
    Displayer displayer;
    public JPanel button_panel = new JPanel();
    private final JButton save_button = new JButton("Save");
    private final JButton load_button = new JButton("Load");
    private final JButton save_as_png_button = new JButton("Save as .png");
    public JRadioButton adjust_button = new JRadioButton("Adjust", true);
    public JRadioButton line_button = new JRadioButton("Line");
    public  JRadioButton circle_button = new JRadioButton("Circle");
    public JRadioButton square_button = new JRadioButton("Square");
    public JRadioButton triangle_button = new JRadioButton("Triangle");

    public JButton undo_button = new JButton("Undo");
    public JButton clear_button = new JButton("Reset");

    public ButtonGroup buttonGroup = new ButtonGroup();

    public UIManager(Displayer displayer)
    {
        this.displayer = displayer;
        button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.Y_AXIS));

        Dimension buttonDimension = new Dimension(500, 30);

        save_button.setMaximumSize(buttonDimension);
        load_button.setMaximumSize(buttonDimension);
        save_as_png_button.setMaximumSize(buttonDimension);
        adjust_button.setMaximumSize(buttonDimension);
        line_button.setMaximumSize(buttonDimension);
        circle_button.setMaximumSize(buttonDimension);
        square_button.setMaximumSize(buttonDimension);
        triangle_button.setMaximumSize(buttonDimension);
        undo_button.setMaximumSize(buttonDimension);
        clear_button.setMaximumSize(buttonDimension);

        save_button.addActionListener(this);
        load_button.addActionListener(this);
        save_as_png_button.addActionListener(this);
        adjust_button.addActionListener(this);
        line_button.addActionListener(this);
        circle_button.addActionListener(this);
        square_button.addActionListener(this);
        triangle_button.addActionListener(this);
        undo_button.addActionListener(this);
        clear_button.addActionListener(this);

        save_button.setFocusable(false);
        load_button.setFocusable(false);
        save_as_png_button.setFocusable(false);
        adjust_button.setFocusable(false);
        line_button.setFocusable(false);
        circle_button.setFocusable(false);
        square_button.setFocusable(false);
        triangle_button.setFocusable(false);
        undo_button.setFocusable(false);
        clear_button.setFocusable(false);

        button_panel.add(save_button);
        button_panel.add(load_button);
        button_panel.add(save_as_png_button);
        button_panel.add(adjust_button);
        button_panel.add(line_button);
        button_panel.add(circle_button);
        button_panel.add(square_button);
        button_panel.add(triangle_button);
        button_panel.add(undo_button);
        button_panel.add(clear_button);

        buttonGroup.add(adjust_button);
        buttonGroup.add(line_button);
        buttonGroup.add(circle_button);
        buttonGroup.add(square_button);
        buttonGroup.add(triangle_button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == save_button)
        {
            displayer.saveFrame();
        }
        else if (e.getSource() == load_button)
        {
            try
            {
                displayer.loadFrame();
            }
            catch (IOException ioe)
            {
                System.out.println("Frame failed to load");
                System.out.println(ioe.getMessage());
                System.out.println(ioe.getStackTrace());
            }
        }
        else if (e.getSource() == save_as_png_button)
        {
            displayer.saveFrameAsPNG();
        }
        else if(e.getSource() == adjust_button)
        {
            displayer.mode = 0;
        }
        else if(e.getSource() == line_button)
        {
            displayer.mode = 1;
        }
        else if(e.getSource() == circle_button)
        {
            displayer.mode = 2;
        }
        else if(e.getSource() == square_button)
        {
            displayer.mode = 3;
        }
        else if(e.getSource() == triangle_button)
        {
            System.out.println("Here");
            displayer.mode = 4;
        }
        else if(e.getSource() == undo_button && displayer.figures.size() != 0)
        {
            displayer.figures = PreviousStateHolder.returnState();
            displayer.repaint();
        }
        else if(e.getSource() == clear_button)
        {
            displayer.figures.clear();
            PreviousStateHolder.addState(displayer.figures);
            displayer.repaint();
        }
    }
}
