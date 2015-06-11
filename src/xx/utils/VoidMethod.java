package xx.utils;

import xx.xxclass.XXClassManager;
import xx.xxclass.XXInstance;

public interface VoidMethod {
  void accept(XXClassManager mgr, XXInstance self, XXInstance... pars);
}
