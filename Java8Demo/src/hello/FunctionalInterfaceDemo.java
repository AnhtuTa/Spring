package hello;

/*
 * Functional interfaces are also called Single Abstract Method interfaces (SAM Interfaces).
 * They permit exactly one abstract method inside them
 * => Nếu có >= 2 abstract method ở trong interface này thì nó sẽ báo lỗi
 */
@FunctionalInterface
public interface FunctionalInterfaceDemo {

    public void firstWork();

    @Override
    public String toString(); // Overridden from Object class

    @Override
    public boolean equals(Object obj); // Overridden from Object class
}
