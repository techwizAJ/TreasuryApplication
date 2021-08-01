package com.pankhudi.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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

import com.pankhudi.model.Customers;
import com.pankhudi.model.Invoice;
import com.pankhudi.model.SalesTransactions;
import com.pankhudi.model.Vendors;
import com.pankhudi.service.CustomerServiceImpl;
import com.pankhudi.service.DaoServices;
import com.pankhudi.service.InvoiceServiceImpl;
import com.pankhudi.service.VendorServicesImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping( path = "api/v1" )
public class DashBoardServiceController {

	private final DaoServices<SalesTransactions> salesTransactionsService;
	private final InvoiceServiceImpl invoiceService;
	private final CustomerServiceImpl customerService;
	private final VendorServicesImpl vendorService;

	@Autowired
	public DashBoardServiceController(DaoServices<SalesTransactions> salesTransactionsService , InvoiceServiceImpl invoiceService,
			CustomerServiceImpl customerService, VendorServicesImpl vendorService) {
		this.salesTransactionsService = salesTransactionsService;
		this.invoiceService = invoiceService;
		this.customerService = customerService;
		this.vendorService = vendorService;
	}

	// list url mappings of sales transactions
	@GetMapping("/sales")
	private List<SalesTransactions> getVendors() {
		return salesTransactionsService.getAllobjects();
	}

	@PostMapping(("/sales/createSales/"))
	private ResponseEntity<SalesTransactions> saveVendors( @RequestBody SalesTransactions sale ){
		SalesTransactions sale1 = salesTransactionsService.getPkObjectId(sale.getPkSaleTransaction() );
		if( sale1 != null ) {
			return (ResponseEntity<SalesTransactions>) ResponseEntity.badRequest().body(sale);
		}
		salesTransactionsService.saveObject(sale);
		return ResponseEntity.ok(sale);	
	}

	@SuppressWarnings("unchecked")
	@PutMapping("/sales/{id}")
	private ResponseEntity<SalesTransactions> updateVendors( @PathVariable int id,@RequestBody SalesTransactions sale1 ){
		SalesTransactions sale = salesTransactionsService.getPkObjectId(id);
		if( sale == null || (id != sale1.getPkSaleTransaction()) ) {
			return (ResponseEntity<SalesTransactions>) ResponseEntity.notFound();
		}
		sale.setAmount( sale1.getAmount() );
		sale.setInvoice( sale1.getInvoice() );
		sale.setQuantity(sale1.getQuantity());
		sale.setProduct(sale1.getProduct() );
		salesTransactionsService.saveObject(sale);
		return ResponseEntity.ok(sale);	
	}

	@DeleteMapping("/sales/{id}")
	private ResponseEntity<SalesTransactions> deleteVendors( @PathVariable int id ){
		SalesTransactions sale = salesTransactionsService.getPkObjectId(id);
		if( sale == null ) {
			return (ResponseEntity<SalesTransactions>) ResponseEntity.badRequest().body(sale);
		}
		salesTransactionsService.deleteObjectByPkId(id);
		return ResponseEntity.ok(sale);	
	}

	// Invoice crud operations
	@GetMapping("/invoice")
	private List<Invoice> getInvoice() {
		return invoiceService.getAllobjects();
	}

	@PostMapping(("/invoice/createInvoice/"))
	private ResponseEntity<Invoice> saveInvoice( @RequestBody Invoice invoice ){
		Invoice invoice1 = invoiceService.getPkObjectId(invoice.getPkInvoiceId() );
		if( invoice1 != null ) {
			return (ResponseEntity<Invoice>) ResponseEntity.badRequest().body(invoice);
		}
		invoiceService.saveObject(invoice);
		return ResponseEntity.ok(invoice);	
	}

