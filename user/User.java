package user;

import post.Post;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class User {

    private static int userIdCounter = 1;

    private int userID;
    private String name, username, password, graduatedSchool;
    private Date dateOfBirth, lastLoginDate;
    private ArrayList<User> collectionOfFriends, collectionOfBlockedUsers;
    private ArrayList<Post> collectionOfPosts;

    // Constructor 1
    public User(String name, String username, String password, Date dateOfBirth, String graduatedSchool) {
        this.userID = userIdCounter;
        userIdCounter++;
        this.name = name;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.graduatedSchool = graduatedSchool;
        this.lastLoginDate = new Date();
        this.collectionOfFriends = new ArrayList<User>();
        this.collectionOfPosts = new ArrayList<Post>();
        this.collectionOfBlockedUsers = new ArrayList<User>();
    }

    // Constructor 2
    public User(String name, String username, String password, Date dateOfBirth, String graduatedSchool,
            Date lastLoginDate, ArrayList<User> collectionOfFriends, ArrayList<Post> collectionOfPosts,
            ArrayList<User> collectionOfBlockedUsers) {
        this.userID = userIdCounter;
        userIdCounter++;
        this.name = name;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.graduatedSchool = graduatedSchool;
        this.lastLoginDate = lastLoginDate;
        this.collectionOfFriends = collectionOfFriends;
        this.collectionOfPosts = collectionOfPosts;
        this.collectionOfBlockedUsers = collectionOfBlockedUsers;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\n" + "Username: " + this.username + "\n" + "Date of Birth: "
                + new SimpleDateFormat("MM/dd/yyyy").format(this.dateOfBirth) + "\n" + "School: "
                + this.graduatedSchool;
    }

    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGraduatedSchool() {
        return graduatedSchool;
    }

    public void setGraduatedSchool(String graduatedSchool) {
        this.graduatedSchool = graduatedSchool;
    }

    public ArrayList<User> getCollectionOfFriends() {
        return collectionOfFriends;
    }

    public ArrayList<Post> getCollectionOfPosts() {
        return collectionOfPosts;

    }

    public ArrayList<User> getCollectionOfBlockedUsers() {
        return collectionOfBlockedUsers;

    }

    // This method allows the user to log into the system by checking the entered
    // password.
    public User signIn(String password, User currentSignedInUser) {
        if (getPassword().equals(password)) {
            currentSignedInUser = this;
            System.out.println("You have successfully signed in.");
            this.lastLoginDate = new Date();
            return currentSignedInUser;
        } else {
            System.out.println("Invalid username or password! Please try again.");
            return null;
        }
    }

    // This method allows the user to log out of the system.
    public User signOut(User currentSignedInUser) {
        currentSignedInUser = null;
        System.out.println("You have successfully signed out.");
        return currentSignedInUser;
    }

    // This method allows the information to be updated according to the personal
    // information entered by the user.
    public boolean updateProfile(String name, String birthDateStr, String graduatedSchool) throws Exception {
        setName(name);
        dateOfBirth = new SimpleDateFormat("MM/dd/yyyy").parse(birthDateStr);
        setDateOfBirth(dateOfBirth);
        setGraduatedSchool(graduatedSchool);
        System.out.println("Your user profile has been successfully updated.");
        return true;
    }

    // This method allows the user to change their password after checking the old
    // password entered.
    public boolean changePassword(String oldPassword, String newPassword) {
        if (!getPassword().equals(oldPassword)) {
            System.out.println("Password mismatch!");
            return false;
        } else {
            setPassword(newPassword);
            return true;
        }
    }

    // This method allows the user to add a new friend if the person they want to
    // add as a friend is not his/her friend.
    public boolean addFriend(User friend) {
        if (!this.getCollectionOfFriends().contains(friend)) {
            this.collectionOfFriends.add(friend);
            System.out.println(friend.getUserName() + " has been successfully added to your friend list.");
            return true;
        } else {
            System.out.println("This user is already in your friend list!");
            return false;
        }
    }

    // This method allows the user to remove anyone on the user's friend list from
    // their friends list.
    public boolean removeFriend(User friend) {
        if (this.getCollectionOfFriends().contains(friend)) {
            this.collectionOfFriends.remove(friend);
            System.out.println(friend.getUserName() + " has been successfully removed from your friend list.");
            return true;
        } else {
            System.out.println("No such friend!");
            return false;
        }
    }

    // This method allows blocking any user on the system.
    public boolean blockUser(User user) {
        getCollectionOfBlockedUsers().add(user);
        System.out.println(user.getUserName() + " has been successfully blocked.");
        return true;
    }

    // This method allows to unblock any user on the system.
    public boolean unblockUser(User user) {
        if (getCollectionOfBlockedUsers().remove(user)) {
            System.out.println(user.getUserName() + " has been successfully unblocked.");
            return true;
        } else {
            System.out.println("No such user in your blocked-user list!");
            return false;
        }
    }

    // This method allows users in the user's friend list to be listed.
    public void listFriends() {
        if (getCollectionOfFriends().isEmpty()) {
            System.out.println("You have not added any friend yet!");
            return;
        }
        for (User user : getCollectionOfFriends()) {
            System.out.println(user);
            System.out.println("-----------------------");
        }
    }

    // This method ensures that all users on the system are listed.
    public void listAllUsers(HashMap<String, User> users) {
        for (User user : users.values()) {
            System.out.println(user);
            System.out.println("-----------------------");
        }
    }

    // This method allows the user to view their blocked friends.
    public void showBlockedFriends() {
        boolean isBlockedAnyFriend = false;
        for (User user : getCollectionOfBlockedUsers()) {
            if (getCollectionOfFriends().contains(user)) {
                isBlockedAnyFriend = true;
                System.out.println(user);
                System.out.println("-----------------------");
            }
        }
        if (!isBlockedAnyFriend) {
            System.out.println("You haven’t blocked any friend yet!");
        }
    }

    // This method allows the user to view blocked users.
    public void showBlockedUsers() {
        if (getCollectionOfBlockedUsers().isEmpty()) {
            System.out.println("You haven’t blocked any user yet!");
            return;
        }
        for (User user : getCollectionOfBlockedUsers()) {
            System.out.println(user);
            System.out.println("-----------------------");
        }
    }

    // This method allows the user to add posts to the system.
    public void addPost(Post post) {
        ArrayList<User> taggedFriends = new ArrayList<>();
        for (User user : post.getTaggedFriends()) {
            if (!getCollectionOfFriends().contains(user)) {
                System.out.println(user.getUserName() + " is not your friend, and will not be tagged!");
            } else {
                taggedFriends.add(user);
            }
        }
        post.setTaggedFriends(taggedFriends);
        collectionOfPosts.add(post);
        System.out.println("The post has been successfully added.");
    }

    // This method allows the user to remove of their last post.
    public void removeLastPost() {
        if (!collectionOfPosts.isEmpty()) {
            collectionOfPosts.remove(collectionOfPosts.size() - 1);
            System.out.println("Your last post has been successfully removed.");
        } else {
            System.out.println("Error: You do not have any post.");
        }
    }
}
