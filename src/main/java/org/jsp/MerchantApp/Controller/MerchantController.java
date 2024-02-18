package org.jsp.MerchantApp.Controller;

import java.util.List;

import org.jsp.MerchantApp.Dto.Merchant;
import org.jsp.MerchantApp.Dto.ResponseStructure;
import org.jsp.MerchantApp.Service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MerchantController {
	@Autowired
	private MerchantService service;
//  private MerchantRepository repository;
  @PostMapping("/merchant")
  public ResponseStructure<Merchant> SaveMerchant(@RequestBody Merchant merchant) {
	  return service.saveMerchant(merchant);
  }
  @PutMapping("/merchant")
  public ResponseEntity<ResponseStructure<Merchant>> UpdateMerchant(@RequestBody Merchant merchant) {
	 return service.UpdateMerchant(merchant);
  }
  @GetMapping("merchant/{id}")
  public ResponseEntity<ResponseStructure<Merchant>> FindById(@PathVariable(name="id") int id) {
	 
	 return service.FindById(id);
	 
	 
  }
  @DeleteMapping("merchant/{id}")
  public ResponseEntity<ResponseStructure<Merchant>> DeletById(@PathVariable(name="id")int id) {
	  return service.delete(id);
  }
  
  
  
  @GetMapping("merchant/fetchall")
  public ResponseStructure<List<Merchant>> FetchAll(){
	  return service.FindAll();
  }
  @PostMapping("merchant/verify_by_phone")
  public ResponseEntity<ResponseStructure<Merchant>> Verify(@RequestParam long phone,@RequestParam String password) {
	  
	  return service.VerifyMerchant(phone, password);
   }
  @GetMapping("merchant/{name}")
  public List<Merchant> findByName(@PathVariable String name){
	  return service.findByName(name); 
  }
}
