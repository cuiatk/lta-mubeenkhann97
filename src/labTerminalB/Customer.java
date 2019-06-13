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
		 * Calculates rental for a customer on the basis of movies he/she selected and the
		 * Rental according to new release and customer.
		 * If customer added more than 1 rental then this function will calculate according to movie type e.g.
		 * if regular adds 2 points and multiplies with 1.5 if days are more than 2
		 * if new release multiplies with 3
		 * if children adds 1.5 points and multiplies with 1.5 if days more than 3
		 * @return result the full receipt of the rents.
		 */

		public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration<Rental> rentals = _rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasMoreElements()) {
			double thisAmount = 0;
			Rental each = (Rental) rentals.nextElement();
			//determine amounts for each line
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
		result += "Amount owed is " + String.valueOf(totalAmount) +
				"\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
		+
		" frequent renter points";
		return result;
	}
}
