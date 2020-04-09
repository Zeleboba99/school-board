import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {FormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
//import {LoginComponent} from './login/login.component';
import {AppRoutingModule} from './app-routing.module';
//import {authInterceptorProviders} from './helpers/auth.interceptor';
import { NewsBoardComponent } from './components/news-board/news-board.component';
import {PostService} from './services/post.service';
import { PostComponent } from './components/post/post.component';
import {CommentService} from './services/comment.service';
import { EditCommentComponent } from './components/edit-comment/edit-comment.component';
import { EditPostComponent } from './components/edit-post/edit-post.component';
import { AdminComponent } from './components/admin/admin.component';
import {AdminService} from './services/admin.service';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import {BaseUrlInterceptor} from './services/interceptors/base-url-interceptor';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import {AuthenticationInterceptor} from './services/interceptors/authentication-interceptor';
import { LoginComponent } from './components/login/login.component';
import {AuthService} from './services/auth.service';



@NgModule({
  declarations: [
    AppComponent,
  //  LoginComponent,
    NewsBoardComponent,
  PostComponent,
  EditCommentComponent,
  EditPostComponent,
  AdminComponent,
  EditUserComponent,
  LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    //authInterceptorProviders,
    { provide: CookieService, useFactory: cookieServiceFactory },
    PostService,
    CommentService,
    AdminService,
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthenticationInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BaseUrlInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
export function cookieServiceFactory() {
  return new CookieService();
}
