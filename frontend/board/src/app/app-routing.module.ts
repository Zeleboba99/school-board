import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
//import {LoginComponent} from './login/login.component';
import {NewsBoardComponent} from './components/news-board/news-board.component';
import {PostComponent} from './components/post/post.component';
import {EditCommentComponent} from './components/edit-comment/edit-comment.component';
import {EditPostComponent} from './components/edit-post/edit-post.component';
import {AdminComponent} from './components/admin/admin.component';

const routes: Routes = [
  //{ path: 'login', component: LoginComponent },
  { path: 'news-board', component: NewsBoardComponent},
  { path: 'post', component: PostComponent},
  { path: 'edit-comment', component: EditCommentComponent},
  { path: 'edit-post', component: EditPostComponent},
  { path: 'admin', component: AdminComponent}
  //{ path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
