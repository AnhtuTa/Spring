package hello;

public class PassByRefDemo {

    // Pass by value
    static void test1(int a) {
        a = a + 5;
    }

    // Pass by reference
    static void test2(int[] arr) {
        arr[0] = arr[0] + 5;
    }

    // Pass by reference
    static void test3(Student st ) {
        st.setName(st.getName() + "_hehehe");
    }
    
    public static void main(String[] args) {
        int a = 10;
        test1(a);
        System.out.println(a);

        int[] arr = {1, 2, 3};
        test2(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        
        Student st = new Student("att");
        test3(st);
        System.out.println(st.getName());
    }
}

class Student {
    private String name;

    public Student(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
