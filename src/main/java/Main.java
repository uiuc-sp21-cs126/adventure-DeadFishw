import com.fasterxml.jackson.databind.ObjectMapper;
import student.adventure.Siebel;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/siebel.json");
        ObjectMapper mapper = new ObjectMapper();
        Siebel adventure = mapper.readValue(file, Siebel.class);

        // Wishing you good luck on your Adventure!
        adventure.start();
    }
}
