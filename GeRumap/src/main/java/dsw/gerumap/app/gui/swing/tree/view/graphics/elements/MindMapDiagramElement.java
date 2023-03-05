package dsw.gerumap.app.gui.swing.tree.view.graphics.elements;

import com.google.gson.annotations.Expose;
import dsw.gerumap.app.gui.swing.tree.view.MindMapDiagramView;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramElementPainter;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.serializer.ElementType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class MindMapDiagramElement extends MapNode {
    @Expose
    Color color;
    @Expose
    ElementType elementType;

    public MindMapDiagramElement(Color color) {
        this.color = color;
        this.elementType = null;
    }

    public java.awt.Color getColor() {
        return color.getColor();
    }

    public abstract DiagramElementPainter createPainter(MindMapDiagramView activeMindMapDiagram);
}