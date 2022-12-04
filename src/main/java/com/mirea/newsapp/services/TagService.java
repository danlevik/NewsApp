package com.mirea.newsapp.services;

import com.mirea.newsapp.models.Tag;
import com.mirea.newsapp.repos.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private TagRepo tagRepo;

    @Autowired
    public TagService(TagRepo tagRepo) {
        this.tagRepo = tagRepo;
    }

    public Tag getTagById(int id) {
        return tagRepo.findById(id);
    }

    public String getTagNameById(int id) {
        return tagRepo.findById(id).getName();
    }

    public List<Tag> getAllTags() {
        return tagRepo.findAll();
    }

    public void saveTag(Tag tag) {
        tagRepo.save(tag);
    }

    public void deleteTagById(int id) {
        tagRepo.deleteById(id);
    }
}
