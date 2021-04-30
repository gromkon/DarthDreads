package Entities;

public class Timetable {
    private String mondayStart;
    private String mondayEnd;
    private String tuesdayStart;
    private String tuesdayEnd;
    private String wednesdayStart;
    private String wednesdayEnd;
    private String thursdayStart;
    private String thursdayEnd;
    private String fridayStart;
    private String fridayEnd;
    private String saturdayStart;
    private String saturdayEnd;
    private String sundayStart;
    private String sundayEnd;


    private String mondayStartH;
    private String mondayStartM;
    private String mondayEndH;
    private String mondayEndM;

    private String tuesdayStartH;
    private String tuesdayStartM;
    private String tuesdayEndH;
    private String tuesdayEndM;

    private String wednesdayStartH;
    private String wednesdayStartM;
    private String wednesdayEndH;
    private String wednesdayEndM;

    private String thursdayStartH;
    private String thursdayStartM;
    private String thursdayEndH;
    private String thursdayEndM;

    private String fridayStartH;
    private String fridayStartM;
    private String fridayEndH;
    private String fridayEndM;

    private String saturdayStartH;
    private String saturdayStartM;
    private String saturdayEndH;
    private String saturdayEndM;

    private String sundayStartH;
    private String sundayStartM;
    private String sundayEndH;
    private String sundayEndM;

    public Timetable() {
        mondayStart = null;
        mondayEnd = null;

        tuesdayStart = null;
        tuesdayEnd = null;

        wednesdayStart = null;
        wednesdayEnd = null;

        thursdayStart = null;
        thursdayEnd = null;

        fridayStart = null;
        fridayEnd = null;

        saturdayStart = null;
        saturdayEnd = null;

        sundayStart = null;
        sundayEnd = null;

        mondayStartH = null;
        mondayStartM = null;
        mondayEndH = null;
        mondayEndM = null;
        tuesdayStartH = null;
        tuesdayStartM = null;
        tuesdayEndH = null;
        tuesdayEndM = null;
        wednesdayStartH = null;
        wednesdayStartM = null;
        wednesdayEndH = null;
        wednesdayEndM = null;
        thursdayStartH = null;
        thursdayStartM = null;
        thursdayEndH = null;
        thursdayEndM = null;
        fridayStartH = null;
        fridayStartM = null;
        fridayEndH = null;
        fridayEndM = null;
        saturdayStartH = null;
        saturdayStartM = null;
        saturdayEndH = null;
        saturdayEndM = null;
        sundayStartH = null;
        sundayStartM = null;
        sundayEndH = null;
        sundayEndM = null;
    }

