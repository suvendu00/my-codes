package com.cts.mailorderpharmacy.drugservice.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cts.mailorderpharmacy.drugservice.dao.DrugLocationRepository;
import com.cts.mailorderpharmacy.drugservice.dao.DrugRepository;
import com.cts.mailorderpharmacy.drugservice.entity.Drug;
import com.cts.mailorderpharmacy.drugservice.entity.DrugLocation;
import com.cts.mailorderpharmacy.drugservice.entity.Stock;
import com.cts.mailorderpharmacy.drugservice.exception.DrugNotFoundException;
import com.cts.mailorderpharmacy.drugservice.exception.StockNotFoundException;
import com.cts.mailorderpharmacy.drugservice.service.Drugservice;

@SpringBootTest
public class DrugServiceTest {

	@Autowired
	Drugservice service;
	
	@MockBean
	DrugRepository drugRepository;
	
	@MockBean
	DrugLocationRepository drugLocationRepository; 
	
		String date = "date";
	List<DrugLocation> list = new ArrayList();
	
	Drug drug = new Drug("PR1","paracetamol","suvendu",date, date, 10, 120, list);
	
	@Test
	void testGetAllDrugs() {
		List<Drug> drugList = List.of(drug);
		when(drugRepository.findAll()).thenReturn(drugList);
		
		assertEquals(1, service.getAllDrugs().size());
	}
	
	@Test
	void testGetAllDrugsFalse() {
		List<Drug> emptyList= new ArrayList();
		when(drugRepository.findAll()).thenReturn(emptyList);
		assertEquals(0, service.getAllDrugs().size());
	}
	
	@Test
	void testGetDrugByIdDrugNotFoundException() throws DrugNotFoundException {
		when(drugRepository.findById("XR2")).thenReturn(Optional.empty());
		assertThrows(DrugNotFoundException.class, ()->service.getDrugById("XR2"));
		
	}
	
	@Test
	void testGetDrugByIdSuccess() {
		when(drugRepository.findById("PR1")).thenReturn(Optional.of(drug));
		Drug temp = service.getDrugById("PR1").get();
		assertEquals(drug, temp);
	}
	
	@Test
	void testGetDrugByNameDrugNotFoundException() {
		when(drugRepository.findByDrugName("Dolo")).thenReturn(Optional.empty());
		assertThrows(DrugNotFoundException.class, ()->service.getDrugByName("Dolo"));
	}
	
	@Test
	void testGetDrugByNameSuccess() {
		when(drugRepository.findByDrugName("paracetamol")).thenReturn(Optional.of(drug));
		Drug temp = service.getDrugByName("paracetamol").get();
		assertEquals(drug, temp);
	}
	
	@Test
	void testGetDrugByIdAndLocationStockNotFoundException() {
		when(drugRepository.findById("SR3")).thenReturn(Optional.of(drug));
		assertThrows(StockNotFoundException.class, ()->service.getDrugByIdAndLocation("SR3", "bangalore"));
	}
	
	@Test
	void getGetDrugByIdAndLocationDrugNotFoundException() {
		when(drugRepository.findById("D5")).thenReturn(null);
		assertThrows(DrugNotFoundException.class,
				() -> service.getDrugByIdAndLocation("D5", "salem"), "getDrugByIdTest");
	}
	
	@Test
	void testGetDrugByIdAndLocationSuccess() {
		DrugLocation location = new DrugLocation("1", "bangalore", 10, null);
		list.add(location);
		when(drugRepository.findById("PR1")).thenReturn(Optional.of(drug));
		Stock expected = service.getDrugByIdAndLocation("PR1", "bangalore");
		Stock actual=new Stock("PR1","paracetamol",date,10);

		String expectedValue = expected.getDrugId()+" "+expected.getDrugName()+" "+expected.getExpiryDate()+" "+expected.getQuantity();

		String actualValue = actual.getDrugId()+" "+actual.getDrugName()+" "+actual.getExpiryDate()+" "+actual.getQuantity();

		assertEquals(expectedValue, actualValue);
		
	}
	
	@Test
	void testUpdateQuantity() throws DrugNotFoundException, StockNotFoundException {
		list.add(new DrugLocation("45", "chennai", 45, new Drug("drug", "drug", "drug", date, date,10, 120, list)));
		String expectedValue = "Refill Done Successfully";
		Drug drugDetails = new Drug("drug", "drug", "drug", date, date,10,120, list);
		when(drugRepository.findByDrugName("drug")).thenReturn(Optional.of(drugDetails));
		Optional<DrugLocation> opt1 = Optional.of(
				new DrugLocation("45", "chennai", 45, new Drug("drug", "drug", "drug", date, date,10, 120, list)));
		when(drugLocationRepository.findById("45")).thenReturn(opt1);
		when(drugLocationRepository.findBySerialId("45")).thenReturn(list);
		when(drugLocationRepository.save(list.get(0))).thenReturn(null);
		
		when(service.updateQuantity("drug", "chennai", 1)).thenReturn(expectedValue);
		
	}
	
	@Test
	void testUpdateQuantityDrugNotFoundException()
			throws DrugNotFoundException {
		
		when(drugRepository.findByDrugName("drug")).thenReturn(null);
		Optional<DrugLocation> opt1 = Optional.of(
				new DrugLocation("45", "chennai", 45, new Drug("PR1", "paracetamol", "drug", date, date,10,120, list)));
		when(drugLocationRepository.findById("45")).thenReturn(opt1);
		assertThrows(DrugNotFoundException.class,
				() -> service.updateQuantity("drug", "chennai", 20), "");

	}
	
	@Test
	void testUpdateQuantityStockNotFoundException()
			throws DrugNotFoundException, StockNotFoundException {

		Drug drugDetails = new Drug("PR1", "paracetamol", "drug", date, date,10, 120, list);
		when(drugRepository.findByDrugName("paracetamol")).thenReturn(Optional.of(drugDetails));
		Optional<DrugLocation> opt = Optional.of(
				new DrugLocation("45", "hyderabad", 45, new Drug("PR1", "paracetamol", "drug", date, date,10,120, list)));
		when(drugLocationRepository.findById("45")).thenReturn(opt);
		assertThrows(StockNotFoundException.class,
				() -> service.updateQuantity("paracetamol", "hyderabad", 20), "");

	}
	
	
	@Test
	void testUpdateQuantityStockNotFoundExceptiontwo()
			throws DrugNotFoundException, StockNotFoundException {
		list.add(new DrugLocation("45", "salem", 4, new Drug("drug", "drug", "drug", date, date,10,120, list)));
		Drug drugDetails = new Drug("drug", "drug", "drug", date, date,10,120, list);
		Optional<Drug> opt = Optional.of(drugDetails);
		when(drugRepository.findByDrugName("drug")).thenReturn(opt);
		Optional<DrugLocation> opt1 = Optional.of(	
				new DrugLocation("45", "salem", 45, new Drug("drug", "drug", "drug", date, date,10,120, list)));
		when(drugLocationRepository.findById("45")).thenReturn(opt1);
		assertThrows(StockNotFoundException.class,
				() -> service.updateQuantity("drug", "salem", 20), "");
	}
	
	
}
