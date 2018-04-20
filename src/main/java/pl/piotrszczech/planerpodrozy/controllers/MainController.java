package pl.piotrszczech.planerpodrozy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.piotrszczech.planerpodrozy.model.entities.Post;
import pl.piotrszczech.planerpodrozy.model.repositories.PostRepository;

import javax.persistence.Id;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private PostRepository postRepository;

    @GetMapping("/")
    public String getIndexPage(Model model){
        model.addAttribute("name", "Piotrek");
        return "index";
    }

    @GetMapping("/addPost")
    public String addPostPage(){
        return "addPost";
    }

    @PostMapping("/addPost")
    public String addPost(Model model,
                          @RequestParam (value = "country") String countryParam,
                          @RequestParam String city,
                          @RequestParam String dateOfDeparture,
                          @RequestParam String costOfDeparture,
                          @RequestParam String howManyPpl){

        Post post = new Post(countryParam, city, dateOfDeparture, costOfDeparture, howManyPpl);
        postRepository.save(post);
        return "index";
    }

    @GetMapping("/posts")
    public String postsPage(Model model){
        List<Post> postsList = new ArrayList<>();
        Iterable<Post> postIterable = postRepository.findAll();

        for (Post post : postIterable) {
            postsList.add(post);
        }

        model.addAttribute("posts", postsList);
        return "posts";
    }

    @GetMapping("/posts/{country}")
    public String postsByCountry(@PathVariable String country,
                                 Model model){
        List<Post> postsList = new ArrayList<>();
        Iterable<Post> postIterable = postRepository.findAllByCountryContains(country);

        for (Post post : postIterable) {
            postsList.add(post);
        }

        model.addAttribute("posts", postsList);
        return "posts";
    }

    @GetMapping("/posts/{country}/{sortField}/{sortDirection}")
    public String postsByCountry(@PathVariable String country,
                                 @PathVariable String sortField,
                                 @PathVariable String sortDirection,
                                 Model model){

        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equals(sortDirection)){
            direction = Sort.Direction.DESC;
        }
        List<Post> postsList = postRepository.findAllByCountryContains(country, Sort.by(Sort.Direction.fromString(sortDirection), sortField));

        model.addAttribute("posts", postsList);
        return "posts";
    }

    @Autowired
    public MainController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
