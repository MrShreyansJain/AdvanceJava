package PracticeStream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicLevelSolutions {
    public static void main(String[] args) {
        streamWithListOfInt();
        streamWithListOfString();
    }

    public static void streamWithListOfInt(){
        //Creating stream with list fof Integer
        List<Integer> list= Arrays.asList(1,8,3,6);
        Stream<Integer> streamList=list.stream();

        //Converting Stream Back into Integer List
        List<Integer> convertedStreamList=streamList.collect(Collectors.toList());
        System.out.println(convertedStreamList);

        //Count of elements in a stream
        long count = list.stream().count();
        System.out.println("Element count from list "+count);

        //Filter Even No from the List
        List<Integer> evenList= list.stream()
                .filter(x->x%2==0).collect(Collectors.toList());
        System.out.println("Even no list "+evenList);

        //Sort list of integer
        List<Integer> sortedAscList = list.stream()
                .sorted().collect(Collectors.toList());
        System.out.println("Sorted Ascending List :"+sortedAscList);

        List<Integer> sortedDecList = list.stream()
                .sorted((a,b)->b-a).collect(Collectors.toList());
        System.out.println("Sorted Descending List :"+sortedDecList);

        //Limit a stream to first 10 element
        List<Integer> first10EvenList = Stream
                .iterate(1,x->x+1).filter(x->x%2==0)
                .limit(10).collect(Collectors.toList());
        System.out.println("First 10 even no from infinite list :"+first10EvenList);

        //Stream which skip first 3 elements
        List<Integer> skipList= list.stream().skip(3).collect(Collectors.toList());
        System.out.println("List after skipping first 3 Elements :"+skipList);

        //First element from Stream
        int firstElement = list.stream().findFirst().get();
        System.out.println("First Element Form List :"+firstElement);

        //Check all values match
        boolean isAllConditionMatch= list.stream().allMatch(e-> e>0);
        System.out.println("All value match :"+ isAllConditionMatch);

        //Check if any values match
        boolean isAnyConditionMatch= list.stream().anyMatch(x -> x>10);
        System.out.println("All value match:"+ isAnyConditionMatch);

        //generate Infinite stream of random numbers
        //List<String> randomList = Stream.generate()
    }



    public static void streamWithListOfString(){
        //List Of String Into UpperCase
        List<String> list = Arrays.asList("shreyans","aman","manas","aman");
        List<String> upperCaseList = list.stream()
                .map(x -> x.toUpperCase()).collect(Collectors.toList());
        System.out.println("Values in Uppercase :"+upperCaseList);

        //remove duplicate element form the list
        List<String> removedList =list.stream().distinct().collect(Collectors.toList());
        System.out.println("Values distinct :"+removedList);

        //collect data from stream into set
        Set<String> streamSet = list.stream().collect(Collectors.toSet());
        System.out.println("Set of Stream :"+streamSet);

        //Creating Stream from an Array
         int [] intArray= {1,8,96,35};
         int[] evenArray=Arrays.stream(intArray).filter(x->x%2==0).toArray();
         System.out.println("Even Array of Stream :"+Arrays.toString(evenArray));
    }

}
