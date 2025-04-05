package PracticeStream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntermediateLevelSolution {
    public static void main(String[] args) {
        intListStream();
        stringListStream();
    }

    public static void intListStream(){
        List<Integer> list= Arrays.asList(4,2,3,8,6);
        //sum of list of integers
          int sum=list.stream().reduce((x,y)->x+y).get();
          System.out.println("Sum Of List of Integer :"+sum);
        //max and min value
          int maxVal= list.stream().max((x,y)->x-y).get();
        System.out.println("Max Value from stream :"+maxVal);

        int minVal= list.stream().min((x,y)->x-y).get();
        System.out.println("Max Value from stream :"+minVal);

        //average of numbers in list
        double avg= list.stream()
                .mapToInt(Integer::intValue)
                .average().getAsDouble();
        System.out.println("Average Of Stream :"+avg);

        //group a list into odd/even
        Map<Boolean,List<Integer>> myMap=list.stream()
                .collect(Collectors.partitioningBy(x-> x%2==0));

        System.out.println("Even List :"+myMap.get(Boolean.TRUE));
        System.out.println("Odd List :"+myMap.get(Boolean.FALSE));

        //Second highest number in list
        int secondMax = list.stream()
                .distinct().sorted((a,b)->b-a).skip(1).findFirst().get();
        System.out.println("Second Max : "+secondMax);


        //Top three numbers in list
        List<Integer> topThreeValues = list.stream()
                .distinct().sorted((a,b)->b-a)
                .limit(3).collect(Collectors.toList());
        System.out.println("Top 3 Values : "+ topThreeValues);

        //Partition list based on condition

    }
    public static void stringListStream(){
        //combine lists together
        List<String> list1=Arrays.asList("shreyans","jain");
        List<String> list2=Arrays.asList("is","here");

        List<String> combinedList= Stream.concat(list1.stream(),list2.stream())
                                   .collect(Collectors.toList());
        System.out.println("Combined List :"+combinedList);

        //segregate Employee based on department
        List<Employee> employeeList = Arrays.asList(
                new Employee("Suresh","IT",50000),
                new Employee("Mukesh","Security",30000),
                new Employee("Kamlesh","Sales",70000),
                new Employee("Pratap","IT",90000)
        );

        //Segregate Employee based on Department
        Map<String,List<Employee>>mapEmployee=employeeList.stream()
                .collect(Collectors.groupingBy(Employee::department));

        System.out.println("Employee from IT :"+ mapEmployee.get("IT"));
        System.out.println("Employee from IT :"+ mapEmployee.get("Sales"));
        System.out.println("Employee from IT :"+ mapEmployee.get("Security"));

        //List Of String To map based on length
        Map<Integer,List<String>> segregatedList= combinedList.stream()
                        .collect(Collectors.groupingBy(String::length));
        System.out.println("Segregated List :"+segregatedList);


        //Longest Word in a list
        String longestWord= combinedList.stream()
                .sorted((a,b)->b.length()-a.length())
                .findFirst().get();

        System.out.println("Longest Word in list :"+longestWord);

        //Concatenate all strings in list in single String
        String combinedString = combinedList.stream().collect(Collectors.joining(" "))
                .toString();
        System.out.println("Combined String :"+combinedString);

        //Remove all the null values
        List<String> listWithNull=Arrays.asList("shreyans",null,"is",null,"back","in",null,"action");
        List<String> listWithNoNull= listWithNull.stream()
                .filter(x->x != null).collect(Collectors.toList());
        System.out.println("List with no null value :"+listWithNoNull);


    }
}

record Employee(String name,String department,int salary){}


