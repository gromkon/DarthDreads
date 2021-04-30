package Entities;

import javafx.beans.property.SimpleStringProperty;

public class TimeData {

    private final SimpleStringProperty time = new SimpleStringProperty("");
    private final SimpleStringProperty monday = new SimpleStringProperty("");
    private final SimpleStringProperty tuesday = new SimpleStringProperty("");
    private final SimpleStringProperty wednesday = new SimpleStringProperty("");
    private final SimpleStringProperty thursday = new SimpleStringProperty("");
    private final SimpleStringProperty friday = new SimpleStringProperty("");
    private final SimpleStringProperty saturday = new SimpleStringProperty("");
    private final SimpleStringProperty sunday = new SimpleStringProperty("");

    public TimeData(String time) {
        this(time, "-", "-", "-", "-", "-", "-", "-");
    }

    public TimeData() {
        this("", "", "", "", "", "", "", "");
    }

    public TimeData(String time, String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday) {
        setTime(time);
        setMonday(monday);
        setTuesday(tuesday);
        setWednesday(wednesday);
        setThursday(thursday);
        setFriday(friday);
        setSaturday(saturday);
        setSunday(sunday);
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getMonday() {
        return monday.get();
    }

    public void setMonday(String time) {
        this.monday.set(time);
    }

    public String getTuesday() {
        return tuesday.get();
    }

    public void setTuesday(String time) {
        this.tuesday.set(time);
    }

    public String getWednesday() {
        return wednesday.get();
    }

    public void setWednesday(String time) {
        this.wednesday.set(time);
    }

    public String getThursday() {
        return thursday.get();
    }

    public void setThursday(String time) {
        this.thursday.set(time);
    }

    public String getFriday() {
        return friday.get();
    }

    public void setFriday(String time) {
        this.friday.set(time);
    }

    public String getSaturday() {
        return saturday.get();
    }

    public void setSaturday(String time) {
        this.saturday.set(time);
    }

    public String getSunday() {
        return sunday.get();
    }

    public void setSunday(String time) {
        this.sunday.set(time);
    }

    public void setText(int column, String text) {
        switch (column) {
            case 1:
                setMonday(text);
                break;
            case 2:
                setTuesday(text);
                break;
            case 3:
                setWednesday(text);
                break;
            case 4:
                setThursday(text);
                break;
            case 5:
                setFriday(text);
                break;
            case 6:
                setSaturday(text);
                break;
            case 7:
                setSunday(text);
                break;

        }
    }

    public String getText(int column) {
        switch (column) {
            case 1:
                return getMonday();
            case 2:
                return getTuesday();
            case 3:
                return getWednesday();
            case 4:
                return getThursday();
            case 5:
                return getFriday();
            case 6:
                return getSaturday();
            case 7:
                return getSunday();
        }
        return null;
    }

}
