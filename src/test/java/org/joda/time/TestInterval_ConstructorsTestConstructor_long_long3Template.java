package org.joda.time;
import static junit.framework.Assert.fail;
public class TestInterval_ConstructorsTestConstructor_long_long3Template {
  public static <TInterval>void testInterval_ConstructorsTestConstructor_long_long3Template(  Class<TInterval> clazzTInterval) throws Throwable {
    DateTime dt1=new DateTime(2005,7,10,1,1,1,1);
    DateTime dt2=new DateTime(2004,6,9,0,0,0,0);
    try {
      clazzTInterval.getDeclaredConstructor(long.class,long.class).newInstance(dt1.getMillis(),dt2.getMillis());
      fail();
    }
 catch (    IllegalArgumentException ex) {
    }
  }
}
