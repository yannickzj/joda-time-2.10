package org.joda.time;
import static org.joda.time.PeriodType.time;
import org.joda.time.chrono.ISOChronology;
import static org.joda.time.chrono.ISOChronology.getInstance;
import org.joda.time.base.BasePeriod;
import static junit.framework.Assert.assertEquals;
public class TestPeriod_ConstructorsTestConstructor_long_PeriodType_Chronology1Template {
  public static <TPeriod extends BasePeriod>void testPeriod_ConstructorsTestConstructor_long_PeriodType_Chronology1Template(  TestPeriod_ConstructorsTestConstructor_long_PeriodType_Chronology1Adapter<TPeriod> adapter,  Class<TPeriod> clazzTPeriod) throws Throwable {
    long length=4 * DateTimeConstants.MILLIS_PER_DAY + 5 * DateTimeConstants.MILLIS_PER_HOUR + 6 * DateTimeConstants.MILLIS_PER_MINUTE + 7 * DateTimeConstants.MILLIS_PER_SECOND + 8;
    TPeriod test=clazzTPeriod.getDeclaredConstructor(long.class,PeriodType.class,Chronology.class).newInstance(length,PeriodType.time().withMillisRemoved(),ISOChronology.getInstance());
    assertEquals(PeriodType.time().withMillisRemoved(),test.getPeriodType());
    assertEquals(0,adapter.getYears(test));
    assertEquals(0,adapter.getMonths(test));
    assertEquals(0,adapter.getWeeks(test));
    assertEquals(0,adapter.getDays(test));
    assertEquals((4 * 24) + 5,adapter.getHours(test));
    assertEquals(6,adapter.getMinutes(test));
    assertEquals(7,adapter.getSeconds(test));
    assertEquals(0,adapter.getMillis(test));
  }
}

interface TestPeriod_ConstructorsTestConstructor_long_PeriodType_Chronology1Adapter<TPeriod> {
	int getYears(TPeriod tPeriod1);

	int getMonths(TPeriod tPeriod1);

	int getWeeks(TPeriod tPeriod1);

	int getDays(TPeriod tPeriod1);

	int getHours(TPeriod tPeriod1);

	int getMinutes(TPeriod tPeriod1);

	int getSeconds(TPeriod tPeriod1);

	int getMillis(TPeriod tPeriod1);
}
