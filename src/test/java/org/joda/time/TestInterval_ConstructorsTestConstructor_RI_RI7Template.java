package org.joda.time;
import static junit.framework.Assert.fail;
public class TestInterval_ConstructorsTestConstructor_RI_RI7Template {
  public static <TInterval>void testInterval_ConstructorsTestConstructor_RI_RI7Template(  Class<TInterval> clazzTInterval) throws Throwable {
    DateTime dt1=new DateTime(2005,7,10,1,1,1,1);
    DateTime dt2=new DateTime(2004,6,9,0,0,0,0);
    try {
      clazzTInterval.getDeclaredConstructor(ReadableInstant.class,ReadableInstant.class).newInstance(dt1,dt2);
      fail();
    }
 catch (    IllegalArgumentException ex) {
    }
  }
}