    public Timetable(String mondayStartH, String mondayStartM, String mondayEndH, String mondayEndM,
                     String tuesdayStartH, String tuesdayStartM, String tuesdayEndH, String tuesdayEndM,
                     String wednesdayStartH, String wednesdayStartM, String wednesdayEndH, String wednesdayEndM,
                     String thursdayStartH, String thursdayStartM, String thursdayEndH, String thursdayEndM,
                     String fridayStartH, String fridayStartM, String fridayEndH, String fridayEndM,
                     String saturdayStartH, String saturdayStartM, String saturdayEndH, String saturdayEndM,
                     String sundayStartH, String sundayStartM, String sundayEndH, String sundayEndM) {
        if (mondayStartH.length() > 0) {
            mondayStart = String.format("'%s:%s:00'", mondayStartH, mondayStartM);
            mondayEnd = String.format("'%s:%s:00'", mondayEndH, mondayEndM);
        } else {
            mondayStart = null;
            mondayEnd = null;
        }

        if (tuesdayStartH.length() > 0) {
            tuesdayStart = String.format("'%s:%s:00'", tuesdayStartH, tuesdayStartM);
            tuesdayEnd = String.format("'%s:%s:00'", tuesdayEndH, tuesdayEndM);
        } else {
            tuesdayStart = null;
            tuesdayEnd = null;
        }

        if (wednesdayStartH.length() > 0) {
            wednesdayStart = String.format("'%s:%s:00'", wednesdayStartH, wednesdayStartM);
            wednesdayEnd = String.format("'%s:%s:00'", wednesdayEndH, wednesdayEndM);
        } else {
            wednesdayStart = null;
            wednesdayEnd = null;
        }

        if (thursdayStartH.length() > 0) {
            thursdayStart = String.format("'%s:%s:00'", thursdayStartH, thursdayStartM);
            thursdayEnd = String.format("'%s:%s:00'", thursdayEndH, thursdayEndM);
        } else {
            thursdayStart = null;
            thursdayEnd = null;
        }

        if (fridayStartH.length() > 0) {
            fridayStart = String.format("'%s:%s:00'", fridayStartH, fridayStartM);
            fridayEnd = String.format("'%s:%s:00'", fridayEndH, fridayEndM);
        } else {
            fridayStart = null;
            fridayEnd = null;
        }

        if (saturdayStartH.length() > 0) {
            saturdayStart = String.format("'%s:%s:00'", saturdayStartH, saturdayStartM);
            saturdayEnd = String.format("'%s:%s:00'", saturdayEndH, saturdayEndM);
        } else {
            saturdayStart = null;
            saturdayEnd = null;
        }

        if (sundayStartH.length() > 0) {
            sundayStart = String.format("'%s:%s:00'", sundayStartH, sundayStartM);
            sundayEnd = String.format("'%s:%s:00'", sundayEndH, sundayEndM);
        } else {
            sundayStart = null;
            sundayEnd = null;
        }

        this.mondayStartH = mondayStartH;
        this.mondayStartM = mondayStartM;
        this.mondayEndH = mondayEndH;
        this.mondayEndM = mondayEndM;
        this.tuesdayStartH = tuesdayStartH;
        this.tuesdayStartM = tuesdayStartM;
        this.tuesdayEndH = tuesdayEndH;
        this.tuesdayEndM = tuesdayEndM;
        this.wednesdayStartH = wednesdayStartH;
        this.wednesdayStartM = wednesdayStartM;
        this.wednesdayEndH = wednesdayEndH;
        this.wednesdayEndM = wednesdayEndM;
        this.thursdayStartH = thursdayStartH;
        this.thursdayStartM = thursdayStartM;
        this.thursdayEndH = thursdayEndH;
        this.thursdayEndM = thursdayEndM;
        this.fridayStartH = fridayStartH;
        this.fridayStartM = fridayStartM;
        this.fridayEndH = fridayEndH;
        this.fridayEndM = fridayEndM;
        this.saturdayStartH = saturdayStartH;
        this.saturdayStartM = saturdayStartM;
        this.saturdayEndH = saturdayEndH;
        this.saturdayEndM = saturdayEndM;
        this.sundayStartH = sundayStartH;
        this.sundayStartM = sundayStartM;
        this.sundayEndH = sundayEndH;
        this.sundayEndM = sundayEndM;
    }


    public String getMondayStart() {
        return mondayStart;
    }

    public String getMondayEnd() {
        return mondayEnd;
    }

    public String getTuesdayStart() {
        return tuesdayStart;
    }

    public String getTuesdayEnd() {
        return tuesdayEnd;
    }

    public String getWednesdayStart() {
        return wednesdayStart;
    }

    public String getWednesdayEnd() {
        return wednesdayEnd;
    }

    public String getThursdayStart() {
        return thursdayStart;
    }

    public String getThursdayEnd() {
        return thursdayEnd;
    }

    public String getFridayStart() {
        return fridayStart;
    }

    public String getFridayEnd() {
        return fridayEnd;
    }

    public String getSaturdayStart() {
        return saturdayStart;
    }

    public String getSaturdayEnd() {
        return saturdayEnd;
    }

    public String getSundayStart() {
        return sundayStart;
    }

    public String getSundayEnd() {
        return sundayEnd;
    }

    public void setMondayStart(String mondayStart) {
        this.mondayStart = mondayStart;
    }

    public void setMondayEnd(String mondayEnd) {
        this.mondayEnd = mondayEnd;
    }

    public void setTuesdayStart(String tuesdayStart) {
        this.tuesdayStart = tuesdayStart;
    }

    public void setTuesdayEnd(String tuesdayEnd) {
        this.tuesdayEnd = tuesdayEnd;
    }

    public void setWednesdayStart(String wednesdayStart) {
        this.wednesdayStart = wednesdayStart;
    }

    public void setWednesdayEnd(String wednesdayEnd) {
        this.wednesdayEnd = wednesdayEnd;
    }

    public void setThursdayStart(String thursdayStart) {
        this.thursdayStart = thursdayStart;
    }

