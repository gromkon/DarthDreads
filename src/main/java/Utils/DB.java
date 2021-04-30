package Utils;

import Entities.Client;
import Entities.Master;
import Entities.Timetable;
import Entities.Visit;
import UserUtility.User;
import UserUtility.UserInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class DB {
    private static Connection connection;
    private static Statement stmt;

    /**
     * Осуществляет подключение к БД
     */
    public static void connect() {
        /*
            serverName, serverPort, databaseName, user, password init here
         */
        try {
            connection = DriverManager.getConnection(connectionString);
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Осуществляет отключение от БД
     */
    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Авторизация
     */
    public static AuthStatus auth(String login, String password) {
        String sql = String.format("SELECT * FROM [user] WHERE login = '%s' and password = '%s'", login, password);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String type = rs.getString("userTypeID");
                int typeInt = Integer.parseInt(type);
                if (typeInt == 1) {
                    return AuthStatus.ADMIN;
                } else if (typeInt == 2) {
                    return AuthStatus.MASTER;
                } else if (typeInt == 3) {
                    return AuthStatus.CLIENT;
                }
            } else {
                return AuthStatus.NO_USER;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AuthStatus.NO_USER;
    }

    /**
     * Возвращает информацию о пользователе
     */
    public static UserInfo getInfo() {
        String sql = String.format("SELECT * FROM [user] WHERE login = '%s'", User.getLogin());
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String secondName = rs.getString("secondName");
                String firstName = rs.getString("firstName");
                String patronymic = rs.getString("patronymic");
                String phone = rs.getString("phone");
                String mail = rs.getString("mail");
                return new UserInfo(secondName, firstName, patronymic, phone, mail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new UserInfo("Фамилия", "Имя", "Отчество", "Телефон", "Почта");
    }

    /**
     * Обновляет информацию о пользователе
     */
    public static void updateInfo(String secondName, String firstName, String patronymic, String phone) {
        String sql = String.format("UPDATE [user]\n " +
                "SET secondName = '%s', " +
                "firstName = '%s', " +
                "patronymic = '%s', " +
                "phone = '%s' " +
                "WHERE login = '%s'",
                secondName, firstName, patronymic, phone, User.getLogin());
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверяет, есть ли в БД пользователь с данным логином
     */
    public static boolean checkLogin(String login) {
        String sql = String.format("SELECT * FROM [user] WHERE login = '%s'", login);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Регистрирует нового клиента
     */
    public static boolean addClient(String login, String password, String secondName, String firstName, String patronymic, String phone, String mail) {
        if (checkLogin(login)) {
            return false;
        }
        String sql = String.format("insert into [user]\n" +
                        "(login, password, firstName, secondName, patronymic, phone, mail, userTypeId)\n" +
                        "values\n" +
                        "('%s', '%s', '%s', '%s', '%s', '%s', '%s', 3)",
                login, password, firstName, secondName, patronymic, phone, mail);
        try {
            if (stmt.executeUpdate(sql) != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Регистрирует нового мастера
     */
    public static boolean addMaster(String login, String password, String secondName, String firstName, String patronymic, String phone, String mail,
                                    ArrayList<String> services, Timetable timetable) {
        if (checkLogin(login)) {
            return false;
        }
        try {

            String sqlAddMaster = String.format("insert into [user]\n" +
                            "(login, password, firstName, secondName, patronymic, phone, mail, userTypeId)\n" +
                            "values\n" +
                            "('%s', '%s', '%s', '%s', '%s', '%s', '%s', 2)",
                    login, password, firstName, secondName, patronymic, phone, mail);
            stmt.executeUpdate(sqlAddMaster);

            String sqlMasterID = String.format("select * from [user] where mail = '%s'", mail);
            ResultSet rsMasterID = stmt.executeQuery(sqlMasterID);
            rsMasterID.next();
            String masterID = rsMasterID.getString("userID");

            for (String s: services) {
                String sqlAddServices = String.format("insert into [user_service_int]\n" +
                        "(userID, serviceID)\n" +
                        "values\n" +
                        "(%s, %s)",
                        masterID, s);
                stmt.executeUpdate(sqlAddServices);
            }

            String sqlTimetable = String.format("insert into timetable  \n" +
                    "(userID, mondayStart, mondayEnd, tuesdayStart, tuesdayEnd, wednesdayStart, wednesdayEnd, thursdayStart, thursdayEnd, fridayStart, fridayEnd, saturdayStart, saturdayEnd, sundayStart, sundayEnd)\n" +
                    "values\n" +
                    "(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)",
                    masterID,
                    timetable.getMondayStart(), timetable.getMondayEnd(),
                    timetable.getTuesdayStart(), timetable.getTuesdayEnd(),
                    timetable.getWednesdayStart(), timetable.getWednesdayEnd(),
                    timetable.getThursdayStart(), timetable.getThursdayEnd(),
                    timetable.getFridayStart(), timetable.getFridayEnd(),
                    timetable.getSaturdayStart(), timetable.getSaturdayEnd(),
                    timetable.getSundayStart(), timetable.getSundayEnd());
            stmt.executeUpdate(sqlTimetable);
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Обновляет информацию о мастере
     */
    public static boolean updateMasterInfo(String secondName, String firstName, String patronymic, String phone, String mail,
                                        ArrayList<String> services, Timetable timetable) {
        try {
            String sqlUpdateUser = String.format("UPDATE [user]\n " +
                            "SET secondName = '%s', " +
                            "firstName = '%s', " +
                            "patronymic = '%s', " +
                            "phone = '%s' " +
                            "WHERE mail = '%s'",
                    secondName, firstName, patronymic, phone, mail);
            stmt.executeUpdate(sqlUpdateUser);

            String sqlMasterID = String.format("select * from [user] where mail = '%s'", mail);
            ResultSet rsMasterID = stmt.executeQuery(sqlMasterID);
            rsMasterID.next();
            String masterID = rsMasterID.getString("userID");

            String sqlDeleteUserVertices = String.format("delete from [user_service_int] where userID = '%s'", masterID);
            stmt.executeUpdate(sqlDeleteUserVertices);

            for (String s: services) {
                String sqlAddServices = String.format("insert into [user_service_int]\n" +
                                "(userID, serviceID)\n" +
                                "values\n" +
                                "(%s, %s)",
                        masterID, s);
                stmt.executeUpdate(sqlAddServices);
            }

            String sqlDeleteUserTimetable = String.format("delete from timetable where userID = '%s'", masterID);
            stmt.executeUpdate(sqlDeleteUserTimetable);

            String sqlTimetable = String.format("insert into timetable  \n" +
                            "(userID, mondayStart, mondayEnd, tuesdayStart, tuesdayEnd, wednesdayStart, wednesdayEnd, thursdayStart, thursdayEnd, fridayStart, fridayEnd, saturdayStart, saturdayEnd, sundayStart, sundayEnd)\n" +
                            "values\n" +
                            "(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)",
                    masterID,
                    timetable.getMondayStart(), timetable.getMondayEnd(),
                    timetable.getTuesdayStart(), timetable.getTuesdayEnd(),
                    timetable.getWednesdayStart(), timetable.getWednesdayEnd(),
                    timetable.getThursdayStart(), timetable.getThursdayEnd(),
                    timetable.getFridayStart(), timetable.getFridayEnd(),
                    timetable.getSaturdayStart(), timetable.getSaturdayEnd(),
                    timetable.getSundayStart(), timetable.getSundayEnd());
            stmt.executeUpdate(sqlTimetable);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Обновляет расписание мастера
     */
    public static boolean updateMasterTimetable(String mail, Timetable timetable) {
        try {
            String sqlMasterID = String.format("select * from [user] where mail = '%s'", mail);
            ResultSet rsMasterID = stmt.executeQuery(sqlMasterID);
            rsMasterID.next();
            String masterID = rsMasterID.getString("userID");

            String sqlDeleteUserTimetable = String.format("delete from timetable where userID = '%s'", masterID);
            stmt.executeUpdate(sqlDeleteUserTimetable);

            String sqlTimetable = String.format("insert into timetable  \n" +
                            "(userID, mondayStart, mondayEnd, tuesdayStart, tuesdayEnd, wednesdayStart, wednesdayEnd, thursdayStart, thursdayEnd, fridayStart, fridayEnd, saturdayStart, saturdayEnd, sundayStart, sundayEnd)\n" +
                            "values\n" +
                            "(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)",
                    masterID,
                    timetable.getMondayStart(), timetable.getMondayEnd(),
                    timetable.getTuesdayStart(), timetable.getTuesdayEnd(),
                    timetable.getWednesdayStart(), timetable.getWednesdayEnd(),
                    timetable.getThursdayStart(), timetable.getThursdayEnd(),
                    timetable.getFridayStart(), timetable.getFridayEnd(),
                    timetable.getSaturdayStart(), timetable.getSaturdayEnd(),
                    timetable.getSundayStart(), timetable.getSundayEnd());
            stmt.executeUpdate(sqlTimetable);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Возвращает список посещений клиента до даты
     */
    public static String getClientVisitsEarlier(String mail, String date) {
        StringBuilder sb = new StringBuilder();
        try {
            String sqlVisits = String.format("select * from visit_view where mail = '%s' AND date < '%s' order by date asc", mail, date);
            ResultSet rsVisits = stmt.executeQuery(sqlVisits);
            while (rsVisits.next()) {
                sb.append(rsVisits.getString("date")).append(" ").append(rsVisits.getString("serviceName")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (sb.length() == 0) {
            return "Нет посещений";
        } else {
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
    }

    /**
     * Возвращает список посещений клиента в данный день и дальше
     */
    public static String getClientVisitsLater(String mail, String date) {
        StringBuilder sb = new StringBuilder();
        try {
            String sqlVisits = String.format("select * from visit_view where mail = '%s' AND date >= '%s' order by date asc", mail, date);
            ResultSet rsVisits = stmt.executeQuery(sqlVisits);
            while (rsVisits.next()) {
                sb.append(rsVisits.getString("date")).append(" ").append(rsVisits.getString("serviceName")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (sb.length() == 0) {
            return "Нет посещений";
        } else {
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
    }


    /**
     * Удаляет мастера
     */
    public static void deleteMaster(String mail) {
        try {
            String sqlMasterID = String.format("select * from [user] where mail = '%s'", mail);
            ResultSet rsMasterID = stmt.executeQuery(sqlMasterID);
            rsMasterID.next();
            String masterID = rsMasterID.getString("userID");

            String sqlDeleteUserVertices = String.format("delete from [user_service_int] where userID = '%s'", masterID);
            stmt.executeUpdate(sqlDeleteUserVertices);

            String sqlDeleteUserTimetable = String.format("delete from timetable where userID = '%s'", masterID);
            stmt.executeUpdate(sqlDeleteUserTimetable);

            String sqlDeleteUser = String.format("delete from [user] where userID = '%s'", masterID);
            stmt.executeUpdate(sqlDeleteUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получает список клиентов для заполнения таблицы
     */
    public static ObservableList<Client> getClients() {
        ObservableList<Client> clients = FXCollections.observableArrayList();
        String sqlVisits = "select * from visit_view order by mail, date";
        try {
            ResultSet rs = stmt.executeQuery(sqlVisits);
            Client lastClient = new Client("", "", "", "", "");
            while (rs.next()) {
                String clientMail = rs.getString("mail");
                if (clientMail.equals(lastClient.getMail())) {
                    String lastVisit = rs.getString("date") + " " + rs.getString("serviceName");
                    lastClient.setLastVisit(lastVisit);

                } else {
                    clients.add(lastClient);
                    String secondName = rs.getString("secondName");
                    String firstName = rs.getString("firstName");
                    String phone = rs.getString("phone");
                    String lastVisit = rs.getString("date") + " " + rs.getString("serviceName");
                    lastClient = new Client(secondName, firstName, phone, clientMail, lastVisit);

                }
            }
            clients.add(lastClient);
            clients.remove(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sqlNoVisits = "select * from no_visit_view";
        try {
            ResultSet rs = stmt.executeQuery(sqlNoVisits);
            while (rs.next()) {
                String secondName = rs.getString("secondName");
                String firstName = rs.getString("firstName");
                String phone = rs.getString("phone");
                String mail = rs.getString("mail");
                String lastVisit = "Нет посещений";
                clients.add(new Client(secondName, firstName, phone, mail, lastVisit));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    /**
     * Возвращает фамилию по почте
     */
    public static String getPatronymicByMail(String mail) {
        String sql = String.format("SELECT * FROM [user] WHERE mail = '%s'", mail);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("patronymic");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Обновляет информацию о клиенте
     */
    public static void updateClientInfo(String secondName, String firstName, String patronymic, String phone, String mail) {
        String sql = String.format("UPDATE [user]\n " +
                        "SET secondName = '%s', " +
                        "firstName = '%s', " +
                        "patronymic = '%s', " +
                        "phone = '%s' " +
                        "WHERE mail = '%s'",
                secondName, firstName, patronymic, phone, mail);
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получает список мастеров для заполнения таблицы
     */
    public static ObservableList<Master> getMasters() {
        ObservableList<Master> masters = FXCollections.observableArrayList();

        String sqlMasters = "SELECT * FROM [user] where userTypeID = 2";
        try {
            ResultSet rsMasters = stmt.executeQuery(sqlMasters);
            while (rsMasters.next()) {
                String firstName = rsMasters.getString("firstName");
                String phone = rsMasters.getString("phone");
                String mail = rsMasters.getString("mail");
                masters.add(new Master(firstName, phone, mail, "", ""));
            }
            for (Master m: masters) {
                StringBuilder sbServices = new StringBuilder();
                String sqlServices = String.format("select * from service \n" +
                        "\twhere serviceID IN (\n" +
                        "\t\tselect serviceID from user_service_int \n" +
                        "\t\t\twhere userID = (\n" +
                        "\t\t\t\tselect userID from [user] \n" +
                        "\t\t\t\t\twhere mail = '%s'\n" +
                        "\t\t\t)\n" +
                        "\t)",
                        m.getMail());
                ResultSet rsServices = stmt.executeQuery(sqlServices);
                while (rsServices.next()) {
                    sbServices.append(rsServices.getString("serviceName"));
                    sbServices.append("\n");
                }
                sbServices.deleteCharAt(sbServices.length() - 1);
                m.setServices(sbServices.toString());

                StringBuilder sbTimetable = new StringBuilder();
                String sqlTimetable = String.format("select * from user_timetable_view where mail = '%s'", m.getMail());
                ResultSet rsTimetable = stmt.executeQuery(sqlTimetable);
                rsTimetable.next();
                String mondayStart = rsTimetable.getString("mondayStart");
                String mondayEnd = rsTimetable.getString("mondayEnd");
                if (mondayStart != null) {
                    mondayStart = mondayStart.substring(0, 5);
                    mondayEnd = mondayEnd.substring(0, 5);
                    sbTimetable.append("Пн: ").append(mondayStart).append("-").append(mondayEnd);
                    sbTimetable.append("\n");
                }
                String tuesdayStart = rsTimetable.getString("tuesdayStart");
                String tuesdayEnd = rsTimetable.getString("tuesdayEnd");
                if (tuesdayStart != null) {
                    tuesdayStart = tuesdayStart.substring(0, 5);
                    tuesdayEnd = tuesdayEnd.substring(0, 5);
                    sbTimetable.append("Вт: ").append(tuesdayStart).append("-").append(tuesdayEnd);
                    sbTimetable.append("\n");
                }
                String wednesdayStart = rsTimetable.getString("wednesdayStart");
                String wednesdayEnd = rsTimetable.getString("wednesdayEnd");
                if (wednesdayStart != null) {
                    wednesdayStart = wednesdayStart.substring(0, 5);
                    wednesdayEnd = wednesdayEnd.substring(0, 5);
                    sbTimetable.append("Ср: ").append(wednesdayStart).append("-").append(wednesdayEnd);
                    sbTimetable.append("\n");
                }
                String thursdayStart = rsTimetable.getString("thursdayStart");
                String thursdayEnd = rsTimetable.getString("thursdayEnd");
                if (thursdayStart != null) {
                    thursdayStart = thursdayStart.substring(0, 5);
                    thursdayEnd = thursdayEnd.substring(0, 5);
                    sbTimetable.append("Чт: ").append(thursdayStart).append("-").append(thursdayEnd);
                    sbTimetable.append("\n");
                }
                String fridayStart = rsTimetable.getString("fridayStart");
                String fridayEnd = rsTimetable.getString("fridayEnd");
                if (fridayStart != null) {
                    fridayStart = fridayStart.substring(0, 5);
                    fridayEnd = fridayEnd.substring(0, 5);
                    sbTimetable.append("Пт: ").append(fridayStart).append("-").append(fridayEnd);
                    sbTimetable.append("\n");
                }
                String saturdayStart = rsTimetable.getString("saturdayStart");
                String saturdayEnd = rsTimetable.getString("saturdayEnd");
                if (saturdayStart != null) {
                    saturdayStart = saturdayStart.substring(0, 5);
                    saturdayEnd = saturdayEnd.substring(0, 5);
                    sbTimetable.append("Сб: ").append(saturdayStart).append("-").append(saturdayEnd);
                    sbTimetable.append("\n");
                }
                String sundayStart = rsTimetable.getString("sundayStart");
                String sundayEnd = rsTimetable.getString("sundayEnd");
                if (sundayStart != null) {
                    sundayStart = sundayStart.substring(0, 5);
                    sundayEnd = sundayEnd.substring(0, 5);
                    sbTimetable.append("Вс: ").append(sundayStart).append("-").append(sundayEnd);
                    sbTimetable.append("\n");
                }
                sbTimetable.deleteCharAt(sbTimetable.length() - 1);
                m.setTimetable(sbTimetable.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masters;
    }

    /**
     * Возвращает расписание мастера записанное в строку
     */
    public static String getMasterTimetable(String mail) {
        String t = null;
        try {
            StringBuilder sbTimetable = new StringBuilder();
            String sqlTimetable = String.format("select * from user_timetable_view where mail = '%s'", mail);
            ResultSet rsTimetable = stmt.executeQuery(sqlTimetable);
            rsTimetable.next();
            String mondayStart = rsTimetable.getString("mondayStart");
            String mondayEnd = rsTimetable.getString("mondayEnd");
            if (mondayStart != null) {
                mondayStart = mondayStart.substring(0, 5);
                mondayEnd = mondayEnd.substring(0, 5);
                sbTimetable.append("Пн: ").append(mondayStart).append("-").append(mondayEnd);
                sbTimetable.append("\n");
            }
            String tuesdayStart = rsTimetable.getString("tuesdayStart");
            String tuesdayEnd = rsTimetable.getString("tuesdayEnd");
            if (tuesdayStart != null) {
                tuesdayStart = tuesdayStart.substring(0, 5);
                tuesdayEnd = tuesdayEnd.substring(0, 5);
                sbTimetable.append("Вт: ").append(tuesdayStart).append("-").append(tuesdayEnd);
                sbTimetable.append("\n");
            }
            String wednesdayStart = rsTimetable.getString("wednesdayStart");
            String wednesdayEnd = rsTimetable.getString("wednesdayEnd");
            if (wednesdayStart != null) {
                wednesdayStart = wednesdayStart.substring(0, 5);
                wednesdayEnd = wednesdayEnd.substring(0, 5);
                sbTimetable.append("Ср: ").append(wednesdayStart).append("-").append(wednesdayEnd);
                sbTimetable.append("\n");
            }
            String thursdayStart = rsTimetable.getString("thursdayStart");
            String thursdayEnd = rsTimetable.getString("thursdayEnd");
            if (thursdayStart != null) {
                thursdayStart = thursdayStart.substring(0, 5);
                thursdayEnd = thursdayEnd.substring(0, 5);
                sbTimetable.append("Чт: ").append(thursdayStart).append("-").append(thursdayEnd);
                sbTimetable.append("\n");
            }
            String fridayStart = rsTimetable.getString("fridayStart");
            String fridayEnd = rsTimetable.getString("fridayEnd");
            if (fridayStart != null) {
                fridayStart = fridayStart.substring(0, 5);
                fridayEnd = fridayEnd.substring(0, 5);
                sbTimetable.append("Пт: ").append(fridayStart).append("-").append(fridayEnd);
                sbTimetable.append("\n");
            }
            String saturdayStart = rsTimetable.getString("saturdayStart");
            String saturdayEnd = rsTimetable.getString("saturdayEnd");
            if (saturdayStart != null) {
                saturdayStart = saturdayStart.substring(0, 5);
                saturdayEnd = saturdayEnd.substring(0, 5);
                sbTimetable.append("Сб: ").append(saturdayStart).append("-").append(saturdayEnd);
                sbTimetable.append("\n");
            }
            String sundayStart = rsTimetable.getString("sundayStart");
            String sundayEnd = rsTimetable.getString("sundayEnd");
            if (sundayStart != null) {
                sundayStart = sundayStart.substring(0, 5);
                sundayEnd = sundayEnd.substring(0, 5);
                sbTimetable.append("Вс: ").append(sundayStart).append("-").append(sundayEnd);
                sbTimetable.append("\n");
            }
            sbTimetable.deleteCharAt(sbTimetable.length() - 1);
            t = sbTimetable.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * Возвращает название услуги по ID услуги
     */
    public static String getServiceIdByName(String name) {
        String sql = String.format("SELECT * FROM [service] WHERE serviceName = '%s'", name);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("serviceID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Возвращает название услуги по ID услуги
     */
    public static String getSecondNameByMail(String mail) {
        String sql = String.format("SELECT * FROM [user] WHERE mail = '%s'", mail);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("secondName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getDateString(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);
        String dateString;
        if (month < 10) {
            if (date < 10) {
                dateString = year + "-0" + month + "-0" + date;
            } else {
                dateString = year + "-0" + month + "-" + date;
            }
        } else {
            if (date < 10) {
                dateString = year + "-" + month + "-0" + date;
            } else {
                dateString = year + "-" + month + "-" + date;
            }
        }
        return dateString;
    }

    /**
     * Возвращает список визитов на 7 дней, начиная с (сегодняшний день + dateFromToday) дня
     */
    public static ArrayList<Visit> getVisitsByDateFromTodayForSevenDays(int dateFromToday) {
        ArrayList<Visit> visits = new ArrayList<>();
        try {

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, dateFromToday);
            String dateStartString = getDateString(calendar);
            calendar.add(Calendar.DATE, 7);
            String dateEndString = getDateString(calendar);
            String sqlVisits = String.format("select * from visit where date >= '%s' and date < '%s'", dateStartString, dateEndString);
            ResultSet rsVisits = stmt.executeQuery(sqlVisits);
            while (rsVisits.next()) {
                visits.add(new Visit(
                        rsVisits.getString("clientID"),
                        rsVisits.getString("masterID"),
                        rsVisits.getString("serviceID"),
                        rsVisits.getString("date"),
                        rsVisits.getString("time").substring(0, 5)
                ));

            }

            for (Visit v: visits) {
                String sqlUserInfo = String.format("select * from [user] where userID = '%s'", v.getClientID());
                ResultSet rsUserInfo = stmt.executeQuery(sqlUserInfo);
                rsUserInfo.next();
                v.setUserFirstName(rsUserInfo.getString("firstName"));
                v.setUserSecondName(rsUserInfo.getString("secondName"));
            }

            for (Visit v: visits) {
                String sqlMasterInfo = String.format("select * from [user] where userID = '%s'", v.getMasterID());
                ResultSet rsMasterInfo = stmt.executeQuery(sqlMasterInfo);
                rsMasterInfo.next();
                v.setMasterName(rsMasterInfo.getString("firstName"));
            }

            for (Visit v: visits) {
                String sqlServiceInfo = String.format("select * from [service] where serviceID = '%s'", v.getServiceID());
                ResultSet rsServiceInfo = stmt.executeQuery(sqlServiceInfo);
                rsServiceInfo.next();
                v.setServiceName(rsServiceInfo.getString("serviceName"));
                v.setDuration(rsServiceInfo.getString("duration").substring(0, 5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visits;
    }

    /**
     * Меняет пароль у пользователя
     */
    public static void changePassword(String password, String mail) {
        String sql = String.format("UPDATE [user] SET password = '%s' WHERE mail = '%s'", password, mail);
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Возвращает список визитов которые будут в заданный день и время
     */
    public static ArrayList<Visit> getVisitsByDateAndTime(String day, String month, String year, String time) {
        ArrayList<Visit> visits = new ArrayList<>();
        String timePlusOneMin = time.substring(0, 4) + "1";
        String sqlVisits = String.format("select * from visit_with_end_view \n" +
                "\twhere date = '%s-%s-%s' and \n" +
                "\ttime <= '%s' and \n" +
                "\tCONVERT(\n" +
                "\t\t\t\tvarchar(8), \n" +
                "\t\t\t\tDATEADD(n, DATEPART(n, duration), DATEADD(hh, DATEPART(HH, duration), time)), \n" +
                "\t\t\t\t108\n" +
                "\t\t\t) > '%s'",
                year, month, day, time, timePlusOneMin);
        try {
            ResultSet rsVisits = stmt.executeQuery(sqlVisits);
            while (rsVisits.next()) {
                visits.add(new Visit(
                            rsVisits.getString("clientID"),
                            rsVisits.getString("masterID"),
                            rsVisits.getString("serviceName"),
                            rsVisits.getString("date"),
                            rsVisits.getString("time"),
                            rsVisits.getString("duration"),
                            rsVisits.getString("cost")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visits;
    }

    /**
     * Возвращает имя и фамилию клиента по ID
     */
    public static String getFirstAndSecondNameById(String id) {
        String info = null;
        String sql = String.format("select * from [user] where userID = '%s'", id);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                info = rs.getString("secondName") + " " + rs.getString("firstName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * Возвращает фамилию клиента по ID
     */
    public static String getSecondNameById(String id) {
        String info = null;
        String sql = String.format("select * from [user] where userID = '%s'", id);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                info = rs.getString("secondName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * Возвращает цену работы по названию
     */
    public static String getCostServiceByName(String name) {
        String cost = null;
        String sql = String.format("select * from service where serviceName = '%s'", name);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                cost = rs.getString("cost");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cost;
    }


    /**
     * Возвращает список мастеров работающих в данное время, причем так, чтобы они могли успеть выполнить выбранную услугу
     */
    public static ObservableList<String> getFreeMasterAtTimeAndFinishServiceBeforeEndWorkingDay(String dayName, String time, String serviceID) {
        ObservableList<String> masters = FXCollections.observableArrayList();
        try {
            ArrayList<String> mastersIDS = new ArrayList<>();
            String sqlMastersWhoCanDoService = String.format("select * from [user_service_int] where serviceID = %s", serviceID);
            ResultSet rsMastersWhoCanDoService = stmt.executeQuery(sqlMastersWhoCanDoService);
            while (rsMastersWhoCanDoService.next()) {
                mastersIDS.add(rsMastersWhoCanDoService.getString("userID"));
            }
            if (mastersIDS.size() == 0) {
                return masters;
            }

            String sqlServiceDuration = String.format("select * from service where serviceId = %s", serviceID);
            ResultSet rsServiceDuration = stmt.executeQuery(sqlServiceDuration);
            rsServiceDuration.next();
            String duration = rsServiceDuration.getString("duration").substring(0, 5);

            for (String masterID: mastersIDS) {
                String sqlMasterTimeStart = String.format("select * from timetable where userID = %s", masterID);
                ResultSet rsMasterTimeStart = stmt.executeQuery(sqlMasterTimeStart);
                rsMasterTimeStart.next();
                String timeStart = rsMasterTimeStart.getString(dayName + "Start");
                if (timeStart == null) {
                    continue;
                }
                timeStart = timeStart.substring(0, 5);
                // складываем 2 времени
                int mins = Integer.parseInt(timeStart.substring(3)) + Integer.parseInt(duration.substring(3));
                int hours = Integer.parseInt(timeStart.substring(0, 2)) + Integer.parseInt(duration.substring(0, 2));
                if (mins >= 60) {
                    mins -= 60;
                    hours += 1;
                }
                String timeEnd = hours + ":" + mins;
                String sqlIsMaster = String.format("select * from timetable where userID = %s AND %sStart <= '%s' AND %sEnd >= '%s'", masterID, dayName, time, dayName, timeEnd);
                ResultSet rsIsMaster = stmt.executeQuery(sqlIsMaster);
                if (rsIsMaster.next()) {
                    masters.add(getFirstAndSecondNameById(masterID));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masters;
    }

    /**
     * Возвращает список всех услуг
     */
    public static ObservableList<String> getServicesNames() {
        ObservableList<String> servicesNames = FXCollections.observableArrayList();
        String sql = "select * from service";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                servicesNames.add(rs.getString("serviceName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicesNames;
    }


    /**
     * Возвращает список клиентов с данной фамилией
     */
    public static ObservableList<String> getUsersBySecondName(String secondName) {
        ObservableList<String> userNames = FXCollections.observableArrayList();
        try {
            String sql = String.format("select * from [user] where secondName = '%s' and userTypeID = 3", secondName);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                userNames.add(rs.getString("secondName") + " " + rs.getString("firstName") + " " + rs.getString("patronymic"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userNames;
    }
}
