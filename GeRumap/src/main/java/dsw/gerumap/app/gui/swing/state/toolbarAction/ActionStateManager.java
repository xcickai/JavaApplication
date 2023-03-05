package dsw.gerumap.app.gui.swing.state.toolbarAction;

import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.state.toolbarAction.model.*;

public class ActionStateManager {
    private State currentState;
    private ElementConceptState conceptState;
    private ElementLinkState linkState;
    private ElementDeleteState deleteState;
    private ElementSelectState selectState;
    private ElementMoveState moveState;
    private CentralTopicState centralTopicState;

    public ActionStateManager(){
        initStates();
    }

    private void initStates(){
        conceptState = new ElementConceptState();
        linkState = new ElementLinkState();
        deleteState = new ElementDeleteState();
        selectState = new ElementSelectState();
        moveState = new ElementMoveState();
        centralTopicState = new CentralTopicState();

        currentState = selectState;
    }

    public void setConceptState() { currentState = conceptState; }
    public void setLinkState() { currentState = linkState; }
    public void setDeleteState() { currentState = deleteState; }
    public void setSelectState() {
        currentState = selectState;
    }
    public void setMoveState() {
        currentState = moveState;
    }
    public void setCentralTopicState() { currentState = centralTopicState; }

    public State getCurrentState(){
        return currentState;
    }
}
