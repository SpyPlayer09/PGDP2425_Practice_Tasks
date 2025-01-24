package FizzBuzzStream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FizzBuzz {

    ///
    /// Passe die Methode so an, dass sie einen Stream von Strings zurückgibt,
    /// wobei jedes Element passend auf die FizzBuzz-Regeln gemappt ist.
    /// Ein Beispiel für die Implementierung von FizzBuzz ist weiter unten gegeben.
    ///
    public static Stream<String> createFizzBuzz(IntStream input){
        return null;
    }

    ///
    /// Modifiziere den Stream so, dass nur noch NICHT Nummern enthalten sind und beschränke den Stream bis
    /// zum ersten FizzBuzz-Element.
    ///
    public static Stream<String> findFirstFizzBuzz(Stream<String> input){
        return null;
    }

    ///
    /// Gebe die Summe aller Zahlen im Stream zurück. Elemente, die keine Zahlen sind, sollen
    /// ignoriert werden.
    ///
    public static int SumOfAllNumbers(Stream<String> input){
        return 0;
    }

    public static String[] FizzBuzz(int n){
        String[] result = new String[n];
        for (int i = 1; i <= n; i++){
            if (i % 3 == 0 && i % 5 == 0){
                result[i-1] = "FizzBuzz";
            } else if (i % 3 == 0){
                result[i-1] = "Fizz";
            } else if (i % 5 == 0){
                result[i-1] = "Buzz";
            } else {
                result[i-1] = Integer.toString(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] fizzBuzz = FizzBuzz(20);
        for (String item : fizzBuzz){
            System.out.println(item);
        }
    }
}
