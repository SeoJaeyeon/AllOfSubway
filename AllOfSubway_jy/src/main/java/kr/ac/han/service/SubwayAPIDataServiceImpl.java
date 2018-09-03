package kr.ac.han.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.NestedServletException;

@Service
public class SubwayAPIDataServiceImpl implements SubwayAPIDataService {
	Logger logger=LoggerFactory.getLogger(SubwayAPIDataServiceImpl.class);
	int retry=0;
	
	@Value("${subway.key}")
	private String appKey;
	
	@Retryable({NestedServletException.class, HttpServerErrorException.class})
	@Override
	public ResponseEntity<String> realAccessTimeData(String station) throws UnsupportedEncodingException, NestedServletException, HttpServerErrorException  {
		 logger.info(Integer.toString(retry++));
		String encodeStation=URLEncoder.encode(station,"UTF-8");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", new HttpHeaders()); 
		RestTemplate restTemplate = new RestTemplate(); 
		URI url=URI.create("http://swopenapi.seoul.go.kr/api/subway/"+appKey+"/json/realtimeStationArrival/0/5/"+encodeStation); 
		ResponseEntity<String> response= restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		
		return response;
	}


	@Override
	public void recover(HttpServerErrorException e, String station) {
		logger.info(e.toString());
	}
	

}
