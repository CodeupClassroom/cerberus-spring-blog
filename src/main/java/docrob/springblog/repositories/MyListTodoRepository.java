package docrob.springblog.repositories;

import docrob.springblog.models.MyList;
import docrob.springblog.models.MyListTodo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyListTodoRepository extends JpaRepository<MyListTodo, Long> {
    public List<MyListTodo> findMyListTodoByList(MyList list);
}
