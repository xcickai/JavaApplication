package dsw.gerumap.app.gui.swing.tree.view.graphics.elements;

import dsw.gerumap.app.gui.swing.tree.view.MindMapDiagramView;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramElementPainter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MindMapDiagramLine extends MindMapDiagramElement {
    private Point from;
    private Point to;
    private float linkWidth = 2;

    public MindMapDiagramLine(Point from, Point to) {
        super(new Color(java.awt.Color.DARK_GRAY));
        this.from = from;
        this.to = to;
    }

    @Override
    public DiagramElementPainter createPainter(MindMapDiagramView activeMindMapDiagram) {
        return null;
    }
}
