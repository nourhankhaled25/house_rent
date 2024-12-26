import java.util.ArrayList;
import java.util.Scanner;

public class Broker extends User{
    Deal deal=new Deal();
    public ArrayList<Deal> deals = new ArrayList<>();
    public ArrayList<House> houses = new ArrayList<>();
    House house=new House();
    Scanner input = new Scanner(System.in);


    public Broker() {
        deal.passDeals(this.deals);
        house.passHouses(this.houses);
    }

    public void options(){
        System.out.println("welcome broker \n broker manu:");
        System.out.println("1 - add house");
        System.out.println("2 - remove house");
        System.out.println("3 - view your house list");
        System.out.println("4 - open deal");
        System.out.println("5 - display your done deals");
        System.out.println("6 - search for a house");
        int option;
        do {
            System.out.println("Enter an option: ");
            option =input.nextInt();
            switch (option) {
                case 1:
                    addHouse();
                    break;
                case 2:
                    removeHouse();
                    break;
                case 3:
                    view_houses();
                    break;
                case 4:
                    deal.addDeal();
                    break;
                case 5:
                    deal.doneDeals();
                    break;
                case 6:
                    searchHouse();
                    break;
                default:
                    System.out.println("invalid choice ");
            }
        }while(option>6||option<1);
        System.out.print("Back to main menu?(y/n)");
        String c=input.next();
        if (c.toLowerCase().equals("y"))options();
    }

    public void addHouse(){
        House house = new House();
        System.out.print("Enter house location: ");
        house.location = input.next();
        System.out.print("Enter house area: ");
        house.area = input.nextDouble();
        addHouseType(house);
        if (house.availableFor==true){
            System.out.print("Enter Deposit value: ");
            house.deposit_value = input.nextInt();
            System.out.print("If the price of the house is negotiable enter 'yes',if not enter 'no': ");
            do {
                String massage = input.next();
                if (massage.toLowerCase().equals("yes")) {
                    house.negotiable = true;
                    break;
                } else if (massage.toLowerCase().equals("no")) {
                    house.negotiable = false;
                    break;
                }else System.out.println("Invalid entry!");
            }while (true);
            System.out.print("enter the total price: ");
            house.totalPrice = input.nextInt();
            houses.add(house);
        }else if (house.availableFor==false){
            System.out.println("Enter the rent value: ");
            house.rent_Value=input.nextInt();
            System.out.print("If the price of the house is negotiable enter 'yes',if not enter 'no': ");
            do {
                String massage = input.next();
                if (massage.toLowerCase().equals("yes")) {
                    house.negotiable = true;
                    break;
                } else if (massage.toLowerCase().equals("no")) {
                    house.negotiable = false;
                    break;
                }else System.out.println("Invalid entry!");
            }while (true);
            houses.add(house);
        }
        house.houses.add(house);
        house.saveHousesToFile();
    }
    public void view_houses() {
        int t;
        boolean c=false;
        String type;
        String o;
        System.out.println("Choose houses type:\n1-Houses available for rent\n2-Houses available for sell");
        do {
            t=input.nextInt();
            switch (t) {
                case 1:c=false;
                    break;
                case 2:c=true;
                    break;
                default:System.out.println("Invalid entry!");
            }
        }while(t>2||t<1);
        if(c==false) {
            type="rent";
            System.out.println("Number\tLocation\tArea(m^2)\tRentCost($)\t\tType\t\tNegotiation\n");
            for (int i = 0; i < houses.size(); i++) {o=houses.get(i).negotiable?"yes":"no";
                if (houses.get(i).availableFor==c) System.out.println((i + 1) + "\t\t" + houses.get(i).location + "\t\t" + houses.get(i).area + "\t\t" + houses.get(i).rent_Value + "\t\t\t" + type + "\t\t" + "\t\t\t" + o);
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------");
        } else if (c==true) {
            type="sell";
            System.out.println("Number\tLocation\tArea(m^2)\tDeposit($)\tType\tPrice\tNegotiation\n");
            for (int i = 0; i < houses.size(); i++) {o=houses.get(i).negotiable?"yes":"no";
                if (houses.get(i).availableFor==c) System.out.println((i + 1) + "\t\t" + houses.get(i).location + "\t\t" + houses.get(i).area + "\t\t" + houses.get(i).deposit_value + "\t\t" + type + "\t  " + houses.get(i).totalPrice + "\t" + o);
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------");
        }


    }
    public void removeHouse(){
        view_houses();
        System.out.println("enter the house number: ");
        int i=input.nextInt() - 1;
        house.houses.remove(i);
        houses.remove(i);
        house.saveHousesToFile();
    }
    public void addHouseType(House house){
        System.out.print("Enter 'rent' for rentable house or 'sell' for saleable house: ");
        do {
            String massage = input.next();
            if (massage.toLowerCase().equals("rent")) {
                house.availableFor = false;
                break;
            } else if (massage.toLowerCase().equals("sell")) {
                house.availableFor = true;
                break;
            } else System.out.println("Invalid Entry!");

        }while (true);
    }

    public void searchHouse(){
        String c;
        boolean found=false;
        System.out.print("Enter house location: ");
        String location=input.next();
        System.out.print("Enter house area: ");
        double area=input.nextDouble();
        for (House house: houses) {
            if(location.equals(house.location)&&area==house.area){
                found=true;
                System.out.println("House location: "+ house.location+" "+"Area: "+ house.area+" "+"Deposit: "+house.deposit_value+" "+ "Type: "+house.availableFor+" "+"Total price: "+house.totalPrice+" "+ "Negotiable: "+house.negotiable);
                System.out.println("Remove?(y,n)");
                c=input.next();
                if(c.toLowerCase().equals("y")){
                    house.houses.remove(house);
                    houses.remove(house);
                    house.saveHousesToFile();
                }
                break;}
        }
        if(!found) {
            System.out.print("House not found!");
            System.out.print("Back?(y,n)");
            c=input.next();
            if(c.toLowerCase().equals("y"))options();
        }
    }

}