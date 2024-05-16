package hibernate.org.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class user {

    public enum Gender {
        Male, Female
    }

    @Id
    @Column(name = "ID")
    private int ID;

    @Column(name = "First_Name", length = 25, nullable = false)
    private String firstName;

    @Column(name = "Last_Name", length = 25, nullable = false)
    private String lastName;

    @Column(name = "Phone_Number", nullable = false)
    private int phoneNumber; // Changed data type to String

    @Column(name = "Address", length = 100, nullable = false)
    private String address;

    @Column(name = "Date_of_Birth", nullable = false)
    private java.sql.Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "Gender", nullable = false)
    private Gender gender; // Used enum Gender

    @Column(name = "Email", length = 75, nullable = false)
    private String email;

    @Column(name = "Password", length = 25, nullable = false)
    private String password;

    @Column(name = "Confirm_Password", length = 25, nullable = false)
    private String confirmPassword;

    @Column(name = "Profile", length = 255)
    private String profile;

    // No-argument constructor
    public user() {
    }

    // Constructor with all parameters
    public user(int ID, String firstName, String lastName, int phoneNumber2, String address, java.sql.Date dateOfBirth, Gender gender, String email, String password, String confirmPassword, String profile) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber2;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.profile = profile;
    }

    // Getters and Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public java.sql.Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(java.sql.Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
