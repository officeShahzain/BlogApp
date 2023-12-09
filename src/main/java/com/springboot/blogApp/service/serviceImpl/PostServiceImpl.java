package com.springboot.blogApp.service.serviceImpl;

import com.springboot.blogApp.dao.CategoryRepository;
import com.springboot.blogApp.dao.PostRepository;
import com.springboot.blogApp.dao.UserRepository;
import com.springboot.blogApp.entities.Category;
import com.springboot.blogApp.entities.Post;
import com.springboot.blogApp.entities.User;
import com.springboot.blogApp.exception.ResourceNotFound;
import com.springboot.blogApp.payloads.CategoryDto;
import com.springboot.blogApp.payloads.PostDto;
import com.springboot.blogApp.payloads.PostResponse;
import com.springboot.blogApp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private  ModelMapper modelMapper;

    //create category
    @Override
    public PostDto createPost(PostDto postDto,int userId, int categoryId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFound("User","Id : ",userId));
        Category addcategory = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFound("Category", "id: ",categoryId));
        Post createPost = this.modelMapper.map(postDto,Post.class);
        createPost.setImage(postDto.getImage());
        createPost.setContent(postDto.getContent());
        createPost.setAddDate(new Date());
        createPost.setTitle(postDto.getTitle());
        createPost.setUser(user);
        createPost.setCategory(addcategory);
        System.out.println(createPost.getCategory().toString());
        System.out.println("*************************************************");
        System.out.println(createPost.getUser().toString());
        createPost = this.postRepository.save(createPost);
        return this.modelMapper.map(createPost,PostDto.class);
    }

    // update post
    @Override
    public PostDto updatePost(PostDto postDto,int postId) {
        Post findPost = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFound("post","id : ",postId));
        findPost.setTitle(postDto.getTitle());
        findPost.setImage(postDto.getImage());
        findPost.setContent(postDto.getContent());
        Post update = this.postRepository.save(findPost);
        return this.modelMapper.map(update,PostDto.class);
    }

    //delete post
    @Override
    public void deletePost(int postId) {
        Post findPost = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFound("Post is not available in our db", "Post_Id : ",postId));
        this.postRepository.delete(findPost);
    }

    //get all post
    @Override
    public List<PostDto> getPost() {
        List<Post> getAllPost = this.postRepository.findAll();
        List<PostDto> postList = getAllPost.stream().map((post -> this.modelMapper.map(post,PostDto.class))).collect(Collectors.toList());
        return postList;
    }

    //get post by id
    @Override
    public PostDto getPostById(int postId) {
        Post getPost = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFound("Post","Post Id",postId));

        return this.modelMapper.map(getPost, PostDto.class);
    }

    //get post by user id
    @Override
    public List<PostDto> getPostByUserId(int userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFound("This user does not have any post yet","UserID : ",userId));
        List<Post> posts = getPostsByUser(user);
        List<PostDto> getListOfPost = posts.stream().map((poost -> this.modelMapper.map(poost, PostDto.class))).collect(Collectors.toList());

        return getListOfPost;
    }

    private List<Post> getPostsByUser(User user) {
        return this.postRepository.findByUser(user);
    }

    // get post ny categoryId
    @Override
    public List<PostDto> getPostByCategory(int categoryId) {
        Category getPostFromCategory = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFound("Post od that Category is not available ","CategoryId: ",categoryId));
        List<Post> posts = this.postRepository.findByCategory(getPostFromCategory);
        List<PostDto> postDtoList= posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtoList;
    }

    public List<PostDto> getPostByPageWise(int pageNumber, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> pagePost = this.postRepository.findAll(pageable);
        List<Post> pages = pagePost.getContent();
        List<PostDto> pagesDto = pages.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return pagesDto;
    }
    public PostResponse getPostsWithPostResponse(int pageNumber, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> pagePost = this.postRepository.findAll(pageable);
        List<Post> pagees = pagePost.getContent();
        List<PostDto> pages = pagees.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(pages);
        postResponse.setPageNo(pagePost.getNumber());
        postResponse.setTotalPage(pagePost.getTotalPages());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement((int) pagePost.getTotalElements());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;

    }

    public List<PostDto> searchingPost(String title)
    {
        List<Post> posts = this.postRepository.findByTitleContaining(title);
        List<PostDto> postDtoList = posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
      return postDtoList;

    }
    //if findBy title Containing not working
    public List<PostDto> searchPost(String title)
    {
        List<Post> list = this.postRepository.searchPost("%"+title+"%");
        List<PostDto> list1 = list.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return list1;
    }

}
