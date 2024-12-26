import java.io.*;
import java.util.ArrayList;

public class House implements Serializable {
    public static final long serialVersionUID = -8839363579341435792L;
    protected String location;
    protected double area;
    protected int deposit_value;

    protected int rent_Value;
    protected boolean availableFor;//true sell,false rent
    protected int totalPrice;
    protected boolean negotiable;
    public ArrayList<House> houses=new ArrayList<>();
    private static final String HOUSES_FILE = "HOUSES.dat";
    public void passHouses(ArrayList<House> houses){
        houses.clear();
        houses.addAll(this.houses);
    }
    public House(){
        createFileIfNotExists();
        loadHousesFromFile();
    }
    public House(String location,double area,int deposit_value,boolean availableFor,int totalPrice, boolean negotiable,int rent_Value) {
        House house=new House();
        house.location = location;
        house.area = area;
        house.deposit_value = deposit_value;
        house.availableFor = availableFor;
        house.totalPrice = totalPrice;
        house.negotiable = negotiable;
        house.rent_Value = rent_Value;
        houses.add(house);
        saveHousesToFile();
    }

    public void pass(ArrayList<House> houses){
        houses=this.houses;
    }
    public void clear(){
        this.houses.clear();
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getDeposit_value() {
        return deposit_value;
    }


    public void setDeposit_value(int deposit_value) {
        this.deposit_value = deposit_value;
    }

    public void setAvailableFor(boolean availableFor){
        this.availableFor=availableFor;
    }

    public boolean getAvailableFor(){
        return availableFor;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean getNegotiablePrice() {
        return negotiable;
    }

    public void setNegotiablePrice(boolean negotiablePrice) {
        this.negotiable = negotiablePrice;
    }
    public void saveHousesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HOUSES_FILE))) {
            oos.writeObject(houses);
            System.out.println("Houses saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadHousesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HOUSES_FILE))) {
            houses = (ArrayList<House>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void createFileIfNotExists(){
        File file = new File(HOUSES_FILE);
        try {
            if (file.createNewFile()) {
            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}