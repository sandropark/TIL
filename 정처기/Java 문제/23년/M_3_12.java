public class M_3_12 {
    static class Person {
        private String name;

        public Person(String val) {
            name = val;
        }

        public static String get() {
            return name; // 오류
        }

        public void print() {
            System.out.println(name);
        }
    }

    public static void main(String[] args) {
        Person obj = new Person("Kim");
        obj.print();
    }
}
