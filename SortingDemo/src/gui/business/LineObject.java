package gui.business;

import java.awt.*;

public class LineObject {
    int x1;
    int y1;
    int x2;
    int y2;


    public LineObject(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void draw(Graphics g, int tmp1, int tmp2) {
        g.drawLine(x1+tmp1, y1+tmp2, x2+tmp1, y2+tmp2);
    }
}
