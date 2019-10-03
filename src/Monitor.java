import java.util.concurrent.Semaphore;

public class Monitor extends Thread{
	
	private Atention atention;
	private String name;
	private Semaphore descanso;
	private Student studentAttended;
	
	public Monitor(Atention atention, String name) {
		super();
		this.atention = atention;
		this.name = name;
		descanso = new Semaphore(0);
	}
	
	public void run () {
		while(true) {
			if(atention.thereStudents()) {
				attentionStudent();
				atention.getAttention().release();
			} else {
				try {
					descanso.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void attentionStudent() {
		try {
			int time = (int)Math.random()*100;
			Thread.sleep(time);	
			studentAttended.setWasAttended(true);
			System.out.println("Estudiante con id"+studentAttended.getIdStudent()+" fue atendido");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getNameMonitor() {
		return name;
	}

	public void setNameMonitor(String name) {
		this.name = name;
	}
	
	public void setSemaphore(Semaphore semaphore) {
		descanso = semaphore;
	}
	
	public Semaphore getDescanso() {
		return descanso;
	}
	
	
	public void setStudent(Student student) {
		studentAttended = student;
	}
}
