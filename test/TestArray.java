import org.junit.Test;

import xx.xxclass.XXClassManager;
import xx.xxclass.XXInstance;

public class TestArray {

  @Test
  public void testArray() {

    XXClassManager cm = new XXClassManager();

    XXInstance array = cm.newArray(cm.$$(10));
    array.put("sum", cm.$$(0));

    for (int i = 0; i < array.getAs("length", Integer.class); i++) {
      array.$("set", cm.$$(i), cm.$$(i));
    }

    for (int i = 0; i < array.getAs("length", Integer.class); i++) {
      final int ii = i;
      array.update("sum", a -> a.$("+", array.$("get", cm.$$(ii))));
    }

    System.out.println(array.get("sum"));

    System.out.println(array);

  }

}
