package exer.minshik.lambdaexpression;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class LambdaApp {
    public static void main(String[] args) {
        BinaryOperator<Integer> sum = (a, b) -> a + b;

    }

    private void run() {
        int baseNumber = 10;

        //Local class
        class LocalClass {
            void printBaseNumber() {
                System.out.println(baseNumber);
            }
        }
        // Lambda
        // Lambda Expression은 선언한 block과 scope를 공유한다.
        // final이거나 Effective final인(final로 선언 되지는 않았지만 값을 어디에서도 변경하지 않는 경우) 경우에만 참조할 수 있다.
        IntConsumer printNumber = (i) -> {
            System.out.println(i + baseNumber);
        };

        // Anonymous class
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(baseNumber);
            }
        };
    }
}
