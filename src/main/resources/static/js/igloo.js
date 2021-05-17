function createCookie() {
  var value = document.querySelector("#userName").value;
  document.cookie = "user" + "=" + value + "; path=/";
}

function getCookie() {
  if (document.cookie.length > 0) {
    c_start = document.cookie.indexOf("user" + "=");
    if (c_start != -1) {
      c_start = c_start + "user".length + 1;
      c_end = document.cookie.indexOf(";", c_start);
      if (c_end == -1) {
        c_end = document.cookie.length;
      }

      var cookie = document.cookie.substring(c_start, c_end);
      console.log(cookie);
      console.log(document.querySelector("#navBarUserName").innerHTML);
      document.querySelector("#navBarUserName").innerHTML = cookie;
    }
  }
}

function showPassword(event) {
  var inputGroup = event.currentTarget.closest(".input-group");

  var passwordStatus = event.currentTarget.querySelector("i").className;
  switch (passwordStatus) {
    case "fas fa-eye-slash":
      inputGroup.querySelector("i").className = "fas fa-eye";
      inputGroup.querySelector("input").type = "text";

      break;

    case "fas fa-eye":
      inputGroup.querySelector("i").className = "fas fa-eye-slash";
      inputGroup.querySelector("input").type = "password";

      break;
  }
}
function checkPassword(event) {
  var pass = document.querySelector("#password").value;
  var passConfirm = document.querySelector("#confirmPassword").value;
  if (pass != passConfirm || pass == "") {
    document.querySelector("#createAccButton").disabled = true;
    document.querySelector("#passwordWarning").innerHTML = "Password do not match";
  } else {
    document.querySelector("#createAccButton").disabled = false;
    document.querySelector("#passwordWarning").innerHTML = "";
  }
}

function loginPassword(event) {
  var input = event.currentTarget;

  var passwordStatus = event.currentTarget.className;
  switch (passwordStatus) {
    case "ml-2 fas fa-eye-slash":
      input.className = "ml-2 fas fa-eye";
      input.closest(".form-group").querySelector("input").type = "text";

      break;

    case "ml-2 fas fa-eye":
      input.className = "ml-2 fas fa-eye-slash";
      input.closest(".form-group").querySelector("input").type = "password";

      break;
  }
}
