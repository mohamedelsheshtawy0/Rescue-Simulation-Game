package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.disasters.Collapse;
import model.disasters.Disaster;
import model.disasters.Fire;
import model.disasters.GasLeak;
import model.disasters.Infection;
import model.disasters.Injury;
import model.events.SOSListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import model.units.Evacuator;
import model.units.Unit;
import model.units.UnitState;
import simulation.Rescuable;
import simulation.Simulator;
import view.Exit;
import view.RescueSimulationView;
import view.WinExc;

public class CommandCenter implements SOSListener,ActionListener {

	private Simulator engine;
	private ArrayList<ResidentialBuilding> visibleBuildings;
	private ArrayList<Citizen> visibleCitizens;
	private ArrayList<Unit> emergencyUnits;
	private ArrayList<JButton> Cells;
	private ArrayList<JButton> Units;
	private RescueSimulationView View;
	private JButton cycle;
    private JButton lastRescuable;
    private JButton lastUnit;
    private boolean isPlaying=false;
    private	boolean flag=false;

	public CommandCenter() throws Exception {
		engine = new Simulator(this);
		visibleBuildings = new ArrayList<ResidentialBuilding>();
		visibleCitizens = new ArrayList<Citizen>();
		emergencyUnits = engine.getEmergencyUnits();
		View = new RescueSimulationView ();
		Cells = new ArrayList<JButton>();
		Units = new ArrayList<JButton>();
		cycle=new JButton("Start");
		cycle.setFont(new Font("Ink Free",50,50));
	    cycle.setActionCommand("NextCycle");
	    cycle.setPreferredSize(new Dimension (300,50));
	    cycle.setBackground(Color.WHITE);
	    cycle.addActionListener(this);
	    View.addButton(cycle);
	    String s = "Current Cycle : "+engine.getCurrentCycle()+". \n";
			s+="Number Of Casualities : "+engine.calculateCasualties()+". \n";
		View.addGeneralInfo(s);
		for (int i=0;i<100;i++){
			JButton btn = new JButton();
			btn.setActionCommand("Cell");
			btn.addActionListener(this);
			for (int j=0;j<engine.getCitizens().size();j++){
				Citizen c = engine.getCitizens().get(j);
				int index=c.getLocation().getX()*10+c.getLocation().getY();
				if (index==i){
					btn.setIcon(new ImageIcon("standing-person.png"));
				}
			}
			for (int j=0;j<engine.getBuildings().size();j++){
				ResidentialBuilding b = engine.getBuildings().get(j);
				int index=b.getLocation().getX()*10+b.getLocation().getY();
				if (index==i){
					btn.setIcon(new ImageIcon("house-buildings.png"));
				}
			}
			Cells.add(btn);
			View.addRescuePanel(btn);
		}
		for (int i=0;i<5;i++){
			JButton btn = new JButton();
			btn.setActionCommand("Unit");
			switch (i){
			case 3:btn.setIcon(new ImageIcon("fire-engine.png"));break;
			case 0:btn.setIcon(new ImageIcon("ambulance.png"));break;
			case 4:btn.setIcon(new ImageIcon("gascontrol.png"));break;
			case 1:btn.setIcon(new ImageIcon("diseasecontrol.png"));break;
			case 2:btn.setIcon(new ImageIcon("police-car.png"));break;
			}
			btn.addActionListener(this);
			Units.add(btn);
			View.addUnitPanel(btn);
		}
	}

	@Override
	public void receiveSOSCall(Rescuable r) {
		
		if (r instanceof ResidentialBuilding) {
			
			if (!visibleBuildings.contains(r))
				visibleBuildings.add((ResidentialBuilding) r);
			
		} else {
			
			if (!visibleCitizens.contains(r))
				visibleCitizens.add((Citizen) r);
		}

	}

