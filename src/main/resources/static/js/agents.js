// Server
var server_url = 'http://localhost:8080/';
// Add Agent Button
function addAgent() {

    if (document.querySelector("#agentForm").style.display == "none") {
        document.querySelector("#agentForm").style.display = "";
    } else {
        document.querySelector("#successAlert").style.display = "none";
        document.querySelector("#agentForm").style.display = "none";
    }

}
// FindAll agents
function showAgents() {

    fetch("http://localhost:8080/api/agent/show")
        .then(r => r.json())
        .then(agents => {
            cleanList();
            fillList(agents);
        });
}

// Create Agent, Confirm agent button
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
    document.querySelector("#successAlert").style.display = "";

}
// Selection
var agentsSelected = [];
function selectForDelete(event) {

    var agentCard = event.currentTarget.closest(".card");
    var h5Id = agentCard.querySelector(".cardId");
    var rawId = h5Id.innerHTML;
    var processedId = rawId.replace("#00", "");

    var idIndex = agentsSelected.indexOf(processedId);
    if (idIndex != -1) {
        agentsSelected.splice(idIndex);
        event.currentTarget.style.color = "";
    }
    else {
        agentsSelected.push(processedId);
        event.currentTarget.style.color = "red";
    }

    console.log(agentsSelected);

}
function deleteAgents() {

    var urlFinal = server_url + '/api/agent/delete?' + "idtodelete=" + agentsSelected.join(',');

    fetch(urlFinal)
    .then(r => r.json())
    .then(agents => {
        cleanList();
        fillList(agents);
        agentsSelected.splice(0,agentsSelected.length);
    });
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
        favIcon.setAttribute("type","button");
        favIcon.className = "far fa-star cardIcon";

        var binContainer = document.createElement("div");
        binContainer.className = "col-3";

        var binIcon = document.createElement("i");
        binIcon.setAttribute("type","button");
        binIcon.className = "fas fa-trash-alt d-flex justify-content-end cardIcon";
        binIcon.addEventListener("click",function (event) {selectForDelete(event)});
      
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

        var cardBodyRow = document.createElement("div");
        cardBodyRow.className = "row";

        var cardNameCol = document.createElement("div");
        cardNameCol.className = "col-8";

        var cardIdCol = document.createElement("div");
        cardIdCol.className = "col-4";

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
        cardIdCol.appendChild(cardId);

        cardBodyRow.appendChild(cardNameCol);
        cardBodyRow.appendChild(cardIdCol);

        cardBody.appendChild(cardBodyRow);

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
