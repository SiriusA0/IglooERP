// Server
var server_url = "http://localhost:8080/";
////////////////////////////////////// Load all clients //////////////////////////////////////
function showClients() {
  fetch("http://localhost:8080/api/client/show")
    .then((r) => r.json())
    .then((clients) => {
      cleanList();
      fillList(clients);
    });
}

////////////////////////////////////// Show client form //////////////////////////////////////
function addClient() {
  if (document.querySelector("#clientForm").style.display == "none") {
    document.querySelector("#clientForm").style.display = "";
  } else {
    document.querySelector("#successAlert").style.display = "none";
    document.querySelector("#clientForm").style.display = "none";
  }
}

////////////////////////////////////// Create Client //////////////////////////////////////
function createClient() {
  // Getting the values of the form.
  var type = document.querySelector("input[name=typeOfClient").value;
  var clientName = document.querySelector("input[name=clientName").value;
  clientName = clientName.split(" ");
  var firstName = clientName[0];
  var lastName = clientName[1];
  var streetLine1 = document.querySelector("input[name=companyAdress1").value;
  var streetLine2 = document.querySelector("input[name=companyAdress2").value;
  var phoneNumber1 = document.querySelector("input[name=phoneNumber1]").value;
  var phoneNumber2 = document.querySelector("input[name=phoneNumber2]").value;
  var cityId = document.querySelector("select[name=city]").value;
  var regionId = document.querySelector("select[name=region]").value;
  var zipCode = document.querySelector("input[name=ZIPCode]").value;
  var email = document.querySelector("input[name=email]").value;
  var countryId = document.querySelector("select[name=country]").value;
  var web = document.querySelector("input[name=webPage]").value;
  var idNumber = document.querySelector("input[name=nif]").value;
  var categoryId = document.querySelector("select[name=category]").value;
  var profilePic = document.querySelector("input[name=profilePic]").value;

  // Making the URL.
  var formData =
    "type" +
    "=" +
    type +
    "&" +
    "firstName" +
    "=" +
    firstName +
    "&" +
    "lastName" +
    "=" +
    lastName +
    "&" +
    "streetLine1" +
    "=" +
    streetLine1 +
    "&" +
    "streetLine2" +
    "=" +
    streetLine2 +
    "&" +
    "cityId" +
    "=" +
    cityId +
    "&" +
    "regionId" +
    "=" +
    regionId +
    "&" +
    "zipCode" +
    "=" +
    zipCode +
    "&" +
    "countryId" +
    "=" +
    countryId +
    "&" +
    "idNumber" +
    "=" +
    idNumber +
    "&" +
    "phoneNumber1" +
    "=" +
    phoneNumber1 +
    "&" +
    "phoneNumber2" +
    "=" +
    phoneNumber2 +
    "&" +
    "email" +
    "=" +
    email +
    "&" +
    "web" +
    "=" +
    web +
    "&" +
    "profilePic" +
    "=" +
    profilePic +
    "&" +
    "categoryId" +
    "=" +
    categoryId;

  var urlFinal = server_url + "/api/client/add?" + formData;

  fetch(urlFinal)
    .then((r) => r.json())
    .then((newClient) => {
      console.log("Added client: ", newClient);
    });
}
////////////////////////////////////// Selection //////////////////////////////////////
var clientsSelected = [];
function selectForDelete(event) {
  var clientCard = event.currentTarget.closest(".card");
  var h5Id = clientCard.querySelector(".cardId");
  var rawId = h5Id.innerHTML;
  var processedId = rawId.replace("#00", "");

  var idIndex = clientsSelected.indexOf(processedId);
  if (idIndex != -1) {
    clientsSelected.splice(idIndex);
    event.currentTarget.style.color = "";
  } else {
    clientsSelected.push(processedId);
    event.currentTarget.style.color = "red";
  }

  console.log(clientsSelected);
}
function deleteClients() {
  var urlFinal =
    server_url +
    "/api/client/delete?" +
    "idtodelete=" +
    clientsSelected.join(",");

  fetch(urlFinal)
    .then((r) => r.json())
    .then((clients) => {
      cleanList();
      fillList(clients);
      clientsSelected.splice(0, clientsSelected.length);
    });
}
////////////////////////////////////// Search Client //////////////////////////////////////
function searchClient() {
  var searchTerm = document.querySelector("#searchTerm").value;
  var urlFinal = server_url + "/api/client/search?" + getQueryVars(searchTerm);

  fetch(urlFinal)
    .then((r) => r.json())
    .then((clients) => {
      cleanList();
      fillList(clients);
    });
}
// Query Vars
function getQueryVars(searchTerm) {
  return "searchTerm=" + searchTerm;
}
////////////////////////////////////// Order by Last Name //////////////////////////////////////
function lastNameClientAsc() {
  var urlFinal = server_url + "/api/client/orderbylastnameasc";

  fetch(urlFinal)
    .then((r) => r.json())
    .then((clients) => {
      cleanList();
      fillList(clients);
    });
}
function lastNameClientDesc() {
  var urlFinal = server_url + "/api/client/orderbylastnamedesc";

  fetch(urlFinal)
    .then((r) => r.json())
    .then((clients) => {
      cleanList();
      fillList(clients);
    });
}
////////////////////////////////////// Order by ID //////////////////////////////////////
function idClientAsc() {
  var urlFinal = server_url + "/api/client/orderbyidasc";

  fetch(urlFinal)
    .then((r) => r.json())
    .then((clients) => {
      cleanList();
      fillList(clients);
    });
}
function idClientDesc() {
  var urlFinal = server_url + "/api/client/orderbyiddesc";

  fetch(urlFinal)
    .then((r) => r.json())
    .then((clients) => {
      cleanList();
      fillList(clients);
    });
}
////////////////////////////////////// Clean Client //////////////////////////////////////
function cleanList(event) {
  document.querySelector("#clientsContainer").innerHTML = "";
}
////////////////////////////////////// Fill Client //////////////////////////////////////
function fillList(clients) {

    // Creating the profile picture container










}

/*
<div class="card mb-3" style="max-width: 540px;">
  <div class="row no-gutters">
    <div class="col-md-4">
      <img src="..." alt="...">
    </div>
    <div class="col-md-8">
      <div class="card-body">
        <h5 class="card-title">Card title</h5>
        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
      </div>
    </div>
  </div>
</div>
*/