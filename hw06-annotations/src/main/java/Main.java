import tests.TestClass;
import tests.TestLauncher;

public class Main {
    public static void main(String[] args) {
        TestLauncher<TestClass> testLauncher = new TestLauncher<>();
        testLauncher.launch(new TestClass());
    }
}
