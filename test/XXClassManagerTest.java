import org.junit.Test;

import xx.xxclass.XXClass;
import xx.xxclass.XXClassManager;
import xx.xxclass.XXInstance;
import xx.xxclass.XXMethod;

public class XXClassManagerTest {

  @Test
  public void testAddClass() {

    XXClassManager cm = new XXClassManager();
    XXClass object = cm.newClassDef("Object");

    object.defFn("__describe", XXMethod.voidMethod((mgr, self, pars) -> {
      System.out.println(self.getXXClass().getName());
    }));

    XXClass xc = cm.newClassDef("TestClass", "Object");

    xc.defFn("test", XXMethod.voidMethod((mgr, self, pars) -> {
      self.put("test", mgr.newInstance("TestClass"));
    }));

    XXInstance instance = cm.newInstance("TestClass");

    instance.$("test");

    instance.$("__describe");

  }

  @Test
  public void testMetaClass() {

    XXClassManager cm = new XXClassManager();

    XXClass testObject = cm.newClassDef("Test");
    testObject.defFn("PutStr", (mgr, self, pars) -> {
      self.put("Str", mgr.$$("Hallo Weld!"));
      return self;
    });

    testObject.setToJavaObject((mgr, self, pars) -> {
      if (pars[0].asJavaObject(Boolean.class) == true) {
        return self.get("Str").asJavaObject(String.class);
      } else {
        return mgr.$$(13).asJavaObject(Integer.class);
      }
    });

    XXInstance instance = testObject.newInstance();

    instance.$("PutStr");
    System.out.println(instance.asJavaObject(Object.class, cm.$$(true)));
    System.out.println(instance.asJavaObject(Object.class, cm.$$(false)));
  }
}
