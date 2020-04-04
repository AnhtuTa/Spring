package hello;

import java.util.Arrays;

/**
 * Method References là một tính năng khá hay và liên quan đến việc sử dụng Lambda Expression. Nó
 * cung cấp các cú pháp hữu ích để truy cập trực tiếp tới các constructor hoặc method đã tồn tại của
 * các lớp hoặc đối tượng trong Java mà không cần thực thi chúng. -> Code đơn giản hơn rất nhiều,
 * nhìn đẹp hơn.
 * 
 * Method References là cú pháp viết tắt của biểu thức Lambda để gọi phương thức
 * 
 * Sử dụng từ khóa ::
 * 
 * Các loại Method References
 * Tham chiếu đến một static method – Class::staticMethod
 * Tham chiếu đến một instance method của một đối tượng cụ thể – object::instanceMethod
 * Tham chiếu đến một instance method của một đối tượng tùy ý của một kiểu cụ thể – Class::instanceMethod
 * Tham chiếu đến một constuctor – Class::new
 * 
 * Chú ý:
 * - Chúng ta có thể sử dụng Method References để thay thế cho các Lambda Expression
 *   khi Lamba gọi một phương thức nào đó đã được định nghĩa sẵn.
 * - Chúng ta không thể truyền tham số cho các Method References, phải sử dụng
 *   đi kèm với Functional Interfaces
 * @author Anhtu
 */
public class MethodReferences {

    public static int doAction(int a, int b, ExecuteFunction func) {
        int kq = func.execute(a, b);
        System.out.println("[doAction]: " + kq);
        return kq;
    }

    public static void main(String[] args) {
        // C1
        MethodReferences.doAction(5, 7, new ExecuteFunction() {
            @Override
            public int execute(int a, int b) {
                return MathUtils1.sum(a, b);
            }
        });

        // C2 (1. Tham chiếu đến một static method)
        // Cách 1 ở trên, ta gọi method doAction, trong method này có một tham số là
        // Functional Interface
        // -> Có thể truyền vào một tham chiếu method có cấu trúc giống với cấu trúc
        // method định nghĩa trong Functional interface
        // (Trong ví dụ này, method MathUtils1.sum có cấu trúc giống hệt với method duy
        // nhất trong ExecuteFunction -> Dùng MathUtils1.sum thay thế được)
        MethodReferences.doAction(5, 7, MathUtils1::sum);

        // C3 (2. Tham chiếu đến một instance method của một đối tượng cụ thể)
        // Giống C2 ở trên, nhưng thằng MathUtils2 ko có static method
        // -> Dùng object để tham chiếu tới method của object đó
        MathUtils2 mu2 = new MathUtils2();
        MethodReferences.doAction(5, 7, mu2::sum);

        // 3. Tham chiếu đến một instance method của một đối tượng tùy ý của một kiểu cụ thể
        String[] stringArray = {"Java", "C++", "PHP", "C#", "Javascript"};
        Arrays.sort(stringArray, String::compareToIgnoreCase);
        for (String str : stringArray) {
            System.out.println(str);
        }
        
        // 4. Tham chiếu đến một constuctor: ClassName::new
        // Chú ý: chỉ dùng cho 1 Functional Interface
        // Éo hiểu cái này nên chưa biết VD như nào!
    }
}


@FunctionalInterface
interface ExecuteFunction {
    public int execute(int a, int b);
}

class MathUtils1 {
    public static int sum(int a, int b) {
        return a + b;
    }

    public static int sum(int a, int b, int c) {
        return a + b + c;
    }

    public static int minus(int a, int b) {
        return a - b;
    }
}

class MathUtils2 {
    public int sum(int a, int b) {
        return a + b;
    }

    public int minus(int a, int b) {
        return a - b;
    }
}
