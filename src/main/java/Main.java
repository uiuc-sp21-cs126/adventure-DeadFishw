import com.fasterxml.jackson.databind.ObjectMapper;
import student.adventure.GameEngine;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        /*HttpServer server = AdventureServer.createServer(AdventureResource.class);
        server.start();*/
        File file = new File("src/main/resources/Adventure.json");
        ObjectMapper mapper = new ObjectMapper();
        GameEngine adventure = new GameEngine(file);

        // Wishing you good luck on your Adventure!
        adventure.startInConsole();
    }
}
