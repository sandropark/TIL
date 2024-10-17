public class M_3_1 {
    public static void main(String[] args) {
        A b = new B();
        b.paint(); // B D C D
        b.draw(); // D
    }

    static class A {
        public void paint() {
            System.out.print("A");
            draw();
        }

        public void draw() {
            System.out.print("B");
            draw(); // 서브 클래스(B)의 draw 호출
        }
    }

    static class B extends A {
        public void paint() {
            super.draw();
            System.out.print("C");
            this.draw();
        }

        public void draw() {
            System.out.print("D");
        }
    }
}