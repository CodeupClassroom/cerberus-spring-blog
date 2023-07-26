package docrob.springblog.repositories;

import docrob.springblog.models.Ad;
import docrob.springblog.models.MyList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyListRepository extends JpaRepository<MyList, Long> {

}
