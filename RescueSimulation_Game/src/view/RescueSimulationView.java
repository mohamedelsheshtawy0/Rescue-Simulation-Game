package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;


public class RescueSimulationView extends JFrame {
       private JPanel RescuePanel;
       private JPanel WorldPanel;
       private JPanel ControlPanel;
       private JPanel DisasterPanel;
       private JPanel struckDPanel;
       private JTextArea struckDInfo;
       private JPanel ADPanel;
       private JTextArea ADInfo;
       private JPanel NewFeeds;
       private JPanel UnitPanel;
       private JPanel AUnitPanel;
       private JPanel UnitInfo;
       private JTextArea UInfoArea;
       private JPanel RUnitPanel;
       private JTextArea RUInfoArea;
       private JPanel TUnitPanel;
       private JTextArea TUInfoArea;
       private JPanel InfoPanel;
       private JPanel GeneralPanel;
       private JTextArea GeneralInfoArea;
       private JTextArea Exception;
       private JPanel specInfo;
       private JTextArea specInfoArea;
       private JPanel deadPanel;
       private JTextArea deadInfo;
       private JScrollPane S1;
       private JScrollPane S2;
       private JScrollPane S3;
       private JScrollPane S4;
       private JScrollPane S5;
       private JScrollPane S6;
       private JScrollPane S7;
       private JScrollPane S8;
	public RescueSimulationView(){
		  this.setBounds(0, 0, 1920, 1080);
		  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		  this.setTitle("Rescue Simulation Game");
		  this.setLayout(new BorderLayout());
	      RescuePanel = new JPanel();
	      RescuePanel.setBackground(Color.BLACK);
	      RescuePanel.setLayout(new BorderLayout());
	      WorldPanel = new JPanel (new GridLayout(10,10));
	      WorldPanel.setBackground(Color.BLACK);
	      WorldPanel.setPreferredSize(new Dimension(1300,800));
	      RescuePanel.add(WorldPanel, BorderLayout.CENTER);
	      ControlPanel = new JPanel ();
	      ControlPanel.setLayout(new BorderLayout());
	      ControlPanel.setPreferredSize(new Dimension(1300,200));
	      ControlPanel.setBackground(Color.WHITE);
	      NewFeeds=new JPanel();
	      NewFeeds.setLayout(new BorderLayout());
	      NewFeeds.setBackground(Color.WHITE);
	      JTextArea titleNew = new JTextArea ("New Feeds : ");
	      titleNew.setFont(new Font("Ink Free",Font.BOLD,20));
	      titleNew.setBackground(Color.GRAY);
	      titleNew.setForeground(Color.WHITE);
	      NewFeeds.add(titleNew, BorderLayout.NORTH);
	      S1=new JScrollPane();
	      S1.setPreferredSize(new Dimension());
	      S1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	      S1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	      NewFeeds.add(S1, BorderLayout.CENTER);
	      ControlPanel.add(NewFeeds, BorderLayout.CENTER);
	      DisasterPanel=new JPanel();
	      DisasterPanel.setPreferredSize(new Dimension(600,230));
	      DisasterPanel.setLayout(new BorderLayout());
	      struckDPanel=new JPanel();
	      struckDPanel.setBackground(Color.BLACK);
	      struckDPanel.setPreferredSize(new Dimension(300,230));
	      struckDPanel.setLayout(new BorderLayout());
	      JTextArea titleSDPanel=new JTextArea("Disasters struck in this cycle : ");
	      titleSDPanel.setFont(new Font("Ink Free",Font.BOLD,20));
	      titleSDPanel.setBackground(Color.GRAY);
	      titleSDPanel.setForeground(Color.WHITE);
	      struckDPanel.add(titleSDPanel, BorderLayout.NORTH);
	      struckDInfo = new JTextArea();
	      struckDInfo.setBackground(Color.DARK_GRAY);
	      struckDInfo.setForeground(Color.GREEN);
	      S2=new JScrollPane(struckDInfo);
	      S2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	      S2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	      struckDPanel.add(S2, BorderLayout.CENTER);
	      DisasterPanel.add(struckDPanel, BorderLayout.WEST);
	      ADPanel=new JPanel();
	      ADPanel.setBackground(Color.LIGHT_GRAY);
	      ADPanel.setPreferredSize(new Dimension(300,230));
	      ADPanel.setLayout(new BorderLayout());
	      JTextArea titleADPanel=new JTextArea("Active Disasters : ");
	      titleADPanel.setFont(new Font("Ink Free",Font.BOLD,20));
	      titleADPanel.setBackground(Color.GRAY);
	      titleADPanel.setForeground(Color.WHITE);
	      ADPanel.add(titleADPanel, BorderLayout.NORTH);
	      ADInfo=new JTextArea();
	      ADInfo.setBackground(Color.DARK_GRAY);
	      ADInfo.setForeground(Color.GREEN);
	      S3=new JScrollPane(ADInfo);
	      S3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	      S3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	      ADPanel.add(S3, BorderLayout.CENTER);
	      DisasterPanel.add(ADPanel, BorderLayout.EAST);
	      ControlPanel.add(DisasterPanel, BorderLayout.WEST);
	      RescuePanel.add(ControlPanel, BorderLayout.SOUTH);
	      UnitPanel=new JPanel();
	      UnitPanel.setBackground(Color.BLACK);
	      AUnitPanel=new JPanel();
	      AUnitPanel.setLayout(new GridLayout (5,1));
	      AUnitPanel.setPreferredSize(new Dimension (300,300));
	      UnitPanel.add(AUnitPanel, BorderLayout.NORTH);
	      UnitInfo=new JPanel();
	      UnitInfo.setLayout(new BorderLayout());
	      JTextArea titleUInfo=new JTextArea("Unit Information :");
	      titleUInfo.setFont(new Font("Ink Free",Font.BOLD,20));
	      titleUInfo.setBackground(Color.GRAY);
	      titleUInfo.setForeground(Color.WHITE);
	      UnitInfo.add(titleUInfo, BorderLayout.NORTH);
	      UnitInfo.setPreferredSize(new Dimension (300,450));
	      UnitInfo.setBackground(Color.BLACK);
	      UInfoArea = new JTextArea ();
	      UInfoArea.setBackground(Color.DARK_GRAY);
	      UInfoArea.setForeground(Color.GREEN);
	      S4=new JScrollPane(UInfoArea);
	      S4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	      S4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	      UnitInfo.add(S4, BorderLayout.CENTER);
	      UnitPanel.add(UnitInfo, BorderLayout.CENTER);
	      RUnitPanel=new JPanel();
	      RUnitPanel.setLayout(new BorderLayout());
	      JTextArea titleResponding = new JTextArea("Responding Units :");
	      titleResponding.setFont(new Font("Ink Free",Font.BOLD,20));
	      titleResponding.setBackground(Color.GRAY);
	      titleResponding.setForeground(Color.WHITE);
	      RUnitPanel.add(titleResponding,BorderLayout.NORTH);
	      RUnitPanel.setPreferredSize(new Dimension(300,125));
	      RUnitPanel.setBackground(Color.GRAY);
	      RUInfoArea = new JTextArea();
	      RUInfoArea.setBackground(Color.DARK_GRAY);
	      RUInfoArea.setForeground(Color.GREEN);
	      S5=new JScrollPane(RUInfoArea);
	      S5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	      S5.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	      RUnitPanel.add(S5, BorderLayout.CENTER);
	      UnitPanel.add(RUnitPanel, BorderLayout.CENTER);
	      TUnitPanel=new JPanel();
	      TUnitPanel.setLayout(new BorderLayout());
	      JTextArea titleTreating = new JTextArea("Treating Units :");
	      titleTreating.setFont(new Font("Ink Free",Font.BOLD,20));
	      titleTreating.setBackground(Color.GRAY);
	      titleTreating.setForeground(Color.WHITE);
	      TUnitPanel.add(titleTreating, BorderLayout.NORTH);
	      TUnitPanel.setPreferredSize(new Dimension(300,105));
	      TUnitPanel.setBackground(Color.LIGHT_GRAY);
	      TUInfoArea = new JTextArea();
	      TUInfoArea.setBackground(Color.DARK_GRAY);
	      TUInfoArea.setForeground(Color.GREEN);
	      S6=new JScrollPane(TUInfoArea);
	      S6.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	      S6.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	      TUnitPanel.add(S6, BorderLayout.CENTER);
	      UnitPanel.add(TUnitPanel, BorderLayout.SOUTH);
	      UnitPanel.setPreferredSize(new Dimension(300,getHeight()));
	      InfoPanel = new JPanel(new BorderLayout());
	      GeneralPanel = new JPanel();
	      GeneralPanel.setLayout(new BorderLayout());
	      GeneralPanel.setBackground(Color.WHITE);
	      GeneralPanel.setPreferredSize(new Dimension(200,250));
	      JTextArea titleGeneral = new JTextArea("General Information :");
	      titleGeneral.setFont(new Font("Ink Free",Font.BOLD,20));
	      titleGeneral.setBackground(Color.GRAY);
	      titleGeneral.setForeground(Color.WHITE);
	      GeneralPanel.add(titleGeneral, BorderLayout.NORTH);
	      GeneralInfoArea=new JTextArea();
	      GeneralInfoArea.setBackground(Color.DARK_GRAY);
	      GeneralInfoArea.setForeground(Color.GREEN);
	      GeneralPanel.add(GeneralInfoArea, BorderLayout.CENTER);
	      Exception=new JTextArea();
	      Exception.setBackground(Color.DARK_GRAY);
	      Exception.setForeground(Color.GREEN);
	      S1=new JScrollPane(Exception);
	      S1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	      S1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	      NewFeeds.add(S1,BorderLayout.CENTER);
	      InfoPanel.add(GeneralPanel, BorderLayout.NORTH);
	      specInfo = new JPanel();
	      specInfo.setLayout(new BorderLayout());
	      specInfo.setBackground(Color.GRAY);
	      specInfo.setPreferredSize(new Dimension(200,250));
	      JTextArea titleSpec = new JTextArea("Rescuables Information :");
	      titleSpec.setFont(new Font("Ink Free",Font.BOLD,20));
	      titleSpec.setBackground(Color.GRAY);
	      titleSpec.setForeground(Color.WHITE);
	      specInfo.add(titleSpec, BorderLayout.NORTH);
	      specInfoArea=new JTextArea();
	      specInfoArea.setBackground(Color.DARK_GRAY);
	      specInfoArea.setForeground(Color.GREEN);
	      S7=new JScrollPane(specInfoArea);
	      S7.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	      S7.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	      specInfo.add(S7, BorderLayout.CENTER);
	      InfoPanel.add(specInfo, BorderLayout.CENTER);
	      deadPanel=new JPanel();
	      deadPanel.setLayout(new BorderLayout());
	      deadPanel.setBackground(Color.BLACK);
	      deadPanel.setPreferredSize(new Dimension(300,230));
	      JTextArea titleDead=new JTextArea("Dead Citizen's :");
	      titleDead.setFont(new Font("Ink Free",Font.BOLD,20));
	      titleDead.setBackground(Color.GRAY);
	      titleDead.setForeground(Color.WHITE);
	      deadPanel.add(titleDead, BorderLayout.NORTH);
	      deadInfo=new JTextArea();
	      deadInfo.setBackground(Color.DARK_GRAY);
	      deadInfo.setForeground(Color.GREEN);
	      S8=new JScrollPane(deadInfo);
	      S8.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	      S8.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	      deadPanel.add(S8, BorderLayout.CENTER);
	      InfoPanel.setBackground(Color.GRAY);
	      InfoPanel.add(deadPanel, BorderLayout.SOUTH);
	      InfoPanel.setPreferredSize(new Dimension(300,getHeight()));
	      this.add(RescuePanel,BorderLayout.CENTER);
	      this.add(UnitPanel, BorderLayout.EAST);
	      this.add(InfoPanel, BorderLayout.WEST);
	      this.setVisible(true);
	}
	public void addRescuePanel(JButton btn){
		btn.setBackground(Color.LIGHT_GRAY);
		WorldPanel.add(btn);
		WorldPanel.invalidate();
		WorldPanel.validate();
		WorldPanel.repaint();
	}
	public void addUnitPanel(JButton btn){
		btn.setBackground(Color.YELLOW);
		AUnitPanel.add(btn);
		AUnitPanel.invalidate();
		AUnitPanel.validate();
		AUnitPanel.repaint();
	}
	public void addInfoUnit(String s){
		this.UInfoArea.setFont(new Font("Ink Free",Font.BOLD,20));
		this.UInfoArea.setText(s);
	}
	public void addInfoRescuable(String s){
		this.specInfoArea.setFont(new Font("Ink Free",Font.BOLD,20));
		this.specInfoArea.setText(s);
	}
	public void addGeneralInfo(String s){
		this.GeneralInfoArea.setFont(new Font("Ink Free",Font.BOLD,20));
		this.GeneralInfoArea.setText(s);
	}
	public void addButton(JButton btn){
		this.ControlPanel.add(btn, BorderLayout.EAST);
	}
	public void addException(String s){
		this.Exception.setFont(new Font("Ink Free",Font.BOLD,20));
		this.Exception.setText(s);
	}
	public void addRUInfo(String s){
		this.RUInfoArea.setFont(new Font("Ink Free",Font.BOLD,20));
		this.RUInfoArea.setText(s);
	}
	public void addTUInfo(String s){
		this.TUInfoArea.setFont(new Font("Ink Free",Font.BOLD,20));
		this.TUInfoArea.setText(s);
	}
	public void addSDInfo(String s){
		this.struckDInfo.setFont(new Font("Ink Free",Font.BOLD,20));
		this.struckDInfo.setText(s);
	}
	public void addADInfo(String s){
		this.ADInfo.setFont(new Font("Ink Free",Font.BOLD,20));
		this.ADInfo.setText(s);
	}
	public void addInfoDead(String s){
		this.deadInfo.setFont(new Font("Ink Free",Font.BOLD,20));
		this.deadInfo.setText(s);
	}
}