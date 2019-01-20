package org.joda.time;
import org.joda.time.base.AbstractInstant;
import static junit.framework.Assert.assertEquals;
import org.joda.time.chrono.ISOChronology;
import static org.joda.time.chrono.ISOChronology.getInstance;
import static junit.framework.Assert.fail;
public class TestBasicsTestGet_DateTimeFieldTemplate {
  public static <TInstant extends AbstractInstant>void testBasicsTestGet_DateTimeFieldTemplate(  Class<TInstant> clazzTInstant) throws Exception {
    TInstant test=clazzTInstant.newInstance();
    assertEquals(1,test.get(ISOChronology.getInstance().era()));
    assertEquals(20,test.get(ISOChronology.getInstance().centuryOfEra()));
    assertEquals(2,test.get(ISOChronology.getInstance().yearOfCentury()));
    assertEquals(2002,test.get(ISOChronology.getInstance().yearOfEra()));
    assertEquals(2002,test.get(ISOChronology.getInstance().year()));
    assertEquals(6,test.get(ISOChronology.getInstance().monthOfYear()));
    assertEquals(9,test.get(ISOChronology.getInstance().dayOfMonth()));
    assertEquals(2002,test.get(ISOChronology.getInstance().weekyear()));
    assertEquals(23,test.get(ISOChronology.getInstance().weekOfWeekyear()));
    assertEquals(7,test.get(ISOChronology.getInstance().dayOfWeek()));
    assertEquals(160,test.get(ISOChronology.getInstance().dayOfYear()));
    assertEquals(0,test.get(ISOChronology.getInstance().halfdayOfDay()));
    assertEquals(1,test.get(ISOChronology.getInstance().hourOfHalfday()));
    assertEquals(1,test.get(ISOChronology.getInstance().clockhourOfDay()));
    assertEquals(1,test.get(ISOChronology.getInstance().clockhourOfHalfday()));
    assertEquals(1,test.get(ISOChronology.getInstance().hourOfDay()));
    assertEquals(0,test.get(ISOChronology.getInstance().minuteOfHour()));
    assertEquals(60,test.get(ISOChronology.getInstance().minuteOfDay()));
    assertEquals(0,test.get(ISOChronology.getInstance().secondOfMinute()));
    assertEquals(60 * 60,test.get(ISOChronology.getInstance().secondOfDay()));
    assertEquals(0,test.get(ISOChronology.getInstance().millisOfSecond()));
    assertEquals(60 * 60 * 1000,test.get(ISOChronology.getInstance().millisOfDay()));
    try {
      test.get((DateTimeField)null);
      fail();
    }
 catch (    IllegalArgumentException ex) {
    }
  }
}
