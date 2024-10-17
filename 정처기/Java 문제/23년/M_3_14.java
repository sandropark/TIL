public class M_3_14 {
    public static void main(String args[]) {
        Parent obj = new Child();
        System.out.print(obj.compute(7)); // 2
    }

    static class Parent {
        int compute(int num) {
            if (num <= 1)
                return num;
            return compute(num - 1) + compute(num - 2);
        }
    }

    static class Child extends Parent {
        int compute(int num) {
            if (num <= 1)
                return num;
            return compute(num - 1) + compute(num - 3);
        }
    }
}