    public void setThursdayEnd(String thursdayEnd) {
        this.thursdayEnd = thursdayEnd;
    }

    public void setFridayStart(String fridayStart) {
        this.fridayStart = fridayStart;
    }

    public void setFridayEnd(String fridayEnd) {
        this.fridayEnd = fridayEnd;
    }

    public void setSaturdayStart(String saturdayStart) {
        this.saturdayStart = saturdayStart;
    }

    public void setSaturdayEnd(String saturdayEnd) {
        this.saturdayEnd = saturdayEnd;
    }

    public void setSundayStart(String sundayStart) {
        this.sundayStart = sundayStart;
    }

    public void setSundayEnd(String sundayEnd) {
        this.sundayEnd = sundayEnd;
    }

    public String getMondayStartH() {
        return mondayStartH;
    }

    public void setMondayStartH(String mondayStartH) {
        this.mondayStartH = mondayStartH;
    }

    public String getMondayStartM() {
        return mondayStartM;
    }

    public void setMondayStartM(String mondayStartM) {
        this.mondayStartM = mondayStartM;
    }

    public String getMondayEndH() {
        return mondayEndH;
    }

    public void setMondayEndH(String mondayEndH) {
        this.mondayEndH = mondayEndH;
    }

    public String getMondayEndM() {
        return mondayEndM;
    }

    public void setMondayEndM(String mondayEndM) {
        this.mondayEndM = mondayEndM;
    }

    public String getTuesdayStartH() {
        return tuesdayStartH;
    }

    public void setTuesdayStartH(String tuesdayStartH) {
        this.tuesdayStartH = tuesdayStartH;
    }

    public String getTuesdayStartM() {
        return tuesdayStartM;
    }

    public void setTuesdayStartM(String tuesdayStartM) {
        this.tuesdayStartM = tuesdayStartM;
    }

    public String getTuesdayEndH() {
        return tuesdayEndH;
    }

    public void setTuesdayEndH(String tuesdayEndH) {
        this.tuesdayEndH = tuesdayEndH;
    }

    public String getTuesdayEndM() {
        return tuesdayEndM;
    }

    public void setTuesdayEndM(String tuesdayEndM) {
        this.tuesdayEndM = tuesdayEndM;
    }

    public String getWednesdayStartH() {
        return wednesdayStartH;
    }

    public void setWednesdayStartH(String wednesdayStartH) {
        this.wednesdayStartH = wednesdayStartH;
    }

    public String getWednesdayStartM() {
        return wednesdayStartM;
    }

    public void setWednesdayStartM(String wednesdayStartM) {
        this.wednesdayStartM = wednesdayStartM;
    }

    public String getWednesdayEndH() {
        return wednesdayEndH;
    }

    public void setWednesdayEndH(String wednesdayEndH) {
        this.wednesdayEndH = wednesdayEndH;
    }

    public String getWednesdayEndM() {
        return wednesdayEndM;
    }

    public void setWednesdayEndM(String wednesdayEndM) {
        this.wednesdayEndM = wednesdayEndM;
    }

    public String getThursdayStartH() {
        return thursdayStartH;
    }

    public void setThursdayStartH(String thursdayStartH) {
        this.thursdayStartH = thursdayStartH;
    }

    public String getThursdayStartM() {
        return thursdayStartM;
    }

    public void setThursdayStartM(String thursdayStartM) {
        this.thursdayStartM = thursdayStartM;
    }

    public String getThursdayEndH() {
        return thursdayEndH;
    }

    public void setThursdayEndH(String thursdayEndH) {
        this.thursdayEndH = thursdayEndH;
    }

    public String getThursdayEndM() {
        return thursdayEndM;
    }

    public void setThursdayEndM(String thursdayEndM) {
        this.thursdayEndM = thursdayEndM;
    }

    public String getFridayStartH() {
        return fridayStartH;
    }

    public void setFridayStartH(String fridayStartH) {
        this.fridayStartH = fridayStartH;
    }

    public String getFridayStartM() {
        return fridayStartM;
    }

    public void setFridayStartM(String fridayStartM) {
        this.fridayStartM = fridayStartM;
    }

    public String getFridayEndH() {
        return fridayEndH;
    }

    public void setFridayEndH(String fridayEndH) {
        this.fridayEndH = fridayEndH;
    }

    public String getFridayEndM() {
        return fridayEndM;
    }

