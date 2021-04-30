package Entities;

import javafx.beans.property.SimpleStringProperty;

public class Client {

    private final SimpleStringProperty secondName = new SimpleStringProperty("");
    private final SimpleStringProperty firstName = new SimpleStringProperty("");
    private final SimpleStringProperty phone = new SimpleStringProperty("");
    private final SimpleStringProperty mail = new SimpleStringProperty("");
    private final SimpleStringProperty lastVisit = new SimpleStringProperty("");

    public Client(String secondName, String firstName, String phone, String mail, String lastVisit) {
        setSecondName(secondName);
        setFirstName(firstName);
        setPhone(phone);
        setMail(mail);
        setLastVisit(lastVisit);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getMail() {
        return mail.get();
    }

    public void setMail(String mail) {
        this.mail.set(mail);
    }

    public String getLastVisit() {
        return lastVisit.get();
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit.set(lastVisit);
    }

}
