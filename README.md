#TravelCost

Calculation of the cost of a trip per person / per company

### Menu:
1. Add a trip from expense items{Name, cost, currency-const(selected)}
   List of expense items from the expense item class.
- [1] Housing (price for 1 person or full housing)
- [2] Directions (public transport /
  car (take into account, capacity 5 people), will there be toll roads)
- [3] Will you use public transport?
- [4] Food (Will you eat in public catering / cook yourself (average cost of purchasing food
  for a week and for how many people, to calculate the cost of food for one day))
- [5] Average excursion price, number of excursions
- [7] Other entertainment
2. View all trips in general table and in table with total price
3. Make changes to your trip
4. Delete trip from a list of trips
5. About the program
6. Exit the program

## **Files**
- Main (start the program)
- Runner (Launch the main functions of the program)
- TripManager - class performs the function:
- TripManager display
- Trip class which implements interface tripInt with methods: create, edit, delete. 
- TripDto as a model for trip.
- Calculator class, there exist all mathematics functions and question to receive a data.
- FileManager to manage file with trips, it helps us write file and read file, when we add trip or change
  or delete trip.
- Helper class Table with inner classes to make a structure date when we display a trips. 

[//]: # (## Project flowchart)

[//]: # ()
[//]: # (![Program architecture]&#40;images/TripDiagramm.svg&#41;)