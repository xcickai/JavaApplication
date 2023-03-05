package dsw.gerumap.app.mapRepository.implementation;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import javax.swing.tree.TreePath;

@Getter
@Setter
public class Project extends MapNodeComposite {
    private String name = "New Project";
    private TreePath path;

    public Project(String name, MapNode parent){
        super(name, parent);
    }

    @Override
    public void addChild(MapNode child) {
        if (child instanceof MindMapView){
            MindMapView mindMapView = (MindMapView) child;
            if(!this.getChildren().contains(mindMapView)){
                this.getChildren().add(mindMapView);
            }
        }
    }
    @Override
    public void deleteChild(MapNode child) {
        if (child instanceof MindMapView){
            MindMapView mindMapView = (MindMapView) child;
            this.getChildren().remove(mindMapView);
        }
    }

}
