package UserUtility;

import Entities.Timetable;

import java.util.ArrayList;

public class EditMaster {

    private static String secondName;
    private static String firstName;
    private static String patronymic;
    private static String phone;
    private static String mail;
    private static ArrayList<String> services;
    private static Timetable timetable;

    public static String getSecondName() {
        return secondName;
    }

    public static void setSecondName(String secondName) {
        EditMaster.secondName = secondName;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        EditMaster.firstName = firstName;
    }

    public static String getPatronymic() {
        return patronymic;
    }

    public static void setPatronymic(String patronymic) {
        EditMaster.patronymic = patronymic;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        EditMaster.phone = phone;
    }

    public static String getMail() {
        return mail;
    }

    public static void setMail(String mail) {
        EditMaster.mail = mail;
    }

    public static ArrayList<String> getServices() {
        return services;
    }

    public static void setServices(ArrayList<String> services) {
        EditMaster.services = services;
    }

    public static Timetable getTimetable() {
        return timetable;
    }

    public static void setTimetable(Timetable timetable) {
        EditMaster.timetable = timetable;
    }
}
