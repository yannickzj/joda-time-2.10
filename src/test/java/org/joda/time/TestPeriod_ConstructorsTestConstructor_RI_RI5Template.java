package org.joda.time;
import org.joda.time.base.BasePeriod;
import static junit.framework.Assert.assertEquals;
import static org.joda.time.PeriodType.standard;
public class TestPeriod_ConstructorsTestConstructor_RI_RI5Template {
  public static <TPeriod extends BasePeriod>void testPeriod_ConstructorsTestConstructor_RI_RI5Template(  TestPeriod_ConstructorsTestConstructor_RI_RI5Adapter<TPeriod> adapter,  Class<TPeriod> clazzTPeriod) throws Throwable {
    DateTime dt1=null;
    DateTime dt2=null;
    TPeriod test=clazzTPeriod.getDeclaredConstructor(ReadableInstant.class,ReadableInstant.class).newInstance(dt1,dt2);
    assertEquals(PeriodType.standard(),test.getPeriodType());
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

interface TestPeriod_ConstructorsTestConstructor_RI_RI5Adapter<TPeriod> {
	int getYears(TPeriod tPeriod1);

	int getMonths(TPeriod tPeriod1);

	int getWeeks(TPeriod tPeriod1);

	int getDays(TPeriod tPeriod1);

	int getHours(TPeriod tPeriod1);

	int getMinutes(TPeriod tPeriod1);

	int getSeconds(TPeriod tPeriod1);

	int getMillis(TPeriod tPeriod1);
}
