package com.nisum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nisum.dao.MongoDao;
import com.nisum.model.Product;

/**
 * Main Spring Boot Application Class
 * @author nisum
 *
 */
@SpringBootApplication
public class NKartApplicationMain implements CommandLineRunner {

	@Autowired
	private MongoDao mongoDAO;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(NKartApplicationMain.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//createDummyProducts(args);
		//createDummyUsers(args);
	}

	public void createDummyProducts(String... args) throws Exception {
		// Currently not deleting any values
		//mongoDAO.deleteProducts();
		Product product1 = new Product("Rayban Glasess", "Cooling glasses, Non Reflective",
				5000, "IndiaMart", "https://assets.ray-ban.com//is/image/RayBan/8053672684315_shad_qt?$594$");
		System.out.println(mongoDAO.createProduct(product1));
		Product product2 = new Product( "Fossil Watch", "Smart Watch,Water Resistant", 50000,
				"Universal Store", "https://static3.wareable.com/media/imager/11349-67ce2eb53fd901111c0a3ada9a610746.jpg");
		System.out.println(mongoDAO.createProduct(product2));
		Product product3 = new Product("Fitbit", "Health Tracker,Smart watch", 12000,
				"HiTechStore",
				"https://img.bbystatic.com/BestBuy_US/store/ee/2017/wear/pr/SOL-11177/Performance_prod.jpg");
		System.out.println(mongoDAO.createProduct(product3));
		Product product4 = new Product( "Nextbit Robin", "Smart Phone, Cloud Storage", 12000,
				"HiTechStore",
				"http://drop.ndtv.com/TECH/product_database/images/Nextbit_db_2_2907_800X600_522016120143PM.jpg");
		System.out.println(mongoDAO.createProduct(product4));
		Product product5 = new Product("Tesla X", "Self Driving Car, Electric Car", 3500000,
				"AIHub",
				"https://cnet4.cbsistatic.com/img/3-14WgVgrMGZ_pKcAqsefdLScJc=/830x467/2016/04/28/5a91e8fc-14bc-4c22-abe4-b990073328d2/2016-tesla-model-s-19-of-43.jpg");
		System.out.println(mongoDAO.createProduct(product5));
		Product product6 = new Product( "Macbook Pro", "Laptop, Apple, MacOS", 150000,
				"HiTechStore",
				"http://cdn.wccftech.com/wp-content/uploads/2016/10/2016-MacBook-Pro-with-Touch-Bar.png");
		System.out.println(mongoDAO.createProduct(product6));
		Product product7 = new Product( "Google Home", "Personal Assistant,AI", 13500,
				"AIHub",
				"https://cnet2.cbsistatic.com/img/d4UzB6o3QwUC6WDouFbXYuW__po=/770x433/2016/11/02/d405a6ab-5f2b-4dec-a1f8-67cccaca77d3/google-home-product-photos-20.jpg");
		System.out.println(mongoDAO.createProduct(product7));
		Product product8 = new Product( "Microsoft Hololens", "AR VR Headset", 103500,
				"AIHub",
				"https://infinityleap.com/wp-content/uploads/2015/07/microsoft-hololens-release-price.jpg");
		System.out.println(mongoDAO.createProduct(product8));
		Product product9 = new Product("Amazon Echo", "Personal Assistant,AI", 10000,
				"AIHub",
				"http://anrg.usc.edu/ee579/spring2016/Alexa/echo.jpg");
		System.out.println(mongoDAO.createProduct(product9));
		Product product10 = new Product( "Lamborgini Aventador SV", "Super Car", 13500000,
				"CarShed",
				"http://www.dupontregistry.com/autos/remote.jpg.ashx?404=default&width=644&height=428&urlb64=aHR0cDovL2NvbnRlbnQuaG9tZW5ldGlvbC5jb20vMzU4Lzk1NzIvNjQweDQ4MC81N2RlZTgzNzBlY2Q0OTQ0OTVkZjQ1MDBhNzUzZmNlMC5qcGc&hmac=Xzo9DpHYyR4");
		System.out.println(mongoDAO.createProduct(product10));		
		
	}

//	// @Override
//	public void createDummyUsers(String... args) throws Exception {
//		// Currently not deleting any values
//		//mongoDAO.deleteAll();
//		User user1 = new User("First1", "Last1", "user1", "user1@gm", "pass1",false);
//		User user2 = new User("First2", "Last2", "user2", "user2@gm", "pass2",false);
//		User user3 = new User("First3", "Last3", "user3", "user3@gm", "pass3",false);
//		User user4 = new User("First4", "Last4", "user4", "user4@gm", "pass4",false);
//		System.out.println(mongoDAO.createUser(user1));
//		System.out.println(mongoDAO.createUser(user2));
//		System.out.println(mongoDAO.createUser(user3));
//		System.out.println(mongoDAO.createUser(user4));
//		System.out.println(mongoDAO.createUser(user1));
//	}

}