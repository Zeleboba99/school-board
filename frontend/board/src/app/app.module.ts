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
import { SpinnerComponent } from './components/spinner/spinner.component';
import {NgxSpinnerModule, NgxSpinnerService} from 'ngx-spinner';
import {MainGuard} from './guards/main.guard';
import {StudentGuard} from './guards/student.guard';
import {TeacherGuard} from './guards/teacher.guard';
import {AdminGuard} from './guards/admin.guard';
import { LOCALE_ID } from '@angular/core';
import { registerLocaleData } from '@angular/common';
import localeRu from '@angular/common/locales/ru';

// the second parameter 'fr-FR' is optional
registerLocaleData(localeRu, 'ru');


@NgModule({
  declarations: [
    AppComponent,
    NewsBoardComponent,
    PostComponent,
    EditCommentComponent,
    EditPostComponent,
    AdminComponent,
    EditUserComponent,
    LoginComponent,
    SpinnerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgxSpinnerModule
  ],
  providers: [
    { provide: CookieService, useFactory: cookieServiceFactory },
    NgxSpinnerService,
    PostService,
    CommentService,
    AdminService,
    AuthService,
    MainGuard,
    StudentGuard,
    TeacherGuard,
    AdminGuard,
    { provide: LOCALE_ID, useValue: 'ru' },
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
