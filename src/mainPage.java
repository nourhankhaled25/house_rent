import java.util.Scanner;

public class mainPage {
    Scanner scanner=new Scanner(System.in);
    public String username;
    public User user;
    public admin Admin=new admin();
    Register register=new Register();
    Customer customer=new Customer();
    Broker broker=new Broker();

    public mainPage(){}

    public void F1() {
        System.out.print("1-Log in\n2-Sign up\nchoose your option: ");

        int o;
        do {
            o = scanner.nextInt();
            switch (o) {
                case 1:
                    this.username = register.login();
                    break;
                case 2:
                    register.register();
                    System.out.print("Log in:\n");
                    this.username = register.login();
                    break;
                default:
                    System.out.print("Invalid Entry!\n");
            }
        } while (o > 2 || o < 1);
    }
    public void F2(){
        for (User user: register.users) if(username.equals(user.username)) this.user=user;
        if(user.type.equals("customer")){
            customer.receiveUser(user);
            customer.options();
        } else if (user.type.equals("broker")) {
            broker.options();
        } else if (user.type.equals("admin")) {
            Admin.admin_menu();
        }

    }
}
