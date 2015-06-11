package xx.utils;

import java.util.function.Consumer;

public interface Helpers {
  public static <E> E initializeWith(E instance, Consumer<E> initializer) {
    initializer.accept(instance);
    return instance;
  }
}
