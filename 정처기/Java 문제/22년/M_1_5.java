public class M_1_5 {
    public static void main(String args[]) {
        // Thread t1 = new Thread(new (가)()); // Car
        // t1.start();
    }

    static class Car implements Runnable {
        int a;

        public void run() {
            try {
                while (++a < 100) {
                    System.out.println("miles traveled :" + a);
                    Thread.sleep(100);
                }
            } catch (Exception E) {
            }
        }
    }
}
