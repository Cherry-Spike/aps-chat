package Cliente.View;

import java.awt.EventQueue;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Servidor.ServidorSkt;

public class BasePaneCliente extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public static JPanel basePane;
		
	public BasePaneCliente() throws ParseException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 150, 940, 580);
        basePane = new JPanel();
        basePane.setBorder(new EmptyBorder(5, 5, 5, 5));
        basePane.setLayout(null);
        setContentPane(basePane);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Cherry Spike Chat");
        new TelaInicialCliente(basePane);
        
	}
	
	public static void main(String[] args){
		EventQueue.invokeLater(() ->{
	        try {
	        	BasePaneCliente frame = new BasePaneCliente();
	            frame.setVisible(true);
	        } catch (Exception e) {
	           
	        }
		});
	
//		new Thread() {
//			@Override
//			public void run() {
//				new ServidorSkt();
//				try {
//					ServidorSkt.main(null);
//				} catch (IOException e) {
//					e.printStackTrace();
//				} 
//			}
//		}.start();
	}
	
}
