/* Coded with love by Igloo team. */
$(".toast").toast();
////////// Server URL //////////
var server_url = "http://localhost:8080";
var search_url = server_url + "/api/order/get?";
////////// Global VARs //////////
var currentPageGlobal = 1; // Current orders page
var selectedOrderId; // Selected order
var selectedOrdersIds = []; // Selected orders array
var selectedId; // Used on edit method
////////// Selection ///////////
function addToSelected(event) {
  var row = event.currentTarget.closest("tr");
  var id = row.querySelector(".idIndicator").innerHTML;
  if (selectedOrdersIds.includes(id)) {
    selectedOrdersIds.splice(selectedOrdersIds.indexOf(id), 1);
  } else {
    selectedOrdersIds.push(id);
  }
  if (selectedOrdersIds.length > 0) {
    document.querySelector("#batchDeleteButton").disabled = false;
    document.querySelector("#batchDeleteButton").className = "btn btn-add btn-lg btn-block filterText ";
  } else {
    document.querySelector("#batchDeleteButton").disabled = true;
    document.querySelector("#batchDeleteButton").className = "btn btn-del btn-lg btn-block filterText ";
  }
}
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
function fetchRequest(finalRequest, toast, globalSearch) {
  if (globalSearch) {
    search_url = finalRequest;
    console.log("Search url:" + search_url);
  }
  console.log("Fetch request to: " + finalRequest);
  fetch(finalRequest)
    .then((r) => r.json())
    .then((orders) => {
      cleanTable();
      fillTable(orders);
      if (toast != null) {
        $(document).ready(function () {
          {
            $(toast).toast("show");
          }
        });
      }
    });
  resetSelectedList();
}
////////// Utilities //////////
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
  getOrders(action, sortTerm, sortMethod, resetPage);
}
function resetSelectedList() {
  selectedOrdersIds = [];
}
function unHideCreationForm() {
  document.querySelector("#creationForm").style.display = "";
}
function hideCreationForm() {
  document.querySelector("#creationForm").style.display = "none";
}
function unHideEditForm() {
  document.querySelector("#editForm").style.display = "";
}
function hideEditForm() {
  document.querySelector("#editForm").style.display = "none";
}
function clearEditForm() {
  document.querySelector("input[name=totalAmountEdited]").value = "";
  document.querySelector("select[name=clientIdEdited]").value = "1";
  document.querySelector("select[name=agentIdEdited]").value = "1";
  document.querySelector("select[name=statusIdEdited]").value = "1";
  document.querySelector("select[name=sectorIdEdited]").value = "1";
}
function clearCreationForm() {
  document.querySelector("input[name=totalAmount]").value = "";
  document.querySelector("select[name=clientId]").value = "1";
  document.querySelector("select[name=agentId]").value = "1";
  document.querySelector("select[name=statusId]").value = "1";
  document.querySelector("select[name=sectorId]").value = "1";
}
function cleanTable() {
  document.querySelector("#orderList").innerHTML = "";
}
/////////////////////////////////
////////// CREATE form //////////
function showCreateForm() {
  hideEditForm();
  if (document.querySelector("#creationForm").style.display == "none") {
    unHideCreationForm();
  } else {
    hideCreationForm();
  }
}
function createOrder() {
  hideCreationForm();
  hideEditForm();
  unHideCreationForm();
  // Request Definition.
  var request = server_url + "/api/order/add?";
  var finalRequest;
  // Get order atributes.
  var totalAmount = document.querySelector("input[name=totalAmount]").value;
  if (totalAmount > 0) {
  } else {
    totalAmount = 0;
  }
  var clientId = document.querySelector("select[name=clientId]").value;
  var agentId = document.querySelector("select[name=agentId]").value;
  var statusId = document.querySelector("select[name=statusId]").value;
  var sectorId = document.querySelector("select[name=sectorId]").value;

  var creationRequest =
    request +
    "totalAmount=" +
    totalAmount +
    "&clientId=" +
    clientId +
    "&sectorId=" +
    sectorId +
    "&statusId=" +
    statusId +
    "&agentId=" +
    agentId;
  finalRequest = creationRequest;

  var auxUrl = search_url;
  auxUrl = auxUrl.split("?");

  if (auxUrl[1].length > 0) {
    finalRequest = finalRequest + "&page=" + currentPageGlobal + "&" + auxUrl[1];
  } else {
    finalRequest = finalRequest + "&page=" + currentPageGlobal;
  }

  fetchRequest(finalRequest, "#createToast");

  clearCreationForm();
  hideCreationForm();
}
///////////////////////////////
////////// EDIT form //////////
function showEditForm(event) {
  hideCreationForm();
  hideEditForm();
  unHideEditForm();
  // Find selected Order ID
  var selectedOrderRow = event.currentTarget.closest("tr");
  selectedOrderId = selectedOrderRow.querySelector(".idIndicator").innerHTML;
  selectedId = selectedOrderId;

  // Fetch request.
  fetch("/api/order/find?id=" + selectedOrderId)
    .then((r) => r.json())
    .then((orderToEdit) => {
      console.log("Selected order: ", orderToEdit);
      // Get order old atributes.
      document.querySelector("input[name=totalAmountEdited]").value = orderToEdit.totalAmount;
      document.querySelector("select[name=clientIdEdited]").getElementsByTagName("option")[
        orderToEdit.client.id - 1
      ].selected = "selected";
      console.log(document.querySelector("select[name=statusIdEdited]").getElementsByTagName("option"));
      document.querySelector("select[name=statusIdEdited]").getElementsByTagName("option")[
        orderToEdit.status.id - 1
      ].selected = "selected";
      document.querySelector("select[name=agentIdEdited]").getElementsByTagName("option")[
        orderToEdit.agent.id - 1
      ].selected = "selected";
      document.querySelector("select[name=sectorIdEdited]").getElementsByTagName("option")[
        orderToEdit.sector.id - 1
      ].selected = "selected";
    });
}
function editOrder() {
  var selectedOrderRow = selectedId;
  // Request Definition.
  var request = server_url + "/api/order/add?id=" + selectedOrderRow + "&";
  var finalRequest;
  // Get order new atributes.
  var totalAmount = document.querySelector("input[name=totalAmountEdited]").value;
  if (totalAmount > 0) {
  } else {
    totalAmount = 0;
  }
  var clientId = document.querySelector("select[name=clientIdEdited]").value;
  var agentId = document.querySelector("select[name=agentIdEdited]").value;
  var statusId = document.querySelector("select[name=statusIdEdited]").value;
  var sectorId = document.querySelector("select[name=sectorIdEdited]").value;

  var editRequest =
    request +
    "totalAmount=" +
    totalAmount +
    "&clientId=" +
    clientId +
    "&sectorId=" +
    sectorId +
    "&statusId=" +
    statusId +
    "&agentId=" +
    agentId;
  finalRequest = editRequest;

  var auxUrl = search_url;
  auxUrl = auxUrl.split("?");
  if (auxUrl[1].length > 0) {
    finalRequest = finalRequest + "&page=" + currentPageGlobal + "&" + auxUrl[1];
  } else {
    finalRequest = finalRequest + "&page=" + currentPageGlobal;
  }
  // Fetch request.
  console.log(finalRequest);
  fetchRequest(finalRequest, "#editToast", false);

  clearEditForm();
  hideEditForm();
}
////////////////////////////////
////////// Method GET //////////
function getOrders(action, sortTerm, sortMethod, resetPage) {
  if (resetPage) {
    currentPageGlobal = 1;
    let currentPage = currentPageGlobal;
    updatePages(currentPage);
  }
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
      // Simple get all request
      finalRequest = request;
  }
  // Fetch request.
  fetchRequest(finalRequest, null, true);
}
///////////////////////////////////
////////// Method DELETE //////////
function markForDelete(event) {
  console.log("Selecting order");
  var selectedOrderRow = event.currentTarget.closest("tr");
  selectedOrderId = selectedOrderRow.querySelector(".idIndicator").innerHTML;
  console.log("Selected order: " + selectedOrderId);
}
function deleteOrder() {
  if (selectedOrderId == null) {
    console.log("Error");
  } else {
    // Request Definition.
    var request = server_url + "/api/order/delete?";
    var finalRequest = "";
    // Find selected Order ID
    var deleteRequest = request + "id=" + selectedOrderId;
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
    selectedOrderId = null;
  }
}
/////////////////////////////////////////
////////// Method Batch DELETE //////////
function batchDelete() {
  if (selectedOrdersIds.length > 0) {
    // Request Definition.
    var request = server_url + "/api/order/delete?";
    var finalRequest = "";

    // Find selected Order ID
    var deleteRequest = request + "id=" + selectedOrdersIds.join(",");
    finalRequest = deleteRequest;
    var auxUrl = search_url;
    auxUrl = auxUrl.split("?");

    if (auxUrl[1].length > 0) {
      finalRequest = finalRequest + "&page=" + currentPageGlobal + "&" + auxUrl[1];
    } else {
      finalRequest = finalRequest + "&page=" + currentPageGlobal;
    }
    console.log("Batch delete " + finalRequest);
    fetchRequest(finalRequest, "#deleteToast", false);
    selectedOrdersIds = [];
    document.querySelector("#batchDeleteButton").disabled = true;
    document.querySelector("#batchDeleteButton").className = "btn btn-del btn-lg btn-block filterText ";
  }
}

