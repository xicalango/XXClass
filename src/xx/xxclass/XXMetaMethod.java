package xx.xxclass;

public interface XXMetaMethod {
  Object $(XXClassManager mgr, XXInstance self, XXInstance... pars);

  public static XXMetaMethod DEFAULT = (mgr, self, pars) -> null;
}
