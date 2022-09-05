package com.advanced.filter.controllers;

import com.advanced.filter.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("")
    public ResponseEntity<List<User>> getAllArticle(){
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getArticleById(@PathVariable Long id){
        if(true) return ResponseEntity.ok().body(null);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<User> saveArticle(
        //@RequestBody UserDto article
    ){
        return ResponseEntity.created(URI.create("/")).body(null
            //articleService.saveArticle(article)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateArticle(@PathVariable Long id //@RequestBody ArticleDto newArticle
    ){
        if(true) return ResponseEntity.ok().body(null);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id){
        if(true
                //articleService.deleteArticle(id)
        ) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
