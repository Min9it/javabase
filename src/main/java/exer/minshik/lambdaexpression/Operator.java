package exer.minshik.lambdaexpression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class Operator {
    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        Integer i = 0;
        while(i<10) {
//            String tmpstr = Integer.toString(++i);
            strList.add("iterator : " + ++i);
        }

        Consumer<String> stringConsumer = s ->

        strList.stream().forEach(System.out::println);
    }
}
