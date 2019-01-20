/*
 *  Copyright 2001-2006 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.time.format;

import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * This class is a Junit unit test for ISODateTimeFormat parsing.
 *
 * @author Stephen Colebourne
 */
public class TestISODateTimeFormatParsing extends TestCase {

    private DateTimeZone originalDateTimeZone = null;
    private TimeZone originalTimeZone = null;
    private Locale originalLocale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestISODateTimeFormatParsing.class);
    }

    public TestISODateTimeFormatParsing(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        originalDateTimeZone = DateTimeZone.getDefault();
        originalTimeZone = TimeZone.getDefault();
        originalLocale = Locale.getDefault();
        DateTimeZone.setDefault(DateTimeZone.forID("Europe/London"));
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
        Locale.setDefault(Locale.UK);
    }

    protected void tearDown() throws Exception {
        DateTimeZone.setDefault(originalDateTimeZone);
        TimeZone.setDefault(originalTimeZone);
        Locale.setDefault(originalLocale);
        originalDateTimeZone = null;
        originalTimeZone = null;
        originalLocale = null;
    }

    //-----------------------------------------------------------------------
    public void test_dateParser() {
        DateTimeFormatter parser = ISODateTimeFormat.dateParser();
        assertParse(parser, true, "2006-06-09");
        assertParse(parser, true, "+2006-06-09");
        assertParse(parser, true, "2006-W27-3");
        assertParse(parser, true, "2006-123");
        assertParse(parser, true, "2006-06-09T+02:00");
        assertParse(parser, true, "2006-W27-3T+02:00");
        assertParse(parser, true, "2006-123T+02:00");
        
        assertParse(parser, false, "2006-06-09T10:20:30.040");
        assertParse(parser, false, "+2006-06-09T10:20:30.040");
        assertParse(parser, false, "2006-W27-3T10:20:30.040");
        assertParse(parser, false, "2006-123T10:20:30.040");
        assertParse(parser, false, "2006-06-09T10:20:30.040+02:00");
        assertParse(parser, false, "2006-W27-3T10:20:30.040+02:00");
        assertParse(parser, false, "2006-123T10:20:30.040+02:00");
        
        assertParse(parser, false, "T10:20:30.040");
        assertParse(parser, false, "T10.5");
        assertParse(parser, false, "T10:20:30.040+02:00");
        assertParse(parser, false, "T10.5+02:00");
        
        assertParse(parser, false, "10:20:30.040");
        assertParse(parser, false, "10.5");
        assertParse(parser, false, "10:20:30.040+02:00");
        assertParse(parser, false, "10.5+02:00");
        
        assertNoPrint(parser);
    }

    //-----------------------------------------------------------------------
    public void test_localDateParser() {
        DateTimeFormatter parser = ISODateTimeFormat.localDateParser();
        assertEquals(DateTimeZone.UTC, parser.getZone());
        assertParse(parser, true, "2006-06-09");
        assertParse(parser, true, "+2006-06-09");
        assertParse(parser, true, "2006-W27-3");
        assertParse(parser, true, "2006-123");
        assertParse(parser, false, "2006-06-09T+02:00");
        assertParse(parser, false, "2006-W27-3T+02:00");
        assertParse(parser, false, "2006-123T+02:00");
        
        assertParse(parser, false, "2006-06-09T10:20:30.040");
        assertParse(parser, false, "2006-W27-3T10:20:30.040");
        assertParse(parser, false, "2006-123T10:20:30.040");
        assertParse(parser, false, "2006-06-09T10:20:30.040+02:00");
        assertParse(parser, false, "2006-W27-3T10:20:30.040+02:00");
        assertParse(parser, false, "2006-123T10:20:30.040+02:00");
        
        assertParse(parser, false, "T10:20:30.040");
        assertParse(parser, false, "T10.5");
        assertParse(parser, false, "T10:20:30.040+02:00");
        assertParse(parser, false, "T10.5+02:00");
        
        assertParse(parser, false, "10:20:30.040");
        assertParse(parser, false, "10.5");
        assertParse(parser, false, "10:20:30.040+02:00");
        assertParse(parser, false, "10.5+02:00");
        
        assertNoPrint(parser);
    }

    //-----------------------------------------------------------------------
    public void test_dateElementParser() {
        DateTimeFormatter parser = ISODateTimeFormat.dateElementParser();
        assertParse(parser, "2006-06-09", new DateTime(2006, 6, 9, 0, 0, 0, 0));
        assertParse(parser, "2006-06-9", new DateTime(2006, 6, 9, 0, 0, 0, 0));
        assertParse(parser, "2006-6-09", new DateTime(2006, 6, 9, 0, 0, 0, 0));
        assertParse(parser, "2006-6-9", new DateTime(2006, 6, 9, 0, 0, 0, 0));
        assertParse(parser, "+2006-06-09", new DateTime(2006, 6, 9, 0, 0, 0, 0));
        assertParse(parser, true, "2006-W27-3");
        assertParse(parser, true, "2006-123");
        assertParse(parser, false, "2006-06-09T+02:00");
        assertParse(parser, false, "2006-W27-3T+02:00");
        assertParse(parser, false, "2006-123T+02:00");
        
        assertParse(parser, false, "2006-06-09T10:20:30.040");
        assertParse(parser, false, "2006-W27-3T10:20:30.040");
        assertParse(parser, false, "2006-123T10:20:30.040");
        assertParse(parser, false, "2006-06-09T10:20:30.040+02:00");
        assertParse(parser, false, "2006-W27-3T10:20:30.040+02:00");
        assertParse(parser, false, "2006-123T10:20:30.040+02:00");
        
        assertParse(parser, false, "T10:20:30.040");
        assertParse(parser, false, "T10.5");
        assertParse(parser, false, "T10:20:30.040+02:00");
        assertParse(parser, false, "T10.5+02:00");
        
        assertParse(parser, false, "10:20:30.040");
        assertParse(parser, false, "10.5");
        assertParse(parser, false, "10:20:30.040+02:00");
        assertParse(parser, false, "10.5+02:00");
        
        assertNoPrint(parser);
    }

    //-----------------------------------------------------------------------
    public void test_timeParser() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.timeParser();
        assertParse(parser, false, "2006-06-09");
        assertParse(parser, false, "2006-W27-3");
        assertParse(parser, false, "2006-123");
        assertParse(parser, false, "2006-06-09T+02:00");
        assertParse(parser, false, "2006-W27-3T+02:00");
        assertParse(parser, false, "2006-123T+02:00");
        
        assertParse(parser, false, "2006-06-09T10:20:30.040");
        assertParse(parser, false, "2006-W27-3T10:20:30.040");
        assertParse(parser, false, "2006-123T10:20:30.040");
        assertParse(parser, false, "2006-06-09T10:20:30.040+02:00");
        assertParse(parser, false, "2006-W27-3T10:20:30.040+02:00");
        assertParse(parser, false, "2006-123T10:20:30.040+02:00");
        
        assertParse(parser, "T10:20:30.040000000", new DateTime(1970, 1, 1, 10, 20, 30, 40));
        assertParse(parser, "T10:20:30.004", new DateTime(1970, 1, 1, 10, 20, 30, 4));
        assertParse(parser, "T10:20:30.040", new DateTime(1970, 1, 1, 10, 20, 30, 40));
        assertParse(parser, "T10:20:30.400", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "T10.5", new DateTime(1970, 1, 1, 10, 30, 0, 0));
        assertParse(parser, "T10:20:30.040+02:00", new DateTime(1970, 1, 1, 8, 20, 30, 40));
        assertParse(parser, "T10.5+02:00", new DateTime(1970, 1, 1, 8, 30, 0, 0));
        
        assertParse(parser, true, "10:20:30.040");
        assertParse(parser, true, "10.5");
        assertParse(parser, true, "10:20:30.040+02:00");
        assertParse(parser, true, "10.5+02:00");
        
        assertNoPrint(parser);
    }

    //-----------------------------------------------------------------------
    public void test_localTimeParser() {
        DateTimeFormatter parser = ISODateTimeFormat.localTimeParser();
        assertEquals(DateTimeZone.UTC, parser.getZone());
        assertParse(parser, false, "2006-06-09");
        assertParse(parser, false, "2006-W27-3");
        assertParse(parser, false, "2006-123");
        assertParse(parser, false, "2006-06-09T+02:00");
        assertParse(parser, false, "2006-W27-3T+02:00");
        assertParse(parser, false, "2006-123T+02:00");
        
        assertParse(parser, false, "2006-06-09T10:20:30.040");
        assertParse(parser, false, "2006-W27-3T10:20:30.040");
        assertParse(parser, false, "2006-123T10:20:30.040");
        assertParse(parser, false, "2006-06-09T10:20:30.040+02:00");
        assertParse(parser, false, "2006-W27-3T10:20:30.040+02:00");
        assertParse(parser, false, "2006-123T10:20:30.040+02:00");
        
        assertParse(parser, true, "T10:20:30.040");
        assertParse(parser, true, "T10.5");
        assertParse(parser, false, "T10:20:30.040+02:00");
        assertParse(parser, false, "T10.5+02:00");
        
        assertParse(parser, true, "10:20:30.040");
        assertParse(parser, true, "10.5");
        assertParse(parser, false, "10:20:30.040+02:00");
        assertParse(parser, false, "10.5+02:00");
        
        assertParse(parser, true, "00:00:10.512345678");
        assertEquals(10512, parser.parseMillis("00:00:10.512345678"));
        
        assertNoPrint(parser);
    }

    //-----------------------------------------------------------------------
    public void test_timeElementParser() {
        DateTimeFormatter parser = ISODateTimeFormat.timeElementParser();
        assertParse(parser, false, "2006-06-09");
        assertParse(parser, false, "2006-W27-3");
        assertParse(parser, false, "2006-123");
        assertParse(parser, false, "2006-06-09T+02:00");
        assertParse(parser, false, "2006-W27-3T+02:00");
        assertParse(parser, false, "2006-123T+02:00");
        
        assertParse(parser, false, "2006-06-09T10:20:30.040");
        assertParse(parser, false, "2006-W27-3T10:20:30.040");
        assertParse(parser, false, "2006-123T10:20:30.040");
        assertParse(parser, false, "2006-06-09T10:20:30.040+02:00");
        assertParse(parser, false, "2006-W27-3T10:20:30.040+02:00");
        assertParse(parser, false, "2006-123T10:20:30.040+02:00");
        
        assertParse(parser, false, "T10:20:30.040");
        assertParse(parser, false, "T10.5");
        assertParse(parser, false, "T10:20:30.040+02:00");
        assertParse(parser, false, "T10.5+02:00");
        
        assertParse(parser, true, "10:20:30.040");
        assertParse(parser, true, "10.5");
        assertParse(parser, false, "10:20:30.040+02:00");
        assertParse(parser, false, "10.5+02:00");
        
        assertParse(parser, true, "00:00:10.512345678");
        // result is offset by London DST in 1970-01-01
        assertEquals(10512, parser.parseMillis("00:00:10.512345678") + DateTimeZone.getDefault().getOffset(0L));
        
        assertNoPrint(parser);
    }

    public void test_dateTimeParser() {
		this.testISODateTimeFormatParsingTest_dateTimeParserTemplate(
				new TestISODateTimeFormatParsingTest_dateTimeParserAdapterImpl(), true, true, true, true);
	}

    public void test_dateOptionalTimeParser() {
		this.testISODateTimeFormatParsingTest_dateTimeParserTemplate(
				new TestISODateTimeFormatParsingTest_dateOptionalTimeParserAdapterImpl(), false, false, false, false);
	}

    //-----------------------------------------------------------------------
    public void test_localDateOptionalTimeParser() {
        DateTimeFormatter parser = ISODateTimeFormat.localDateOptionalTimeParser();
        assertEquals(DateTimeZone.UTC, parser.getZone());
        assertParse(parser, true, "2006-06-09");
        assertParse(parser, true, "2006-W27-3");
        assertParse(parser, true, "2006-123");
        assertParse(parser, false, "2006-06-09T+02:00");
        assertParse(parser, false, "2006-W27-3T+02:00");
        assertParse(parser, false, "2006-123T+02:00");
        
        assertParse(parser, true, "2006-06-09T10:20:30.040");
        assertParse(parser, true, "2006-W27-3T10:20:30.040");
        assertParse(parser, true, "2006-123T10:20:30.040");
        assertParse(parser, false, "2006-06-09T10:20:30.040+02:00");
        assertParse(parser, false, "2006-W27-3T10:20:30.040+02:00");
        assertParse(parser, false, "2006-123T10:20:30.040+02:00");
        
        assertParse(parser, false, "T10:20:30.040");
        assertParse(parser, false, "T10.5");
        assertParse(parser, false, "T10:20:30.040+02:00");
        assertParse(parser, false, "T10.5+02:00");
        
        assertParse(parser, false, "10:20:30.040");
        assertParse(parser, false, "10.5");
        assertParse(parser, false, "10:20:30.040+02:00");
        assertParse(parser, false, "10.5+02:00");
        
        assertNoPrint(parser);
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    public void test_date() {
        DateTimeFormatter parser = ISODateTimeFormat.date();
        assertParse(parser, "2006-02-04", new DateTime(2006, 2, 4, 0, 0, 0, 0));
        assertParse(parser, "2006-2-04", new DateTime(2006, 2, 4, 0, 0, 0, 0));
        assertParse(parser, "2006-02-4", new DateTime(2006, 2, 4, 0, 0, 0, 0));
        assertParse(parser, "2006-2-4", new DateTime(2006, 2, 4, 0, 0, 0, 0));
        assertParse(parser, "+2006-02-04", new DateTime(2006, 2, 4, 0, 0, 0, 0));
        assertParse(parser, false, "2006-02-");
        assertParse(parser, false, "2006-02");
        assertParse(parser, false, "2006--4");
        assertParse(parser, false, "2006-1");
        assertParse(parser, false, "2006");
    }

    //-----------------------------------------------------------------------
    public void test_time() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.time();
        assertParse(parser, "10:20:30.400999999Z", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "10:20:30.400Z", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "10:20:30.40Z", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "10:20:30.4Z", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "5:6:7.8Z", new DateTime(1970, 1, 1, 5, 6, 7, 800));
        assertParse(parser, false, "10:20.400Z");
        assertParse(parser, false, "10:2.400Z");
        assertParse(parser, false, "10.400Z");
        assertParse(parser, false, "1.400Z");
    }

    public void test_timeNoMillis() {
		this.testISODateTimeFormatParsingNoMillisTemplate(
				new TestISODateTimeFormatParsingTest_timeNoMillisAdapterImpl(), "10:20:30Z", "5:6:7Z", "10:20Z",
				"10:2Z", "10Z", "1Z");
	}

    //-----------------------------------------------------------------------
    public void test_tTime() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.tTime();
        assertParse(parser, "T10:20:30.400999999Z", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "T10:20:30.400Z", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "T10:20:30.40Z", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "T10:20:30.4Z", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "T5:6:7.8Z", new DateTime(1970, 1, 1, 5, 6, 7, 800));
        assertParse(parser, false, "T10:20.400Z");
        assertParse(parser, false, "T102.400Z");
        assertParse(parser, false, "T10.400Z");
        assertParse(parser, false, "T1.400Z");
    }

    public void test_tTimeNoMillis() {
		this.testISODateTimeFormatParsingNoMillisTemplate(
				new TestISODateTimeFormatParsingTest_tTimeNoMillisAdapterImpl(), "T10:20:30Z", "T5:6:7Z", "T10:20Z",
				"T10:2Z", "T10Z", "T1Z");
	}

    //-----------------------------------------------------------------------
    public void test_dateTime() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.dateTime();
        assertParse(parser, "2006-02-04T10:20:30.400999999Z", new DateTime(2006, 2, 4, 10, 20, 30, 400));
        assertParse(parser, "2006-02-04T10:20:30.400Z", new DateTime(2006, 2, 4, 10, 20, 30, 400));
        assertParse(parser, "2006-02-04T10:20:30.40Z", new DateTime(2006, 2, 4, 10, 20, 30, 400));
        assertParse(parser, "2006-02-04T10:20:30.4Z", new DateTime(2006, 2, 4, 10, 20, 30, 400));
        assertParse(parser, "2006-02-4T10:20:30.400Z", new DateTime(2006, 2, 4, 10, 20, 30, 400));
        assertParse(parser, "2006-2-04T10:20:30.400Z", new DateTime(2006, 2, 4, 10, 20, 30, 400));
        assertParse(parser, "2006-2-4T10:20:30.400Z", new DateTime(2006, 2, 4, 10, 20, 30, 400));
        assertParse(parser, "2006-02-04T5:6:7.800Z", new DateTime(2006, 2, 4, 5, 6, 7, 800));
        assertParse(parser, "+2006-02-04T5:6:7.800Z", new DateTime(2006, 2, 4, 5, 6, 7, 800));
        assertParse(parser, false, "2006-02-T10:20:30.400Z");
        assertParse(parser, false, "2006-12T10:20:30.400Z");
        assertParse(parser, false, "2006-1T10:20:30.400Z");
        assertParse(parser, false, "2006T10:20:30.400Z");
        assertParse(parser, false, "200T10:20:30.400Z");
        assertParse(parser, false, "20T10:20:30.400Z");
        assertParse(parser, false, "2T10:20:30.400Z");
        assertParse(parser, false, "2006-02-04T10:20.400Z");
        assertParse(parser, false, "2006-02-04T10:2.400Z");
        assertParse(parser, false, "2006-02-04T10.400Z");
        assertParse(parser, false, "2006-02-04T1.400Z");
    }

    //-----------------------------------------------------------------------
    public void test_dateTimeNoMillis() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.dateTimeNoMillis();
        assertParse(parser, "2006-02-04T10:20:30Z", new DateTime(2006, 2, 4, 10, 20, 30, 0));
        assertParse(parser, "2006-02-4T10:20:30Z", new DateTime(2006, 2, 4, 10, 20, 30, 0));
        assertParse(parser, "2006-2-04T10:20:30Z", new DateTime(2006, 2, 4, 10, 20, 30, 0));
        assertParse(parser, "2006-2-4T10:20:30Z", new DateTime(2006, 2, 4, 10, 20, 30, 0));
        assertParse(parser, "2006-02-04T5:6:7Z", new DateTime(2006, 2, 4, 5, 6, 7, 0));
        assertParse(parser, "+2006-02-04T5:6:7Z", new DateTime(2006, 2, 4, 5, 6, 7, 0));
        assertParse(parser, false, "2006-02-T10:20:30Z");
        assertParse(parser, false, "2006-12T10:20:30Z");
        assertParse(parser, false, "2006-1T10:20:30Z");
        assertParse(parser, false, "2006T10:20:30Z");
        assertParse(parser, false, "200T10:20:30Z");
        assertParse(parser, false, "20T10:20:30Z");
        assertParse(parser, false, "2T10:20:30Z");
        assertParse(parser, false, "2006-02-04T10:20Z");
        assertParse(parser, false, "2006-02-04T10:2Z");
        assertParse(parser, false, "2006-02-04T10Z");
        assertParse(parser, false, "2006-02-04T1Z");
    }

    //-----------------------------------------------------------------------
    public void test_ordinalDate() {
        DateTimeFormatter parser = ISODateTimeFormat.ordinalDate();
        assertParse(parser, "2006-123", new DateTime(2006, 1, 1, 0, 0, 0, 0).withDayOfYear(123));
        assertParse(parser, "2006-12", new DateTime(2006, 1, 1, 0, 0, 0, 0).withDayOfYear(12));
        assertParse(parser, "2006-1", new DateTime(2006, 1, 1, 0, 0, 0, 0).withDayOfYear(1));
        assertParse(parser, "+2006-123", new DateTime(2006, 1, 1, 0, 0, 0, 0).withDayOfYear(123));
        assertParse(parser, false, "2006-");
        assertParse(parser, false, "2006");
    }

    //-----------------------------------------------------------------------
    public void test_ordinalDateTime() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.ordinalDateTime();
        assertParse(parser, "2006-123T10:20:30.400999999Z", new DateTime(2006, 1, 1, 10, 20, 30, 400).withDayOfYear(123));
        assertParse(parser, "2006-123T10:20:30.400Z", new DateTime(2006, 1, 1, 10, 20, 30, 400).withDayOfYear(123));
        assertParse(parser, "2006-123T10:20:30.40Z", new DateTime(2006, 1, 1, 10, 20, 30, 400).withDayOfYear(123));
        assertParse(parser, "2006-123T10:20:30.4Z", new DateTime(2006, 1, 1, 10, 20, 30, 400).withDayOfYear(123));
        assertParse(parser, "2006-12T10:20:30.400Z", new DateTime(2006, 1, 1, 10, 20, 30, 400).withDayOfYear(12));
        assertParse(parser, "2006-1T10:20:30.400Z", new DateTime(2006, 1, 1, 10, 20, 30, 400).withDayOfYear(1));
        assertParse(parser, "2006-123T5:6:7.800Z", new DateTime(2006, 1, 1, 5, 6, 7, 800).withDayOfYear(123));
        assertParse(parser, "+2006-123T5:6:7.800Z", new DateTime(2006, 1, 1, 5, 6, 7, 800).withDayOfYear(123));
        assertParse(parser, false, "2006-T10:20:30.400Z");
        assertParse(parser, false, "2006T10:20:30.400Z");
        assertParse(parser, false, "2006-123T10:20.400Z");
        assertParse(parser, false, "2006-123T10:2.400Z");
        assertParse(parser, false, "2006-123T10.400Z");
        assertParse(parser, false, "2006-123T1.400Z");
    }

    //-----------------------------------------------------------------------
    public void test_ordinalDateTimeNoMillis() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.ordinalDateTimeNoMillis();
        assertParse(parser, "2006-123T10:20:30Z", new DateTime(2006, 1, 1, 10, 20, 30, 0).withDayOfYear(123));
        assertParse(parser, "2006-12T10:20:30Z", new DateTime(2006, 1, 1, 10, 20, 30, 0).withDayOfYear(12));
        assertParse(parser, "2006-1T10:20:30Z", new DateTime(2006, 1, 1, 10, 20, 30, 0).withDayOfYear(1));
        assertParse(parser, "2006-123T5:6:7Z", new DateTime(2006, 1, 1, 5, 6, 7, 0).withDayOfYear(123));
        assertParse(parser, "+2006-123T5:6:7Z", new DateTime(2006, 1, 1, 5, 6, 7, 0).withDayOfYear(123));
        assertParse(parser, false, "2006-T10:20:30Z");
        assertParse(parser, false, "2006T10:20:30Z");
        assertParse(parser, false, "2006-123T10:20Z");
        assertParse(parser, false, "2006-123T10:2Z");
        assertParse(parser, false, "2006-123T10Z");
        assertParse(parser, false, "2006-123T1Z");
    }

    //-----------------------------------------------------------------------
    public void test_weekDate() {
        DateTimeFormatter parser = ISODateTimeFormat.weekDate();
        assertParse(parser, "2006-W27-3", new DateTime(2006, 6, 1, 0, 0, 0, 0).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, "2006-W2-3", new DateTime(2006, 6, 1, 0, 0, 0, 0).withWeekOfWeekyear(2).withDayOfWeek(3));
        assertParse(parser, "+2006-W27-3", new DateTime(2006, 6, 1, 0, 0, 0, 0).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, false, "2006-W-3");
        assertParse(parser, false, "2006-W27-");
        assertParse(parser, false, "2006-W27");
        assertParse(parser, false, "2006-W2");
        assertParse(parser, false, "2006-W");
    }

    //-----------------------------------------------------------------------
    public void test_weekDateTime() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.weekDateTime();
        assertParse(parser, "2006-W27-3T10:20:30.400999999Z", new DateTime(2006, 6, 1, 10, 20, 30, 400).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, "2006-W27-3T10:20:30.400Z", new DateTime(2006, 6, 1, 10, 20, 30, 400).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, "2006-W27-3T10:20:30.40Z", new DateTime(2006, 6, 1, 10, 20, 30, 400).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, "2006-W27-3T10:20:30.4Z", new DateTime(2006, 6, 1, 10, 20, 30, 400).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, "2006-W2-3T10:20:30.400Z", new DateTime(2006, 6, 1, 10, 20, 30, 400).withWeekOfWeekyear(2).withDayOfWeek(3));
        assertParse(parser, "2006-W27-3T5:6:7.800Z", new DateTime(2006, 6, 1, 5, 6, 7, 800).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, "+2006-W27-3T5:6:7.800Z", new DateTime(2006, 6, 1, 5, 6, 7, 800).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, false, "2006-W27-T10:20:30.400Z");
        assertParse(parser, false, "2006-W27T10:20:30.400Z");
        assertParse(parser, false, "2006-W2T10:20:30.400Z");
        assertParse(parser, false, "2006-W-3T10:20:30.400Z");
        assertParse(parser, false, "2006-W27-3T10:20.400Z");
        assertParse(parser, false, "2006-W27-3T10:2.400Z");
        assertParse(parser, false, "2006-W27-3T10.400Z");
        assertParse(parser, false, "2006-W27-3T1.400Z");
    }

    //-----------------------------------------------------------------------
    public void test_weekDateTimeNoMillis() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.weekDateTimeNoMillis();
        assertParse(parser, "2006-W27-3T10:20:30Z", new DateTime(2006, 6, 1, 10, 20, 30, 0).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, "2006-W2-3T10:20:30Z", new DateTime(2006, 6, 1, 10, 20, 30, 0).withWeekOfWeekyear(2).withDayOfWeek(3));
        assertParse(parser, "2006-W27-3T5:6:7Z", new DateTime(2006, 6, 1, 5, 6, 7, 0).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, "+2006-W27-3T5:6:7Z", new DateTime(2006, 6, 1, 5, 6, 7, 0).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, false, "2006-W27-T10:20:30Z");
        assertParse(parser, false, "2006-W27T10:20:30Z");
        assertParse(parser, false, "2006-W2T10:20:30Z");
        assertParse(parser, false, "2006-W-3T10:20:30Z");
        assertParse(parser, false, "2006-W27-3T10:20Z");
        assertParse(parser, false, "2006-W27-3T10:2Z");
        assertParse(parser, false, "2006-W27-3T10Z");
        assertParse(parser, false, "2006-W27-3T1Z");
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    public void test_basicDate() {
        DateTimeFormatter parser = ISODateTimeFormat.basicDate();
        assertParse(parser, "20060204", new DateTime(2006, 2, 4, 0, 0, 0, 0));
        assertParse(parser, "+20060204", new DateTime(2006, 2, 4, 0, 0, 0, 0));
        assertParse(parser, false, "2006024");
        assertParse(parser, false, "200602");
        assertParse(parser, false, "20061");
        assertParse(parser, false, "2006");
    }

    //-----------------------------------------------------------------------
    public void test_basicTime() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.basicTime();
        assertParse(parser, "102030.400999999Z", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "102030.400Z", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "102030.40Z", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "102030.4Z", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, false, "10203.400Z");
        assertParse(parser, false, "1020.400Z");
        assertParse(parser, false, "102.400Z");
        assertParse(parser, false, "10.400Z");
        assertParse(parser, false, "1.400Z");
    }

    public void test_basicTimeNoMillis() {
		this.testISODateTimeFormatParsingTest_basicTimeNoMillisTemplate(
				new TestISODateTimeFormatParsingTest_basicTimeNoMillisAdapterImpl(), "102030Z", "10203Z", "1020Z",
				"102Z", "10Z", "1Z");
	}

    //-----------------------------------------------------------------------
    public void test_basicTTime() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.basicTTime();
        assertParse(parser, "T102030.400999999Z", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "T102030.400Z", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "T102030.40Z", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "T102030.4Z", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, false, "T10203.400Z");
        assertParse(parser, false, "T1020.400Z");
        assertParse(parser, false, "T102.400Z");
        assertParse(parser, false, "T10.400Z");
        assertParse(parser, false, "T1.400Z");
    }

    public void test_basicTTimeNoMillis() {
		this.testISODateTimeFormatParsingTest_basicTimeNoMillisTemplate(
				new TestISODateTimeFormatParsingTest_basicTTimeNoMillisAdapterImpl(), "T102030Z", "T10203Z", "T1020Z",
				"T102Z", "T10Z", "T1Z");
	}

    //-----------------------------------------------------------------------
    public void test_basicDateTime() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.basicDateTime();
        assertParse(parser, "20061204T102030.400999999Z", new DateTime(2006, 12, 4, 10, 20, 30, 400));
        assertParse(parser, "20061204T102030.400Z", new DateTime(2006, 12, 4, 10, 20, 30, 400));
        assertParse(parser, "20061204T102030.40Z", new DateTime(2006, 12, 4, 10, 20, 30, 400));
        assertParse(parser, "20061204T102030.4Z", new DateTime(2006, 12, 4, 10, 20, 30, 400));
        assertParse(parser, "+20061204T102030.4Z", new DateTime(2006, 12, 4, 10, 20, 30, 400));
        assertParse(parser, false, "2006120T102030.400Z");
        assertParse(parser, false, "200612T102030.400Z");
        assertParse(parser, false, "20061T102030.400Z");
        assertParse(parser, false, "2006T102030.400Z");
        assertParse(parser, false, "200T102030.400Z");
        assertParse(parser, false, "20T102030.400Z");
        assertParse(parser, false, "2T102030.400Z");
        assertParse(parser, false, "20061204T10203.400Z");
        assertParse(parser, false, "20061204T1020.400Z");
        assertParse(parser, false, "20061204T102.400Z");
        assertParse(parser, false, "20061204T10.400Z");
        assertParse(parser, false, "20061204T1.400Z");
    }

    //-----------------------------------------------------------------------
    public void test_basicDateTimeNoMillis() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.basicDateTimeNoMillis();
        assertParse(parser, "20061204T102030Z", new DateTime(2006, 12, 4, 10, 20, 30, 0));
        assertParse(parser, "+20061204T102030Z", new DateTime(2006, 12, 4, 10, 20, 30, 0));
        assertParse(parser, false, "2006120T102030Z");
        assertParse(parser, false, "200612T102030Z");
        assertParse(parser, false, "20061T102030Z");
        assertParse(parser, false, "2006T102030Z");
        assertParse(parser, false, "200T102030Z");
        assertParse(parser, false, "20T102030Z");
        assertParse(parser, false, "2T102030Z");
        assertParse(parser, false, "20061204T10203Z");
        assertParse(parser, false, "20061204T1020Z");
        assertParse(parser, false, "20061204T102Z");
        assertParse(parser, false, "20061204T10Z");
        assertParse(parser, false, "20061204T1Z");
    }

    //-----------------------------------------------------------------------
    public void test_basicOrdinalDate() {
        DateTimeFormatter parser = ISODateTimeFormat.basicOrdinalDate();
        assertParse(parser, "2006123", new DateTime(2006, 1, 1, 0, 0, 0, 0).withDayOfYear(123));
        assertParse(parser, "+2006123", new DateTime(2006, 1, 1, 0, 0, 0, 0).withDayOfYear(123));
        assertParse(parser, false, "200612");
        assertParse(parser, false, "20061");
        assertParse(parser, false, "2006");
    }

    //-----------------------------------------------------------------------
    public void test_basicOrdinalDateTime() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.basicOrdinalDateTime();
        assertParse(parser, "2006123T102030.400999999Z", new DateTime(2006, 1, 1, 10, 20, 30, 400).withDayOfYear(123));
        assertParse(parser, "2006123T102030.400Z", new DateTime(2006, 1, 1, 10, 20, 30, 400).withDayOfYear(123));
        assertParse(parser, "2006123T102030.40Z", new DateTime(2006, 1, 1, 10, 20, 30, 400).withDayOfYear(123));
        assertParse(parser, "2006123T102030.4Z", new DateTime(2006, 1, 1, 10, 20, 30, 400).withDayOfYear(123));
        assertParse(parser, "+2006123T102030.4Z", new DateTime(2006, 1, 1, 10, 20, 30, 400).withDayOfYear(123));
        assertParse(parser, false, "200612T102030.400Z");
        assertParse(parser, false, "20061T102030.400Z");
        assertParse(parser, false, "2006T102030.400Z");
        assertParse(parser, false, "200T102030.400Z");
        assertParse(parser, false, "20T102030.400Z");
        assertParse(parser, false, "2T102030.400Z");
        assertParse(parser, false, "2006123T10203.400Z");
        assertParse(parser, false, "2006123T1020.400Z");
        assertParse(parser, false, "2006123T102.400Z");
        assertParse(parser, false, "2006123T10.400Z");
        assertParse(parser, false, "2006123T1.400Z");
    }

    //-----------------------------------------------------------------------
    public void test_basicOrdinalDateTimeNoMillis() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.basicOrdinalDateTimeNoMillis();
        assertParse(parser, "2006123T102030Z", new DateTime(2006, 1, 1, 10, 20, 30, 0).withDayOfYear(123));
        assertParse(parser, "+2006123T102030Z", new DateTime(2006, 1, 1, 10, 20, 30, 0).withDayOfYear(123));
        assertParse(parser, false, "200612T102030Z");
        assertParse(parser, false, "20061T102030Z");
        assertParse(parser, false, "2006T102030Z");
        assertParse(parser, false, "200T102030Z");
        assertParse(parser, false, "20T102030Z");
        assertParse(parser, false, "2T102030Z");
        assertParse(parser, false, "2006123T10203Z");
        assertParse(parser, false, "2006123T1020Z");
        assertParse(parser, false, "2006123T102Z");
        assertParse(parser, false, "2006123T10Z");
        assertParse(parser, false, "2006123T1Z");
    }

    //-----------------------------------------------------------------------
    public void test_basicWeekDate() {
        DateTimeFormatter parser = ISODateTimeFormat.basicWeekDate();
        assertParse(parser, "2006W273", new DateTime(2006, 6, 1, 0, 0, 0, 0).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, "+2006W273", new DateTime(2006, 6, 1, 0, 0, 0, 0).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, false, "2006W27");
        assertParse(parser, false, "2006W2");
        assertParse(parser, false, "2006W");
    }

    //-----------------------------------------------------------------------
    public void test_basicWeekDateTime() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.basicWeekDateTime();
        assertParse(parser, "2006W273T102030.400999999Z", new DateTime(2006, 6, 1, 10, 20, 30, 400).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, "2006W273T102030.400Z", new DateTime(2006, 6, 1, 10, 20, 30, 400).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, "2006W273T102030.40Z", new DateTime(2006, 6, 1, 10, 20, 30, 400).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, "2006W273T102030.4Z", new DateTime(2006, 6, 1, 10, 20, 30, 400).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, "+2006W273T102030.4Z", new DateTime(2006, 6, 1, 10, 20, 30, 400).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, false, "2006W27T102030.400Z");
        assertParse(parser, false, "2006W2T102030.400Z");
        assertParse(parser, false, "2006W273T10203.400Z");
        assertParse(parser, false, "2006W273T1020.400Z");
        assertParse(parser, false, "2006W273T102.400Z");
        assertParse(parser, false, "2006W273T10.400Z");
        assertParse(parser, false, "2006W273T1.400Z");
    }

    //-----------------------------------------------------------------------
    public void test_basicWeekDateTimeNoMillis() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.basicWeekDateTimeNoMillis();
        assertParse(parser, "2006W273T102030Z", new DateTime(2006, 6, 1, 10, 20, 30, 0).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, "+2006W273T102030Z", new DateTime(2006, 6, 1, 10, 20, 30, 0).withWeekOfWeekyear(27).withDayOfWeek(3));
        assertParse(parser, false, "2006W27T102030Z");
        assertParse(parser, false, "2006W2T102030Z");
        assertParse(parser, false, "2006W273T10203Z");
        assertParse(parser, false, "2006W273T1020Z");
        assertParse(parser, false, "2006W273T102Z");
        assertParse(parser, false, "2006W273T10Z");
        assertParse(parser, false, "2006W273T1Z");
    }

    public void test_hourMinute() {
		this.testISODateTimeFormatParsingTest_hourMinuteTemplate(
				new TestISODateTimeFormatParsingTest_hourMinuteAdapterImpl(), "10:20", 0, "5:6", 0, "10:20:30");
	}

    public void test_hourMinuteSecond() {
		this.testISODateTimeFormatParsingTest_hourMinuteTemplate(
				new TestISODateTimeFormatParsingTest_hourMinuteSecondAdapterImpl(), "10:20:30", 30, "5:6:7", 7,
				"10:20:30.4");
	}

    //-----------------------------------------------------------------------
    public void test_hourMinuteSecondMillis() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.hourMinuteSecondMillis();
        assertParse(parser, "10:20:30.400", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "10:20:30.40", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "10:20:30.4", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "5:6:7.8", new DateTime(1970, 1, 1, 5, 6, 7, 800));
        assertParse(parser, false, "10:20:30.400999999");
        assertParse(parser, false, "10:20.400");
        assertParse(parser, false, "10:2.400");
        assertParse(parser, false, "10.400");
        assertParse(parser, false, "1.400");
    }

    //-----------------------------------------------------------------------
    public void test_hourMinuteSecondFraction() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        DateTimeFormatter parser = ISODateTimeFormat.hourMinuteSecondFraction();
        assertParse(parser, "10:20:30.400999999", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "10:20:30.400", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "10:20:30.40", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "10:20:30.4", new DateTime(1970, 1, 1, 10, 20, 30, 400));
        assertParse(parser, "5:6:7.8", new DateTime(1970, 1, 1, 5, 6, 7, 800));
        assertParse(parser, false, "10:20.400");
        assertParse(parser, false, "10:2.400");
        assertParse(parser, false, "10.400");
        assertParse(parser, false, "1.400");
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    private void assertParse(DateTimeFormatter parser, boolean expected, String str) {
        if (expected) {
            parser.parseMillis(str);
        } else {
            try {
                parser.parseMillis(str);
                fail();
            } catch (IllegalArgumentException ex) {
                // expected
            }
        }
    }

    private void assertParse(DateTimeFormatter parser, String str, DateTime expected) {
        DateTime dt = parser.parseDateTime(str);
        assertEquals(expected, dt);
    }

    private void assertNoPrint(DateTimeFormatter parser) {
        try {
            parser.print(new DateTime(2012, 6, 30, 12, 0, 0));
        } catch (UnsupportedOperationException ex) {
            // expected
        }
    }

	public void testISODateTimeFormatParsingTest_dateTimeParserTemplate(
			TestISODateTimeFormatParsingTest_dateTimeParserAdapter adapter, boolean b1, boolean b2, boolean b3,
			boolean b4) {
		DateTimeFormatter parser = adapter.dateTimeParser();
		assertParse(parser, true, "2006-06-09");
		assertParse(parser, true, "2006-W27-3");
		assertParse(parser, true, "2006-123");
		assertParse(parser, true, "2006-06-09T+02:00");
		assertParse(parser, true, "2006-W27-3T+02:00");
		assertParse(parser, true, "2006-123T+02:00");
		assertParse(parser, true, "2006-06-09T10:20:30.040");
		assertParse(parser, true, "2006-W27-3T10:20:30.040");
		assertParse(parser, true, "2006-123T10:20:30.040");
		assertParse(parser, true, "2006-06-09T10:20:30.040+02:00");
		assertParse(parser, true, "2006-W27-3T10:20:30.040+02:00");
		assertParse(parser, true, "2006-123T10:20:30.040+02:00");
		assertParse(parser, b1, "T10:20:30.040");
		assertParse(parser, b2, "T10.5");
		assertParse(parser, b3, "T10:20:30.040+02:00");
		assertParse(parser, b4, "T10.5+02:00");
		assertParse(parser, false, "10:20:30.040");
		assertParse(parser, false, "10.5");
		assertParse(parser, false, "10:20:30.040+02:00");
		assertParse(parser, false, "10.5+02:00");
		assertNoPrint(parser);
	}

	interface TestISODateTimeFormatParsingTest_dateTimeParserAdapter {
		DateTimeFormatter dateTimeParser();
	}

	class TestISODateTimeFormatParsingTest_dateTimeParserAdapterImpl
			implements TestISODateTimeFormatParsingTest_dateTimeParserAdapter {
		public DateTimeFormatter dateTimeParser() {
			return ISODateTimeFormat.dateTimeParser();
		}
	}

	class TestISODateTimeFormatParsingTest_dateOptionalTimeParserAdapterImpl
			implements TestISODateTimeFormatParsingTest_dateTimeParserAdapter {
		public DateTimeFormatter dateTimeParser() {
			return ISODateTimeFormat.dateOptionalTimeParser();
		}
	}

	public void testISODateTimeFormatParsingTest_hourMinuteTemplate(
			TestISODateTimeFormatParsingTest_hourMinuteAdapter adapter, String string1, int i1, String string2, int i2,
			String string3) {
		DateTimeZone.setDefault(DateTimeZone.UTC);
		DateTimeFormatter parser = adapter.hourMinute();
		assertParse(parser, string1, new DateTime(1970, 1, 1, 10, 20, i1, 0));
		assertParse(parser, string2, new DateTime(1970, 1, 1, 5, 6, i2, 0));
		assertParse(parser, false, "10:20:30.400999999");
		assertParse(parser, false, "10:20:30.400");
		assertParse(parser, false, string3);
		assertParse(parser, false, "10:20.400");
		assertParse(parser, false, "10:2.400");
		assertParse(parser, false, "10.400");
		assertParse(parser, false, "1.400");
	}

	interface TestISODateTimeFormatParsingTest_hourMinuteAdapter {
		DateTimeFormatter hourMinute();
	}

	class TestISODateTimeFormatParsingTest_hourMinuteAdapterImpl
			implements TestISODateTimeFormatParsingTest_hourMinuteAdapter {
		public DateTimeFormatter hourMinute() {
			return ISODateTimeFormat.hourMinute();
		}
	}

	class TestISODateTimeFormatParsingTest_hourMinuteSecondAdapterImpl
			implements TestISODateTimeFormatParsingTest_hourMinuteAdapter {
		public DateTimeFormatter hourMinute() {
			return ISODateTimeFormat.hourMinuteSecond();
		}
	}

	public void testISODateTimeFormatParsingTest_basicTimeNoMillisTemplate(
			TestISODateTimeFormatParsingTest_basicTimeNoMillisAdapter adapter, String string1, String string2,
			String string3, String string4, String string5, String string6) {
		DateTimeZone.setDefault(DateTimeZone.UTC);
		DateTimeFormatter parser = adapter.basicTimeNoMillis();
		assertParse(parser, string1, new DateTime(1970, 1, 1, 10, 20, 30, 0));
		assertParse(parser, false, string2);
		assertParse(parser, false, string3);
		assertParse(parser, false, string4);
		assertParse(parser, false, string5);
		assertParse(parser, false, string6);
	}

	interface TestISODateTimeFormatParsingTest_basicTimeNoMillisAdapter {
		DateTimeFormatter basicTimeNoMillis();
	}

	class TestISODateTimeFormatParsingTest_basicTimeNoMillisAdapterImpl
			implements TestISODateTimeFormatParsingTest_basicTimeNoMillisAdapter {
		public DateTimeFormatter basicTimeNoMillis() {
			return ISODateTimeFormat.basicTimeNoMillis();
		}
	}

	class TestISODateTimeFormatParsingTest_basicTTimeNoMillisAdapterImpl
			implements TestISODateTimeFormatParsingTest_basicTimeNoMillisAdapter {
		public DateTimeFormatter basicTimeNoMillis() {
			return ISODateTimeFormat.basicTTimeNoMillis();
		}
	}

	public void testISODateTimeFormatParsingNoMillisTemplate(TestISODateTimeFormatParsingNoMillisAdapter adapter,
			String string1, String string2, String string3, String string4, String string5, String string6) {
		DateTimeZone.setDefault(DateTimeZone.UTC);
		DateTimeFormatter parser = adapter.timeNoMillis();
		assertParse(parser, string1, new DateTime(1970, 1, 1, 10, 20, 30, 0));
		assertParse(parser, string2, new DateTime(1970, 1, 1, 5, 6, 7, 0));
		assertParse(parser, false, string3);
		assertParse(parser, false, string4);
		assertParse(parser, false, string5);
		assertParse(parser, false, string6);
	}

	interface TestISODateTimeFormatParsingNoMillisAdapter {
		DateTimeFormatter timeNoMillis();
	}

	class TestISODateTimeFormatParsingTest_timeNoMillisAdapterImpl
			implements TestISODateTimeFormatParsingNoMillisAdapter {
		public DateTimeFormatter timeNoMillis() {
			return ISODateTimeFormat.timeNoMillis();
		}
	}

	class TestISODateTimeFormatParsingTest_tTimeNoMillisAdapterImpl
			implements TestISODateTimeFormatParsingNoMillisAdapter {
		public DateTimeFormatter timeNoMillis() {
			return ISODateTimeFormat.tTimeNoMillis();
		}
	}

}
