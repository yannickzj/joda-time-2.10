package org.joda.time;
import org.joda.time.base.BaseInterval;
import java.lang.Object;
import static junit.framework.Assert.assertEquals;
public class TestInterval_ConstructorsTestConstructor_Object1Template {
  public static <TInterval extends BaseInterval>void testInterval_ConstructorsTestConstructor_Object1Template(  Class<TInterval> clazzTInterval) throws Throwable {
    DateTime dt1=new DateTime(2004,6,9,0,0,0,0);
    DateTime dt2=new DateTime(2005,7,10,1,1,1,1);
    TInterval test=clazzTInterval.getDeclaredConstructor(Object.class).newInstance(dt1.toString() + '/' + dt2.toString());
    assertEquals(dt1.getMillis(),test.getStartMillis());
    assertEquals(dt2.getMillis(),test.getEndMillis());
  }
}
