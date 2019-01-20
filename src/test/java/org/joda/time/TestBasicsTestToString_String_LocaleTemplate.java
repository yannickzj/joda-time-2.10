package org.joda.time;
import java.util.Locale;
import java.lang.String;
import org.joda.time.base.AbstractPartial;
import static junit.framework.Assert.assertEquals;
import java.lang.IllegalArgumentException;
public class TestBasicsTestToString_String_LocaleTemplate {
  public static <TPartial extends AbstractPartial>void testBasicsTestToString_String_LocaleTemplate(  TestBasicsTestToString_String_LocaleAdapter<TPartial> adapter,  Class<TPartial> clazzTPartial,  String string1,  String string2,  String string3,  String string4,  String string5) throws Exception {
    TPartial test=clazzTPartial.getDeclaredConstructor(int.class,int.class,int.class).newInstance(1970,6,9);
    assertEquals(string1,adapter.toString(test,"EEE d/M",Locale.ENGLISH));
    assertEquals(string2,adapter.toString(test,"EEE d/M",Locale.FRENCH));
    assertEquals(string3,adapter.toString(test,null,Locale.ENGLISH));
    assertEquals(string4,adapter.toString(test,"EEE d/M",null));
    assertEquals(string5,adapter.toString(test,null,null));
  }
}

interface TestBasicsTestToString_String_LocaleAdapter<TPartial> {
	String toString(TPartial tPartial1, String string1, Locale locale1) throws IllegalArgumentException;
}
