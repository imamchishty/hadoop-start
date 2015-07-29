package com.shedhack.hadoop.starter.model;

/**
 * Created by ichishty on 28/07/15.
 */
public class OdiBattingModel {

    //Runs,Mins,BF,4s,6s,SR,Pos,Dismissal,Inns,Opposition,Ground,Start DateAscending, ODI number,

    private int runs, mins, ballsFaced, fours, sixes, battingPosition, innings, odiNumber;
    private float strikeRate;
    private String dismissal, opposition, ground;
    private String date;

    /**
     * Yes this is a massive constructor, but hey it's a demo.
     * @param runs
     * @param mins
     * @param ballsFaced
     * @param fours
     * @param sixes
     * @param strikeRate
     * @param battingPosition
     * @param dismissal
     * @param innings
     * @param opposition
     * @param ground
     * @param date
     * @param odiNumber
     */
    public OdiBattingModel(int runs, int mins, int ballsFaced, int fours, int sixes, float strikeRate, int battingPosition,
        String dismissal, int innings, String opposition, String ground, String date, int odiNumber) {
        this.runs = runs;
        this.mins = mins;
        this.ballsFaced = ballsFaced;
        this.fours = fours;
        this.sixes = sixes;
        this.strikeRate = strikeRate;
        this.battingPosition = battingPosition;
        this.dismissal = dismissal;
        this.innings = innings;
        this.opposition = opposition;
        this.ground = ground;
        this.date = date;
        this.odiNumber = odiNumber;
    }

    public int getRuns() {
        return runs;
    }

    public int getMins() {
        return mins;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public int getFours() {
        return fours;
    }

    public int getSixes() {
        return sixes;
    }

    public int getBattingPosition() {
        return battingPosition;
    }

    public int getInnings() {
        return innings;
    }

    public int getOdiNumber() {
        return odiNumber;
    }

    public float getStrikeRate() {
        return strikeRate;
    }

    public String getDismissal() {
        return dismissal;
    }

    public String getOpposition() {
        return opposition;
    }

    public String getGround() {
        return ground;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "OdiBattingModel{" +
                "runs=" + runs +
                ", mins=" + mins +
                ", ballsFaced=" + ballsFaced +
                ", fours=" + fours +
                ", sixes=" + sixes +
                ", battingPosition=" + battingPosition +
                ", innings=" + innings +
                ", odiNumber=" + odiNumber +
                ", strikeRate=" + strikeRate +
                ", dismissal='" + dismissal + '\'' +
                ", opposition='" + opposition + '\'' +
                ", ground='" + ground + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
