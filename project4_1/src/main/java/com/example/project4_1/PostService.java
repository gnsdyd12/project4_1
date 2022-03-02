package com.example.project4_1;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepo postRepo;

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public void save(Post post){
        postRepo.save(post);
    }

    public List<PostDto.PostListDto> findByAll() {return postRepo.findAllByIdIsNotNull();}

    public Optional<PostDto.PostDetailDto> findById(Long id) {
        Optional<Post> post = postRepo.findById(id);
        if(post.isEmpty()){
            throw new RuntimeException("아이디가 없습니다");
        }else{
            return Optional.of(new PostDto.PostDetailDto(post.get()));
        }
    }

    public void view_Count(Long id){
        Optional<Post> post = postRepo.findById(id);
        post.ifPresent(m->{
            m.setViews(m.getViews()+1L);
            postRepo.save(m);
        });
    }
}
