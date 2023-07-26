package docrob.springblog.dtos;

import docrob.springblog.models.MyList;
import docrob.springblog.models.MyListTodo;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class MyListComponents {
    private MyList list;
    private List<MyListTodo> todos;
}
