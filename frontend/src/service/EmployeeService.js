import axios from "axios";

const EMPLOYEE_API_BASE_URL = "http://localhost:9191/api/employees?employeeId=";
const EMPLOYEE_ID = 1;

function getEmployees() {
    return axios.get(`${EMPLOYEE_API_BASE_URL}${EMPLOYEE_ID}`);
}

export default getEmployees;
