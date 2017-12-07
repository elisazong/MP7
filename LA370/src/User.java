
public class User {

	public int money = 100;
	public int energy = 100;
	public int food = 40;
	public int water = 30;
	public int co2 = 70;
	public int opinion = 65;
	public boolean gameOver = false;
	
	public User(){		
	}
	
	public boolean nextTurn() {
		if (money < 0 || energy < 10 || food < 10 || water < 10 || opinion < 40) {
			gameOver = false;
			money = (int) (money * 0.2 + 70);
			energy -= 10;
			food -= 10;
			water -= 10;
			co2 += 10;
		} else {
			gameOver = true;
		}
		return gameOver;
	}
	
	public boolean policyAction(PolicyCard param) {
		this.money += param.money;
		if (this.money < 0) {
			this.money -= param.money;
			System.out.println("Sorry, you do not have enough money. You may choose another policy.");
			return false;
		}
		this.energy += param.energy;
		if (this.energy < 0) {
			this.energy -= param.energy;
			System.out.println("Sorry, you do not have enough energy. You may choose another policy.");
			return false;
		}
		this.food += param.food;
		if (this.food < 0) {
			this.food -= param.food;
			System.out.println("Sorry, you do not have enough food. You may choose another policy.");
			return false;
		}
		this.water += param.water;
		if (this.water < 0) {
			this.water -= param.water;
			System.out.println("Sorry, you do not have enough water. You may choose another policy.");
			return false;
		}
		this.co2 += param.co2;
		if (this.co2 < 0) {
			this.co2 = 0;
		}
		this.opinion = param.opinion;
		if (this.opinion < 0) {
			this.opinion = 0;
		}
		if (this.opinion > 100) {
			this.opinion = 100;
		}
		return true;
	}
	
	public void printStatus() {
		System.out.printf("Now you have: Money %d, Energy %d, Food %d, Water %d, CO2 %d, Public Opinion %d.\n", money, energy, food, water, co2, opinion);
	}
	
}
