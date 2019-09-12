package Duke;
import Command.AddCommand;
import Command.ExitCommand;
import DukeException.*;
import Task.ToDo;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void parseInputTest() {
        // Test exit
        try {
            assertTrue(Parser.parseInput("bye") instanceof ExitCommand);
        } catch(Exception ignored){}

        // Test wrong input command
        try {
            Parser.parseInput("this is a wrong input");
        } catch(Exception e) {
            assertTrue(e instanceof UnknownCommandException);
        }

        // Test ToDo creation
        try {
            assertTrue(Parser.parseInput("todo Finish essay /by 02/03/2000 0000") instanceof AddCommand);
        } catch(Exception ignored) {}

        // Test Deadline creation
        try {
            assertTrue(Parser.parseInput("deadline Finish essay /by 02/03/2000 0000") instanceof AddCommand);
        } catch(Exception ignored) {}

        // Test Event creation
        try {
            assertTrue(Parser.parseInput("event Party /by 04/12/2000 1000") instanceof AddCommand);
        } catch(Exception ignored) {}

        // Test incomplete new task entry
        try {
            Parser.parseInput("todo Finish coding");
        } catch(Exception e) {
            assertTrue(e instanceof IncompleteListEntryException);
        }

        // Test wrong dateTime format
        try {
            Parser.parseInput("todo Finish coding /by 2/2/98");
        } catch(Exception e) {
            assertTrue(e instanceof UnknownDateTimeFormatException);
        }

        // Test incomplete command entered
        try {
            Parser.parseInput("done");
        } catch(Exception e) {
            assertTrue(e instanceof IncompleteCommandException);
        }
    }

    @Test
    public void descriptionParseTest() {
        // Test description extraction (parsing)
        try {
            assertEquals("Finish homework",
                    Parser.descriptionParse("Finish homework /by 01/01/1998 1800".split(" ", 0)));
        } catch(Exception e) {}

        // Test incomplete list entry exception
        try {
            Parser.descriptionParse("".split(" ", 0));
        } catch(Exception e) {
            assertTrue(e instanceof IncompleteListEntryException);
        }
    }
}
