import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
class Deal implements Serializable {
    Random random=new Random();
    private int ID=1;
    private long Customer_ISPN;
    private long Broker_ISPN;
    public LocalDate Deal_Date;
    public LocalDate Deposit_Date;
    public String Type;
    public String Status;
    public ArrayList<Deal> deals=new ArrayList<>();
    public transient Scanner scanner=new Scanner(System.in);
    private static final String DEALS_FILE = "DEALS.dat";
    public Deal() {
        createFileIfNotExists();
        loadDealsFromFile();
    }//default constructor

    public Deal(LocalDate DDate, LocalDate DDDate, String Type, String Status){
        Deal deal=new Deal();
        deal.ID=this.ID;
        deal.Customer_ISPN= random.nextLong() % 1_000_000_000_000L;
        deal.Broker_ISPN=random.nextLong() % 1_000_000_000_000L;
        deal.Deal_Date=DDate;
        deal.Deposit_Date=DDDate;
        deal.Type=Type;
        deal.Status=Status;
        deals.add(deal);
        saveDealsToFile();
        this.ID++;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setCustomer_ISPN(int customer_ISPN) {
        Customer_ISPN = customer_ISPN;
    }

    public void setBroker_ISPN(int broker_ISPN) {
        Broker_ISPN = broker_ISPN;
    }
    public void addDeal() {
        Deal deal=new Deal();
        deal.ID=this.ID;
        deal.Customer_ISPN= random.nextLong()% 1_000_000_000_000L;
        deal.Broker_ISPN= random.nextLong()% 1_000_000_000_000L;
        deal.Deal_Date= LocalDate.now();
        System.out.print("Enter deposit date(yyyy-MM-DD): ");
        deal.Deposit_Date= LocalDate.parse(scanner.next());
        System.out.print("Enter deal type: ");
        deal.Type=scanner.next();
        deals.add(deal);
        saveDealsToFile();
        ID++;
    }
    public void doneDeals(){
        for (Deal deal: deals) {
            if(deal.Status.equals("done"))System.out.println("Customer ISPN: " + deal.Customer_ISPN + " " + "Broker ISPN: " + deal.Broker_ISPN + " " + "Deal date: " + deal.Deal_Date + " "+ "Deal type: "+deal.Type);
        }

    }
    public void passDeals(ArrayList<Deal> deals){
        deals.clear();
        deals.addAll(this.deals);
    }

    public void Cancel_Deal(int ID) {
        boolean exist = false;
        for (Deal obj : deals) {
            if (obj.getID() == ID) {
                deals.remove(obj);
                exist = true;
                break;
            }
        }
        if (exist == false) {
            System.out.println("Invalid ID!");
        }
    }

    public void Display_DDate(int ID) {
        boolean Exist = false;
        for (Deal obj : deals) {
            if (obj.getID() == ID) {
                System.out.println("Deal Date is " + obj.Deal_Date);
                Exist = true;
                break;
            }
        }
        if (!Exist) {
            System.out.println("Invalid ID!");
        }
    }

    public void Display_DDDate(int ID) {
        boolean exist = false;
        for (Deal obj : deals) {
            if (obj.getID() == ID) {
                System.out.println("Deposit Date is " + obj.Deposit_Date);
                exist = true;
                break;
            }
        }
        if (exist == false) {
            System.out.println("Invalid ID!");
        }
    }

    public void Display_Status(int ID) {
        boolean exist = false;
        for (Deal obj : deals) {
            if (obj.getID() == ID) {
                System.out.println("the deal is " + obj.Status);
                exist = true;
                break;
            }
        }
        if (exist == false) {
            System.out.println("Invalid ID!");
        }
    }

    public void Display_Type(int ID) {
        boolean exist = false;
        for (Deal obj : deals) {
            if (obj.getID() == ID) {
                System.out.println("Type "+obj.Type);
                exist = true;
                break;
            }
        }
        if (exist == false) {
            System.out.println("Invalid ID!");
        }
    }

    public void saveDealsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DEALS_FILE))) {
            oos.writeObject(deals);
            System.out.println("Deals saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDealsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DEALS_FILE))) {
            //noinspection unchecked
            deals = (ArrayList<Deal>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions, e.g., file not found or class not found
            e.printStackTrace();
        }
    }

    public void createFileIfNotExists() {
        File file = new File(DEALS_FILE);
        try {
            if (file.createNewFile()) {

            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


