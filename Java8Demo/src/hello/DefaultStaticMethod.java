package hello;

/**
 * If any class implements this interface then it need not to implement it's
 * own version of sayHello() method
 */
public interface DefaultStaticMethod {

    void doSomething();

    default void sayHello() {
        System.out.println("[DefaultMethod] Hello Java8!");
    }

    static void sayGoobye() {
        System.out.println("[DefaultMethod] Goodbye Java8!");
    }
}
