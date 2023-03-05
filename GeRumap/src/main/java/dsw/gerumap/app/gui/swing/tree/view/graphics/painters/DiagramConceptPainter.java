package dsw.gerumap.app.gui.swing.tree.view.graphics.painters;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramConcept;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramElement;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

import static dsw.gerumap.app.core.messageGenerator.EventType.CONCEPT_NOT_SELECTED;

@Getter
@Setter
public class DiagramConceptPainter extends DiagramElementPainter {
    private MindMapDiagramConcept concept;

    public DiagramConceptPainter(MindMapDiagramConcept concept) {
        super(concept);
        this.concept = concept;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(getConcept().getLineWidth()));
        g2.setColor(concept.getColor());
        g2.drawOval(concept.getPosition().X, concept.getPosition().Y, concept.getWidth(), concept.getHeight());

        Point startTextPosition = getMiddlePointForText(g);
        g2.drawString(concept.getTopicName(), startTextPosition.x, startTextPosition.y);
        FontMetrics metrics =  g2.getFontMetrics();

        int potentialNewWidth = metrics.stringWidth(concept.getTopicName()) + 12;
        if (potentialNewWidth > concept.getMinWidth()) {
            concept.setWidth(potentialNewWidth);
        } else {
            concept.setWidth(concept.getMinWidth());
        }

        if (concept.getIsSelected()) {
            g2.setColor(Color.GRAY);
            g2.setStroke(new BasicStroke(1));
            g2.drawRect(concept.getPosition().X, concept.getPosition().Y, concept.getWidth(), concept.getHeight());
        }
    }

    @Override
    public boolean elementAt(MindMapDiagramElement element, Point pos) {
        return concept.getSelectionRectangle().getBounds().contains(pos);
    }

    public boolean elementAtPoint(Point pos) {
        return concept.getSelectionRectangle().getBounds().contains(pos);
    }

    private Point getMiddlePointForText(Graphics g){
        FontMetrics metrics = g.getFontMetrics();
        return new Point(concept.getPosition().X + (concept.getWidth() - metrics.stringWidth(concept.getTopicName())) / 2,
                concept.getPosition().Y + ((concept.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent());
    }

    public MindMapDiagramConcept getConcept() {
        try {
            return concept;
        } catch (Exception e) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(CONCEPT_NOT_SELECTED);
            return null;
        }
    }

    public boolean overlaps (Rectangle r) {
        return (concept.getSelectionRectangle().x < r.x + r.width &&
                concept.getSelectionRectangle().x + concept.getWidth() > r.x &&
                concept.getSelectionRectangle().y < r.y + r.height &&
                concept.getSelectionRectangle().y + concept.getHeight() > r.y);
    }

    public boolean overlaps (Point point) {
        return (concept.getSelectionRectangle().x < point.x &&
                concept.getSelectionRectangle().x + concept.getWidth() > point.x &&
                concept.getSelectionRectangle().y < point.y &&
                concept.getSelectionRectangle().y + concept.getHeight() > point.y);
    }

    public void setPosition(Point p) {
        this.concept.getSelectionRectangle().setLocation(p);
        this.getConcept().setPosition(new dsw.gerumap.app.gui.swing.tree.view.graphics.elements.Point(p));
    }

}
