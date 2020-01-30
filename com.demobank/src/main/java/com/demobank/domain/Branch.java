package com.demobank.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity(name = "branch") // Create table named branch
@XmlRootElement
public class Branch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long branchId;
	private String branchCountry;
	private String branchState;
	private String branchCity;
	private String branchZipcode;
	
	@XmlTransient
	@OneToMany(fetch = FetchType.EAGER)
	private List<Account> accounts = new ArrayList<>();
	
	
	
	public Branch(String branchCountry, String branchState, String branchCity, String branchZipcode) {
		super();
		this.branchCountry = branchCountry;
		this.branchState = branchState;
		this.branchCity = branchCity;
		this.branchZipcode = branchZipcode;
	}

	// Constructor
	public Branch() {
		// TODO Auto-generated constructor stub
	}

	public long getBranchId() {
		return branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}
	
	public String getBranchCountry() {
		return branchCountry;
	}

	public void setBranchCountry(String branchCountry) {
		this.branchCountry = branchCountry;
	}

	public String getBranchState() {
		return branchState;
	}

	public void setBranchState(String branchState) {
		this.branchState = branchState;
	}

	public String getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

	public String getBranchZipcode() {
		return branchZipcode;
	}

	public void setBranchZipcode(String branchZipcode) {
		this.branchZipcode = branchZipcode;
	}

	@XmlTransient
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
}
