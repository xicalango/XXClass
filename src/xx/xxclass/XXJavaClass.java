package xx.xxclass;

public class XXJavaClass extends XXClass {

  XXJavaClass(XXClassManager classManager, String name) {
    super(classManager, name);

    setToJavaObject((mgr, self, pars) -> ((XXJavaInstance) self).getJavaValue());

    defFn("+", (mgr, self, pars) -> {
      return mgr.$$(self.asJavaObject(Number.class).doubleValue() + pars[0].asJavaObject(Number.class).doubleValue());
    });

    defFn("-", (mgr, self, pars) -> {
      return mgr.$$(self.asJavaObject(Number.class).doubleValue() - pars[0].asJavaObject(Number.class).doubleValue());
    });

    defFn("*", (mgr, self, pars) -> {
      return mgr.$$(self.asJavaObject(Number.class).doubleValue() * pars[0].asJavaObject(Number.class).doubleValue());
    });

    defFn("/", (mgr, self, pars) -> {
      return mgr.$$(self.asJavaObject(Number.class).doubleValue() / pars[0].asJavaObject(Number.class).doubleValue());
    });

    defFn("<", (mgr, self, pars) -> {
      return mgr.$$(self.asJavaObject(Number.class).doubleValue() < pars[0].asJavaObject(Number.class).doubleValue());
    });

    defFn(">", (mgr, self, pars) -> {
      return mgr.$$(self.asJavaObject(Number.class).doubleValue() > pars[0].asJavaObject(Number.class).doubleValue());
    });

    defFn("==", (mgr, self, pars) -> {
      return mgr.$$(self.asJavaObject(Number.class).doubleValue() == pars[0].asJavaObject(Number.class).doubleValue());
    });

    defFn("<=", (mgr, self, pars) -> {
      return mgr.$$(self.asJavaObject(Number.class).doubleValue() <= pars[0].asJavaObject(Number.class).doubleValue());
    });

    defFn(">=", (mgr, self, pars) -> {
      return mgr.$$(self.asJavaObject(Number.class).doubleValue() >= pars[0].asJavaObject(Number.class).doubleValue());
    });

    defFn("..", (mgr, self, pars) -> {
      return mgr.$$(self.asJavaObject(Object.class).toString() + pars[0].asJavaObject(Object.class).toString());
    });

    redefFn("__str__", old -> (mgr, self, pars) -> {
      return mgr.$$(String.valueOf(self.asJavaObject(Object.class)));
    });
  }

  XXJavaInstance newInstance(Object o) {
    return new XXJavaInstance(this, o);
  }

}
