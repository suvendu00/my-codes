package com.cts.mailorderpharmacy.drugservice.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.mailorderpharmacy.drugservice.entity.Drug;

/**
 * 
 * @author Suvendu Gorain
 * date created - 26-july-2022
 *  this a repository class to fetch data from Drug table
 */

@Repository
public interface DrugRepository extends JpaRepository<Drug, String>{
	
	
	Optional<Drug> findByDrugName(String name);

}
