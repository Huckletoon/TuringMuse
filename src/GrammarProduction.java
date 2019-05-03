import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class GrammarProduction {

	private String producer;	// Symbol to produce on
	private List<String> productionOutputs;
	private List<Double> productionChances;
	private Random rando;
	
	public GrammarProduction(String prod) {
		producer = prod;
		productionOutputs = new ArrayList<>();
		productionChances = new ArrayList<>();
		rando = new Random(Calendar.getInstance().getTime().getTime());
	}
	
	public void addProduction(String output, double chance) {
		productionOutputs.add(output);
		productionChances.add(chance);
	}
	
	public String nextProduction() {
		String output = "";
		boolean found = false;
		double select = rando.nextDouble();
		double chance = 0;
		for (int x = 0; x < productionOutputs.size(); x++) {
			if (!found) {
				chance += productionChances.get(x);
				if (select <= chance) {
					output = productionOutputs.get(x);
					found = true;
				}
			}
		}
		return output;
	}
	
	public String getProducer() {
		return producer;
	}
}
