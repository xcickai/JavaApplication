package dsw.gerumap.app.gui.swing.tree.view.graphics.elements;

import com.google.gson.annotations.Expose;
import dsw.gerumap.app.gui.swing.tree.view.MindMapDiagramView;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramConceptPainter;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramElementPainter;
import dsw.gerumap.app.gui.swing.tree.view.graphics.painters.DiagramLinkPainter;
import dsw.gerumap.app.serializer.ElementType;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class MindMapDiagramConcept extends MindMapDiagramElement {
    @Expose
    private UUID uuid;
    @Expose
    private String topicName;
    @Expose
    private int size;
    @Expose
    private Point position;
    private Boolean isSelected;
    @Expose
    private float lineWidth = 3;
    private Rectangle selectionRectangle;
    @Expose
    private int width;
    @Expose
    private int height;
    protected int minWidth;


    public MindMapDiagramConcept(String name, Point position){
        super(new Color(java.awt.Color.BLACK));
        this.uuid = UUID.randomUUID();
        this.elementType = ElementType.CONCEPT;
        this.topicName = name;
        this.position = position;
        this.isSelected = false;
        this.size = name.length() + 10;

        width = 200;
        minWidth = width;
        height = 100;
        selectionRectangle = new Rectangle(width, height);
        selectionRectangle.setLocation(getMiddlePoint());
        this.setPosition(getMiddlePoint());
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    private Point getMiddlePoint() {
        return new Point(getPosition().X - getWidth() / 2,
                getPosition().Y - getHeight() / 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MindMapDiagramConcept that = (MindMapDiagramConcept) o;
        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, topicName);
    }

    @Override
    public DiagramElementPainter createPainter(MindMapDiagramView activeMindMapDiagram) {
        this.isSelected = false;
        this.minWidth = 200;
        this.selectionRectangle = new Rectangle(width, height);
        this.selectionRectangle.setLocation(getMiddlePoint());
        this.setPosition(getMiddlePoint());
        return new DiagramConceptPainter(this);
    }

    public List<MindMapDiagramLink> getConnectedLinks(List<DiagramLinkPainter> fromLinkList){
        List<MindMapDiagramLink> connectedLinks = new ArrayList<>();
        for(DiagramLinkPainter link : fromLinkList) {
            if (link.getLink().getFrom() == this || link.getLink().getTo() == this) {
                connectedLinks.add(link.getLink());
            }
        }
        return connectedLinks;
    }

}

