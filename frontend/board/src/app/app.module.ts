import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
//import {LoginComponent} from './login/login.component';
import {AppRoutingModule} from './app-routing.module';
import {authInterceptorProviders} from './helpers/auth.interceptor';
import { NewsBoardComponent } from './components/news-board/news-board.component';
import {PostService} from './services/post.service';
import { PostComponent } from './components/post/post.component';
import {CommentService} from './services/comment.service';


@NgModule({
  declarations: [
    AppComponent,
  //  LoginComponent,
    NewsBoardComponent,
  PostComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    authInterceptorProviders,
    PostService,
    CommentService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
