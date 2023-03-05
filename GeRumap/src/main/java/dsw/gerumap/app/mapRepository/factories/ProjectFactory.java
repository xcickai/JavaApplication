package dsw.gerumap.app.mapRepository.factories;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Project;

public class ProjectFactory implements NodeFactory {
    public MapNode createNode(String name, MapNode parent) {
        return new Project(name, parent);
    }
}
