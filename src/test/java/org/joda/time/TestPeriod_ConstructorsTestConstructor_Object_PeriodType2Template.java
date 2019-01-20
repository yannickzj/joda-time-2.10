package org.joda.time;
import static org.joda.time.PeriodType.yearMonthDayTime;
import org.joda.time.base.BasePeriod;
import java.lang.Object;
import static junit.framework.Assert.assertEquals;
public class TestPeriod_ConstructorsTestConstructor_Object_PeriodType2Template {
  public static <TPeriod extends BasePeriod>void testPeriod_ConstructorsTestConstructor_Object_PeriodType2Template(  TestPeriod_ConstructorsTestConstructor_Object_PeriodType2Adapter<TPeriod> adapter,  Class<TPeriod> clazzTPeriod) throws Throwable {
    TPeriod test=clazzTPeriod.getDeclaredConstructor(Object.class,PeriodType.class).newInstance((Object)null,PeriodType.yearMonthDayTime());
    assertEquals(PeriodType.yearMonthDayTime(),test.getPeriodType());
    assertEquals(0,adapter.getYears(test));
    assertEquals(0,adapter.getMonths(test));
    assertEquals(0,adapter.getWeeks(test));
    assertEquals(0,adapter.getDays(test));
    assertEquals(0,adapter.getHours(test));
    assertEquals(0,adapter.getMinutes(test));
    assertEquals(0,adapter.getSeconds(test));
    assertEquals(0,adapter.getMillis(test));
  }
}

interface TestPeriod_ConstructorsTestConstructor_Object_PeriodType2Adapter<TPeriod> {
	int getYears(TPeriod tPeriod1);

	int getMonths(TPeriod tPeriod1);

	int getWeeks(TPeriod tPeriod1);

	int getDays(TPeriod tPeriod1);

	int getHours(TPeriod tPeriod1);

	int getMinutes(TPeriod tPeriod1);

	int getSeconds(TPeriod tPeriod1);

	int getMillis(TPeriod tPeriod1);
}
