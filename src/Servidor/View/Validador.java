package Servidor.View;

import javax.swing.text.MaskFormatter;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class Validador {

public Validador() {
	
}

public MaskFormatter MaskPorta () throws java.text.ParseException {
	try{
        MaskFormatter mascaraPorta = new MaskFormatter("#####");
        mascaraPorta.setPlaceholderCharacter('_');
        return mascaraPorta;
        
	   }
 catch(ParseException excp) {
        System.err.println("Erro na formatação: " + excp.getMessage());
        System.exit(-1);
 	}
	return null;
	
}

public MaskFormatter MaskIp() throws java.text.ParseException {
	try{
        
		MaskFormatter mascaraIp = new MaskFormatter("###.###.###");
        mascaraIp.setPlaceholderCharacter('_');
        return mascaraIp;
       }
 catch(ParseException excp) {
        System.err.println("Erro na formatação: " + excp.getMessage());
        System.exit(-1);
 	}
	return null;
	
 }

}
