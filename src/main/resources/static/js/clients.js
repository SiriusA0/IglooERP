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
/* function deleteClients() {
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
} */
//////////////////////////////// delete Client /////////////////////////////////////


var urlToDelete="";
function deleteClientModal(event){
  var clientInfo= event.currentTarget.closest(".clientInfo");
  var clientId = clientInfo.id;
  urlToDelete =
    server_url +
    "/api/client/delete?" +
    "id=" +clientId;

  
}

function deleteClient(){

  fetch(urlToDelete)
    .then((r) => r.json())
    .then((clients) => {
      cleanList();
      fillList(clients);
      
    });

}

/////////////////////////////Edit Client ////////////////////////////
function editClient(){




  
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

  var cardContainer=document.querySelector("#clientsContainer");
  for (i in clients){
      var cardBox=document.createElement("div");
      cardBox.className="card mb-3";
      cardBox.style="max-width: 540px;"
      cardContainer.appendChild(cardBox);

      var cardRow=document.createElement("div");
      cardRow.className="row no-gutters";
      cardBox.appendChild(cardRow);

      var cardFirstCol=document.createElement("div");
      cardFirstCol.className="col-md-4";
      cardRow.appendChild(cardFirstCol);

      var cardcheckrow = document.createElement("div");
      cardcheckrow.className = "row pt-2 pl-4 pr-2";
      cardFirstCol.appendChild(cardcheckrow);
      var BodyCol1=document.createElement("div");
      BodyCol1.className="col-1";
      var col1Check=document.createElement("input");
      col1Check.setAttribute("type", "checkbox");
      cardcheckrow.appendChild(BodyCol1);
      BodyCol1.appendChild(col1Check);
      
      var divImg=document.createElement("div");
      divImg.className="clientProfilePic";

      var cardImg=document.createElement("img");
      cardImg.src=clients[i].profilePic;
      divImg.appendChild(cardImg);
      cardFirstCol.appendChild(divImg);

      var cardSecondCol=document.createElement("div");
      cardSecondCol.className="col-md-8";
      cardRow.appendChild(cardSecondCol);

      // ---- card icons
      // Icons

      var cardIcons = document.createElement("div");
      cardIcons.className = "row pt-2 pl-2 pr-2 clientInfo";
      cardIcons.id=clients[i].id;
  
      var favContainer = document.createElement("div");
      favContainer.className = "col-1 offset-8";
      cardIcons.appendChild(favContainer);
      var favIcon = document.createElement("i");
      favIcon.setAttribute("type", "button");
      favIcon.className = "far fa-star cardIcon";
      favContainer.appendChild(favIcon);
      
      var editContainer = document.createElement("div");
      editContainer.className = "col-1";
      cardIcons.appendChild(editContainer);
      var editIcon = document.createElement("i");
      editIcon.setAttribute("type", "button");
      editIcon.className = "fas fa-edit  cardIcon";
      editContainer.appendChild(editIcon);

      
      var binContainer = document.createElement("div");
      binContainer.className = "col-1";
      cardIcons.appendChild(binContainer);
      var binIcon = document.createElement("i");
      binIcon.setAttribute("type", "button");
      binIcon.className = "fas fa-trash-alt cardIcon deleteButton";
      binContainer.appendChild(binIcon);
      binIcon.addEventListener("click", function (event) {deleteClientModal(event)}); 
      $(".deleteButton").attr("data-toggle", "modal");
      $(".deleteButton").attr("data-target", "#deleteModal");
      cardSecondCol.appendChild(cardIcons);
      ////


      var cardBody=document.createElement("div");
      cardBody.className="card-body";
      cardSecondCol.appendChild(cardBody);   

      var clientName=document.createElement("h5");
      clientName.className="card-title"
      clientName.innerHTML=clients[i].firstName + " " + clients[i].lastName
      cardBody.appendChild(clientName)
      
      var clientEmail=document.createElement("p");
      clientEmail.className="card-text";
      clientEmail.innerHTML=clients[i].email;
      cardBody.appendChild(clientEmail);

      var clientLocation=document.createElement("p");
      clientLocation.className="card-text"
      clientLocation.innerHTML=clients[i].city.name + ", " +  clients[i].country.name + "."
      cardBody.appendChild(clientLocation);
  }
}