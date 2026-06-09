Here are some of the requirements for this assignment:

MovieCollection class:
1) Add a private method sortCollection() to sort the movies. Sorts the art alphabetically by production
company. If two movies were made by the same company, they should be sorted by year,
newest first.
2) Modify the MovieCollection constructor so that it calls sortCollection() after loading the file.
3) Modify the printCollection() method so that:
  a. The production company is printed in the first column and year in the second column.
  b. printCollection should only print the production company name once. 
  c. If both the company and year are the same as the previous movie, then neither the
  company or the year should be shown for that movie: only the title and duration should
  be displayed, lined up with the appropriate columns.
4) Create a method binarySearch that takes the name of the production company as a parameter
and returns a Movie object. If there is no movie from a company with that name, this
method should return null.

Main method: 
1) In main, created a MovieCollection object by calling the constructor.
2) Call printCollection()
3) Call endReport() the database is saved to the file correctly.
4) Use a loop to ask the user for a production company name. This should display the name of the
company and the title of the movie if one is found, or a message that no movie was found for
that company. Asks the user if they want to search for another company, and repeat this process
if they choose yes.
