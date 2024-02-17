package org.jsp.MerchantApp.Service;

import java.util.List;
import java.util.Optional;

import org.jsp.MerchantApp.Dao.MerchantDao;
import org.jsp.MerchantApp.Dto.Merchant;
import org.jsp.MerchantApp.Dto.ResponseStructure;
import org.jsp.MerchantApp.Exception.IdNotFoundException;
import org.jsp.MerchantApp.Exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
 @Autowired
	private MerchantDao merchantDao;
	
	public ResponseStructure<Merchant>saveMerchant(Merchant merchant){
		ResponseStructure<Merchant> structure= new ResponseStructure<>();
		structure.setMessage("Merchant Saved");
		structure.setData(merchantDao.saveMerchant(merchant));
		structure.setStatus_code(HttpStatus.CREATED.value());
		return structure;
	}
	
	public ResponseEntity<ResponseStructure<Merchant>>UpdateMerchant(Merchant merchant){
		ResponseStructure<Merchant> structure= new ResponseStructure<>();
		Optional<Merchant> merch= merchantDao.findById(merchant.getId());
		if(merch.isPresent()) {
			Merchant dbmerch=merch.get();
			dbmerch.setId(merchant.getId());
			dbmerch.setEmail(merchant.getEmail());
			dbmerch.setGstno(merchant.getGstno());
			dbmerch.setName(merchant.getName());
			dbmerch.setPhone(merchant.getPhone());
			dbmerch.setPassword(merchant.getPassword());
			
			structure.setMessage("Merchant Updated");
			structure.setData(merchantDao.saveMerchant(merchant));
			structure.setStatus_code(HttpStatus.ACCEPTED.value());
			
			return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.ACCEPTED);
			
		}
//		structure.setMessage("Merchant Cannotr Updated");
//		structure.setData(null);
//		structure.setStatus_code(HttpStatus.NOT_FOUND.value());
		
		//return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.NOT_FOUND);
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Merchant>> FindById(int id){
		Optional<Merchant> merch= merchantDao.findById(id);
		ResponseStructure<Merchant> structure= new ResponseStructure<>();
		if(merch.isPresent()) {
			structure.setMessage("Merchant Found");
			structure.setData(merch.get());
			structure.setStatus_code(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);
			
		}
//		structure.setMessage("Merchant Not Found");
//		structure.setData(null);
//		structure.setStatus_code(HttpStatus.NOT_FOUND.value());
//		
//		return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.NOT_FOUND);
//		
		throw new IdNotFoundException();
		
	}
	
	public ResponseStructure<List<Merchant>> FindAll(){
		ResponseStructure<List<Merchant>> structure= new ResponseStructure<>();
		structure.setMessage("All Merchant Found");
		structure.setData(merchantDao.findAll());
		structure.setStatus_code(HttpStatus.OK.value());
		return structure;
	}
	
	public ResponseEntity<ResponseStructure<Merchant>> VerifyMerchant(long phone,String password){
		Optional<Merchant> merch= merchantDao.Verify(phone, password);
		ResponseStructure<Merchant> structure= new ResponseStructure<>();
		if(merch.isPresent()) {
			structure.setMessage("Verification Sucessfull");
			structure.setData(merch.get());
			structure.setStatus_code(HttpStatus.OK.value());
			 return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);
		}

		throw new InvalidCredentialsException("Invalid Phone Numver or Password");
		
		
	}
	public List<Merchant> findByName(String name){
		return merchantDao.findByName(name);
	}
	
	public ResponseEntity<ResponseStructure<Merchant>> delete(int id){
		ResponseStructure<Merchant> structure= new ResponseStructure<>();
		
		if(merchantDao.Delet(id)=="success") {
			structure.setMessage("delet Succesfull");
			structure.setStatus_code(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}
}
