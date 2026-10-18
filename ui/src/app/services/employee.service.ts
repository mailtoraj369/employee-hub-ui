import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';

export interface Employee {
  empId: number;
  name: string;
  email: string;
  department: string;
  location: string;
  salary: number;
}

export interface CountryEmployeeCount {
  location: string;
  employeeCount: number;
}

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  constructor(private readonly http: HttpClient) {}

  getEmployees(pageNumber = 0, pageSize = 10): Observable<Employee[]> {
    return this.http.get<Employee[]>(`/api/employees?pageNumber=${pageNumber}&pageSize=${pageSize}`);
  }

  getHighestPaidEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>('/api/employees/highest-paid').pipe(
      map((response) => (Array.isArray(response) ? response : response ? [response as Employee] : []))
    );
  }

  getCountryCounts(): Observable<CountryEmployeeCount[]> {
    return this.http
      .get<CountryEmployeeCount[] | Record<string, number> | CountryEmployeeCount>(
        '/api/employees/country-counts'
      )
      .pipe(
        map((response) => {
          if (Array.isArray(response)) {
            return response;
          }

          if (response && typeof response === 'object' && 'location' in response) {
            return [response as CountryEmployeeCount];
          }

          return Object.entries(response ?? {}).map(([location, employeeCount]) => ({
            location,
            employeeCount: Number(employeeCount),
          }));
        })
      );
  }
}
