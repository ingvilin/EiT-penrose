package no.penrose.prosjekt;

public class Spill {
	private int money;
	private int days;
	private int rocks; //tengs til å produere enheter
	private Produksjon[] mProduction;
	private int science_level; //øker produksjonstiden
	//øker mengden å produsere
	private int electionic_level; //billigiste salgskomponent og minst krav av silisium??
	private int alloy_level; //nest billigste??
	private int solar_level; //dyreste??
	
	private static int price_per_rock = 20;
	
	private static int price_science_Level_1 = 10000;
	private static int price_science_level_2 = 50000;
	private static int price_sicence_level_3 = 100000;
	private static int price_sicence_level_4 = 500000;
	private static int price_sicence_level_5 = 1000000;
	
	private static int price_electionic_level_1 = 100000;
	private static int price_electionic_level_2 = 200000;
	private static int price_electionic_level_3 = 400000;
	
	private static int price_alloy_level_1 = 200000;
	private static int price_alloy_level_2 = 400000;
	private static int price_alloy_level_3 = 600000;
	
	private static int price_solar_level_1 = 300000;
	private static int price_solar_level_2 = 600000;
	private static int price_solar_level_3 = 900000;
	
	private int sale_price_electionic;
	private int sale_price_alloy;
	private int sale_price_solar;
	
	private int production_time_electionics;
	private int producion_time_alloy;
	private int producion_time_solar;
	
	public Spill() {
		money = 1000000;
		days = 0;
		rocks = 100;
		science_level = 0;
		electionic_level = 0;
		alloy_level = 0;
		solar_level = 0;
		mProduction = new Produksjon[3];
		
		initSalePrices();
		initProducitonTime();
	}
	
	/** Skal kalles hver gang man trykker en knapp og den inkrementerer dager avhengig av handlingen,
	 *  sjekker om man har nådd maks antall dager(365?)
	 *  oppdaterer fortjeneste */
	public void updateSpill() {
		updateSalePrices();
		updateProductionTime();
		days = days + 1;
		for(int i = 0; i < 3; i++) {
			mProduction[i].decrementTimer(1);
			if(mProduction[i].isProductionDone() == true) {
				sellProduct(i);
			}
		}
		if(days >= 365) {
			//avslutt spill
		}
	}
	
	/** Allows the player to start production of an amount of a silicon type
	 * @param amount: the amount of rocks to be used under production of type
	 * @param type: type of product; 1. electionic, 2. alloy, 3. solar
	 * @return: true if transaction went trough else false
	 */
	private boolean startProduction(int amount, int type) {
		updateProductionTime();
		if(rocks - amount >= 0){
			if(type == 1) {
				mProduction[0].startProduction(rocks, production_time_electionics, amount);
				return true;
			}
			else if(type == 2) {
				mProduction[1].startProduction(rocks, producion_time_alloy, amount);
				return true;
			}
			else if(type == 3) {
				mProduction[3].startProduction(rocks, producion_time_solar, amount);
				return true;
			}
		}
		return false;
	}
	
	/**Allows the player sell the product he has made
	 * @param type: type of product(1. electrionics, 2. alloy, 3. solar) */
	private void sellProduct(int type) {
		mProduction[type].removeProduct();
	}
	
	private void initSalePrices() {
		sale_price_electionic = 30;
		sale_price_alloy = 35;
		sale_price_solar = 40;
	}
	
	private void initProducitonTime() {
		production_time_electionics = 6;
		producion_time_alloy = 8;
		producion_time_solar = 10;
	}
	
	private void updateProductionTime() {
		if(science_level == 0){
			production_time_electionics = 6;
			producion_time_alloy = 8;
			producion_time_solar = 10;
		}
		else if(science_level == 1) {
			production_time_electionics = 5;
			producion_time_alloy = 7;
			producion_time_solar = 9;
		}
		else if(science_level == 2) {
			production_time_electionics = 4;
			producion_time_alloy = 6;
			producion_time_solar = 8;
		}
		else if(science_level == 3) {
			production_time_electionics = 3;
			producion_time_alloy = 5;
			producion_time_solar = 7;
		}
		else if(science_level == 4) {
			production_time_electionics = 2;
			producion_time_alloy = 4;
			producion_time_solar = 6;
		}
		else if(science_level == 5) {
			production_time_electionics = 1;
			producion_time_alloy = 3;
			producion_time_solar = 5;
		}
	}
	
