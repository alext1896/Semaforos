import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Semaphore barbero,clientes,mutex; 
		Silla s; //Objeto que cuenta número de clientes esperando
		Cliente c;
		Barbero b;
	
		
		barbero=new Semaphore(0);
		clientes=new Semaphore(0);
		mutex=new Semaphore(1);
		s=new Silla(0);
		b=new Barbero(barbero,clientes,mutex,s);
		b.start();
		for(int i=0;i<40;i++){ 
		   c=new Cliente(barbero,clientes,mutex,s,i);
		   c.start();
		}
		
	}

}
