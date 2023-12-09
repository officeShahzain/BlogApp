package com.springboot.blogApp.dao;

import com.springboot.blogApp.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments,Integer> {
}
