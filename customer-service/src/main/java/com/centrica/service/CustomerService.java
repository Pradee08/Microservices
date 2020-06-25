package com.centrica.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.centrica.model.Customer;
import com.centrica.model.CustomerDatabase;
import com.centrica.model.PhoneNumber;
import com.centrica.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repo;

	public void add(Customer customer) {
		CustomerDatabase customers = new CustomerDatabase();
		ModelMapper mapper = new ModelMapper();
		List<String> number = customer.getTelephoneNumbers().stream().map(p -> p.getNumber())
				.collect(Collectors.toList());
		List<String> type = customer.getTelephoneNumbers().stream().map(p -> p.getType()).collect(Collectors.toList());
		customers = mapper.map(customer, CustomerDatabase.class);
		List<String> energyaccount = customer.getEnergyAccounts();
		customers.setEnergyAccounts(String.join(",", energyaccount).replace("[", "").replace("]", ""));
		customers.setNumber(String.join(",", number));
		customers.setType(String.join(",", type));
		repo.save(customers);
		return;
	}

	public List<Customer> retrieve(String ucrn) {
		List<Customer> listofcustomer = new ArrayList<Customer>();
		List<CustomerDatabase> customerList = repo.findByUcrn(ucrn);
		ModelMapper mapper = new ModelMapper();
		customerList.forEach(customers -> {
			Customer customer = new Customer();
			ArrayList<PhoneNumber> phonedetails = new ArrayList<PhoneNumber>();
			customer = mapper.map(customers, Customer.class);
			List<String> energyaccount = Stream.of(customers.getEnergyAccounts().split(","))
					.collect(Collectors.toList());
			customer.setEnergyAccounts(energyaccount);
			List<String> telephonenumber = Stream.of(customers.getNumber().split(",")).collect(Collectors.toList());
			List<String> type = Stream.of(customers.getType().split(",")).collect(Collectors.toList());
			String[] tempnumber = telephonenumber.toArray(new String[0]);
			String[] temptype = type.toArray(new String[0]);
			int length = telephonenumber.size();
			IntStream.range(0, length).forEach(i -> {
				PhoneNumber phonenumber = new PhoneNumber();
				phonenumber.setNumber(tempnumber[i]);
				phonenumber.setType(temptype[i]);
				phonedetails.add(phonenumber);
			});
			customer.setTelephoneNumbers(phonedetails);
			listofcustomer.add(customer);
		});
		return listofcustomer;
	}

	public Customer retrivecustomer(int id) {
		Customer customer = new Customer();
		CustomerDatabase customerList = repo.findById(id);
		ModelMapper mapper = new ModelMapper();
		ArrayList<PhoneNumber> phonedetails = new ArrayList<PhoneNumber>();
		customer = mapper.map(customerList, Customer.class);
		List<String> energyaccount = Stream.of(customerList.getEnergyAccounts().split(","))
				.collect(Collectors.toList());
		customer.setEnergyAccounts(energyaccount);
		List<String> telephonenumber = Stream.of(customerList.getNumber().split(",")).collect(Collectors.toList());
		List<String> type = Stream.of(customerList.getType().split(",")).collect(Collectors.toList());
		String[] tempnumber = telephonenumber.toArray(new String[0]);
		String[] temptype = type.toArray(new String[0]);
		int length = telephonenumber.size();
		IntStream.range(0, length).forEach(i -> {
			PhoneNumber phonenumber = new PhoneNumber();
			phonenumber.setNumber(tempnumber[i]);
			phonenumber.setType(temptype[i]);
			phonedetails.add(phonenumber);
		});
		customer.setTelephoneNumbers(phonedetails);
		return customer;
	}

	public void update(Customer customer, int id, Customer existCustomer) {
		ModelMapper mapper = new ModelMapper();
		if (existCustomer.getId().equals(id)) {
			existCustomer.setFirstName(customer.getFirstName());
			existCustomer = mapper.map(customer, Customer.class);
			CustomerDatabase customers = new CustomerDatabase();
			ModelMapper mapper1 = new ModelMapper();
			List<String> number = existCustomer.getTelephoneNumbers().stream().map(p -> p.getNumber())
					.collect(Collectors.toList());
			List<String> type = existCustomer.getTelephoneNumbers().stream().map(p -> p.getType())
					.collect(Collectors.toList());
			customers.setEnergyAccounts(String.join(",", existCustomer.getEnergyAccounts()));
			customers = mapper1.map(existCustomer, CustomerDatabase.class);
			customers.setNumber(String.join(",", number));
			customers.setType(String.join(",", type));
			repo.save(customers);
		}
		return;
	}

	public void delete(int id) {
		repo.deleteById(id);
	}
}
