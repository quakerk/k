package TestReact.k;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages={"TestReact.k.JpaRepo", "TestReact.k.Entity"})
@EnableJpaRepositories(basePackages = {"TestReact.k.JpaRepo"})
@SpringBootApplication
public class KApplication {

	public static void main(String[] args)
	{
		System.out.println("시작!!");
		//SpringApplication.run(KApplication.class, args);
		new SpringApplicationBuilder(KApplication.class).web(WebApplicationType.SERVLET).run(args);
	}

}
