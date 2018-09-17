package kr.ac.han;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.nio.charset.Charset;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import kr.ac.han.pf.RequestMessageVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class PfTest {

	Logger logger=LoggerFactory.getLogger(SubwayTest.class);
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mock;
	
	@Before
	public void beforeTest(){
		logger.info("/pf test before");
		mock=MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testSample() throws Exception{
		logger.info("/message 호출");
		RequestMessageVO rv=new RequestMessageVO();
		rv.setType("text");
		rv.setContent("홍대인데 언제?");
		
		  ObjectMapper mapper = new ObjectMapper();
		  mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		    String requestJson=ow.writeValueAsString(rv);
		this.mock.perform(post("/message").contentType(APPLICATION_JSON_UTF8)
			        .content(requestJson))
					.andDo(print())
			        .andExpect(status().isOk());
    
	}
	
	@After
	public void afterTest(){
		logger.info("/message test after");
	}
}
