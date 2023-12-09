package com.springboot.blogApp.dao;

import com.springboot.blogApp.entities.Category;
import com.springboot.blogApp.entities.Post;
import com.springboot.blogApp.entities.User;
import com.springboot.blogApp.payloads.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    @Query("select p from Post p where p.title like:key")
    List<Post> searchPost(@Param("key") String title);




    List<Post> findByTitleContaining(String title);
}
