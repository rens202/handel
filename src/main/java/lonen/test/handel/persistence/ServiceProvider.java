package lonen.test.handel.persistence;

public class ServiceProvider {
	//vanuit deze klasse worden alle services aangeroepen
	private static KlantenService klantenService = new KlantenService();


	public static KlantenService getKlantenService() {
		return klantenService;
	}
	

}