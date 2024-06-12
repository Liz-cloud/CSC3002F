package molecule;

public class Hydrogen extends Thread {

	private static int hydrogenCounter =0;
	private int id;
	private Propane sharedPropane;
	
	
	public Hydrogen(Propane propane_obj) {
		Hydrogen.hydrogenCounter+=1;
		id=hydrogenCounter;
		this.sharedPropane = propane_obj;
		
	}
	public void run() {
		try {
			sharedPropane.mutex.acquire();

			sharedPropane.addHydrogen();
			int carbon=sharedPropane.getCarbon();
			int hydrogen=sharedPropane.getHydrogen();
			if(carbon>=3 & hydrogen>=8 ){
				System.out.println("---Group ready for bonding---");
				sharedPropane.hydrogensQ.release(8);
				sharedPropane.removeHydrogen(8);
				sharedPropane.carbonQ.release(3);
				sharedPropane.removeCarbon(3);
			}
			else {
				sharedPropane.mutex.release();
			}
			sharedPropane.hydrogensQ.acquire();
			sharedPropane.bond("H"+ this.id);
			sharedPropane.barrier.b_wait();
			sharedPropane.mutex.release();
		}catch (InterruptedException ex) { System.out.println(ex);/* not handling this  */}
		//System.out.println(" ");
	}

}
