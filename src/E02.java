public class E02 {
    public static void main(String[] args) {
        B b = new B();
        b.foo();
    }
}

class A {
    public void foo() {
        System.out.println("foo in A");
        bar();
    }

    private void bar() {
        System.out.println("bar in A");
    }
}

class B extends A {
    @Override
    public void foo() {
        System.out.println("foo in B");
    }
}
