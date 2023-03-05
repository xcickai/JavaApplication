package dsw.gerumap.app.mapRepository.factories;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.MindMapElement;

public class ElementFactory implements NodeFactory {
    public MindMapElement createNode(String name, MapNode parent) {
        return new MindMapElement(name, parent);
    }
}