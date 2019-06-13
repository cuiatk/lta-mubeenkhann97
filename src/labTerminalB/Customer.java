package labTerminalB;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
		private String _name;
		private Vector<Rental> _rentals = new Vector<Rental>();
		public Customer (String name){
			_name = name;
		};
		public void addRental(Rental arg) {
			_rentals.addElement(arg);
		}
		public String getName (){
			return _name;
		};
		
		/*
		 * TODO 4		20 Marks
		 * Identify the bad smells in the statement method and refactor the code
		 * In case you add a new method also provide specifications for new method
		 * after refactoring all test cases must still be passed
		 * Commit after each change and include a meaningful message about the change you made
		 * e.g. Method Move methodName();
		 * 
		 */
		
		/**
		 * TODO 3		10 Marks
		 * Provide suitable specification for statement method 
		 */
		/**
		 * The program calculates and prints a statement of a customer's charges at a video store. There are
		 * three kinds of movies a customer can rent: 
		 * Regular
		 * children's
		 * new releases
		 * The program is told which movies a customer rented and for how many days. It then calculates
		 * the charges. The process for rent calculation depends on movie type and days rented. Details are
		 * as follows:
		 * 1. Regular movie has a base rent of 2 for up to two days. If the rented days exceed two, for
		 *	 each exceeding day 1.5 per day is added to base rent.
		 *2. New release move has a rent 3 per day.
		 *3. Children’s movie has a base rent of 1.5 for up to three days. If the rented days exceed
		 *		three, for each exceeding day 1.5 per day is added to base rent.
		 *4. For now, frequent renter points are not used for any discounts.
		 * 	customer can rent as many movies as he desires
		 * 
		 * The system also calculates frequent renter points (FRP). There is 1 FRP for each movie rented.
		 * However, if the customer rents new release for more than one day an additional FRP is awarded
		 * to the customer. System does NOT need to keep record of FRPs for each customer.
		 * The system prints charge statement for customer which consists of following information
		 * 1. Customer name
		 * 2. Movies name with charges
		 * 3. Total amount for all rentals
		 * 4. Frequent Renter points
		 */

		public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration<Rental> rentals = _rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		result = rentalRecord(totalAmount, frequentRenterPoints, rentals, result);
		return result;
	}
		private String rentalRecord(double totalAmount, int frequentRenterPoints, Enumeration<Rental> rentals,
				String result) {
			while (rentals.hasMoreElements()) {
				double thisAmount = 0;
				Rental each = (Rental) rentals.nextElement();
				//determine amounts for each line
				thisAmount = calculateAmount(thisAmount, each);
				// add frequent renter points
				frequentRenterPoints ++;
				// add bonus for a two day new release rental
				if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
						&&
						each.getDaysRented() > 1) frequentRenterPoints ++;
				//show figures for this rental
				result += "\t" + each.getMovie().getTitle()+ "\t" +
						String.valueOf(thisAmount) + "\n";
				totalAmount += thisAmount;
			}
			//add footer lines
			result = addFooter(totalAmount, frequentRenterPoints, result);
			return result;
		}
		private String addFooter(double totalAmount, int frequentRenterPoints, String result) {
			result += "Amount owed is " + String.valueOf(totalAmount) +
					"\n";
			result += "You earned " + String.valueOf(frequentRenterPoints)
			+
			" frequent renter points";
			return result;
		}
		private double calculateAmount(double thisAmount, Rental each) {
			switch (each.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				thisAmount += 2;
				if (each.getDaysRented() > 2)
					thisAmount += (each.getDaysRented() - 2) * 1.5;
				break;
			case Movie.NEW_RELEASE:
				thisAmount += each.getDaysRented() * 3;
				break;
			case Movie.CHILDRENS:
				thisAmount += 1.5;
				if (each.getDaysRented() > 3)
					thisAmount += (each.getDaysRented() - 3) * 1.5;
				break;
			}
			return thisAmount;
		}
}
