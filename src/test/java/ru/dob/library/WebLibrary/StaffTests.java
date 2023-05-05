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
import ru.dob.library.WebLibrary.controllers.StaffController;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("user1")
@TestPropertySource("/application-test.properties")
@Sql(value = {"create-db-before.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"create-db-after.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class StaffTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StaffController staffController;

    @Test
    public void getTest() throws Exception{
        this.mockMvc.perform(get("/staff"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("staff/index"))
                .andExpect(model().attributeExists("staffs"));
    }

    @Test
    @WithUserDetails("user1")
    public void registrationTestStaff() throws Exception{
        this.mockMvc.perform(post("/staff/registration")
                .param("username", "staff1")
                .param("password", "1825")
                .param("full_name", "Денисов Александр Сергеевич")
                .param("role", "ROLE_ADMIN")
                .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
