package m2.MedMngt.DB;

import m2.MedMngt.DB.repository.FarmaciaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MedicationManagementDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicationManagementDbApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}

	@Bean
	CommandLineRunner run(FarmaciaRepository farmaciaRepository){
		return args -> {};
	}
}
