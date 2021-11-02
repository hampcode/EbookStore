import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LibroListComponent } from './libros/libro-list/libro-list.component';
import { NewLibroComponent } from './libros/new-libro/new-libro.component';
import { EditLibroComponent } from './libros/edit-libro/edit-libro.component';
import { LayoutComponent } from './layout/layout.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: 'libros',
        component: LibroListComponent,
      },
      {
        path: 'libros/nuevo',
        component: NewLibroComponent,
      },
      {
        path: 'libros/:id/editar',
        component: EditLibroComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
