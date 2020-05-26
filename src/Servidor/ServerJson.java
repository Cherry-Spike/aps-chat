package Servidor;

import java.util.ArrayList;
import java.util.List;

public class ServerJson {

	private int tipo;
	private List<String> mensagem;
	
	public ServerJson(int tipo) {
		setTipo(tipo);
		mensagem = new ArrayList<String>();
	}

	/* GETS & SETS */ 	
	public int getTipo() {
		return tipo;
	}	
	private void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public List<String> getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem2) {
		mensagem.add(mensagem2);
	}
}
