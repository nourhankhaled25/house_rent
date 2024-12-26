import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Scanner;
import java.util.Random;
public class Request implements Serializable {
    String customerName;
    public transient Scanner input = new Scanner(System.in);
    Random random = new Random();
    private int ID = 1;
    private long customer_ISBN;
    LocalDate request_date;
    private House Requested_house;
    String Type;//(Buy),(Rent)
    public ArrayList<Request> requests = new ArrayList<>();
    private static final String REQUESTS_FILE = "REQUESTS.dat";
    public Request() {
        createFileIfNotExists();
        loadRequestsFromFile();
    }
    public Request(LocalDate RDate,House RHouse,String Type) {
        Request request=new Request();
        request.ID=this.ID;
        request.customer_ISBN=random.nextLong() % 1_000_000_000_000L;
        request.request_date=RDate;
        request.Requested_house=RHouse;
        request.Type=Type;
        requests.add(request);
        saveRequestsToFile();
        this.ID++;
    }
    public void setRequest(House house, String type, String customerName) {
        Request request = new Request();
        request.ID = this.ID;
        request.customerName=customerName;
        request.customer_ISBN = random.nextLong() % 1_000_000_000_000L;
        request.request_date = LocalDate.now();
        request.Requested_house = house;
        request.Type = type;
        requests.add(request);
        saveRequestsToFile();
        this.ID++;
    }
    public void editRequest(String customerName) {
        String c;
        for (Request request : requests) {
            if (customerName.equals(request.customerName)) {
                System.out.print("The current type is " + request.Type + "\nDo you want to change your request type?(y,n)");
                c = input.next();
                if (c.toLowerCase().equals("y")) {
                    System.out.print("Enter Buy / Rent: ");
                    request.Type = input.next();
                }
            }
        }
    }
    public void removeRequest(String customerName) {
        try {
            for (Request request1 : requests) {
                if (customerName.equals(request1.customerName)) {
                    requests.remove(request1);
                    System.out.print("Request removed");
                    saveRequestsToFile();
                }
            }

        }catch (Exception exc){
            exc.getMessage();
        }
    }
    public void saveRequestsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(REQUESTS_FILE))) {
            oos.writeObject(requests);
            System.out.println("Requests saved to file: " + REQUESTS_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadRequestsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(REQUESTS_FILE))) {
            //noinspection unchecked
            requests = (ArrayList<Request>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions (file not found, etc.)
            e.printStackTrace();
        }
    }
    public void createFileIfNotExists() {
        File file = new File(REQUESTS_FILE);
        try {
            if (file.createNewFile()) {
            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
