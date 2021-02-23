package student.adventure;

/**
 * The layout of the map.
 */
public class Layout {
    private String startingRoom;
    private String endingRoom;
    private Room[] Rooms;


    public void setEndingRoom(String endingRoom) {
        this.endingRoom = endingRoom;
    }

    public void setRooms(Room[] Rooms) {
        this.Rooms = Rooms;
    }

    public void setStartingRoom(String startingRoom) {
        this.startingRoom = startingRoom;
    }

    public Room[] getRooms() {
        return Rooms;
    }

    public String getEndingRoom() {
        return endingRoom;
    }

    public String getStartingRoom() {
        return startingRoom;
    }


}
