package RiskPackage;

import uipackage.DeployUI;

public class Deploy {
    private Territory territory;
    private int infantryToDeploy;
    private int cavalryToDeploy;
    private int artilleryToDeploy;
    private Army armyOnTerritory;

    public Deploy(Territory territory, int infantryToDeploy, int cavalryToDeploy, int artilleryToDeploy){
        this.territory = territory;
        this.infantryToDeploy = infantryToDeploy;
        this.cavalryToDeploy = cavalryToDeploy;
        this.artilleryToDeploy = artilleryToDeploy;
        DeployUI dplyUI = new DeployUI();
    }

    public void deployInfantry() {
        int currentInfantry = territory.armyOnTerritory.getInfantry();
        territory.armyOnTerritory.setInfantry(currentInfantry + infantryToDeploy);
    }
    public int getAmountInfantry(){
        return infantryToDeploy;
    }
    
    public void deployCavalry() {
        int currentCavalry = territory.armyOnTerritory.getCavalry();
        territory.armyOnTerritory.setCavalry(currentCavalry + cavalryToDeploy);
    }

    public void deployArtillery() {
        int currentArtillery = territory.armyOnTerritory.getArtillery();
        territory.armyOnTerritory.setArtillery(currentArtillery + artilleryToDeploy);
    }

    public void deployAll() {
        deployInfantry();
        deployCavalry();
        deployArtillery();
    }

    public void setInfantryToDeploy(int infantryToDeploy) {
        this.infantryToDeploy = infantryToDeploy;
    }
}
