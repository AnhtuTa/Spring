package design_pattern.creational.factory;

/*
 * Factory Pattern là một mẫu thiết kế tạo dựng (Creation Pattern), 
 * nó được sử dụng rộng rãi trong JDK và các Framework như Spring, Struts.
 * 
 * Factory Pattern được sử dụng khi có một class cha (super class) với 
 * nhiều class con (sub-class), dựa trên đầu vào và phải trả về 1 trong 
 * những class con đó.
 * 
 * Lợi ích của Factory Pattern
	- Cung cấp 1 hướng tiếp cận với Interface thay thì các cài đặt.
	- Mở rộng code dễ dàng hơn (Khi muốn mở rộng thêm 1 loại class con 
	  khác, ta chỉ việc thêm mới nó và sửa đầu vào trong Factory Method là được).
   
   Ví dụ: Một số class, method áp dụng Factory Pattern trong JDK
	- java.util.Calendar, ResourceBundle and NumberFormat là các class sử dụng Factory Pattern.
	- Method valueOf() trong một số wrapper class như Boolean, Integer, Double…
   Ví dụ sau tham khảo tại:
   https://www.tutorialspoint.com/design_pattern/factory_pattern.htm
   
   Factory pattern is one of the most used design patterns in Java. This type of design 
   pattern comes under creational pattern as this pattern provides one of the best ways 
   to create an object.

   In Factory pattern, we create object without exposing the creation logic to the 
   client and refer to newly created object using a common interface.
 */
public class Test {

	public static void main(String[] args) {
		ShapeFactory shapeFactory = new ShapeFactory();
		
		Shape s1 = shapeFactory.getShape(ShapeEnum.CICLE);
		Shape s2 = shapeFactory.getShape(ShapeEnum.SQUARE);
		
		System.out.println("Area of s1 = " + s1.getArea());
		System.out.println("Area of s2 = " + s2.getArea());
	}

}
