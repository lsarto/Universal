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
		

		Type kitCompleti = new Type();
		kitCompleti.setName("Kit Completi");
		kitCompleti.setProducts(new ArrayList<Product>());
		Type bigBattery = new Type();
		bigBattery.setName("Big Battery");
		bigBattery.setProducts(new ArrayList<Product>());
		Type batterie = new Type();
		batterie.setName("Batterie");
		batterie.setProducts(new ArrayList<Product>());
		Type atomANDcoil = new Type();
		atomANDcoil.setName("Atom&Coil");
		atomANDcoil.setProducts(new ArrayList<Product>());
		Type accessori = new Type();
		accessori.setName("Accessori");
		accessori.setProducts(new ArrayList<Product>());

		

		Category kitAramax = new Category();
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
		
		Category aramax = new Category();
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
		
		Category batterieLitio = new Category();
		Category batterieEgo = new Category();
		Category batterieVision = new Category();
		Category caricabatterieBB = new Category();
		Category caricabatterieEgo = new Category();
		Category caricabatterieHubUsb = new Category();
		
		Category advken = new Category();
		Category aramaxAtom = new Category();
		Category arteryVapor = new Category();
		Category aspireAtom = new Category();
		Category blitz = new Category();
		Category cigpet = new Category();
		Category cthulhu = new Category();
		Category desire = new Category();
		Category digiflavorAtom = new Category();
		Category ehpro = new Category();
		Category eleafAspire = new Category();
		Category eycotechAtom = new Category();
		Category eTube = new Category();
		Category geekVape = new Category();
		Category heatVape = new Category();
		Category hotcigAtom = new Category();
		Category innokinAtom = new Category();
		Category ijoyAtom = new Category();
		Category justfog = new Category();
		Category joyetechAtom = new Category();
		Category kangertechAtom = new Category();
		Category kindbright = new Category();
		Category ncr = new Category();
		Category obs = new Category();
		Category oumier = new Category();
		Category oniyo = new Category();
		Category sense = new Category();
		Category sigeleiAtom = new Category();
		Category smoantBattlestar = new Category();
		Category smokAtom = new Category();
		Category teslaAtom = new Category();
		Category tigertek = new Category();
		Category unicig = new Category();
		Category vandyVape = new Category();
		Category vaporessoAtom = new Category();
		Category vgod = new Category();
		Category vapeOnly = new Category();
		Category vivakitaAtom = new Category();
		Category voopoo = new Category();
		Category wismecAtom = new Category();
		Category wotofoAtom = new Category();
		Category youde = new Category();
		
		
		Category circuit = new Category();
		Category adattatori = new Category();
		Category cover = new Category();
		Category handSpinnerFidget = new Category();
		Category dripTip = new Category();
		Category ecigTesterTip = new Category();
		Category flaconiEsiringhe = new Category();
		Category lavatriciUltrasuoni = new Category();
		Category agitatoriMagnetici = new Category();
		Category nanofixit = new Category();
		Category stand = new Category();
		Category cotton = new Category();
		Category wire = new Category();
		Category tools = new Category();
		Category vapenut = new Category();


		kitAramax.setName("Kit Aramax");
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

		aramax.setName("Aramax V/W");
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

		batterieLitio.setName("Batterie Litio");
		batterieEgo.setName("Batterie Ego");
		batterieVision.setName("Batterie Vision");
		caricabatterieBB.setName("Caricabatterie BB");
		caricabatterieEgo.setName("Caricabatterie Ego");
		caricabatterieHubUsb.setName("Caricabatterie HUB USB");

		advken.setName("Advken");
		aramaxAtom.setName("Aramax");
		arteryVapor.setName("Artery Vapor");
		aspireAtom.setName("Aspire");
		blitz.setName("Blitz");
		cigpet.setName("Cigpet");
		cthulhu.setName("Cthulhu");
		desire.setName("Desire");
		digiflavorAtom.setName("Digiflavor");
		ehpro.setName("Ehpro");
		eleafAspire.setName("EleafAspire");
		eycotechAtom.setName("Eycotech");
		eTube.setName("eTube");
		geekVape.setName("Geek Vape");
		heatVape.setName("Heat Vape");
		hotcigAtom.setName("Hotcig");
		innokinAtom.setName("Innokin");
		ijoyAtom.setName("Ijoy");
		justfog.setName("Justfog");
		joyetechAtom.setName("Joyetech");
		kangertechAtom.setName("Kangertech");
		kindbright.setName("Kindbright");
		ncr.setName("NCR");
		obs.setName("OBS");
		oumier.setName("Oumier");
		oniyo.setName("Oniyo");
		sense.setName("Sense");
		sigeleiAtom.setName("Sigelei");
		smoantBattlestar.setName("Smoant Battlestar");
		smokAtom.setName("Smok");
		teslaAtom.setName("Tesla");
		tigertek.setName("Tigertek");
		unicig.setName("Unicig");
		vandyVape.setName("Vandy Vape");
		vaporessoAtom.setName("Vaporesso");
		vgod.setName("Vgod");
		vapeOnly.setName("Vape Only");
		vivakitaAtom.setName("Vivakita");
		voopoo.setName("Voopoo");
		wismecAtom.setName("Wismec");
		wotofoAtom.setName("Wotofo");
		youde.setName("Youde");

		
		circuit.setName("Circuit V/W");
		adattatori.setName("Adattatori");
		cover.setName("Cover");
		handSpinnerFidget.setName("Hand Spinner Fidget");
		dripTip.setName("Drip Tip");
		ecigTesterTip.setName("Ecig Tester Tip");
		flaconiEsiringhe.setName("Flaconi e Siringhe");
		lavatriciUltrasuoni.setName("Lavatrici Ultrasuoni");
		agitatoriMagnetici.setName("Agitatori Magnetici");
		nanofixit.setName("Nanofixit");
		stand.setName("Stand");
		cotton.setName("Cotton");
		wire.setName("Wire");
		tools.setName("Tools");
		vapenut.setName("Vapenut");
		
	
		typeService.createType(kitCompleti);
		typeService.createType(bigBattery);
		typeService.createType(batterie);
		typeService.createType(atomANDcoil);
		typeService.createType(accessori);

		
		categoryService.createCategory(kitAramax, kitCompleti);
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
		
		categoryService.createCategory(aramax, bigBattery);
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


		categoryService.createCategory(batterieLitio, batterie);
		categoryService.createCategory(batterieEgo, batterie);
		categoryService.createCategory(batterieVision, batterie);
		categoryService.createCategory(caricabatterieBB, batterie);
		categoryService.createCategory(caricabatterieEgo, batterie);
		categoryService.createCategory(caricabatterieHubUsb, batterie);
		
		
		categoryService.createCategory(advken, atomANDcoil);
		categoryService.createCategory(aramaxAtom, atomANDcoil);
		categoryService.createCategory(arteryVapor, atomANDcoil);
		categoryService.createCategory(aspireAtom, atomANDcoil);
		categoryService.createCategory(blitz, atomANDcoil);
		categoryService.createCategory(cigpet, atomANDcoil);
		categoryService.createCategory(cthulhu, atomANDcoil);
		categoryService.createCategory(desire, atomANDcoil);
		categoryService.createCategory(digiflavorAtom, atomANDcoil);
		categoryService.createCategory(ehpro, atomANDcoil);
		categoryService.createCategory(eleafAspire, atomANDcoil);
		categoryService.createCategory(eycotechAtom, atomANDcoil);
		categoryService.createCategory(eTube, atomANDcoil);
		categoryService.createCategory(geekVape, atomANDcoil);
		categoryService.createCategory(heatVape, atomANDcoil);
		categoryService.createCategory(hotcigAtom, atomANDcoil);
		categoryService.createCategory(innokinAtom, atomANDcoil);
		categoryService.createCategory(ijoyAtom, atomANDcoil);
		categoryService.createCategory(justfog, atomANDcoil);
		categoryService.createCategory(joyetechAtom, atomANDcoil);
		categoryService.createCategory(kangertechAtom, atomANDcoil);
		categoryService.createCategory(kindbright, atomANDcoil);
		categoryService.createCategory(ncr, atomANDcoil);
		categoryService.createCategory(obs, atomANDcoil);
		categoryService.createCategory(oumier, atomANDcoil);
		categoryService.createCategory(oniyo, atomANDcoil);
		categoryService.createCategory(sense, atomANDcoil);
		categoryService.createCategory(sigeleiAtom, atomANDcoil);
		categoryService.createCategory(smoantBattlestar, atomANDcoil);
		categoryService.createCategory(smokAtom, atomANDcoil);
		categoryService.createCategory(teslaAtom, atomANDcoil);
		categoryService.createCategory(tigertek, atomANDcoil);
		categoryService.createCategory(unicig, atomANDcoil);
		categoryService.createCategory(vandyVape, atomANDcoil);
		categoryService.createCategory(vaporessoAtom, atomANDcoil);
		categoryService.createCategory(vgod, atomANDcoil);
		categoryService.createCategory(vapeOnly, atomANDcoil);
		categoryService.createCategory(vivakitaAtom, atomANDcoil);
		categoryService.createCategory(voopoo, atomANDcoil);
		categoryService.createCategory(wismecAtom, atomANDcoil);
		categoryService.createCategory(wotofoAtom, atomANDcoil);


		categoryService.createCategory(circuit, accessori);
		categoryService.createCategory(adattatori, accessori);
		categoryService.createCategory(cover, accessori);
		categoryService.createCategory(handSpinnerFidget, accessori);
		categoryService.createCategory(dripTip, accessori);
		categoryService.createCategory(ecigTesterTip, accessori);
		categoryService.createCategory(flaconiEsiringhe, accessori);
		categoryService.createCategory(lavatriciUltrasuoni, accessori);
		categoryService.createCategory(agitatoriMagnetici, accessori);
		categoryService.createCategory(nanofixit, accessori);
		categoryService.createCategory(stand, accessori);
		categoryService.createCategory(cotton, accessori);
		categoryService.createCategory(wire, accessori);
		categoryService.createCategory(tools, accessori);
		categoryService.createCategory(vapenut, accessori);

	}
}
