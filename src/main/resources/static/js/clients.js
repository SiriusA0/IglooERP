// Server
var server_url = "http://localhost:8080/";
// Load all clients
function showClients() {
  fetch("http://localhost:8080/api/client/show")
    .then((r) => r.json())
    .then((clients) => {
      cleanList();
      fillList(clients);
    });
}

// Show client form
function addClient() {
  if (document.querySelector("#clientForm").style.display == "none") {
    document.querySelector("#clientForm").style.display = "";
  } else {
    document.querySelector("#successAlert").style.display = "none";
    document.querySelector("#clientForm").style.display = "none";
  }
}

// Create Client
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
    "categoryId" +
    "=" +
    categoryId;

  var urlFinal = server_url + "/api/client/add?" + formData;

  fetch(urlFinal)
    .then((r) => r.json())
    .then((newClient) => {
      console.log("Added agent: " + newClient.firstName);
    });
}
// Selection
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
// Search Client Button
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
// Order by Last Name
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
// Order by ID
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
// Clean Client
function cleanList(event) {
  document.querySelector("#clientsContainer").innerHTML = "";
}
// Fill Client
function fillList(clients) {
  var clientsContainer = document.querySelector("#clientsContainer");

  for (i in clients) {
    // Client Card

    var clientCol = document.createElement("div");
    clientCol.className = "col mb-4";

    var clientCard = document.createElement("div");
    clientCard.className = "card h-90 w-80";

    // Card Content

    // Icons

    var cardIcons = document.createElement("div");
    cardIcons.className = "row pt-2 pl-2 pr-2 d-flex justify-content-between";

    var favContainer = document.createElement("div");
    favContainer.className = "col-3";

    var favIcon = document.createElement("i");
    favIcon.setAttribute("type", "button");
    favIcon.className = "far fa-star cardIcon";

    var binContainer = document.createElement("div");
    binContainer.className = "col-3";

    var binIcon = document.createElement("i");
    binIcon.setAttribute("type", "button");
    binIcon.className = "fas fa-trash-alt d-flex justify-content-end cardIcon";
    binIcon.addEventListener("click", function (event) {
      selectForDelete(event);
    });

    // Appends

    favContainer.appendChild(favIcon);
    binContainer.appendChild(binIcon);
    cardIcons.appendChild(favContainer);
    cardIcons.appendChild(binContainer);

    // Img and text

    var cardContent = document.createElement("div");
    cardContent.className = "row";

    var cardContentCol = document.createElement("div");
    cardContentCol.className = "col";

    var containerImg = document.createElement("div");
    containerImg.className = "clientProfilePic";

    var cardImg = document.createElement("img");
    cardImg.className = "card-img-top";
    cardImg.src = clients[i].profilePic;

    var cardBody = document.createElement("div");
    cardBody.className = "card-body";

    var cardBodyRow = document.createElement("div");
    cardBodyRow.className = "row";

    var cardNameCol = document.createElement("div");
    cardNameCol.className = "col-8";

    var cardIdCol = document.createElement("div");
    cardIdCol.className = "col-4";

    var cardName = document.createElement("h5");
    cardName.className = "card-title";
    cardName.innerHTML = clients[i].firstName + " " + clients[i].lastName;

    var cardId = document.createElement("h5");
    cardId.className = "card-title cardId";
    cardId.innerHTML = "#00" + clients[i].id;

    var cardEmail = document.createElement("p");
    cardEmail.className = "card-text";
    cardEmail.innerHTML = clients[i].email;

    // Appends

    cardNameCol.appendChild(cardName);
    cardIdCol.appendChild(cardId);

    cardBodyRow.appendChild(cardNameCol);
    cardBodyRow.appendChild(cardIdCol);

    cardBody.appendChild(cardBodyRow);

    cardBody.appendChild(cardEmail);

    containerImg.appendChild(cardImg);
    cardContentCol.appendChild(containerImg);
    cardContentCol.appendChild(cardBody);

    cardContent.appendChild(cardContentCol);

    // Client Card Appends

    clientCard.appendChild(cardIcons);
    clientCard.appendChild(cardContent);

    clientCol.appendChild(clientCard);

    clientsContainer.appendChild(clientCol);
  }
}
// Query Vars
function getQueryVars(searchTerm) {
  return "searchTerm=" + searchTerm;
}
