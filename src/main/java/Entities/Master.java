
package Entities;

import javafx.beans.property.SimpleStringProperty;

public class Master {

    private final SimpleStringProperty firstName = new SimpleStringProperty("");
    private final SimpleStringProperty phone = new SimpleStringProperty("");
    private final SimpleStringProperty mail = new SimpleStringProperty("");
    private final SimpleStringProperty services = new SimpleStringProperty("");
    private final SimpleStringProperty timetable = new SimpleStringProperty("");

    public Master(String firstName, String phone, String mail, String services, String timetable) {
        setFirstName(firstName);
        setPhone(phone);
        setMail(mail);
        setServices(services);
        setTimetable(timetable);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
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

    public String getServices() {
        return services.get();
    }

    public void setServices(String services) {
        this.services.set(services);
    }

    public String getTimetable() {
        return timetable.get();
    }

    public void setTimetable(String timetable) {
        this.timetable.set(timetable);
    }


}
