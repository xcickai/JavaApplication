package dsw.gerumap.app.gui.swing.tree.view.graphics.elements;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Point extends java.awt.Point {
    @Expose
    public int X;
    @Expose
    public int Y;

    public Point() {
        this(0, 0);
    }
    public Point(java.awt.Point p) {
        this(p.x, p.y);
    }
    public Point(Point p) { this(p.X, p.Y); }
    public Point(int x, int y) {
        super(x, y);
        this.X = x;
        this.Y = y;
    }
    public double getX() {
        return X;
    }
    public double getY() {
        return Y;
    }
}
