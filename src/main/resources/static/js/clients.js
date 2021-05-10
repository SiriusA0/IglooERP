// Server
var server_url = 'http://localhost:8080/';
// Add Client Button
function addClient() {

    if (document.querySelector("#clientForm").style.display == "none") {
        document.querySelector("#clientForm").style.display = "";
    } else {
        document.querySelector("#successAlert").style.display = "none";
        document.querySelector("#clientForm").style.display = "none";
    }

}
// FindAll clients
function showClients() {

    fetch("http://localhost:8080/api/client/show")
        .then(r => r.json())
        .then(clients => {
            cleanList();
            fillList(clients);
        });
}

// Create Client, Confirm client button
function createClient() {
    var firstName = document.querySelector("#firstName").value;
    var lastName = document.querySelector("#lastName").value;
    var email = document.querySelector("#email").value;
    var profilePic = document.querySelector("#profilePic").value;
    var urlFinal = server_url + '/api/client/add?' + "firstName=" + firstName + "&" + "lastName=" + lastName + "&" + "email=" + email + "&" + "profilePic=" + profilePic;

    fetch(urlFinal)
        .then(r => r.json())
        .then(newClient => {
            console.log("Added client: " + newClient.firstName);
            console.log(newClient.lastName);
            console.log(newClient.id);
        });

    document.querySelector("#firstName").value = "";
    document.querySelector("#lastName").value = "";
    document.querySelector("#email").value = "";
    document.querySelector("#profilePic").value = "";
    document.querySelector("#successAlert").style.display = "";

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
    }
    else {
        clientsSelected.push(processedId);
        event.currentTarget.style.color = "red";
    }

    console.log(clientsSelected);

}
function deleteClients() {

    var urlFinal = server_url + '/api/client/delete?' + "idtodelete=" + clientsSelected.join(',');

    fetch(urlFinal)
        .then(r => r.json())
        .then(clients => {
            cleanList();
            fillList(clients);
            clientsSelected.splice(0, clientsSelected.length);
        });
}
// Search Client Button
function searchClient() {

    var searchTerm = document.querySelector("#searchTerm").value;
    var urlFinal = server_url + '/api/client/search?' + getQueryVars(searchTerm);

    fetch(urlFinal)
        .then(r => r.json())
        .then(clients => {
            cleanList();
            fillList(clients);
        });

}
// Order by Last Name
function lastNameClientAsc() {

    var urlFinal = server_url + '/api/client/orderbylastnameasc';

    fetch(urlFinal)
        .then(r => r.json())
        .then(clients => {
            cleanList();
            fillList(clients);
        });

}
function lastNameClientDesc() {

    var urlFinal = server_url + '/api/client/orderbylastnamedesc';

    fetch(urlFinal)
        .then(r => r.json())
        .then(clients => {
            cleanList();
            fillList(clients);
        });

}
// Order by ID
function idClientAsc() {

    var urlFinal = server_url + '/api/client/orderbyidasc';

    fetch(urlFinal)
        .then(r => r.json())
        .then(clients => {
            cleanList();
            fillList(clients);
        });

}
function idClientDesc() {

    var urlFinal = server_url + '/api/client/orderbyiddesc';

    fetch(urlFinal)
        .then(r => r.json())
        .then(clients => {
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
        binIcon.addEventListener("click", function (event) { selectForDelete(event) });

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

    return 'searchTerm=' + searchTerm;
}
