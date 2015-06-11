package xx.xxclass;

import java.util.HashMap;
import java.util.Map;

public class FunctionMap {

  private final XXClass xxclass;
  private final Map<String, XXMethod> methods = new HashMap<>();

  public FunctionMap(XXClass xxclass) {
    this.xxclass = xxclass;
  }

  public XXMethod getMethod(String name) {
    XXMethod method = methods.get(name);

    if (method == null) {
      for (XXClass parent : xxclass.getParents()) {
        method = parent.getFunctionMap().getMethod(name);
        if (method != null) {
          break;
        }
      }
    }

    return method;
  }

  public XXInstance call(String name, XXInstance self, XXInstance... pars) {
    XXMethod method = getMethod(name);
    if (method == null) {
      throw new IllegalArgumentException("No such method: " + name);
    }
    return method.$(xxclass.getClassManager(), self, pars);
  }

  public void put(String name, XXMethod body) {
    if (methods.containsKey(name)) {
      throw new IllegalArgumentException();
    }

    methods.put(name, body);
  }

  public void overwrite(String name, XXMethod body) {
    methods.put(name, body);
  }

}
