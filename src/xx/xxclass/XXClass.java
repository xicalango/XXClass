package xx.xxclass;

import static xx.utils.Helpers.initializeWith;

import java.util.function.Function;

public class XXClass {

  private final XXClassManager classManager;
  private final String name;
  private final FunctionMap functionMap = new FunctionMap(this);
  private final XXClass[] parents;
  private XXMetaMethod toJavaObject = XXMetaMethod.DEFAULT;

  XXClass(XXClassManager classManager, String name, XXClass... parents) {
    this.classManager = classManager;
    this.name = name;
    this.parents = parents;

    defFn("__str__", (mgr, self, pars) -> {
      return mgr.$$("XXInstance[" + name + "]");
    });

    defFn("__init__", XXMethod.IDENTITY);
  }

  public XXMetaMethod getToJavaObject() {
    return toJavaObject;
  }

  public XXClass setToJavaObject(XXMetaMethod toJavaObject) {
    this.toJavaObject = toJavaObject;
    return this;
  }

  public String getName() {
    return name;
  }

  FunctionMap getFunctionMap() {
    return functionMap;
  }

  public XXInstance newInstance(XXInstance... pars) {
    return initializeWith(new XXInstance(this), e -> e.$("__init__", pars));
  }

  public XXClass defFn(String name, XXMethod body) {
    functionMap.put(name, body);
    return this;
  }

  public XXClass redefFn(String name, Function<XXMethod, XXMethod> body) {
    XXMethod oldFn = functionMap.getMethod(name);
    if (oldFn == null) {
      oldFn = XXMethod.IDENTITY;
    }
    functionMap.overwrite(name, body.apply(oldFn));
    return this;
  }

  public XXClass[] getParents() {
    return parents;
  }

  public XXClassManager getClassManager() {
    return classManager;
  }

  public XXInstance call(String name, XXInstance self, XXInstance... pars) {
    return functionMap.call(name, self, pars);
  }

}
