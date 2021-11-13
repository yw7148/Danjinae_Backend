package com.capstone.danjinae.post.controller;
import com.capstone.danjinae.post.DTO.postDTO.PostRequest;
import com.capstone.danjinae.post.DTO.postDTO.PostResponse;
import com.capstone.danjinae.post.entity.Post;
import com.capstone.danjinae.post.service.CommentService;
import com.capstone.danjinae.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    //게시물 등록
    @PostMapping("/add")
    public Boolean post(@RequestBody PostRequest post){
        Post toadd;
        try {
            toadd = Post.builder().title(post.getTitle()).content(post.getContent()).userId(post.getUserId())
                    .aptId(post.getAptId()).build();

            postService.write(toadd);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //게시물 전체 리스트
    @GetMapping("/total-list")
    public Page<PostResponse> totalList(@PageableDefault(page=0,size=10,sort="postId",direction= Sort.Direction.DESC) Pageable pageable,@RequestParam(value="keyword",required = false) String keyword){
        Page<PostResponse> dtoList;
        Page<Post> list= null;

        if(keyword==null){
            list= postService.totalPostList(pageable);
        }
        else{
            list= postService.searchKeyword(keyword,pageable);
        }

        dtoList = list.map(new Function< Post, PostResponse> (){

            @Override
            public PostResponse apply(Post entity) {
                PostResponse dto = new PostResponse();
                dto.setPostId(entity.getPostId());
                dto.setTitle(entity.getTitle());
                dto.setContent(entity.getContent());
                dto.setAptId(entity.getAptId());
                dto.setUserId(entity.getUserId());
                return dto;
            }
        });
        return dtoList;
    }


    //선택된 게시물 불러오기
    @GetMapping("/select")
    public PostResponse selectPost(@RequestParam("postId") Integer postId){

        Post post = postService.getPost(postId);
        PostResponse dto = new PostResponse();

        dto.setPostId(post.getPostId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setAptId(post.getAptId());
        dto.setUserId(post.getUserId());
        return dto;
    }


    //선택된 게시물 삭제
    @DeleteMapping("/delete")
    public Page<PostResponse> deletePost(@RequestParam("postId") Integer postId, @PageableDefault(page=0,size=10,sort="postId",direction= Sort.Direction.DESC) Pageable pageable) {

        postService.deletePost(postId);
        Page<Post> list = postService.totalPostList(pageable);
        Page<PostResponse> dtoList;

        dtoList = list.map(new Function<Post, PostResponse>() {
            @Override
            public PostResponse apply(Post entity) {
                PostResponse dto = new PostResponse();
                dto.setPostId(entity.getPostId());
                dto.setTitle(entity.getTitle());
                dto.setContent(entity.getContent());
                dto.setAptId(entity.getAptId());
                dto.setUserId(entity.getUserId());
                return dto;
            }
        });
        return dtoList;
    }

}

