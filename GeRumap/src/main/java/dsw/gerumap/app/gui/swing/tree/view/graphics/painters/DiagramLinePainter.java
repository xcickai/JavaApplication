package dsw.gerumap.app.gui.swing.tree.view.graphics.painters;

import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramElement;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramLine;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramLink;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class DiagramLinePainter extends DiagramElementPainter {
    private MindMapDiagramLine line;

    public DiagramLinePainter(MindMapDiagramLine line) {
        super(line);
        this.line = line;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getElement().getColor());
        g2.setStroke(new BasicStroke(getLine().getLinkWidth()));
        Point from = new Point(getLine().getFrom());
        Point to = new Point(getLine().getTo());
        g2.drawLine(from.x, from.y, to.x, to.y);

    }


    @Override
    public boolean elementAt(MindMapDiagramElement element, Point pos) {
        return getShape().contains(pos);
    }

}
