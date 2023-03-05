package dsw.gerumap.app.mapRepository.factories;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Project;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;

import java.util.Random;

public class NodeFactoryManager {
    private ElementFactory elementFactory = new ElementFactory();
    private MindMapFactory mindMapFactory = new MindMapFactory();
    private ProjectExplorerFactory projectExplorerFactory = new ProjectExplorerFactory();
    private ProjectFactory projectFactory = new ProjectFactory();

    public NodeFactory getFactoryFor(MapNode parent) {
        if (parent instanceof ProjectExplorer)
            return projectFactory;
        if (parent instanceof Project)
            return mindMapFactory;
        return null;
    }

    public MapNode getNodeFor(MapNode parent) {
        if (parent instanceof ProjectExplorer)
            return projectFactory.createNode("Project" + new Random().nextInt(1000), parent);
        if (parent instanceof Project)
            return mindMapFactory.createNode("MindMapView" + new Random().nextInt(1000), parent);
        return null;
    }
}
