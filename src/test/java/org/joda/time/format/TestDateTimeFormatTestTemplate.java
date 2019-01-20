package org.joda.time.format;
import java.lang.String;
import static junit.framework.Assert.fail;
public class TestDateTimeFormatTestTemplate {
  public static void testDateTimeFormatTestTemplate(  TestDateTimeFormatTestAdapter adapter,  String string1,  String string2){
    try {
      adapter.forAction(null);
      fail();
    }
 catch (    IllegalArgumentException ex) {
    }
    try {
      adapter.forAction("");
      fail();
    }
 catch (    IllegalArgumentException ex) {
    }
    try {
      adapter.forAction(string1);
      fail();
    }
 catch (    IllegalArgumentException ex) {
    }
    try {
      adapter.forAction(string2);
      fail();
    }
 catch (    IllegalArgumentException ex) {
    }
  }
}

interface TestDateTimeFormatTestAdapter {
	DateTimeFormatter forAction(String string1);
}
