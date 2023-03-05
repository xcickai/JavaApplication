package dsw.gerumap.app.gui.swing.tree.view.graphics.elements;

import com.google.gson.annotations.Expose;
import dsw.gerumap.app.gui.swing.tree.view.MindMapDiagramView;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramElementPainter;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramLinkPainter;
import dsw.gerumap.app.serializer.ElementType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class MindMapDiagramLink extends MindMapDiagramElement{
    @Expose
    private MindMapDiagramConcept from;
    @Expose
    private MindMapDiagramConcept to;
    @Expose
    private float linkWidth = 3;

    public MindMapDiagramLink(MindMapDiagramConcept from, MindMapDiagramConcept to){
        super(new Color(java.awt.Color.DARK_GRAY));
        elementType = ElementType.LINK;
        this.from = from;
        this.to = to;
    }

    @Override
    public DiagramElementPainter createPainter(MindMapDiagramView activeMindMapDiagram) {
//        this.from = ((MindMapDiagramConcept)this.from.createPainter(activeMindMapDiagram).getElement());
//        this.to = ((MindMapDiagramConcept)this.to.createPainter(activeMindMapDiagram).getElement());
        List<MindMapDiagramElement> elementList = activeMindMapDiagram.getElementsToSerialize();
        this.from = (MindMapDiagramConcept) elementList.get(elementList.indexOf(this.from));
        this.to = (MindMapDiagramConcept) elementList.get(elementList.indexOf(this.to));
        return new DiagramLinkPainter(this);
    }
}