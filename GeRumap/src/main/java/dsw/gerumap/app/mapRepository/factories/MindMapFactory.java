package dsw.gerumap.app.mapRepository.factories;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.MindMapView;

public class MindMapFactory implements NodeFactory {
    public MapNode createNode(String name, MapNode parent) {
        return new MindMapView(name, parent);
    }
}
