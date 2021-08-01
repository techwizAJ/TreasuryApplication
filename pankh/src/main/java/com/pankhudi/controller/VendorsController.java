package com.pankhudi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pankhudi.model.Vendors;
import com.pankhudi.service.DaoServices;

@CrossOrigin( origins = "*" )
@RestController
@RequestMapping( path = "api/v1" )
public class VendorsController {
	
	private final DaoServices<Vendors> vendorService;

	@Autowired
	public VendorsController(DaoServices<Vendors> vendorService) {
		this.vendorService = vendorService;
	}

	// list url mappings
	@GetMapping("/vendors")
	private List<Vendors> getVendors() {
		return vendorService.getAllobjects();
	}
	
	@PostMapping(("/vendors/createvendors/"))
	private ResponseEntity<Vendors> saveVendors( @RequestBody Vendors vendor ){
		Vendors vendor1 = vendorService.getObjectByName(vendor.getShortName() );
		if( vendor1 != null ) {
			return (ResponseEntity<Vendors>) ResponseEntity.badRequest().body(vendor);
		}
		vendorService.saveObject(vendor);
		return ResponseEntity.ok(vendor);	
	}

	@PutMapping("/vendors/{id}")
	private ResponseEntity<Vendors> updateVendors( @PathVariable int id,@RequestBody Vendors vendor1 ){
		Vendors vendor = vendorService.getPkObjectId(id);
		if( vendor == null || (id != vendor1.getPkVendorId()) ) {
			return (ResponseEntity<Vendors>) ResponseEntity.badRequest().body(vendor1);
		}
		vendor.setShortName(vendor1.getShortName());
		vendor.setMail(vendor1.getMail());
		vendor.setPkVendorId(vendor1.getPkVendorId());
		vendor.setCreateDate(vendor1.getCreateDate() );
		vendor.setContact(vendor1.getContact() );
		vendorService.saveObject(vendor);
		return ResponseEntity.ok(vendor);	
	}

	@DeleteMapping("/vendors/{id}")
	private ResponseEntity<Vendors> deleteVendors( @PathVariable int id ){
		Vendors vendor = vendorService.getPkObjectId(id);
		if( vendor == null ) {
			return (ResponseEntity<Vendors>) ResponseEntity.badRequest().body(vendor);
		}
		vendorService.deleteObjectByPkId(id);
		return ResponseEntity.ok(vendor);	
	}

}