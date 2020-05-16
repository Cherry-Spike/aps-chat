package View;

import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import com.sun.istack.internal.logging.Logger;

public class BasePane extends JFrame{
	//private static final Logger LOGGER = Logger.getLogger(BasePane.class.getName(), null);
	public static JPanel basePane;
	
	public BasePane() throws ParseException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 150, 940, 580);
        basePane = new JPanel();
        basePane.setBorder(new EmptyBorder(5, 5, 5, 5));
        basePane.setLayout(null);
        setContentPane(basePane);
        setResizable(false);
        setTitle("Cherry Spike Chat");
        new TelaInicial(basePane);
	}
public static void main(String[] args) {
		
		EventQueue.invokeLater(() ->{
            try {
            	BasePane frame = new BasePane();
                frame.setVisible(true);
            } catch (Exception e) {
               // LOGGER.info(e.getMessage());
            }
		});
	}
}
