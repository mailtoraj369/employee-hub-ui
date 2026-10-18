import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Employee } from '../services/employee.service';
import { DashboardComponent } from './dashboard.component';

describe('DashboardComponent', () => {
  let fixture: ComponentFixture<DashboardComponent>;
  let httpMock: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DashboardComponent, HttpClientTestingModule],
    }).compileComponents();

    httpMock = TestBed.inject(HttpTestingController);
    fixture = TestBed.createComponent(DashboardComponent);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should load employees and render them in the table', () => {
    const employees: Employee[] = [
      {
        empId: 101,
        name: 'Alice Johnson',
        email: 'alice@company.com',
        department: 'Engineering',
        location: 'Remote',
        salary: 120000,
      },
    ];

    fixture.detectChanges();

    const request = httpMock.expectOne('/api/employees');
    expect(request.request.method).toBe('GET');
    request.flush(employees);

    fixture.detectChanges();

    const native = fixture.nativeElement as HTMLElement;
    expect(native.querySelector('h1')?.textContent).toContain('Employee Dashboard');
    expect(native.querySelectorAll('tbody tr').length).toBe(1);
    expect(native.textContent).toContain('Alice Johnson');
  });

  it('should show an error message when the employee request fails', () => {
    fixture.detectChanges();

    const request = httpMock.expectOne('/api/employees');
    request.flush('Server error', { status: 500, statusText: 'Server Error' });

    fixture.detectChanges();

    const native = fixture.nativeElement as HTMLElement;
    expect(fixture.componentInstance.error).toBe('Unable to load employees.');
    expect(native.textContent).toContain('Unable to load employees.');
  });
});
