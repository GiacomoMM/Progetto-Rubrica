import java.util.Objects;

public class Persona {
	private String nome;
	private String cognome;
	private String indirizzo;
	private String telefono;
	private Integer eta;
	
	public Persona(String n,String c,String i,String t,Integer e){
		this.nome=n;
		this.cognome=c;
		this.indirizzo=i;
		this.telefono=t;
		this.eta=e;
	}
	
	public void setNome(String n) {
		this.nome=n;
	}
	
	public void setCognome(String c) {
		this.cognome=c;
	}
	
	public void setIndirizzo(String i) {
		this.indirizzo=i;
	}
	
	public void setTelefono(String t) {
		this.telefono=t;
	}
	
	public void setEta(Integer e) {
		this.eta=e;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getCognome() {
		return this.cognome;
	}
	
	public String getIndirizzo() {
		return this.indirizzo;
	}
	
	public String getTelefono() {
		return this.telefono;
	}
	
	public Integer getEta() {
		return this.eta;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.telefono);
	}
	
	public String getStringHashCode() {
		return String.valueOf(this.hashCode());
	}
}
