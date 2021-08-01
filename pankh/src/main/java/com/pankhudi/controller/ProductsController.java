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

import com.pankhudi.model.Products;
import com.pankhudi.service.DaoServices;

@CrossOrigin( origins = "*" )
@RestController
@RequestMapping( path = "api/v1" )
public class ProductsController {

	
	private final DaoServices<Products> productService;

	@Autowired
	public ProductsController(DaoServices<Products> productService) {
		this.productService = productService;
	}

	// list url mappings
	@GetMapping("/products")
	private List<Products> getProduct() {
		return productService.getAllobjects();
	}

	@PostMapping(("/products/createProducts/"))
	private ResponseEntity<Products> saveProducts( @RequestBody Products product1 ){
		Products product = productService.getObjectByName(product1.getShortName() );
		if( product != null ) {
			return (ResponseEntity<Products>) ResponseEntity.badRequest().body(product1);
		}
		productService.saveObject(product1);
		return ResponseEntity.ok(product1);	
	}

	@PutMapping("/products/{id}")
	private ResponseEntity<Products> updateProducts( @PathVariable int id,@RequestBody Products product ){
		Products product1 = productService.getPkObjectId(id);
		if( product1 == null || (id != product.getPkProductId()) ) {
			return (ResponseEntity<Products>) ResponseEntity.badRequest().body(product);
		}
		product1.setShortName(product.getShortName());
		product1.setTrueAmount(product.getTrueAmount());
		product1.setPkProductId(product.getPkProductId());
		product1.setImagePath(product.getImagePath() );
		productService.saveObject(product1);
		return ResponseEntity.ok(product1);	
	}

	@DeleteMapping("/products/{id}")
	private ResponseEntity<Products> deleteProducts( @PathVariable int id ){
		Products product1 = productService.getPkObjectId(id);
		if( product1 == null ) {
			return (ResponseEntity<Products>) ResponseEntity.badRequest().body(product1);
		}
		productService.deleteObjectByPkId(id);
		return ResponseEntity.ok(product1);	
	}

}
