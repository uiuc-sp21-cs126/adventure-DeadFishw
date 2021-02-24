package student.adventure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import student.server.AdventureException;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestAdventureGameInServer {
    GameEngine engine;
    ObjectMapper mapper;
    AdventureGameInServer game;
    int gameID;
    @Before
    public void setUp() throws IOException, AdventureException {
        // This is run before every test.
        File file = new File("src/main/resources/adventure.json");
        mapper = new ObjectMapper();

        engine = new GameEngine(file);
        gameID = engine.newGame();
        game = engine.getGameInServer(gameID);
    }
    @Test
    public void testGoSomewhereWest() {
        game.take("wooden sword");
        game.goSomewhere("west");
        assertTrue(game.getCharacter().getCurrentRoom().getName().equals("Forest of Mist"));
    }

    @Test
    public void testGoSomewhereCannotGo() {
        game.goSomewhere("north");
        assertEquals("I can't go \"north\"!", game.getGameStatus().getMessage());
    }

    @Test
    public void testGoSomewhereRandomString() {
        game.take("wooden sword");
        game.goSomewhere("sdfsdf");
        assertEquals("I can't go \"sdfsdf\"!", game.getGameStatus().getMessage());
    }

    @Test
    public void testGoSomewhereIgnoresCase() {
        game.take("wooden sword");
        game.goSomewhere("WEsT");
        assertEquals(game.getCharacter().getCurrentRoom().getName(), "Forest of Mist");
    }

    @Test
    public void testGoSomewhereIgnoresSpace() {
        game.take("wooden sword");
        game.goSomewhere("       west       ");
        assertEquals("Forest of Mist", game.getCharacter().getCurrentRoom().getName());
    }

    @Test
    public void testGoSomewhereLevelTooLow() {
        game.goSomewhere("west");
        assertEquals(game.getGameStatus().getMessage(), "I can't go \"west\"! I cannot protect myself there!");
    }

    @Test
    public void testTakeWoodenSword() {
        game.take("wooden sword");
        Item woodenSword = new Item("Wooden Sword", 25,40,5);

        assertTrue(game.getCharacter().getItems().contains(woodenSword));
    }

    @Test
    public void testTakeTooMuchLoad() {
        game.getCharacter().getCurrentRoom().addItem(new Item("load", 0, 200, 0));
        game.take("load");
        assertEquals(game.getGameStatus().getMessage(),"I can't take \"load\"! It's too heavy for me!");
    }

    @Test
    public void testTakeItemNotExist() {
        game.take("money");
        assertEquals(game.getGameStatus().getMessage(), "There is no item \"money\" in the room.");
    }

    @Test
    public void testTakeRandomString() {
        game.take("sdfsdf");
        assertEquals("There is no item \"sdfsdf\" in the room.",game.getGameStatus().getMessage());
    }

    @Test
    public void testTakeIgnoresCase() {
        game.take("woODen SwORd");
        Item woodenSword = new Item("Wooden Sword", 25,40,5);

        assertTrue(game.getCharacter().getItems().contains(woodenSword));
    }

    @Test
    public void testTakeIgnoresSpace() {
        game.take("        wooden sword           ");
        Item woodenSword = new Item("Wooden Sword", 25,40,5);

        assertTrue(game.getCharacter().getItems().contains(woodenSword));
    }

    @Test
    public void testDropMissingNote() {
        game.take("wooden sword");
        Item woodenSword = new Item("Wooden Sword", 25,40,5);
        game.drop("wooden sword");

        assertTrue(game.getCharacter().getCurrentRoom().getItems().contains(woodenSword));
    }

    @Test
    public void testDropDontHave() {
        game.drop("coins");
        assertEquals(game.getGameStatus().getMessage(), "You don't have \"coins\"!");
    }

    @Test
    public void testDropRandomString() {
        game.take("wooden sword");
        game.drop("sdfsdf");
        assertEquals(game.getGameStatus().getMessage(), "You don't have \"sdfsdf\"!");
    }

    @Test
    public void testDropIgnoresCase() {
        game.take("wooden sword");
        Item woodenSword = new Item("Wooden Sword", 25,40,5);
        game.drop("WooDEN SwORd");
        assertTrue(game.getCharacter().getCurrentRoom().getItems().contains(woodenSword));
    }

    @Test
    public void testDropIgnoresSpace() {
        game.take("wooden sword");
        Item woodenSword = new Item("Wooden Sword", 25,40,5);
        game.drop("                wooden sword            ");
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
        assertEquals(game.getGameStatus().getMessage(), "You are at the Long Lake Town, the starting point of your adventure. You might want to take a sword with you to defend the danger. \n" +
                "Lately a dragon from the west are harassing the town. You, the warrior, suggested to go slay the dragon. Good luck on your adventure!\n" +
                "From here, you can go: West \n" +
                "Items visible: Wooden Sword");
    }

    @Test
    public void testWinCase() {
        game.take("wooden sword");
        game.goSomewhere("west");
        game.goSomewhere("west");
        game.take("ancient armor");
        game.goSomewhere("in");
        game.take("golden sword");
        game.goSomewhere("north");
        game.take("silver helmet");

        game.goSomewhere("south");
        game.goSomewhere("south");
        game.take("fine steel leg armor");
        game.goSomewhere("north");
        game.goSomewhere("down");
        game.goSomewhere("down");
        game.goSomewhere("fight the dragon");
        assertEquals(game.getGameStatus().getMessage(), "Congratulations! You have slayed the dragon! You are the hero of the town!");
    }

    @Test
    public void testLoseCase() {
        game.take("wooden sword");
        game.goSomewhere("west");
        game.goSomewhere("west");
        game.take("ancient armor");
        game.goSomewhere("in");
        game.take("golden sword");
        game.goSomewhere("north");
        game.take("silver helmet");
        game.goSomewhere("south");
        game.goSomewhere("south");
        game.take("fine steel leg armor");
        game.goSomewhere("north");
        game.goSomewhere("down");
        game.goSomewhere("down");
        game.drop("golden sword");
        game.goSomewhere("fight the dragon");
        assertEquals(game.getGameStatus().getMessage(), "You meet your own death. Try challenge the dragon with more force next time.");
    }
}
