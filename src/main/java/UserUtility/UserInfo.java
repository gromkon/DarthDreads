package UserUtility;

public class UserInfo {

    private String secondName;
    private String firstName;
    private String patronymic;
    private String phone;
    private String mail;

    public UserInfo() {
        this.secondName = "";
        this.firstName = "";
        this.patronymic = "";
        this.phone = "";
        this.mail = "";
    }

    public UserInfo(String secondName, String firstName, String patronymic, String phone, String mail) {
        this.secondName = secondName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.mail = mail;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }
}
