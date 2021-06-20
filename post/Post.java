package post;

import user.User;
import location.Location;
import java.util.ArrayList;
import java.util.Date;

public abstract class Post {

    private Location location;
    private ArrayList<User> taggedFriends;
    private Date postOriginated;

    // Constructor
    public Post(Location location, ArrayList<User> taggedFriends, Date postOriginated) {
        this.setLocation(location);
        this.setTaggedFriends(taggedFriends);
        this.setPostOriginated(postOriginated);
    }

    // Getters and Setters
    public Date getPostOriginated() {
        return postOriginated;
    }

    public ArrayList<User> getTaggedFriends() {
        return taggedFriends;
    }

    public Location getLocation() {
        return location;
    }

    public void setTaggedFriends(ArrayList<User> taggedFriends) {
        this.taggedFriends = taggedFriends;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setPostOriginated(Date postOriginated) {
        this.postOriginated = postOriginated;
    }

    public abstract void displayFormat();
}
