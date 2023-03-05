package dsw.gerumap.app.mapRepository.implementation;

import dsw.gerumap.app.gui.swing.controller.actions.commands.CommandManager;
import dsw.gerumap.app.gui.swing.tree.view.MindMapDiagramView;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;

@Setter
@Getter
public class MindMapView extends MapNodeComposite {
    private boolean isTemplate;
    private String name = "New Mindmap";
    private String author = "New Author";
    private TreePath path;
    private MindMapDiagramView diagramRepresentation;
    private CommandManager commandManager;

    protected String filePath;
    protected boolean changed = true;

    public MindMapView(String name, MapNode parent) {
        super(name, parent);
        diagramRepresentation = new MindMapDiagramView(this);
        diagramRepresentation.setPreferredSize(new Dimension(20000, 20000));
        commandManager = new CommandManager();
    }

    @Override
    public void addChild(MapNode child) {
        super.getChildren().add(child);
    }

    @Override
    public void deleteChild(MapNode child) {}
}
