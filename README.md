Wildcards
=========

The purpose of the code was to accept wildcards in the directory path given as input to give a output of a list of directories that satisfy the input path

Descritption:
The purpose of this code is to accept a directory path with wildcards and process it to generate a list of valid directories which would contain the non-wildcard folder names in the correct position as positioned in the original directory path. In order to achieve this, I start by splitting the directory path into individual folders and storing it into an array of String. This array is passed as one of the parameters to the method _getDirectoryList I created as a recursive function. It starts with first element of array and checks the next element to it, if it is a folder i.e. non-wildcard entry, then it builds a path by appending it and passing the path for recursion, also the counter of array is incremented. If it was a wildcard, then it gets all the directories at first level to the parent and proceeds with recursion in the same way.
If the path is valid and has achieved the depth, it is put in the directory list. The end result of this is a list of desired directories.

Input:  D:\parent\*\F22
Output:  	D:\parent\ABC\F22
		D:\parent\DEF\F22

Wildcards Accepted:
*  <-- Any number of characters in the folder
?  <-- Specific number of characters in the folder

I carried on the testing by executing following scenarios which gave desired results:

No.	Directory Path	No. of wildcards
1	D:\parent\	0
2	D:\parent\*\	1
3	D:\parent\?\	1
4	D:\parent\???\	3
5	D:\parent\*\F22	1
6	D:\parent\*\F22\*\F30	2
7	D:\parent\*\F22\?\F30	2
8	D:\parent\*\F22\???\F30\*\F15	4
9	D:\parent\*\*\???\F30\*\F15	5
10	D:\parent\*\*\???\*\*\F15	6
11	D:\parent\*\*\???\*\?\F15	7
12	D:\parent\*\*\???\*\???\F15	9
13	D:\ parent \*\*\???\*\???\F15	10
14	D:\ parent \??\??\???\??\???\F15	13
15	D:\ parent \???\???\???\???\???\F15	16
16	D:\*\*\F22\???\F30\*\F15	6
17	D:\*\*\*\*\*\*\F15	6
