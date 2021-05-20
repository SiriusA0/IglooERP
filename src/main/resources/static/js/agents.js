/* Coded with love by Igloo team. */
$(".toast").toast();
// Server
var server_url = "http://localhost:8080/";
var search_url = server_url + "/api/agent/get?";
////////// Global VARs //////////
var currentPageGlobal = 1; // Current invoices page
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
      fetchRequest(finalRequest);
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
      fetchRequest(finalRequest);
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
function fetchRequest(finalRequest, globalSearch) {
    //toast
    if (globalSearch) {
      search_url = finalRequest;
      console.log("Search url:" + search_url);
    }
    console.log("Fetch request to: " + finalRequest);
    fetch(finalRequest)
      .then((r) => r.json())
      .then((agents) => {
        cleanList();
        fillList(agents);
    
      });

  }
  function buttonFn(event, action, sortTerm, sortMethod, resetPage) {
    var aux = document.querySelectorAll(".lateralMenuAux");
    console.log(aux);
    for (var i = 0; i < aux.length; i++) {
      aux[i].style.color = "rgb(255, 255, 255)";
    }
  
    if (event != null) {
      var rowButton = event.currentTarget.closest(".row");
      var ico = rowButton.querySelector("i");
      var lab = rowButton.querySelector("label");
      if (ico.style.color == "rgb(89, 190, 201)" && lab.style.color == "rgb(89, 190, 201)") {
      } else if (ico.style.color == "rgb(255, 255, 255)" && lab.style.color == "rgb(255, 255, 255)") {
        ico.style.color = "rgb(89, 190, 201)";
        lab.style.color = "rgb(89, 190, 201)";
      }
    }
    getAgents(action, sortTerm, sortMethod, resetPage);
  }
////////////////////////////////////// Show Agent form //////////////////////////////////////
function addAgent() {

    if (document.querySelector("#agentForm").style.display == "none") {
        document.querySelector("#agentForm").style.display = "";
        document.querySelector("#agentEditForm").style.display = "none";
    } else {
        document.querySelector("#agentForm").style.display = "none";
    }

}

function showAgents() {

    fetch("http://localhost:8080/api/agent/show")
        .then(r => r.json())
        .then(agents => {
            cleanList();
            fillList(agents);
        });
}

/////////////////////////////////////// Create Agent ////////////////////
function createAgent() {
    var firstName = document.querySelector("#firstName").value;
    var lastName = document.querySelector("#lastName").value;
    var email = document.querySelector("#email").value;
    var profilePic = document.querySelector("#profilePic").value;
    var urlFinal = server_url + '/api/agent/add?' + "firstName=" + firstName + "&" + "lastName=" + lastName + "&" + "email=" + email + "&" + "profilePic=" + profilePic;

    fetch(urlFinal)
        .then(r => r.json())
        .then(newAgent => {
            console.log("Added agent: " + newAgent.firstName);
            console.log(newAgent.lastName);
            console.log(newAgent.id);
        });

    document.querySelector("#firstName").value = "";
    document.querySelector("#lastName").value = "";
    document.querySelector("#email").value = "";
    document.querySelector("#profilePic").value = "";
    
}
function cancelCreateAgent() {
    document.querySelector("#agentForm").style.display = "none";
  }

/////////////////////////////Edit Client ////////////////////////////
var selectedAgentID = "";
function editAgent(event) {
        console.log("hola")
        document.querySelector("#agentEditForm").style.display="";
        document.querySelector("#agentForm").style.display = "none";
        var agentCard = event.currentTarget.closest(".card");
        var h5Id = agentCard.querySelector(".cardId");
        var rawId = h5Id.innerHTML;
        var processedId = rawId.replace("#00", "");
        selectedAgentID= processedId;

  // Fetch request.
   fetch("/api/agent/find?id=" + processedId)
    .then((r) => r.json())
    .then((agentToEdit) => { 
      // Get agent old atributes. 
       document.querySelector("#firstNameEdit").value = agentToEdit.firstName;
      document.querySelector("#lastNameEdit").value = agentToEdit.lastName;
      document.querySelector("#emailEdit").value = agentToEdit.email;
      document.querySelector("#profilePicEdit").value = agentToEdit.profilePic;  
    });

    //var urlFinal = server_url + '/api/agent/add?id=" + agentId + "&"; + "firstName=" + firstName + "&" + "lastName=" + lastName + "&" + "email=" + email + "&" + "profilePic=" + profilePic;
}

