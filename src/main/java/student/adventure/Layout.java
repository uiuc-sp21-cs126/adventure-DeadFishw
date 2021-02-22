package student.adventure;

/**
 * The layout of the map.
 */
public class Layout {
    private String startingRoom;
    private String endingRoom;
    private room[] rooms;


    public void setEndingRoom(String endingRoom) {
        this.endingRoom = endingRoom;
    }

    public void setRooms(room[] rooms) {
        this.rooms = rooms;
    }

    public void setStartingRoom(String startingRoom) {
        this.startingRoom = startingRoom;
    }

    public room[] getRooms() {
        return rooms;
    }

    public String getEndingRoom() {
        return endingRoom;
    }

    public String getStartingRoom() {
        return startingRoom;
    }


}
