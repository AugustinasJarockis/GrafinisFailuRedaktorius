package Main;

import FigureTypes.Figure;

import java.util.ArrayList;

public abstract class PreviousStateHolder {
    private static ArrayList<ArrayList<Figure>> states = new ArrayList<>();
    private static ArrayList<ArrayList<Dot>> dotStates = new ArrayList<>();
    public static void addState(ArrayList<Figure> state)
    {
        states.add((ArrayList<Figure>) state.clone());
        dotStates.add(new ArrayList<Dot>());
        for(int i = 0; i < Dot.dots.size(); i++)
        {
            try
            {
                dotStates.get(dotStates.size() - 1).add((Dot)Dot.dots.get(i).clone());
            }
            catch (CloneNotSupportedException cnse)
            {
                System.out.println("Unable to clone dots");
                System.out.println(cnse.getStackTrace());
                System.exit(1);
            }
        }
    }

    public static ArrayList<Figure> returnState()
    {
        int currentFigureCount = states.get(states.size() - 1).size();
        Figure figureMayBeRemoved = states.get(states.size() - 1).get(states.get(states.size() - 1).size() - 1);
        if(states.size() > 1)
        {
            states.remove(states.size() - 1);
            dotStates.remove(dotStates.size() - 1);
        }

        if( currentFigureCount == states.get(states.size() - 1).size())
        {
            for(int i = 0; i < dotStates.get(dotStates.size() - 1).size(); i++)
            {
                Dot.dots.get(i).x = dotStates.get(dotStates.size() - 1).get(i).x;
                Dot.dots.get(i).y = dotStates.get(dotStates.size() - 1).get(i).y;
            }
        }
        else
        {
            for(int stateNr = 0; stateNr < dotStates.size(); stateNr++)
            {
                for(int i = 0; i < dotStates.get(stateNr).size(); i++)
                {
                    dotStates.get(stateNr).get(i).restrainingFigures.remove(figureMayBeRemoved);
                    if(dotStates.get(stateNr).get(i).restrainingFigures.size() == 0)
                    {
                        dotStates.get(stateNr).remove(i);
                    }
                }
            }
            Dot.dots = dotStates.get(dotStates.size() - 1);
        }
        return states.get(states.size() - 1);
    }
}
