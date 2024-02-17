package org.jsp.MerchantApp.Dao;

import java.util.List;
import java.util.Optional;

import org.jsp.MerchantApp.Dto.Merchant;
import org.jsp.MerchantApp.Repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
	@Autowired
     private MerchantRepository repository;
	
	public Merchant saveMerchant(Merchant merchant) {
		return  repository.save(merchant);
	}
	
	public Optional<Merchant> findById(int id){
		return repository.findById(id);
	}
	
	public List<Merchant> findAll(){
		return repository.findAll();
	}
        
	public Optional<Merchant> Verify(long phone ,String password) {
		Optional<Merchant> merch=repository.verify(phone, password);
		if(merch.isPresent()) {
			return merch;
		}
		return null;
	}
	
	public List<Merchant> findByName(String name){
		return repository.finByName(name);
		
	}
	
	public String Delet(int id) {
		Optional<Merchant> merch = repository.findById(id);
		if(merch.isPresent()) {
			repository.delete(merch.get());
			
			return "success";
		}
		return "error";
	}
}
