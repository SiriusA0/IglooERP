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
      console.log(document.querySelector("#navBarUserName").innerHTML)
      document.querySelector("#navBarUserName").innerHTML=cookie;
    }
  }
}
