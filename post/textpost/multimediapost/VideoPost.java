package post.textpost.multimediapost;

import location.Location;
import user.User;
import java.util.ArrayList;
import java.util.Date;

public class VideoPost extends MultimediaPost {

    private static final int maxLength = 10;
    private float videoDuration;

    // Constructor
    public VideoPost(String text, Location location, ArrayList<User> taggedFriends, Date postOriginated,
            String videoFileName, float videoDuration) throws Exception {
        super(text, location, taggedFriends, postOriginated, videoFileName);

        if (videoDuration > maxLength)
            throw new Exception("Error: Your video exceeds maximum allowed duration of " + maxLength + " minutes.");
        this.videoDuration = videoDuration;

    }

    @Override
    public void displayFormat() {
        super.displayFormat();
        System.out.println("Video: " + super.getFilename());
        System.out.println("Video duration: " + this.videoDuration + " minutes");
    }
}
