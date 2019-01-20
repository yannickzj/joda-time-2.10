package org.joda.time.convert;
import org.joda.time.TimeOfDay;
import org.joda.time.chrono.ISOChronology;
import static org.joda.time.chrono.ISOChronology.getInstance;
import static junit.framework.Assert.assertEquals;
import java.util.Arrays;
import static java.util.Arrays.equals;
public class TestConverterTestGetPartialValuesTemplate {
  public static <TObject>void testConverterTestGetPartialValuesTemplate(  Class<TObject> clazzTObject,  AbstractConverter abstractConverter1) throws Exception {
    TimeOfDay tod=new TimeOfDay();
    int[] expected=ISOChronology.getInstance().get(tod,12345678L);
    int[] actual=abstractConverter1.getPartialValues(tod,(Object)clazzTObject.getDeclaredConstructor(long.class).newInstance(12345678L),ISOChronology.getInstance());
    assertEquals(true,Arrays.equals(expected,actual));
  }
}
