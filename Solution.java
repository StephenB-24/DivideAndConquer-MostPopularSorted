import java.util.ArrayList;

public class Solution {
    /**
     *
     * @param data
     * @param <T>
     * @return
     */
    static <T extends Comparable<T>> T popularSorted(ArrayList<T> data)
    {
        return popularSortedHelper(data, 0, data.size() - 1);
    }

    /**
     *
     * @param data The sorted ArrayList to look through.
     * @param leftIndex The left-most index of the subarray
     * @param rightIndex The right-most index of the subarray
     * @param <T> The element type in the ArrayList.
     * @return The element that is the most popular. If there are multiple, only one is returned.
     */
    private static <T extends Comparable<T>> T popularSortedHelper(ArrayList<T> data, int leftIndex, int rightIndex)
    {
        // Test base case, where sub-array is 1 element. Return this element if true.
        if (leftIndex == rightIndex)
        {
            return data.get(leftIndex);
        }
        else
        {
            // Find the mid-point index.
            int midIndex = (leftIndex + rightIndex) / 2;

            // Recurse through left and right sub-arrays until base case is hit.
            T leftResult = popularSortedHelper(data, leftIndex, midIndex);
            T rightResult = popularSortedHelper(data, midIndex + 1, rightIndex);
            T middleResult = data.get(midIndex);

            // Using the recursive results, count the number of times each element occurs in the current sub-array.
            int leftResultCount = binarySearchLastIndex(data, leftResult, leftIndex, midIndex)
                    - binarySearchFirstIndex(data, leftResult, leftIndex, midIndex) + 1;
            int rightResultCount = binarySearchLastIndex(data, rightResult, midIndex + 1, rightIndex)
                    - binarySearchFirstIndex(data, rightResult, midIndex + 1, rightIndex) + 1;
            int middleResultCount = binarySearchLastIndex(data, middleResult, leftIndex, rightIndex)
                    - binarySearchFirstIndex(data, middleResult, leftIndex, rightIndex) + 1;

            // Compare the results and select the appropriate element to return.
            if (leftResultCount >= rightResultCount && leftResultCount > middleResultCount)
            {
                 return leftResult;
            }
            else if (rightResultCount > leftResultCount && rightResultCount > middleResultCount)
            {
                return rightResult;
            }
            else
            {
                return middleResult;
            }
        }
    }

    /**
     *
     * @param data
     * @param findMe
     * @param leftIndex
     * @param rightIndex
     * @param <T>
     * @return
     * @throws IndexOutOfBoundsException
     */
    // Get the middle element, if the element we are looking for is smaller, recurse left. Recurse right otherwise.
    // If the middle element is the correct element, check if it is the first one. If not, recurse left.
    private static <T extends Comparable<T>> int binarySearchFirstIndex(ArrayList<T> data, T findMe, int leftIndex, int rightIndex) throws IndexOutOfBoundsException
    {
        // Validate data
        if (rightIndex < leftIndex)
        {
            throw new IndexOutOfBoundsException("Right index cannot be less than the left index.");
        }
        else if (rightIndex > data.size() - 1)
        {
            throw new ArrayIndexOutOfBoundsException("Right index cannot be greater than the ArrayList");
        }
        else if (leftIndex < 0)
        {
            throw new ArrayIndexOutOfBoundsException("Left index cannot be less than 0.");
        }

        int midIndex = (leftIndex + rightIndex) / 2;

        if (leftIndex == rightIndex && !data.get(leftIndex).equals(findMe))
        {
            return -1;
        }
        else if (findMe.equals(data.get(midIndex)))
        {
            if (midIndex == leftIndex)
            {
                return leftIndex; // First occurrence is at left-most index
            }
            else if (data.get(midIndex - 1) != findMe)
            {
                return midIndex;
            }
            else
            {
                return binarySearchFirstIndex(data, findMe, leftIndex, midIndex - 1);
            }
        }
        else if (findMe.compareTo(data.get(midIndex)) < 0)
        {
            return binarySearchFirstIndex(data, findMe, leftIndex, midIndex - 1);
        }
        else
        {
            return binarySearchFirstIndex(data, findMe, midIndex + 1, rightIndex);
        }
    }

    /**
     *
     * @param data
     * @param findMe
     * @param leftIndex
     * @param rightIndex
     * @param <T>
     * @return
     */
    // Get the middle element, if the element we are looking for is smaller, recurse left. Recurse right otherwise.
    // If the middle element is the correct element, check if it is the last one. If not, recurse right.
    private static <T extends Comparable<T>> int binarySearchLastIndex(ArrayList<T> data, T findMe, int leftIndex, int rightIndex)
    {
        // Validate data
        if (rightIndex < leftIndex)
        {
            throw new IndexOutOfBoundsException("Right index cannot be less than the left index.");
        }
        else if (rightIndex > data.size() - 1)
        {
            throw new ArrayIndexOutOfBoundsException("Right index cannot be greater than the ArrayList");
        }
        else if (leftIndex < 0)
        {
            throw new ArrayIndexOutOfBoundsException("Left index cannot be less than 0.");
        }

        int midIndex = (leftIndex + rightIndex) / 2;

        if (leftIndex == rightIndex && !data.get(leftIndex).equals(findMe))
        {
            return -1;
        }
        else if (findMe.equals(data.get(midIndex)))
        {
            if (midIndex == rightIndex)
            {
                return rightIndex; // Last occurrence is at right-most index
            }
            else if (data.get(midIndex + 1) != findMe)
            {
                return midIndex;
            }
            else
            {
                return binarySearchLastIndex(data, findMe, midIndex + 1, rightIndex);
            }
        }
        else if (findMe.compareTo(data.get(midIndex)) < 0)
        {
            return binarySearchLastIndex(data, findMe, leftIndex, midIndex - 1);
        }
        else
        {
            return binarySearchLastIndex(data, findMe, midIndex + 1, rightIndex);
        }
    }
}
