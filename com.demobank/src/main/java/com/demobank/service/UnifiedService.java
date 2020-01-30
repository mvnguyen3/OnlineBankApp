package com.demobank.service;

public interface UnifiedService extends AccountService, 
BranchService, 
CustomerService, 
MailService, 
RoleService, 
TransactionService, 
UserService 
{

}
