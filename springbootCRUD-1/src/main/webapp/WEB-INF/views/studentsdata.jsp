<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Display Students</title>
    <link rel="stylesheet" href="https://cdn.syncfusion.com/ej2/material.css" />
</head>
<body>
    <h2>Student Data</h2>

    <div id="grid"></div>

    <script src="https://cdn.syncfusion.com/ej2/dist/ej2.min.js"></script>
    <script>
    document.addEventListener('DOMContentLoaded', function () {

        var students = [];
        var selectedRecords = []; // Declare selectedRecords at a higher scope

        // Initialize the DataGrid
        ej.grids.Grid.Inject(ej.grids.Edit, ej.grids.Toolbar);
        var grid = new ej.grids.Grid({
            dataSource: students,
            editSettings: { allowEditing: true, allowDeleting: true, allowAdding: true, mode: 'Dialog', height: 400 },
            toolbar: ['Add', 'Edit', 'Delete', 'Search'],
            columns: [
                { field: 'id', headerText: 'RollNo' , isPrimaryKey: true},
                { field: 'firstname', headerText: 'First Name' },
                { field: 'lastname', headerText: 'Last Name' },
                { field: 'email', headerText: 'Email' },
                { field: 'mobile', headerText: 'Mobile' },
            ],

            allowPaging: true,
            gridLines: 'Both',
            pageSettings: { pageSize: 10 },
            allowSorting: true,

            actionComplete: function (args) {
                if (args.requestType === 'save') {
                    // The 'save' requestType indicates that an add, edit, or delete operation was performed
                    if (args.action === 'add') {
                        // If it was an 'add' action, send the new record to the server to add to the database
                        addStudent(args.data);
                    } else if (args.action === 'edit') {
                        // If it was an 'edit' action, send the updated record to the server to update the database
                        updateStudentData(args.data);
                    }
                }
            }

        });

        // Function to fetch students data
        function fetchStudentsData() {
            // Make an AJAX request to the server to fetch data
            fetch('/allstudents')
                .then(response => response.json())
                .then(data => {
                    console.log('Data received:', data);

                    // Update the DataGrid with the retrieved data
                    grid.dataSource = data;
                    grid.refresh();
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });
        }

        // Function to add a new student
        function addStudent(student) {
            fetch('/addstudent', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(student),
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Student added successfully:', data);
                    // Fetch updated data after adding a new student
                    fetchStudentsData();
                })
                .catch(error => {
                    console.error('Error adding student:', error);
                });
        }
        
        // Function to update a student
        function updateStudentData(updatedStudent) {
            fetch('/updateStudent/' + updatedStudent.id, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(updatedStudent),
            })
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('Error updating student: ' + response.statusText);
                    }
                })
                .then(data => {
                    // Process the updated student data
                    console.log('Student updated successfully:', data);
                    fetchStudentsData(); // Refresh the data after a successful update
                })
                .catch(error => {
                    console.error('Error updating student:', error.message);
                });
        }

        grid.toolbarClick = function (args) {
            if (args.item.id === 'grid_delete') {
                // Get the selected records
                var selectedRecords = grid.getSelectedRecords();

                if (selectedRecords.length > 0) {
                    // Send a POST request to the server to delete the selected record
                    fetch('/deleteStudent/' + selectedRecords[0].id, {
                        method: 'POST',
                    })
                        .then(response => response.text())
                        .then(message => {
                            console.log(message);
                            // Refresh the grid after a successful deletion
                            fetchStudentsData();
                        })
                        .catch(error => {
                            console.error('Error deleting record:', error);
                        });
                } else {
                    console.warn('No records selected for deletion');
                }
            }
        };

        // Render the DataGrid
        grid.appendTo('#grid');

        // Automatically fetch data when the DOM content is loaded
        fetchStudentsData();

    });

    </script>
</body>
</html>
<style>.e-grid .e-toolbar .e-btn {
    background-color: #deecf9;
    }
    .e-grid .e-headercell {
   color: darkblue;
    font-weight: bold;
   
}
.e-grid .e-gridheader {
    
      font-weight: bold;
}
.e-grid .e-headercelldiv {
   font-size: 15px;
}
</style>
