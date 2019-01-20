package org.joda.time;
import java.lang.Object;
import org.joda.time.base.AbstractPartial;
import org.joda.time.chrono.CopticChronology;
import static org.joda.time.chrono.CopticChronology.getInstanceUTC;
import static junit.framework.Assert.assertEquals;
public class TestPropertiesTestPropertyEqualsTemplate {
  public static <TPartial extends AbstractPartial>void testPropertiesTestPropertyEqualsTemplate(  TestPropertiesTestPropertyEqualsAdapter<TPartial> adapter,  Class<TPartial> clazzTPartial) throws Exception {
    TPartial test1=clazzTPartial.getDeclaredConstructor(int.class,int.class,int.class).newInstance(2005,11,8);
    TPartial test2=clazzTPartial.getDeclaredConstructor(int.class,int.class,int.class).newInstance(2005,11,9);
    TPartial test3=clazzTPartial.getDeclaredConstructor(int.class,int.class,int.class,Chronology.class).newInstance(2005,11,8,CopticChronology.getInstanceUTC());
    assertEquals(false,adapter.dayOfMonth(test1).equals(adapter.year(test1)));
    assertEquals(false,adapter.dayOfMonth(test1).equals(adapter.monthOfYear(test1)));
    assertEquals(true,adapter.dayOfMonth(test1).equals(adapter.dayOfMonth(test1)));
    assertEquals(false,adapter.dayOfMonth(test1).equals(adapter.year(test2)));
    assertEquals(false,adapter.dayOfMonth(test1).equals(adapter.monthOfYear(test2)));
    assertEquals(false,adapter.dayOfMonth(test1).equals(adapter.dayOfMonth(test2)));
    assertEquals(false,adapter.monthOfYear(test1).equals(adapter.year(test1)));
    assertEquals(true,adapter.monthOfYear(test1).equals(adapter.monthOfYear(test1)));
    assertEquals(false,adapter.monthOfYear(test1).equals(adapter.dayOfMonth(test1)));
    assertEquals(false,adapter.monthOfYear(test1).equals(adapter.year(test2)));
    assertEquals(true,adapter.monthOfYear(test1).equals(adapter.monthOfYear(test2)));
    assertEquals(false,adapter.monthOfYear(test1).equals(adapter.dayOfMonth(test2)));
    assertEquals(false,adapter.dayOfMonth(test1).equals(null));
    assertEquals(false,adapter.dayOfMonth(test1).equals("any"));
    assertEquals(false,adapter.dayOfMonth(test1).equals(adapter.dayOfMonth(test3)));
  }
}

interface TestPropertiesTestPropertyEqualsAdapter<TPartial> {
	Object year(TPartial tPartial1);

	Object dayOfMonth(TPartial tPartial1);

	Object monthOfYear(TPartial tPartial1);
}
