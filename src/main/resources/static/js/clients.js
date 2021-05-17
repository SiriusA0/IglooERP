/* Coded with love by Igloo team. */
$(".toast").toast();
// Server
var server_url = "http://localhost:8080/";
var search_url = server_url + "/api/client/get?";
////////// Global VARs //////////
var currentPageGlobal = 1; // Current invoices page

////////// Pagination //////////
function nextPage() {
  var finalRequest = "";
  currentPage = parseInt(currentPageGlobal);
  if (currentPage > 0) {
    var nextPage = currentPage + 1;
    if (search_url.indexOf("&") != -1) {
      search_url = search_url.split("&page")[0];
      var auxUrl = search_url;
      finalRequest = auxUrl + "&page=" + nextPage;
    } else {
      search_url = search_url.split("page")[0];
      var auxUrl = search_url;
      finalRequest = auxUrl + "page=" + nextPage;
    }
    fetchRequest(finalRequest, null);
    currentPageGlobal = nextPage;
    updatePages(nextPage);
  }
}
function prevPage() {
  var finalRequest = "";
  currentPage = parseInt(currentPageGlobal);
  if (currentPage > 0) {
    var prevPage = currentPage - 1;
    if (prevPage < 1) {
      var prevPage = currentPage;
    }
    if (search_url.indexOf("&") != -1) {
      search_url = search_url.split("&page")[0];
      var auxUrl = search_url;
      finalRequest = auxUrl + "&page=" + prevPage;
    } else {
      search_url = search_url.split("page")[0];
      var auxUrl = search_url;
      finalRequest = auxUrl + "page=" + prevPage;
    }
    fetchRequest(finalRequest, null);
    currentPageGlobal = prevPage;
    updatePages(prevPage);
  }
}

function updatePages(currentPage) {
  currentPageInt = parseInt(currentPage);
  var pageIndicators = document.querySelector("#paginationControls");
  pageIndicators.querySelector("#currentPageItem").innerHTML = currentPageInt;
  pageIndicators.querySelector("#currentPageItem").style.color = "#59bec9";
}
////////// Fetch //////////
function fetchRequest(finalRequest,toast, globalSearch) { //toast
  if (globalSearch) {
    search_url = finalRequest;
    console.log("Search url:" + search_url);
  }
  console.log("Fetch request to: " + finalRequest);
  fetch(finalRequest)
    .then((r) => r.json())
    .then((clients) => {
      cleanList();
      fillList(clients);
    /*  if (toast != null) {
        $(document).ready(function () {
          {
            $(toast).toast("show");
          } 
        });
      } */
    });
  //resetSelectedList();
}
////////////////////////////////////// Load all clients //////////////////////////////////////
/* function showClients() {
  fetch("http://localhost:8080/api/client/show")
    .then((r) => r.json())
    .then((clients) => {
      cleanList();
      fillList(clients);
    });
} */

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
  var type = document.querySelector("input[name=typeOfClient]").value;
  var clientName = document.querySelector("input[name=clientName]").value;
  clientName = clientName.split(" ");
  var firstName = clientName[0];
  var lastName = clientName[1];
  var streetLine1 = document.querySelector("input[name=companyAdress1]").value;
  var streetLine2 = document.querySelector("input[name=companyAdress2]").value;
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
      cleanList();
      fillList(newClient);
      $(document).ready(function () {
        {
          $("#createToast").toast("show");
        }});
    });
    document.querySelector("#clientForm").style.display="none";



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

function favClient(event){

  if (event.currentTarget.className=="fas fa-star cardIcon"){
    event.currentTarget.className="far fa-star cardIcon"

  }
  else {
    event.currentTarget.className="fas fa-star cardIcon"
  }
  
  var clientInfo= event.currentTarget.closest(".clientInfo");
  var clientId = clientInfo.id;
  var urlToFav = server_url +
  "/api/client/favorite?" +
  "id=" +clientId;
  console.log(urlToFav);
  fetch(urlToFav)
  //.then((r) => r.json())
  .then(() => {
    //cleanList();
    //fillList(clients);

  });
}


/////////////////////////////Edit Client ////////////////////////////

function editClientForm(event){
  document.querySelector("#clientForm").style.display="none";
  document.querySelector("#clientEdit").style.display="";
  var clientInfo=event.currentTarget.closest(".clientInfo");
  var clientId=clientInfo.id;

  // Fetch request.
  fetch("/api/client/find?id=" + clientId)
    .then((r) => r.json())
    .then((clientToEdit) => {
      console.log("Selected client: ", clientToEdit);
      // Get invoice old atributes.
    /*   document.querySelector("input[name=clientName]").value = clientToEdit.firstname + "" + clientToEdit.lastname;
      document.querySelector("input[name=companyAdress1Edit]").value =  clientToEdit.companyAdress1;
      document.querySelector("input[name=phoneNumber1Edit]").value = clientToEdit.phoneNumber1
      document.querySelector("input[name=companyAdress2Edit]").value = clientToEdit.companyAdress2;
      document.querySelector("input[name=phoneNumber2Edit]").value = clientToEdit.phoneNumber2;
      document.querySelector("input[name=ZIPCodeEdit]").value =
      document.querySelector("input[name=emailEdit]").value =
      document.querySelector("input[name=webPageEdit]").value =
      document.querySelector("input[name=nifEdit]").value =
      document.querySelector("input[name=profilePicEdit]").value =
 */ 
      /*  document.querySelector("select[name=clientIdEdited]").getElementsByTagName("option")[
        invoiceToEdit.client.id - 1
      ].selected = "selected";
      console.log(document.querySelector("select[name=statusIdEdited]").getElementsByTagName("option"));
      document.querySelector("select[name=statusIdEdited]").getElementsByTagName("option")[
        invoiceToEdit.status.id - 1
      ].selected = "selected"; */
    });
}


