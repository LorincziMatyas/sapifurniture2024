package ro.sapientia.furniture.bdt.ee.definition;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import ro.sapientia.furniture.model.EnchantingTable;


@CucumberContextConfiguration
@SpringBootTest
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@AutoConfigureTestEntityManager
@TestPropertySource(locations = "classpath:eetest.properties")
@ContextConfiguration
public class EnchantingTableStepDefinition {
    
    @Autowired
    private TestEntityManager entityManager;

    // Given that we have the following enchanting tables:
    @Given("^that we have the following enchanting tables:$")
    public void that_we_have_the_following_enchanting_tables(final DataTable enchantingTables) throws Throwable {
        for (final Map<String, String> data : enchantingTables.asMaps(String.class, String.class)) {
            EnchantingTable enchantingTable = new EnchantingTable();
            enchantingTable.setMagicLevel(Integer.parseInt(data.get("magicLevel")));
            enchantingTable.setMaterial(data.get("material"));
            enchantingTable.setOwner(data.get("owner"));
            entityManager.persist(enchantingTable);
        }
        entityManager.flush();
    }
    
    // When I invoke the enchanting table all endpoint
    @When("^I invoke the enchanting table all endpoint$")
    public void I_invoke_the_enchanting_table_all_endpoint() throws Throwable {
    }

    // Then I should get the material "wood" for the position "0"
    @Then("^I should get the material \"([^\"]*)\" for the position \"([^\"]*)\"$")
    public void I_should_get_the_material_for_the_position(final String material, final String position) throws Throwable {
        WebClient webClient = WebClient.create();
        webClient.get().uri("http://localhost:8080/enchantingTable/all")
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(response -> response.toEntityList(EnchantingTable.class))
                .flatMapIterable(entity -> entity.getBody())
                .elementAt(0)
                .doOnNext( et -> {
                    assert et != null;
                    assert et.getMaterial().equals("wood");
                });
    }

    @After
    public void cleanUp() {
    }
}
