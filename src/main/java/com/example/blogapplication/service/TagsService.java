package com.example.blogapplication.service;

import com.example.blogapplication.model.entities.Tag;
import com.example.blogapplication.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagsService {

    @Autowired
    TagsRepository tagsRepository;

    public void saveTags(Tag tag){
        tagsRepository.save(tag);
    }

    public Tag getTags(Integer id) throws UserNotFoundException {
        Optional<Tag> result = tagsRepository.findById((id));
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Cound not found that id" + id);
    }
}
