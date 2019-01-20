/*
 *  Copyright 2001-2005 Stephen Colebourne
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

import java.util.Locale;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * This class is a Junit unit test for Partial.
 *
 * @author Stephen Colebourne
 */
public class TestPartial_Properties extends TestCase {

    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    
    private DateTimeZone zone = null;
    private static final DateTimeFieldType[] TYPES = new DateTimeFieldType[] {
        DateTimeFieldType.hourOfDay(),
        DateTimeFieldType.minuteOfHour(),
        DateTimeFieldType.secondOfMinute(),
        DateTimeFieldType.millisOfSecond()
    };
    private static final int[] VALUES = new int[] {10, 20, 30, 40};
    private static final int[] VALUES1 = new int[] {1, 2, 3, 4};
    private static final int[] VALUES2 = new int[] {5, 6, 7, 8};

//    private long TEST_TIME_NOW =
//        10L * DateTimeConstants.MILLIS_PER_HOUR
//        + 20L * DateTimeConstants.MILLIS_PER_MINUTE
//        + 30L * DateTimeConstants.MILLIS_PER_SECOND
//        + 40L;
//        
    private long TEST_TIME1 =
        1L * DateTimeConstants.MILLIS_PER_HOUR
        + 2L * DateTimeConstants.MILLIS_PER_MINUTE
        + 3L * DateTimeConstants.MILLIS_PER_SECOND
        + 4L;
    private long TEST_TIME2 =
        1L * DateTimeConstants.MILLIS_PER_DAY
        + 5L * DateTimeConstants.MILLIS_PER_HOUR
        + 6L * DateTimeConstants.MILLIS_PER_MINUTE
        + 7L * DateTimeConstants.MILLIS_PER_SECOND
        + 8L;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestPartial_Properties.class);
    }

    public TestPartial_Properties(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        zone = DateTimeZone.getDefault();
        DateTimeZone.setDefault(DateTimeZone.UTC);
    }

    protected void tearDown() throws Exception {
        DateTimeZone.setDefault(zone);
        zone = null;
    }

    public void testPropertyGetHour() {
		this.testPartial_PropertiesTestPropertyGetTemplate(new TestPartial_PropertiesTestPropertyGetHourAdapterImpl(),
				"hourOfDay", "Property[hourOfDay]", 10, "10", "10", "10", "10", "10");
	}

    public void testPropertyGetMaxMinValuesHour() {
		this.testPartial_PropertiesTestPropertyGetMaxMinValuesTemplate(
				new TestPartial_PropertiesTestPropertyGetMaxMinValuesHourAdapterImpl(), 23, 23);
	}