	private void updateSalePrices() {
		if(electionic_level == 0) {
			sale_price_electionic = 30;
		}
		else if(electionic_level == 1) {
			sale_price_electionic = 35;
		}
		else if(electionic_level == 2) {
			sale_price_electionic = 40;
		}
		else if(alloy_level == 0) {
			sale_price_alloy = 35;
		}
		else if(alloy_level == 1) {
			sale_price_alloy = 40;
		}
		else if(alloy_level == 2) {
			sale_price_alloy = 45;
		}
		else if(solar_level == 0) {
			sale_price_solar = 40;
		}
		else if(solar_level == 1) {
			sale_price_solar = 45;
		}
		else if(solar_level == 2) {
			sale_price_solar = 50;
		}
	}
	
	/** Allows the player to buy rocks
	 * @param amount: amount of rocks the player wants to buy
	 * @return: return true if transaction was successful or else false */
	public boolean buyRock(int amount) {
		if((money - (amount * price_per_rock)) >= 0) {
			money = money - (amount * price_per_rock);
			rocks = rocks + amount;
			return true;
		}
		return false;
	}
	
	/** Allows the player to upgrade the science lab one level for ability to upgrade product quality
	 * @return: true if transaction was successful or else false */
	public boolean upgradeScienceLab() {
		if((science_level == 0) && ((money - price_science_Level_1) >= 0)) {
			science_level = 1;
			money = money - price_science_Level_1;
			days = days + 5;
			return true;
		}
		else if((science_level == 1) && ((money - price_science_level_2) >= 0)) {
			science_level = 2;
			money = money - price_science_level_2;
			days = days + 10;
			return true;
		}
		else if((science_level == 2) && ((money - price_sicence_level_3) >= 0)) {
			science_level = 3;
			money = money - price_sicence_level_3;
			days = days + 15;
			return true;
		}
		else if((science_level == 3) && ((money - price_sicence_level_4) >= 0)) {
			science_level = 4;
			money = money - price_sicence_level_4;
			days = days + 20;
			return true;
		}
		else if((science_level == 4) && ((money - price_sicence_level_5) >= 0)) {
			science_level = 5;
			money = money - price_sicence_level_5;
			days = days + 25;
			return true;
		}
		return false;
	}
	
	/** Allows the player to upgrade the factory in one of there areas(solar, electronics or alloys)
	 * 
	 * @param number: specifies which area the player want to upgrade; 1. electronics, 2. alloys, 3. solar
	 * @return: returns true if transaction went trough else false
	 */
	private boolean upgradeFactory(int number) {
		if((electionic_level == 0) && ((money - price_electionic_level_1) >= 0) && (number == 1)) {
			electionic_level = 1;
			money = money - price_electionic_level_1;
			days = days + 5;
			return true;
		}
		else if((electionic_level == 1) && ((money - price_electionic_level_2) >= 0) && (number == 1)) {
			electionic_level = 2;
			money = money - price_electionic_level_2;
			days = days + 10;
			return true;
		}
		else if((electionic_level == 2) && ((money - price_electionic_level_3) >= 0) && (number == 1)) {
			electionic_level = 3;
			money = money - price_electionic_level_3;
			days = days + 15;
			return true;
		}
		else if((alloy_level == 0) && ((money - price_alloy_level_1) >= 0) && (number == 2)) {
			alloy_level = 1;
			money = money - price_alloy_level_1;
			days = days + 5;
			return true;
		}
		else if((alloy_level == 1) && ((money - price_alloy_level_2) >= 0) && (number == 2)) {
			alloy_level = 2;
			money = money - price_alloy_level_2;
			days = days + 10;
			return true;
		}
		else if((alloy_level == 2) && ((money - price_alloy_level_3) >= 0) && (number == 2)) {
			alloy_level = 3;
			money = money - price_alloy_level_3;
			days = days + 15;
			return true;
		}
		else if((solar_level == 0) && ((money - price_solar_level_1) >= 0) && (number == 3)) {
			solar_level = 1;
			money = money - price_solar_level_1;
			days = days + 5;
			return true;
		}
		else if((solar_level == 1) && ((money - price_solar_level_2) >= 0) && (number == 3)) {
			solar_level = 2;
			money = money - price_solar_level_2;
			days = days + 10;
			return true;
		}
		else if((solar_level == 2) && ((money - price_solar_level_3) >= 0) && (number == 3)) {
			solar_level = 3;
			money = money - price_solar_level_3;
			days = days + 15;
			return true;
		}
		return false;
	}
}