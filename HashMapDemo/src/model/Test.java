package model;

public class Test {
	public static void main(String[] args) {
		Student st1 = new Student(1, "Long");
		Student st2 = new Student(1, "Long");
		Student st3 = st1;
		
		System.out.println(st1.equals(st2));
		System.out.println(st1.equals(st3));
	}

}
