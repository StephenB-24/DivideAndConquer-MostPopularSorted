# Divide and Conquer - Find the Most Popular Element of a Sorted List
A divide and conquer appraoch to finding the most popular element in a sorted list.

Results are analyzed for correctness using brute-force to generate all valid possible answers.

The runtime of the algrorithm is also broken down and analyzed with Master Theorem in the following text.

<h2>The Problem</h2>

Given a sorted list of comparable elements, return the most popular.

The most popular element is the one that occurs the most, or as much as, any other element in the list.

<h2>Pseudocode</h2>

Given an array A, from A_l to A_r:

Base Case: 'l' = 'r'

Find left element by recursing on the left half of A
Find right element by recursing on the right half of A
Find the middle element by computing the midpoint m.

Use two modified binary search calls to find the number of occurences of the left element
Use two modified binary search calls to find the number of occurences of the right element
Use two modified binary search calls to find the number of occurences of the middle element

return the element with the greater number of occurences.

<h2>Files Included</h2>

- Solution.java => This is where the divide-and-conquer solution lies
- BruteForce.java => This is where problem generation, the brute-force solver (finds all valid solutions per example), and the brute-force solution checker lie.
- Run.java => The main method lies here. It calls methods from above in a specific fashion, and is thoroughly commented for the reader.

<h2>Runtime - Master Theorem</h2>

Ignoring constant time operations:

We recurse on the left half, and then the right half, these are both O(n/2).

Then we do a total of six binary search calls, each at O(log(n))

This means that our recurrence formula looks something like this: T(n) = 2T(n/2) + theta(log(n))

Defining the Master Theorem variables...

a = 2, b = 2, and the work done at each level is theta(log(n))

This is case 1 of master theorem, because f(n) = theta(log(n)) is bounded above by n^(log_2 of 2) = n

This means that T(n) = Theta(n)

![image](https://user-images.githubusercontent.com/76532502/146705517-aeedfa17-fae6-43e0-b23f-cc51e04720e4.png)
