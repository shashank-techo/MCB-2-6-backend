package com.school.student.service;

import com.school.student.form.ItemForm;
import com.school.student.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TodoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Item> getAllItems(){
        List<Item> itemList = mongoTemplate.find(Query.query(Criteria.where("isDeleted").is(false)),Item.class);
        itemList.stream().forEach(item -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat output = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");

            String formattedTime = output.format(item.getCreatedOn());
            item.setCreatedOnStr(formattedTime);
        });

        return itemList;
    }

    public String addNewItem(ItemForm itemForm){
        Item item = new Item();
        item.setId(UUID.randomUUID().toString());
        item.setCreatedOn(new Date());
        item.setTitle(itemForm.getTitle());
        item.setDescription(itemForm.getDescription());
        item.setDeleted(Boolean.FALSE);
        mongoTemplate.save(item, "Item");
        return "Success";
    }

    public String taskCompleted (String itemId){
        Item item  = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(itemId)), Item.class);
        if(item==null){
            return "Invalid itemId.";
        }
        Update update = new Update();
        update.set("completed", Boolean.TRUE);
        update.set("completedOn", new Date());
        mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(itemId)),update,"Item");
        return "Success";
    }

    public String removeItem(String itemId){
        Item item  = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(itemId)), Item.class);
        if(item==null){
            return "Invalid itemId.";
        }
        mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(itemId)),Update.update("isDeleted",Boolean.TRUE),"Item");
        return "Success";
    }
}
