package gr.cinema.api.jsp.controller;

import gr.cinema.api.jsp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TodoController {

    private TodoService todoService;
    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService=todoService;
    }

    @RequestMapping(value = "/list-todos",method = RequestMethod.GET)
    public String showTodos(ModelMap model){
        String username = (String) model.get("username");
        model.put("todos", todoService.retrieveTodos(username));
        return "list-todos";
    }

}
