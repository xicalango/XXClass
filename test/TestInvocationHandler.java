import org.junit.Test;

import xx.xxclass.XXClassManager;
import xx.xxclass.XXInstance;
import xx.xxclass.XXInvocationHandler;

public class TestInvocationHandler {

  private static interface TestInterface {
    String getHelloWorld();

    String getHelloWorld(String bla);

    XXInstance getBla(XXInstance blubb);
  }

  @Test
  public void testInvocationHandler() {

    XXClassManager mngr = new XXClassManager();
    mngr.newClassDef("TestClass")

    .defFn("getHelloWorld", (mgr, self, pars) -> {
      if (pars.length == 0) {
        return mgr.$$("Hello Weld!");
      } else {
        return mgr.$$("Hallo ").$("..", pars[0]);
      }
    })

    .defFn("getBla", (mgr, self, pars) -> {
      return mgr.$$("bla ").$("..", pars[0]);
    })

    .redefFn("__str__", old -> (mgr, self, pars) -> {
      self.getXXClass().redefFn("__str__", old2 -> (mgr2, self2, pars2) -> {
        return old2.$(mgr2, self2, pars2).$("..", mgr.$$(" is awesome"));
      });

      if (self.get("cntr").isNull()) {
        self.put("cntr", mgr.$$(0));
      }

      self.update("cntr", v -> v.$("+", mgr.$$(1)));

      return old.$(mgr, self, pars);
    });

    XXInstance ins = mngr.newInstance("TestClass");

    TestInterface testInt = XXInvocationHandler.wrap(TestInterface.class, ins);

    System.out.println(testInt.getHelloWorld());
    System.out.println(testInt.getHelloWorld("Alex"));

    ins.getXXClass().redefFn("getHelloWorld", old -> (mgr, self, pars) -> {
      return old.$(mgr, self, pars).$("..", mgr.$$(" Hallo Wald!"));
    });

    System.out.println(testInt.getHelloWorld());
    System.out.println(testInt.getHelloWorld("Alex"));
    System.out.println(ins.toString());
    System.out.println(ins.toString());
    System.out.println(ins.toString());
    System.out.println(ins.toString());

    XXInstance ins2 = mngr.newInstance("TestClass");
    System.out.println(ins2);
    System.out.println(ins2.toString());

    System.out.println(ins.get("cntr"));
    System.out.println(ins2.get("cntr"));

    System.out.println(testInt.getBla(mngr.$$("a")));
  }
}
