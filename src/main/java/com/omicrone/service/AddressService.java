package com.omicrone.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.omicrone.AddresseNotFoundExceeption;
import com.omicrone.entity.Address;
import com.omicrone.repository.AddressRepository;
import com.omicrone.request.CreateAddressRequest;
import com.omicrone.response.AddressResponse;

import ch.qos.logback.classic.Logger;

@Service
public class AddressService {


	Logger logger = (Logger) LoggerFactory.getLogger(AddressService.class);

	
	@Autowired
	AddressRepository addressRepository;
	
	@Value("${server.port}")
	private int ServerPort;
	
	public AddressResponse createAddress(CreateAddressRequest createStudentRequest) {

		Address address = new Address();
		address.setStreet(createStudentRequest.getStreet());
		address.setCity(createStudentRequest.getCity());

		address = addressRepository.save(address);

		return new AddressResponse(address);
	}

	public AddressResponse getById(long id) {
		
		logger.info("PORT Exécuter =>" +ServerPort);
		if(addressRepository.findById(id).isPresent()) {
		Address address = addressRepository.findById(id).get();
		return new AddressResponse(address);
		}
		else {
			throw new AddresseNotFoundExceeption("addresse inexistant");
		}
	}
}
