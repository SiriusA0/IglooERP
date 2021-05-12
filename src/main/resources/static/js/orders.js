/* Coded with love by Igloo team. */
////////// Diary of a backend, frontend hater, developer  //////////
// All orders / sort - request : /api/order/get
// Variable "action":
// if get -> bring all orders from server
// if sort -> bring orders sorted by: "amount" or "date"
// Create order - request : /api/order/add

////////// Server URL //////////
var server_url = "http://localhost:8080";

////////// Method GET //////////
// Example sort: getOrders("sort","amount","asc")
// Example search: getOrders("search","name",null)
function getOrders(action, sortTerm, sortMethod) {
  // Request Definition.
  var request = server_url + "/api/order/get";
  var finalRequest;
  // Action selector
  switch (action) {
    case "sort": // Sort request /api/order/get?action=sort&sortmethod=asc&sortterm=amount
      // Sort Definition.
      sortRequest = request + "?action=sort&";
      // Request Parameters.
      finalRequest = sortRequest + "sortmethod=" + sortMethod + "&" + "sortterm=" + sortTerm;
      break;

    case "searchClient": // Search by request client /api/order/get?action=search&searchtermclient=clientname
      // Search Definition.
      var searchRequest = request + "?action=search&";
      // Request Parameter.
      var searchTerm = document.querySelector("#searchTermClient").value;
      finalRequest = searchRequest + "searchtermclient=" + searchTerm;
      break;

    case "searchAgent": // Search by request agent  /api/order/get?action=search&searchtermagent=agentname
      // Search Definition.
      var searchRequest = request + "?action=search&";
      // Request Parameter.
      var searchTerm = document.querySelector("#searchTermAgent").value;
      finalRequest = searchRequest + "searchtermagent=" + searchTerm;
      break;

    default:
      // /api/order/get findAll
      // Simple get all request
      finalRequest = request;
  }
  // Fetch request.
  fetch(finalrequest)
    .then((r) => r.json())
    .then((orders) => {
      cleanTable();
      fillTable(orders);
    });
}
////////// Show CREATE form //////////
function showCreateForm() {
  if (document.querySelector("#orderForm").style.display == "none") {
    document.querySelector("#orderForm").style.display = "";
  } else {
    document.querySelector("#successAlert").style.display = "none";
    document.querySelector("#orderForm").style.display = "none";
  }
}
////////// Method CREATE //////////
function createOrder() {
  // Request Definition.
  var request = server_url + "/api/order/add?";
  var finalRequest;
  // Get order atributes.
  var totalAmount = document.querySelector("input[name=totalAmount").value;
  var clientId = document.querySelector("select[name=clientId").value;
  var agentId = document.querySelector("select[name=agentId").value;
  var statusId = document.querySelector("select[name=statusId").value;
  var sectorId = document.querySelector("select[name=sectorId").value;

  var creationRequest =
    request +
    "totalamount=" +
    totalAmount +
    "&clientdd=" +
    clientId +
    "&agentid=" +
    agentId +
    "&statusid=" +
    statusId +
    "&sectorid=" +
    sectorId;

  finalRequest = creationRequest;

  // Fetch request.
  fetch(finalrequest)
    .then((r) => r.json())
    .then((neworder) => {
      console.log("Added order: " + neworder.id);
    });

  //Clean form
  document.querySelector("input[name=totalAmount").value = "";
  document.querySelector("select[name=clientId").value = "";
  document.querySelector("select[name=agentId").value = "";
  document.querySelector("select[name=statusId").value = "";
  document.querySelector("select[name=sectorId").value = "";
}
////////// Method CREATE //////////
function editOrder(event) {
  // Request Definition.
  var request = server_url + "/api/order/add?";
  var finalRequest;

  // Get order atributes.
  var orderid = event.currentTarget.closest("input[class=card]").id;

  // Fetch request.
  fetch("/api/order/find?id=" + orderid)
    .then((r) => r.json())
    .then((orderToEdit) => {
      console.log("Selected order: " + orderToEdit.id);
    });

  // Get order old atributes.
  document.querySelector("input[name=totalAmount").value = orderToEdit.totalAmount;
  document.querySelector("select[name=clientId").value = orderToEdit.clientId;
  document.querySelector("select[name=agentId").value = orderToEdit.agentId;
  document.querySelector("select[name=statusId").value = orderToEdit.statusId;
  document.querySelector("select[name=sectorId").value = orderToEdit.sectorId;

  // Get order new atributes.
  var totalAmount = document.querySelector("input[name=totalAmount").value;
  var clientId = document.querySelector("select[name=clientId").value;
  var agentId = document.querySelector("select[name=agentId").value;
  var statusId = document.querySelector("select[ name=statusId").value;
  var sectorId = document.querySelector("select[ name=sectorId").value;

  var editRequest =
    request +
    "totalamount=" +
    totalAmount +
    "&clientdd=" +
    clientId +
    "&agentid=" +
    agentId +
    "&statusid=" +
    statusId +
    "&sectorid=" +
    sectorId;

  finalRequest = editRequest;

  // Fetch request.
  fetch(finalrequest)
    .then((r) => r.json())
    .then((neworder) => {
      console.log("Edited order: " + neworder.id);
    });

  //Clean form
  document.querySelector("input[name=totalAmount").value = "";
  document.querySelector("select[name=clientId").value = "";
  document.querySelector("select[name=agentId").value = "";
  document.querySelector("select[name=statusId").value = "";
  document.querySelector("select[name=sectorId").value = "";
}
////////// Method DELETE //////////
function deleteOrder(event) {
  // Request Definition.
  var request = server_url + "/api/order/delete?";
  var finalRequest = "";
  // Get order atributes.
  var orderid = event.currentTarget.closest("input[class=card]").id;

  var deleteRequest = request + "orderid=" + orderid;

  finalRequest = deleteRequest;

  // Fetch request.
  fetch(finalrequest)
    .then((r) => r.json())
    .then((neworder) => {
      console.log("Added order: " + neworder.id);
    });
}
////////// Utilities //////////

