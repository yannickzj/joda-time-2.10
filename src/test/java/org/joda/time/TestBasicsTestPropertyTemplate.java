package org.joda.time;
import org.joda.time.field.AbstractPartialFieldProperty;
import org.joda.time.base.BasePartial;
import static junit.framework.Assert.assertEquals;
import static org.joda.time.DateTimeFieldType.monthOfYear;
import static org.joda.time.DateTimeFieldType.millisOfDay;
import static junit.framework.Assert.fail;
public class TestBasicsTestPropertyTemplate {
  public static <TMonth extends BasePartial>void testBasicsTestPropertyTemplate(  TestBasicsTestPropertyAdapter<TMonth> adapter,  Class<TMonth> clazzTMonth) throws Exception {
    TMonth test=clazzTMonth.getDeclaredConstructor(int.class,int.class).newInstance(6,6);
    assertEquals(adapter.monthOfYear(test),adapter.property(test,DateTimeFieldType.monthOfYear()));
    assertEquals(adapter.action1(test),adapter.property(test,adapter.action2()));
    try {
      adapter.property(test,DateTimeFieldType.millisOfDay());
      fail();
    }
 catch (    IllegalArgumentException ex) {
    }
    try {
      adapter.property(test,null);
      fail();
    }
 catch (    IllegalArgumentException ex) {
    }
  }
}

interface TestBasicsTestPropertyAdapter<TMonth> {
	AbstractPartialFieldProperty monthOfYear(TMonth tMonth1);

	AbstractPartialFieldProperty property(TMonth tMonth1, DateTimeFieldType dateTimeFieldType1);

	AbstractPartialFieldProperty action1(TMonth tMonth1);

	DateTimeFieldType action2();
}
