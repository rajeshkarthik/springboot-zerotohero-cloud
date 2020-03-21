package com.udusdha.zero_to_hero_cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.stream.Stream;

@SpringBootApplication
public class ZeroToHeroCloudApplication {

	@Bean
	CommandLineRunner commandLineRunner (ReservationRepository reservationRepository) {
		return strings -> {
			Stream.of("Udaya","Shamendra", "Sirisena", "Dulini", "Jagodage")
					.forEach( name -> reservationRepository.save(new Reservation(name)));

			reservationRepository.findAll().forEach(System.out::println);
			reservationRepository.findByReservationName("Udaya").forEach(System.out::println);
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(ZeroToHeroCloudApplication.class, args);
	}

}

@Component //From Spring Bot Stereotypes
class DummyCLR implements CommandLineRunner //From Spring boot
{
	@Override //From Java Lang
	public void run(String... strings) throws Exception {

	}

	@Autowired //Field injection is not recommended: why?
	private ReservationRepository reservationRepository;
}

@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@RestResource (path = "by-name") //Why?
	Collection<Reservation> findByReservationName (String rn);

}

@Entity //From Javax Persistence API
class Reservation {
	@Id //From Javax Persistence API
	@GeneratedValue //From Javax Persistence API
	private Long id ;

	private String reservationName ;

	public Reservation(String reservationName) {
		this.reservationName = reservationName;
	}

	public Reservation() { // for JPA why JPA why?
	}

	@Override
	public String toString() {
		return "Reservation{" +
				"id=" + id +
				", reservationName='" + reservationName + '\'' +
				'}';
	}

	public Long getId() {
		return id;
	}

	public String getReservationName() {
		return reservationName;
	}
}
