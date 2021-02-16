package student.adventure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;
import static student.adventure.Siebel.runGame;


public class AdventureTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    Character character;
    Siebel adventure;
    ObjectMapper mapper;
    @Before
    public void setUp() throws IOException {
        System.setOut(new PrintStream(outputStreamCaptor));
        // This is run before every test.
        File file = new File("src/main/resources/siebel.json");
        mapper = new ObjectMapper();
        adventure = mapper.readValue(file, Siebel.class);
        character = new Character(adventure.getRooms()[0], adventure);
    }

    @Test
    public void sanityCheck() {
        // TODO: Remove this unnecessary test case.
        assertThat("CS 126: Software Design Studio", CoreMatchers.containsString("Software"));
    }

    @Test
    public void testGoSomewhereEast() {
        character.goSomewhere("go east");
        assertEquals(character.getCurrentRoom(), new Room("SiebelEntry"));
    }

    @Test
    public void testGoSomewhereCannotGo() {
        character.goSomewhere("go north");
        assertEquals("I can't go \"north\"!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testGoSomewhereRandomString() {
        character.goSomewhere("go sdfsdf");
        assertEquals("I can't go \"sdfsdf\"!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testGoSomewhereIgnoresCase() {
        character.goSomewhere("go EAsT");
        assertEquals(character.getCurrentRoom(), new Room("SiebelEntry"));
    }

    @Test
    public void testGoSomewhereIgnoresSpace() {
        character.goSomewhere("go        east       ");
        assertEquals(character.getCurrentRoom(), new Room("SiebelEntry"));
    }

    @Test
    public void testTakeMissingNote() {
        character.take("take missing notes");
        assertTrue(character.getItems().contains("missing notes"));
    }

    @Test
    public void testTakeNoItem() {
        character.take("take money");
        assertEquals("There is no \"money\" in the room.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testTakeRandomString() {
        character.take("take sdfsdf");
        assertEquals("There is no \"sdfsdf\" in the room.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testTakeIgnoresCase() {
        character.take("take MiSSinG NoTEs");
        assertTrue(character.getItems().contains("missing notes"));
    }

    @Test
    public void testTakeIgnoresSpace() {
        character.take("take        missing notes       ");
        assertTrue(character.getItems().contains("missing notes"));
    }

    @Test
    public void testDropMissingNote() {
        character.drop("drop missing notes");
        assertTrue(character.getCurrentRoom().getItems().contains("missing notes"));
    }

    @Test
    public void testDropDontHave() {
        character.drop("drop coins");
        assertEquals("You don't have \"coins\"!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testDropRandomString() {
        character.take("take missing notes");
        character.drop("drop sdfsdf");
        assertEquals("You don't have \"sdfsdf\"!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testDropIgnoresCase() {
        character.take("take missing notes");
        character.drop("drop MiSSinG NoTEs");
        assertTrue(character.getCurrentRoom().getItems().contains("missing notes"));
    }

    @Test
    public void testDropIgnoresSpace() {
        character.take("take missing notes");
        character.drop("drop        missing notes       ");
        assertTrue(character.getCurrentRoom().getItems().contains("missing notes"));
    }

    @Test
    public void testRunGameExit() {
        String testExit = "exit";
        //Code below derived from:
        //https://blog.csdn.net/weixin_41287260/article/details/102539111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        runGame(character);
    }

    @Test
    public void testRunGameExitIgnoresCase() {
        String testExit = "EXiT";
        //Code below derived from:
        //https://blog.csdn.net/weixin_41287260/article/details/102539111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        runGame(character);
    }

    @Test
    public void testRunGameExitIgnoresSpace() {
        String testExit = "       exit      ";
        //Code below derived from:
        //https://blog.csdn.net/weixin_41287260/article/details/102539111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        runGame(character);
    }

    @Test
    public void testRunGameQuit() {
        String testExit = "quit";
        //Code below derived from:
        //https://blog.csdn.net/weixin_41287260/article/details/102539111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        runGame(character);
    }

    @Test
    public void testRunGameQuitIgnoresCase() {
        String testExit = "QuIT";
        //Code below derived from:
        //https://blog.csdn.net/weixin_41287260/article/details/102539111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        runGame(character);
    }

    @Test
    public void testRunGameQuitIgnoresSpace() {
        String testExit = "        quit        ";
        //Code below derived from:
        //https://blog.csdn.net/weixin_41287260/article/details/102539111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        runGame(character);
    }

    @Test
    public void testRunGamePrint() {
        String testExit = "quit";
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        runGame(character);
        assertEquals("You are on Matthews, outside the Siebel Center\n" +
                "From here, you can go: East \n" +
                "Items visible: missing notes", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testWinCase() {
        character.goSomewhere("go east");
        character.goSomewhere("go east");
        character.goSomewhere("go down");
        character.goSomewhere("go in");
        character.goSomewhere("go log in to zoom");
        assertEquals("Congratulations! You Win! Enjoy your code review!", outputStreamCaptor.toString().trim());
    }
}