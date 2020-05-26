package Servidor;

import java.io.Serializable;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class BuscaUsuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private JList<String> listaUsuario = new JList<String>(new DefaultListModel<String>());
	
	public void adicionarNomeUsuario(String nomeUsuario) {
		((DefaultListModel<String>)listaUsuario.getModel()).addElement(nomeUsuario);
	}
	
	public JList<String> getListaUsuario(){
		return listaUsuario;
	}

	public void setListaUsuario(JList<String> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	
	public void removeUsuario(JList<String> listaUsuario, String nomeUsuario) {		
		int i = 0;
		boolean b = true;
		while(b) {
			String checarNome = ((DefaultListModel<String>)listaUsuario.getModel()).get(i);
			if(checarNome == nomeUsuario) {
				((DefaultListModel<String>)listaUsuario.getModel()).remove(i);
				b = false;
			}
			i++;
		}
	}
	
}
