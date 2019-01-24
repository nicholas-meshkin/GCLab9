package gcLab9;

import java.util.ArrayList;
import java.util.Scanner;
import gcLab9.Validator;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class GCLab9 {

	public static void main(String[] args) {

		// Specs: All specs and extended challenges included except for repeating the
		// menu (which was optional) and
//					alternate menu entry system using only letter or number (because I'm tired and don't feel like it)
//			Prices from https://gizmodo.com/heres-how-much-body-parts-cost-on-the-black-market-5904129

		Scanner scnr = new Scanner(System.in);
		String item1 = "Gallbladder";
		String item2 = "Kidney";
		String item3 = "Stomach";
		String item4 = "Sq. inch of skin";
		String item5 = "Spleen";
		String item6 = "Heart";
		String item7 = "Pair of Eyeballs";
		String item8 = "Small Intestine";

		String userCont = "y";
		String userOrder;
		int userNum;

		List<String> itemsOrdered = new ArrayList<>();
		List<Double> itemPrices = new ArrayList<>();
		List<Integer> itemAmounts = new ArrayList<>();

		Map<String, Double> itemList = new HashMap<>();

		itemList.put(item1, 1219.00);
		itemList.put(item2, 262000.00);
		itemList.put(item3, 508.00);
		itemList.put(item4, 10.00);
		itemList.put(item5, 508.00);
		itemList.put(item6, 119000.00);
		itemList.put(item7, 1525.00);
		itemList.put(item8, 2519.00);

		System.out.println("Welcome to Fred's Black Market Organs!");
		System.out.println("Item                Price        ");
		System.out.println("=================================");
		for (String name : itemList.keySet()) {
			String key = name;
			Double value = itemList.get(name);
			System.out.printf("%-20s", key);
			System.out.printf("$%-13.2f", value);
			System.out.println();
		}

		do {
			System.out.println();
			System.out.println("What item would you like to order?");

			userOrder = scnr.nextLine();
			if (itemList.containsKey(userOrder)) {
				if (!itemsOrdered.contains(userOrder)) {
					itemPrices.add(itemList.get(userOrder));
					itemsOrdered.add(userOrder);
					userNum = Validator.getInt(scnr, "How many would you like?", 1, 20);
					itemAmounts.add(userNum);
					System.out.printf(userNum + " " + userOrder + " added to cart at $" + "%.2f",
							itemList.get(userOrder));
					System.out.println(" each.");
//				System.out.println(itemsOrdered); // tester
//				System.out.println(itemPrices); // tester
				} else {
					int newAmount = 0;
					userNum = Validator.getInt(scnr, "How many would you like?", 1, 20);
					newAmount = itemAmounts.get(itemsOrdered.indexOf(userOrder)) + userNum;
					itemAmounts.set((itemsOrdered.indexOf(userOrder)), newAmount);
					System.out.printf(userNum + " " + userOrder + " added to cart at $" + "%.2f",
							itemList.get(userOrder));
					System.out.println(" each.");
				}
			} else {
				System.out.println("That is not an available item.");
			}

			userCont = Validator.getString(scnr,
					"Would you like more? Press \"y\" to continue, any other entry to complete your order.");
		} while (userCont.equalsIgnoreCase("y"));

		if (itemsOrdered.size() > 0) {
			System.out.println();
			System.out.println("Thank you for your order!");
			System.out.println("Here's what you got:");

			for (int i = 0; i < itemsOrdered.size(); i++) {
				System.out.printf("%-5d", itemAmounts.get(i));
				System.out.printf(" " + "%-20s", itemsOrdered.get(i));
				System.out.printf("$%-13.2f", itemPrices.get(i) * itemAmounts.get(i));
				System.out.println();
			}

			System.out.println();
			System.out.printf("Order total was $" + "%.2f", doubleSum(itemPrices, itemAmounts));
			System.out.println();
			System.out.printf("Average price per item in order was $" + "%.2f", doubleAvg(itemPrices, itemAmounts));
			System.out.println();
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
			System.out.println();
			System.out.println("See ya later! Remember to come back to Fred's for all your organ needs!");

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
		for (int i = 0; i < input.size(); i++) {
			total += (input.get(i) * multiplier.get(i));
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
		for (int i = 0; i < input.size(); i++) {
			total += (input.get(i) * multiplier.get(i));
		}
		Double avg = total / sumItems(multiplier);
		return avg;
	}
}
