const employeeList = document.getElementById("employee-list");
const addEmployeeForm = document.getElementById("addEmployeeForm");
const employeeForm = document.getElementById("employeeForm");
const viewManageButton = document.getElementById("viewManage");
const addEmployeeButton = document.getElementById("addEmployee");
const editEmployeeForm = document.getElementById("editEmployeeForms");
const editForm = document.getElementById("editEmployeeForm");
let editingEmployeeId = null; // Track the employee being edited
// Show the "View/Manage Employees" section and hide the "Add Employee" form
function showViewEmployees() {
  employeeList.classList.remove("hidden");
  addEmployeeForm.classList.add("hidden");
  editEmployeeForm.classList.add("hidden");
  editingEmployeeId = null; // Reset editing mode
  employeeForm.reset();
}
// Show the "Add Employee" form and hide the "View/Manage Employees" section
function showAddEmployeeForm(employee = null) {
  employeeList.classList.add("hidden");
  addEmployeeForm.classList.remove("hidden");
  editEmployeeForm.classList.add("hidden");
  employeeForm.reset();
}
function showEditEmployeeForm(employee) {
  employeeList.classList.add("hidden");
  addEmployeeForm.classList.add("hidden");
  editEmployeeForm.classList.remove("hidden");
  // Pre-fill the edit form with employee data
  editingEmployeeId = employee.id;
  document.getElementById("editName").value = employee.name;
  document.getElementById("editEmail").value = employee.email;
  document.getElementById("editDepartment").value = employee.department;
  document.getElementById("editDesignation").value = employee.designation;
  document.getElementById("editSalary").value = employee.salary;
}
// Fetch employees from the backend
async function fetchEmployees() {
  try {
    const response = await fetch("/employees", {
      method: "GET",
      credentials: "include",
    });
    if (response.ok) {
      const employees = await response.json();
      renderEmployees(employees);
    } else {
      console.error("Failed to fetch employees");
    }
  } catch (error) {
    console.error("Error fetching employees:", error);
  }
}
// Render employees on the dashboard
function renderEmployees(employees) {
  employeeList.innerHTML = ""; // Clear existing employees
  employees.forEach((employee) => {
    const employeeDiv = createEmployeeElement(employee);
    employeeList.appendChild(employeeDiv);
  });
}
// Create a single employee entry
function createEmployeeElement(employee) {
  const employeeDiv = document.createElement("div");
  employeeDiv.classList.add("employee");
  employeeDiv.innerHTML = `
        <div class="employee-details">
            <div class="detail"><strong>Name:</strong> ${employee.name}</div>
            <div class="detail"><strong>Email:</strong> ${employee.email}</div>
            <div class="detail"><strong>Designation:</strong> ${
              employee.designation || "Not Assigned"
            }</div>
            <div class="detail"><strong>Department:</strong> ${
              employee.department || "Not Assigned"
            }</div>
            <div class="detail"><strong>Salary:</strong> ${
              employee.salary || 0
            }</div>
            <div class="employee-actions">
                <button class="edit" data-id="${employee.id}">Edit</button>
                <button class="delete" data-id="${employee.id}">Delete</button>
            </div>
        </div>
    `;
  // Add event listener for edit button
  const editButton = employeeDiv.querySelector(".edit");
  editButton.addEventListener("click", () => {
    showEditEmployeeForm(employee);
  });
  // Add event listener for delete button
  const deleteButton = employeeDiv.querySelector(".delete");
  deleteButton.addEventListener("click", async () => {
    const check = confirm(
      "Are you sure you want to delete this employee? This action cannot be undone."
    );
    if (check) {
      await deleteEmployee(employee.id);
    }
  });
  return employeeDiv;
}
// Add or update an employee
async function saveEmployee(employee) {
  try {
    const url = "/employees/add";
    const response = await fetch(url, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(employee),
    });
    if (response.ok) {
      fetchEmployees(); // Refresh the list
      showViewEmployees(); // Show the View/Manage section
    } else {
      console.error("Failed to save employee");
    }
  } catch (error) {
    console.error("Error saving employee:", error);
  }
}
editForm.addEventListener("submit", async (event) => {
  event.preventDefault();
  const check = confirm(
    "Are you sure you want to update this employee? This action cannot be undone."
  );
  if (!check) {
    return;
  }
  const formData = new FormData(editForm);
  const updatedEmployee = {
    name: formData.get("name"),
    email: formData.get("email"),
    department: formData.get("department"),
    designation: formData.get("designation"),
    salary: formData.get("salary"),
  };
  console.log(updatedEmployee);
  try {
    const response = await fetch(`/employees/edit/${editingEmployeeId}`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(updatedEmployee),
    });
    if (response.ok) {
      fetchEmployees();
      showViewEmployees();
    }
  } catch (error) {
    console.error("Error saving employee:", error);
  }
});
// Delete an employee
async function deleteEmployee(id) {
  try {
    const response = await fetch(`/employees/delete/${id}`, {
      method: "DELETE",
    });
    if (response.ok) {
      fetchEmployees(); // Refresh the list
    } else {
      console.error("Failed to delete employee");
    }
  } catch (error) {
    console.error("Error deleting employee:", error);
  }
}
// Form submission handler for adding or updating an employee
employeeForm.addEventListener("submit", async (event) => {
  event.preventDefault();
  const formData = new FormData(employeeForm);
  const employee = {
    name: formData.get("name"),
    email: formData.get("email"),
    department: formData.get("department"),
    designation: formData.get("designation"),
    salary: formData.get("salary"),
  };
  await saveEmployee(employee);
});
// Attach click event listeners to toggle sections
viewManageButton.addEventListener("click", showViewEmployees);
addEmployeeButton.addEventListener("click", () => showAddEmployeeForm());
// Fetch employees and show the View/Manage section by default
fetchEmployees();
showViewEmployees();
