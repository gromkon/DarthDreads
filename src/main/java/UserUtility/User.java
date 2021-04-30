package UserUtility;

public class User {

    private static UserType type = UserType.NO_USER;
    private static String login = null;

    public static UserType getType() {
        return type;
    }

    public static String getLogin() {
        return login;
    }

    public static void setType(UserType type) {
        User.type = type;
    }

    public static void setLogin(String login) {
        User.login = login;
    }

    public boolean isAdmin() {
        return type == UserType.ADMIN;
    }

    public boolean isClient() {
        return type == UserType.CLIENT;
    }

    public boolean isMaster() {
        return type == UserType.MASTER;
    }

    public boolean isAuth() {
        return type != UserType.NO_USER;
    }
}
