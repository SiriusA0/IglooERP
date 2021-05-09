// Server
var server_url = 'http://localhost:8080/';
// Add Agent Button
function addAgent() {

    if (document.querySelector("#agentForm").style.display == "none") {
        document.querySelector("#agentForm").style.display = "";
    } else {
        document.querySelector("#agentForm").style.display = "none";
    }

}
// Search Agent Button
function searchAgent() {

    var searchTerm = document.querySelector("#searchTerm").value;
    var urlFinal = server_url + '/api/agent/search?' + getQueryVars(searchTerm);

    fetch(urlFinal)
        .then(r => r.json())
        .then(agents => {
            cleanList();
            fillList(agents);
        });

}
// Order by Last Name
function lastNameAgentAsc() {

    var urlFinal = server_url + '/api/agent/orderbylastnameasc';

    fetch(urlFinal)
        .then(r => r.json())
        .then(agents => {
            cleanList();
            fillList(agents);
        });

}
function lastNameAgentDesc() {

    var urlFinal = server_url + '/api/agent/orderbylastnamedesc';

    fetch(urlFinal)
        .then(r => r.json())
        .then(agents => {
            cleanList();
            fillList(agents);
        });

}
// Order by ID
function idAgentAsc() {

    var urlFinal = server_url + '/api/agent/orderbyidasc';

    fetch(urlFinal)
        .then(r => r.json())
        .then(agents => {
            cleanList();
            fillList(agents);
        });

}
function idAgentDesc() {

    var urlFinal = server_url + '/api/agent/orderbyiddesc';

    fetch(urlFinal)
        .then(r => r.json())
        .then(agents => {
            cleanList();
            fillList(agents);
        });

}
// Clean Agent
function cleanList(event) {

    document.querySelector("#agentsContainer").innerHTML = "";

}
// Fill Agent
function fillList(agents) {

    var agentsContainer = document.querySelector("#agentsContainer");

    for (i in agents) {

        // Agent Card

        var agentCol = document.createElement("div");
        agentCol.className = "col mb-4";

        var agentCard = document.createElement("div");
        agentCard.className = "card h-90 w-80";

        // Card Content

        // Icons

        var cardIcons = document.createElement("div");
        cardIcons.className = "row pt-2 pl-2 pr-2 d-flex justify-content-between";

        var favContainer = document.createElement("div");
        favContainer.className = "col-3";

        var favIcon = document.createElement("i");
        favIcon.type = "button";
        favIcon.className = "far fa-star cardIcon";

        var binContainer = document.createElement("div");
        binContainer.className = "col-3";

        var binIcon = document.createElement("i");
        binIcon.type = "button";
        binIcon.className = "fas fa-trash-alt d-flex justify-content-end cardIcon";

        // Appends

        favContainer.appendChild(favIcon);
        binContainer.appendChild(binIcon);
        cardIcons.appendChild(favContainer);
        cardIcons.appendChild(binContainer);

        // Img and text

        var cardContent = document.createElement("div");
        cardContent.className = "row";

        var cardImg = document.createElement("img");
        cardImg.className = "card-img-top";
        cardImg.src = agents[i].profilePic;

        var cardBody = document.createElement("div");
        cardBody.className = "card-body";

        var cardName = document.createElement("h5");
        cardName.className = "card-title";
        cardName.innerHTML = agents[i].firstName + " " + agents[i].lastName;

        var cardEmail = document.createElement("p");
        cardEmail.className = "card-text";
        cardEmail.innerHTML = agents[i].email;

        // Appends

        cardBody.appendChild(cardName);
        cardBody.appendChild(cardEmail);

        cardContent.appendChild(cardImg);
        cardContent.appendChild(cardBody);

        // Agent Card Appends

        agentCard.appendChild(cardIcons);
        agentCard.appendChild(cardContent);

        agentCol.appendChild(agentCard);

        agentsContainer.appendChild(agentCol);
    }

}
// Query Vars
function getQueryVars(searchTerm) {

    return 'searchTerm=' + searchTerm;
}
