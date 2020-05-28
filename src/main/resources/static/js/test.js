$(document).ready(
    function getAll() {
        getAllUsers();
        deleteUser();
        userInfo(id);

        updateUser();
        addUser();
    }
);

function getAllUsers() {
    $('#list_users').empty();
    $.getJSON('/admin/users', function (users) {
            table(users);
        }
    )
}

function deleteUser() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/admin/delete/' + document.getElementById('idDelete').value,
        cache: false
    });
}

function updateUser() {
    $.post('/admin/edit/update',
        $("#user_update").serialize()
    )
}

function table(users) {
    let tr = [];
    for (let i in users) {
        tr.push('<tr>');
        tr.push('<td>' + users[i].id + '</td>');
        tr.push('<td>' + users[i].name + '</td>');
        tr.push('<td>' + users[i].email + '</td>');
        tr.push('<td>');
        for (let r in users[i].roleSet) {
            tr.push(users[i].roleSet[r].role + '<br>');
        }
        tr.push('</td>');
        tr.push('<td><button type="submit" class="btn btn-primary mb-3" data-toggle="modal"' +
            ' data-target="#modalWindowDelete" onclick="userInfo(' + users[i].id + ');">Delete</button></td>');
        tr.push('<td><button type="submit" class="btn btn-danger" data-toggle="modal"' +
            ' data-target="#modalWindowUpdate" onclick="userInfo(' + users[i].id + ');">Update</button></td>');


        tr.push('</tr>');
    }
    $('#list_users').append($(tr.join('')));
}

function userInfo(id) {
    $.getJSON('http://localhost:8080/user/' + id, function (userEdit) {
        document.getElementById('modalWindowUpdateLabel').textContent = 'Edit user - ' + userEdit.name;
        document.getElementById('modalWindowDeleteLabel').textContent = 'Delete user - ' + userEdit.name;
        document.getElementById('idUpdate').value = userEdit.id;
        document.getElementById('idDelete').value = userEdit.id;
        document.getElementById('loginUpdate').value = userEdit.name;
        document.getElementById('loginDelete').value = userEdit.name;
        document.getElementById('passwordUpdate').value = userEdit.password;
        document.getElementById('passwordDelete').value = userEdit.password;
        document.getElementById('emailUpdate').value = userEdit.email;
        document.getElementById('emailDelete').value = userEdit.email;
        // document.getElementById('roleUpdate').value = userEdit.roles;
    })
}

function addUser() {
    $.post('/admin/users',
        $("#addNewUser").serialize()
    )
}