import { Component } from '@angular/core';
import { ApiService } from '../api-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-component',
  standalone: false,
  templateUrl: './login-component.html',
  styleUrl: './login-component.css',
})
export class LoginComponent {

  opname = '';

  constructor(private api: ApiService, private router: Router) {}

  login() {
    this.api.login(this.opname).subscribe({
      next: (res: any) => {
        localStorage.setItem('operator', JSON.stringify(res));
        this.router.navigate(['/dashboard']);
      },
      error: err => alert(err.error)
    });
  }
}
