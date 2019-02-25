

function onMouseEnterId () {
    document.getElementById("emp-id-input").value = "Example : 7432";
}

function onMouseOutId () {
    document.getElementById("emp-id-input").value = "";
}

function onMouseEnterName() {
    document.getElementById("emp-name-input").value = "Ivan Ivanovich";
}

function onMouseOutName () {
    document.getElementById("emp-name-input").value = "";
}

function validateName () {
    var input = document.getElementById("emp-name-input");
    var matches = input.value.match(/\d+/g);
    if (matches != null) {
        alert("Name should not contain digits");
        input.value = "";
        return;
    }
    transfer();
}

function transfer() {
    //var a = document.f.elements['name'].value;
    var name = document.getElementById('emp-name-input').value;
    window.location.href = "newEmployee.html?" + name;
}

function confirmDelete (itemToDelete) {
    var answer = confirm("R u sure 2 delete dat stuff?");
    if (answer) {
        document.getElementById(itemToDelete).remove();
    }
}
