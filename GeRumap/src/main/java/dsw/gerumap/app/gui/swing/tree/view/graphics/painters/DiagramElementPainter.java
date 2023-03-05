package dsw.gerumap.app.gui.swing.tree.view.graphics.painters;

import com.google.gson.annotations.Expose;
import dsw.gerumap.app.gui.swing.tree.view.graphics.elements.MindMapDiagramElement;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.implementation.MindMapElement;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Observable;

@Getter
@Setter
public abstract class DiagramElementPainter extends Observable {
    @Expose
    private Shape shape;
    private MindMapDiagramElement element;

    public DiagramElementPainter(MindMapDiagramElement element) {
        super();
        this.element = element;
        this.addObserver(MainFrame.getInstance().getProjectView());
    }

    public abstract void draw(Graphics g);
    public abstract boolean elementAt(MindMapDiagramElement element, Point pos);
}