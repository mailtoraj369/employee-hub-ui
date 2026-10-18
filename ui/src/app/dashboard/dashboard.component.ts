import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Employee, EmployeeService } from '../services/employee.service';

@Component({
 selector: 'app-dashboard',
 standalone: true,
 imports: [CommonModule],
 changeDetection: ChangeDetectionStrategy.OnPush,
 templateUrl: './dashboard.component.html',
 styleUrl: './dashboard.component.scss',
})
export class DashboardComponent implements OnInit {
 employees: Employee[] = [];
 pagedEmployees: Employee[] = [];
 loading = true;
 error = '';
 pageSize = 10;
 currentPage = 0;
 totalPages = 1;
 canGoNext = false;
 canGoPrevious = false;

 constructor(
   private readonly employeeService: EmployeeService,
   private readonly cdr: ChangeDetectorRef
 ) {}

 ngOnInit(): void {
   this.loadEmployees(this.currentPage, this.pageSize);
 }

 loadEmployees(pageNumber: number, pageSize: number): void {
   this.loading = true;
   this.error = '';

   this.employeeService.getEmployees(pageNumber, pageSize).subscribe({
     next: (data) => {
       this.employees = data;
       this.pagedEmployees = data;
       this.canGoPrevious = pageNumber > 0;
       this.canGoNext = data.length === pageSize;
       this.totalPages = Math.max(1, this.currentPage + 1);
       this.loading = false;
       this.cdr.markForCheck();
     },
     error: () => {
       this.error = 'Unable to load employees.';
       this.loading = false;
       this.cdr.markForCheck();
     },
   });
 }

 nextPage(): void {
   if (this.loading || !this.canGoNext) {
     return;
   }

   this.currentPage += 1;
   this.loadEmployees(this.currentPage, this.pageSize);
 }

 previousPage(): void {
   if (this.loading || !this.canGoPrevious) {
     return;
   }

   this.currentPage -= 1;
   this.loadEmployees(this.currentPage, this.pageSize);
 }
}
