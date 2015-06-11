package xx.xxclass;

import java.util.HashMap;
import java.util.Map;

import xx.utils.Helpers;

public class XXClassManager {

  private final Map<String, XXClass> classCache = new HashMap<>();
  private final XXJavaClass xxJavaObjectClass;
  private final XXArrayClass xxArrayClass;
  private final XXInstance xxNullInstance;

  public XXClassManager() {
    xxJavaObjectClass = new XXJavaClass(this, "JavaObject");
    xxArrayClass = new XXArrayClass(this, "Array");
    xxNullInstance = xxJavaObjectClass.newInstance((Object) null);
  }

  public XXClass newClassDef(String name, String... parents) {
    if (classCache.containsKey(name)) {
      throw new IllegalArgumentException(name + " already exists");
    }

    XXClass[] parentClasses = new XXClass[parents.length];
    for (int i = 0; i < parents.length; i++) {
      parentClasses[i] = classCache.get(parents[i]);
    }

    return Helpers.initializeWith(new XXClass(this, name, parentClasses), c -> classCache.put(name, c));
  }

  public XXInstance newInstance(String name, XXInstance... pars) {
    return getXXClass(name).newInstance(pars);
  }

  public XXInstance newArray(XXInstance dim) {
    return xxArrayClass.newArrayInstance(dim.asJavaObject(Number.class).intValue());
  }

  public XXInstance newArrayOf(XXInstance... objs) {
    XXInstance array = newArray($$(objs.length));
    for (int i = 0; i < objs.length; i++) {
      array.$("set", $$(i), objs[i]);
    }
    return array;
  }

  public XXClass getXXClass(String name) {
    if (!classCache.containsKey(name)) {
      throw new IllegalAccessError("No such class: " + name);
    }

    return classCache.get(name);
  }

  public XXInstance $$(Object o) {
    return xxJavaObjectClass.newInstance(o);
  }

  public XXInstance getNull() {
    return xxNullInstance;
  }
}
