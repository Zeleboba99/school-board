import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
//import {LoginComponent} from './login/login.component';
import {NewsBoardComponent} from './components/news-board/news-board.component';
import {PostComponent} from './components/post/post.component';
import {EditCommentComponent} from './components/edit-comment/edit-comment.component';
import {EditPostComponent} from './components/edit-post/edit-post.component';
import {AdminComponent} from './components/admin/admin.component';
import {EditUserComponent} from './components/edit-user/edit-user.component';
import {LoginComponent} from './components/login/login.component';
import {MainGuard} from './guards/main.guard';
import {StudentGuard} from './guards/student.guard';
import {TeacherGuard} from './guards/teacher.guard';
import {AdminGuard} from './guards/admin.guard';

const routes: Routes = [
  { path: 'news-board', component: NewsBoardComponent, canActivate: [MainGuard, StudentGuard]},
  { path: 'post', component: PostComponent, canActivate: [MainGuard, StudentGuard]},
  { path: 'edit-comment', component: EditCommentComponent, canActivate: [MainGuard, StudentGuard]},
  { path: 'edit-post', component: EditPostComponent, canActivate: [MainGuard, TeacherGuard]},
  { path: 'admin', component: AdminComponent, canActivate: [MainGuard, AdminGuard]},
  { path: 'edit-user', component: EditUserComponent, canActivate: [MainGuard, AdminGuard]},
  { path: 'login', component: LoginComponent},
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '**', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
