package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import FigureTypes.Figure;
import InputManagers.KeyManager;
import InputManagers.MouseManager;
import InputManagers.UIManager;

public class Displayer extends JApplet {
    private static JFrame frame = new JFrame();
    private boolean initialized = false;

    public Canvas canvas = new Canvas(this);
    private KeyManager keyManager = new KeyManager(this);
    private MouseManager mouseManager = new MouseManager(this);

    private UIManager uiManager = new UIManager(this);
    public ArrayList<Figure> figures = new ArrayList<Figure>();
    public int dotSelected = 0;
    public int figureSelected = 0;
    public int mode = 0;
    public void saveFrameAsPNG()
    {
        BufferedImage im = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        canvas.paint(im.getGraphics());
        try
        {
            ImageIO.write(im, "PNG", new File("figures.png"));
        }
        catch (Exception e){System.out.println("Unexpected error: Failed to save the image");}
    }
    public void saveFrame() {
        ArrayList<Figure> saved_figures = (ArrayList<Figure>)figures.clone();
        SavingThread savingThread = new SavingThread(saved_figures);
        savingThread.start();
    }
    synchronized public void loadFrame() throws IOException
    {
        figures.clear();
        FileInputStream file = null;
        try
        {
            file = new FileInputStream("figures.bin");
        }
        catch (IOException ioe)
        {
            System.out.println("File failed to open");
            System.out.println(ioe.getMessage());
            System.out.println(ioe.getStackTrace());
            System.exit(1);
        }
        try
        {
            file = new FileInputStream("figures.bin");
        }
        catch (IOException ioe)
        {
            System.out.println("File failed to open");
            System.out.println(ioe.getMessage());
            System.out.println(ioe.getStackTrace());
            System.exit(1);
        }
        ObjectInputStream input = new ObjectInputStream(file);

        try {
            Dot.dots = (ArrayList<Dot>) input.readObject();
            while(true)
            {
                figures.add((Figure) input.readObject());
            }
        }
        catch (EOFException eofe) {}
        catch (ClassNotFoundException cnfe)
        {
            System.out.println(cnfe.getMessage());
            System.out.println(cnfe.getStackTrace());
        }
        input.close();
        file.close();
        if(initialized == false)
        {
            setFrame();
        }
        else canvas.repaint();
    }
    public void setFrame(ArrayList<Figure> figures)
    {
        this.figures = figures;
        setFrame();
    }

    public void setFrame()
    {
        initialized = true;
        PreviousStateHolder.addState(this.figures);

        JPanel main_panel = new JPanel();
        main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.X_AXIS));
        frame.add(main_panel);
        add(main_panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas.setBounds(200, 100, 500, 50);
        canvas.setPreferredSize(new Dimension(1900, 500));
        canvas.setBackground(Color.white);
        main_panel.add(canvas);

        frame.add(this);

        frame.addKeyListener(keyManager);
        frame.addMouseListener(mouseManager);
        canvas.addMouseListener(mouseManager);
        canvas.addMouseMotionListener(mouseManager);
        canvas.addMouseWheelListener(mouseManager);
        canvas.addKeyListener(keyManager);
        addMouseListener(mouseManager);
        frame.addMouseMotionListener(mouseManager);
        frame.addMouseWheelListener(mouseManager);
        addMouseMotionListener(mouseManager);
        addMouseWheelListener(mouseManager);

        main_panel.add(uiManager.button_panel);

        frame.setSize(ScreenBounds.rightBound - ScreenBounds.leftBound, ScreenBounds.lowerBound - ScreenBounds.upperBound);
        frame.setVisible(true);
    }
}
