import { Component, OnInit } from '@angular/core';
import { Libro } from 'src/app/admin/libros/shared/libro.model';
import { HomeService } from '../shared/home.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css'],
})
export class IndexComponent implements OnInit {
  ultimosLibros: Libro[] = [];

  urlImage: string =
    'https://images-na.ssl-images-amazon.com/images/I/41NmUGJh1UL._SX218_BO1,204,203,200_QL40_ML2_.jpg';

  constructor(private homeService: HomeService) {}

  ngOnInit(): void {
    this.getAllLatestBooks();
  }

  getAllLatestBooks(): void {
    let request = this.homeService.getUltimosLibros();

    request.subscribe((data: any) => {
      this.ultimosLibros = data['body'];
    });
  }
}
