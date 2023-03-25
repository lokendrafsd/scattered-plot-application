package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import io.swagger.annotations.*;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.mapper.EntriesMapper;
import net.javaguides.springboot.model.EntriesDBO;
import net.javaguides.springboot.model.EntriesDTO;
import net.javaguides.springboot.repository.ScatteredPlotRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class ScatterdPlotController {

	@Autowired
	private ScatteredPlotRepository repository;
	
	@Autowired
	private EntriesMapper entriesMapper;
	
	/**
	 *@Def: Method to Get All Scattered Plot Data
	 *@Returns: List<EntriesDTO>
	 * 
	 **/
	@ApiOperation(value = "Get All Scattered-Plot Entries", notes = "Endpoint-Purpose: To Retreive all Scattered-plot Entries to be displayed on Plot")
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Successfully retrieved"),
	  @ApiResponse(code = 404, message = "Not found - The Scattered Plot Entries were not found")
	})
	@GetMapping("/scattered-plot")
	public ResponseEntity<List<EntriesDTO>> getAllScatteredPlotData(){
		return new ResponseEntity<>(entriesMapper.mapToEntriesDTOList(repository.findAll()), HttpStatus.OK);
	}		
	

	/**
	 *@Def: Method to Created One/Multiple Entries of Scattered Plot
	 *@Returns: List<EntriesDTO> (Created Entries)
	 * 
	 **/
	@ApiOperation(value = "Save Single/Multi Scattered-Plot Entries", notes = "Endpoint-Purpose: To Save Single/Multi Scattered-plot Entries to be displayed on Plot")
	@ApiResponses(value = {
	  @ApiResponse(code = 202, message = "Created Successfully")
	})
	@PostMapping("/scattered-plot")
	public ResponseEntity<List<EntriesDTO>> createScatteredPlotEntry(@RequestBody List<EntriesDTO> entries) {
		return new ResponseEntity<>(entriesMapper.mapToEntriesDTOList(repository.saveAll(entriesMapper.mapToEntriesDBOList(entries))), HttpStatus.CREATED);
	}
	
	
	/**
	 *@Def: Method to Update an Entry of Scattered Plot Data
	 *@Returns: EntriesDTO (Updated Entry)
	 * 
	 **/
	@ApiOperation(value = "Update a Scattered-Plot Entry", notes = "Endpoint-Purpose: To Update a Scattered-plot Entries to be displayed on Plot")
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Updated Successfully"),
	  @ApiResponse(code = 404, message = "Not found - The Scattered Plot Entries were not found")
	})
	@PutMapping("/scattered-plot/{id}")
	public ResponseEntity<EntriesDTO> updateEmployee(@PathVariable Long id, @RequestBody EntriesDTO entry){
		EntriesDBO entryDbo = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Scattered Plot Entry does not exist with id :" + id));
		entryDbo.setId(entry.getId());
		entryDbo.setX(entry.getX());
		entryDbo.setY(entry.getY());
		return ResponseEntity.ok(entriesMapper.mapToEntriesDTO(repository.save(entryDbo)));
	}
	

	/**
	 *@Def: Method to Delete an Entry from Scattered Plot Data
	 *@Returns: Map<String, Boolean> [#True if Successful]
	 * 
	 **/
	@ApiOperation(value = "Delete an Scattered-Plot Entry", notes = "Endpoint-Purpose: To Delete a Scattered-plot Entry")
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Updated Successfully"),
	  @ApiResponse(code = 404, message = "Not found - The Scattered Plot Entries were not found")
	})
	@DeleteMapping("/scattered-plot/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		EntriesDBO employee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Scattered Plot Entry does not exist with id :" + id));
		
		repository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
