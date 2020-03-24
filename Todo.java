
public class Todo {
	private String todo;
	private int nr;
	private boolean aktiv = false;
	private DataBase db;
	
	public Todo(String todo, DataBase db) {
		this.nr = (int)(Math.random()*10000); // ?? må nok finne en oppdaterings mulighet her.
		this.todo = todo;
		this.aktiv = true;
		this.db = db;
	}
	
	public void leggTilIDb() {
		String tempSQL = "INSERT INTO todo values(" + this.nr + ", '" + this.todo + "', " + this.aktiv +")" ;
		db.lagNy(tempSQL);
	}
	
	public void gjorInnaktiv() {
		this.aktiv = false;
		// update sqlite med inaktiv
		// update sql where 
		String tempSQL = "";
	}
	
	public String toString() {
		if ( this.aktiv ) {
		return "Gjøremål: " + todo;
	} else {
		System.out.print("inaktiv");
		return "";
		}
	}
}
