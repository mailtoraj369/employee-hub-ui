# ACME Salary Management System — Requirements Document

Version: 1.0 | Status: Draft

1. Goal

Build a secure, web-based salary management system that enables ACME’s HR manager to maintain and analyze salary data for employees across multiple countries. The system will replace Excel-based workflows with a centralized source of truth, reduce manual errors, and help HR answer questions about how the organization pays its people.

2. Scope & Features

In scope (MVP)

	- Portal access secured with login screen, limited to certain users now.
	
	- Employee salary management: view employee salary records. Each record includes employee ID, name, department,country, base salary.

	- Salary analytics and reporting: Provide dashboards and reports salary ranges, headcount by country, department. 

Future Release Enhancements :
	- Natural-language questions: Allow authorized users to ask questions such as “What is the max salary by country?” or “Which departments have the highest average salaries?” and receive answers via AI LLM chats

## 3. Non-Functional Requirements

- Performance and scale: Support 10,000 employee records, with common searches and dashboard queries returning within 3 seconds under normal operating conditions.

- Accuracy: All reports must use validated source data with exposed endpoint to measure the source employee count

- Reliability and usability: Provide clear error messages and a simple interface usable by non-technical HR staff.


## 4. Deliberately Out of Scope

- Advanced AI-driven pay recommendations: The system will answer descriptive questions using verified calculations, but will not recommend individual salaries or make compensation decisions. This reduces the risk of unsupported conclusions and biased outcomes.
- Payroll processing and salary payments: The system manages salary information, not payroll calculations, tax deductions, or bank transfers. These introduce substantial financial and regulatory complexity.
- Employee self-service: Employees will not view or edit their own salary records in the MVP. The initial focus is the HR manager’s workflow and reporting needs.
- Automatic currency conversion and live exchange rates: The MVP will use an explicitly configured exchange-rate source or supplied rates rather than introducing real-time financial data dependencies.


## 5. Success Criteria

The MVP will be considered successful when:

- Authorized HR users can view employee salary records without relying on spreadsheets as the primary system.
- unauthorized access is prevented.

Guiding principle: Deliver a secure, accurate salary data source and useful reporting first; expand into payroll, integrations, and compensation workflows only after the core system proves reliable.

