package principal.cs;

import java.io.*;

import javax.swing.JOptionPane;

public class ClienThread extends Thread{

	ObjectInputStream ois;
	Mensaje mje = null;
	
	public ClienThread(ObjectInputStream ois){
		super();
		this.ois = ois;
	}
	
	public void run(){
		while(true){
			try {
				this.mje = (Mensaje) ois.readObject();
				} catch (Exception e) {
						try {
							ois.close();
							break;
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "No se pudo cerrar el OIS.","Error en Thread del Cliente", JOptionPane. ERROR_MESSAGE);
						}
			}
		}
	}
	
	public Mensaje getMensaje(){
		while(this.mje == null){
			
		}
		Mensaje recibido = this.mje;
		this.mje = null;
		return recibido;
	}
}