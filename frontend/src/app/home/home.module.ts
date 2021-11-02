import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { MaterialModule } from '../material/material.module';

import { HomeRoutingModule } from './home-routing.module';
import { IndexComponent } from './index/index.component';
import { LayoutComponent } from './layout/layout.component';

@NgModule({
  declarations: [IndexComponent, LayoutComponent],
  imports: [CommonModule, FormsModule, MaterialModule, HomeRoutingModule],
})
export class HomeModule {}
