
public class Student extends Thread{
	
	private int id;
	private boolean wasAttended;
	private Atention atention;
	
	public Student(int id, Atention atention) {
		super();
		this.id = id;
		this.atention = atention;
	}
	
	public void run() {
		while(!wasAttended) {
			atention.startAtention(this);
			int dormir = (int)(Math.random()*100);
			try {
				Thread.sleep(dormir);
				System.out.println("Estudiante con id "+this.id+" fue a programar en sala de computo");
			} catch (Exception e){
				
			}
		}
	}
	
	public int getIdStudent() {
		return id;
	}
	public void setIdStudent(int id) {
		this.id = id;
	}


	public boolean isWasAttended() {
		return wasAttended;
	}

	public void setWasAttended(boolean wasAttended) {
		this.wasAttended = wasAttended;
	}

	
	
	
}
