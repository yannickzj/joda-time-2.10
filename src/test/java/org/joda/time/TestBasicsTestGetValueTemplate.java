package org.joda.time;
import org.joda.time.base.BasePartial;
import static junit.framework.Assert.assertEquals;
public class TestBasicsTestGetValueTemplate {
  public static <TMonth extends BasePartial>void testBasicsTestGetValueTemplate(  Class<TMonth> clazzTMonth,  int i1,  int i2) throws Exception {
    TMonth test=clazzTMonth.newInstance();
    assertEquals(i1,test.getValue(0));
    assertEquals(i2,test.getValue(1));
    try {
      test.getValue(-1);
    }
 catch (    IndexOutOfBoundsException ex) {
    }
    try {
      test.getValue(2);
    }
 catch (    IndexOutOfBoundsException ex) {
    }
  }
}
