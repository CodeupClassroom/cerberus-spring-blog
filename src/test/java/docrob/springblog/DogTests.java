package docrob.springblog;


import docrob.springblog.models.Dog;
import docrob.springblog.models.DogOwner;
import docrob.springblog.models.Toy;
import docrob.springblog.repositories.DogOwnerRepository;
import docrob.springblog.repositories.DogRepository;
import docrob.springblog.repositories.ToyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DogTests {

//    @Autowired
//    private DogRepository dogDao;
//
//    @Autowired
//    private DogOwnerRepository dogOwnerDao;
//
//    @Autowired
//    private ToyRepository toyDao;
//
//    @Test
//    public void fetchDogs() {
//        List<Dog> dogs = dogDao.findAll();
//        System.out.println(dogs);
//    }
//
//    @Test
//    public void fetchLolasDog() {
//        Dog dog = dogDao.findById(8L).get();
//        System.out.println(dog);
//    }
//
//    @Test
//    public void fetchAndDeleteThug() {
//        Dog dog = dogDao.findById(3L).get();
//        System.out.println(dog);
//
//        dogDao.delete(dog);
//    }
//
//    @Test
//    public void fetchLeatherBear() {
//        Toy toy = toyDao.findById(3L).get();
//        System.out.println(toy.getName() + " is loved by " + toy.getDogs());
//
//    }
//
//    @Test
//    public void deleteDog() {
//    }
//
//    @Test
//    public void getLolaADog() {
//        // get Lola's pet owner object from the dao
//        DogOwner lola = dogOwnerDao.findById(1L).get();
//
//        Dog dog = new Dog();
//        dog.setName("Avocado");
//        dog.setAge(1);
//        dog.setOwner(lola);
//
//        dogDao.save(dog);
//    }
//
//    @Test
//    public void giveThatDogSomeToys() {
//    }

}
