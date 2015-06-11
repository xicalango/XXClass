package xx.xxclass;

public class XXArrayClass extends XXClass {

  XXArrayClass(XXClassManager classManager, String name) {
    super(classManager, name);

    redefFn("__str__", old -> (mgr, self, pars) -> {
      return mgr.$$("Array[").$("..", self.get("length")).$("..", mgr.$$("]"));
    });

    defFn("get", (mgr, self, pars) -> {
      XXArrayInstance arraySelf = (XXArrayInstance) self;
      return arraySelf.get(pars[0].asJavaObject(Number.class).intValue());
    });

    defFn("set", (mgr, self, pars) -> {
      XXArrayInstance arraySelf = (XXArrayInstance) self;
      return arraySelf.set(pars[0].asJavaObject(Number.class).intValue(), pars[1]);
    });
  }

  XXArrayInstance newArrayInstance(int dimension) {
    return new XXArrayInstance(this, dimension);
  }

}
