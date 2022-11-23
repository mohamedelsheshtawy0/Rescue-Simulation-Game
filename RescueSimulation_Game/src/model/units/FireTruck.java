package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import simulation.Address;
import simulation.Rescuable;

public class FireTruck extends FireUnit {

	public FireTruck(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener) {
		super(unitID, location, stepsPerCycle, worldListener);
	}

	@Override
	public void treat() {
		getTarget().getDisaster().setActive(false);

		ResidentialBuilding target = (ResidentialBuilding) getTarget();
		if (target.getStructuralIntegrity() == 0) {
			jobsDone();
			return;
		} else if (target.getFireDamage() > 0)

			target.setFireDamage(target.getFireDamage() - 10);

		if (target.getFireDamage() == 0)

			jobsDone();

	}
	public void respond (Rescuable r) throws CannotTreatException,IncompatibleTargetException{
		if (r ==null){
			throw new IncompatibleTargetException (this,r,"Incompatible Target !");
		}
		else{
		if (r instanceof Citizen){
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