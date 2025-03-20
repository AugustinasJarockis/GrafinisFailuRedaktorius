package Interfaces;

import Main.Dot;

public interface Movable {
    public void move(int dot_nr, int offset_x, int offset_y);
    public void move(Dot dot, int offset_x, int offset_y);
}
