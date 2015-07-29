package com.shedhack.hadoop.starter.parser;

import com.shedhack.hadoop.starter.model.OdiBattingModel;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ichishty on 28/07/15.
 */
public class OdiBattingParser {

    public static String DELIMATOR = ",";

    // Parse
    public static OdiBattingModel parse(String record) {

        // Split the string and map
        return mapToModel(splitString(record));
    }

    // Split by ,
    private static List<String> splitString(String record) {
        return Stream
                .of(record)
                .map(w -> w.split(DELIMATOR)).flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    // Map to the model
    private static OdiBattingModel mapToModel(List<String> elements) {

        if(elements.isEmpty() || elements.size() < 13) {
            throw new IllegalArgumentException("Invalid record");
        }

        //int runs, int mins, int ballsFaced, int fours, int sixes, float strikeRate, int battingPosition,
        //String dismissal, int innings, String opposition, String ground, String date, int odiNumber) {

        int x = 0;

        OdiBattingModelBuilder builder = new OdiBattingModelBuilder();
        builder.withRuns(elements.get(x++));
        builder.withMins(elements.get(x++));
        builder.withBallsFaced(elements.get(x++));
        builder.withFours(elements.get(x++));
        builder.withSixes(elements.get(x++));
        builder.withStrikeRate(elements.get(x++));
        builder.withBattingPosition(elements.get(x++));
        builder.withDismissal(elements.get(x++));
        builder.withInnings(elements.get(x++));
        builder.withOpposition(elements.get(x++));
        builder.withGround(elements.get(x++));
        builder.withDate(elements.get(x++));
        builder.withOdiNumber(elements.get(x++));

        return builder.build();
    }
}
