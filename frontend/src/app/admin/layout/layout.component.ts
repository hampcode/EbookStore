import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserStorageService } from 'src/app/auth/shared/user-storage.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css'],
})
export class LayoutComponent implements OnInit {
  constructor(
    private userStorageService: UserStorageService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  signOut(): void {
    this.userStorageService.destroy();
    this.router.navigate(['/auth/login']);
  }
}
