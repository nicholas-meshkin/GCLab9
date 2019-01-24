package gcLab9;

//import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import gcLab9.Validator;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class GCLab9 {

	public static void main(String[] args) {

		Scanner scnr = new Scanner(System.in);
		String item1 = "item1";
		String item2 = "item2";
		String item3 = "item3";
		String item4 = "item4";
		String item5 = "item5";
		String item6 = "item6";
		String item7 = "item7";
		String item8 = "item8";

		String userCont = "y";
		String userOrder;
		int userNum;

		List<String> itemsOrdered = new ArrayList<>();
		List<Double> itemPrices = new ArrayList<>();
		List<Integer> itemAmounts = new ArrayList<>();

		Map<String, Double> itemList = new HashMap<>();

		itemList.put(item1, 1.00);
		itemList.put(item2, 2.00);
		itemList.put(item3, 3.00);
		itemList.put(item4, 4.00);
		itemList.put(item5, 5.00);
		itemList.put(item6, 6.00);
		itemList.put(item7, 7.00);
		itemList.put(item8, 8.00);
		
		System.out.println("Welcome to the market!");
		System.out.println("Item           Price        ");
		System.out.println("=============================");
		for (String name : itemList.keySet()) {
			String key = name;
			Double value = itemList.get(name);
			System.out.printf("%-15s", key);
			System.out.printf("$%-13.2f", value);
			System.out.println();
		}

		do {
			System.out.println("What item would you like to order?");

			userOrder = scnr.nextLine();
			if (itemList.containsKey(userOrder)) {
				itemPrices.add(itemList.get(userOrder));
				itemsOrdered.add(userOrder);
//				System.out.println("How many would you like?");
				userNum = Validator.getInt(scnr, "How many would you like?", 1, 20);
				itemAmounts.add(userNum);
//				if() {}
				System.out.printf(userNum + " " + userOrder + " added to cart at $" + "%.2f", itemList.get(userOrder));
				System.out.println(" each.");
//				System.out.println(itemsOrdered); // tester
//				System.out.println(itemPrices); // tester
			} else {
				System.out.println("That is not an available item.");
			}
			
			userCont = Validator.getString(scnr, "Would you like more? Press \"y\" to continue.");
		} while (userCont.equalsIgnoreCase("y"));
		
//		double orderTotal = 0;
//		for (int i = 0; i < itemsOrdered.size(); i++) {
//			orderTotal += itemPrices.get(i);
//		} did this with a method instead

		if (itemsOrdered.size() > 0) {
			System.out.println("Thank you for your order!");
			System.out.println("Here's what you got:");
			for (int i = 0; i < itemsOrdered.size(); i++) {
				System.out.printf(itemAmounts.get(i) + " " + "%-15s", itemsOrdered.get(i));
				System.out.printf("$%-13.2f", itemPrices.get(i)*itemAmounts.get(i));
				System.out.println();
			}
			System.out.printf("Order total was $" + "%.2f", doubleSum(itemPrices,itemAmounts));
			System.out.println();
			System.out.printf("Average price per item in order was $" + "%.2f", doubleAvg(itemPrices,itemAmounts));
			System.out.println();
			System.out.printf(
					"The most expensive item you purchased was "
							+ itemsOrdered.get(itemPrices.indexOf(maxDouble(itemPrices))) + " for $" + "%.2f",
					maxDouble(itemPrices));
			System.out.println();
			System.out.printf(
					"The least expensive item you purchased was "
							+ itemsOrdered.get(itemPrices.indexOf(minDouble(itemPrices))) + " for $" + "%.2f",
					minDouble(itemPrices));
			System.out.println();
			System.out.println("Goodbye!");
		} else {
			System.out.println(
					"Well get outta here then if you're not gonna order anything. See the sign? It says NO LOITERING.");
		}
	}

	public static Double maxDouble(List<Double> input) {
		Double max = input.get(0);
		for (Double item : input) {
			if (item > max) {
				max = item;
			}
		}
		return max;
	}

	public static Double minDouble(List<Double> input) {
		Double min = input.get(0);
		for (Double item : input) {
			if (item < min) {
				min = item;
			}
		}
		return min;
	}

	public static Double doubleSum(List<Double> input) {
		Double total = 0.00;
		for (Double item : input) {
			total += item;
		}
		return total;
	}
	public static Double doubleSum(List<Double> input, List<Integer> multiplier) {
		Double total = 0.00;
		for (int i=0; i<input.size();i++) {
			total += (input.get(i)*multiplier.get(i));
		}
		return total;
	}

	public static Double doubleAvg(List<Double> input) {
		Double total = 0.00;
		for (Double item : input) {
			total += item;
		}
		Double avg = total / input.size();
		return avg;
	}
	public static int sumItems(List<Integer> input) {
		int total = 0;
		for (Integer item : input) {
			total += item;
		}
		return total;
	}
	public static Double doubleAvg(List<Double> input, List<Integer> multiplier) {
		Double total = 0.00;
		for (int i=0; i<input.size();i++) {
			total += (input.get(i)*multiplier.get(i));
		}
		Double avg = total / sumItems(multiplier);
		return avg;
	}
}
