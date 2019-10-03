
public class Principal {
	
	public static void main (String [] args) {
		Atention atention = new Atention();
		int n = (int)(Math.random()*100);
		for(int i = 0; i < n; i++) {
			Student student = new Student(i+1,atention);
			student.start();
		}
	}
}
