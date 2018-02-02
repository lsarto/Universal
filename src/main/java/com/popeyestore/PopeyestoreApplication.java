package com.popeyestore;

import java.util.ArrayList;
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
		Role role1= new Role();
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
		Role role2= new Role();
		role2.setRoleId(1);
		role2.setName("ROLE_USER");
		userRoles.add(new UserRole(user2, role2));
		
		userService.createUser(user2, userRoles);
		
//		Type type1 = new Type();
//		Type type2 = new Type();
//		type1.setName("Type1");
//		type2.setName("Type2");
//		type1.setProducts(new ArrayList<Product>());
//		type2.setProducts(new ArrayList<Product>());
		Type kitCompleti = new Type();
		kitCompleti.setName("Kit Completi");
		kitCompleti.setProducts(new ArrayList<Product>());
		Type tvEaudio = new Type();
		tvEaudio.setName("Tv e Audio");
		tvEaudio.setProducts(new ArrayList<Product>());
		Type fotografiaEauto = new Type();
		fotografiaEauto.setName("Fotografia e Auto");
		fotografiaEauto.setProducts(new ArrayList<Product>());
		Type elettrodomestici = new Type();
		elettrodomestici.setName("Elettrodomestici");
		elettrodomestici.setProducts(new ArrayList<Product>());

		
//		Category category11 = new Category();
//		Category category12 = new Category();
//		Category category21 = new Category();
//		Category category22 = new Category();
		Category kitArmax = new Category();
		Category kitAspire = new Category();
		Category kitEleaf = new Category();
		Category kitEnvii = new Category();
		Category kitEycotech = new Category();
		Category kitGeekvape = new Category();
		Category kitHcigar = new Category();
		Category kitHugoVapor = new Category();
		Category kitIjoi = new Category();
		Category kitYocan = new Category();
		Category kitInnokin = new Category();
		Category kitKanger = new Category();
		Category kitJustfog = new Category();
		Category kitJoyetech = new Category();
		Category kitVapeOnly = new Category();
		Category kitVaporesso = new Category();
		Category kitVivakita = new Category();
		Category kitSigelei = new Category();
		Category kitSmok = new Category();
		Category kitSuorin = new Category();
		Category kitWismec = new Category();


		kitArmax.setName("Kit Armax");
		kitAspire.setName("Kit Aspire");
		kitEleaf.setName("Kit Eleaf");
		kitEnvii.setName("Kit Envii");
		kitEycotech.setName("Kit Eycotech");
		kitGeekvape.setName("Kit Geekvape");
		kitHcigar.setName("Kit Hcigar");
		kitHugoVapor.setName("Kit Hugo Vapor");
		kitIjoi.setName("Kit Ijoi");
		kitYocan.setName("Kit Yocan");
		kitInnokin.setName("Kit Innokin");
		kitKanger.setName("Kit Kanger");
		kitJustfog.setName("Kit Justfog");
		kitJoyetech.setName("Kit Joyetech");
		kitVapeOnly.setName("Kit Vapeonly");
		kitVaporesso.setName("Kit Vaporesso");
		kitVivakita.setName("Kit Vivakita");
		kitSigelei.setName("Kit Sigelei");
		kitSmok.setName("Kit Smok");
		kitSuorin.setName("kit Suorin");
		kitWismec.setName("Kit Wismec");

		
//		category11.setName("category11");
//		category12.setName("category12");
//		
//		category21.setName("category21");
//		category22.setName("category22");
		
//		typeService.createType(type1);
//		typeService.createType(type2);
		typeService.createType(kitCompleti);
		typeService.createType(tvEaudio);
		typeService.createType(fotografiaEauto);
		typeService.createType(elettrodomestici);
		typeService.createType(elettrodomestici);


		
//		categoryService.createCategory(category11, type1);
//		categoryService.createCategory(category12, type1);
//		categoryService.createCategory(category21, type2);
//		categoryService.createCategory(category22, type2);
		categoryService.createCategory(kitArmax, kitCompleti);
		categoryService.createCategory(kitAspire, kitCompleti);
		categoryService.createCategory(kitEleaf, kitCompleti);
		categoryService.createCategory(kitEnvii, kitCompleti);
		categoryService.createCategory(kitEycotech, kitCompleti);
		categoryService.createCategory(kitGeekvape, kitCompleti);
		categoryService.createCategory(kitHcigar, kitCompleti);
		categoryService.createCategory(kitHugoVapor, kitCompleti);
		categoryService.createCategory(kitIjoi, kitCompleti);
		categoryService.createCategory(kitYocan, kitCompleti);
		categoryService.createCategory(kitInnokin, kitCompleti);
		categoryService.createCategory(kitKanger, kitCompleti);
		categoryService.createCategory(kitJustfog, kitCompleti);
		categoryService.createCategory(kitJustfog, kitCompleti);
		categoryService.createCategory(kitJoyetech, kitCompleti);
		categoryService.createCategory(kitVapeOnly, kitCompleti);
		categoryService.createCategory(kitVaporesso, kitCompleti);
		categoryService.createCategory(kitVivakita, kitCompleti);
		categoryService.createCategory(kitSigelei, kitCompleti);
		categoryService.createCategory(kitSmok, kitCompleti);
		categoryService.createCategory(kitSuorin, kitCompleti);
		categoryService.createCategory(kitWismec, kitCompleti);
		
		
//		categoryService.createCategory(tv, tvEaudio);
//		categoryService.createCategory(videoproiettori, tvEaudio);
//		categoryService.createCategory(accessoriTV, tvEaudio);
//		categoryService.createCategory(decoder, tvEaudio);
//		categoryService.createCategory(macchineFotografiche, fotografiaEauto);
//		categoryService.createCategory(obiettiviReflex, fotografiaEauto);
//		categoryService.createCategory(obiettiviMirrorless, fotografiaEauto);
//		categoryService.createCategory(navigazione, fotografiaEauto);
//		categoryService.createCategory(cottura, elettrodomestici);
//		categoryService.createCategory(frigoriferi, elettrodomestici);
//		categoryService.createCategory(lavastoviglie, elettrodomestici);
//		categoryService.createCategory(lavatrici, elettrodomestici);

	}
}
