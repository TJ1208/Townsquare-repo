import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PostBoardComponent } from './components/post-board/post-board.component';

const routes: Routes = [
  { path: "home", component: PostBoardComponent },
  { path: "**", redirectTo: "home" }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