//    public void testPropertyAddHour() {
//        Partial test = new Partial(TYPES, VALUES);
//        Partial copy = test.property(DateTimeFieldType.hourOfDay()).addToCopy(9);
//        check(test, 10, 20, 30, 40);
//        check(copy, 19, 20, 30, 40);
//        
//        copy = test.property(DateTimeFieldType.hourOfDay()).addToCopy(0);
//        check(copy, 10, 20, 30, 40);
//        
//        copy = test.property(DateTimeFieldType.hourOfDay()).addToCopy(13);
//        check(copy, 23, 20, 30, 40);
//        
//        copy = test.property(DateTimeFieldType.hourOfDay()).addToCopy(14);
//        check(copy, 0, 20, 30, 40);
//        
//        copy = test.property(DateTimeFieldType.hourOfDay()).addToCopy(-10);
//        check(copy, 0, 20, 30, 40);
//        
//        copy = test.property(DateTimeFieldType.hourOfDay()).addToCopy(-11);
//        check(copy, 23, 20, 30, 40);
//    }
//
    public void testPropertyAddHour() {
        Partial test = new Partial(TYPES, VALUES);
        Partial copy = test.property(DateTimeFieldType.hourOfDay()).addToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 19, 20, 30, 40);
        
        copy = test.property(DateTimeFieldType.hourOfDay()).addToCopy(0);
        check(copy, 10, 20, 30, 40);
        
        copy = test.property(DateTimeFieldType.hourOfDay()).addToCopy(13);
        check(copy, 23, 20, 30, 40);
        
        try {
            test.property(DateTimeFieldType.hourOfDay()).addToCopy(14);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20, 30, 40);
        
        copy = test.property(DateTimeFieldType.hourOfDay()).addToCopy(-10);
        check(copy, 0, 20, 30, 40);
        
        try {
            test.property(DateTimeFieldType.hourOfDay()).addToCopy(-11);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20, 30, 40);
    }

    public void testPropertyAddWrapFieldHour() {
        Partial test = new Partial(TYPES, VALUES);
        Partial copy = test.property(DateTimeFieldType.hourOfDay()).addWrapFieldToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 19, 20, 30, 40);
        
        copy = test.property(DateTimeFieldType.hourOfDay()).addWrapFieldToCopy(0);
        check(copy, 10, 20, 30, 40);
        
        copy = test.property(DateTimeFieldType.hourOfDay()).addWrapFieldToCopy(18);
        check(copy, 4, 20, 30, 40);
        
        copy = test.property(DateTimeFieldType.hourOfDay()).addWrapFieldToCopy(-15);
        check(copy, 19, 20, 30, 40);
    }

    public void testPropertySetHour() {
        Partial test = new Partial(TYPES, VALUES);
        Partial copy = test.property(DateTimeFieldType.hourOfDay()).setCopy(12);
        check(test, 10, 20, 30, 40);
        check(copy, 12, 20, 30, 40);
        
        try {
            test.property(DateTimeFieldType.hourOfDay()).setCopy(24);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            test.property(DateTimeFieldType.hourOfDay()).setCopy(-1);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testPropertySetTextHour() {
        Partial test = new Partial(TYPES, VALUES);
        Partial copy = test.property(DateTimeFieldType.hourOfDay()).setCopy("12");
        check(test, 10, 20, 30, 40);
        check(copy, 12, 20, 30, 40);
    }

    public void testPropertyWithMaximumValueHour() {
        Partial test = new Partial(TYPES, VALUES);
        Partial copy = test.property(DateTimeFieldType.hourOfDay()).withMaximumValue();
        check(test, 10, 20, 30, 40);
        check(copy, 23, 20, 30, 40);
    }

    public void testPropertyWithMinimumValueHour() {
        Partial test = new Partial(TYPES, VALUES);
        Partial copy = test.property(DateTimeFieldType.hourOfDay()).withMinimumValue();
        check(test, 10, 20, 30, 40);
        check(copy, 0, 20, 30, 40);
    }

    public void testPropertyCompareToHour() {
		this.testPartial_PropertiesTestPropertyCompareToTemplate(
				new TestPartial_PropertiesTestPropertyCompareToHourAdapterImpl());
	}

    public void testPropertyGetMinute() {
		this.testPartial_PropertiesTestPropertyGetTemplate(new TestPartial_PropertiesTestPropertyGetMinuteAdapterImpl(),
				"minuteOfHour", "Property[minuteOfHour]", 20, "20", "20", "20", "20", "20");
	}

    public void testPropertyGetMaxMinValuesMinute() {
		this.testPartial_PropertiesTestPropertyGetMaxMinValuesTemplate(
				new TestPartial_PropertiesTestPropertyGetMaxMinValuesMinuteAdapterImpl(), 59, 59);
	}

//    public void testPropertyAddMinute() {
//        Partial test = new Partial(TYPES, VALUES);
//        Partial copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(9);
//        check(test, 10, 20, 30, 40);
//        check(copy, 10, 29, 30, 40);
//        
//        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(39);
//        check(copy, 10, 59, 30, 40);
//        
//        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(40);
//        check(copy, 11, 0, 30, 40);
//        
//        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(1 * 60 + 45);
//        check(copy, 12, 5, 30, 40);
//        
//        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(13 * 60 + 39);
//        check(copy, 23, 59, 30, 40);
//        
//        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(13 * 60 + 40);
//        check(copy, 0, 0, 30, 40);
//        
//        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(-9);
//        check(copy, 10, 11, 30, 40);
//        
//        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(-19);
//        check(copy, 10, 1, 30, 40);
//        
//        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(-20);
//        check(copy, 10, 0, 30, 40);
//        
//        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(-21);
//        check(copy, 9, 59, 30, 40);
//        
//        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(-(10 * 60 + 20));
//        check(copy, 0, 0, 30, 40);
//        
//        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(-(10 * 60 + 21));
//        check(copy, 23, 59, 30, 40);
//    }

    public void testPropertyAddMinute() {
        Partial test = new Partial(TYPES, VALUES);
        Partial copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 29, 30, 40);
        
        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(39);
        check(copy, 10, 59, 30, 40);
        
        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(40);
        check(copy, 11, 0, 30, 40);
        
        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(1 * 60 + 45);
        check(copy, 12, 5, 30, 40);
        
        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(13 * 60 + 39);
        check(copy, 23, 59, 30, 40);
        
        try {
            test.property(DateTimeFieldType.minuteOfHour()).addToCopy(13 * 60 + 40);
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20, 30, 40);
        
        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(-9);
        check(copy, 10, 11, 30, 40);
        
        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(-19);
        check(copy, 10, 1, 30, 40);
        
        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(-20);
        check(copy, 10, 0, 30, 40);
        
        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(-21);
        check(copy, 9, 59, 30, 40);
        
        copy = test.property(DateTimeFieldType.minuteOfHour()).addToCopy(-(10 * 60 + 20));
        check(copy, 0, 0, 30, 40);
        
        try {
            test.property(DateTimeFieldType.minuteOfHour()).addToCopy(-(10 * 60 + 21));
            fail();
        } catch (IllegalArgumentException ex) {}
        check(test, 10, 20, 30, 40);
    }

    public void testPropertyAddWrapFieldMinute() {
        Partial test = new Partial(TYPES, VALUES);
        Partial copy = test.property(DateTimeFieldType.minuteOfHour()).addWrapFieldToCopy(9);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 29, 30, 40);
        
        copy = test.property(DateTimeFieldType.minuteOfHour()).addWrapFieldToCopy(49);
        check(copy, 10, 9, 30, 40);
        
        copy = test.property(DateTimeFieldType.minuteOfHour()).addWrapFieldToCopy(-47);
        check(copy, 10, 33, 30, 40);
    }

    public void testPropertySetMinute() {
        Partial test = new Partial(TYPES, VALUES);
        Partial copy = test.property(DateTimeFieldType.minuteOfHour()).setCopy(12);
        check(test, 10, 20, 30, 40);
        check(copy, 10, 12, 30, 40);
        
        try {
            test.property(DateTimeFieldType.minuteOfHour()).setCopy(60);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            test.property(DateTimeFieldType.minuteOfHour()).setCopy(-1);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testPropertySetTextMinute() {
        Partial test = new Partial(TYPES, VALUES);
        Partial copy = test.property(DateTimeFieldType.minuteOfHour()).setCopy("12");
        check(test, 10, 20, 30, 40);
        check(copy, 10, 12, 30, 40);
    }

    public void testPropertyCompareToMinute() {
		this.testPartial_PropertiesTestPropertyCompareToTemplate(
				new TestPartial_PropertiesTestPropertyCompareToMinuteAdapterImpl());
	}

    //-----------------------------------------------------------------------
    private void check(Partial test, int hour, int min, int sec, int milli) {
        assertEquals(hour, test.get(DateTimeFieldType.hourOfDay()));
        assertEquals(min, test.get(DateTimeFieldType.minuteOfHour()));
        assertEquals(sec, test.get(DateTimeFieldType.secondOfMinute()));
        assertEquals(milli, test.get(DateTimeFieldType.millisOfSecond()));
    }

	public void testPartial_PropertiesTestPropertyCompareToTemplate(
			TestPartial_PropertiesTestPropertyCompareToAdapter adapter) {
		Partial test1 = new Partial(TYPES, VALUES1);
		Partial test2 = new Partial(TYPES, VALUES2);
		assertEquals(true, test1.property(adapter.ofHour()).compareTo(test2) < 0);
		assertEquals(true, test2.property(adapter.ofHour()).compareTo(test1) > 0);
		assertEquals(true, test1.property(adapter.ofHour()).compareTo(test1) == 0);
		try {
			test1.property(adapter.ofHour()).compareTo((ReadablePartial) null);
			fail();
		} catch (IllegalArgumentException ex) {
		}
		DateTime dt1 = new DateTime(TEST_TIME1);
		DateTime dt2 = new DateTime(TEST_TIME2);
		assertEquals(true, test1.property(adapter.ofHour()).compareTo(dt2) < 0);
		assertEquals(true, test2.property(adapter.ofHour()).compareTo(dt1) > 0);
		assertEquals(true, test1.property(adapter.ofHour()).compareTo(dt1) == 0);
		try {
			test1.property(adapter.ofHour()).compareTo((ReadableInstant) null);
			fail();
		} catch (IllegalArgumentException ex) {
		}
	}

	interface TestPartial_PropertiesTestPropertyCompareToAdapter {
		DateTimeFieldType ofHour();
	}

	class TestPartial_PropertiesTestPropertyCompareToHourAdapterImpl
			implements TestPartial_PropertiesTestPropertyCompareToAdapter {
		public DateTimeFieldType ofHour() {
			return DateTimeFieldType.hourOfDay();
		}
	}

	class TestPartial_PropertiesTestPropertyCompareToMinuteAdapterImpl
			implements TestPartial_PropertiesTestPropertyCompareToAdapter {
		public DateTimeFieldType ofHour() {
			return DateTimeFieldType.minuteOfHour();
		}
	}

	public void testPartial_PropertiesTestPropertyGetTemplate(TestPartial_PropertiesTestPropertyGetAdapter adapter,
			String string1, String string2, int i1, String string3, String string4, String string5, String string6,
			String string7) {
		Partial test = new Partial(TYPES, VALUES);
		assertSame(adapter.ofHour(test.getChronology()), test.property(adapter.ofHour1()).getField());
		assertEquals(string1, test.property(adapter.ofHour1()).getName());
		assertEquals(string2, test.property(adapter.ofHour1()).toString());
		assertSame(test, test.property(adapter.ofHour1()).getReadablePartial());
		assertSame(test, test.property(adapter.ofHour1()).getPartial());
		assertEquals(i1, test.property(adapter.ofHour1()).get());
		assertEquals(string3, test.property(adapter.ofHour1()).getAsString());
		assertEquals(string4, test.property(adapter.ofHour1()).getAsText());
		assertEquals(string5, test.property(adapter.ofHour1()).getAsText(Locale.FRENCH));
		assertEquals(string6, test.property(adapter.ofHour1()).getAsShortText());
		assertEquals(string7, test.property(adapter.ofHour1()).getAsShortText(Locale.FRENCH));
		assertEquals(adapter.action1(test.getChronology()), test.property(adapter.ofHour1()).getDurationField());
		assertEquals(adapter.action2(test.getChronology()), test.property(adapter.ofHour1()).getRangeDurationField());
		assertEquals(2, test.property(adapter.ofHour1()).getMaximumTextLength(null));
		assertEquals(2, test.property(adapter.ofHour1()).getMaximumShortTextLength(null));
	}

	interface TestPartial_PropertiesTestPropertyGetAdapter {
		DateTimeField ofHour(Chronology chronology1);

		DateTimeFieldType ofHour1();

		DurationField action1(Chronology chronology1);

		DurationField action2(Chronology chronology1);
	}

	class TestPartial_PropertiesTestPropertyGetHourAdapterImpl implements TestPartial_PropertiesTestPropertyGetAdapter {
		public DateTimeField ofHour(Chronology chronology1) {
			return chronology1.hourOfDay();
		}

		public DateTimeFieldType ofHour1() {
			return DateTimeFieldType.hourOfDay();
		}

		public DurationField action1(Chronology chronology1) {
			return chronology1.hours();
		}

		public DurationField action2(Chronology chronology1) {
			return chronology1.days();
		}
	}

	class TestPartial_PropertiesTestPropertyGetMinuteAdapterImpl
			implements TestPartial_PropertiesTestPropertyGetAdapter {
		public DateTimeField ofHour(Chronology chronology1) {
			return chronology1.minuteOfHour();
		}

		public DateTimeFieldType ofHour1() {
			return DateTimeFieldType.minuteOfHour();
		}

		public DurationField action1(Chronology chronology1) {
			return chronology1.minutes();
		}

		public DurationField action2(Chronology chronology1) {
			return chronology1.hours();
		}
	}

	public void testPartial_PropertiesTestPropertyGetMaxMinValuesTemplate(
			TestPartial_PropertiesTestPropertyGetMaxMinValuesAdapter adapter, int i1, int i2) {
		Partial test = new Partial(TYPES, VALUES);
		assertEquals(0, test.property(adapter.ofHour()).getMinimumValue());
		assertEquals(0, test.property(adapter.ofHour()).getMinimumValueOverall());
		assertEquals(i1, test.property(adapter.ofHour()).getMaximumValue());
		assertEquals(i2, test.property(adapter.ofHour()).getMaximumValueOverall());
	}

	interface TestPartial_PropertiesTestPropertyGetMaxMinValuesAdapter {
		DateTimeFieldType ofHour();
	}

	class TestPartial_PropertiesTestPropertyGetMaxMinValuesHourAdapterImpl
			implements TestPartial_PropertiesTestPropertyGetMaxMinValuesAdapter {
		public DateTimeFieldType ofHour() {
			return DateTimeFieldType.hourOfDay();
		}
	}

	class TestPartial_PropertiesTestPropertyGetMaxMinValuesMinuteAdapterImpl
			implements TestPartial_PropertiesTestPropertyGetMaxMinValuesAdapter {
		public DateTimeFieldType ofHour() {
			return DateTimeFieldType.minuteOfHour();
		}
	}
}
