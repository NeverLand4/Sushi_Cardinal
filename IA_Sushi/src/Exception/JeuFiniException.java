package Exception;

public class JeuFiniException extends Exception {
	
	private static String message = "Le jeu est fini";
	public JeuFiniException(){
		super (message);
	}

}