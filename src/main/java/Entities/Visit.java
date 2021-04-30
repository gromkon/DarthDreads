package Entities;

public class Visit {
    private String clientID;
    private String masterID;
    private String serviceID;
    private String userFirstName;
    private String userSecondName;
    private String masterName;
    private String serviceName;
    private String date;
    private String time;
    private String duration;
    private String cost;

    public Visit(String clientID, String masterID, String serviceID, String date, String time) {
        this.clientID = clientID;
        this.masterID = masterID;
        this.serviceID = serviceID;
        this.date = date;
        this.time = time;
    }

    public Visit(String clientID, String masterID, String serviceName, String date, String time, String duration, String cost) {
        this.clientID = clientID;
        this.masterID = masterID;
        this.serviceName = serviceName;
        this.date = date;
        this.time = time.substring(0, 5);
        this.duration = duration.substring(0, 5);
        this.cost = cost;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getMasterID() {
        return masterID;
    }

    public void setMasterID(String masterID) {
        this.masterID = masterID;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserSecondName() {
        return userSecondName;
    }

    public void setUserSecondName(String userSecondName) {
        this.userSecondName = userSecondName;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return date.substring(8);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getCountCellForDuration() {
        int hours = Integer.parseInt(duration.substring(0, 2));
        int mins = Integer.parseInt(duration.substring(3));
        if (mins == 0) {
            return hours * 2;
        } else {
            return hours * 2 + 1;
        }
    }


    @Override
    public String toString() {
        return "Visit{" +
                "userID='" + clientID + '\'' +
                ", masterID='" + masterID + '\'' +
                ", serviceID='" + serviceID + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userSecondName='" + userSecondName + '\'' +
                ", masterName='" + masterName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", duration='" + duration + '\'' +
                '}' + "\n";
    }
}
