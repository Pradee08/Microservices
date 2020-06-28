package com.centrica.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.centrica.model.Account;
import com.centrica.model.AccountDatabase;
import com.centrica.model.Tariff;
import com.centrica.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository repo;

	public void add(Account account) {
		AccountDatabase accounts = new AccountDatabase();
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		accounts = mapper.map(account, AccountDatabase.class);
		accounts.setSupplierName(account.getTariffDetails().getSupplierName());
		accounts.setUnitRate(account.getTariffDetails().getUnitRate());
		accounts.setStandingCharge(account.getTariffDetails().getStandingCharge());
		accounts.setPersonalProjection(account.getTariffDetails().getPersonalProjection());
		accounts.setEstimatedAnnualConsumption(account.getTariffDetails().getEstimatedAnnualConsumption());
		accounts.setCancellationCharge(account.getTariffDetails().getCancellationCharge());
		accounts.setEndDate(account.getTariffDetails().getEndDate());
		accounts.setTariffName(account.getTariffDetails().getTariffName());
		repo.save(accounts);
		return;
	}

	public Account retrieve(String id) {
		Account account = new Account();
		Optional<AccountDatabase> fetchedAccountList = repo.findById(id);
		if (fetchedAccountList.isPresent()) {
			ModelMapper mapper = new ModelMapper();
			Tariff tariffDetails = new Tariff();
			account = mapper.map(fetchedAccountList.get(), Account.class);
			tariffDetails.setUnitRate(fetchedAccountList.get().getUnitRate());
			tariffDetails.setSupplierName(fetchedAccountList.get().getSupplierName());
			tariffDetails.setTariffName(fetchedAccountList.get().getTariffName());
			tariffDetails.setStandingCharge(fetchedAccountList.get().getStandingCharge());
			tariffDetails.setPersonalProjection(fetchedAccountList.get().getPersonalProjection());
			tariffDetails.setEstimatedAnnualConsumption(fetchedAccountList.get().getEstimatedAnnualConsumption());
			tariffDetails.setCancellationCharge(fetchedAccountList.get().getCancellationCharge());
			tariffDetails.setEndDate(fetchedAccountList.get().getEndDate());
			account.setTariffDetails(tariffDetails);
		} else {
			throw new NoSuchElementException("Account with given id does not exist");
		}
		return account;
	}

	public void update(Account account, String id, Account existAccount) {
		ModelMapper mapper = new ModelMapper();
		if (existAccount.getId().equals(id)) {
			existAccount = mapper.map(account, Account.class);
			AccountDatabase accounts = new AccountDatabase();
			ModelMapper mapper1 = new ModelMapper();
			mapper1.getConfiguration().setAmbiguityIgnored(true);
			accounts = mapper1.map(existAccount, AccountDatabase.class);
			accounts.setSupplierName(existAccount.getTariffDetails().getSupplierName());
			accounts.setUnitRate(existAccount.getTariffDetails().getUnitRate());
			accounts.setStandingCharge(existAccount.getTariffDetails().getStandingCharge());
			accounts.setPersonalProjection(existAccount.getTariffDetails().getPersonalProjection());
			accounts.setEstimatedAnnualConsumption(existAccount.getTariffDetails().getEstimatedAnnualConsumption());
			accounts.setCancellationCharge(existAccount.getTariffDetails().getCancellationCharge());
			accounts.setEndDate(existAccount.getTariffDetails().getEndDate());
			accounts.setTariffName(existAccount.getTariffDetails().getTariffName());
			repo.save(accounts);
		}
		return;
	}

	public List<Account> retrieveAccount(int customerId) {
		List<Account> listOfAccounts = new ArrayList<Account>();
		Optional<List<AccountDatabase>> fetchedAccountList = repo.findByCustomerId(customerId);
		if (fetchedAccountList.isPresent()) {
			fetchedAccountList.get().forEach(accounts -> {
				Account account = new Account();
				ModelMapper mapper = new ModelMapper();
				Tariff tariffDetails = new Tariff();
				account = mapper.map(accounts, Account.class);
				tariffDetails.setUnitRate(accounts.getUnitRate());
				tariffDetails.setSupplierName(accounts.getSupplierName());
				tariffDetails.setTariffName(accounts.getTariffName());
				tariffDetails.setStandingCharge(accounts.getStandingCharge());
				tariffDetails.setPersonalProjection(accounts.getPersonalProjection());
				tariffDetails.setEstimatedAnnualConsumption(accounts.getEstimatedAnnualConsumption());
				tariffDetails.setCancellationCharge(accounts.getCancellationCharge());
				tariffDetails.setEndDate(accounts.getEndDate());
				account.setTariffDetails(tariffDetails);
				listOfAccounts.add(account);
			});
		} else {
			throw new NoSuchElementException("Accounts with given customer id does not exist");
		}
		return listOfAccounts;
	}

	public void delete(String id) {
		repo.deleteById(id);
	}
}