///////////////////////////////////////
////////// Method Fill Table //////////
function fillTable(orders) {
  if (orders.length == 0) {
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
  for (i in orders) {
    // Row
    var row = document.createElement("tr");

    // Selection Column
    var selectionCol = document.createElement("td");
    var checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    checkbox.addEventListener("change", function (event) {
      addToSelected(event);
    });
    selectionCol.appendChild(checkbox);
    row.appendChild(selectionCol);

    //  ID Column
    var idCol = document.createElement("td");
    idCol.className = "filterTextDark  idIndicator";
    idCol.innerHTML = orders[i].id;
    row.appendChild(idCol);

    //  Creation Date Column
    var creationDateCol = document.createElement("td");
    creationDateCol.className = "filterTextDark";
    creationDateCol.innerHTML = orders[i].creationDate;
    row.appendChild(creationDateCol);

    //  Client Column
    var clientCol = document.createElement("td");
    clientCol.className = "filterTextDark";
    clientCol.innerHTML = orders[i].client.firstName + " " + orders[i].client.lastName;
    row.appendChild(clientCol);

    //  Agent Column
    var agentCol = document.createElement("td");
    agentCol.className = "filterTextDark";
    agentCol.innerHTML = orders[i].agent.firstName + " " + orders[i].agent.lastName;
    row.appendChild(agentCol);

    // Amount Column
    var totalAmount = document.createElement("td");
    totalAmount.className = "filterTextDark";
    totalAmount.innerHTML = orders[i].totalAmount;
    row.appendChild(totalAmount);

    //  Status Column
    var statusCol = document.createElement("td");
    statusCol.className = "filterTextDark";
    statusCol.innerHTML = orders[i].status.name;
    row.appendChild(statusCol);

    //  Sector Column
    var sectorCol = document.createElement("td");
    sectorCol.className = "filterTextDark";
    sectorCol.innerHTML = orders[i].sector.name;
    row.appendChild(sectorCol);

    //  Actions Column
    var actionsCol = document.createElement("td");
    actionsCol.className = "filterTextDark";
    var buttogroup = document.createElement("div");
    buttogroup.className = "btn-group";
    buttogroup.role = "group";

    var editbutton = document.createElement("button");
    editbutton.type = "button";
    editbutton.className = "btn btn-secondary";
    editbutton.addEventListener("click", function (event) {
      showEditForm(event);
    });
    var editIcon = document.createElement("i");
    editIcon.className = "fas fa-edit";
    editbutton.appendChild(editIcon);
    buttogroup.appendChild(editbutton);

    var deleteButton = document.createElement("button");
    deleteButton.type = "button";
    deleteButton.className = "btn btn-secondary deleteButton";
    var deleteIcon = document.createElement("i");
    deleteIcon.className = "fas fa-trash-alt";
    deleteButton.addEventListener("click", function (event) {
      markForDelete(event);
    });
    deleteButton.setAttribute("data-target", "#deleteModal");
    deleteButton.setAttribute("data-toggle", "modal");
    deleteButton.appendChild(deleteIcon);
    buttogroup.appendChild(deleteButton);
    actionsCol.appendChild(buttogroup);
    row.appendChild(actionsCol);
    document.querySelector("#orderList").appendChild(row);
  }
}
