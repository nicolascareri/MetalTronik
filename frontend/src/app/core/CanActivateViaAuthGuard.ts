import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { CanActivateChild } from '@angular/router';
import { AuthService } from '../usuarios/services/auth.service';

@Injectable()
export class CanActivateViaAuthGuard implements CanActivateChild {

    constructor(private authService: AuthService, 
                private router: Router) { }

    canActivateChild() {
        if (!this.authService.logIn()) {
            console.log('No estás logueado');
            this.router.navigate(['/']);
            return false;
        }

        return true;
    }
}