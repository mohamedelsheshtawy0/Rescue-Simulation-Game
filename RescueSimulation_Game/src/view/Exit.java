package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Exit extends JFrame {
	private JTextArea txt;
	private JPanel imagePanel;
	private JLabel imageLabel;
	public Exit (){
		this.setTitle("Game is Finished !");
		this.setBounds(500,250,1000,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		txt=new JTextArea();
		txt.setPreferredSize(new Dimension (200,100));
		txt.setEditable(false);
		this.add(txt, BorderLayout.NORTH);
		imagePanel=new JPanel();
		ImageIcon image=new ImageIcon("morgan.jpeg");
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
