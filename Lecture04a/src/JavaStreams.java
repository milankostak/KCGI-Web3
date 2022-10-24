import java.util.ArrayList;
import java.util.List;

public class JavaStreams {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        List<Integer> collected = numbers.stream().filter((number) -> number > 3).toList();
        System.out.println(collected.size());
    }

}
