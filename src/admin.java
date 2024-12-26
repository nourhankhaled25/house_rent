import java.util.Scanner;

public class admin {
    Scanner input = new Scanner(System.in);
    private int id;
    private static final String password = "admin";
    private static final String userName = "admin";
    private String email;
    private String cellphone;
    private String address;
    private static final String Type = "admin";
    private String firstName;
    private String lastName;
    Request request = new Request();
    Register register = new Register();

    public admin() {
    }

    public void admin_menu() {
        int c;
        String o;
        System.out.println("Admin menu:");
        System.out.println("1-Add broker/customer");
        System.out.println("2-Remove broker/customer");
        System.out.println("3-View all customers");
        System.out.println("4-View all brokers");
        System.out.println("5-View all requests");
        System.out.println("Enter your option: ");

        do {
            c = input.nextInt();
            switch (c) {
                case 1:
                    addUser();
                    break;
                case 2:
                    removeUsr();
                    break;
                case 3:
                    viewCustomers();
                    break;
                case 4:
                    viewBrokers();
                    break;
                case 5:
                    viewRequest();
                    break;
                default:
                    System.out.println("Invalid entry!");
            }
        } while (c > 5 || c < 1);
        System.out.print("Back?(y,n)");
        o = input.next();
        if (o.toLowerCase().equals("y")) admin_menu();
    }

    public void addUser() {
        User user=new User();
        System.out.print("Customer or Broker?");
        user.type = input.next();
        System.out.print("Firstname: ");
        user.firstName = input.next();
        System.out.print("Lastname: ");
        user.lastName = input.next();
        System.out.print("Username: ");
        user.username = input.next();
        System.out.print("Address: ");
        user.address = input.next();
        System.out.print("Cellphone: ");
        user.cellphone = input.nextInt();
        System.out.print("Email: ");
        user.email = input.next();
        System.out.print("Password: ");
        user.password = input.next();
        register.users.add(user);
        register.saveUsersToFile();
    }

    public void removeUsr() {
        int c;
        int i = 1;
        System.out.println("1-Customer\n2-Broker");
        c = input.nextInt();
        if (c == 1) {
            System.out.println("Number" + "\tFirstName" + "\tLastName" + "\tUserName" + "\tType" + "\t\t\tAddress" + "\t\tCellPhone" + "\t\tEmail\n");
            for (User user : register.users) {
                if (user.type.toLowerCase().equals("customer"))
                    System.out.println(i + "\t\t" + user.firstName + "\t\t" + user.lastName + "\t\t" + user.username + "\t\t" + user.type + "\t\t" + user.address + "\t\t" + user.cellphone + "\t\t" + user.email);
                i++;
            }
        } else if (c == 2) {
            System.out.println("Number" + "\tFirstName" + "\tLastName" + "\tUserName" + "\tType" + "\t\tAddress" + "\t\tCellPhone" + "\t\tEmail\n");
            for (User user : register.users) {
                if (user.type.toLowerCase().equals("broker"))
                    System.out.println(i + "\t\t" + user.firstName + "\t\t" + user.lastName + "\t\t" + user.username + "\t\t" + user.type + "\t\t" + user.address + "\t\t" + user.cellphone + "\t\t" + user.email);
                i++;
            }
        }
        System.out.print("Enter the number of the one you want to remove: ");
        c = input.nextInt();
        register.users.remove(c - 1);
        register.saveUsersToFile();
    }

    public void viewCustomers() {
        int i = 1;
        System.out.println("Number" + "\tFirstName" + "\tLastName" + "\tUserName" + "\tType" + "\t\t\tAddress" + "\t\tCellPhone" + "\t\tEmail\n");
        for (User user : register.users) {
            if (user.type.toLowerCase().equals("customer")) {
                System.out.println(i + "\t\t" + user.firstName + "\t\t" + user.lastName + "\t\t" + user.username + "\t\t" + user.type + "\t\t" + user.address + "\t\t" + user.cellphone + "\t\t" + user.email);
                i++;
            }
        }
    }

    public void viewBrokers() {
        int i = 1;
        System.out.println("Number" + "\tFirstName" + "\tLastName" + "\tUserName" + "\tType" + "\t\tAddress" + "\t\tCellPhone" + "\t\tEmail\n");
        for (User user : register.users) {
            if (user.type.toLowerCase().equals("broker")) {
                System.out.println(i + "\t\t" + user.firstName + "\t\t" + user.lastName + "\t\t" + user.username + "\t\t" + user.type + "\t\t" + user.address + "\t\t" + user.cellphone + "\t\t" + user.email);
                i++;
            }
        }
    }

    public void viewRequest() {
        int i = 1;
        for (Request request1 : request.requests) {
            System.out.println(i + "-" + request1.customerName + " " + request1.Type + " " + request1.request_date);
            i++;
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
