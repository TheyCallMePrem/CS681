What kind of app you considered: 
The Movie Ticket App is a simplified application for renting and returning movie tickets. 
It consists of two classes: Movie and Customer. The Movie class represents a movie with a 
title and a number of available copies, 
while the Customer class represents a customer who can rent and return a movie.


Why the original code is not thread-safe: 
The original code suffered from a potential deadlock scenario. 
The Customer class and the Movie class both use locks to protect their shared resources. 
However, the ordering of acquiring the locks can lead to a situation where both the Customer and 
Movie objects are waiting for each other to release the locks they hold, resulting in a deadlock. 
Specifically, the Customer class first acquires its own lock, then attempts to acquire the Movie lock, 
while the Movie class first acquires its own lock, then attempts to acquire the Customer lock.


How you revised it to be thread-safe: 

To make the code thread-safe, I  made the following changes:

- Added a Condition object to both the Customer and Movie classes to allow waiting and signaling.
- Changed the rentMovie() method in the Customer class to wait on the movieReturned condition if the selected movie is not available.
- Changed the returnMovie() method in the Movie class to signal all waiting customers on the movieReturned condition when a movie is returned.
- Added proper synchronization using the lock, await(), and signalAll() methods to ensure that concurrent access to shared resources is properly handled.
