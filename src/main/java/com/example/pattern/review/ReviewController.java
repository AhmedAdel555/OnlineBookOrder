package com.example.pattern.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add-review")
    public String addReview(@RequestParam Long bookId, @RequestParam String reviewContent){
        if(bookId == null || reviewContent.isBlank()){
            return "redirect:/";
        }
        reviewService.addReview(1L, bookId, reviewContent);
        return "redirect:/books/"+bookId;
    }
}
