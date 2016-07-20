# ZuoraTest

## Task
Given a dataset that represents a user's navigation of a website, find the top N most frequently visited paths.

## Solution

1. Read logs line by line from input file
2. Use map to keep track paths by users. The key is user name, the value is a queue of size with page length (default is 3).
While the size of queue is equal to page length, poll out the first item, then add the current path to queue. Then use another 
map to count the frequency of current path.  
3. Use a min heap (size = N) to get top N most frequently traversed path. 
4  Print out results

## Testing

Here I tried several test cases:
test1 is normal case;
test2 is a empty file;
test3 has no available paths, because all traversed paths are not long enough.
test4 is more complex test cases.


