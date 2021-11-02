import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Libro } from './libro.model';

@Injectable({
  providedIn: 'root',
})
export class LibroService {
  private apiBase: string = environment.apiBase;

  private libroCambio: Subject<Libro[]> = new Subject<Libro[]>();
  private mensajeCambio: Subject<string> = new Subject<string>();

  constructor(private http: HttpClient) {}

  //{{base_url}}/admin/books?page=0&size=2
  getAllPageable(page: number, size: number) {
    let params = new HttpParams();
    params = params.append('page', page);
    params = params.append('size', size);

    return this.http.get<any>(`${this.apiBase}/books/pageable`, {
      params,
    });
  }

  getAll() {
    return this.http.get<Libro[]>(`${this.apiBase}/books`);
  }

  get(id: number) {
    return this.http.get(`${this.apiBase}/books/${id}`);
  }

  create(libro: Libro) {
    return this.http.post(`${this.apiBase}/books`, libro);
  }

  update(libro: Libro) {
    return this.http.put(`${this.apiBase}/books`, libro);
  }

  delete(id: number) {
    return this.http.delete(`${this.apiBase}/books/${id}`);
  }

  getLibroCambio() {
    return this.libroCambio.asObservable();
  }

  setLibrocambio(lista: Libro[]) {
    this.libroCambio.next(lista);
  }

  setMensajeCambio(mensaje: string) {
    this.mensajeCambio.next(mensaje);
  }

  getMensajeCambio() {
    return this.mensajeCambio.asObservable();
  }
}
