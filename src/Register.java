import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Register {
    Scanner scanner = new Scanner(System.in);//#1 Register Method
    //Attributes
    int id=1;
    public ArrayList<User> users = new ArrayList<>();
    private static final String USERS_FILE = "USERS.dat";
    Register (){
        createFileIfNotExists();
        loadUsersFromFile();
    }//Default Constructor
    public void register() {
        User user=new User();
        user.Id=id;
        System.out.print("Enter First Name: ");
        user.firstName= scanner.next();//input first name
        System.out.print("Enter Last Name: ");
        user.lastName = scanner.next();//input last name
        System.out.print("Enter username: ");
        user.username = scanner.next();//changeable either take it from the user or leave this
        System.out.print("Enter password: ");
        user.password = scanner.next();
        getType(user);
        System.out.print("Enter your Address: ");
        user.address = scanner.next();//input address
        System.out.print("Enter your Cellphone number: ");
        user.cellphone = scanner.nextInt();//input phone number
        getEmail(user);
        users.add(user);
        id++;
        saveUsersToFile();
    }

    public void getEmail(User user){
        do{
            System.out.print("Enter your email address: ");
            String email=scanner.next();
            if(!email.endsWith("@gmail.com")) System.out.print("invalid email!\n");
            else if(checkEmail(email)){
                user.email=email;
                break;
            }
            else {
                System.out.print("this email is already used\n");
            }
        }while (true);


    }

    public Boolean checkEmail(String email){
        for (User user : users) {
            if (email.equals(user.email)) {
                return false; // Email already used
            }
        }
        return true; // Email is available
    }

    public void getType(User user) {
        do {
            System.out.print("Broker or Customer: ");
            String type = scanner.next();
            if (type.toLowerCase().equals("customer")) {
                user.type = type;
                break;
            } else if (type.toLowerCase().equals("broker")) {
                user.type = type;
                break;
            } else System.out.print("Invalid Entry!\n");
        }while (true);
    }

    public String login(){
        Boolean check=false;
        String username;
        String password;
        do {
            System.out.print("Username: ");
            username = scanner.next();
            System.out.print("Password: ");
            password = scanner.next();

            for (User user : users) {
                if (username.equals(user.username) && password.equals(user.password)) {
                    System.out.println("Logged in successfully!");
                    check = true;
                    break;
                }
            }

            if (!check) {
                System.out.println("Invalid username or password!");
            }

        } while (!check);
        return username;
    }
    public void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
            System.out.println("Users saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadUsersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            //noinspection unchecked
            users = (ArrayList<User>) ois.readObject();
            // Find the maximum user id
            id = users.stream().mapToInt(User::getId).max().orElse(0);
        } catch (IOException | ClassNotFoundException e) {
            // File not found or other exceptions
            // This is normal on the first run when the file doesn't exist
            // You can handle it based on your application's needs
        }
    }
    public void createFileIfNotExists() {
        File file = new File(USERS_FILE);
        try {
            if (file.createNewFile()) {
            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

