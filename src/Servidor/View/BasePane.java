package Servidor.View;

import java.awt.EventQueue;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Servidor.ServidorSkt;


public class BasePane extends JFrame{
	
	public static JPanel basePane;
	
	public BasePane() throws ParseException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 150, 940, 580);
        basePane = new JPanel();
        basePane.setBorder(new EmptyBorder(5, 5, 5, 5));
        basePane.setLayout(null);
        setContentPane(basePane);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Cherry Spike Chat");
        new TelaChat(basePane, null);
        
	}
}