function cleanTable() {
  document.querySelector("#orderList").innerHTML = "";
}
function fillTable(orders) {
  for (i in orders) {
    // Row
    var row = document.createElement("tr");

    // Selection Column
    var selectionCol = document.createElement("td");
    var checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    selectionCol.appendChild(checkbox);
    row.appendChild(selectionCol);

    //  ID Column
    var idCol = document.createElement("td");
    idColContent.className = "filterTextDark";
    idColContent.innerHTML = orders[i].id;
    idCol.appendChild(idColContent);
    row.appendChild(idCol);

    //  Date Column
    var dateCol = document.createElement("td");
    dateColContent.className = "filterTextDark";
    dateColContent.innerHTML = orders[i].creationDate;
    dateCol.appendChild(dateColContent);
    row.appendChild(dateCol);

    //  Client Column
    var clientCol = document.createElement("td");
    clientColContent.className = "filterTextDark";
    clientColContent.innerHTML = orders[i].client;
    clientCol.appendChild(clientColContent);
    row.appendChild(clientCol);

    //  Agent Column
    var agentCol = document.createElement("td");
    agentColContent.className = "filterTextDark";
    agentColContent.innerHTML = orders[i].agent;
    agentCol.appendChild(agentColContent);
    row.appendChild(agentCol);

    //  Total Amount Column
    var totalAmountCol = document.createElement("td");
    totalAmountColContent.className = "filterTextDark";
    totalAmountColContent.innerHTML = orders[i].totalAmount;
    totalAmountCol.appendChild(totalAmountColContent);
    row.appendChild(totalAmountCol);

    //  Total Status Column
    var statusCol = document.createElement("td");
    statusColContent.className = "filterTextDark";
    statusColContent.innerHTML = orders[i].status;
    statusCol.appendChild(statusColContent);
    row.appendChild(statusCol);

    //  Actions Column
    var actionsCol = document.createElement("td");
    actionsColContent.className = "filterTextDark";

    var favbutton = document.createElement("button");
    favbutton.type = "button";
    favbutton.className = "btn btn-secondary";
    var favIcon = document.createElement("i");
    favIcon.className = "far fa-star";
    favIcon.addEventListener("click", function (event) {
      favoriteOrder(event);
    });
    favbutton.appendChild(favIcon);
    actionsColContent.appendChild(favbutton);

    var editbutton = document.createElement("button");
    editbutton.type = "button";
    editbutton.className = "btn btn-secondary";
    var editIcon = document.createElement("i");
    editIcon.className = "fas fa-edit";
    editIcon.addEventListener("click", function (event) {
      editOrder(event);
    });
    editbutton.appendChild(editIcon);
    actionsColContent.appendChild(editbutton);

    var deletebutton = document.createElement("button");
    deletebutton.type = "button";
    deletebutton.className = "btn btn-secondary";
    var deleteIcon = document.createElement("i");
    deleteIcon.className = "fas fa-trash-alt";
    favIcon.addEventListener("click", function (event) {
      deleteOrder(event);
    });
    deletebutton.appendChild(deleteIcon);
    actionsColContent.appendChild(deletebutton);

    actionsCol.appendChild(actionsColContent);
    row.appendChild(actionsCol);
  }
}