function editAgentConfirm() {

  var firstName=document.querySelector("#firstNameEdit").value ;
  var lastName=document.querySelector("#lastNameEdit").value;
  var email=document.querySelector("#emailEdit").value;
  var profilePic=document.querySelector("#profilePicEdit").value ;

  var agentId = selectedAgentID;
 //Request Definition.
   var request = server_url + "/api/agent/add?id=" + agentId + "&";
    var editData = request + "firstName=" + firstName +
     "&" + "lastName=" + 
     lastName + "&" + 
     "email=" + 
     email + "&" + 
     "profilePic=" + 
     profilePic;
  
     finalRequest = editData;

     var auxUrl = search_url;
  auxUrl = auxUrl.split("?");
  if (auxUrl[1].length > 0) {
    finalRequest = finalRequest + "&page=" + currentPageGlobal + "&" + auxUrl[1];
  } else {
    finalRequest = finalRequest + "&page=" + currentPageGlobal;
  }

  fetchRequest(finalRequest);

  document.querySelector("#agentEditForm").style.display = "none";

  $(document).ready(function () {
    {
      $("#editToast").toast("show");
    }
  });

}

function cancelEditAgent() {
  document.querySelector("#agentEditForm").style.display="none";

} 


//////////////////////////////// delete Agents /////////////////////////////////////

var urlToDelete = "";
function deleteAgentModal(event) {

    var agentCard = event.currentTarget.closest(".card");
    var h5Id = agentCard.querySelector(".cardId");
    var rawId = h5Id.innerHTML;
    var processedId = rawId.replace("#00", "");

  urlToDelete = server_url + "/api/agent/delete?" + "id=" + processedId;
}

function deleteAgent() {
  var toDelete = urlToDelete;
  console.log(toDelete)
  fetch(toDelete)
    .then((r) => r.json())
    .then((agents) => {
      if (agents.length == 0) {
        $(document).ready(function () {
          {
            $("#deleteToastFail").toast("show");
          }
        });
      } else {
        cleanList();
        fillList(agents);
        $(document).ready(function () {
          {
            $("#deleteToast").toast("show");
          }
        });
      }
    });
}
///////////////////////////////////////// FAV Agent ////////////////////////////////////////////////////////////////////////////
function favAgent(event) {
    if (event.currentTarget.className == "fas fa-star cardIcon") {
      event.currentTarget.className = "far fa-star cardIcon";
    } else {
      event.currentTarget.className = "fas fa-star cardIcon";
    }
  
    var agentCard = event.currentTarget.closest(".card");
    var h5Id = agentCard.querySelector(".cardId");
    var rawId = h5Id.innerHTML;
    var processedId = rawId.replace("#00", "");
    var urlToFav = server_url + "/api/agent/favorite?" + "id=" + processedId;
    
    fetch(urlToFav)
      //.then((r) => r.json())
      .then(() => {
        //cleanList();
        //fillList(clients);
      });
  }

////////////////////////////////////// Get Agent //////////////////////////////////////

