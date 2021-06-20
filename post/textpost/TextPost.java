package post.textpost;

import post.Post;
import user.User;
import location.Location;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TextPost extends Post {

    private String text;

    // Constructor
    public TextPost(String text, Location location, ArrayList<User> taggedFriends, Date postOriginated) {
        super(location, taggedFriends, postOriginated);
        this.text = text;
    }

    // Getter
    public String getText() {
        return text;
    }

    @Override
    public void displayFormat() {
        System.out.println(this.getText());
        System.out.println("Date: " + new SimpleDateFormat("MM/dd/yyyy").format(super.getPostOriginated()));
        System.out.println(super.getLocation());
        if (super.getTaggedFriends().size() != 0) {
            ArrayList<String> friendNames = new ArrayList<String>();
            for (User u : this.getTaggedFriends())
                friendNames.add(u.getName());
            System.out.println("Friends tagged in this post: " + String.join(", ", friendNames));
        }

    }
}
