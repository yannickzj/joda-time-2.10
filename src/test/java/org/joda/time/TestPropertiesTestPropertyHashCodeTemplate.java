package org.joda.time;
import org.joda.time.field.AbstractPartialFieldProperty;
import org.joda.time.base.BasePartial;
import static junit.framework.Assert.assertEquals;
public class TestPropertiesTestPropertyHashCodeTemplate {
  public static <TMonth extends BasePartial>void testPropertiesTestPropertyHashCodeTemplate(  TestPropertiesTestPropertyHashCodeAdapter<TMonth> adapter,  Class<TMonth> clazzTMonth,  boolean b1,  boolean b2) throws Exception {
    TMonth test1=clazzTMonth.getDeclaredConstructor(int.class,int.class).newInstance(5,11);
    TMonth test2=clazzTMonth.getDeclaredConstructor(int.class,int.class).newInstance(5,12);
    assertEquals(true,adapter.action1(test1).hashCode() == adapter.action1(test1).hashCode());
    assertEquals(b1,adapter.action1(test1).hashCode() == adapter.action1(test2).hashCode());
    assertEquals(true,adapter.monthOfYear(test1).hashCode() == adapter.monthOfYear(test1).hashCode());
    assertEquals(b2,adapter.monthOfYear(test1).hashCode() == adapter.monthOfYear(test2).hashCode());
  }
}

interface TestPropertiesTestPropertyHashCodeAdapter<TMonth> {
	AbstractPartialFieldProperty action1(TMonth tMonth1);

	AbstractPartialFieldProperty monthOfYear(TMonth tMonth1);
}
