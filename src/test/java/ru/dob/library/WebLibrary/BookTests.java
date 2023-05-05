package ru.dob.library.WebLibrary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.dob.library.WebLibrary.controllers.BookController;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("user2")
@TestPropertySource("/application-test.properties")
@Sql(value = {"create-db-before.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"create-db-after.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class BookTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookController bookController;

    @Test
    public void getTest() throws Exception{
        this.mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("books/index"))
                .andExpect(model().attributeExists("books"));
    }

    @Test
    public void postTest() throws Exception{
        this.mockMvc
                .perform(post("/books")
                        .param("name", "Горе от ума")
                        .param("year", "1825")
                        .param("author", "Грибоедов Александр Сергеевич")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/books"))
                .andDo(print());
    }
}
