package ru.dob.library.WebLibrary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.dob.library.WebLibrary.controllers.HelloController;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"create-db-before.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"create-db-after.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class HelloTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HelloController helloController;

    @Test
    @WithUserDetails("user1")
    public void helloTestStaff() throws Exception{
        this.mockMvc.perform(get("/hello"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(content().string(containsString("Сотрудники")));
    }
}
