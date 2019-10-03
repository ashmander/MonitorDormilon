import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;

public class Atention {
	
	private Semaphore attention;
	private Semaphore room;
	private PriorityQueue<Student> roomWait; 
	private Monitor monitor;
	
	public Atention () {
		attention = new Semaphore(1);
		room = new Semaphore(1);
		roomWait = new PriorityQueue<Student>();
		monitor = new Monitor(this, "Andres");
		monitor.start();
		System.out.println("Monitor listo para ayudar");
	}
	
	public void startAtention(Student student) {
		if(!thereStudents())  {
			attendStudent(student);
		} else {
			addStudentRoomWait(student);
		}
	}
	
	public void addStudentRoomWait (Student student) {
		try {
			room.acquire();
			if(roomWait.size()<3) {
				roomWait.add(student);
			}
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} finally {
			room.release();
		}
	}
	
	public void attendStudent(Student student) {
		try {
			if(attention.availablePermits()==0) {
				addStudentRoomWait(student);
			} else {
				if(monitor.getDescanso().availablePermits()==1) {
					attention.acquire();
					monitor.setStudent(student);
					
				} else {
					monitor.getDescanso().release();
					attention.acquire();
					monitor.setStudent(student);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(thereStudents()) {
				attendStudent(roomWait.poll());
			}
		}
	}
	
	public boolean thereStudents() {
		return roomWait.size()>0?true:false;
	}
	
	public Semaphore getAttention() {
		return attention;
	}
	
	
}
