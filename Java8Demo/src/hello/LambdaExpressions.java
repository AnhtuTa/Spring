package hello;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Lambda Expressions (Biểu thức Lambda) là một trong những tính năng mới được giới thiệu trong Java
 * 8.
 * 
 * Trước khi Java 8, mọi thứ chủ yếu là hướng đối tượng. Ngoại trừ các kiểu dữ liệu nguyên thủy
 * (primitive type), mọi thứ trong java tồn tại dưới dạng đối tượng. Tất cả các lời gọi đến các
 * method/ function sẽ được thực hiện bằng cách sử dụng các class hoặc object. Các method/function
 * không tồn tại độc lập.
 * 
 * Với Java 8, lập trình chức năng (functional programming) đã được giới thiệu. Vì vậy, chúng ta có
 * thể dễ dàng sử dụng các chức năng ẩn danh (anonymous functions). Nó tạo điều kiện cho các lập
 * trình viên lập trình Functional và phát triển ứng dụng đơn giản hơn rất nhiều so với những phiên
 * bản trước đó. Nó cung cấp một cách rõ ràng và ngắn ngọn để đại diện cho một Functional Interface
 * sử dụng một biểu thức lamda.
 * 
 * Lambda Expression (biểu thức Lambda) có thể được định nghĩa là một hàm ẩn danh, cho phép người
 * dùng chuyển các phương thức làm đối số. Điều này giúp loại bỏ rất nhiều mã soạn sẵn.
 * 
 * Lambda Expression là một hàm không có tên và không thuộc bất kỳ lớp nào, không có phạm vi truy
 * cập (private, public hoặc protected), không khai báo kiểu trả về.
 * 
 * Tại sao sử dụng biểu thức Lambda?
 * - Cung cấp bản implement cho Functional interface.
 * - Viết ít code hơn.
 * - Hiệu quả hơn nhờ hỗ trợ thực hiện tuần tự (sequential) và song song (parallel) thông qua
 * Stream API.
 * 
 * Sử dụng biểu thức Lambda
 * Để sử dụng biểu thức lambda, chúng ta cần tạo Functional interface của
 * riêng mình hoặc sử dụng Functional interface do Java cung cấp. Một interface chỉ có 1 phương thức
 * trừu tượng duy nhất được gọi là Functional interface. Ví dụ: Runnable, callable, Comparator, …
 * 
 * Sử dụng Functional interface:
 * Trước Java 8: chúng ta tạo anonymous inner classes.
 * Từ Java 8: sử dụng biểu thức lambda thay vì các anonymous inner classes
 * 
 * @author Anhtu
 * 
 * Nhận thấy thằng này giống hệt Arrow Function trong Javascript
 */
public class LambdaExpressions {

    public static void main(String[] args) {
        List<String> languages = Arrays.asList("Java", "C#", "C++", "PHP", "Javascript");

        /*------------- First example -------------*/
        // No Lambda
        Collections.sort(languages, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str1.compareTo(str2);
            }
        });

        // Using Lambda
        Collections.sort(languages, (str1, str2) -> {
            return str1.compareTo(str2);
        });

        // More simple
        Collections.sort(languages, (str1, str2) -> str1.compareTo(str2));

        /*------------- 
         * Using Lambda in FunctionalInterface
         * Số lượng tham số của biểu thức Lambda phụ thuộc vào số lượng tham số của
         * phương thức trừu tượng của Functional Interface.
         * -------------*/
        // biểu thức Lambda không có tham số
        Sayable1 s1 = () -> "I have nothing to say";
        System.out.println(s1.say());

        // biểu thức Lambda có một tham số duy nhất
        Sayable2 s2 = (name) -> "Hello " + name;
        System.out.println(s2.say("Tuzaku"));

        // biểu thức Lambda có nhiều tham số, sử dụng hoặc không sử dụng từ khóa return
        Addable a1 = (int a, int b) -> {
            return a + b;
        };
        System.out.println(a1.add(5, 7));

        Addable a2 = (a, b) -> {
            return a + b;
        };
        System.out.println(a2.add(5, 7));

        Addable a3 = (a, b) -> (a + b);
        System.out.println(a3.add(5, 7));

        /*------------- biểu thức Lambda với vòng lặp Foreach -------------*/
        // No Lambda
        languages.forEach(new Consumer<String>() {
            @Override
            public void accept(String t) {
                System.out.println(t.toUpperCase());
            }
        });

        // Using Lambda
        languages.forEach(t -> {
            System.out.println(t.toUpperCase());
        });

        // More simple
        languages.forEach(t -> System.out.println(t.toUpperCase()));

        /*------------- 
         * truy cập các biến phạm vi bên ngoài từ các biểu thức lambda:
         * Việc truy cập các biến phạm vi bên ngoài từ các biểu thức lambda rất
         * giống với các đối tượng ẩn danh (anonymous objects). Bạn có thể
         * truy cập bất kỳ biến final, static hoặc biến chỉ được gán một lần.
         * Biểu thức Lambda throw một lỗi biên dịch, nếu một biến được gán một
         * giá trị lần thứ hai.
         *  -------------*/
        int demo = 10;
        Addable a4 = (a, b) -> (a + b) * demo;
        System.out.println(a4.add(5, 7));
    }
}


@FunctionalInterface
interface Sayable1 {
    public String say();
}

@FunctionalInterface
interface Sayable2 {
    public String say(String name);
}

@FunctionalInterface
interface Addable {
    int add(int a, int b);
}