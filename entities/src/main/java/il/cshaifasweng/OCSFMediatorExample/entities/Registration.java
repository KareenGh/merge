package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;


@Entity
@Table(name = "Registration")

public class Registration {
    private static final long serialVersionUID = 4412085390748840478L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String FirstName;
    private String LastName;
    private String ID;
    private String Email;
    private String PhoneNumber;
    private String UserName;
    private String Password;
    private String Status;
    private String CreditCard;
    private String ExpiryDate;
    private String AccountType;

    public Registration(String firstName, String lastName, String ID, String email, String phoneNumber, String userName, String password, String status, String creditCard, String expiryDate, String accountType) {
        FirstName = firstName;
        LastName = lastName;
        this.ID = ID;
        Email = email;
        PhoneNumber = phoneNumber;
        UserName = userName;
        Password = password;
        Status = status;
        CreditCard = creditCard;
        ExpiryDate = expiryDate;
        AccountType = accountType;
    }

    public Registration() {

    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCreditCard() {
        return CreditCard;
    }

    public void setCreditCard(String creditCard) {
        CreditCard = creditCard;
    }

    public String getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        ExpiryDate = expiryDate;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }
}
