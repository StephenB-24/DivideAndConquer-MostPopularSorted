import java.lang.reflect.Array;
import java.util.ArrayList;

public class Run {
    public static void main(String[] args) throws Exception {
        // First we will do some very basic testing, with three different examples:
        // Example One:   [1, 1, 2, 3]
        // Example Two:   [1, 2, 2, 3]
        // Example Three: [1, 2, 3, 3]

        ArrayList<Integer> testArrList1 = new ArrayList<>();
        testArrList1.add(1);
        testArrList1.add(1);
        testArrList1.add(2);
        testArrList1.add(3);
        System.out.println("Expected 1: " + Solution.<Integer>popularSorted(testArrList1));

        ArrayList<Integer> testArrList2 = new ArrayList<>();
        testArrList2.add(1);
        testArrList2.add(2);
        testArrList2.add(2);
        testArrList2.add(3);
        System.out.println("Expected 2: " + Solution.<Integer>popularSorted(testArrList2));

        ArrayList<Integer> testArrList3 = new ArrayList<>();
        testArrList3.add(1);
        testArrList3.add(2);
        testArrList3.add(3);
        testArrList3.add(3);
        System.out.println("Expected 3: " + Solution.<Integer>popularSorted(testArrList3));

        // Next we will confirm completeness by brute-force testing every example from length 1 to 30, using integers 1 through 5
        // Although it's not a proof, every edge case should be covered, and the algorithm should be scalable to any reasonable size.
        ArrayList<ArrayList<Integer>> bruteForcedExamples = BruteForce.CreateSortedExamples(1, 5, 30);
        ArrayList<ArrayList<Integer>> bruteForcedExamplesAnswers = BruteForce.FindValidAnswers(bruteForcedExamples);

        // Get the divide-and-conquer results for this example set.
        ArrayList<Integer> divideAndConquerAnswers = new ArrayList<>();
        for (int i = 0; i < bruteForcedExamples.size(); i++)
        {
            divideAndConquerAnswers.add(Solution.popularSorted(bruteForcedExamples.get(i)));
        }

        // Calculate and display difference count in the brute-force algo versus the divide-and-conquer.
        int errorCount = BruteForce.CountErrors(bruteForcedExamplesAnswers, divideAndConquerAnswers);
        System.out.println("Total incorrect results of the " + bruteForcedExamples.size() + " examples tested: " +
                errorCount);

        // Running the above, one should find that a rigorous examination of 324,631 examples was correct in every case
        // of the divide-and-conquer solution.

        // Check GitHub for a runtime examination of this solution using Master Theorem.
    }
}