	@PutMapping("/invoice/{id}")
	private ResponseEntity<Invoice> updateInvoice( @PathVariable int id,@RequestBody Invoice invoice1 ){
		Invoice invoice = invoiceService.getPkObjectId(id);
		if( invoice == null || (id != invoice1.getPkInvoiceId()) ) {
			return (ResponseEntity<Invoice>) ResponseEntity.badRequest().body(invoice1);
		}
		invoice.setCustomer( invoice1.getCustomer() );
		invoice.setCreateDate( invoice1.getCreateDate() );
		invoice.setDiscountAmount(invoice1.getDiscountAmount());
		invoice.setPaymentAmount(invoice1.getPaymentAmount() );
		invoice.setTotalAmount(invoice1.getTotalAmount() );
		invoiceService.saveObject(invoice);
		return ResponseEntity.ok(invoice);	
	}

	@DeleteMapping("/invoice/{id}")
	private ResponseEntity<Invoice> deleteInvoice( @PathVariable int id ){
		Invoice invoice = invoiceService.getPkObjectId(id);
		if( invoice == null ) {
			return (ResponseEntity<Invoice>) ResponseEntity.badRequest().body(invoice);
		}
		salesTransactionsService.deleteObjectByPkId(id);
		return ResponseEntity.ok(invoice);	
	}

	// Get total invoice data based on region
	@GetMapping("/invoice/totalRevenue/region")
	private HashMap<String, Float> getTotalRevenueByRegion() {
		List<String> region = new ArrayList<String>();
		region.add("apac");region.add("europe");region.add("na");region.add("sa");region.add("africa");
		HashMap<String, Float> map = new HashMap<String, Float>();
		for(String reg : region) {
			List<Customers> customerPk = customerService.findPkCustomBasedOnRegion(reg);
			map.put(reg, getTotalValue(customerPk));
		}
		return map;
	}

	private float getTotalValue(List<Customers> customerPk) {

		float total = 0 ; String value;
		for (Customers cus : customerPk) {
			if(cus!= null ) {
				value = invoiceService.getTotalPaymentAmountByCustomerId(cus);
				total += value != null ? Float.parseFloat(value) : 0;
			}
		}

		return total;
	}

	// Get total customers based on region
	@GetMapping("/customers/countOfCustomerBasedOnRegion")
	private HashMap<String , Integer > getCountOfCustomerBasedOnRegion( ){
		HashMap<String , Integer > map = new HashMap<String, Integer>();
		List<String> region = new ArrayList<String>();
		region.add("apac");region.add("europe");region.add("na");region.add("sa");region.add("africa");
		for(String reg : region) {
			List<Customers> customerList = customerService.findPkCustomBasedOnRegion(reg);
			map.put(reg, customerList != null ? customerList.size() : 0);
		}		
		return map;
	}

	// Get total vendors based on region
	@GetMapping("/vendors/countOfVendorBasedOnRegion")
	private HashMap<String , Integer > getCountOfVendorsBasedOnRegion( ){
		HashMap<String , Integer > map = new HashMap<String, Integer>();
		List<String> region = new ArrayList<String>();
		region.add("apac");region.add("europe");region.add("na");region.add("sa");region.add("africa");
		for(String reg : region) {
			List<Vendors> vendorsList = vendorService.findPkCustomBasedOnRegion(reg);
			map.put(reg, vendorsList != null ? vendorsList.size() : 0);
		}		
		return map;
	}
	// Get last X days revenue data
	@GetMapping("/invoice/revenue/{days}")
	private HashMap<String,Float> getLastestRevenueData( @PathVariable int days ){
		HashMap<String , Float > map = new HashMap<String, Float>();
		Calendar calendar = Calendar.getInstance(); // this would default to now
		calendar.add(Calendar.DAY_OF_MONTH, -days);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");			
		try {
			List<Invoice> invoiceList = invoiceService.getInvoiceFromDate( Date.valueOf( sdf.format( calendar.getTime())  )   );
			sdf = new SimpleDateFormat("MM/dd/YY");
			for(Invoice invoice : invoiceList) {
				map.put( sdf.format(invoice.getCreateDate()) , invoice.getPaymentAmount());
			}
		}catch(Exception e ) {
			System.out.println("Error occured " + e);
			return map ;
		}
		return map;	
	}
}