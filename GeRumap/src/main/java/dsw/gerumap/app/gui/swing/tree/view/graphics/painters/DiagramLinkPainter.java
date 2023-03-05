package dsw.gerumap.app.gui.swing.tree.view.graphics.painters;

import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramElement;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramLink;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Objects;

@Getter
@Setter
public class DiagramLinkPainter extends DiagramElementPainter {
    private MindMapDiagramLink link;

    public DiagramLinkPainter(MindMapDiagramLink link) {
        super(link);
        this.link = link;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getElement().getColor());
        g2.setStroke(new BasicStroke(getLink().getLinkWidth()));
        Point from = new Point(link.getFrom().getPosition());
        Point to = new Point(link.getTo().getPosition());

        // TOP
        if ((from.getY() + link.getFrom().getHeight() < to.getY())) {
            if (from.getX() + link.getFrom().getWidth() < to.getX()) {               // Top-Left
                g2.drawLine(from.x + link.getFrom().getWidth(), from.y + link.getFrom().getHeight(), (int) to.getX(), (int) to.getY());
            } else if (from.getX() > to.getX() + link.getTo().getWidth()) {         // Top-Right
                g2.drawLine(from.x, from.y + link.getFrom().getHeight(), (int) to.getX() + link.getTo().getWidth(), (int) to.getY());
            } else {                                                                // Top
                g2.drawLine((from.x + link.getFrom().getWidth() / 2), from.y + link.getFrom().getHeight(), ((int) to.getX() + link.getTo().getWidth() / 2), (int) to.getY());
            }
            // BOTTOM
        } else if ((from.getY() > to.getY() + link.getTo().getHeight())) {
            if (from.getX() + link.getFrom().getWidth() < to.getX()) {               // Bottom-Left
                g2.drawLine(from.x + link.getFrom().getWidth(), from.y, (int) to.getX(), (int) to.getY() + link.getTo().getHeight());
            } else if (from.getX() > to.getX() + link.getTo().getWidth()) {         // Bottom-Right
                g2.drawLine(from.x, from.y, (int) to.getX() + link.getTo().getWidth(), (int) to.getY() + link.getTo().getHeight());
            } else {                                                                // Bottom
                g2.drawLine((from.x + link.getFrom().getWidth() / 2), from.y, ((int) to.getX() + link.getTo().getWidth() / 2), (int) to.getY() + link.getTo().getHeight());
            }
            // LEFT
        } else if (from.getX() + link.getFrom().getWidth() < to.getX()) {
            g2.drawLine(from.x + link.getFrom().getWidth(), (from.y + link.getFrom().getHeight() / 2), (int) to.getX(), ((int) to.getY() + link.getTo().getHeight() / 2));
        }
        // RIGHT
        else {
            g2.drawLine(from.x, (from.y + link.getFrom().getHeight() / 2), (int) to.getX() + link.getTo().getWidth(), ((int) to.getY() + link.getTo().getHeight() / 2));
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }

    @Override
    public boolean elementAt(MindMapDiagramElement element, Point pos) {
        return getShape().contains(pos);
    }
}