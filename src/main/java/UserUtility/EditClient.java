package UserUtility;

public class EditClient {

    private static String secondName;
    private static String firstName;
    private static String patronymic;
    private static String phone;
    private static String mail;

    public static String getSecondName() {
        return secondName;
    }

    public static void setSecondName(String secondName) {
        EditClient.secondName = secondName;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        EditClient.firstName = firstName;
    }

    public static String getPatronymic() {
        return patronymic;
    }

    public static void setPatronymic(String patronymic) {
        EditClient.patronymic = patronymic;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        EditClient.phone = phone;
    }

    public static String getMail() {
        return mail;
    }

    public static void setMail(String mail) {
        EditClient.mail = mail;
    }
}
