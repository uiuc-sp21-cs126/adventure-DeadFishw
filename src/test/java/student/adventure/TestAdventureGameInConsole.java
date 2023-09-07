package student.adventure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

import java.io.*;

public class TestAdventureGameInConsole {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    GameEngine engine;
    ObjectMapper mapper;
    AdventureGameInConsole game;
    @Before
    public void setUp() throws IOException {
        System.setOut(new PrintStream(outputStreamCaptor));
        // This is run before every test.
        File file = new File("src/main/resources/adventure.json");
        mapper = new ObjectMapper();

        engine = new GameEngine(file);
        game = engine.getConsoleGame();
    }
    @Test
    public void testGoSomewhereWest() {
        game.take("take wooden sword");
        game.goSomewhere("go west");
        assertTrue(game.getCharacter().getCurrentRoom().getName().equals("Forest of Mist"));
    }

    @Test
    public void testGoSomewhereCannotGo() {
        game.goSomewhere("go north");
        assertEquals("I can't go \"north\"!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testGoSomewhereRandomString() {
        game.take("take wooden sword");
        game.goSomewhere("go sdfsdf");
        assertEquals("I can't go \"sdfsdf\"!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testGoSomewhereIgnoresCase() {
        game.take("take wooden sword");
        game.goSomewhere("go WEsT");
        assertEquals(game.getCharacter().getCurrentRoom().getName(), "Forest of Mist");
    }

    @Test
    public void testGoSomewhereIgnoresSpace() {
        game.take("take wooden sword");
        game.goSomewhere("go        west       ");
        assertEquals("Forest of Mist", game.getCharacter().getCurrentRoom().getName());
    }

    @Test
    public void testGoSomewhereLevelTooLow() {
        game.goSomewhere("go west");
        assertEquals("I can't go \"west\"! I cannot protect myself there!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testTakeWoodenSword() {
        game.take("take wooden sword");
        Item woodenSword = new Item("Wooden Sword", 25,40,5);

        assertTrue(game.getCharacter().getItems().contains(woodenSword));
    }

    @Test
    public void testTakeTooMuchLoad() {
        game.character.getCurrentRoom().addItem(new Item("load", 0, 200, 0));
        game.take("take load");
        assertEquals("I can't take \"load\"! It's too heavy for me!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testTakeItemNotExist() {
        game.take("take money");
        assertEquals("There is no item \"money\" in the room.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testTakeRandomString() {
        game.take("take sdfsdf");
        assertEquals("There is no item \"sdfsdf\" in the room.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testTakeIgnoresCase() {
        game.take("take woODen SwORd");
        Item woodenSword = new Item("Wooden Sword", 25,40,5);

        assertTrue(game.getCharacter().getItems().contains(woodenSword));
    }

    @Test
    public void testTakeIgnoresSpace() {
        game.take("take         wooden sword           ");
        Item woodenSword = new Item("Wooden Sword", 25,40,5);

        assertTrue(game.getCharacter().getItems().contains(woodenSword));
    }

    @Test
    public void testDropMissingNote() {
        game.take("take wooden sword");
        Item woodenSword = new Item("Wooden Sword", 25,40,5);
        game.drop("drop wooden sword");

        assertTrue(game.getCharacter().getCurrentRoom().getItems().contains(woodenSword));
    }

    @Test
    public void testDropDontHave() {
        game.drop("drop coins");
        assertEquals("You don't have \"coins\"!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testDropRandomString() {
        game.take("take wooden sword");
        game.drop("drop sdfsdf");
        assertEquals("You don't have \"sdfsdf\"!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testDropIgnoresCase() {
        game.take("take wooden sword");
        Item woodenSword = new Item("Wooden Sword", 25,40,5);
        game.drop("drop WooDEN SwORd");
        assertTrue(game.getCharacter().getCurrentRoom().getItems().contains(woodenSword));
    }

    @Test
    public void testDropIgnoresSpace() {
        game.take("take wooden sword");
        Item woodenSword = new Item("Wooden Sword", 25,40,5);
        game.drop("drop                 wooden sword            ");
        assertTrue(game.getCharacter().getCurrentRoom().getItems().contains(woodenSword));
    }

    @Test
    public void testRunGameExit() {
        String testExit = "exit";
        //Code below derived from:
        //https://blog.csdn.net/weixin_41287260/article/details/102539111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        engine.startInConsole();
    }

    @Test
    public void testRunGameExitIgnoresCase() {
        String testExit = "EXiT";
        //Code below derived from:
        //https://blog.csdn.net/weixin_41287260/article/details/102539111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        engine.startInConsole();
    }

    @Test
    public void testRunGameExitIgnoresSpace() {
        String testExit = "       exit      ";
        //Code below derived from:
        //https://blog.csdn.net/weixin_41287260/article/details/102539111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        engine.startInConsole();
    }

    @Test
    public void testRunGameQuit() {
        String testExit = "quit";
        //Code below derived from:
        //https://blog.csdn.net/weixin_41287260/article/details/102539111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        engine.startInConsole();
    }

    @Test
    public void testRunGameQuitIgnoresCase() {
        String testExit = "QuIT";
        //Code below derived from:
        //https://blog.csdn.net/weixin_41287260/article/details/102539111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        engine.startInConsole();
    }

    @Test
    public void testRunGameQuitIgnoresSpace() {
        String testExit = "        quit        ";
        //Code below derived from:
        //https://blog.csdn.net/weixin_41287260/article/details/102539111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-5.control
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        engine.startInConsole();
    }

    @Test
    public void testRunGamePrint() {
        String testExit = "quit";
        System.setIn(new ByteArrayInputStream(testExit.getBytes()));
        engine.startInConsole();
        assertEquals("You are at the Long Lake Town, the starting point of your adventure. You might want to take a sword with you to defend the danger. \n" +
                "Lately a dragon from the west are harassing the town. You, the warrior, suggested to go slay the dragon. Good luck on your adventure!\n" +
                "From here, you can go: West \n" +
                "Items visible: Wooden Sword", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testWinCase() {
        game.take("take wooden sword");
        game.goSomewhere("go west");
        game.goSomewhere("go west");
        game.take("take ancient armor");
        game.goSomewhere("go in");
        game.take("take golden sword");
        game.goSomewhere("go north");
        game.take("take silver helmet");

        game.goSomewhere("go south");
        game.goSomewhere("go south");
        game.take("take fine steel leg armor");
        game.goSomewhere("go north");
        game.goSomewhere("go down");
        game.goSomewhere("go down");
        game.goSomewhere("go fight the dragon");
        assertEquals("Congratulations! You have slayed the dragon! You are the hero of the town!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testLoseCase() {
        game.take("take wooden sword");
        game.goSomewhere("go west");
        game.goSomewhere("go west");
        game.take("take ancient armor");
        game.goSomewhere("go in");
        game.take("take golden sword");
        game.goSomewhere("go north");
        game.take("take silver helmet");
        game.goSomewhere("go south");
        game.goSomewhere("go south");
        game.take("take fine steel leg armor");
        game.goSomewhere("go north");
        game.goSomewhere("go down");
        game.goSomewhere("go down");
        game.drop("drop golden sword");
        game.goSomewhere("go fight the dragon");
        assertEquals("You meet your own death. Try challenge the dragon with more force next time.", outputStreamCaptor.toString().trim());
    }
}

