package dsw.gerumap.app.mapRepository.implementation;


import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;

public class ProjectExplorer extends MapNodeComposite {

    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public void addChild(MapNode child) {
        if (child instanceof Project){
            Project project = (Project) child;
            if(!this.getChildren().contains(project)){
                this.getChildren().add(project);
            }
        }
    }

    @Override
    public void deleteChild(MapNode child) {
        if (child instanceof Project){
            Project project = (Project) child;
            this.getChildren().remove(project);
        }
    }
}
