import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CountryEmployeeCount, Employee, EmployeeService } from '../services/employee.service';

@Component({
  selector: 'app-report',
  standalone: true,
  imports: [CommonModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './report.component.html',
  styleUrl: './report.component.scss',
})
export class ReportComponent implements OnInit {
  highestPaidEmployees: Employee[] = [];
  countryCounts: CountryEmployeeCount[] = [];
  selectedTab: 'country' | 'salary' = 'country';
  loading = true;
  error = '';

  constructor(
    private readonly employeeService: EmployeeService,
    private readonly cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.employeeService.getHighestPaidEmployees().subscribe({
      next: (employees) => {
        this.highestPaidEmployees = employees;
        this.loadCountryCounts();
      },
      error: () => {
        this.error = 'Unable to load report data.';
        this.loading = false;
        this.cdr.markForCheck();
      },
    });
  }

  private loadCountryCounts(): void {
    this.employeeService.getCountryCounts().subscribe({
      next: (counts) => {
        this.countryCounts = counts;
        this.loading = false;
        this.cdr.markForCheck();
      },
      error: () => {
        this.error = 'Unable to load report data.';
        this.loading = false;
        this.cdr.markForCheck();
      },
    });
  }
}
