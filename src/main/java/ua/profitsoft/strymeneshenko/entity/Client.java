package ua.profitsoft.strymeneshenko.entity;

//Абсрактная сущность клиент
public abstract class Client {

	protected String adress = "";

	public Client() {
		adress = "Kharkiv, Klochkivska street 111-A";
	}

	public Client(String adress) {
		this.adress = adress;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

}
