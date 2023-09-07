package student.adventure;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import student.server.AdventureException;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestJsonParser {
    @Test
    public void testBlankJSON() throws IOException {
        File file = new File("src/main/resources/Blank.json");
        GameEngine engine = new GameEngine(file);
    }
}
