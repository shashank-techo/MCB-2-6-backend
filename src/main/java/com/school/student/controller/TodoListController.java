package com.school.student.controller;

import com.school.student.form.ItemForm;
import com.school.student.form.StudentForm;
import com.school.student.model.Item;
import com.school.student.model.Student;
import com.school.student.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/todo")
public class TodoListController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/listitems")
    public List<Item> studentList(){
        return todoService.getAllItems();
    }

    @PostMapping("/add-item")
    public Object addItem(@RequestBody ItemForm itemForm){
        String status = todoService.addNewItem(itemForm);
        return new ResponseEntity(status, status.equals("Success")?HttpStatus.OK:HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/check-item")
    public Object checkItem(@RequestParam("itemId") String itemId){
        String status = todoService.taskCompleted(itemId);
        return new ResponseEntity(status, status.equals("Success")?HttpStatus.OK:HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/remove-item")
    public Object removeItem(@RequestParam("itemId") String itemId){
        String status = todoService.removeItem(itemId);
        return new ResponseEntity(status, status.equals("Success")?HttpStatus.OK:HttpStatus.BAD_REQUEST);
    }
}
