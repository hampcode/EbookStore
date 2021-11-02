import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Libro } from 'src/app/admin/libros/shared/libro.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class HomeService {
  private apiBase: string = environment.apiBase;

  constructor(private http: HttpClient) {}

  getUltimosLibros(): Observable<Libro[]> {
    return this.http.get<Libro[]>(`${this.apiBase}/books/latest-books`);
  }
}
