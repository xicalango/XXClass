package xx.xxclass;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class XXInstance {

  private final XXClass xxClass;
  private final Map<String, XXInstance> fieldMap = new HashMap<>();

  public static final void IF(XXInstance cond, Runnable then) {
    if (cond.asJavaObject(Boolean.class)) {
      then.run();
    }
  }

  public static final void IF(XXInstance cond, Runnable then, Runnable elseR) {
    if (cond.asJavaObject(Boolean.class)) {
      then.run();
    } else {
      elseR.run();
    }
  }

  public static final void IF(XXInstance cond, Runnable then, XXInstance cond2, Runnable else2, Runnable elseR) {
    if (cond.asJavaObject(Boolean.class)) {
      then.run();
    } else if (cond2.asJavaObject(Boolean.class)) {
      else2.run();
    } else {
      elseR.run();
    }
  }

  public static final void WHILE(XXInstance cond, Runnable body) {
    while (cond.asJavaObject(Boolean.class)) {
      body.run();
    }
  }

  XXInstance(XXClass xxClass) {
    this.xxClass = xxClass;
  }

  public XXInstance $(XXInstance name, XXInstance... pars) {
    return $(name.toString(), pars);
  }

  public XXInstance $(String name, XXInstance... pars) {
    return xxClass.call(name, this, pars);
  }

  public XXInstance put(XXInstance key, XXInstance value) {
    return put(key.toString(), value);
  }

  public XXInstance put(String key, XXInstance value) {
    return fieldMap.put(key, value);
  }

  public XXInstance get(XXInstance key) {
    return get(key.toString());
  }

  public XXInstance get(String key) {
    if (fieldMap.containsKey(key)) {
      return fieldMap.get(key);
    } else {
      return xxClass.getClassManager().getNull();
    }
  }

  public <E> E getAs(String key, Class<E> expected, XXInstance... pars) {
    return get(key).asJavaObject(expected, pars);
  }

  public XXInstance update(String key, Function<XXInstance, XXInstance> update) {
    return put(key, update.apply(get(key)));
  }

  public XXClass getXXClass() {
    return xxClass;
  }

  public <E> E asJavaObject(Class<E> expected, XXInstance... pars) {
    return expected.cast(xxClass.getToJavaObject().$(xxClass.getClassManager(), this, pars));
  }

  public boolean isNull() {
    return this == xxClass.getClassManager().getNull();
  }

  @Override
  public String toString() {
    return $("__str__").asJavaObject(String.class);
  }

}
