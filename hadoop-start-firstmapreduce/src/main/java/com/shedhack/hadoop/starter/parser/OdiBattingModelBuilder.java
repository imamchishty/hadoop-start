package com.shedhack.hadoop.starter.parser;

import com.shedhack.hadoop.starter.model.OdiBattingModel;

/**
 * Created by ichishty on 28/07/15.
 */
public class OdiBattingModelBuilder {

    private int runs, mins, ballsFaced, fours, sixes, battingPosition, innings, odiNumber;
    private float strikeRate;
    private String dismissal = "DNB";
    private String opposition, ground;
    private String date;

    public OdiBattingModelBuilder(){}
    
    public OdiBattingModelBuilder withRuns(String runs) {
        if(!runs.contains("DNB")) {
            runs = runs.replace("*", "");
            this.runs = Integer.parseInt(runs);
        }
        return this;
    }

    public OdiBattingModelBuilder withMins(String mins) {
        if(!mins.contains("-")) {
            this.mins = Integer.parseInt(mins);
        }
        return this;
    }

    public OdiBattingModelBuilder withBallsFaced(String ballsFaced) {
        if(!ballsFaced.contains("-")) {
            this.ballsFaced = Integer.parseInt(ballsFaced);
        }
        return this;
    }

    public OdiBattingModelBuilder withFours(String fours) {
        if(!fours.contains("-")) {
            this.fours = Integer.parseInt(fours);
        }
        return this;
    }

    public OdiBattingModelBuilder withSixes(String sixes) {
        if(!sixes.contains("-")) {
            this.sixes = Integer.parseInt(sixes);
        }
        return this;
    }

    public OdiBattingModelBuilder withBattingPosition(String battingPosition) {
        if(!battingPosition.contains("-")) {
            this.battingPosition = Integer.parseInt(battingPosition);
        }
        return this;
    }

    public OdiBattingModelBuilder withInnings(String innings) {
        if(!innings.contains("-")) {
            this.innings = Integer.parseInt(innings);
        }
        return this;
    }

    public OdiBattingModelBuilder withOdiNumber(String odiNumber) {
        if(!odiNumber.contains("-")) {
            this.odiNumber = Integer.parseInt(odiNumber);
        }
        return this;
    }

    public OdiBattingModelBuilder withStrikeRate(String strikeRate) {
        if(!strikeRate.contains("-")) {
            this.strikeRate = Float.parseFloat(strikeRate);
        }
        return this;
    }

    public OdiBattingModelBuilder withDismissal(String dismissal) {
        if(!dismissal.contains("-")) {
            this.dismissal = dismissal;
        }
        return this;
    }

    public OdiBattingModelBuilder withOpposition(String opposition) {
        this.opposition = opposition;
        return this;
    }

    public OdiBattingModelBuilder withGround(String ground) {
        this.ground = ground;
        return this;
    }

    public OdiBattingModelBuilder withDate(String date) {
        this.date = date;
        return this;
    }
    
    public OdiBattingModel build(){
        return new OdiBattingModel(runs, mins, ballsFaced, fours, sixes, strikeRate, battingPosition,
        dismissal, innings, opposition, ground, date, odiNumber);
    }
}
