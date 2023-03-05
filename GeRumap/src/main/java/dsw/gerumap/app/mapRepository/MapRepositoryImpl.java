package dsw.gerumap.app.mapRepository;

import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.factories.NodeFactoryManager;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;


public class MapRepositoryImpl implements MapRepository {
    private ProjectExplorer projectExplorer;
    private NodeFactoryManager fm = new NodeFactoryManager();


    public MapRepositoryImpl() {
        projectExplorer = new ProjectExplorer("My Project Explorer");
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

    @Override
    public void addChild(MapNodeComposite parent, MapNode child) {
        parent.addChild(fm.getNodeFor(parent));
    }

}
