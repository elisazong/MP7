
public class PolicyCard {
	
	String name;
	int id, money, energy, food, water, co2, opinion;
	boolean used;
	
	public PolicyCard(String policy, int id, int m, int e, int f, int w, int c, int o) {
		this.name = policy;
		this.id = id;
		money = m;
		energy = e;
		food = f;
		water = w;
		co2 = c;
		opinion = o;
	}
	
	public void printPolicy() {
		System.out.println(id + " " + name + " | Money: " + money + "  Energy: " + energy + "  Food: " + food + "  Water: " + water + "  CO2: " + co2 + "  Public Opinion: " + opinion);
	}
	
}
