package org.joda.time;
import org.joda.time.base.AbstractPartial;
import java.lang.Object;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
public class TestConstructorsTestChronologyTemplate {
  public static <TTime extends AbstractPartial>void testConstructorsTestChronologyTemplate(  TestConstructorsTestChronologyAdapter<TTime> adapter,  Class<TTime> clazzTTime) throws Throwable {
    TTime test=clazzTTime.getDeclaredConstructor(Object.class).newInstance("T10:20");
    assertEquals(10,adapter.getHourOfDay(test));
    assertEquals(20,adapter.getMinuteOfHour(test));
    assertEquals(0,adapter.getSecondOfMinute(test));
    assertEquals(0,adapter.getMillisOfSecond(test));
    try {
      clazzTTime.getDeclaredConstructor(Object.class).newInstance("T1020");
      fail();
    }
 catch (    IllegalArgumentException ex) {
    }
  }
}

interface TestConstructorsTestChronologyAdapter<TTime> {
	int getHourOfDay(TTime tTime1);

	int getMinuteOfHour(TTime tTime1);

	int getSecondOfMinute(TTime tTime1);

	int getMillisOfSecond(TTime tTime1);
}
