import java.io.*;

public class User implements Serializable {
    protected String firstName;
    protected String lastName;
    protected int Id;
    protected String password;
    protected String address;
    protected String email;
    protected int cellphone;
    protected String username;
    protected String type;
    User(){};

    public User(String firstName,int id,String lastName,String address,String username,String password,String email,int cellPhone,String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username=username;
        this.Id = id;
        this.password = password;
        this.address = address;
        this.email = email;
        this.cellphone = cellPhone;
        this.type = type;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCellphone() {
        return cellphone;
    }

    public void setCellphone(int cellphone) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

