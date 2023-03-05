package dsw.gerumap.app.gui.swing.tree.view.graphics.elements;

import dsw.gerumap.app.gui.swing.tree.view.MindMapDiagramView;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramElementPainter;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;


@Getter
@Setter
public class MindMapDiagramSelectionRectangle extends MindMapDiagramElement{
    private Rectangle selectionRectangle;
    private float lineWidth = 1;

    public MindMapDiagramSelectionRectangle(Rectangle r) {
        super(new Color(java.awt.Color.PINK));
        selectionRectangle = r;
    }

    @Override
    public DiagramElementPainter createPainter(MindMapDiagramView activeMindMapDiagram) {
        return null;
    }
}
