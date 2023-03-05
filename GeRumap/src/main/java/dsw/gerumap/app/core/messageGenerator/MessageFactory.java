package dsw.gerumap.app.core.messageGenerator;

public class MessageFactory {

    public Message generateMessage(EventType type) {
        switch (type) {
            case TRYING_TO_DELETE_PROJECT_EXPLORER:
                return new Message(type, "Invalid Operation: Trying to delete Project Explorer!");
            case NODE_NAME_EMPTY:
                return new Message(type, "Info: Node's name cannot be left empty!");
            case FAILED_TO_PARSE_STR_TO_FLOAT:
                return new Message(type, "Error: Failed to parse string as float!");
            case CONCEPT_NOT_SELECTED:
                return new Message(type, "Error: Concept not selected!");
            case INFORMATION:
                break;
            case MINDMAP_NOT_SELECTED:
                return new Message(type, "Error: There are no active Mindmaps selected. Please create a Mindmap and try again.");
            default:
                break;
        }
        return null;
    }
}