function getAgents(action, sortTerm, sortMethod, resetPage) {
    if (resetPage) {
      currentPageGlobal = 1;
      let currentPage = currentPageGlobal;
      updatePages(currentPage);
    }
    // Request Definition.
    var request = server_url + "/api/agent/get";
    var finalRequest;
    // Action selector
  
    switch (action) {
      case "sort": // Sort request
        // Sort Definition.
        sortRequest = request + "?action=sort&";
        // Request Parameters.
        finalRequest = sortRequest + "option=" + sortMethod + "&" + "term=" + sortTerm;
        break;
  
      case "searchAgent": // Search by request agent
        // Search Definition.
        var searchRequest = request + "?action=search&";
        // Request Parameter.
        var searchTerm = document.querySelector("#searchTerm").value;
        finalRequest = searchRequest + "option=agent&term=" + searchTerm;
        break;
  
      case "searchFavorites": {
        // Search favorite Agents
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
    fetchRequest(finalRequest, true);
  }

//////////////////////////////// Clean  Agents Cards /////////////////////////////////////////
function cleanList() {
    document.querySelector("#agentsContainer").innerHTML = "";

}
//////////////////////////////// Fill Agents Cards /////////////////////////////////////////
function fillList(agents) {

    if (agents.length == 0) {
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
    
    var agentsContainer = document.querySelector("#agentsContainer");
    for (i in agents) {

        // Agent Card
        var agentCol = document.createElement("div");
        agentCol.className = "col mb-4";

        var agentCard = document.createElement("div");
        agentCard.className = "card h-80 w-70";

        // Card Content

        // Icons

        var cardIcons = document.createElement("div");
        cardIcons.className = "row pt-2 pl-2 pr-2 d-flex justify-content-between";

        var favContainer = document.createElement("div");
        favContainer.className = "col-2 d-flex justify-content-start";
        var favIcon = document.createElement("i");
        favIcon.setAttribute("type", "button");
        if (agents[i].favorite == true) {
            favIcon.className = "fas fa-star cardIcon";
          } else {
            favIcon.className = "far fa-star cardIcon";
          }
          favIcon.addEventListener("click", function (event) {
            favAgent(event);
          });
        
        var editContainer = document.createElement("div");
        editContainer.className ="col-2 offset-6 d-flex justify-content-end";
        var editIcon = document.createElement("i");
        editIcon.setAttribute("type", "button");
        editIcon.className = "fas fa-edit cardIcon";
        editIcon.addEventListener("click", function (event) {editAgent(event)});
    

        var binContainer = document.createElement("div");
        binContainer.className = "col-2 d-flex justify-content-end";
        var binIcon = document.createElement("i");
        binIcon.setAttribute("type", "button");
        binIcon.className = "fas fa-trash-alt cardIcon";
        binIcon.addEventListener("click", function (event) {deleteAgentModal(event)});
        binIcon.setAttribute("data-toggle", "modal");
        binIcon.setAttribute("data-target", "#deleteModal");

        // Appends

        favContainer.appendChild(favIcon);
        editContainer.appendChild(editIcon);
        binContainer.appendChild(binIcon);
        cardIcons.appendChild(favContainer);
        cardIcons.appendChild(editContainer);
        cardIcons.appendChild(binContainer);

        // Img and text

        var cardContent = document.createElement("div");
        cardContent.className = "row";

        var cardContentCol = document.createElement("div");
        cardContentCol.className = "col";

        var containerImg = document.createElement("div");
        containerImg.className = "agentProfilePic";

        var cardImg = document.createElement("img");
        cardImg.className = "card-img-top";
        cardImg.src = agents[i].profilePic;

        var cardBody = document.createElement("div");
        cardBody.className = "card-body";

        var cardBodyRow = document.createElement("div");
        cardBodyRow.className = "row";

        var cardNameCol = document.createElement("div");
        cardNameCol.className = "col d-flex justify-content-between";

        var cardName = document.createElement("h5");
        cardName.className = "card-title";
        cardName.innerHTML = agents[i].firstName + " " + agents[i].lastName;

        var cardId = document.createElement("h5");
        cardId.className = "card-title cardId";
        cardId.innerHTML = "#00" + agents[i].id;

        var cardEmail = document.createElement("p");
        cardEmail.className = "card-text";
        cardEmail.innerHTML = agents[i].email;


        // Appends

        cardNameCol.appendChild(cardName);
        cardNameCol.appendChild(cardId);

        cardBodyRow.appendChild(cardNameCol);
        

        cardBody.appendChild(cardBodyRow);

        cardBody.appendChild(cardEmail);

        containerImg.appendChild(cardImg);
        cardContentCol.appendChild(containerImg);
        cardContentCol.appendChild(cardBody);

        cardContent.appendChild(cardContentCol);

        // Agent Card Appends

        agentCard.appendChild(cardIcons);
        agentCard.appendChild(cardContent);

        agentCol.appendChild(agentCard);

        agentsContainer.appendChild(agentCol);
    }

}