	@Override
	public void actionPerformed(ActionEvent click) {
       		JButton btn = (JButton)click.getSource();
       		if (btn.getActionCommand().equals("NextCycle")){
       			if (engine.checkGameOver()==true){
       				this.isPlaying=false;
       				String s = "";
       				s+="GameOver :) ! \n";
       				s+="Your Score is : "+engine.calculateCasualties()+"\n";
       				View.addException(s);
       				btn.setText("End !");
       				Exit exit=new Exit();
       				exit.setMessage(s);
       				return;
       			}
       			this.isPlaying=true;
       			btn.setText("NextCycle");
       			engine.nextCycle();
       			String s = "Current Cycle : "+engine.getCurrentCycle()+". \n";
       			s+="Number Of Casualities : "+engine.calculateCasualties()+". \n";
       			View.addGeneralInfo(s);
       			View.addException("");
       			if (lastRescuable!=null){
       				if (lastRescuable.getActionCommand().equals("Cell")){
       	       			int index = Cells.indexOf(lastRescuable);
       	       			int x = index/10;
       	       	    	int y= index%10;
       	       	    	Rescuable r = engine.getBuildingByLocation(engine.getWorld()[x][y]);
       	       	    	if (r == null){
       	       	    		r=engine.getCitizenByLocation(engine.getWorld()[x][y]);
       	       	    	}
       	       	    	if (r instanceof ResidentialBuilding){
       	       	    		ResidentialBuilding b = (ResidentialBuilding)r;
       	       	    		View.addInfoRescuable(b.toString());
       	       	    	}
       	       	    	
       	       	    	if (r instanceof Citizen){
       	       	    		Citizen c = (Citizen)r;
       	       	    		View.addInfoRescuable(c.toString());
       	       	    	String result="";
           	    		for(int i=0;i<engine.getCitizens().size();i++){
           	    			if(engine.getCitizens().get(i).getLocation().getX()==x && engine.getCitizens().get(i).getLocation().getY()==y){
           	    				result+=engine.getCitizens().get(i).toString()+"\n";
           	    			}
           	    		}
           	    		View.addInfoRescuable(result);
       	       	    	}
       	       		}
       			}
       			if (lastUnit!=null){
       	       		if (lastUnit.getActionCommand().equals("Unit")){
       	       			int index=Units.indexOf(lastUnit);
       	       			Unit u = this.emergencyUnits.get(index);
       	       			View.addInfoUnit(u.toString());
       	       		}
       			}
       			HandleRespondingUnits();
       			HandleTreatingUnits();
       			HandleRep();
       			HandleRepUnits();
       			HandleStruckDisasters();
       			HandleActiveDisasters();
       			HandleDeadCitizens();
       		}
       		if (btn.getActionCommand().equals("Cell")){
       			int index = Cells.indexOf(btn);
       			int x = index/10;
       	    	int y= index%10;
       	    	Rescuable r = engine.getBuildingByLocation(engine.getWorld()[x][y]);
       	    	if (r == null){
       	    		r=engine.getCitizenByLocation(engine.getWorld()[x][y]);
       	    	}
       	    	if (r instanceof ResidentialBuilding){
       	    		ResidentialBuilding b = (ResidentialBuilding)r;
       	    		View.addInfoRescuable(b.toString());
       	    	}
       	    	if (r instanceof Citizen){
       	    		Citizen c = (Citizen)r;
	       	    	View.addInfoRescuable(c.toString());    
	       	    	String result="";
       	    		for(int i=0;i<engine.getCitizens().size();i++){
       	    			if(engine.getCitizens().get(i).getLocation().getX()==x && engine.getCitizens().get(i).getLocation().getY()==y){
       	    				result+=engine.getCitizens().get(i).toString()+"\n";
       	    			}
       	    		}
       	    		View.addInfoRescuable(result);
	       	    	}
       	    	if (lastUnit!=null){
       	    		if (lastUnit.getActionCommand().equals("Unit")){
       	    			int j=Units.indexOf(lastUnit);
       	       			Unit u = this.emergencyUnits.get(j);
       	       		    if (this.isPlaying==true){
       	       				try {
								u.respond(r);
				       			View.addException("The Unit is Responding :) !");
								View.addInfoUnit(u.toString());
								HandleRespondingUnits();
				       			HandleTreatingUnits();
							} 
       	       				catch (IncompatibleTargetException e) {
       	       					View.addException(e.getMessage());
       	       				WinExc window=new WinExc();
       	       				window.setMessage(e.getMessage());
							}
       	       				catch(CannotTreatException e){
       	       					View.addException(e.getMessage());
       	       				WinExc window=new WinExc();
       	       				window.setMessage(e.getMessage());
       	       				}
       	       		    }
       	    		}
       	    	}
       	    	this.lastRescuable=btn;
       		}
       		if (btn.getActionCommand().equals("Unit")){
       			int index=Units.indexOf(btn);
       			Unit u = this.emergencyUnits.get(index);
       			View.addInfoUnit(u.toString());
           		this.lastUnit=btn;
       		}
	}
	public void HandleDeadCitizens(){
		String s="";
		for (int i=0;i<engine.getCitizens().size();i++){
			if (engine.getCitizens().get(i).getState()==CitizenState.DECEASED){
				s+="Citizen is Dead : \n";
				s+="Citizen's Name : "+engine.getCitizens().get(i).getName()+". \n";
				s+="Citizen's Location : ("+engine.getCitizens().get(i).getLocation().getX()+","+engine.getCitizens().get(i).getLocation().getY()+"). \n";
			}
		}
		View.addInfoDead(s);
	}
	public void HandleStruckDisasters(){
		String s="";
		for (int i=0;i<engine.getExecutedDisasters().size();i++){
			if (engine.getExecutedDisasters().get(i).getStartCycle()==engine.getCurrentCycle()){
				s+="Disaster's Type : "+getType (engine.getExecutedDisasters().get(i))+". \n";
				s+="Disaster's Location : ("+engine.getExecutedDisasters().get(i).getTarget().getLocation().getX()+","+engine.getExecutedDisasters().get(i).getTarget().getLocation().getY()+"). \n";
			}
		}
		View.addSDInfo(s);
	}
	public String getType(Disaster d){
		if (d instanceof Collapse){
			return "Collapse";
		}
		if (d instanceof Fire){
			return "Fire";
		}
		if (d instanceof GasLeak){
			return "GasLeak";
		}
		if (d instanceof Infection){
			return "Infection";
		}
		if (d instanceof Injury){
			return "Injury";
		}
		return "";
	}
	public void HandleActiveDisasters(){
		String s="";
		for (int i=0;i<engine.getExecutedDisasters().size();i++){
			if (engine.getExecutedDisasters().get(i).isActive()==true){
				s+="Disaster's Type : "+getType (engine.getExecutedDisasters().get(i))+". \n";
				s+="Disaster's Location : ("+engine.getExecutedDisasters().get(i).getTarget().getLocation().getX()+","+engine.getExecutedDisasters().get(i).getTarget().getLocation().getY()+"). \n";
			}
		}
		View.addADInfo(s);
	}
	public void HandleRep(){
		for (int i=0;i<engine.getCitizens().size();i++){
			if (engine.getCitizens().get(i).getState()==CitizenState.DECEASED){
				int index=engine.getCitizens().get(i).getLocation().getX()*10+engine.getCitizens().get(i).getLocation().getY();
				JButton btn=Cells.get(index);
				btn.setIcon(new ImageIcon("skull.png"));
			}
			if (engine.getCitizens().get(i).getDisaster() instanceof Injury && engine.getCitizens().get(i).getState()==CitizenState.IN_TROUBLE && engine.getCitizens().get(i).getDisaster().isActive()==true){
				int index=engine.getCitizens().get(i).getLocation().getX()*10+engine.getCitizens().get(i).getLocation().getY();
				JButton btn=Cells.get(index);
				btn.setIcon(new ImageIcon("injury.png"));
			}
			else{
				if (engine.getCitizens().get(i).getDisaster() instanceof Injury && engine.getCitizens().get(i).getDisaster().isActive()==false && engine.getCitizens().get(i).getBloodLoss()==0){
					if (engine.getCitizens().get(i).getHp()==100){
						int index=engine.getCitizens().get(i).getLocation().getX()*10+engine.getCitizens().get(i).getLocation().getY();
						JButton btn=Cells.get(index);
						btn.setIcon(new ImageIcon("standing-person.png"));
					}
				}
			}
			if (engine.getCitizens().get(i).getDisaster() instanceof Infection && engine.getCitizens().get(i).getState()==CitizenState.IN_TROUBLE && engine.getCitizens().get(i).getDisaster().isActive()==true){
				int index=engine.getCitizens().get(i).getLocation().getX()*10+engine.getCitizens().get(i).getLocation().getY();
				JButton btn=Cells.get(index);
				btn.setIcon(new ImageIcon("diseased.png"));
			}
			else{
				if (engine.getCitizens().get(i).getDisaster() instanceof Infection && engine.getCitizens().get(i).getDisaster().isActive()==false && engine.getCitizens().get(i).getToxicity()==0){
					if (engine.getCitizens().get(i).getHp()==100){
						int index=engine.getCitizens().get(i).getLocation().getX()*10+engine.getCitizens().get(i).getLocation().getY();
						JButton btn=Cells.get(index);
						btn.setIcon(new ImageIcon("standing-person.png"));
					}
				}
			}
		}
		for (int i=0;i<engine.getBuildings().size();i++){
			if (engine.getBuildings().get(i).getStructuralIntegrity()==0){
				int index=engine.getBuildings().get(i).getLocation().getX()*10+engine.getBuildings().get(i).getLocation().getY();
				JButton btn=Cells.get(index);
				btn.setIcon(new ImageIcon("derelict-house-building.png"));
			}
			if (engine.getBuildings().get(i).getDisaster() instanceof Fire && engine.getBuildings().get(i).getStructuralIntegrity()!=0&&engine.getBuildings().get(i).getDisaster().isActive()==true){
				int index=engine.getBuildings().get(i).getLocation().getX()*10+engine.getBuildings().get(i).getLocation().getY();
				JButton btn=Cells.get(index);
				btn.setIcon(new ImageIcon("fire.gif"));
			}
			else{
				if (engine.getBuildings().get(i).getDisaster() instanceof Fire&& engine.getBuildings().get(i).getDisaster().isActive()==false&&engine.getBuildings().get(i).getFireDamage()==0){
					int index=engine.getBuildings().get(i).getLocation().getX()*10+engine.getBuildings().get(i).getLocation().getY();
					JButton btn=Cells.get(index);
					btn.setIcon(new ImageIcon("house-buildings.png"));
				}
			}
			if (engine.getBuildings().get(i).getDisaster() instanceof GasLeak && engine.getBuildings().get(i).getStructuralIntegrity()!=0&&engine.getBuildings().get(i).getDisaster().isActive()==true){
				int index=engine.getBuildings().get(i).getLocation().getX()*10+engine.getBuildings().get(i).getLocation().getY();
				JButton btn=Cells.get(index);
				btn.setIcon(new ImageIcon("gas.png"));
			}
			else{
				if (engine.getBuildings().get(i).getDisaster() instanceof GasLeak&& engine.getBuildings().get(i).getDisaster().isActive()==false&&engine.getBuildings().get(i).getGasLevel()==0){
					int index=engine.getBuildings().get(i).getLocation().getX()*10+engine.getBuildings().get(i).getLocation().getY();
					JButton btn=Cells.get(index);
					btn.setIcon(new ImageIcon("house-buildings.png"));
				}
			}
			if (engine.getBuildings().get(i).getDisaster() instanceof Collapse && engine.getBuildings().get(i).getStructuralIntegrity()!=0&&engine.getBuildings().get(i).getDisaster().isActive()==true){
				int index=engine.getBuildings().get(i).getLocation().getX()*10+engine.getBuildings().get(i).getLocation().getY();
				JButton btn=Cells.get(index);
				btn.setIcon(new ImageIcon("warning-sign.png"));
			}
			
		}
	}
	public void HandleRespondingUnits(){
		String s="";
		for (int i=0;i<this.emergencyUnits.size();i++){
			if (this.emergencyUnits.get(i).getState()==UnitState.RESPONDING){
				int j = Integer.parseInt(this.emergencyUnits.get(i).getUnitID());
			    switch (j){
			    case 1:s+="-Ambulance. \n";break;
			    case 2:s+="-DiseaseControlUnit. \n";break;
			    case 3:s+="-Evacuator. \n";break;
			    case 4:s+="-FireTruck. \n";break;
			    case 5:s+="-GasControlUnit. \n";break;
			    }
			    s+="Unit's Location : ("+this.emergencyUnits.get(i).getLocation().getX()+","+this.emergencyUnits.get(i).getLocation().getY()+"). \n";
			}
		}
		View.addRUInfo(s);
	}
	public void HandleTreatingUnits(){
		String s="";
		for (int i=0;i<this.emergencyUnits.size();i++){
			if (this.emergencyUnits.get(i).getState()==UnitState.TREATING){
				int j = Integer.parseInt(this.emergencyUnits.get(i).getUnitID());
			    switch (j){
			    case 1:s+="-Ambulance. \n";break;
			    case 2:s+="-DiseaseControlUnit. \n";break;
			    case 3:s+="-Evacuator. \n";break;
			    case 4:s+="-FireTruck. \n";break;
			    case 5:s+="-GasControlUnit. \n";break;
			    }
			    s+="Unit's Location : ("+this.emergencyUnits.get(i).getLocation().getX()+","+this.emergencyUnits.get(i).getLocation().getY()+"). \n";
			}
		}
		View.addTUInfo(s);
	}
	public void HandleRepUnits(){
		for (int i=0;i<this.emergencyUnits.size();i++){
			int x1=this.emergencyUnits.get(i).getLocation().getX();
			int y1=this.emergencyUnits.get(i).getLocation().getY();
			if (this.emergencyUnits.get(i).getTarget()!=null){
			int x2=this.emergencyUnits.get(i).getTarget().getLocation().getX();
			int y2=this.emergencyUnits.get(i).getTarget().getLocation().getY();
			int index=x2*10+y2;
			JButton btn=this.Cells.get(index);
			if (x1==x2&&y1==y2&&this.emergencyUnits.get(i).getState()==UnitState.TREATING){
				btn.setIcon(new ImageIcon(whichPng(this.emergencyUnits.get(i).getUnitID())));
			}
			if (this.emergencyUnits.get(i)instanceof Evacuator&&flag==true){
				flag=false;
				if (engine.getBuildingByLocation(engine.getWorld()[0][0])!=null){
					this.Cells.get(0).setIcon(new ImageIcon("citizen-building.png"));
				}
				else{
					this.Cells.get(0).setIcon(new ImageIcon("standing-person.png"));
				}
			}
			if (this.emergencyUnits.get(i) instanceof Evacuator){
				if (x1==0&&y1==0&&this.emergencyUnits.get(i).getState()==UnitState.TREATING){
					this.Cells.get(0).setIcon(new ImageIcon("police-car.png"));
					if ((((ResidentialBuilding)this.emergencyUnits.get(i).getTarget())).getStructuralIntegrity()==0){
						btn.setIcon(new ImageIcon("derelict-house-building.png"));
					}
					else{
						btn.setIcon(new ImageIcon("warning-sign.png"));
					}
					flag=true;
				}
				
			}
		}
		}
	}
	public String whichPng(String s){
		int i=Integer.parseInt(s);
		switch (i){
		case 1:return "ambulance.png";
		case 2:return "diseasecontrol.png";
		case 3:return "police-car.png";
		case 4:return "fire-engine.png";
		case 5:return "gascontrol.png";
		}
		return "";
	}
	public ArrayList<JButton> getCells() {
		return Cells;
	}

	public void setCells(ArrayList<JButton> cells) {
		Cells = cells;
	}
    public static void main (String [] args) throws Exception{
    	new CommandCenter();
    }
}
