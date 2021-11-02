import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LibroService } from '../shared/libro.service';

@Component({
  selector: 'app-new-libro',
  templateUrl: './new-libro.component.html',
  styleUrls: ['./new-libro.component.css'],
})
export class NewLibroComponent implements OnInit {
  constructor(private libroService: LibroService, private router: Router) {}

  ngOnInit(): void {}

  create(libro: any) {
    /*let libro = new Libro();
    libro.id = this.form.value['id'];
    libro.title = this.form.value['title'];
    libro.description = this.form.value['description'];
    libro.price = this.form.value['price'];*/

    this.libroService.create(libro).subscribe(
      () => {
        this.router.navigate(['admin/libros']);
        /*this.libroService.getAll().subscribe((data) => {
        this.libroService.setLibrocambio(data);
        this.libroService.setMensajeCambio('SE REGISTRO');
      });*/
      },
      (error) => {}
    );

    //this.router.navigate(['admin/libros']);
  }
}
