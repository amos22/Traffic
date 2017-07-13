package com.sumo.traffic;

/**
 * Created by lawre on 7/13/2017.
 */


/**
 * Created by william on 5/29/2017.
 */

/**
 * Created by BW-33105 on 5/15/2017.
 */

public class TurnItem
{
    private String turn;
    private String dis;
    private String dur;


    public void setturn(String turn) {
        this.turn = turn;
    }

    public void setdis(String dis) {
        this.dis = dis;
    }


    public void setdur(String dur) {
        this.dur = dur;
    }

    //initialize these strings


    // get the int and string
    public String getturn() {
        return turn;
    }

    public String getdis() {
        return dis;
    }


    public String getdur() {
        return dur;
    }
}