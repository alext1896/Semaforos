import java.util.concurrent.Semaphore;

public class Cliente extends Thread {
	
	private Semaphore barbero,clientes,mutex; 
	private Silla s; 
	private static final int SILLAS=5; //Número de sillas para esperar
	private int num;
	
	public Cliente(Semaphore barbero, Semaphore clientes, Semaphore mutex, Silla s,int i){
		
		this.barbero=barbero;
		this.clientes=clientes;
		this.mutex=mutex;
		this.s=s;
		this.num=i;
	}
	
	public void run(){
		System.out.println("Cliente: "+num+" llega");
		mutex.acquireUninterruptibly();
		if (s.getEsperando()<SILLAS){
			System.out.println("Cliente "+num+" encuentra sitio");
			s.setEsperando(s.getEsperando()+1);
			clientes.release();
			mutex.release();
			System.out.println("Cliente "+num+" se pone a esperar a que lo atiendan");
			barbero.acquireUninterruptibly();
			System.out.println("Cliente "+num+" me van a cortar el pelo ");
			try {
				Thread.sleep(1500);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			mutex.release();
			System.out.println("Cliente: "+num+" .No había sitio. Me voy");
		}
		
	}

}
