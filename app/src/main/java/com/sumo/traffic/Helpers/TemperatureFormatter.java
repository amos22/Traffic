package com.sumo.traffic.Helpers;

/**
 * Created by kixkikx on 1/11/2017.
 */
public class TemperatureFormatter {

    public static String format(float temperature) {
        return String.valueOf(Math.round(temperature)) + "°";
    }
}


