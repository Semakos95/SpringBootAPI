package gr.cinema.api.jsp.service;

import gr.cinema.api.jsp.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;

    static {
        todos.add(new Todo(1,"giwrgos","bought chocolate",new Date(), false ));
        todos.add(new Todo(2, "giwrgos","bought iceCream", new Date(), false));
        todos.add(new Todo(3,"giwrgos", "bought cocaCola",new Date(),false));
    }

    public List<Todo> retrieveTodos(String user) {
        List<Todo> filteredTodos = new ArrayList<Todo>();

        for (Todo todo : todos){
            if (todo.getUser().equals(user)){
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }
}
