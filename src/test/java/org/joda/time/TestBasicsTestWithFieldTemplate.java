package org.joda.time;
import org.joda.time.base.AbstractPartial;
import static org.joda.time.DurationFieldType.millis;
import static junit.framework.Assert.assertEquals;
import static org.joda.time.DurationFieldType.seconds;
import static org.joda.time.DurationFieldType.minutes;
import static org.joda.time.DurationFieldType.hours;
public class TestBasicsTestWithFieldTemplate {
  public static <TTime extends AbstractPartial>void testBasicsTestWithFieldTemplate(  TestBasicsTestWithFieldAdapter<TTime> adapter,  Class<TTime> clazzTTime) throws Exception {
    TTime test=clazzTTime.getDeclaredConstructor(int.class,int.class,int.class,int.class).newInstance(0,0,0,0);
    TTime result=adapter.withFieldAdded(test,DurationFieldType.millis(),-1);
    assertEquals(clazzTTime.getDeclaredConstructor(int.class,int.class,int.class,int.class).newInstance(23,59,59,999),result);
    test=clazzTTime.getDeclaredConstructor(int.class,int.class,int.class,int.class).newInstance(0,0,0,0);
    result=adapter.withFieldAdded(test,DurationFieldType.seconds(),-1);
    assertEquals(clazzTTime.getDeclaredConstructor(int.class,int.class,int.class,int.class).newInstance(23,59,59,0),result);
    test=clazzTTime.getDeclaredConstructor(int.class,int.class,int.class,int.class).newInstance(0,0,0,0);
    result=adapter.withFieldAdded(test,DurationFieldType.minutes(),-1);
    assertEquals(clazzTTime.getDeclaredConstructor(int.class,int.class,int.class,int.class).newInstance(23,59,0,0),result);
    test=clazzTTime.getDeclaredConstructor(int.class,int.class,int.class,int.class).newInstance(0,0,0,0);
    result=adapter.withFieldAdded(test,DurationFieldType.hours(),-1);
    assertEquals(clazzTTime.getDeclaredConstructor(int.class,int.class,int.class,int.class).newInstance(23,0,0,0),result);
  }
}

interface TestBasicsTestWithFieldAdapter<TTime> {
	TTime withFieldAdded(TTime tTime1, DurationFieldType durationFieldType1, int i1);
}
