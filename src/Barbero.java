import java.util.concurrent.Semaphore;

public class Barbero extends Thread {
	
	private Semaphore barbero,clientes,mutex; 
	private Silla s; 
	
	public Barbero(Semaphore barbero, Semaphore clientes, Semaphore mutex, Silla s){
		
		this.barbero=barbero;
		this.clientes=clientes;
		this.mutex=mutex;
		this.s=s;
	}
	
	public void run(){
		while (true){
			System.out.println("Barbero se pone a dormir...ZZZZZZ ");
			clientes.acquireUninterruptibly();
			System.out.println("Cliente me avisa ");
			mutex.acquireUninterruptibly();
			s.setEsperando(s.getEsperando()-1);//resto 1 a clientes esperando
			barbero.release();//aviso a un cliente de que estoy listo
			mutex.release();
			//Cortar pelo
			System.out.println("Barbero listo para empezar a cortar el pelo");
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
//Con esta solucion en que orden se atiende a los clientes
//Si no es en orden FIFO, modificar la solucion para que funcionase así
//Hacer la solucion para N barberos.
	 
}
