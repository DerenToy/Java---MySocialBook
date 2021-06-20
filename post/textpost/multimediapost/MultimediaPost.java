package post.textpost.multimediapost;

import location.Location;
import post.textpost.TextPost;
import user.User;

import java.util.ArrayList;
import java.util.Date;

public abstract class MultimediaPost extends TextPost {
    private String filename;

    protected MultimediaPost(String text, Location location, ArrayList<User> taggedFriends, Date postOriginated,
            String filename) {
        super(text, location, taggedFriends, postOriginated);
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
