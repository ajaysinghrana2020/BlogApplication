package com.example.blogapplication.service;

import com.example.blogapplication.model.entities.Post;
import com.example.blogapplication.model.entities.Tag;
import com.example.blogapplication.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagsService {

    @Autowired
    TagsRepository tagsRepository;

    public void saveTags(Tag tag){

        tagsRepository.save(tag);
    }


    public void insertTags(List<Tag> listOfTags) {
        for(Tag tag : listOfTags){
            Optional<String> name = tagsRepository.getName(tag.getName());
            if(name.isEmpty()){
                tagsRepository.save(tag);
            }
        }
    }

//    public List<Tag> filterTag(String query){
//        if(query!=null){
//            return tagsRepository.filterTags(query);
//        }
//        List<Tag> listofTags=tagsRepository.findAll();
//        return listofTags;
//
//    }
    public List<Tag> allTags(){
        List<Tag> listOfTag = tagsRepository.findAll();
        return listOfTag;
    }
}
