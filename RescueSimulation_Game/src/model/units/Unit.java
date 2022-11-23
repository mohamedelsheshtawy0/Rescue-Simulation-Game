package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.disasters.Collapse;
import model.disasters.Disaster;
import model.disasters.Fire;
import model.disasters.GasLeak;
import model.disasters.Infection;
import model.disasters.Injury;
import model.events.SOSResponder;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;

public abstract class Unit implements Simulatable, SOSResponder {
	private String unitID;
	private UnitState state;
	private Address location;
	private Rescuable target;
	private int distanceToTarget;
	private int stepsPerCycle;
	private WorldListener worldListener;

	public Unit(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener) {
		this.unitID = unitID;
		this.location = location;
		this.stepsPerCycle = stepsPerCycle;
		this.state = UnitState.IDLE;
		this.worldListener = worldListener;
	}

	public void setWorldListener(WorldListener listener) {
		this.worldListener = listener;
	}

	public WorldListener getWorldListener() {
		return worldListener;
	}

	public UnitState getState() {
		return state;
	}

	public void setState(UnitState state) {
		this.state = state;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public String getUnitID() {
		return unitID;
	}

	public Rescuable getTarget() {
		return target;
	}

	public int getStepsPerCycle() {
		return stepsPerCycle;
	}

	public void setDistanceToTarget(int distanceToTarget) {
		this.distanceToTarget = distanceToTarget;
	}

	@Override
	public void respond(Rescuable r) throws IncompatibleTargetException, CannotTreatException {
       
	}
	public void reactivateDisaster() {
		Disaster curr = target.getDisaster();
		curr.setActive(true);
	}

	public void finishRespond(Rescuable r) {
		target = r;
		state = UnitState.RESPONDING;
		Address t = r.getLocation();
		distanceToTarget = Math.abs(t.getX() - location.getX())
				+ Math.abs(t.getY() - location.getY());

	}

	public abstract void treat();

	public void cycleStep() {
		if (state == UnitState.IDLE)
			return;
		if (distanceToTarget > 0) {
			distanceToTarget = distanceToTarget - stepsPerCycle;
			if (distanceToTarget <= 0) {
				distanceToTarget = 0;
				Address t = target.getLocation();
				worldListener.assignAddress(this, t.getX(), t.getY());
			}
		} else {
			state = UnitState.TREATING;
			treat();
		}
	}

	public void jobsDone() {
		target = null;
		state = UnitState.IDLE;

	}
	public boolean canTreat (Rescuable r){
		if (r instanceof Citizen){
			Citizen c = (Citizen) r;
			if (c.getState()==CitizenState.SAFE){
				return false;
			}
			if (c.getHp()==100){
				return false;
			}
		}
		if (r instanceof ResidentialBuilding){
			ResidentialBuilding b = (ResidentialBuilding) r;
			if (this instanceof Evacuator && (b.getStructuralIntegrity()==0 || !(b.getDisaster() instanceof Collapse))){
				return false;
			}
			if (this instanceof FireTruck && b.getFireDamage()==0){
				return false;
			}
			if (this instanceof GasControlUnit && b.getGasLevel()==0){
				return false;
			}
		}
		return true;
	}
	public String toString(){
		String x = "Unit's ID : "+this.getUnitID()+". \n";
		if (this instanceof Ambulance){
			x+="Unit's Type : Ambulance. \n";
		}
		if (this instanceof DiseaseControlUnit){
			x+="Unit's Type : DiseaseControlUnit. \n";
		}
		if (this instanceof Evacuator){
			x+="Unit's Type : Evacuator. \n";
		}
		if (this instanceof FireTruck){
			x+="Unit's Type : FireTruck. \n";
		}
		if (this instanceof GasControlUnit){
			x+="Unit's Type : GasControlUnit. \n";
		}
		x+="Unit's Location : ("+this.getLocation().getX()+","+this.getLocation().getY()+"). \n";
		x+="Unit's Steps Per Cycle : "+this.getStepsPerCycle()+". \n";
	    if (this.getTarget() instanceof Citizen){
	    	x+="Unit's Target : Citizen : "+((Citizen)this.getTarget()).getName()+". \n";
	    	x+="Target's Location : ("+this.getTarget().getLocation().getX()+","+this.getTarget().getLocation().getY()+"). \n";
	    }
	    if (this.getTarget() instanceof ResidentialBuilding){
	    	x+="Unit's Target : Building. \n";
	    	x+="Target's Location : ("+this.getTarget().getLocation().getX()+","+this.getTarget().getLocation().getY()+"). \n";
	    }
	    x+="Unit's State : "+this.getState()+". \n";
	    if (this instanceof Evacuator){
	    	x+="Number of Passengers : "+((Evacuator)this).getPassengers().size()+". \n";
	    	if (((Evacuator)this).getPassengers().size()>0)
	    	x+="Information of Passengers : \n";
	    	for (int i=0;i<((Evacuator)this).getPassengers().size();i++){
	    		x+=((Evacuator)this).getPassengers().get(i).toString();
	    	}
	    }
		return x;
	}
}
