package com.example.SpringBootJPA.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.SpringBootJPA.common.AppUtils;
import com.example.SpringBootJPA.config.security.JWTUtils;
import com.example.SpringBootJPA.entities.CountriesEntity;
import com.example.SpringBootJPA.entities.UsersEntity;
import com.example.SpringBootJPA.exceptions.RecordNotFoundException;
import com.example.SpringBootJPA.repositories.CountriesRepository;
import com.example.SpringBootJPA.repositories.UsersRepository;
import com.example.SpringBootJPA.service.UsersService;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

	private static Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
	
	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	private CountriesRepository countryRepository;
	
	@Override
	public UsersEntity getUser(String userName) {
		List<UsersEntity> usersList = userRepository.findByUserNameIgnoreCase(userName);
		if(usersList.isEmpty()){
			throw new RecordNotFoundException(userName+" Not Exists");
		}
		return usersList.get(0);
	}

	@Override
	public void saveUser(UsersEntity user, Long loginUser) {
		
		userRepository.saveAndFlush(user);
		if(user.getActiveLink()){
			sendEmail(user);
		}
		
	}

	private void sendEmail(UsersEntity user) {
		
		String activeToken=JWTUtils.createActiveToken(user.getUserId(), user.getUserName());
		String recipientAddress = user.getEmail();
		String confirmationUrl  =  "http://localhost:9090/regitrationConfirm.html?activeToken=" + activeToken;
		String message =" Hi "+user.getFirstName()+" \r\n"
				+ "Please click below link to confirm your registration.... ";
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject("Sample registration link");
		email.setText(message+"\r\n"+confirmationUrl);
		mailSender.send(email);
	}

	@Override
	@Cacheable(cacheNames="countries")
	public List<CountriesEntity> getAllCountries() {
		return countryRepository.findAll(Sort.by("niceName"));
	}
	
	public List<UsersEntity> getAllUsers(){
		return userRepository.findAll(Sort.by("userName"));
	}

	@Override
	public void downloadUsers(List<Map<String, String>> columns, 
			HttpServletResponse response) throws Exception {
		List<UsersEntity> usersList = getAllUsers();
		AppUtils.addExcelToResponse(columns, usersList, "Users", "users", response);
		
	}
	
	@Override
	public UsersEntity getUser(Long userId) {
		Optional<UsersEntity> userOpt = userRepository.findById(userId);
		if(!userOpt.isPresent()){
			throw new RecordNotFoundException(userId+" not exists");
		}
		return userOpt.get();
	}
	
	public Map<String, List<Map<String,Object>>> getUserDropdowns(){
		Map<String, List<Map<String,Object>>> dropDowns = new HashMap<>();
		List<CountriesEntity> countries = getAllCountries();
		List<Map<String,Object>> countryMap = countries.stream().map( country -> {
			Map<String,Object> item = new HashMap<>();
			item.put("label", country.getNiceName());
			item.put("value", country.getCountryId());
			return item;
		}).collect(Collectors.toList());
		dropDowns.put("countries", countryMap);
		return dropDowns;
	}

}
