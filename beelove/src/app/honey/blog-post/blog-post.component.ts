import { Component, OnInit } from '@angular/core';
import { BlogContentService } from './provider/blog-content.service';

@Component({
  selector: 'app-blog-post',
  templateUrl: './blog-post.component.html',
  styleUrls: ['./blog-post.component.css']
})
export class BlogPostComponent implements OnInit {

  posts: any;

  constructor(private blogContentService: BlogContentService) { }

  ngOnInit() {
    this.blogContentService.getBlogContent().subscribe((result: any) => {
      this.posts = result;
      console.log(this.posts);
    });
  }

}
