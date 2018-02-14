package com.popeyestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.popeyestore.domain.Type;
import com.popeyestore.domain.Category;
import com.popeyestore.domain.Product;
import com.popeyestore.domain.User;
import com.popeyestore.domain.security.Role;
import com.popeyestore.domain.security.UserRole;
import com.popeyestore.service.CategoryService;
import com.popeyestore.service.TypeService;
import com.popeyestore.service.UserService;
import com.popeyestore.utility.SecurityUtility;

@SpringBootApplication
public class PopeyestoreApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private CategoryService categoryService;

	public static void main(String[] args) {
		SpringApplication.run(PopeyestoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setUsername("admin");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("admin"));
		user1.setEmail("admin@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(0);
		role1.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user1, role1));

		userService.createUser(user1, userRoles);

		User user2 = new User();
		user2.setFirstName("John");
		user2.setLastName("Adams");
		user2.setUsername("j");
		user2.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user2.setEmail("lino.sarto@gmail.com");
		userRoles = new HashSet<>();
		Role role2 = new Role();
		role2.setRoleId(1);
		role2.setName("ROLE_USER");
		userRoles.add(new UserRole(user2, role2));

		userService.createUser(user2, userRoles);
		
		List<String> typesName = Arrays.asList("Big Battery", "Atom&Coil", "Accessori", "E-Liquid", "Aromi&Diy");
		List<String> categoriesBigBatteryName = Arrays.asList("Aramax V/W", "Arctic Dolphin V/W", "Aimidi V/W",
				"Aspire V/W", "Demon Killer V/W", "Dovpo V/W", "Digiflavor V/W", "Eleaf V/W", "Envii V/W",
				"Eycotech Box - Tubi", "Geekvape V/W", "GTRS V/W", "Hotcig V/W", "Hcigar", "Hugo Vapor V/W",
				"Sxmini Yihi V/W", "Innokin V/W", "Ijoy V/W", "Lost Vape V/W", "Justfog V/W", "Joyetech V/W",
				"Kangertech V/W", "Rev", "Sigelei V/W", "Smok V/W", "Sofgod", "Suorin V/W", "Tesla V/W", "Yosta V/W",
				"Yiloong V/W", "Yocan V/W", "Thinkvape V/W", "UD - Youde V/W", "Vapecige V/W", "Vandy Vape V/W",
				"Vapeonly V/W", "Vaporesso V/W", "Vivakita V/W", "Vgod V/W", "Vzone V/W", "Wismec V/W", "Wotofo");
		List<String> categoriesAtomCoilName = Arrays.asList("Advken", "Aramax", "Artery Vapor", "Aspire", "Blitz",
				"Cigpet", "Cthulhu", "Demon Killer", "Desire", "Digiflavor", "Ehpro", "Eleaf", "Eyotech", "E-Tube",
				"Geekvape", "Hellvape", "Heatvape", "Hotcig", "Innokin", "Ijoy", "Justfog", "Joyetech", "Kangertech",
				"Kindbright", "NCR", "OBS", "Oumier", "Oniyo", "Sense", "Sigelei", "Smoant Battlestar", "Smok", "Tesla",
				"Tigertek", "Unicig", "Vandy Vape", "Vaporesso", "Vgod", "Vapeonly", "Vivakita", "Voopoo", "Wismec",
				"Wotofo", "Youde");
		List<String> categoriesAccessoriName = Arrays.asList("Circuiti V/W", "Adattatori", "Cover - Vape Bag",
				"Hand Spinner Fidget", "Drip Tip", "Ecig Tester Tip", "Flaconi e Siringhe", "Lavatrici Ultrasuoni",
				"Agitatori Magnetici", "Nanofixit", "Stand", "Cotton", "Wire", "Tools", "Vapenut");
		List<String> categoriesEliquidName = Arrays.asList("Bam Bam's(USA)", "Biofumo (ITA)", "Black Mvrket (USA)",
				"Boombox (USA)", "Candy Pops (USA)", "Delixia - Elixir (ITA)", "Drops (SPA)", "Enjoysvapo (ITA)",
				"Flavourart (ITA)", "La Tabaccheria (ITA)", "Mylk (USA)", "Mr Macaron (USA)", "Naked 100 (USA)",
				"One Hit Winder (USA)", "Pierre's Toast (USA)", "Real Farma (ITA)", "Royal Blend (ITA)",
				"Rope Cut (Can)", "Saffire Moonshine (USA)", "Smoke Angel (ITA)", "TNT Vape (ITA)", "T-Juice (ENG)",
				"Valkiria (ITA)", "Vaporart (ITA)");
		List<String> categoriesAromiName = Arrays.asList("Basi Nature", "814", "Azhad's Elixirs", "Bigmouth",
				"Ejuice Depo", "Enjoysvapo", "Flavourart", "Galaxy Vape", "King Kong Flavour", "La Tabaccheria", "Lop",
				"Lord Hero", "Mp Juice", "Perfumer's Apprentice", "Super Flavor", "TNT Vape", "T-Juice", "Valkiria",
				"Vaporart");

		List<List<String>> allCategories = new ArrayList<List<String>>();
		allCategories.add(categoriesBigBatteryName);
		allCategories.add(categoriesAtomCoilName);
		allCategories.add(categoriesAccessoriName);
		allCategories.add(categoriesEliquidName);
		allCategories.add(categoriesAromiName);

		

		for (int i = 0; i < typesName.size(); i++) {
			String typeName = typesName.get(i);
			List<String> categoriesName = allCategories.get(i);
			createTypeWithCategories(typeName, categoriesName);
		}

	}

	private void createTypeWithCategories(String typeName, List<String> categoriesName) {
		Type type = new Type();
		type.setName(typeName);
		type.setProducts(new ArrayList<Product>());
		typeService.createType(type);

		for (String categoryName : categoriesName) {
			Category category = new Category();
			category.setName(categoryName);
			categoryService.createCategory(category, type);
		}
	}
}
