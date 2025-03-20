package Main;

import FigureTypes.Figure;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SavingThread extends Thread{
    ArrayList<Figure> saved_figures = null;
    public SavingThread(ArrayList<Figure> saved_figures)
    {
        this.saved_figures = saved_figures;
    }
    public void run()
    {
        save();
    }
    private void save()
    {
        synchronized ("figures.bin")
        {
            try
            {
                boolean tryAgain = false;
                FileOutputStream file = null;
                do {
                    tryAgain = false;
                    try
                    {
                        file = new FileOutputStream("figures.bin");
                    }
                    catch (IOException ioe)
                    {
                        System.out.println("File failed to open");
                        file = null;
                        tryAgain = true;
                    }
                }
                while(tryAgain);
                ObjectOutputStream output = new ObjectOutputStream(file);
                output.writeObject(Dot.dots);
                for(int i = 0; i < saved_figures.size(); i++)
                {
                    output.writeObject(saved_figures.get(i));
                }
                output.close();
                file.close();
            }
            catch(IOException exp)
            {
                System.out.println(exp.getMessage());
                System.out.println(exp.getStackTrace());
            }
        }
    }
}
