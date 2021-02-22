package student.adventure;

/**
 * The layout of the map.
 */
public class Layout {
    private String startingRoom;
    private String endingRoom;
    private Region[] regions;


    public void setEndingRoom(String endingRoom) {
        this.endingRoom = endingRoom;
    }

    public void setRooms(Region[] regions) {
        this.regions = regions;
    }

    public void setStartingRoom(String startingRoom) {
        this.startingRoom = startingRoom;
    }

    public Region[] getRooms() {
        return regions;
    }

    public String getEndingRoom() {
        return endingRoom;
    }

    public String getStartingRoom() {
        return startingRoom;
    }


}
