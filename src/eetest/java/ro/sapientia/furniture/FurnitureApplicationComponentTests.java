package ro.sapientia.furniture;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.transaction.annotation.Transactional;

import ro.sapientia.furniture.model.EnchantingTable;
import ro.sapientia.furniture.model.FurnitureBody;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@TestPropertySource(locations = "classpath:eetest.properties")
class FurnitureApplicationComponentTests {


    @Autowired
    private MockMvc mvc;
  
    @Autowired
    private TestEntityManager entityManager;
    
    private void addOneElement() {
    	FurnitureBody body = new FurnitureBody();
    	body.setHeigth(10);
    	entityManager.persist(body);
    	entityManager.flush();
    }

	private void addEnchantingTable() {
		EnchantingTable enchantingTable = new EnchantingTable();
		enchantingTable.setMagicLevel(10);
		enchantingTable.setMaterial("wood");
		enchantingTable.setOwner("Steve");
		entityManager.persist(enchantingTable);
		entityManager.flush();
	}

	private void addTwoEnchantingTables(){
		EnchantingTable enchantingTable = new EnchantingTable();
		enchantingTable.setMagicLevel(10);
		enchantingTable.setMaterial("wood");
		enchantingTable.setOwner("Steve");
		entityManager.persist(enchantingTable);
		entityManager.flush();

		EnchantingTable enchantingTable2 = new EnchantingTable();
		enchantingTable2.setMagicLevel(20);
		enchantingTable2.setMaterial("stone");
		enchantingTable2.setOwner("Alex");
		entityManager.persist(enchantingTable2);
		entityManager.flush();
	}
    
	@Test
	void furnitureAll_oneElement() throws Exception{
		addOneElement();
		mvc.perform(get("/furniture/all")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].heigth", is(10)));
	}

	@Test
	void enchantingTableAll_oneElement() throws Exception{
		addEnchantingTable();
		mvc.perform(get("/enchantingTable/all")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].material", is("wood")));
	}

	@Test
	void enchantingTableAll_twoElements() throws Exception{
		addTwoEnchantingTables();
		mvc.perform(get("/enchantingTable/all")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].material", is("wood")))
			      .andExpect(jsonPath("$[1].material", is("stone")));
	}

	@Test
	void enchantingTableAll_noElements() throws Exception{
		mvc.perform(get("/enchantingTable/all")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(content().json("[]"));
	}
}
