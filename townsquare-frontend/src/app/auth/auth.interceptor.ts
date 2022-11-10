import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { catchError, Observable, throwError } from "rxjs";
import { AuthService } from "../services/auth/auth.service";

@Injectable({
    providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor {

    constructor(private authService: AuthService, private router: Router) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        if (req.headers.get("No-Auth") === "True") {
            return next.handle(req.clone());
        }

        const token = this.authService.getToken();

        req = this.addToken(req, token);

        return next.handle(req).pipe(
            catchError(
                (error: HttpErrorResponse) => {
                    console.log(error.status);
                    if (error.status === 401) {
                        this.router.navigate(['/error']);
                        this.authService.clear();
                    } else if (error.status === 403) {
                        this.router.navigate(['/forbidden'])
                    }
                    return throwError("Something went wrong :(");
                }
            )
        );
    }

    private addToken(request: HttpRequest<any>, token: string) {
        return request.clone(
            {
                setHeaders: {
                    Authorization: `Bearer ${token}`
                }
            }
        );
    }

}