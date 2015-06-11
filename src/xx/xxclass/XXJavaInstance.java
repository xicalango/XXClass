package xx.xxclass;

class XXJavaInstance extends XXInstance {

  private final Object javaValue;

  XXJavaInstance(XXClass xxClass, Object javaValue) {
    super(xxClass);

    this.javaValue = javaValue;
  }

  Object getJavaValue() {
    return javaValue;
  }

  @Override
  public int hashCode() {
    return javaValue.hashCode();
  }

}
