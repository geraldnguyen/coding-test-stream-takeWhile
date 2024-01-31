import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TakeWhileTest {

    @ParameterizedTest
    @ValueSource(strings = {
        "",                 // empty list
        "1",                // single element, entire list
        "1, 2, 3",          // multiple elements, entire list
        "1, 2, 3, -1",      // multiple elements, partial list, negative end
        "1, 2, 3, 0",      // multiple elements, partial list, 0 end
        "1, 2, -1, 3",      // multiple elements, partial list, negative middle
        "1, 2, 0, 3",      // multiple elements, partial list, 0 middle
        "0, 2, 0, 3",      // multiple elements, 0 front
        "-123, 2, 0, 3",      // multiple elements, negative front
    })
    void takeWhilePositive(String integersCsv) {
        List<Integer> list = Collections.emptyList();
        if (integersCsv != null && !integersCsv.isBlank()) {
            list = Arrays.stream(integersCsv.split(",\\s*")).map(Integer::parseInt).collect(Collectors.toList());
        }


        assertEquals(
                list.stream().takeWhile(i -> i > 0).collect(Collectors.toList()),
                (new TakeWhile()).takeWhilePositive_loopImplicitStateManagement(list)
        );

        assertEquals(
                list.stream().takeWhile(i -> i > 0).collect(Collectors.toList()),
                (new TakeWhile()).takeWhilePositive_loopExplicitStateManagement(list)
        );

        assertEquals(
                list.stream().takeWhile(i -> i > 0).collect(Collectors.toList()),
                (new TakeWhile()).takeWhilePositive_streamForEach(list)
        );

        assertEquals(
                list.stream().takeWhile(i -> i > 0).collect(Collectors.toList()),
                (new TakeWhile()).takeWhilePositive_streamFilter(list)
        );

        assertEquals(
                list.stream().takeWhile(i -> i > 0).collect(Collectors.toList()),
                (new TakeWhile()).takeWhilePositive_streamMapFilter(list)
        );

        assertEquals(
                list.stream().takeWhile(i -> i > 0).collect(Collectors.toList()),
                (new TakeWhile()).takeWhilePositive_loopSublist(list)
        );

        assertEquals(
                list.stream().takeWhile(i -> i > 0).collect(Collectors.toList()),
                (new TakeWhile()).takeWhilePositive_streamSubList(list)
        );

        assertEquals(
                list.stream().takeWhile(i -> i > 0).collect(Collectors.toList()),
                (new TakeWhile()).takeWhilePositive_streamLimit(list)
        );
    }
}