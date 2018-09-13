package design_pattern.creational.singleton;

/*
 * Singleton Pattern là 1 mẫu thiết kế tạo dựng (Creation Pattern)

Singleton Pattern được dùng để ngăn cản việc tạo các thể hiện của một lớp (class) 
nhằm đảm bảo rằng luôn chỉ có 1 thể hiện của class tồn tại trong JVM.

Class Singleton Pattern phải cung cấp 1 điểm truy cập global để lấy ra được thể hiện của class.

Singleton Pattern được dùng cho logging, các driver object, caching và thread pools.

Singleton Pattern cũng được sử dụng trong các Design Pattern khác như Abstract Factory, Builder, Facede…

Một số class Singleton trong Java core như java.lang.Runtime, java.awt.Desktop

(Luôn chỉ có nhi�?u nhất 1 object của class  java.lang.Runtime, java.awt.Desktop được tạo)

Một Singleton Pattern thư�?ng là 1 class (Class Singleton) có các đặc điểm:
	Hàm khởi tạo private để ngăn cản việc tạo thể hiện của class từ các class khác
	Biến private static của class, nó là thể hiện duy nhất của class.
	Method public static để trả v�? thể hiện của class.

 */
public class Test {
	public static void main(String[] args) {
		EagerInitializedSingleton eagerSingleton = EagerInitializedSingleton.getInstance();
		StaticBlockSingleton staticSingleton = StaticBlockSingleton.getInstance();
		LazyInitializedSingleton lazySingleton = LazyInitializedSingleton.getInstance();
		ThreadSafeSingleton threadSingleton = ThreadSafeSingleton.getInstance();
		BillPughSingleton billSingleton = BillPughSingleton.getInstance();
		
		System.out.println("This instance belong to: " + eagerSingleton.getClass().getSimpleName());
		System.out.println("This instance belong to: " + staticSingleton.getClass().getSimpleName());
		System.out.println("This instance belong to: " + lazySingleton.getClass().getSimpleName());
		System.out.println("This instance belong to: " + threadSingleton.getClass().getSimpleName());
		System.out.println("This instance belong to: " + billSingleton.getClass().getSimpleName());
		
		Integer.valueOf("1");
		
		EagerInitializedSingleton ei1 = EagerInitializedSingleton.getInstance();
		EagerInitializedSingleton ei2 = EagerInitializedSingleton.getInstance();
		System.out.println(ei1 == ei2);
	}
}
