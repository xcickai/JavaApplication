package dsw.gerumap.app.mapRepository.factories;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;

public class ProjectExplorerFactory implements NodeFactory {
    public ProjectExplorer createNode(String name, MapNode parent) {
        return new ProjectExplorer(name);
    }
}