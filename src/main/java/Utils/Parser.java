package Utils;

import Entities.Timetable;

import java.util.ArrayList;

public class Parser {

    public static ArrayList<String> parseServices(String services) {
        ArrayList<String> s = new ArrayList<>();
        String[] servicesSplit = services.split("\n");
        for (String service: servicesSplit) {
            s.add(DB.getServiceIdByName(service));
        }
        return s;
    }

    public static Timetable parseTimetable(String timetable) {
        int timeStartPosStartH = 4;
        int timeStartPosEndH = 6;
        int timeStartPosStartM = 7;
        int timeStartPosEndM = 9;
        int timeEndPosStartH = 10;
        int timeEndPosEndH = 12;
        int timeEndPosStartM = 13;
        int timeEndPosEndM = 15;
        Timetable t = new Timetable();

        String[] timetableSplit = timetable.split("\n");
        for (String time: timetableSplit) {
            if (time.startsWith("Пн")) {
                t.setMondayStartH(time.substring(timeStartPosStartH, timeStartPosEndH));
                t.setMondayStartM(time.substring(timeStartPosStartM, timeStartPosEndM));
                t.setMondayEndH(time.substring(timeEndPosStartH, timeEndPosEndH));
                t.setMondayEndM(time.substring(timeEndPosStartM, timeEndPosEndM));
            } else if (time.startsWith("Вт")) {
                t.setTuesdayStartH(time.substring(timeStartPosStartH, timeStartPosEndH));
                t.setTuesdayStartM(time.substring(timeStartPosStartM, timeStartPosEndM));
                t.setTuesdayEndH(time.substring(timeEndPosStartH, timeEndPosEndH));
                t.setTuesdayEndM(time.substring(timeEndPosStartM, timeEndPosEndM));
            } else if (time.startsWith("Ср")) {
                t.setWednesdayStartH(time.substring(timeStartPosStartH, timeStartPosEndH));
                t.setWednesdayStartM(time.substring(timeStartPosStartM, timeStartPosEndM));
                t.setWednesdayEndH(time.substring(timeEndPosStartH, timeEndPosEndH));
                t.setWednesdayEndM(time.substring(timeEndPosStartM, timeEndPosEndM));
            } else if (time.startsWith("Чт")) {
                t.setThursdayStartH(time.substring(timeStartPosStartH, timeStartPosEndH));
                t.setThursdayStartM(time.substring(timeStartPosStartM, timeStartPosEndM));
                t.setThursdayEndH(time.substring(timeEndPosStartH, timeEndPosEndH));
                t.setThursdayEndM(time.substring(timeEndPosStartM, timeEndPosEndM));
            } else if (time.startsWith("Пт")) {
                t.setFridayStartH(time.substring(timeStartPosStartH, timeStartPosEndH));
                t.setFridayStartM(time.substring(timeStartPosStartM, timeStartPosEndM));
                t.setFridayEndH(time.substring(timeEndPosStartH, timeEndPosEndH));
                t.setFridayEndM(time.substring(timeEndPosStartM, timeEndPosEndM));
            } else if (time.startsWith("Сб")) {
                t.setSaturdayStartH(time.substring(timeStartPosStartH, timeStartPosEndH));
                t.setSaturdayStartM(time.substring(timeStartPosStartM, timeStartPosEndM));
                t.setSaturdayEndH(time.substring(timeEndPosStartH, timeEndPosEndH));
                t.setSaturdayEndM(time.substring(timeEndPosStartM, timeEndPosEndM));
            } else if (time.startsWith("Вс")) {
                t.setSundayStartH(time.substring(timeStartPosStartH, timeStartPosEndH));
                t.setSundayStartM(time.substring(timeStartPosStartM, timeStartPosEndM));
                t.setSundayEndH(time.substring(timeEndPosStartH, timeEndPosEndH));
                t.setSundayEndM(time.substring(timeEndPosStartM, timeEndPosEndM));
            }
        }
        return t;
    }

}
