import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Libro } from '../shared/libro.model';
import { LibroService } from '../shared/libro.service';

@Component({
  selector: 'app-libro-list',
  templateUrl: './libro-list.component.html',
  styleUrls: ['./libro-list.component.css'],
})
export class LibroListComponent implements OnInit {
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  dataSource!: MatTableDataSource<Libro>;
  displayedColumns: string[] = ['id', 'title', 'description', 'acciones'];
  cantidad: number = 0;

  constructor(
    private libroService: LibroService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.getAll();

    /* this.libroService.getLibroCambio().subscribe((data) => {
      this.crearTabla(data);
    });

    this.libroService.getMensajeCambio().subscribe((data) => {
      this.snackBar.open(data, 'AVISO', {
        duration: 2000,
        verticalPosition: 'top',
        horizontalPosition: 'right',
      });
    });*/
  }

  /*crearTabla(data: Libro[]) {
    this.dataSource = new MatTableDataSource(data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }*/

  getAll() {
    this.libroService.getAllPageable(0, 3).subscribe((data) => {
      this.cantidad = data.totalElements;
      this.dataSource = new MatTableDataSource(data.content);
      this.dataSource.sort = this.sort;
    });
  }

  filtrar(value: string) {
    this.dataSource.filter = value.trim().toLowerCase();
  }

  mostrarMas(e: any) {
    this.libroService
      .getAllPageable(e.pageIndex, e.pageSize)
      .subscribe((data) => {
        this.cantidad = data.totalElements;
        this.dataSource = new MatTableDataSource(data.content);
        this.dataSource.sort = this.sort;
      });
  }

  /*eliminar(id: number) {
    this.libroService.delete(id).subscribe(() => {
      this.libroService.getAll().subscribe((data) => {
        this.libroService.setLibrocambio(data);
        this.libroService.setMensajeCambio('SE ELIMINO');
      });
    });
  }*/

  eliminar(id: number) {
    const ok = confirm('¿Estás seguro de eliminar el libro?');

    if (ok) {
      this.libroService.delete(id).subscribe(() => {
        this.getAll();
      });
    }
  }
}
