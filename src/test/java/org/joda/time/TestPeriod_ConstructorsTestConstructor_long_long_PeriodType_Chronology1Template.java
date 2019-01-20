package org.joda.time;
import org.joda.time.chrono.CopticChronology;
import static org.joda.time.chrono.CopticChronology.getInstance;
import org.joda.time.base.BasePeriod;
import static junit.framework.Assert.assertEquals;
import static org.joda.time.PeriodType.standard;
public class TestPeriod_ConstructorsTestConstructor_long_long_PeriodType_Chronology1Template {
  public static <TPeriod extends BasePeriod>void testPeriod_ConstructorsTestConstructor_long_long_PeriodType_Chronology1Template(  TestPeriod_ConstructorsTestConstructor_long_long_PeriodType_Chronology1Adapter<TPeriod> adapter,  Class<TPeriod> clazzTPeriod) throws Throwable {
    DateTime dt1=new DateTime(2004,6,9,0,0,0,0,CopticChronology.getInstance());
    DateTime dt2=new DateTime(2005,7,10,1,1,1,1,CopticChronology.getInstance());
    TPeriod test=clazzTPeriod.getDeclaredConstructor(long.class,long.class,PeriodType.class,Chronology.class).newInstance(dt1.getMillis(),dt2.getMillis(),(PeriodType)null,CopticChronology.getInstance());
    assertEquals(PeriodType.standard(),test.getPeriodType());
    assertEquals(1,adapter.getYears(test));
    assertEquals(1,adapter.getMonths(test));
    assertEquals(0,adapter.getWeeks(test));
    assertEquals(1,adapter.getDays(test));
    assertEquals(1,adapter.getHours(test));
    assertEquals(1,adapter.getMinutes(test));
    assertEquals(1,adapter.getSeconds(test));
    assertEquals(1,adapter.getMillis(test));
  }
}

interface TestPeriod_ConstructorsTestConstructor_long_long_PeriodType_Chronology1Adapter<TPeriod> {
	int getYears(TPeriod tPeriod1);

	int getMonths(TPeriod tPeriod1);

	int getWeeks(TPeriod tPeriod1);

	int getDays(TPeriod tPeriod1);

	int getHours(TPeriod tPeriod1);

	int getMinutes(TPeriod tPeriod1);

	int getSeconds(TPeriod tPeriod1);

	int getMillis(TPeriod tPeriod1);
}
