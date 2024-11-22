public class M_2_17 {
    public static void main(String args[]) {
        Conv obj = new Conv(3);
        obj.a = 5;
        int b = obj.func();   // 56
        System.out.print(obj.a+b);  // 61
    }

    static class Conv {
        int a;

        public Conv(int a) {
            this.a = a;
        }

        int func() {
            int b = 1;
            for (int i = 1; i < a; i++) { // 1~4
                b = a * i + b;   //6 -> 16 -> 31 -> 51
            }
            return a + b;   // 56
        }
    }
}
