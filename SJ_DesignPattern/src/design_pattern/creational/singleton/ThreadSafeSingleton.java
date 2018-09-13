package design_pattern.creational.singleton;

/*
 * Giống LazyInitializedSingleton nhưng method getInstance() 
 * được đánh dấu là synchronized  tức là chỉ có duy nhất 1 
 * thread được g�?i đến nó trong 1 th�?i điểm
 */
public class ThreadSafeSingleton {
	private static ThreadSafeSingleton instance;

	private ThreadSafeSingleton() {
		System.out.println("Constructor of ThreadSafeSingleton");
	}

	public static synchronized ThreadSafeSingleton getInstance() {
		if (instance == null) {
			instance = new ThreadSafeSingleton();
		}
		return instance;
	}
}
