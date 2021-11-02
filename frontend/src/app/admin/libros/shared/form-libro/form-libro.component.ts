import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Libro } from '../libro.model';
import { LibroService } from '../libro.service';

@Component({
  selector: 'app-form-libro',
  templateUrl: './form-libro.component.html',
  styleUrls: ['./form-libro.component.css'],
})
export class FormLibroComponent implements OnInit {
  form!: FormGroup;
  errors: any[] = [];

  loading: boolean = false;
  //@Input() libro?: any;
  @Input() libro: Libro = new Libro();
  @Output() onSave: EventEmitter<any> = new EventEmitter();

  constructor(
    private libroService: LibroService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      title: [
        this.libro?.title,
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(250),
        ],
      ],
      description: [this.libro?.description, [Validators.required]],
      price: [this.libro?.price, [Validators.min(1)]],
    });
  }

  save() {
    this.onSave.emit(this.form.value);
  }
}
