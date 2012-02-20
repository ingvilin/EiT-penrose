package no.penrose.prosjekt;

public class Produksjon {
	private String name;
	private int type; 		//type of product as integer
	private int timer; 		//counts down to production finish time
	private int amount;		//amount of product being made
	
	public Produksjon(String n, int t) {
		name = n;
		type = t;
		timer = 0;
		amount = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public int getTimer() {
		return timer;
	}
	
	public boolean startProduction(int availableAmount, int productionTime, int size) {
		if(availableAmount - size >= 0) {
			timer = productionTime;
			amount = size;
			return true;
		}
		return false;
	}
	
	public void decrementTimer(int time) {
		if((timer - time) <= 0) {
			timer = 0;
		}
		else {
			timer = timer - time;
		}
	}
	
	public int removeProduct() {
		if(timer == 0) {
			int temp = amount;
			amount = 0;
			return temp;
		}
		return 0;
	}
	
	public boolean isProductionDone() {
		if((timer == 0) && (amount > 0)) {
			return true;
		}
		return false;
	}
}