package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class to test Deadline class
 */
public class DeadlineTest {
    /**
     * Test if the deadline representation is generated as expected.
     * 
     * @throws DukeException Thrown if deadline fails to be generated.
     */
    @Test
    public void deadlineCreationTest() throws DukeException {
        assertEquals(new Deadline("description","2012-12-01").toString(),
                "[D] description (by: Dec 12 2012)");
    }
}
