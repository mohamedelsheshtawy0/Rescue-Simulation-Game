package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class WinExc extends JFrame{
	private JTextArea txt;
	private JPanel imagePanel;
	private JLabel imageLabel;
	public WinExc(){
		this.setTitle("Your Move is Wrong !");
		this.setBounds(500,150,1000,800);
		txt=new JTextArea();
		txt.setPreferredSize(new Dimension (200,100));
		txt.setEditable(false);
		this.add(txt, BorderLayout.NORTH);
		imagePanel=new JPanel();
		ImageIcon image=new ImageIcon("shatta.jpg");
		imageLabel=new JLabel(image);
		imagePanel.add(imageLabel);
		this.add(imagePanel, BorderLayout.CENTER);
        this.setVisible(true);
	
	}
	public void setMessage(String s){
		txt.setFont(new Font("Ink Free",Font.BOLD,20));
		txt.setText(s);
	}
}
