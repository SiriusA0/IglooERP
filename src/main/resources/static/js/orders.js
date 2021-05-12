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
      var searchTerm = document.querySelector("#searchTermClient").value;
      finalRequest = searchRequest + "option=client&term=" + searchTerm;
      break;

    case "searchAgent": // Search by request agent
      // Search Definition.
      var searchRequest = request + "?action=search&";
      // Request Parameter.
      var searchTerm = document.querySelector("#searchTermAgent").value;
      finalRequest = searchRequest + "option=agent&term=" + searchTerm;
      break;

    default:
      // /api/order/get findAll
      // Simple get all request
      finalRequest = request;
  }
  // Fetch request.
  fetch(finalRequest)
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
////////// Show EDIT form //////////
function showEditForm(event) {
  if (document.querySelector("#orderEditForm").style.display == "none") {
    document.querySelector("#orderEditForm").style.display = "";
    // Get order atributes.
    var orderrow = event.currentTarget.closest("tr");
    var orderid = orderrow.querySelector(".id").innerHTML;

    // Fetch request.
    fetch("/api/order/find?id=" + orderid)
      .then((r) => r.json())
      .then((orderToEdit) => {
        console.log("Selected order: " + orderToEdit.id);
      });

    // Get order old atributes.
    document.querySelector("input[name=totalAmountEdit").value = orderToEdit.totalAmount;
    document.querySelector("select[name=clientIdEdit").value = orderToEdit.clientId;
    document.querySelector("select[name=agentIdEdit").value = orderToEdit.agentId;
    document.querySelector("select[name=statusIdEdit").value = orderToEdit.statusId;
    document.querySelector("select[name=sectorIdEdit").value = orderToEdit.sectorId;
  } else {
    document.querySelector("#successEditAlert").style.display = "none";
    document.querySelector("#orderEditForm").style.display = "none";
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
  fetch(finalRequest)
    .then((r) => r.json())
    .then((neworder) => {
      console.log("Added order: " + neworder.id);
    });

  //Clean form
  document.querySelector("input[name=totalAmount").value = "";
  document.querySelector("select[name=clientId").value = "1";
  document.querySelector("select[name=agentId").value = "1";
  document.querySelector("select[name=statusId").value = "1";
  document.querySelector("select[name=sectorId").value = "1";
}
////////// Method EDIT //////////
function editOrder() {
  // Request Definition.
  var request = server_url + "/api/order/add?";
  var finalRequest;

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
    "&clientid=" +
    clientId +
    "&agentid=" +
    agentId +
    "&statusid=" +
    statusId +
    "&sectorid=" +
    sectorId;

  finalRequest = editRequest;

  // Fetch request.
  fetch(finalRequest)
    .then((r) => r.json())
    .then((editedOrder) => {
      console.log("Edited order: " + editedOrder.id);
    });

  //Clean form
  document.querySelector("input[name=totalAmount").value = "";
  document.querySelector("select[name=clientId").value = "1";
  document.querySelector("select[name=agentId").value = "1";
  document.querySelector("select[name=statusId").value = "1";
  document.querySelector("select[name=sectorId").value = "1";
}
////////// Method DELETE //////////
function deleteOrder(event) {
  // Request Definition.
  var request = server_url + "/api/order/delete?";
  var finalRequest = "";
  // Get order atributes.
  // Get order atributes.
  var orderrow = event.currentTarget.closest("tr");
  var orderid = orderrow.querySelector(".id").innerHTML;

  var deleteRequest = request + "orderid=" + orderid;

  finalRequest = deleteRequest;

  // Fetch request.
  fetch(finalRequest)
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
    idCol.className = "filterTextDark";
    idCol.innerHTML = orders[i].id;
    row.appendChild(idCol);

    //  Date Column
    var dateCol = document.createElement("td");
    dateCol.className = "filterTextDark";
    dateCol.innerHTML = orders[i].creationDate;
    row.appendChild(dateCol);

    //  Client Column
    var clientCol = document.createElement("td");
    clientCol.className = "filterTextDark";
    clientCol.innerHTML = orders[i].client;
    row.appendChild(clientCol);

    //  Agent Column
    var agentCol = document.createElement("td");
    agentCol.className = "filterTextDark";
    agentCol.innerHTML = orders[i].agent;
    row.appendChild(agentCol);

    //  Total Amount Column
    var totalAmountCol = document.createElement("td");
    totalAmountCol.className = "filterTextDark";
    totalAmountCol.innerHTML = orders[i].totalAmount;
    row.appendChild(totalAmountCol);

    //  Total Status Column
    var statusCol = document.createElement("td");
    statusCol.className = "filterTextDark";
    statusCol.innerHTML = orders[i].status;
    row.appendChild(statusCol);

    //  Actions Column
    var actionsCol = document.createElement("td");
    actionsCol.className = "filterTextDark";
    var buttogroup = document.createElement("div");
    buttogroup.className = "btn-group";
    buttogroup.role = "group";

    var favbutton = document.createElement("button");
    favbutton.type = "button";
    favbutton.className = "btn btn-secondary";
    var favIcon = document.createElement("i");
    favIcon.className = "far fa-star";
    favIcon.addEventListener("click", function (event) {
      favoriteOrder(event);
    });
    favbutton.appendChild(favIcon);
    buttogroup.appendChild(favbutton);

    var editbutton = document.createElement("button");
    editbutton.type = "button";
    editbutton.className = "btn btn-secondary";
    var editIcon = document.createElement("i");
    editIcon.className = "fas fa-edit";
    editIcon.addEventListener("click", function (event) {
      showEditForm(event);
    });
    editbutton.appendChild(editIcon);
    buttogroup.appendChild(editbutton);

    var deletebutton = document.createElement("button");
    deletebutton.type = "button";
    deletebutton.className = "btn btn-secondary";
    var deleteIcon = document.createElement("i");
    deleteIcon.className = "fas fa-trash-alt";
    favIcon.addEventListener("click", function (event) {
      deleteOrder(event);
    });
    deletebutton.appendChild(deleteIcon);
    buttogroup.appendChild(deletebutton);

    actionsCol.appendChild(buttogroup);
    row.appendChild(actionsCol);
  }
}
