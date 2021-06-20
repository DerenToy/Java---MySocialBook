package post.textpost.multimediapost;

import location.Location;
import user.User;
import java.util.ArrayList;
import java.util.Date;

public class ImagePost extends MultimediaPost {

    private String imgResolution;

    // Constructor
    public ImagePost(String text, Location location, ArrayList<User> taggedFriends, Date postOriginated,
            String imgFileName, String imgResolution) {
        super(text, location, taggedFriends, postOriginated, imgFileName);
        this.imgResolution = imgResolution;

    }

    @Override
    public void displayFormat() {
        super.displayFormat();
        System.out.println("Image: " + super.getFilename());
        System.out.println("Image resolution: " + this.imgResolution);
    }
}
