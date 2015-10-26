package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.Task;

public class testTask {

	//Variables needed for testing	
	private final ByteArrayOutputStream _console = new ByteArrayOutputStream();
	
	@Before
	public void setUp() {
	    System.setOut(new PrintStream(_console));
	}
	
	@Test //Partition: Custom description, default deadline, category, priority
	public void testOnlyDescription() throws ParseException {
		String[] contents = {"buy milk"};
		Task testTask = new Task(contents);
		String expectedDescription = "buy milk";
		String expectedEndDate = "someday";
		String expectedCategory = "none";
		String expectedPriority = "med";
		assertEquals(expectedDescription, testTask.getDescription());
		assertEquals(expectedEndDate, testTask.getEndDate());
		assertEquals(expectedCategory, testTask.getCategory());
		assertEquals(expectedPriority, testTask.getPriority());
	}
	
	@Test //Partition: Custom description, deadline:"today", default category, priority
	public void testWithToday() throws ParseException {
		String[] contents = {"buy milk", "today"};
		Task testTask = new Task(contents);
		String expectedDescription = "buy milk";
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar theDate = Calendar.getInstance();
		String expectedEndDate = dateFormat.format(theDate.getTime());
		String expectedCategory = "none";
		String expectedPriority = "med";
		assertEquals(expectedDescription, testTask.getDescription());
		assertEquals(expectedEndDate, testTask.getEndDate());
		assertEquals(expectedCategory, testTask.getCategory());
		assertEquals(expectedPriority, testTask.getPriority());
	}
	
	@Test //Partition: Custom description, deadline:"tomorrow", default category, priority
	public void testWithTomorrow() throws ParseException {
		String[] contents = {"buy milk", "tomorrow"};
		Task testTask = new Task(contents);
		String expectedDescription = "buy milk";
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar theDate = Calendar.getInstance();
		theDate.add(Calendar.DATE, 1);
		String expectedEndDate = dateFormat.format(theDate.getTime());
		String expectedCategory = "none";
		String expectedPriority = "med";
		assertEquals(expectedDescription, testTask.getDescription());
		assertEquals(expectedEndDate, testTask.getEndDate());
		assertEquals(expectedCategory, testTask.getCategory());
		assertEquals(expectedPriority, testTask.getPriority());
	}
	
	@Test //Partition: Custom description and date, default category, priority
	public void testWithDate() throws ParseException {
		String[] contents = {"buy milk", "25/12/2015"};
		Task testTask = new Task(contents);
		String expectedDescription = "buy milk";
		String expectedEndDate = "25/12/2015";
		String expectedCategory = "none";
		String expectedPriority = "med";
		assertEquals(expectedDescription, testTask.getDescription());
		assertEquals(expectedEndDate, testTask.getEndDate());
		assertEquals(expectedCategory, testTask.getCategory());
		assertEquals(expectedPriority, testTask.getPriority());
	}
	
	@Test //Partition: Custom description and date with time, default category, priority
	public void testWithDateAndTime() throws ParseException {
		String[] contents = {"buy milk", "25/12/2015 5:00 am"};
		Task testTask = new Task(contents);
		String expectedDescription = "buy milk";
		String expectedEndDate = "25/12/15 5:00 AM";
		String expectedCategory = "none";
		String expectedPriority = "med";
		assertEquals(expectedDescription, testTask.getDescription());
		assertEquals(expectedEndDate, testTask.getEndDate());
		assertEquals(expectedCategory, testTask.getCategory());
		assertEquals(expectedPriority, testTask.getPriority());
	}
	
	@Test (expected = ParseException.class) //Partition: invalid date
	public void testWithInvalidDate() throws ParseException {
		String[] contents = {"buy milk", "I am an extremely important date"};
		Task testTask = new Task(contents);
	}
	
	@Test //Testing Class Passing Methods
	public void testWithIntId() {
		Task testTask = new Task(1, "buy milk", "23/10/2015", "high", "personal");
		String expectedId = "t1";
		String expectedDescription = "buy milk";
		String expectedEndDate = "23/10/2015";
		String expectedCategory = "personal";
		String expectedPriority = "high";
		assertEquals(expectedId, testTask.getId());
		assertEquals(expectedDescription, testTask.getDescription());
		assertEquals(expectedEndDate, testTask.getEndDate());
		assertEquals(expectedCategory, testTask.getCategory());
		assertEquals(expectedPriority, testTask.getPriority());
	}
	
	@Test //Testing Class Passing Methods
	public void testWithStringId() {
		Task testTask = new Task("t1", "buy milk", "23/10/2015", "high", "personal");
		String expectedId = "t1";
		String expectedDescription = "buy milk";
		String expectedEndDate = "23/10/2015";
		String expectedCategory = "personal";
		String expectedPriority = "high";
		assertEquals(expectedId, testTask.getId());
		assertEquals(expectedDescription, testTask.getDescription());
		assertEquals(expectedEndDate, testTask.getEndDate());
		assertEquals(expectedCategory, testTask.getCategory());
		assertEquals(expectedPriority, testTask.getPriority());
	}
	
	@After
	public void tearDown() { 
	    System.setOut(null);
	}
	
}