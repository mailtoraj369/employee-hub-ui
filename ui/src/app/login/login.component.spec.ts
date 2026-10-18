import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let fixture: ComponentFixture<LoginComponent>;
  let authService: AuthService;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoginComponent, ReactiveFormsModule],
      providers: [
        {
          provide: AuthService,
          useValue: {
            isAuthenticated: () => false,
            login: () => true,
          },
        },
        {
          provide: Router,
          useValue: {
            navigateByUrl: jasmine.createSpy('navigateByUrl'),
          },
        },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    authService = TestBed.inject(AuthService);
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(fixture.componentInstance).toBeTruthy();
  });

  it('should submit valid credentials and navigate to dashboard', () => {
    const authSpy = spyOn(authService, 'login').and.returnValue(true);
    fixture.componentInstance.loginForm.setValue({ username: 'admin', password: 'admin' });

    fixture.componentInstance.onSubmit();

    expect(authSpy).toHaveBeenCalledWith('admin', 'admin');
    expect(router.navigateByUrl).toHaveBeenCalledWith('/dashboard');
  });
});
