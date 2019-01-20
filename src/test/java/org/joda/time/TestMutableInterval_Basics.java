/*
 *  Copyright 2001-2011 Stephen Colebourne
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
package org.joda.time;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.TimeZone;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.base.AbstractInterval;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.ISOChronology;

/**
 * This class is a Junit unit test for Instant.
 *
 * @author Stephen Colebourne
 */
public class TestMutableInterval_Basics extends TestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)

    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final Chronology COPTIC_PARIS = CopticChronology.getInstance(PARIS);
    
    long y2002days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
                     366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
                     365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
                     366 + 365;
    long y2003days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
                     366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
                     365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
                     366 + 365 + 365;
    
    // 2002-06-09
    private long TEST_TIME_NOW =
            (y2002days + 31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;
            
    // 2002-04-05
    private long TEST_TIME1 =
            (y2002days + 31L + 28L + 31L + 5L -1L) * DateTimeConstants.MILLIS_PER_DAY
            + 12L * DateTimeConstants.MILLIS_PER_HOUR
            + 24L * DateTimeConstants.MILLIS_PER_MINUTE;
        
    // 2003-05-06
    private long TEST_TIME2 =
            (y2003days + 31L + 28L + 31L + 30L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
            + 14L * DateTimeConstants.MILLIS_PER_HOUR
            + 28L * DateTimeConstants.MILLIS_PER_MINUTE;
        
    private DateTimeZone originalDateTimeZone = null;
    private TimeZone originalTimeZone = null;
    private Locale originalLocale = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestMutableInterval_Basics.class);
    }

    public TestMutableInterval_Basics(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        originalDateTimeZone = DateTimeZone.getDefault();
        originalTimeZone = TimeZone.getDefault();
        originalLocale = Locale.getDefault();
        DateTimeZone.setDefault(LONDON);
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
        Locale.setDefault(Locale.UK);
    }

    protected void tearDown() throws Exception {
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(originalDateTimeZone);
        TimeZone.setDefault(originalTimeZone);
        Locale.setDefault(originalLocale);
        originalDateTimeZone = null;
        originalTimeZone = null;
        originalLocale = null;
    }

    //-----------------------------------------------------------------------
    public void testTest() {
        assertEquals("2002-06-09T00:00:00.000Z", new Instant(TEST_TIME_NOW).toString());
        assertEquals("2002-04-05T12:24:00.000Z", new Instant(TEST_TIME1).toString());
        assertEquals("2003-05-06T14:28:00.000Z", new Instant(TEST_TIME2).toString());
    }

    //-----------------------------------------------------------------------
    public void testGetMillis() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertEquals(TEST_TIME1, test.getStartMillis());
        assertEquals(TEST_TIME1, test.getStart().getMillis());
        assertEquals(TEST_TIME2, test.getEndMillis());
        assertEquals(TEST_TIME2, test.getEnd().getMillis());
        assertEquals(TEST_TIME2 - TEST_TIME1, test.toDurationMillis());
        assertEquals(TEST_TIME2 - TEST_TIME1, test.toDuration().getMillis());
    }

    public void testGetDuration1() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertEquals(TEST_TIME2 - TEST_TIME1, test.toDurationMillis());
        assertEquals(TEST_TIME2 - TEST_TIME1, test.toDuration().getMillis());
    }

    public void testGetDuration2() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME1);
        assertSame(Duration.ZERO, test.toDuration());
    }

    public void testEqualsHashCode() {
        MutableInterval test1 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        MutableInterval test2 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertEquals(true, test1.equals(test2));
        assertEquals(true, test2.equals(test1));
        assertEquals(true, test1.equals(test1));
        assertEquals(true, test2.equals(test2));
        assertEquals(true, test1.hashCode() == test2.hashCode());
        assertEquals(true, test1.hashCode() == test1.hashCode());
        assertEquals(true, test2.hashCode() == test2.hashCode());
        
        MutableInterval test3 = new MutableInterval(TEST_TIME_NOW, TEST_TIME2);
        assertEquals(false, test1.equals(test3));
        assertEquals(false, test2.equals(test3));
        assertEquals(false, test3.equals(test1));
        assertEquals(false, test3.equals(test2));
        assertEquals(false, test1.hashCode() == test3.hashCode());
        assertEquals(false, test2.hashCode() == test3.hashCode());
        
        MutableInterval test4 = new MutableInterval(TEST_TIME1, TEST_TIME2, GJChronology.getInstance());
        assertEquals(true, test4.equals(test4));
        assertEquals(false, test1.equals(test4));
        assertEquals(false, test2.equals(test4));
        assertEquals(false, test4.equals(test1));
        assertEquals(false, test4.equals(test2));
        assertEquals(false, test1.hashCode() == test4.hashCode());
        assertEquals(false, test2.hashCode() == test4.hashCode());
        
        MutableInterval test5 = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertEquals(true, test1.equals(test5));
        assertEquals(true, test2.equals(test5));
        assertEquals(false, test3.equals(test5));
        assertEquals(true, test5.equals(test1));
        assertEquals(true, test5.equals(test2));
        assertEquals(false, test5.equals(test3));
        assertEquals(true, test1.hashCode() == test5.hashCode());
        assertEquals(true, test2.hashCode() == test5.hashCode());
        assertEquals(false, test3.hashCode() == test5.hashCode());
        
        assertEquals(false, test1.equals("Hello"));
        assertEquals(true, test1.equals(new MockInterval()));
        assertEquals(false, test1.equals(new DateTime(TEST_TIME1)));
    }
    
    class MockInterval extends AbstractInterval {
        public MockInterval() {
            super();
        }
        public Chronology getChronology() {
            return ISOChronology.getInstance();
        }
        public long getStartMillis() {
            return TEST_TIME1;
        }
        public long getEndMillis() {
            return TEST_TIME2;
        }
    }

    //-----------------------------------------------------------------------
    public void testContains_long() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertEquals(true, test.contains(TEST_TIME1));
        assertEquals(false, test.contains(TEST_TIME1 - 1));
        assertEquals(true, test.contains(TEST_TIME1 + (TEST_TIME2 - TEST_TIME1) / 2));
        assertEquals(false, test.contains(TEST_TIME2));
        assertEquals(true, test.contains(TEST_TIME2 - 1));
    }

    public void testContainsNow() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1);
        assertEquals(true, test.containsNow());
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1 - 1);
        assertEquals(false, test.containsNow());
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME1 + (TEST_TIME2 - TEST_TIME1) / 2);
        assertEquals(true, test.containsNow());
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME2);
        assertEquals(false, test.containsNow());
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME2 - 1);
        assertEquals(true, test.containsNow());
    }

    public void testContains_RI() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        assertEquals(true, test.contains(new Instant(TEST_TIME1)));
        assertEquals(false, test.contains(new Instant(TEST_TIME1 - 1)));
        assertEquals(true, test.contains(new Instant(TEST_TIME1 + (TEST_TIME2 - TEST_TIME1) / 2)));
        assertEquals(false, test.contains(new Instant(TEST_TIME2)));
        assertEquals(true, test.contains(new Instant(TEST_TIME2 - 1)));
        assertEquals(true, test.contains((ReadableInstant) null));
    }

    //-----------------------------------------------------------------------
    public void testContains_RInterval() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        assertEquals(true, test.contains(new Interval(TEST_TIME1, TEST_TIME1)));
        assertEquals(false, test.contains(new Interval(TEST_TIME1 - 1, TEST_TIME1)));
        
        assertEquals(true, test.contains(new Interval(TEST_TIME1, TEST_TIME1 + 1)));
        assertEquals(false, test.contains(new Interval(TEST_TIME1 - 1, TEST_TIME1 + 1)));
        assertEquals(true, test.contains(new Interval(TEST_TIME1 + 1, TEST_TIME1 + 1)));
        
        assertEquals(true, test.contains(new Interval(TEST_TIME1, TEST_TIME2)));
        assertEquals(false, test.contains(new Interval(TEST_TIME1 - 1, TEST_TIME2)));
        assertEquals(true, test.contains(new Interval(TEST_TIME1 + (TEST_TIME2 - TEST_TIME1) / 2, TEST_TIME2)));
        assertEquals(false, test.contains(new Interval(TEST_TIME2, TEST_TIME2)));
        assertEquals(true, test.contains(new Interval(TEST_TIME2 - 1, TEST_TIME2)));
        
        assertEquals(true, test.contains(new Interval(TEST_TIME1, TEST_TIME2 - 1)));
        assertEquals(false, test.contains(new Interval(TEST_TIME1 - 1, TEST_TIME2 - 1)));
        assertEquals(true, test.contains(new Interval(TEST_TIME1 + (TEST_TIME2 - TEST_TIME1) / 2, TEST_TIME2 - 1)));
        assertEquals(true, test.contains(new Interval(TEST_TIME2 - 1, TEST_TIME2 - 1)));
        assertEquals(true, test.contains(new Interval(TEST_TIME2 - 2, TEST_TIME2 - 1)));
        
        assertEquals(false, test.contains(new Interval(TEST_TIME1, TEST_TIME2 + 1)));
        assertEquals(false, test.contains(new Interval(TEST_TIME1 - 1, TEST_TIME2 + 1)));
        assertEquals(false, test.contains(new Interval(TEST_TIME1 + (TEST_TIME2 - TEST_TIME1) / 2, TEST_TIME2 + 1)));
        assertEquals(false, test.contains(new Interval(TEST_TIME2, TEST_TIME2 + 1)));
        assertEquals(false, test.contains(new Interval(TEST_TIME2 - 1, TEST_TIME2 + 1)));
        assertEquals(false, test.contains(new Interval(TEST_TIME1 - 2, TEST_TIME1 - 1)));
        
        assertEquals(true, test.contains((ReadableInterval) null));
    }

    public void testOverlaps_RInterval() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        assertEquals(false, test.overlaps(new Interval(TEST_TIME1, TEST_TIME1)));
        assertEquals(false, test.overlaps(new Interval(TEST_TIME1 - 1, TEST_TIME1)));
        
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1, TEST_TIME1 + 1)));
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1 - 1, TEST_TIME1 + 1)));
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1 + 1, TEST_TIME1 + 1)));
        
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1, TEST_TIME2)));
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1 - 1, TEST_TIME2)));
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1 + (TEST_TIME2 - TEST_TIME1) / 2, TEST_TIME2)));
        assertEquals(false, test.overlaps(new Interval(TEST_TIME2, TEST_TIME2)));
        assertEquals(true, test.overlaps(new Interval(TEST_TIME2 - 1, TEST_TIME2)));
        
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1, TEST_TIME2 + 1)));
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1 - 1, TEST_TIME2 + 1)));
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1 + (TEST_TIME2 - TEST_TIME1) / 2, TEST_TIME2 + 1)));
        assertEquals(false, test.overlaps(new Interval(TEST_TIME2, TEST_TIME2 + 1)));
        assertEquals(true, test.overlaps(new Interval(TEST_TIME2 - 1, TEST_TIME2 + 1)));
        
        assertEquals(false, test.overlaps(new Interval(TEST_TIME1 - 1, TEST_TIME1 - 1)));
        assertEquals(false, test.overlaps(new Interval(TEST_TIME1 - 1, TEST_TIME1)));
        assertEquals(true, test.overlaps(new Interval(TEST_TIME1 - 1, TEST_TIME1 + 1)));
        
        assertEquals(true, test.overlaps((ReadableInterval) null));
        
        MutableInterval empty = new MutableInterval(TEST_TIME1, TEST_TIME1);
        assertEquals(false, empty.overlaps(empty));
        assertEquals(false, empty.overlaps(test));
        assertEquals(false, test.overlaps(empty));
    }

    public void testIsBefore_long() {
		this.testMutableInterval_BasicsTestIsTemplate(new TestMutableInterval_BasicsTestIsBefore_longAdapterImpl(),
				false, true, true);
	}

    public void testIsBeforeNow() {
		this.testMutableInterval_BasicsTestIsNowTemplate(new TestMutableInterval_BasicsTestIsBeforeNowAdapterImpl(),
				TEST_TIME2, false, TEST_TIME2, true, TEST_TIME2, true);
	}

    public void testIsBefore_RI() {
		this.testMutableInterval_BasicsTestIsRITemplate(new TestMutableInterval_BasicsTestIsBefore_RIAdapterImpl(),
				false, true, true);
	}

    public void testIsBefore_RInterval() {
		this.testMutableInterval_BasicsTestIsRIntervalTemplate(
				new TestMutableInterval_BasicsTestIsBefore_RIntervalAdapterImpl(), false, false, true, true);
	}

    public void testIsAfter_long() {
		this.testMutableInterval_BasicsTestIsTemplate(new TestMutableInterval_BasicsTestIsAfter_longAdapterImpl(), true,
				false, false);
	}

    public void testIsAfterNow() {
		this.testMutableInterval_BasicsTestIsNowTemplate(new TestMutableInterval_BasicsTestIsAfterNowAdapterImpl(),
				TEST_TIME1, true, TEST_TIME1, false, TEST_TIME1, false);
	}

    public void testIsAfter_RI() {
		this.testMutableInterval_BasicsTestIsRITemplate(new TestMutableInterval_BasicsTestIsAfter_RIAdapterImpl(), true,
				false, false);
	}

    public void testIsAfter_RInterval() {
		this.testMutableInterval_BasicsTestIsRIntervalTemplate(
				new TestMutableInterval_BasicsTestIsAfter_RIntervalAdapterImpl(), true, true, false, false);
	}

    //-----------------------------------------------------------------------
    public void testToInterval1() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        Interval result = test.toInterval();
        assertEquals(test, result);
    }

    //-----------------------------------------------------------------------
    public void testToMutableInterval1() {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2, COPTIC_PARIS);
        MutableInterval result = test.toMutableInterval();
        assertEquals(test, result);
        assertNotSame(test, result);
    }

    //-----------------------------------------------------------------------
    public void testToPeriod() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10, COPTIC_PARIS);
        DateTime dt2 = new DateTime(2005, 8, 13, 12, 14, 16, 18, COPTIC_PARIS);
        MutableInterval base = new MutableInterval(dt1, dt2);
        
        Period test = base.toPeriod();
        Period expected = new Period(dt1, dt2, PeriodType.standard());
        assertEquals(expected, test);
    }

    //-----------------------------------------------------------------------
    public void testToPeriod_PeriodType1() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10, COPTIC_PARIS);
        DateTime dt2 = new DateTime(2005, 8, 13, 12, 14, 16, 18, COPTIC_PARIS);
        MutableInterval base = new MutableInterval(dt1, dt2);
        
        Period test = base.toPeriod(null);
        Period expected = new Period(dt1, dt2, PeriodType.standard());
        assertEquals(expected, test);
    }

    public void testToPeriod_PeriodType2() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10);
        DateTime dt2 = new DateTime(2005, 8, 13, 12, 14, 16, 18);
        MutableInterval base = new MutableInterval(dt1, dt2);
        
        Period test = base.toPeriod(PeriodType.yearWeekDayTime());
        Period expected = new Period(dt1, dt2, PeriodType.yearWeekDayTime());
        assertEquals(expected, test);
    }

    //-----------------------------------------------------------------------
    public void testSerialization() throws Exception {
        MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        MutableInterval result = (MutableInterval) ois.readObject();
        ois.close();
        
        assertEquals(test, result);
    }

    //-----------------------------------------------------------------------
    public void testToString() {
        DateTime dt1 = new DateTime(2004, 6, 9, 7, 8, 9, 10, DateTimeZone.UTC);
        DateTime dt2 = new DateTime(2005, 8, 13, 12, 14, 16, 18, DateTimeZone.UTC);
        MutableInterval test = new MutableInterval(dt1, dt2);
        assertEquals("2004-06-09T07:08:09.010Z/2005-08-13T12:14:16.018Z", test.toString());
    }

    //-----------------------------------------------------------------------
    public void testCopy() {
        MutableInterval test = new MutableInterval(123L, 456L, COPTIC_PARIS);
        MutableInterval cloned = test.copy();
        assertEquals(test, cloned);
        assertNotSame(test, cloned);
    }
    public void testClone() {
        MutableInterval test = new MutableInterval(123L, 456L, COPTIC_PARIS);
        MutableInterval cloned = (MutableInterval) test.clone();
        assertEquals(test, cloned);
        assertNotSame(test, cloned);
    }

	public void testMutableInterval_BasicsTestIsRITemplate(TestMutableInterval_BasicsTestIsRIAdapter adapter,
			boolean b1, boolean b2, boolean b3) {
		MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
		assertEquals(b1, adapter.is(test, new Instant(TEST_TIME1 - 1)));
		assertEquals(false, adapter.is(test, new Instant(TEST_TIME1)));
		assertEquals(false, adapter.is(test, new Instant(TEST_TIME1 + 1)));
		assertEquals(false, adapter.is(test, new Instant(TEST_TIME2 - 1)));
		assertEquals(b2, adapter.is(test, new Instant(TEST_TIME2)));
		assertEquals(b3, adapter.is(test, new Instant(TEST_TIME2 + 1)));
		assertEquals(false, adapter.is(test, (ReadableInstant) null));
	}

	interface TestMutableInterval_BasicsTestIsRIAdapter {
		boolean is(MutableInterval mutableInterval1, ReadableInstant readableInstant1);
	}

	class TestMutableInterval_BasicsTestIsBefore_RIAdapterImpl implements TestMutableInterval_BasicsTestIsRIAdapter {
		public boolean is(MutableInterval test, ReadableInstant readableInstant1) {
			return test.isBefore(readableInstant1);
		}
	}

	class TestMutableInterval_BasicsTestIsAfter_RIAdapterImpl implements TestMutableInterval_BasicsTestIsRIAdapter {
		public boolean is(MutableInterval test, ReadableInstant readableInstant1) {
			return test.isAfter(readableInstant1);
		}
	}

	public void testMutableInterval_BasicsTestIsRIntervalTemplate(
			TestMutableInterval_BasicsTestIsRIntervalAdapter adapter, boolean b1, boolean b2, boolean b3, boolean b4) {
		MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
		assertEquals(b1, adapter.is(test, new Interval(Long.MIN_VALUE, TEST_TIME1 - 1)));
		assertEquals(b2, adapter.is(test, new Interval(Long.MIN_VALUE, TEST_TIME1)));
		assertEquals(false, adapter.is(test, new Interval(Long.MIN_VALUE, TEST_TIME1 + 1)));
		assertEquals(false, adapter.is(test, new Interval(TEST_TIME2 - 1, Long.MAX_VALUE)));
		assertEquals(b3, adapter.is(test, new Interval(TEST_TIME2, Long.MAX_VALUE)));
		assertEquals(b4, adapter.is(test, new Interval(TEST_TIME2 + 1, Long.MAX_VALUE)));
		assertEquals(false, adapter.is(test, (ReadableInterval) null));
	}

	interface TestMutableInterval_BasicsTestIsRIntervalAdapter {
		boolean is(MutableInterval mutableInterval1, ReadableInterval readableInterval1);
	}

	class TestMutableInterval_BasicsTestIsBefore_RIntervalAdapterImpl
			implements TestMutableInterval_BasicsTestIsRIntervalAdapter {
		public boolean is(MutableInterval test, ReadableInterval readableInterval1) {
			return test.isBefore(readableInterval1);
		}
	}

	class TestMutableInterval_BasicsTestIsAfter_RIntervalAdapterImpl
			implements TestMutableInterval_BasicsTestIsRIntervalAdapter {
		public boolean is(MutableInterval test, ReadableInterval readableInterval1) {
			return test.isAfter(readableInterval1);
		}
	}

	public void testMutableInterval_BasicsTestIsTemplate(TestMutableInterval_BasicsTestIsAdapter adapter, boolean b1,
			boolean b2, boolean b3) {
		MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
		assertEquals(b1, adapter.is(test, TEST_TIME1 - 1));
		assertEquals(false, adapter.is(test, TEST_TIME1));
		assertEquals(false, adapter.is(test, TEST_TIME1 + 1));
		assertEquals(false, adapter.is(test, TEST_TIME2 - 1));
		assertEquals(b2, adapter.is(test, TEST_TIME2));
		assertEquals(b3, adapter.is(test, TEST_TIME2 + 1));
	}

	interface TestMutableInterval_BasicsTestIsAdapter {
		boolean is(MutableInterval mutableInterval1, long l1);
	}

	class TestMutableInterval_BasicsTestIsBefore_longAdapterImpl implements TestMutableInterval_BasicsTestIsAdapter {
		public boolean is(MutableInterval test, long l1) {
			return test.isBefore(l1);
		}
	}

	class TestMutableInterval_BasicsTestIsAfter_longAdapterImpl implements TestMutableInterval_BasicsTestIsAdapter {
		public boolean is(MutableInterval test, long l1) {
			return test.isAfter(l1);
		}
	}

	public void testMutableInterval_BasicsTestIsNowTemplate(TestMutableInterval_BasicsTestIsNowAdapter adapter, long l1,
			boolean b1, long l2, boolean b2, long l3, boolean b3) {
		MutableInterval test = new MutableInterval(TEST_TIME1, TEST_TIME2);
		DateTimeUtils.setCurrentMillisFixed(l1 - 1);
		assertEquals(b1, adapter.isNow(test));
		DateTimeUtils.setCurrentMillisFixed(l2);
		assertEquals(b2, adapter.isNow(test));
		DateTimeUtils.setCurrentMillisFixed(l3 + 1);
		assertEquals(b3, adapter.isNow(test));
	}

	interface TestMutableInterval_BasicsTestIsNowAdapter {
		boolean isNow(MutableInterval mutableInterval1);
	}

	class TestMutableInterval_BasicsTestIsBeforeNowAdapterImpl implements TestMutableInterval_BasicsTestIsNowAdapter {
		public boolean isNow(MutableInterval test) {
			return test.isBeforeNow();
		}
	}

	class TestMutableInterval_BasicsTestIsAfterNowAdapterImpl implements TestMutableInterval_BasicsTestIsNowAdapter {
		public boolean isNow(MutableInterval test) {
			return test.isAfterNow();
		}
	}


}
