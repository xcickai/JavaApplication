package dsw.gerumap.app.mapRepository.composite;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class MapNode {
    private MapNode parent;
    private String name;

    public MapNode(String name, MapNode parent) {
        this.name = name;
        this.parent = parent;
    }
    public MapNode() {}

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MapNode) {
            MapNode otherObj = (MapNode) obj;
            return this.name.equals(otherObj.name);
        }
        return false;
    }
}
