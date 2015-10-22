package application;

import java.text.ParseException;

import org.json.simple.JSONObject;

public class AddCommand extends Command {
	
	//Unique attributes
	private String _createdItemID;
	
	//Constant Strings
	protected static final String FEEDBACK_MESSAGE =  "Added: %1$s";
	private static final String FEEDBACK_MISSING_DESC = "Error: A task needs a description";
	private static final String FEEDBACK_WRONG_DATE = "Error: Invalid Date";

	public AddCommand(String content) {
		super(content);
	}

	@Override
	public Feedback execute() {
		String feedbackString;
		try {
			_store.storeTemp();
			Task createdTask = createTaskOrEvent();
			_createdItemID = createdTask.getId();
			_store.entries_.add(_parse.convertToJSON(createdTask));
			_store.storeChanges();
			feedbackString = String.format(FEEDBACK_MESSAGE, _content);
			return new Feedback(feedbackString);
		} catch (NullPointerException e) {
			feedbackString = FEEDBACK_MISSING_DESC;
			return new Feedback(feedbackString);
		} catch (ParseException e) {
			feedbackString = FEEDBACK_WRONG_DATE;
			return new Feedback(feedbackString);
		} 
	}
	
	private Task createTaskOrEvent() throws ParseException {
		Task createdItem = _parse.createItem(_content);
		if(createdItem instanceof Event) {
			createdItem.setId(_store.getEventId());
		} else {
			createdItem.setId(_store.getTaskId());
		}
		return createdItem;
	}

	@Override
	public Feedback undo() {
		DeleteCommand undoAdd = new DeleteCommand(_createdItemID);
		if(_createdItemID.contains("e")){
			_store.decreaseEventID();
		} else {
			_store.decreaseTaskID();
		}
		return undoAdd.execute();
	}

}
