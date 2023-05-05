package ru.dob.library.WebLibrary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.dob.library.WebLibrary.controllers.AuthController;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"create-db-before.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"create-db-after.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AuthTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AuthController authController;

	@Test
	public void authTest() throws Exception {
		this.mockMvc.perform(get("/auth/login"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Вход")));
	}

    @Test
	public void accessDeniedTest() throws Exception {
		this.mockMvc.perform(get("/books"))
				.andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("http://localhost/auth/login"));

	}

	@Test
	public void correctLoginTest() throws Exception {
		this.mockMvc.perform(formLogin("/process_login").user("user1").password("12345"))
				.andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/hello"));
	}

	@Test
	public void badCredentials() throws Exception {
		this.mockMvc.perform(post("/process_login").param("user","user12345"))
				.andDo(print())
				.andExpect(status().isForbidden());
	}
}
