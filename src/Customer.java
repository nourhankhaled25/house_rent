import java.util.Scanner;
public class Customer extends User {
    Scanner input = new Scanner(System.in);
    Register register = new Register();
    Broker broker = new Broker();
    User user;
    House house;
    Request request = new Request();

    public void receiveUser(User user) {
        this.user = user;
    }

    public Customer() {

    }

    public void options() {
        System.out.println("Dear Customer Here Is Your Options: ");
        System.out.println("1-Edit personal information.");
        System.out.println("2-See all the available houses and request.");
        System.out.println("3-Search for a house and request.");
        System.out.println("4-Edit his previous request.");
        System.out.println("5-Remove his previous request.");
        System.out.println("Please choose an option: ");
        int option = input.nextInt();
        customerOption(option);

    }//done

    public void InfoOption() {
        System.out.println("1-firstname");
        System.out.println("2-lastname");
        System.out.println("3-password");
        System.out.println("4-username");
        System.out.println("5-address");
        System.out.println("6-cellphone");
        System.out.println("7-email");
        System.out.println("8-Back");
        System.out.print("choose:");
        int choice = input.nextInt();
        editCustomerInfo(choice);

    }//done

    public void editPassword() {
        do {
            System.out.println("Enter your new password: ");
            String newPassword = input.nextLine();
            System.out.println("Enter your new password again: ");
            String confirmPassword = input.next();
            if (newPassword.equals(confirmPassword)) {
                user.password = newPassword;
                break;
            } else
                System.out.println("Invalid!\n");
        } while (true);
    }//done

    public void editEmail() {
        do {
            System.out.print("Enter your new email: ");
            String email = input.next();
            if (!email.endsWith("@gmail.com")) System.out.print("invalid email!\n");
            else if (register.checkEmail(email)) {
                user.email = email;
                break;
            } else {
                System.out.print("this email is already used\n");
            }
        } while (true);
    }//done

    public void editCustomerInfo(int option) {
        do {
            switch (option) {
                case 1:
                    System.out.print("Enter your new first name: ");
                    user.firstName = input.next();
                    break;
                case 2:
                    System.out.print("Enter your new last name: ");
                    user.lastName = input.next();
                    break;
                case 3:
                    editPassword();
                    break;
                case 4:
                    System.out.print("Enter your new username: ");
                    user.username = input.next();
                    break;
                case 5:
                    System.out.print("Enter your new address: ");
                    user.address = input.next();
                    break;
                case 6:
                    System.out.print("Enter your new cellphone: ");
                    user.cellphone = input.nextInt();
                    break;
                case 7:
                    editEmail();
                    break;
                case 8:
                    options();
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (option > 8 || option < 1);
        System.out.print("want to edit anything else?(Y/N)\n");
        String ans = input.next();
        if (ans.toLowerCase().equals("y")) {
            InfoOption();
        }
    }//done
    public void customerOption(int option) {
        String c;

        switch (option) {
            case 1:
                InfoOption();
                break;
            case 2:
                displayHouses(user.username);
                break;
            case 3:
                searchHouse();
                break;
            case 4:
                request.editRequest(user.username);
                break;
            case 5:
                request.removeRequest(user.username);
                break;
            default:
                System.out.println("Invalid option. Please choose again.");
                break;
        }
        System.out.print("Back?(y,n)");
        c = input.next();
        if (c.toLowerCase().equals("y")) options();
    }
    public void displayHouses(String customerName) {
        broker.view_houses();
        System.out.print("Do you want to request a house?(y,n)");
        String c = input.next();
        String t;
        int n;
        if (c.toLowerCase().equals("y")) {
            System.out.print("Enter house number: ");
            n = input.nextInt();
            House house1 = new House();
            house1 = broker.houses.get(n - 1);
            System.out.print("Enter request type(Buy,Rent): ");
            t = input.next();
            request.setRequest(house1, t, customerName);
        }
    }
    public void searchHouse() {
        boolean found = false;
        String c;
        System.out.print("Enter house location: ");
        String location = input.next();
        System.out.print("Enter house area: ");
        double area = input.nextDouble();
        for (House house : broker.houses) {
            if (location.equals(house.location) && area == house.area) {
                found = true;
                System.out.println("House location: " + house.location + " " + "Area: " + house.area + " " + "Deposit: " + house.deposit_value + " " + "Type: " + house.availableFor + " " + "Total price: " + house.totalPrice + " " + "Negotiable: " + house.negotiable);
                System.out.println("Request this house?(y,n)");
                c = input.next();
                if (c.toLowerCase().equals("y")) {
                    System.out.print("Enter request type(Buy,Rent): ");
                    String t = input.next();
                    request.setRequest(house, t, user.username);
                }
                break;
            }
        }
        if (!found) System.out.print("House not found!\n");
    }


}