package ayi.bookstore.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ayi.bookstore.entity.Publishing;
import ayi.bookstore.model.Adress;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PublishingRepositoryTest {

    @Autowired
    private PublishingRepository publishingRepository;

    private Adress adressTest = new Adress("Test Street", 123, 1234);
    private Publishing publishingTest = new Publishing("Test", adressTest);

    @Test
    public void savePublishing_withCorrectInfo_ShouldSucceed() {
        assertDoesNotThrow(()->publishingRepository.save(publishingTest));
    }

    @Test
    public void findByName_WithValidName_ShouldSucceed() {
        publishingTest = publishingRepository.findByPublishingName("ABC MEDIA CORP.");
        
        assertEquals("Sant Louis", publishingTest.getAdress().getStreet());
        assertEquals(234, publishingTest.getAdress().getNumber());
        assertEquals(3456, publishingTest.getAdress().getZipCode());
    }
    
}
