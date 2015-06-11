package xx.xxclass;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class XXInvocationHandler implements InvocationHandler {

  private final XXInstance wrappedInstance;

  private XXInvocationHandler(XXInstance wrappedInstance) {
    this.wrappedInstance = wrappedInstance;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    XXInstance[] pars;

    if (args != null) {

      pars = new XXInstance[args.length];

      for (int i = 0; i < args.length; i++) {
        if (args[i] instanceof XXInstance) {
          pars[i] = (XXInstance) args[i];
        } else {
          pars[i] = wrappedInstance.getXXClass().getClassManager().$$(args[i]);
        }
      }
    } else {
      pars = new XXInstance[0];
    }

    final XXInstance retVal = wrappedInstance.$(method.getName(), pars);

    if (method.getReturnType() == XXInstance.class) {
      return retVal;
    } else if (method.getReturnType() == void.class) {
      return null;
    } else {
      return retVal.asJavaObject(method.getReturnType());
    }

  }

  public static <E> E wrap(Class<E> eClass, XXInstance instance) {
    return eClass.cast(Proxy.newProxyInstance(XXInvocationHandler.class.getClassLoader(), new Class[] { eClass }, new XXInvocationHandler(instance)));
  }

}
