import FizzBuzzStream.FizzBuzz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class FizzBuzzTests {

    private String[] expectedArray;

    @BeforeEach
    public void BaseSetup(){
        expectedArray = createFizzBuzz(IntStream.range(1, 20)).toArray(String[]::new);
    }

    @Test
    public void TestStreamCreation() {
        var inp = IntStream.range(1, 20);

        var student = FizzBuzz.createFizzBuzz(inp);
        assertNotNull(student, "Returned stream should not be null");

        var studentArray = student.toArray(String[]::new);
        assertArrayEquals(expectedArray, studentArray);
    }

    @Test
    public void TestLimitFirstFizzBuzz(){
        var student = FizzBuzz.findFirstFizzBuzz(Arrays.stream(expectedArray));
        var expected = Arrays.stream(expectedArray).filter(x -> x.matches("(Fizz|Buzz)*")).takeWhile(x -> !x.equals("FizzBuzz")).toArray(String[]::new);
        System.out.println(Arrays.toString(expected));
        assertNotNull(student, "Returned stream should not be null");
        assertArrayEquals(expected, student.toArray(String[]::new));
    }

    @Test
    public void TestLimitDifferentInput(){
        var input = new String[]{"1", "2", "FizzBuz", "BuzzFizz", "FizzBuzz", "FizzBuzz", "5", "6"};
        var student = FizzBuzz.findFirstFizzBuzz(Arrays.stream(input));
        var expected = Arrays.stream(input).filter(x -> x.matches("(Fizz|Buzz)*")).takeWhile(x -> !x.equals("FizzBuzz")).toArray(String[]::new);
        assertNotNull(student, "Returned stream should not be null");
        assertArrayEquals(expected, student.toArray());
    }

    @Test
    public void TestSumOfAllNumbers(){
        var student = FizzBuzz.SumOfAllNumbers(Arrays.stream(expectedArray));
        var expected = Arrays.stream(expectedArray).filter(x -> x.matches("[0123456789]*")).mapToInt(Integer::parseInt).sum();
        assertNotNull(student, "Returned stream should not be null");
        assertEquals(expected, student, "Sum of all numbers in the stream did not match");
    }

    @Test
    public void TestSumZero(){
        var student = FizzBuzz.SumOfAllNumbers(Stream.of("Fizz", "Buzz", "FizzBuzz", "0", "0000"));
        var expected = 0;
        assertNotNull(student, "Returned stream should not be null");
        assertEquals(expected, student, "Sum of all numbers in the stream did not match");
    }

    private static Stream<String> createFizzBuzz(IntStream input){
        return input.mapToObj(i -> {
            if (i % 3 == 0 && i % 5 == 0){
                return "FizzBuzz";
            } else if (i % 3 == 0){
                return "Fizz";
            } else if (i % 5 == 0){
                return "Buzz";
            } else {
                return Integer.toString(i);
            }
        });
    }
}
