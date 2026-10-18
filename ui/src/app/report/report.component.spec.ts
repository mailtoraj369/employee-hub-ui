import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { EmployeeService } from '../services/employee.service';
import { ReportComponent } from './report.component';

describe('ReportComponent', () => {
  let fixture: ComponentFixture<ReportComponent>;
  let employeeService: EmployeeService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReportComponent],
      providers: [
        {
          provide: EmployeeService,
          useValue: {
            getHighestPaidEmployees: () => of([
              {
                empId: 101,
                name: 'Alice Johnson',
                email: 'alice@company.com',
                department: 'Engineering',
                location: 'Remote',
                salary: 120000,
              },
            ]),
            getCountryCounts: () => of([
              { location: 'Remote', employeeCount: 1 },
            ]),
          },
        },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(ReportComponent);
    employeeService = TestBed.inject(EmployeeService);
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(fixture.componentInstance).toBeTruthy();
  });

  it('should render both reports', () => {
    const native = fixture.nativeElement as HTMLElement;
    expect(native.textContent).toContain('Employees by Country');
    expect(native.textContent).toContain('Highest Paid Employees');
    expect(native.textContent).toContain('Alice Johnson');
  });

  it('should call both APIs on init', () => {
    expect(employeeService.getHighestPaidEmployees).toHaveBeenCalled();
    expect(employeeService.getCountryCounts).toHaveBeenCalled();
  });
});
