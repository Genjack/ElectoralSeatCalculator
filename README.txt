NAME: Tim J. Dempsey
STUDENT ID: 19390664
UNIT AND TIME: DSA, Tuesdays @ 12pm

====
README FILE FOR GOVERNMENT.JAVA
====

"The American Republic will endure until the day Congress discovers that it can
 bribe the public with the public's money"   - Alexis De Tocqueville

Directory Contents:

Java files:
    > Candidate.java                > DSAHashTable.java
    > DSALinkedList.java            > File.java
    > Format.java                   > Menu.java
    > SeatChallenger.java           > Sorts.java
    > TestClass.java                > User.java
    > Utility.java                  > Validate.java
    > Government.java 

Text files:
    > hc.csv                        > hs.csv
    > testCases.txt                 > testLoad.txt
    > testMarginal.txt              > testSeats.txt
    > tfNoBuffer                    > AirportDist1.0.csv
    > ElectDist1.1.csv

====
HOW TO RUN THE PROGRAM
====

Please run 'java Government' after compiling. The program functions with a menu,
which provides you with different options for various functions. The menu is
operated with the number keys, as shown in the prompts. The files needed are
hardcoded; there are some testfiles - testLoad for part 1/2 and testSeats for
part 3 - if you would like to swap them out.

====
FILE DESCRIPTIONS
====

Candidate.java:
    This a container class for a Candidate object, which is constructed from 
    file out of the information provided in the two large government csv files.
    It is aggregated by the SeatChallenger class, and thus forms a classfield of
    that class.

DSAHashTable.java:
    This is a hashtable data structure, which was designed for Practical 8 of 
    DSA. It serves a small purpose in fulfilling part 3 of this assignment.

DSALinkedList.java
    This is the most abused ADT that I have, and it holds a series of methods
    needed to create and manipulate a linked list, as created for practical 5.

File.java:
    Contains all methods related to file I/O for the assignment, as well as the
    methods required to construct both the Candidate and SeatChallenger objects,
    which are both read from file so I keep them in here.

Format.java:
    This class is where I store methods related to the preparation of the data
    structures and ADTs for use in the various tasks. Methods related to
    filtering and ordering are mostly stored here, with the exception of the 
    sort algorithms themselves.

Menu.java:
    Serves as a homebase class, where the user is immediately sent after the
    program is initiated. Contains a large menu within a case statement for
    guiding the flow of the program, and allows the user to view how the 
    program runs at a glance.

SeatChallenger.java:
    The second of two Object container classes, that is created through the 
    hs.csv file in part 3. Aggregates the Candidate class, and adds fields for
    voting.

Sorts.java:
    Kind of like the java algorithm version of Australia's PMs: an excessive
    number of very similar roles one after the other. Each mergeSort, of which
    there are a few, contains slight variations on the others, due to the 
    calling class and the responsible methods.

TestClass.java:
    A generic testing class that produces objects purely for testing. Based on
    great American Civil War Generals.

User.java:
    Generic user input class so handy I've almost forgotten how to do user input
    without it. Created last semester for OOPD, used lovingly ever since. I
    didn't include a test harness for this class because it's tested implicitly
    everywhere else.

Utility.java:
    An auxiliary class for Format.java, and acts as a sort of Getter wrapper
    class for any methods that require more complex methods to retrieve the
    necessary information. Also contains the main function to search the list
    for a particular candidate as required in part 2.

Validate.java:
    A small validation class used for parsing user input when ordering and
    filtering. Difficult to guarantee validity of search terms in this context,
    but effectively acts as a soft barrier before the cruel and cold exceptions.

Government.java:
    The executable file which should be run to initiate the program. Simply
    calls Menu.run().

DSAGraph.java:
    The code I have written for my graph prac, plus an Edge class created more
    recently. Not really used in the assignment.

====
TEXT FILE DESCRIPTIONS
====

hc.csv: The csv file containing the national list of candidates.
hs.csv: The csv file containing the information on Seats. Both csvs renamed for
    brevity.
testCases/testLoad/testSeats/testMarginal.txt: Various shortened versions of the
    above csv files used during testing.
tfNoBuffer: A test file without the two lines I normally skip at the top.
Elect/Airport distances: Necessary assignment spec files.

DesignNotes.txt/notes.txt/AssignmentNotes.txt: These are just note files that
I made during my work to help focus, and may provide insight into my thought
processes if you're interested.
