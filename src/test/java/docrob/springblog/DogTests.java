package docrob.springblog;


import docrob.springblog.repositories.DogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DogTests {

    @Autowired
    private DogRepository dogDao;

    @Test
    public void fetchDogs() {
    }

    @Test
    public void deleteDog() {
    }

    @Test
    public void getLolaADog() {
    }

    @Test
    public void giveThatDogSomeToys() {
    }

}
