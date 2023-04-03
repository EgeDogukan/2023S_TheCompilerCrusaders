package RiskPackage;

import java.util.ArrayList;
import java.util.LinkedList;

public class Army {
	
	private ArrayList<ArrayList<Soldier>> army ;
	
	public Army() {
		
		this.army = new ArrayList<ArrayList<Soldier>>();
		
		ArrayList<Soldier> inf = new ArrayList<Soldier>();
	    ArrayList<Soldier> art = new ArrayList<Soldier>();
	    ArrayList<Soldier> cav = new ArrayList<Soldier>();
	    
	    for (int i=0;i<4;i++) {
	    	inf.add(new Infantry(false));
	    }
	    for (int i=0;i<3;i++) {
	    	art.add(new Artillery(false));
	    }
	    for (int i=0;i<2;i++) {
	    	cav.add(new Cavalry(false));
	    }
	    
	    this.army.add(inf);
	    this.army.add(art);
	    this.army.add(cav);
	    
	}
	

	public ArrayList<ArrayList<Soldier>> getArmy() {
		return this.army;
	}

	public void setArmy(ArrayList<ArrayList<Soldier>> army) {
		this.army = army;
	}
	
	public ArrayList<Soldier> getInfantry() {
		return this.army.get(0);
	}
	
	public void setInfantry(ArrayList<Soldier> newInfranty) {
		this.army.set(0, newInfranty);
	}
	
	public ArrayList<Soldier> getCavalry () {
		return this.army.get(1);
	}
	
	public void setCavalry(ArrayList<Soldier> newCavalry) {
		this.army.set(1, newCavalry);
	}
	
	public ArrayList<Soldier> getArtillery() {
		return this.army.get(2);
	}
	
	public void setArtillery(ArrayList<Soldier> newArtillery) {
		this.army.set(2, newArtillery);
	}
	
	

}
