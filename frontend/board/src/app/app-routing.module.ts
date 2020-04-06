import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
//import {LoginComponent} from './login/login.component';
import {NewsBoardComponent} from './components/news-board/news-board.component';
import {PostComponent} from './components/post/post.component';

const routes: Routes = [
  //{ path: 'login', component: LoginComponent },
  { path: 'news-board', component: NewsBoardComponent},
  { path: 'post', component: PostComponent}
  //{ path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
