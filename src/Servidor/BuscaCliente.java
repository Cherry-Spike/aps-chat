package Servidor;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class BuscaCliente {

	private JList<String> listaCliente = new JList<String>(new DefaultListModel<String>());
	
	public void adicionarNomeCliente(String nomeCliente) {
		((DefaultListModel<String>)listaCliente.getModel()).addElement(nomeCliente);
	}
	
	public JList<String> getListaCliente(){
		return listaCliente;
	}

	public void setListaCliente(JList<String> listaCliente) {
		this.listaCliente = listaCliente;
	}
}
