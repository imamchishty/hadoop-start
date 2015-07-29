package com.shedhack.hadoop.starter.parser;

import com.shedhack.hadoop.starter.model.OdiBattingModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ichishty on 28/07/15.
 */
public class OdiBattingParserTest {

    @Test
    public void should_parse_valid_record() {

        // Arrange: create a valid record
        String validRecord = "78,92,70,9,0,111.42,5,bowled,1,South Africa,Leeds,22 Aug 2008,2748";

        // Act: call the parser
        OdiBattingModel model = OdiBattingParser.parse(validRecord);

        // Assert: verify that everything is what it should be.
        //Runs,Mins,BF,4s,6s,SR,Pos,Dismissal,Inns,Opposition,Ground,Start DateAscending,
        //78,92,70,9,0,111.42,5,bowled,1,South Africa,Leeds,22 Aug 2008,274
        assertEquals(78, model.getRuns());
        assertEquals(92, model.getMins());
        assertEquals(70, model.getBallsFaced());
        assertEquals(9, model.getFours());
        assertEquals(0, model.getSixes());
        assertEquals(111.42, model.getStrikeRate(), 10);
        assertEquals(5, model.getBattingPosition());
        assertEquals("bowled", model.getDismissal());
        assertEquals(1, model.getInnings());
        assertEquals("South Africa", model.getOpposition());
        assertEquals("Leeds", model.getGround());
        assertEquals("22 Aug 2008", model.getDate());
        assertEquals(2748, model.getOdiNumber());
    }

    @Test
    public void should_parse_not_out_record() {

        // Arrange: create a valid record
        String notOutRecord = "78*,106,77,9,1,101.29,5,not out,1,South Africa,The Oval,29 Aug 2008,2757";

        // Act: call the parser
        OdiBattingModel model = OdiBattingParser.parse(notOutRecord);

        // Assert: verify that everything is what it should be.
        //78*,106,77,9,1,101.29,5,not out,1,South Africa,The Oval,29 Aug 2008,2757
        assertEquals(78, model.getRuns());
        assertEquals(106, model.getMins());
        assertEquals(77, model.getBallsFaced());
        assertEquals(9, model.getFours());
        assertEquals(1, model.getSixes());
        assertEquals(101.29, model.getStrikeRate(), 10);
        assertEquals(5, model.getBattingPosition());
        assertEquals("not out", model.getDismissal());
        assertEquals(1, model.getInnings());
        assertEquals("South Africa", model.getOpposition());
        assertEquals("The Oval", model.getGround());
        assertEquals("29 Aug 2008", model.getDate());
        assertEquals(2757, model.getOdiNumber());
    }

    @Test
    public void should_parse_dnb_record() {
        String dnbRecord = "DNB,-,-,-,-,-,-,-,2,Bangladesh,Nairobi (Gym),5 Oct 2000,1632";

        // Act: call the parser
        OdiBattingModel model = OdiBattingParser.parse(dnbRecord);

        // Assert: verify that everything is what it should be.
        //DNB,-,-,-,-,-,-,-,2,Bangladesh,Nairobi (Gym),5 Oct 2000,1632
        assertEquals(0, model.getRuns());
        assertEquals(0, model.getMins());
        assertEquals(0, model.getBallsFaced());
        assertEquals(0, model.getFours());
        assertEquals(0, model.getSixes());
        assertEquals(0, model.getStrikeRate(), 10);
        assertEquals(0, model.getBattingPosition());
        assertEquals("DNB", model.getDismissal());
        assertEquals(2, model.getInnings());
        assertEquals("Bangladesh", model.getOpposition());
        assertEquals("Nairobi (Gym)", model.getGround());
        assertEquals("5 Oct 2000", model.getDate());
        assertEquals(1632, model.getOdiNumber());
    }
}
