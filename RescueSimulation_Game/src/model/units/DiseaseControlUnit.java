package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.disasters.Infection;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;

public class DiseaseControlUnit extends MedicalUnit {

	public DiseaseControlUnit(String unitID, Address location,
			int stepsPerCycle, WorldListener worldListener) {
		super(unitID, location, stepsPerCycle, worldListener);
	}

	@Override
	public void treat() {
		getTarget().getDisaster().setActive(false);
		Citizen target = (Citizen) getTarget();
		if (target.getHp() == 0) {
			jobsDone();
			return;
		} else if (target.getToxicity() > 0) {
			target.setToxicity(target.getToxicity() - getTreatmentAmount());
			if (target.getToxicity() == 0)
				target.setState(CitizenState.RESCUED);
		}

		else if (target.getToxicity() == 0)
			heal();

	}

	public void respond (Rescuable r) throws CannotTreatException,IncompatibleTargetException{
		if (r == null){
			throw new IncompatibleTargetException (this,r,"Incompatible Target !");
		}
		else{
		if (r instanceof ResidentialBuilding){
			throw new IncompatibleTargetException (this,r,"Incompatible Target !");
		}
		else{
			if (this.canTreat(r)){
				if (getTarget() != null && getState() == UnitState.TREATING)
   			    	reactivateDisaster();
   			  finishRespond(r);
			}
			else{
				throw new CannotTreatException(this,r,"Cannot Treat !");			}
		}
	}
}
}