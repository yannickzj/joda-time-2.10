package org.joda.time.field;
import org.joda.time.TimeOfDay;
import static junit.framework.Assert.assertEquals;
import java.util.Arrays;
import static java.util.Arrays.equals;
import static junit.framework.Assert.fail;
public class TestPreciseDateTimeFieldTest_add_RP_int_intarray_intTemplate {
  public static <TMockStandardDateTimeField extends BaseDateTimeField>void testPreciseDateTimeFieldTest_add_RP_int_intarray_intTemplate(  Class<TMockStandardDateTimeField> clazzTMockStandardDateTimeField) throws Exception {
    int[] values=new int[]{10,20,30,40};
    int[] expected=new int[]{10,20,30,40};
    BaseDateTimeField field=clazzTMockStandardDateTimeField.newInstance();
    int[] result=field.add(new TimeOfDay(),2,values,0);
    assertEquals(true,Arrays.equals(expected,result));
    values=new int[]{10,20,30,40};
    expected=new int[]{10,20,31,40};
    result=field.add(new TimeOfDay(),2,values,1);
    assertEquals(true,Arrays.equals(expected,result));
    values=new int[]{10,20,30,40};
    expected=new int[]{10,21,0,40};
    result=field.add(new TimeOfDay(),2,values,30);
    assertEquals(true,Arrays.equals(expected,result));
    values=new int[]{23,59,30,40};
    try {
      field.add(new TimeOfDay(),2,values,30);
      fail();
    }
 catch (    IllegalArgumentException ex) {
    }
    values=new int[]{10,20,30,40};
    expected=new int[]{10,20,29,40};
    result=field.add(new TimeOfDay(),2,values,-1);
    assertEquals(true,Arrays.equals(expected,result));
    values=new int[]{10,20,30,40};
    expected=new int[]{10,19,59,40};
    result=field.add(new TimeOfDay(),2,values,-31);
    assertEquals(true,Arrays.equals(expected,result));
    values=new int[]{0,0,30,40};
    try {
      field.add(new TimeOfDay(),2,values,-31);
      fail();
    }
 catch (    IllegalArgumentException ex) {
    }
  }
}
