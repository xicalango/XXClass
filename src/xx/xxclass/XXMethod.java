package xx.xxclass;

import xx.utils.VoidMethod;

public interface XXMethod {

  XXInstance $(XXClassManager mgr, XXInstance self, XXInstance... pars);

  public static final XXMethod IDENTITY = (mgr, self, pars) -> self;

  public static XXMethod voidMethod(VoidMethod body) {
    return (mgr, self, pars) -> {
      body.accept(mgr, self, pars);
      return self;
    };
  }
}
