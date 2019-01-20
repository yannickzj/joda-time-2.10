package org.joda.time;
import org.joda.time.base.BasePartial;
import static junit.framework.Assert.assertEquals;
import org.joda.time.chrono.GregorianChronology;
import static org.joda.time.chrono.GregorianChronology.getInstanceUTC;
import static junit.framework.Assert.fail;
import static org.joda.time.DateTimeFieldType.centuryOfEra;
import static org.joda.time.DateTimeFieldType.halfdayOfDay;
import static org.joda.time.DateTimeFieldType.dayOfMonth;
public class TestBasicsTestCompareToTemplate {
  public static <TMonth extends BasePartial>void testBasicsTestCompareToTemplate(  TestBasicsTestCompareToAdapter<TMonth> adapter,  Class<TMonth> clazzTMonth,  int i1) throws Exception {
    TMonth test1=clazzTMonth.getDeclaredConstructor(int.class,int.class).newInstance(6,6);
    TMonth test1a=clazzTMonth.getDeclaredConstructor(int.class,int.class).newInstance(6,6);
    assertEquals(0,adapter.compareTo(test1,test1a));
    assertEquals(0,adapter.compareTo(test1a,test1));
    assertEquals(0,adapter.compareTo(test1,test1));
    assertEquals(0,adapter.compareTo(test1a,test1a));
    TMonth test2=clazzTMonth.getDeclaredConstructor(int.class,int.class).newInstance(6,7);
    assertEquals(-1,adapter.compareTo(test1,test2));
    assertEquals(+1,adapter.compareTo(test2,test1));
    TMonth test3=clazzTMonth.getDeclaredConstructor(int.class,int.class,Chronology.class).newInstance(6,7,GregorianChronology.getInstanceUTC());
    assertEquals(-1,adapter.compareTo(test1,test3));
    assertEquals(+1,adapter.compareTo(test3,test1));
    assertEquals(0,adapter.compareTo(test3,test2));
    DateTimeFieldType[] types=new DateTimeFieldType[]{adapter.year(),adapter.monthOf()};
    int[] values=new int[]{i1,6};
    Partial p=new Partial(types,values);
    assertEquals(0,adapter.compareTo(test1,p));
    try {
      adapter.compareTo(test1,null);
      fail();
    }
 catch (    NullPointerException ex) {
    }
    try {
      adapter.compareTo(test1,new LocalTime());
      fail();
    }
 catch (    ClassCastException ex) {
    }
    Partial partial=new Partial().with(DateTimeFieldType.centuryOfEra(),1).with(DateTimeFieldType.halfdayOfDay(),0).with(DateTimeFieldType.dayOfMonth(),9);
    try {
      adapter.compareTo(clazzTMonth.getDeclaredConstructor(int.class,int.class).newInstance(10,6),partial);
      fail();
    }
 catch (    ClassCastException ex) {
    }
  }
}

interface TestBasicsTestCompareToAdapter<TMonth> {
	int compareTo(TMonth tMonth1, ReadablePartial readablePartial1);

	DateTimeFieldType year();

	DateTimeFieldType monthOf();
}
