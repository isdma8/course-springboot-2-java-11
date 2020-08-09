package com.enterprise.course.config;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.enterprise.course.entities.Category;
import com.enterprise.course.entities.Order;
import com.enterprise.course.entities.User;
import com.enterprise.course.entities.enums.OrderStatus;
import com.enterprise.course.repositories.CategoryRepository;
import com.enterprise.course.repositories.OrderRepository;
import com.enterprise.course.repositories.UserRepository;

//Usamos esta class unicamente para testes e confs, por isso mesmo usamos tambem uma 
//interface de testes em application-test.properties
@Configuration
//test no profile, nome igualzinho ao colocado no application propreties
// assim identifica que so roda configuração apenas quando esta no perfil de test
@Profile("test")
public class TestConfig implements CommandLineRunner {
	// para ja usamos esta class para popular a nossa BD

	// Injeções de dependencias dos Repositorys que no fundo dependem por completo
	// do JPA
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void run(String... args) throws Exception {// fizemos um implements da interface CommandLineRunner e temos
														// assim obrigaçao de implementar este metodo que irá ser
														// executado sempre que executarmos a aplicação e é isso que nos
														// queremos
		// TODO Auto-generated method stub

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

		// Para salvar chamo o objeto userrepository que acessa dados
		userRepository.saveAll(Arrays.asList(u1, u2));

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

		orderRepository.saveAll(Arrays.asList(o1, o2, o3));

		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));

	}

}
