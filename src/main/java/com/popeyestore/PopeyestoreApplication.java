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
		Type bigBattery = new Type();
		bigBattery.setName("Big Battery");
		bigBattery.setProducts(new ArrayList<Product>());

		
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
		
		Category armax = new Category();
		Category arctic = new Category();
		Category aimidi = new Category();
		Category aspire = new Category();
		Category dovpo = new Category();
		Category digiflavor = new Category();
		Category eleaf = new Category();
		Category envii = new Category();
		Category eycotech = new Category();
		Category geekvape = new Category();
		Category gtrs = new Category();
		Category hotcig = new Category();
		Category hcigar = new Category();
		Category hugoVapor = new Category();
		Category sxminiYihi = new Category();
		Category innokin = new Category();
		Category ijoy = new Category();
		Category lostVape = new Category();
		Category justFog = new Category();
		Category joyetech = new Category();
		Category kangertech = new Category();
		Category rev = new Category();
		Category sigelei = new Category();
		Category smok = new Category();
		Category sofgod = new Category();
		Category suorin = new Category();
		Category tesla = new Category();
		Category yosta = new Category();
		Category yiloong = new Category();
		Category yocan = new Category();
		Category thinkvape = new Category();
		Category ud = new Category();
		Category vaoecige = new Category();
		Category vandy = new Category();
		Category vapeonly = new Category();
		Category vaporesso = new Category();
		Category vivakita = new Category();
		Category vgid = new Category();
		Category vzone = new Category();
		Category wismec = new Category();
		Category wotofo = new Category();


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

		armax.setName("Armax V/W");
		arctic.setName("Arctic V/W");
		aimidi.setName("Aimidi V/W");
		aspire.setName("Aspire V/W");
		dovpo.setName("Dovpo V/W");
		digiflavor.setName("Digiflavor V/W");
		eleaf.setName("Eleaf V/W");
		envii.setName("Envii V/W");
		eycotech.setName("Eycotech V/W");
		geekvape.setName("Geekvape V/W");
		gtrs.setName("Gtrs V/W");
		hotcig.setName("Hotcig V/W");
		hcigar.setName("Hcigar V/W");
		hugoVapor.setName("HugoVapor V/W");
		sxminiYihi.setName("SxminiYihi V/W");
		innokin.setName("Innokin V/W");
		ijoy.setName("Ijoy V/W");
		lostVape.setName("LostVape V/W");
		justFog.setName("JustFog V/W");
		joyetech.setName("Joyetech V/W");
		kangertech.setName("Kangertech V/W");
		rev.setName("Rev V/W");
		sigelei.setName("Sigelei V/W");
		smok.setName("Smok V/W");
		sofgod.setName("Sofgod V/W");
		suorin.setName("Suorin V/W");
		tesla.setName("Tesla V/W");
		yosta.setName("Yosta V/W");
		yiloong.setName("Yiloong V/W");
		yocan.setName("Yocan V/W");
		thinkvape.setName("Thinkvape V/W");
		ud.setName("Ud V/W");
		vaoecige.setName("Vaoecige V/W");
		vandy.setName("Vandy V/W");
		vapeonly.setName("Vapeonly V/W");
		vaporesso.setName("Vaporesso V/W");
		vivakita.setName("Vivakita V/W");
		vgid.setName("Vgid V/W");
		vzone.setName("Vzone V/W");
		wismec.setName("Wismec V/W");
		wotofo.setName("Wotofo V/W");

		
		
//		category11.setName("category11");
//		category12.setName("category12");
//		
//		category21.setName("category21");
//		category22.setName("category22");
		
//		typeService.createType(type1);
//		typeService.createType(type2);
		typeService.createType(kitCompleti);
		typeService.createType(bigBattery);


		
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
		
		categoryService.createCategory(armax, bigBattery);
		categoryService.createCategory(arctic, bigBattery);
		categoryService.createCategory(aimidi, bigBattery);
		categoryService.createCategory(aspire, bigBattery);
		categoryService.createCategory(dovpo, bigBattery);
		categoryService.createCategory(digiflavor, bigBattery);
		categoryService.createCategory(eleaf, bigBattery);
		categoryService.createCategory(envii, bigBattery);
		categoryService.createCategory(eycotech, bigBattery);
		categoryService.createCategory(geekvape, bigBattery);
		categoryService.createCategory(gtrs, bigBattery);
		categoryService.createCategory(hotcig, bigBattery);
		categoryService.createCategory(hcigar, bigBattery);
		categoryService.createCategory(hugoVapor, bigBattery);
		categoryService.createCategory(sxminiYihi, bigBattery);
		categoryService.createCategory(innokin, bigBattery);
		categoryService.createCategory(ijoy, bigBattery);
		categoryService.createCategory(lostVape, bigBattery);
		categoryService.createCategory(justFog, bigBattery);
		categoryService.createCategory(joyetech, bigBattery);
		categoryService.createCategory(kangertech, bigBattery);
		categoryService.createCategory(rev, bigBattery);
		categoryService.createCategory(sigelei, bigBattery);
		categoryService.createCategory(smok, bigBattery);
		categoryService.createCategory(sofgod, bigBattery);
		categoryService.createCategory(suorin, bigBattery);
		categoryService.createCategory(tesla, bigBattery);
		categoryService.createCategory(yosta, bigBattery);
		categoryService.createCategory(yiloong, bigBattery);
		categoryService.createCategory(yocan, bigBattery);
		categoryService.createCategory(thinkvape, bigBattery);
		categoryService.createCategory(ud, bigBattery);
		categoryService.createCategory(vaoecige, bigBattery);
		categoryService.createCategory(vandy, bigBattery);
		categoryService.createCategory(vapeonly, bigBattery);
		categoryService.createCategory(vaporesso, bigBattery);
		categoryService.createCategory(vivakita, bigBattery);
		categoryService.createCategory(vgid, bigBattery);
		categoryService.createCategory(vzone, bigBattery);
		categoryService.createCategory(wismec, bigBattery);
		categoryService.createCategory(wotofo, bigBattery);


		
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
