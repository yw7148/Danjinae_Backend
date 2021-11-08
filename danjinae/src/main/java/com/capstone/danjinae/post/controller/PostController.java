package com.capstone.danjinae.post.controller;
import com.capstone.danjinae.post.entity.Post;
import com.capstone.danjinae.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    //게시물 등록
    @PostMapping("/post")
    public Post post(@RequestBody Post post){
        return postService.write(post);
    }

    //게시물 전체 리스트
    @GetMapping("/total-list")
    public Page<Post> totalList(@PageableDefault(page=0,size=10,sort="postId",direction= Sort.Direction.DESC) Pageable pageable,@RequestParam(value="keyword",required = false) String keyword){
        Page<Post> list= null;

        if(keyword==null){
            list= postService.totalPostList(pageable);
        }
        else{
            list= postService.searchKeyword(keyword,pageable);
        }

        int nowPage=list.getPageable().getPageNumber()+1;
        int startPage=Math.max(nowPage-4,1);
        int endPage=Math.min(nowPage+5,list.getTotalPages());

        return list;
    }
    
    //선택된 게시물 불러오기
    @GetMapping("/select/post")
    public Post selectPost(@RequestParam("postId") Integer postId){

        return postService.getPost(postId);
    }

    //선택된 게시물 삭제
    @DeleteMapping("/delete/post")
    public Page<Post> deletePost(@RequestParam("postId") Integer postId, @PageableDefault(page=0,size=10,sort="postId",direction= Sort.Direction.DESC) Pageable pageable){

        postService.deletePost(postId);
        Page<Post> list= postService.totalPostList(pageable);
        return list;
    }
}
