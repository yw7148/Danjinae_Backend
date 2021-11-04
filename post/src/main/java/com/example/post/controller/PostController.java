package com.example.post.controller;
import com.example.post.entity.Post;
import com.example.post.service.PostService;
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

    //게시물 작성 폼
    @GetMapping("/addpostform") //localhost:8080/addpostform
    public String AddPostForm(){
        return "addpostform";
    }

    //게시물 등록
    @PutMapping("/addpost")
    public Post addPost(@RequestBody Post post){
        return postService.write(post);
    }

    //게시물 전체 리스트
    @GetMapping("/totalpostlist/{keyword}")
    public Page<Post> totalPostList(Model model, @PageableDefault(page=0,size=10,sort="id",direction= Sort.Direction.DESC) Pageable pageable,@PathVariable String keyword){

        Page<Post> list= null;

        if(keyword==null){
            list= postService.totalpostlist(pageable);
        }
        else{
            list= postService.searchkeyword(keyword,pageable);
        }

        int nowPage=list.getPageable().getPageNumber()+1;
        int startPage=Math.max(nowPage-4,1);
        int endPage=Math.min(nowPage+5,list.getTotalPages());

        model.addAttribute("list",list);
        model.addAttribute("newPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return list;
    }

    //선택된 게시물 불러오기
    @GetMapping("/getpost/{id}")
    public Post getPost(Model model,@PathVariable Integer id){

        model.addAttribute("post",postService.getpost(id));
        return postService.getpost(id);
    }

    //선택한 게시글 삭제
    @GetMapping("/deletepost/{id}")
    public Page<Post> deletePost(@PathVariable Integer id, @PageableDefault(page=0,size=10,sort="id",direction= Sort.Direction.DESC) Pageable pageable){

        postService.deletepost(id);
        Page<Post> list= postService.totalpostlist(pageable);
        return list;
    }
}
