function add_user(inputClassCss) {
    var user = new Object();

    var inputs = document.querySelectorAll(inputClassCss);
    for (let i in inputs) {
        var attribute = inputs[i].name;
        user[attribute] = inputs[i].value;
    }

    if (user.password == document.querySelector("#confirmPassword").value) {

        var userID = user.userName;
        window.localStorage.setItem(userID, JSON.stringify(user));

    } else {
        alert("Passwords don't match")
    }
}

function get_user() {
    var userNameInput = document.querySelector("#userInput").value;
    var userPasswordInput = document.querySelector("#passwordInput").value;
    var user = JSON.parse(window.localStorage.getItem(userNameInput));

    if (userPasswordInput == user.password) {
        document.cookie = "loggedUser="+user.userName;
        console.log("Login correcto");
        window.location.href = "./index.html";
    } else {
        console.log("Login incorrecto");
        alert("Login incorrecto");
    }

   
}

function logged_user() {

    var loggedUser = document.cookie;
    loggedUser = loggedUser.split("=");
    console.log(loggedUser);
    document.querySelector("#logged_user").innerHTML = loggedUser[1];

}