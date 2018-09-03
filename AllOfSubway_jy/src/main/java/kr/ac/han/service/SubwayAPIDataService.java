package kr.ac.han.service;

import java.io.UnsupportedEncodingException;

import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.util.NestedServletException;


public interface SubwayAPIDataService {
	@Retryable(
		      value = {NestedServletException.class, HttpServerErrorException.class }, 
		      maxAttempts = 3)
	public ResponseEntity<String> realAccessTimeData(String station) throws UnsupportedEncodingException,NestedServletException, HttpServerErrorException;
	
	@Recover
    void recover(HttpServerErrorException e, String station);
}
