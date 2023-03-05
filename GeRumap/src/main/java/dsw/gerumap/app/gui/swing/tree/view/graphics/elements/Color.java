package dsw.gerumap.app.gui.swing.tree.view.graphics.elements;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Color {
    @Expose
    int R;
    @Expose
    int G;
    @Expose
    int B;

    public Color(int r, int g, int b) {
        this.R = r;
        this.G = g;
        this.B = b;
    }

    public Color(java.awt.Color color) {
        this.R = color.getRed();
        this.G = color.getGreen();
        this.B = color.getBlue();
    }

    public java.awt.Color getColor() {
        return new java.awt.Color(R, G, B);
    }
}