function cancelEditClient(){
  document.querySelector("#clientEdit").style.display="none";

}
////////////////////////////////////// Get Client //////////////////////////////////////

function getClients(action, sortTerm, sortMethod, resetPage) {
  if (resetPage) {
    currentPageGlobal = 1;
    let currentPage = currentPageGlobal;
    updatePages(currentPage);
  }
  // Request Definition.
  var request = server_url + "/api/client/get";
  var finalRequest;
  // Action selector

  switch (action) {
	
    case "sort": // Sort request
      // Sort Definition.
      sortRequest = request + "?action=sort&";
      // Request Parameters.
      finalRequest = sortRequest + "option=" + sortMethod + "&" + "term=" + sortTerm;
      break;

    case "searchClient": // Search by request client
      // Search Definition.
      var searchRequest = request + "?action=search&";
      // Request Parameter.
      var searchTerm = document.querySelector("#searchTerm").value;
      finalRequest = searchRequest + "option=client&term=" + searchTerm;
      break;

    case "searchFavorites": {
 		// Search favorite clients
	    // Search Definition.
	    var searchRequest = request + "?action=search";
	     // Request Parameter.
	     finalRequest = searchRequest + "&option=favorite";
   		 break;
   	}

    default:
      // Simple get all request
      finalRequest = request;
  }
  // Fetch request.
  console.log(finalRequest);
  fetchRequest(finalRequest, null, true);
}

///////////////////////////////////////////// Method DELETE //////////
function markForDelete(event) {
  console.log("Selecting invoice");
  var selectedInvoiceRow = event.currentTarget.closest("tr");
  selectedInvoiceId = selectedInvoiceRow.querySelector(".idIndicator").innerHTML;
  console.log("Selected order: " + selectedInvoiceId);
}
function deleteInvoice() {
  if (selectedInvoiceId == null) {
    console.log("Error");
  } else {
    // Request Definition.
    var request = server_url + "/api/invoice/delete?";
    var finalRequest = "";
    // Find selected Invoice ID
    var deleteRequest = request + "id=" + selectedInvoiceId;
    finalRequest = deleteRequest;
    var auxUrl = search_url;
    auxUrl = auxUrl.split("?");

    if (auxUrl[1].length > 0) {
      finalRequest = finalRequest + "&page=" + currentPageGlobal + "&" + auxUrl[1];
    } else {
      finalRequest = finalRequest + "&page=" + currentPageGlobal;
    }
    console.log(finalRequest);
    fetchRequest(finalRequest, "#deleteToast", false);
    selectedInvoiceId = null;
  }
}

////////////////////////////////////// Clean Client //////////////////////////////////////
function cleanList(event) {
  document.querySelector("#clientsContainer").innerHTML = "";
}
////////////////////////////////////// Fill Client //////////////////////////////////////
function fillList(clients) {

   if (clients.length == 0) {
    currentPageGlobal = currentPageGlobal - 1;
    document.querySelector("#nextPage").disabled = true;
    var afterTableContainer = document.querySelector("#afterTableContainer");
    afterTableContainer.innerHTML = "";
    var afterTableContainer = document.querySelector("#afterTableContainer");
    var noMoreResultAlert = document.createElement("div");
    noMoreResultAlert.className = "alert alert-dark";
    noMoreResultAlert.role = "alert alert";
    noMoreResultAlert.innerHTML = "No more results to show";
    afterTableContainer.appendChild(noMoreResultAlert);
  } else {
    var afterTableContainer = document.querySelector("#afterTableContainer");
    afterTableContainer.innerHTML = "";
    document.querySelector("#nextPage").disabled = false;
  } 


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
      favIcon.id="editIcon";
      favIcon.setAttribute("type", "button");
      //favIcon.className = "far fa-star cardIcon";
      //change color of favorites clients...
      
      if (clients[i].favorite==true){
        favIcon.className= "fas fa-star cardIcon"
      }
      else {
        favIcon.className= "far fa-star cardIcon"
      }
      favIcon.addEventListener("click", function (event) {favClient(event)});
      favContainer.appendChild(favIcon);

      var editContainer = document.createElement("div");
      editContainer.className = "col-1";
      cardIcons.appendChild(editContainer);
      var editIcon = document.createElement("i");
      editIcon.setAttribute("type", "button");
      editIcon.addEventListener("click", function (event) {editClientForm(event)}); 
      editIcon.className = "fas fa-edit  cardIcon";
      editContainer.appendChild(editIcon);

      
      var binContainer = document.createElement("div");
      binContainer.className = "col-1";
      cardIcons.appendChild(binContainer);
      var binIcon = document.createElement("i");
      binIcon.setAttribute("type", "button");
      binIcon.className = "fas fa-trash-alt cardIcon deleteButton";
      binIcon.setAttribute("data-toggle", "modal");
      binIcon.setAttribute("data-target", "#deleteModal");
      binIcon.addEventListener("click", function (event) {deleteClientModal(event)});
      binContainer.appendChild(binIcon);
      cardSecondCol.appendChild(cardIcons);

    
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