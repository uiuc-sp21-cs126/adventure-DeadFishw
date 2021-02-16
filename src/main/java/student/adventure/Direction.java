package student.adventure;

/**
 * Directions indication which direction and the destination of that direction.
 */
public class Direction {
    private String directionName;
    //Room to be in if heading to that direction.
    private String room;

    public Direction() {

    }

    public String getDirectionName() {
        return directionName;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

}
