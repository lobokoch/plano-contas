package br.com.kerubin.api.financeiro.planocontas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
		exclude = { 
		        DataSourceAutoConfiguration.class,
		        HibernateJpaAutoConfiguration.class,
		        DataSourceTransactionManagerAutoConfiguration.class
		}
		, scanBasePackages = { "br.com.kerubin.api" }
)
@EnableEurekaClient
// @EnableJpaRepositories("br.com.kerubin.api")
// @EntityScan("br.com.kerubin.api")
public class PlanoContasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanoContasApplication.class, args);
	}
}
