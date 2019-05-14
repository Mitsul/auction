/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the implementation class of the AddressService class
 */

package com.cygnet.Auction.serviceImpl;

import javax.persistence.OptimisticLockException;
import javax.validation.Valid;

import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.cygnet.Auction.dto.AddressDto;
import com.cygnet.Auction.model.Address;
import com.cygnet.Auction.model.Employee;
import com.cygnet.Auction.repository.AddressRepository;
import com.cygnet.Auction.repository.EmployeeRepository;
import com.cygnet.Auction.responseDto.ResponseAddressDto;
import com.cygnet.Auction.service.AddressService;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class AddressServiceImpl implements AddressService{
	
	static Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

	@Autowired private AddressRepository addressRepository;
	@Autowired private EmployeeRepository employeeRepository;
	@Autowired private UuidAndTimeStamp uuidAndTimeStamp;

	/**
	 * <b> Add address : </b> This function is for adding the address of the employees
	 * @param address This is the parameter for the addAddress function
	 * @return String This is the return of the function
	 * @exception OptimisticLockException,StaleObjectStateException,HibernateOptimisticLockingFailureException, e This are the exceptions for the function
	 * @see OptimisticLockException
	 * @see StaleObjectStateException
	 * @see HibernateOptimisticLockingFailureException
	 * @see e
	 */
	public String addAddress(AddressDto address) {
		logger.info("With in add Address service");
		Employee emp = employeeRepository.findById(address.getEmpId()).get();
		try {
			address.setAddressId(uuidAndTimeStamp.getUuid());
			if (emp!= null) {
				@Valid Address a1 = new Address(address.getAddressId(),emp,address.getCity(),address.getState(),address.getContactNo());
				addressRepository.save(a1);		
				return "Address submitted successfully.";
			}
			else
				return "Something went, please try after again.";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in addAddress for emp " + emp.getName() + " id is " + emp.getEmpId() + " error :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in addAddress for emp " + emp.getName() + " id is " + emp.getEmpId() + " error :- " + e);
			return "Please try again, due to exception :- " + e	;
		}
	}

	/**
	 * <b> Update address : </b> This function is for updating the address of the employees
	 * @param address This is the parameter for the updateAddress function
	 * @return String This is the return of the function
	 * @exception OptimisticLockException,StaleObjectStateException,HibernateOptimisticLockingFailureException, e This are the exceptions for the function
	 * @see OptimisticLockException
	 * @see StaleObjectStateException
	 * @see HibernateOptimisticLockingFailureException
	 * @see e
	 */
	public String updateAddress(AddressDto address) {
		logger.info("With in update address");
		Employee emp = employeeRepository.findById(address.getEmpId()).get();
		try {
			addressRepository.updateAddress(emp,address.getCity(),address.getState(),address.getContactNo());
			return "Information updated successfully.";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in updateAddress for emp " + emp.getName() + " id is " + emp.getEmpId() + " error :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in updateAddress for emp " + emp.getName() + " id is " + emp.getEmpId() + " error :- " + e);
			return "Please try again, due to exception :-\" + e";
		}
	}

	/**
	 * <b> Get address : </b> This function is for getting the address of the employees
	 * @param address This is the parameter for the addAddress function
	 * @return ResponseAddressDto This is the return of the function
	 * @exception e This are the exceptions for the function
	 * @see e
	 */
	public ResponseAddressDto getAddress(String empId) {
		logger.info("With in get ddress");
		try {
			return addressRepository.findDetailByEmployee(empId);
		}catch (Exception e) {
			logger.error("Error with in get address :- " + e);
			return null;
		}
	}
}
