// Name, Surname: Kıymet Deren TOY 
// Number: 170709012

import post.textpost.multimediapost.ImagePost;
import post.Post;
import post.textpost.TextPost;
import post.textpost.multimediapost.VideoPost;
import user.User;
import location.Location;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class MySocialBook {

    public static HashMap<String, User> users = new HashMap<String, User>();
    public static User currentSignedInUser = null;

    public static void main(String[] args) {

        // Reading users from the given file ('users.txt')
        try (Scanner scanner = new Scanner(new File(args[0]), "UTF8")) {
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] words = data.split("\t");

                Date dateOfBirth = null;
                try {
                    dateOfBirth = new SimpleDateFormat("MM/dd/yyyy").parse(words[3]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                // Creating User objects according to given parameters in this file
                User user = new User(words[0], words[1], words[2], dateOfBirth, words[4]);
                // Saving these User objects to the collection of users variable 'users'
                users.put(words[1], user);

            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException ignored) {
            ignored.printStackTrace();
        }

        // Reading 'commands.txt' file
        try (Scanner scanner = new Scanner(new File(args[1]))) {
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] inputWords = data.split("\t");

                System.out.println("Command: " + data);

                // Calling functions/methods according to the first word in the lines
                switch (inputWords[0]) {
                    case "SIGNIN":
                        User u = users.get(inputWords[1]);
                        if (u != null) {
                            currentSignedInUser = u.signIn(inputWords[2], currentSignedInUser);
                        } else {
                            System.out.println("Invalid username or password! Please try again.");
                        }
                        break;
                    case "ADDUSER":
                        addUser(inputWords[1], inputWords[2], inputWords[3], inputWords[4], inputWords[5]);
                        break;
                    case "REMOVEUSER":
                        removeUser(Integer.parseInt(inputWords[1]));
                        break;
                    case "SHOWPOSTS":
                        showPosts(inputWords[1]);
                        break;
                    // In all cases after this, it is checked whether the user has logged in to the
                    // system.
                    case "SIGNOUT":
                        if (!checkUserSignedIn(currentSignedInUser)) {
                            printErrorSignedIn();
                            break;
                        }
                        currentSignedInUser = currentSignedInUser.signOut(currentSignedInUser);
                        break;
                    case "UPDATEPROFILE":
                        if (!checkUserSignedIn(currentSignedInUser)) {
                            printErrorSignedIn();
                            break;
                        }
                        try {
                            currentSignedInUser.updateProfile(inputWords[1], inputWords[2], inputWords[3]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "CHPASS":
                        if (!checkUserSignedIn(currentSignedInUser)) {
                            printErrorSignedIn();
                            break;
                        }
                        currentSignedInUser.changePassword(inputWords[1], inputWords[2]);
                        break;
                    case "ADDFRIEND":
                        if (!checkUserSignedIn(currentSignedInUser)) {
                            printErrorSignedIn();
                            break;
                        }
                        u = users.get(inputWords[1]);
                        if (u != null) {
                            currentSignedInUser.addFriend(u);
                        } else {
                            System.out.println("No such user!");
                        }
                        break;
                    case "REMOVEFRIEND":
                        if (!checkUserSignedIn(currentSignedInUser)) {
                            printErrorSignedIn();
                            break;
                        }
                        u = users.get(inputWords[1]);
                        if (u != null) {
                            currentSignedInUser.removeFriend(u);
                        } else {
                            System.out.println("No such user!");
                        }
                        break;
                    case "BLOCK":
                        if (!checkUserSignedIn(currentSignedInUser)) {
                            printErrorSignedIn();
                            break;
                        }
                        u = users.get(inputWords[1]);
                        if (u != null) {
                            currentSignedInUser.blockUser(u);
                        } else {
                            System.out.println("No such user!");
                        }
                        break;
                    case "UNBLOCK":
                        if (!checkUserSignedIn(currentSignedInUser)) {
                            printErrorSignedIn();
                            break;
                        }
                        u = users.get(inputWords[1]);
                        if (u != null) {
                            currentSignedInUser.unblockUser(u);
                        } else {
                            System.out.println("No such user!");
                        }
                        break;
                    case "LISTFRIENDS":
                        if (!checkUserSignedIn(currentSignedInUser)) {
                            printErrorSignedIn();
                            break;
                        }
                        currentSignedInUser.listFriends();
                        break;
                    case "LISTUSERS":
                        if (!checkUserSignedIn(currentSignedInUser)) {
                            printErrorSignedIn();
                            break;
                        }
                        currentSignedInUser.listAllUsers(users);
                        break;
                    case "SHOWBLOCKEDFRIENDS":
                        if (!checkUserSignedIn(currentSignedInUser)) {
                            printErrorSignedIn();
                            break;
                        }
                        currentSignedInUser.showBlockedFriends();
                        break;
                    case "SHOWBLOCKEDUSERS":
                        if (!checkUserSignedIn(currentSignedInUser)) {
                            printErrorSignedIn();
                            break;
                        }
                        currentSignedInUser.showBlockedUsers();
                        break;
                    case "ADDPOST-TEXT":
                        if (!checkUserSignedIn(currentSignedInUser)) {
                            printErrorSignedIn();
                            break;
                        }
                        currentSignedInUser.addPost(new TextPost(inputWords[1],
                                new Location(Double.parseDouble(inputWords[2]), Double.parseDouble(inputWords[3])),
                                parseUsers(inputWords[4]), new Date()));
                        break;
                    case "ADDPOST-IMAGE":
                        if (!checkUserSignedIn(currentSignedInUser)) {
                            printErrorSignedIn();
                            break;
                        }
                        currentSignedInUser.addPost(new ImagePost(inputWords[1],
                                new Location(Double.parseDouble(inputWords[2]), Double.parseDouble(inputWords[3])),
                                parseUsers(inputWords[4]), new Date(), inputWords[5], inputWords[6]));
                        break;
                    case "ADDPOST-VIDEO":
                        if (!checkUserSignedIn(currentSignedInUser)) {
                            printErrorSignedIn();
                            break;
                        }
                        try {
                            currentSignedInUser
                                    .addPost(new VideoPost(inputWords[1],
                                            new Location(Double.parseDouble(inputWords[2]),
                                                    Double.parseDouble(inputWords[3])),
                                            parseUsers(inputWords[4]), new Date(), inputWords[5],
                                            Float.parseFloat(inputWords[6])));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "REMOVELASTPOST":
                        if (!checkUserSignedIn(currentSignedInUser)) {
                            printErrorSignedIn();
                            break;
                        }
                        currentSignedInUser.removeLastPost();
                        break;
                    default:
                        break;
                }
                System.out.println("---------------------------");
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException ignored) {
            ignored.printStackTrace();
        }
    }

    // This function checks whether users are logged in to use some method.
    private static boolean checkUserSignedIn(User currentSignedInUser) {
        if (currentSignedInUser == null) {
            return false;
        }
        return true;
    }

    // This function prints the error message if users tried to use some method
    // without logging in.
    private static boolean printErrorSignedIn() {
        System.out.println("Error: Please sign in and try again.");
        return false;
    }

    // This function allows a colon-separated tagged string of users to be
    // comma-separated and added to a list.
    private static ArrayList<User> parseUsers(String usersStr) {
        String[] taggedUsersStr = usersStr.split(":");
        ArrayList<User> taggedUsers = new ArrayList<>();
        for (String userStr : taggedUsersStr) {
            User user = users.get(userStr);
            if (user != null) {
                taggedUsers.add(user);
            } else {
                System.out.println(userStr + " is not your friend, and will not be tagged!");
            }
        }
        return taggedUsers;
    }

    // This function adds users to the system.
    private static void addUser(String name, String username, String password, String dateOfBirthStr,
            String graduatedSchool) {
        Date dateOfBirth = null;
        try {
            dateOfBirth = new SimpleDateFormat("MM/dd/yyyy").parse(dateOfBirthStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User newUser = new User(name, username, password, dateOfBirth, graduatedSchool);
        if (users.get(newUser.getUserName()) == null) {
            users.put(newUser.getUserName(), newUser);
            System.out.println(newUser.getName() + " has been successfully added.");
        } else {
            System.out.println("Please enter a unique username!");
        }
    }

    // This function removes users from the system.
    private static void removeUser(int userId) {
        for (User u : users.values()) {
            if (userId == u.getUserID()) {
                users.remove(u.getUserName());
                System.out.println("User has been successfully removed.");
                return;
            }
        }
        System.out.println("No such user");
    }

    // This function shows user's post by using their username.
    private static void showPosts(String username) {
        User user = users.get(username); // Checking if 'users' hashmap contains a user using this username
        if (user != null) {
            System.out.println("**************");
            System.out.println(user.getName() + "'s Posts");
            System.out.println("**************");
            for (Post post : user.getCollectionOfPosts()) {
                post.displayFormat();
                System.out.println("------------------");
            }
        } else {
            System.out.println("No such user!");
        }
    }
}
