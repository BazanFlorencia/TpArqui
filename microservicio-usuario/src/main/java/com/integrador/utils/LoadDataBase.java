package com.integrador.utils;

import java.sql.Timestamp;
import java.util.Date;

import com.integrador.domain.mongodb.CuentaMongo;
import com.integrador.domain.mongodb.UsuarioMongo;
import com.integrador.repository.mongodb.CuentaMongoRepository;
import com.integrador.repository.mongodb.UsuarioMongoRepository;
import com.integrador.service.dto.cuenta.CuentaDTO;
import com.integrador.service.dto.usuario.UsuarioDTO;
import com.integrador.service.mongodb.CuentaServiceMongo;
import com.integrador.service.mongodb.UsuarioServiceMongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDataBase {


	Logger log = LoggerFactory.getLogger(LoadDataBase.class);

	@Bean
	CommandLineRunner initDatabase(UsuarioMongoRepository usuarioRepository, CuentaMongoRepository cuentaRepository) {
		return args -> {

			UsuarioDTO ur1 = new UsuarioDTO("1","Gustavo", "Lopez", "2494352789", "gustavolopez@gmail.com");
			UsuarioDTO ur2 = new UsuarioDTO("2","Pedro", "Perez", "2494271078", "pedroperez@gmail.com");
			UsuarioDTO ur3 = new UsuarioDTO("3","Marcelo", "Rodriguez", "2494247492", "marcelorodriguez@gmail.com");
			UsuarioDTO ur4 = new UsuarioDTO("4","Julieta", "Martinez", "2494964521", "julietamartinez@gmail.com");
			UsuarioDTO ur5 = new UsuarioDTO("5","Pamela", "Benitez", "2494957934", "pamelabenitez@gmail.com");
			UsuarioDTO ur6 = new UsuarioDTO("6","Tomás", "Rupero", "2494957934", "tomasrupero@gmail.com");
			UsuarioDTO ur7 = new UsuarioDTO("7","Lucia", "Torino", "2494957934", "luciatorino@gmail.com");

			UsuarioServiceMongo us = new UsuarioServiceMongo (usuarioRepository, cuentaRepository);

			us.save(ur1);
			us.save(ur2);
			us.save(ur3);
			us.save(ur4);
			us.save(ur5);
			us.save(ur6);
			us.save(ur7);

			Timestamp fecha1 = Timestamp.valueOf("2023-04-24 10:10:10.0");
			Timestamp fecha2 = Timestamp.valueOf("2023-05-30 10:10:10.0");
			Timestamp fecha3 = Timestamp.valueOf("2023-06-12 10:10:10.0");
			Timestamp fecha4 = Timestamp.valueOf("2023-06-13 10:10:10.0");
			Timestamp fecha5 = Timestamp.valueOf("2023-09-21 10:10:10.0");
			Timestamp fecha6 = Timestamp.valueOf("2023-10-19 10:10:10.0");
			Date date1 = new Date(fecha1.getTime());
			Date date2= new Date(fecha2.getTime());
			Date date3= new Date(fecha3.getTime());
			Date date4= new Date(fecha4.getTime());
			Date date5= new Date(fecha5.getTime());
			Date date6= new Date(fecha6.getTime());


			CuentaDTO cr1 = new CuentaDTO("1",510, date1, true);
			CuentaDTO cr2 = new CuentaDTO("2",246, date2, false);
			CuentaDTO cr3 = new CuentaDTO("3",432, date3, true);
			CuentaDTO cr4 = new CuentaDTO("4",1245, date4, false);
			CuentaDTO cr5 = new CuentaDTO("5",2465, date5, true);
			CuentaDTO cr6 = new CuentaDTO("6",7821, date6, true);

			CuentaServiceMongo cs = new CuentaServiceMongo(cuentaRepository);

			cs.save(cr1);
			cs.save(cr2);
			cs.save(cr3);
			cs.save(cr4);
			cs.save(cr5);
			cs.save(cr6);


			UsuarioMongo usuario1 = new UsuarioMongo(ur1);
			UsuarioMongo usuario2 = new UsuarioMongo(ur2);
			UsuarioMongo usuario3 = new UsuarioMongo(ur3);
			UsuarioMongo usuario4 = new UsuarioMongo(ur4);
			UsuarioMongo usuario5 = new UsuarioMongo(ur5);
			UsuarioMongo usuario6 = new UsuarioMongo(ur6);
			UsuarioMongo usuario7 = new UsuarioMongo(ur7);

			CuentaMongo cuenta1 = new CuentaMongo(cr1);
			CuentaMongo cuenta2 = new CuentaMongo(cr2);
			CuentaMongo cuenta3 = new CuentaMongo(cr3);
			CuentaMongo cuenta4 = new CuentaMongo(cr4);
			CuentaMongo cuenta5 = new CuentaMongo(cr5);
			CuentaMongo cuenta6 = new CuentaMongo(cr6);



			usuario1.insertarCuenta(cuenta1);
			usuario1.insertarCuenta(cuenta2);
			usuario2.insertarCuenta(cuenta2);
			usuario3.insertarCuenta(cuenta3);
			usuario4.insertarCuenta(cuenta5);
			usuario5.insertarCuenta(cuenta5);
			usuario6.insertarCuenta(cuenta6);
			usuario3.insertarCuenta(cuenta4);
			usuario7.insertarCuenta(cuenta6);




		};

	}
}