    public void setFridayEndM(String fridayEndM) {
        this.fridayEndM = fridayEndM;
    }

    public String getSaturdayStartH() {
        return saturdayStartH;
    }

    public void setSaturdayStartH(String saturdayStartH) {
        this.saturdayStartH = saturdayStartH;
    }

    public String getSaturdayStartM() {
        return saturdayStartM;
    }

    public void setSaturdayStartM(String saturdayStartM) {
        this.saturdayStartM = saturdayStartM;
    }

    public String getSaturdayEndH() {
        return saturdayEndH;
    }

    public void setSaturdayEndH(String saturdayEndH) {
        this.saturdayEndH = saturdayEndH;
    }

    public String getSaturdayEndM() {
        return saturdayEndM;
    }

    public void setSaturdayEndM(String saturdayEndM) {
        this.saturdayEndM = saturdayEndM;
    }

    public String getSundayStartH() {
        return sundayStartH;
    }

    public void setSundayStartH(String sundayStartH) {
        this.sundayStartH = sundayStartH;
    }

    public String getSundayStartM() {
        return sundayStartM;
    }

    public void setSundayStartM(String sundayStartM) {
        this.sundayStartM = sundayStartM;
    }

    public String getSundayEndH() {
        return sundayEndH;
    }

    public void setSundayEndH(String sundayEndH) {
        this.sundayEndH = sundayEndH;
    }

    public String getSundayEndM() {
        return sundayEndM;
    }

    public void setSundayEndM(String sundayEndM) {
        this.sundayEndM = sundayEndM;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "mondayStart='" + mondayStart + '\'' +
                ", mondayEnd='" + mondayEnd + '\'' +
                ", tuesdayStart='" + tuesdayStart + '\'' +
                ", tuesdayEnd='" + tuesdayEnd + '\'' +
                ", wednesdayStart='" + wednesdayStart + '\'' +
                ", wednesdayEnd='" + wednesdayEnd + '\'' +
                ", thursdayStart='" + thursdayStart + '\'' +
                ", thursdayEnd='" + thursdayEnd + '\'' +
                ", fridayStart='" + fridayStart + '\'' +
                ", fridayEnd='" + fridayEnd + '\'' +
                ", saturdayStart='" + saturdayStart + '\'' +
                ", saturdayEnd='" + saturdayEnd + '\'' +
                ", sundayStart='" + sundayStart + '\'' +
                ", sundayEnd='" + sundayEnd + '\'' +
                ", mondayStartH='" + mondayStartH + '\'' +
                ", mondayStartM='" + mondayStartM + '\'' +
                ", mondayEndH='" + mondayEndH + '\'' +
                ", mondayEndM='" + mondayEndM + '\'' +
                ", tuesdayStartH='" + tuesdayStartH + '\'' +
                ", tuesdayStartM='" + tuesdayStartM + '\'' +
                ", tuesdayEndH='" + tuesdayEndH + '\'' +
                ", tuesdayEndM='" + tuesdayEndM + '\'' +
                ", wednesdayStartH='" + wednesdayStartH + '\'' +
                ", wednesdayStartM='" + wednesdayStartM + '\'' +
                ", wednesdayEndH='" + wednesdayEndH + '\'' +
                ", wednesdayEndM='" + wednesdayEndM + '\'' +
                ", thursdayStartH='" + thursdayStartH + '\'' +
                ", thursdayStartM='" + thursdayStartM + '\'' +
                ", thursdayEndH='" + thursdayEndH + '\'' +
                ", thursdayEndM='" + thursdayEndM + '\'' +
                ", fridayStartH='" + fridayStartH + '\'' +
                ", fridayStartM='" + fridayStartM + '\'' +
                ", fridayEndH='" + fridayEndH + '\'' +
                ", fridayEndM='" + fridayEndM + '\'' +
                ", saturdayStartH='" + saturdayStartH + '\'' +
                ", saturdayStartM='" + saturdayStartM + '\'' +
                ", saturdayEndH='" + saturdayEndH + '\'' +
                ", saturdayEndM='" + saturdayEndM + '\'' +
                ", sundayStartH='" + sundayStartH + '\'' +
                ", sundayStartM='" + sundayStartM + '\'' +
                ", sundayEndH='" + sundayEndH + '\'' +
                ", sundayEndM='" + sundayEndM + '\'' +
                '}';
    }
}
