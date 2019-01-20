package org.joda.time;
import org.joda.time.base.AbstractPartial;
import static junit.framework.Assert.assertEquals;
import org.joda.time.format.DateTimeFormat;
import static org.joda.time.format.DateTimeFormat.forPattern;
import org.joda.time.format.DateTimeFormatter;
public class TestBasicsTestToString_DTFormatterTemplate {
  public static <TPartial extends AbstractPartial>void testBasicsTestToString_DTFormatterTemplate(  Class<TPartial> clazzTPartial) throws Exception {
    TPartial test=clazzTPartial.getDeclaredConstructor(int.class,int.class,int.class).newInstance(2002,6,9);
    assertEquals("2002 \ufffd\ufffd",test.toString(DateTimeFormat.forPattern("yyyy HH")));
    assertEquals("2002-06-09",test.toString((DateTimeFormatter)null));
  }
}
