package cn.enn.testoauth2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.enn.testoauth2.annotition.Authorization;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OAuthController {

	@Authorization
	@RequestMapping(value = "/login/", method = RequestMethod.GET)
	public ResponseEntity<String> vehicleColor() {
		log.info("=========>" + "sklhdklashd");
		return new ResponseEntity<String>("----", HttpStatus.OK);
	}
}
