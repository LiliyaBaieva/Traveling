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
2. View the trip
3. Make changes to your trip
4. About the program
5. Exit the program

## **Files**
- Main (start the program)
- GoTravel (Launch the main functions of the program)
- TripManager - class performs the function:
- TripManager display
- Editing a trip (edit/delete line)
- TripCreator (Creating a trip from the name and list of Expense expenses)
- Appart, with the appartCalc() method
- Transfer (travel calculation) -> transferCalc()
- LocalTransport (local transport, optional, we will ask) localTransportCalc()
- Food(food costs) foodCalc()
- Excursion (excursion costs) excursionCalc()
- Entertainment(entertainment, optional, we will ask) entertainmentCalc()
- Expense (expense item constructor)

## Project flowchart

![Program architecture](images/TripDiagramm.svg)