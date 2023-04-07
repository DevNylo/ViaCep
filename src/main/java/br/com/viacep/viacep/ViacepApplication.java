package br.com.viacep.viacep;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.viacep.viacep.model.Address;
import br.com.viacep.viacep.services.ViaCepService;

@SpringBootApplication
public class ViacepApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViacepApplication.class, args);

		Scanner input = new Scanner(System.in);
		ViaCepService viaCepService = new ViaCepService();

		System.out.println("Informe o seu CEP (apenas números): ");
        String cep = input.nextLine();

		try {
			Address address = viaCepService.getAddress(cep);
			System.out.println(address);
		} catch (IOException e) {
			System.out.println("O cep informado não existe ou você digitou incorretamente;\n"+e);
		}
		input.close();
	}

}
