package co.edu.icesi.joancaliz.paycool_prototype;

/*Esta clase es usada para guardar la información de cada usuario en
* instancias (objetos) para luego guardarlos en la base de datos de Firebase*/

import java.util.ArrayList;

public class User {
    private String userID, name, surname, dni, phoneNumber, email, password;
    private int money, paycoolPoints;

    private ArrayList<Challengue> challengues;
    private Challengue weeklyChallengue;

    // Hasta donde recuerdo, las clases que usaran con Firebase requieren un constructor vacío para funcionar correctamente. No se por qué.
    public User() {

    }

    // Este otro constructor es en donde se inicializa la instancia de User.
    public User(String userID, String name, String surname, String dni, String phoneNumber, String email, String password, int money, int paycoolPoints) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.paycoolPoints=paycoolPoints;
        this.money=money;

        challengues = new ArrayList<>();
    }

    // Getters y Setters
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPaycoolPoints() {
        return paycoolPoints;
    }

    public void setPaycoolPoints(int paycoolPoints) {
        this.paycoolPoints = paycoolPoints;
    }
}
