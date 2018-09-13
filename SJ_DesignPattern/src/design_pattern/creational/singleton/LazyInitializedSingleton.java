package design_pattern.creational.singleton;

/*
 * Tạo thể hiện của Class Singleton trong method access. 
 * Cách này có nhược điểm là không hoạt động đúng trong 
 * trư�?ng hợp có nhi�?u thread, giả sử có nhi�?u thread cùng 
 * lúc g�?i method getInstance() sẽ có nhi�?u thể hiện khác 
 * nhau được tạo
 */
public class LazyInitializedSingleton {
	private static LazyInitializedSingleton instance;

	private LazyInitializedSingleton() {
	}

	public static LazyInitializedSingleton getInstance() {
		if (instance == null) {
			instance = new LazyInitializedSingleton();
		}
		return instance;
	}
}
