import java.util.Scanner;

public class Game {
	
	public static PolicyCard[][] policyAll = {
			{new PolicyCard("Introduce Environmental Tax", 1, 10, 0, 0, 0, -10, -25), 
				new PolicyCard("Introduce Green Energy Subsidy", 2, -10, 5, 0, 0, -10, -10),
				new PolicyCard("Increase Fuel Tax", 3, 5, -2, 0, 0, -10, -25),
				new PolicyCard("Build Wind Farms", 4, -5, 10, 0, 0, -5, 7),
				new PolicyCard("Plant Forest", 5, -1, 0, 0, 0, -1, 3),
				new PolicyCard("Build Gas Power Station", 6, -25, 30, 0, 0, 20, 5),
				new PolicyCard("Invest in Hydrogen Infrastructure", 7, -15, 0, 0, 15, 5, 5),
				new PolicyCard("Raise Retirement Age", 8, 60, 0, 0, 0, 10, -25)}, 
			{new PolicyCard("Import Food", 1, -12, -5, 15, 0, 10, 0),
				new PolicyCard("Export Food", 2, 10, -5, -15, 0, 10, -5),
				new PolicyCard("Import Water", 3, -12, -5, 0, 15, 10, 0),
				new PolicyCard("Export Food", 4, 10, 10, 0, -15, 10, -5),
				new PolicyCard("Import Energy", 5, -12, 10, 0, 0, 10, 0),
				new PolicyCard("Export Energy", 6, 10, -15, 0, 0, 10, -5),
				new PolicyCard("Import Green Technologies", 7, -15, 0, 0, 0, -5, 10),
				new PolicyCard("Export Green Technologies", 8, 10, 10, 0, 0, 0, 5)},
			{new PolicyCard("Support Organic Farming", 1, -25, 5, 5, -5, -10, 15),
				new PolicyCard("Support Low-intensity Farming", 2, -40, -5, 20, -10, -2, 5),
				new PolicyCard("Support Medium-intensity Farming", 3, -40, -10, 35, -20, 15, 5),
				new PolicyCard("Support High-intensity Farming", 4, -40, -10, 40, -25, 20, 5),
				new PolicyCard("Support Emission Trading", 5, -5, 0, 0, 0, -10, 10),
				new PolicyCard("Reduce Packaging", 6, 10, 15, 0, 0, -5, -10),
				new PolicyCard("Subsidize Aviation", 7, -10, 0, 0, 0, 15, 20)},
			{new PolicyCard("Invest in Public Transportation", 1, -20, -10, 0, 0, -10, 10),
				new PolicyCard("Invest in Inter-state Trains", 2, -15, -5, 0, 0, -5, 5),
				new PolicyCard("Build Affordable Housing", 3, -20, -10, 0, -10, 25, 30),
				new PolicyCard("Improve Building Regulations", 4, -5, 0, 0, 0, -10, 10),
				new PolicyCard("Restrict Use of Coal", 5, -10, -30, 0, 0, -20, -10),
				new PolicyCard("Promote Use of Gas", 6, -10, 20, 0, 0, 5, 0),
				new PolicyCard("Apply Biogas from Landfills", 7, -5, 0, 0, 0, -10, 10),
				new PolicyCard("Promote Rain Water Use", 8, -10, 0, 0, 10, -10, 5),
				new PolicyCard("Build Water Treatment Plants", 9, -40, 0, 0, 30, 15, 10)},
			{new PolicyCard("Charge for Plastic Bags", 1, 0, 0, 0, 0, -2, -1),
				new PolicyCard("Subsidize Hybrid Vehicles", 2, -5, 0, 0, 0, -10, 10),
				new PolicyCard("Restrict Private Use of Petroleum Vehicles", 3, 0, 10, 0, 0, -10, -10),
				new PolicyCard("Promote Energy Efficient Appliances", 4, -5, 5, 0, 0, -7, 5),
				new PolicyCard("Promote Solar Energy Cells", 5, -5, 5, 0, 0, -7, 5),
				new PolicyCard("Promote Domestic Wind Turbines", 6, -10, 8, 0, 0, -10, 8),
				new PolicyCard("Introduce Water Metering", 7, -2, 0, 0, 2, -2, 3)}};
		
	public static void policyPrinter(int cate) {
		for (int i = 0; i < policyAll[cate - 1].length; i++) {
			policyAll[cate - 1][i].printPolicy();
		}
	}
	
	public static void playATurn(User user) {
		int count = 4;
		Scanner scanner = new Scanner(System.in);
		while (count > 0) {
			System.out.printf("Which category of policy would you like to take? (%d left)\n", count);
			System.out.println("(1 - National, 2 - Trade, 3 - Industry, 4 - Local, 5 - Household, 0 - Skip)");
			int chooseCategory = scanner.nextInt();
			if (chooseCategory != 0) {
				policyPrinter(chooseCategory);
				System.out.printf("Which policy would you take? (%d left) (0 - Skip)\n", count);
				int choosePol = scanner.nextInt();
				if (choosePol != 0) {
					if (user.policyAction(policyAll[chooseCategory - 1][choosePol - 1])) {
						user.printStatus();
						count--;
					}
				}
			} else {
				break;
			}
		}
//		scanner.close();
	}

	public static void main(String[] args) {
		System.out.println("GAME START!\n");
		System.out.println("Now it is 2020. You are the president of a developed country.\n"
				+ "You have some points of money, energy, food, water, CO2 emission level and public opinion level. \n"
				+ "Your goal is to control the emission level of CO2 below 30 at 2070.\n"
				+ "You have 5 turns to go. Each turn is 10 years.\n"
				+ "In each turn, you will lose some points of energy, food, water and add some points of money and CO2 for general development by default.\n"
				+ "The point of money left in the last turn will be proportionally added to the next turn.\n"
				+ "You have 4 policies (or less) to be implemented each turn.\n"
				+ "You have to keep money, energy, food and water not less than 0. Public opinion should always be more than 40.\n");
		System.out.println("READY? \n");
		try {
			  Thread.sleep(500);
			} catch (InterruptedException ex) {
			  Thread.currentThread().interrupt();
			  throw new RuntimeException(ex);
			}
		User a = new User();
		int year = 2010;
		while (a.nextTurn() && year < 2070) {
			year += 10;
			System.out.printf("======================== Year %d ========================\n", year);
			a.printStatus();
			playATurn(a);
		}
		System.out.println("=========================================================");
		if (year == 2070) {
			if (a.co2 <= 30 && a.opinion >= 40) {
				System.out.println("YOU WIN!");
			} else {
				System.out.println("YOU LOST :(");
			}
			if (a.co2 > 30) {
				System.out.println("Your CO2 level is higher than standards.");
			}
			if (a.opinion < 40) {
				System.out.println("You are not supported by your population.");
			}
		} else {
			System.out.println("YOU LOST :(");
			if (a.opinion < 40) {
				System.out.println("You are not supported by your population.");
			} else {
				System.out.println("Your resources are out of use.");
			}
		}
		try {
			  Thread.sleep(500);
			} catch (InterruptedException ex) {
			  Thread.currentThread().interrupt();
			  throw new RuntimeException(ex);
			}
		System.out.println("\nNow do you have a better understanding about how the government make efforts on balancing development and environment protection?");
	}
	
}
