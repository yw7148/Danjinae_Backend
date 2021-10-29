package com.example.post.service;

import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void write(Post post){

        postRepository.save(post);

    }

    public Page<Post> totalpostlist(Pageable pageable){

        return postRepository.findAll(pageable);
    }

    public Post getpost(Integer id){

        return postRepository.findById(id).get();
    }

    public void deletepost(Integer id){

        postRepository.deleteById(id);
    }

    public Page<Post> searchkeyword(String keyword,Pageable pageable){

        return postRepository.findByTitleContaining(keyword, pageable);
    }
}
