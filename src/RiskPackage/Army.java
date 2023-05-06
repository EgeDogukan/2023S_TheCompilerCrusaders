package RiskPackage;

import java.util.ArrayList;
import java.util.LinkedList;

public class Army {
	
	private ArrayList<ArrayList<Soldier>> army ;
	private Cavalry cavalry;
	private Artillery artillery;
	private Infantry infantry;
	private int Cnumber;
	private int Anumber;
	private int Inumber;



	public Army(int Cnumber, int Anumber, int Inumber) {
		this.Anumber = Anumber;
		this.Cnumber = Cnumber;
		this.Inumber = Inumber;
		
	}

	public ArrayList<ArrayList<Soldier>> getArmy() {
		return this.army;
	}

	public int calculateStrength(){
		return this.Anumber*10 + this.Cnumber*5 + this.Inumber;
		
	}

	public void setArmy(ArrayList<ArrayList<Soldier>> army) {
		this.army = army;

	}
	
	public int getInfantry() {
		return this.Inumber;
	}
	
	public void setInfantry(int Inumber) {
		this.Inumber = Inumber;
	}
	
	public int getCavalry () {
		return this.Cnumber;
	}
	
	public void setCavalry(int Cnumber) {
		this.Cnumber = Cnumber;
	}
	
	public int  getArtillery() {
		return this.Anumber;
	}
	
	public void setArtillery(int Anumber) {
		this.Anumber = Anumber;
	}
	
	

}
