import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class BasicsStream {
    public static void main(String[] args) {
        //java 8

        //lambda expression
        //it is an anonymous function with no name ,no return type and no access modifier

        //since MathOperation is a functional interface thus we can have lamda expression
        //if there is only single line then we dont even need return type
        MathOperation sumOperation= (i,j)-> i+j;
        System.out.println(sumOperation.operate(3,5));

        //some commonly used functional interface

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Predicate it (boolean value function)(accepts an expression)
        //predicate holds a condition and in a variable and we can check that condition/expression
        Predicate<Integer> isEven= x -> x % 2 == 0;
        System.out.println(isEven.test(7));


        Predicate<String> isWordStartWithA= x ->x.toLowerCase().startsWith("a");
        Predicate<String> isWordEndsWithY= x ->x.toLowerCase().endsWith("y");

        Predicate<String> fullValidName=isWordStartWithA.and(isWordEndsWithY);

        System.out.println(fullValidName.test("Auly"));
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Function it works for you and you pass some object it will work and return some output
        Function<Integer,Integer> squreIt = x -> x*x;
        Function<Integer,Integer> cubeIt = x -> x*x*x;

        //here first squreIt will apply first and then cubeIt
        System.out.println(squreIt.andThen(cubeIt).apply(2));
        //here first cubeIt will apply first and then squreIt
        System.out.println(squreIt.compose(cubeIt).apply(2));

        //whatever you supply it will return that again to you
        Function<Integer,Integer> function= Function.identity();
        System.out.println(function.apply(5));
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Consumer
        Consumer<List<Integer>> printList= x ->{
            for (int i: x) {
                System.out.println(i+" ");
            }
        };
        List<Integer> intList= Arrays.asList(1,2,3);
        printList.accept(intList);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       //Supplier
       // It does not takes in any parameter and still gives some out put
        Supplier<String> supplier_m= () -> "Hello World";


        // combined example

        Predicate<Integer> predicate = x -> x%2==0;
        Function<Integer,Integer> func = x -> x+x;
        Consumer<Integer> consumer = x -> System.out.println(x*2);
        Supplier<Integer> supplier = () ->10;

        if(predicate.test(supplier.get())){
            consumer.accept(func.apply(supplier.get()));
        }

        // above all functional interface are accepting single element
        // now we are going to disccuss about fuctional interface that accepts 2 parameters

        BiPredicate<Integer,Integer> biPredicate = (x,y) -> (x+y) % 2 == 0;
        BiFunction<String,String,Integer> biFunction =(x,y) -> (x+y).length();
        BiConsumer<String,Integer> biConsumer = (x,y) -> System.out.println(x+">>>"+y);

        if(biPredicate.test(10,10)){
            biConsumer.accept("Shreyans",biFunction.apply("My","jain"));
        }

        UnaryOperator<Integer> unaryOperator = x -> x*x;            //Replaces Function<Integer,Integer>
        BinaryOperator<Integer> binaryOperator = (x,y) -> x+y;      //Replaces Function<Integer,Integer,Integer>


        //Method Reference --> use method without invoking and in place of lamda expression
        List<String> nameList= Arrays.asList("shreyans","and","jain");

        nameList.forEach(x-> System.out.println(x));
        //this can also be written like this
        nameList.forEach(System.out::println);

        //Constructor Reference -->
        List<String> phoneList= Arrays.asList("a","b","c");

        phoneList.stream().map(x->new MobilePhone(x)).collect(Collectors.toList());
        //this can also be return as
        phoneList.stream().map(MobilePhone::new).collect(Collectors.toList());
    }
}

class MobilePhone{
    String name;
    MobilePhone(String name){
        this.name=name;
    }
}


interface MathOperation{
    int operate(int i ,int j);//consumer it takes in some input and work on it but return any out put
}


