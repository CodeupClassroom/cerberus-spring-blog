package docrob.springblog.repositories;

import docrob.springblog.models.DogOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogOwnerRepository extends JpaRepository<DogOwner, Long> {
}
