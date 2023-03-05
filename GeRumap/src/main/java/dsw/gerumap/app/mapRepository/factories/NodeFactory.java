package dsw.gerumap.app.mapRepository.factories;

import dsw.gerumap.app.mapRepository.composite.MapNode;

interface NodeFactory {
    MapNode createNode(String name, MapNode parent);

}