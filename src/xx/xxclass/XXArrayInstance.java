package xx.xxclass;

public class XXArrayInstance extends XXInstance {

  private final XXInstance[] array;

  XXArrayInstance(XXClass xxClass, int dimension) {
    super(xxClass);
    array = new XXInstance[dimension];
    put("length", xxClass.getClassManager().$$(dimension));
  }

  public XXInstance get(int i) {
    return array[i];
  }

  public XXInstance set(int i, XXInstance val) {
    return array[i] = val;
  }

}
