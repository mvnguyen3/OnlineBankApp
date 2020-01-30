package com.demobank.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.demobank.domain.Account;
import com.demobank.domain.Branch;
import com.demobank.domain.Customer;
import com.demobank.domain.Transaction;
import com.demobank.domain.User;
import com.demobank.repository.AccountRepository;
import com.demobank.repository.BranchRepository;
import com.demobank.repository.CustomerRepository;
import com.demobank.repository.RoleRepository;
import com.demobank.repository.TransactionRepository;
import com.demobank.repository.UserRepository;

@Service
@Transactional
public class UnifiedServiceImpl implements UnifiedService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	BranchRepository branchRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public Account saveAccount(Account account) {
		// TODO Auto-generated method stub
		return accountRepository.save(account);
	}

	@Override
	public boolean accountExist(long id) {
		// TODO Auto-generated method stub
		return accountRepository.existsById(id);
	}

	@Override
	public List<Account> findAllAccount() {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

	@Override
	public void deleteAccountById(long id) {

		accountRepository.deleteById(id);

	}

	@Override
	public void saveLinkAccountCustomer(long customerId, long accountId) {
		accountRepository.saveAccountCustomer(customerId, accountId);

	}

	@Override
	public void deleteAccountByCusId(long customerId) {
		accountRepository.deleteAccountByCusId(customerId);

	}

	@Override
	public List<Account> findAllAccountByCusId(long customerLinkedId) {

		return accountRepository.findAllByCusId(customerLinkedId);
	}

	// Branch

	@Override
	public Branch saveBranch(Branch branch) {
		return branchRepository.save(branch);
	}

	@Override
	public boolean branchExist(Branch branch) {

		return branchRepository.existsById(branch.getBranchId());
	}

	@Override
	public boolean branchExistById(long branchId) {

		return branchRepository.existsById(branchId);
	}

	@Override
	public void deleteBranchById(long branchId) {

		branchRepository.deleteById(branchId);
	}

	@Override
	public List<Branch> findAllBranch() {
		return branchRepository.findAll();
	}

	@Override
	public Branch findBranchById(long id) {

		return branchRepository.findById(id).get();
	}

	// Customer

	@Override
	public List<Customer> findAllCustomer() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.save(customer);
	}

	@Override
	public void deleteCustomerById(long id) throws InterruptedException {
		// TODO Auto-generated method stub
		customerRepository.deleteById(id);
		System.out.println("Deleted Customer Id: " + id);
		Thread.sleep(500);
		customerRepository.deleteUserLink(id);
		System.out.println("Deleted customer_user Link!!!");
		if (findAllCustomer().isEmpty()) {
			customerRepository.truncate();
			System.out.println("Customer schema is empty");
		}

	}

	@Override
	public Customer findCustomerById(long id) {
		// TODO Auto-generated method stub
		try {
			return customerRepository.findById(id).get(); // User might or might not exist)
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public void saveLinkCustomerUser(long customerId, long userId) {
		customerRepository.saveCustomerUser(customerId, userId);

	}

	@Override
	public Customer findCustomerByEmail(String email) {

		return customerRepository.findByEmail(email);
	}

	@Override
	public Customer findCustomerByName(String name) {
		System.out.println("Name: " + customerRepository.findByName(name));
		return customerRepository.findByName(name);
	}

	// Mail

	public void sendMail(String emailTo, String from, String description) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(emailTo);
		message.setFrom(from);
		message.setSubject("Customer Feedback");
		message.setText(description);

		mailSender.send(message);

		System.out.println("Sucessfully send message");

	}

	// Role

	@Override
	public void saveLinkUserRole(long userId, long roleId) {
		roleRepository.saveUserRole(userId, roleId);
	}

	@Override
	public long getCustomerId(long userId) {

		try {
			return roleRepository.getCustomerId(userId);
		} catch (Exception e) {
			System.out.println("NO ID FOUND");
			return 0;
		}

	}

	@Override
	public void saveRole(long roleId, String roleName) {
		roleRepository.saveRole(roleId, roleName);

	}

	@Override
	public List<Long> findRole() {
		try {
			return roleRepository.findRole();
		} catch (NullPointerException e) {
			return null;
		}
	}

	// Transaction

	@Override
	public void saveTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
	}

	@Override
	public List<Transaction> findAllTransactions() {

		return transactionRepository.findAll();
	}

	@Override
	public void deleteTransactionByIdfix(long toAccountNumber) {
		transactionRepository.deleteByIdfix(toAccountNumber);

	}

	@Override
	public List<Transaction> findTransactionByFromAccNumber(long accountId) {
		return transactionRepository.findByFromAccNumber(accountId);
	}

	// User
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findAllUsers() {

		return userRepository.findAll();
	}

	@Override
	public void deleteUserById(long userId) {
		System.out.println("Deleted User Id: " + userId);
		userRepository.deleteById(userId);
	}

	@Override
	public void updateUser() {

	}

	public User findUserById(long userId) {
		return userRepository.findByUserId(userId);
	}

	@Override
	public long getUserMaxId() {

		return userRepository.getMaxId() + 1L;
	}

	@Override
	public User findUserByEmail(String email) {
		try {
			return userRepository.findUserByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public User findByUserName(String username) {

		// System.out.println("User: ***" + userRepository.findByUsername(username));
		return userRepository.findByUsername(username);
	}

}
