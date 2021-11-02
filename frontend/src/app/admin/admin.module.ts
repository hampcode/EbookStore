import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MaterialModule } from '../material/material.module';

import { AdminRoutingModule } from './admin-routing.module';
import { LayoutComponent } from './layout/layout.component';
import { NewLibroComponent } from './libros/new-libro/new-libro.component';
import { LibroListComponent } from './libros/libro-list/libro-list.component';
import { EditLibroComponent } from './libros/edit-libro/edit-libro.component';
import { FormLibroComponent } from './libros/shared/form-libro/form-libro.component';

@NgModule({
  declarations: [
    LayoutComponent,
    NewLibroComponent,
    LibroListComponent,
    EditLibroComponent,
    FormLibroComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    MaterialModule,
  ],
})
export class AdminModule {}
