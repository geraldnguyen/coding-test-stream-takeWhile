import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TakeWhile {
    /*
    Given a list of integers, returns a list of positive numbers from the first elements until and excluding the
    first non-positive number in the original list

    Extra: Implement takeWhile(positive) method using other Stream operations
     */
    public List<Integer> takeWhilePositive_streamLimit(List<Integer> integers) {
        var firstNonPositiveIdx = IntStream.range(0, integers.size())
                .filter(idx -> integers.get(idx) <= 0)
                .findFirst();

        if (firstNonPositiveIdx.isEmpty()) {
            return integers;
        }
        return integers.stream()
                .limit(firstNonPositiveIdx.getAsInt())
                .collect(Collectors.toList());
    }

    public List<Integer> takeWhilePositive_streamSubList(List<Integer> integers) {
        var firstNonPositiveIdx = IntStream.range(0, integers.size())
                .filter(idx -> integers.get(idx) <= 0)
                .findFirst();

        if (firstNonPositiveIdx.isEmpty()) {
            return integers;
        }
        return integers.subList(0, firstNonPositiveIdx.getAsInt());
    }

    public List<Integer> takeWhilePositive_loopSublist(List<Integer> integers) {
        int firstNonPositiveIdx = -1;
        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i) <= 0) {
                firstNonPositiveIdx = i;
                break;
            }
        }

        if (firstNonPositiveIdx < 0) {
            return integers;
        }
        return integers.subList(0, firstNonPositiveIdx);
    }

    public List<Integer> takeWhilePositive_streamMapFilter(List<Integer> integers) {
        List<Integer> result = new ArrayList<>();
        Map<String, Boolean> takeInFlag = new HashMap<>();
        takeInFlag.put("takeIn", true);

        return integers.stream()
                .map( i -> {
                    if (takeInFlag.get("takeIn") && i > 0) {
                        return i;
                    } else {
                        takeInFlag.put("takeIn", false);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<Integer> takeWhilePositive_streamFilter(List<Integer> integers) {
        Map<String, Boolean> takeInFlag = new HashMap<>();
        takeInFlag.put("takeIn", true);

        return integers.stream()
                .filter(i -> {
                    if (takeInFlag.get("takeIn") && i > 0) {
                        return true;
                    } else {
                        takeInFlag.put("takeIn", false);
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    public List<Integer> takeWhilePositive_streamForEach(List<Integer> integers) {
        List<Integer> result = new ArrayList<>();
        Map<String, Boolean> takeInFlag = new HashMap<>();
        takeInFlag.put("takeIn", true);

        integers.stream().forEach(i -> {
            if (takeInFlag.get("takeIn") && i > 0) {
                result.add(i);
            } else takeInFlag.put("takeIn", false);
        });

        return result;
    }

    public List<Integer> takeWhilePositive_loopExplicitStateManagement(List<Integer> integers) {
        List<Integer> result = new ArrayList<>();
        boolean takeIn = true;

        for (int i : integers) {
            if (takeIn && i > 0) {
                result.add(i);
            } else takeIn = false;
        }
        return result;
    }

    public List<Integer> takeWhilePositive_loopImplicitStateManagement(List<Integer> integers) {
        List<Integer> result = new ArrayList<>();

        for (int i : integers) {
            if (i > 0) {
                result.add(i);
            } else break;
        }
        return result;
    }
}
