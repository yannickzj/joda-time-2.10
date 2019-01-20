package org.joda.time;
import java.lang.String;
import org.joda.time.base.BasePeriod;
import static junit.framework.Assert.assertEquals;
public class TestPeriod_BasicsTestToStringTemplate {
  public static <TPeriod extends BasePeriod>void testPeriod_BasicsTestToStringTemplate(  TestPeriod_BasicsTestToStringAdapter<TPeriod> adapter,  Class<TPeriod> clazzTPeriod) throws Exception {
    TPeriod test=clazzTPeriod.getDeclaredConstructor(int.class,int.class,int.class,int.class,int.class,int.class,int.class,int.class).newInstance(1,2,3,4,5,6,7,8);
    assertEquals("P1Y2M3W4DT5H6M7.008S",adapter.toString(test));
    test=clazzTPeriod.getDeclaredConstructor(int.class,int.class,int.class,int.class,int.class,int.class,int.class,int.class).newInstance(0,0,0,0,0,0,0,0);
    assertEquals("PT0S",adapter.toString(test));
    test=clazzTPeriod.getDeclaredConstructor(long.class).newInstance(12345L);
    assertEquals("PT12.345S",adapter.toString(test));
  }
}

interface TestPeriod_BasicsTestToStringAdapter<TPeriod> {
	String toString(TPeriod tPeriod1);
}
