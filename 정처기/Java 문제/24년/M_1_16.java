public class M_1_16 {
    public static void main(String[] args) {
        classOne one = new classTwo(10);
        one.print(); // 21
    }

    static class classOne {
        int a, b;

        public classOne(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public void print() {
            System.out.println(a + b);
        }

    }

    static class classTwo extends classOne {
        int po = 3;

        public classTwo(int i) {
            super(i, i + 1);
        }

        public void print() { // Override를 했기 때문에 이게 호출된다.
            System.out.println(po * po);
        }
    }
}