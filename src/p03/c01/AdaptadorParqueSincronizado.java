package src.p03.c01;



public class AdaptadorParqueSincronizado implements IParque{


	
	private static IParque parque = new Parque(50);
	private int contadorPersonasTotales;
	
	
	private static AdaptadorParqueSincronizado instancia  = new AdaptadorParqueSincronizado();
	
	
	private AdaptadorParqueSincronizado() {
		parque = new Parque(50);
		this.contadorPersonasTotales = parque.getAforo();
		
	}
	
	public static AdaptadorParqueSincronizado getInstancia() {
		return instancia;
	}
	
	@Override
	public synchronized void entrarAlParque(String puerta) {
		
		parque.entrarAlParque(puerta);
		
	}

	@Override
	public synchronized void salirDelParque(String puerta) {
		parque.salirDelParque(puerta);
		
	}
	
	
	
	
	protected void comprobarAntesDeEntrar(){
		while(contadorPersonasTotales == 50) {
			try {
				this.wait();	
				
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		this.notify();
		
	}
	
	protected void comprobarAntesDeSalir(){
		while(contadorPersonasTotales == 0) {
			try {
				this.wait();	

			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		this.notify();
		
	}

	@Override
	public int getAforo() {
		// TODO Auto-generated method stub
		return parque.getAforo();
	}

}