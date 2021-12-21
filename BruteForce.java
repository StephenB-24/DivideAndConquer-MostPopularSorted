import java.util.ArrayList;

public class BruteForce {
    /**
     * Creates an ArrayList of ordered permutations of integers, repetition allowed.
     *
     * @return An ArrayList of ArrayLists that are all Integer combinations integers 1 through 3
     *         from lengths 1 through 5.
     */
    public static ArrayList<ArrayList<Integer>> CreateSortedExamples(int smallestNumber, int largestNumber, int maxLength)
    {
        ArrayList<ArrayList<Integer>> examples = new ArrayList<>();

        for (int i = 0; i < maxLength; i++)
        {
            // If the method has just been called, make the initial array.
            if (examples.size() == 0)
            {
                for (int j = smallestNumber; j <= largestNumber; j++)
                {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.add(j);
                    examples.add(tmp);
                }

                continue; // Skip the remaining code, the first iteration is special.
            }

            // Add integers from smallestNumber through largestNumber to new ArrayLists that are clones of the largest
            // sized ArrayLists within the ArrayList that is to be returned ('example').
            ArrayList<ArrayList<Integer>> currentExamples = (ArrayList<ArrayList<Integer>>)examples.clone();
            for (int j = 0; j < currentExamples.size(); j++)
            {
                if (currentExamples.get(j).size() == i)
                {
                    for (int q = smallestNumber; q <= largestNumber; q++)
                    {
                        if (currentExamples.get(j).get(i - 1) <= q)
                        {
                            ArrayList<Integer> tmp = (ArrayList<Integer>) currentExamples.get(j).clone();
                            tmp.add(q);
                            examples.add(tmp);
                        }
                    }
                }
            }
        }

        return examples;
    }

    /**
     * Given lists of numbers, finds all valid "popular" elements.
     * For example, [1, 2, 3, 4] can have 1, 2, 3, and 4 all as valid answers.
     * Likewise, [1, 1, 2, 3] can only have 1 as a valid answer.
     *
     * @param examples An ArrayList containing ArrayLists of Integers, which are integer lists.
     * @return An ArrayList which contains ArrayLists, where each element is a valid answer.
     */
    public static ArrayList<ArrayList<Integer>> FindValidAnswers(ArrayList<ArrayList<Integer>> examples)
    {
        ArrayList<ArrayList<Integer>> validAnswers = new ArrayList<>();

        for (int i = 0; i < examples.size(); i++)
        {
            ArrayList<Integer> currentExample = examples.get(i);
            ArrayList<Integer> numbersUsed = new ArrayList<Integer>();
            ArrayList<Integer> counts = new ArrayList<>();

            for (int j = 0; j < currentExample.size(); j++)
            {
                if (!numbersUsed.contains(currentExample.get(j)))
                {
                    numbersUsed.add(currentExample.get(j));
                    counts.add(0);
                }
            }

            for (int j = 0; j < currentExample.size(); j++)
            {
                int currentElement = currentExample.get(j);

                for (int k = 0; k < numbersUsed.size(); k++)
                {
                    if (numbersUsed.get(k) == currentElement)
                    {
                        int currCount = counts.get(k);
                        currCount++;
                        counts.set(k, currCount);
                        break;
                    }
                }
            }

            int maxIndex = 0;
            for (int j = 0; j < counts.size(); j++)
            {
                if (counts.get(j) > counts.get(maxIndex))
                {
                    maxIndex = j;
                }
            }

            // For every count that maxes the max count, add each of these valid values to the final ArrayList.
            ArrayList<Integer> tmpValidAnswers = new ArrayList<>();
            for (int j = 0; j < counts.size(); j++)
            {
                if (counts.get(j) == counts.get(maxIndex))
                {
                    tmpValidAnswers.add(numbersUsed.get(j));
                }
            }

            // Add the most popular number(s) to the validAnswers ArrayList (each are valid answers).
            validAnswers.add(tmpValidAnswers);
        }

        return validAnswers;
    }

    /**
     *  This function counts the number of times that the newly made list of answers, from something like that of the
     *  divide-and-conquer method creates, does not match the 'bruteForcedAnswers' list of accetable answers.
     *
     * @param bruteForcedAnswers This is a list of lists, like that obtained from "FindValidAnswers", where each list
     *        is one containing all the valid answers to the corresponding examples.
     * @param answersToCheck This is a newly created list, like that of which the divide-and-conquer algorithm creates,
     *        and is the list that the method will test for accuracy against 'bruteForcedAnswers'
     * @param <T> The comparable element type that each element of the lists will use.
     * @return The number of times that an entry from 'answersToCheck' does not match the 'bruteForcedAnswers' list of
     *         acceptable answers.
     * @throws Exception If the 'bruteForcedAnswers' and 'answersToCheck' do not match, an error is thrown.
     */
    public static <T extends Comparable<T>> Integer CountErrors(ArrayList<ArrayList<T>> bruteForcedAnswers, ArrayList<T> answersToCheck) throws Exception
    {
        if (answersToCheck.size() != bruteForcedAnswers.size())
        {
            throw new Exception("The answers to be checked do not match the size of the answer key.");
        }

        int totalErrors = 0;

        for (int i = 0; i < answersToCheck.size(); i++)
        {
            ArrayList<T> currentAnswerKey = (ArrayList<T>) bruteForcedAnswers.get(i);

            for (int j = 0; j < currentAnswerKey.size(); j++)
            {
                if (currentAnswerKey.get(j) == answersToCheck.get(i))
                {
                    break;
                }

                // The last element was tested and no match was found, this is incorrect.
                if (j == currentAnswerKey.size() - 1)
                {
                    totalErrors++;
                }
            }
        }

        return totalErrors;
    }
}
