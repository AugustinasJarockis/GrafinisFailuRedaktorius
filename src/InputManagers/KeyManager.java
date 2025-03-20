package InputManagers;

import Main.Displayer;
import Main.Dot;
import Main.PreviousStateHolder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
public class KeyManager implements KeyListener {
    Displayer displayer;

    boolean ctrlPressed = false;
    public KeyManager(Displayer displayer)
    {
        this.displayer = displayer;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case 17:
                ctrlPressed = true;
                break;
            case 68:
                displayer.mode = 1;
                break;
            case 76: //L - Load
                synchronized (this)
                {
                    if(ctrlPressed)
                    {
                        try
                        {
                            displayer.loadFrame();
                        }
                        catch (IOException ioe){
                            System.out.println(ioe.getMessage());
                            System.out.println(ioe.getStackTrace());
                        }
                    }
                }
                break;
            case 83: //S - Save
                if(ctrlPressed)
                {
                    displayer.saveFrame();
                }
                break;
            case 90: //Z - Undo
                if(ctrlPressed && displayer.figures.size() != 0)
                {
                    displayer.figures = PreviousStateHolder.returnState();
                    displayer.repaint();
                }
                break;
        }

        if(displayer.dotSelected == 0)
        {
            switch(e.getKeyCode())
            {
                case 38: //Up arrow - move all down
                    Dot.moveAll(0,5);
                    displayer.canvas.repaint();
                    break;
                case 37: //Left arrow - move all right
                    Dot.moveAll(5,0);
                    displayer.canvas.repaint();
                    break;
                case 39: //Right arrow - move all left
                    Dot.moveAll(-5,0);
                    displayer.canvas.repaint();
                    break;
                case 40: //Down arrow - move all up
                    Dot.moveAll(0,-5);
                    displayer.canvas.repaint();
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case 17:
                ctrlPressed = false;
                break;
            case 68:
                displayer.mode = 0;
                break;
        }
    }
}
