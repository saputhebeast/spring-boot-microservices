import React, { useEffect, useState } from 'react';
import getEmployees from '../service/EmployeeService';

function EmployeeComponent() {
    const [employeeData, setEmployeeData] = useState({});
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        getEmployees()
            .then((response) => {
                setEmployeeData(response.data);
                setLoading(false);
            })
            .catch((error) => {
                console.log(error);
                setLoading(false);
            });
    }, []);

    return (
        <div className="container mt-5">
            {loading ? (
                <div className="text-center">Loading data...</div>
            ) : (
                <div>
                    <h1>Employee Details</h1>
                    <div className="card mb-3">
                        <div className="card-body">
                            <p className="card-text"><strong>First Name:</strong> {employeeData.employee.firstName}</p>
                            <p className="card-text"><strong>Last Name:</strong> {employeeData.employee.lastName}</p>
                            <p className="card-text"><strong>Email:</strong> {employeeData.employee.email}</p>
                        </div>
                    </div>

                    <h1>Department Details</h1>
                    <div className="card mb-3">
                        <div className="card-body">
                            <p className="card-text"><strong>Department Name:</strong> {employeeData.department.departmentName}</p>
                            <p className="card-text"><strong>Department Description:</strong> {employeeData.department.departmentDescription}</p>
                            <p className="card-text"><strong>Department Code:</strong> {employeeData.department.departmentCode}</p>
                        </div>
                    </div>

                    <h1>Organization Details</h1>
                    <div className="card mb-3">
                        <div className="card-body">
                            <p className="card-text"><strong>Organization Name:</strong> {employeeData.organizationDto.organizationName}</p>
                            <p className="card-text"><strong>Organization Description:</strong> {employeeData.organizationDto.organizationDescription}</p>
                            <p className="card-text"><strong>Organization Code:</strong> {employeeData.organizationDto.organizationCode}</p>
                            <p className="card-text"><strong>Created Date:</strong> {employeeData.organizationDto.createdDate}</p>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}

export default EmployeeComponent;
