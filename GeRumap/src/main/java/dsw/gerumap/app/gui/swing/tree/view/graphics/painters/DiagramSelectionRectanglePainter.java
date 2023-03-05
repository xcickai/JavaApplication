package dsw.gerumap.app.gui.swing.tree.view.graphics.painters;

import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramElement;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramSelectionRectangle;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class DiagramSelectionRectanglePainter extends DiagramElementPainter{
    private MindMapDiagramSelectionRectangle rectangle;

    public DiagramSelectionRectanglePainter(MindMapDiagramSelectionRectangle rectangle) {
        super(rectangle);
        this.rectangle = rectangle;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getElement().getColor());
        g2.setStroke(new BasicStroke(getRectangle().getLineWidth()));
        g2.drawRect(getRectangle().getSelectionRectangle().x,
                getRectangle().getSelectionRectangle().y,
                getRectangle().getSelectionRectangle().width,
                getRectangle().getSelectionRectangle().height);
    }

    @Override
    public boolean elementAt(MindMapDiagramElement element, Point pos) {
        return getShape().contains(pos);
    }

}
