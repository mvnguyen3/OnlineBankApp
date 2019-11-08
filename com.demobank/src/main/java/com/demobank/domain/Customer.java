package com.demobank.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "customer") // Create table named customer
public class Customer {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment by SQL
	private long customerId;

	private String customerName;
	private String customerEmail;
	private String customerPhone;
	private String customerGender;
	private String customerSsn;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private String customerDob;

	@Embedded // Depend on customer, does not require primary key
	private Address address;

	// 1 customer can have many accounts
	@OneToMany
	private Set<Account> customerAccounts = new HashSet<>();

	@OneToOne
	private User user;

	public Customer() {
		// TODO Auto-generated constructor stub
	}


	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerGender() {
		return customerGender;
	}

	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}

	public String getCustomerSsn() {
		return customerSsn;
	}

	public void setCustomerSsn(String customerSsn) {
		this.customerSsn = customerSsn;
	}

	public String getCustomerDob() {
		return customerDob;
	}

	public void setCustomerDob(String customerDob) {
		this.customerDob = customerDob;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Account> getCustomerAccounts() {
		return customerAccounts;
	}

	public void setCustomerAccounts(Set<Account> customerAccounts) {
		this.customerAccounts = customerAccounts;
	}

	public User getUsers() {
		return user;
	}

	public void setUsers(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", customerPhone=" + customerPhone + ", customerGender=" + customerGender
				+ ", customerSsn=" + customerSsn + ", customerDob=" + customerDob + ", address=" + address
				+ ", customerAccounts=" + customerAccounts + ", users=" + user + "]";
	}

}
