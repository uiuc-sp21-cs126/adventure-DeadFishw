package student.adventure;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The layout of the map.
 */
public class Layout {
    private String startingRoom;
    private String endingRoom;
    private Room[] rooms;


    public void setEndingRoom(String endingRoom) {
        this.endingRoom = endingRoom;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public void setStartingRoom(String startingRoom) {
        this.startingRoom = startingRoom;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public String getEndingRoom() {
        return endingRoom;
    }

    public String getStartingRoom() {
        return startingRoom;
    }


}
